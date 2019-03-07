package org.springframework.context.annotation;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AspectJTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ComponentScanBeanDefinitionParser
  implements BeanDefinitionParser
{
  private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";
  private static final String RESOURCE_PATTERN_ATTRIBUTE = "resource-pattern";
  private static final String USE_DEFAULT_FILTERS_ATTRIBUTE = "use-default-filters";
  private static final String ANNOTATION_CONFIG_ATTRIBUTE = "annotation-config";
  private static final String NAME_GENERATOR_ATTRIBUTE = "name-generator";
  private static final String SCOPE_RESOLVER_ATTRIBUTE = "scope-resolver";
  private static final String SCOPED_PROXY_ATTRIBUTE = "scoped-proxy";
  private static final String EXCLUDE_FILTER_ELEMENT = "exclude-filter";
  private static final String INCLUDE_FILTER_ELEMENT = "include-filter";
  private static final String FILTER_TYPE_ATTRIBUTE = "type";
  private static final String FILTER_EXPRESSION_ATTRIBUTE = "expression";
  public static final int JAVA_18 = 5;

  public BeanDefinition parse(Element element, ParserContext parserContext)
  {
    String[] basePackages = StringUtils.commaDelimitedListToStringArray(element.getAttribute("base-package"));

    ClassPathBeanDefinitionScanner scanner = configureScanner(parserContext, element);
    Set beanDefinitions = scanner.doScan(basePackages);
    registerComponents(parserContext.getReaderContext(), beanDefinitions, element);

    return null;
  }

  protected ClassPathBeanDefinitionScanner configureScanner(ParserContext parserContext, Element element) {
    XmlReaderContext readerContext = parserContext.getReaderContext();

    boolean useDefaultFilters = true;
    if (element.hasAttribute("use-default-filters")) {
      useDefaultFilters = Boolean.valueOf(element.getAttribute("use-default-filters")).booleanValue();
    }

    ClassPathBeanDefinitionScanner scanner = createScanner(readerContext, useDefaultFilters);
    scanner.setResourceLoader(readerContext.getResourceLoader());
    scanner.setBeanDefinitionDefaults(parserContext.getDelegate().getBeanDefinitionDefaults());
    scanner.setAutowireCandidatePatterns(parserContext.getDelegate().getAutowireCandidatePatterns());

    if (element.hasAttribute("resource-pattern")) {
      scanner.setResourcePattern(element.getAttribute("resource-pattern"));
    }
    try
    {
      parseBeanNameGenerator(element, scanner);
    }
    catch (Exception ex) {
      readerContext.error(ex.getMessage(), readerContext.extractSource(element), ex.getCause());
    }
    try
    {
      parseScope(element, scanner);
    }
    catch (Exception ex) {
      readerContext.error(ex.getMessage(), readerContext.extractSource(element), ex.getCause());
    }

    parseTypeFilters(element, scanner, readerContext);

    return scanner;
  }

  protected ClassPathBeanDefinitionScanner createScanner(XmlReaderContext readerContext, boolean useDefaultFilters) {
    return new ClassPathBeanDefinitionScanner(readerContext.getRegistry(), useDefaultFilters);
  }

  protected void registerComponents(XmlReaderContext readerContext, Set<BeanDefinitionHolder> beanDefinitions, Element element)
  {
    Object source = readerContext.extractSource(element);
    CompositeComponentDefinition compositeDef = new CompositeComponentDefinition(element.getTagName(), source);

    for (Iterator it = beanDefinitions.iterator(); it.hasNext(); ) {
      BeanDefinitionHolder beanDefHolder = (BeanDefinitionHolder)it.next();
      compositeDef.addNestedComponent(new BeanComponentDefinition(beanDefHolder));
    }

    boolean annotationConfig = true;
    if (element.hasAttribute("annotation-config")) {
      annotationConfig = Boolean.valueOf(element.getAttribute("annotation-config")).booleanValue();
    }
    if (annotationConfig) {
      Set processorDefinitions = AnnotationConfigUtils.registerAnnotationConfigProcessors(readerContext.getRegistry(), source);

      for (BeanDefinitionHolder processorDefinition : processorDefinitions) {
        compositeDef.addNestedComponent(new BeanComponentDefinition(processorDefinition));
      }
    }

    readerContext.fireComponentRegistered(compositeDef);
  }

  protected void parseBeanNameGenerator(Element element, ClassPathBeanDefinitionScanner scanner) {
    if (element.hasAttribute("name-generator")) {
      BeanNameGenerator beanNameGenerator = (BeanNameGenerator)instantiateUserDefinedStrategy(element.getAttribute("name-generator"), BeanNameGenerator.class, scanner.getResourceLoader().getClassLoader());

      scanner.setBeanNameGenerator(beanNameGenerator);
    }
  }

  protected void parseScope(Element element, ClassPathBeanDefinitionScanner scanner)
  {
    if (element.hasAttribute("scope-resolver")) {
      if (element.hasAttribute("scoped-proxy")) {
        throw new IllegalArgumentException("Cannot define both 'scope-resolver' and 'scoped-proxy' on <component-scan> tag");
      }

      ScopeMetadataResolver scopeMetadataResolver = (ScopeMetadataResolver)instantiateUserDefinedStrategy(element.getAttribute("scope-resolver"), ScopeMetadataResolver.class, scanner.getResourceLoader().getClassLoader());

      scanner.setScopeMetadataResolver(scopeMetadataResolver);
    }

    if (element.hasAttribute("scoped-proxy")) {
      String mode = element.getAttribute("scoped-proxy");
      if ("targetClass".equals(mode)) {
        scanner.setScopedProxyMode(ScopedProxyMode.TARGET_CLASS);
      }
      else if ("interfaces".equals(mode)) {
        scanner.setScopedProxyMode(ScopedProxyMode.INTERFACES);
      }
      else if ("no".equals(mode)) {
        scanner.setScopedProxyMode(ScopedProxyMode.NO);
      }
      else
        throw new IllegalArgumentException("scoped-proxy only supports 'no', 'interfaces' and 'targetClass'");
    }
  }

  protected void parseTypeFilters(Element element, ClassPathBeanDefinitionScanner scanner, XmlReaderContext readerContext)
  {
    ClassLoader classLoader = scanner.getResourceLoader().getClassLoader();
    NodeList nodeList = element.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      if (node.getNodeType() == 1) {
        String localName = node.getLocalName();
        try {
          if ("include-filter".equals(localName)) {
            TypeFilter typeFilter = createTypeFilter((Element)node, classLoader);
            scanner.addIncludeFilter(typeFilter);
          }
          else if ("exclude-filter".equals(localName)) {
            TypeFilter typeFilter = createTypeFilter((Element)node, classLoader);
            scanner.addExcludeFilter(typeFilter);
          }
        }
        catch (Exception ex) {
          readerContext.error(ex.getMessage(), readerContext.extractSource(element), ex.getCause());
        }
      }
    }
  }

  protected TypeFilter createTypeFilter(Element element, ClassLoader classLoader)
  {
    String filterType = element.getAttribute("type");
    String expression = element.getAttribute("expression");
    try {
      if ("annotation".equals(filterType)) {
        return new AnnotationTypeFilter(classLoader.loadClass(expression));
      }
      if ("assignable".equals(filterType)) {
        return new AssignableTypeFilter(classLoader.loadClass(expression));
      }
      if ("aspectj".equals(filterType)) {
        return new AspectJTypeFilter(expression, classLoader);
      }
      if ("regex".equals(filterType)) {
        return new RegexPatternTypeFilter(Pattern.compile(expression));
      }
      if ("custom".equals(filterType)) {
        Class filterClass = classLoader.loadClass(expression);
        if (!TypeFilter.class.isAssignableFrom(filterClass)) {
          throw new IllegalArgumentException("Class is not assignable to [" + TypeFilter.class.getName() + "]: " + expression);
        }

        return (TypeFilter)BeanUtils.instantiateClass(filterClass);
      }

      throw new IllegalArgumentException("Unsupported filter type: " + filterType);
    }
    catch (ClassNotFoundException ex)
    {
      throw new FatalBeanException("Type filter class not found: " + expression, ex);
    }
  }

  private Object instantiateUserDefinedStrategy(String className, Class strategyType, ClassLoader classLoader)
  {
    Object result = null;
    try {
      result = classLoader.loadClass(className).newInstance();
    }
    catch (ClassNotFoundException ex) {
      throw new IllegalArgumentException("Class [" + className + "] for strategy [" + strategyType.getName() + "] not found", ex);
    }
    catch (Exception ex)
    {
      throw new IllegalArgumentException("Unable to instantiate class [" + className + "] for strategy [" + strategyType.getName() + "]. A zero-argument constructor is required", ex);
    }

    if (!strategyType.isAssignableFrom(result.getClass())) {
      throw new IllegalArgumentException("Provided class name must be an implementation of " + strategyType);
    }
    return result;
  }
}