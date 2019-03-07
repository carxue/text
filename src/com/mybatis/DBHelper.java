package com.mybatis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class DBHelper {
	private static String dburl;
	private static String name;
	private static String password;
	private static String driver;

	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static CallableStatement call;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dburl = Config.getValue("dburl");
			name = Config.getValue("name");
			password = Config.getValue("password");
			driver = Config.getValue("driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(dburl, name, password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void productXML(String table,boolean isTuoFeng) throws SQLException, FileNotFoundException,
			Exception {
		conn = DriverManager.getConnection(dburl, name, password);
		Statement st = conn.createStatement();
		Statement stComments = conn.createStatement();
		rs = st.executeQuery("select * from "+table);
		ResultSet rsComments = stComments.executeQuery("select t.comments,t.column_name from user_col_comments t where table_name='"+table.toUpperCase()+"'");
		Map<String,Object> commnetsMap = new HashMap<String,Object>();
		while(rsComments.next()){
			commnetsMap.put(rsComments.getString("column_name"), rsComments.getString("comments"));
		}
		
		ResultSetMetaData rsm = rs.getMetaData();
		if(isTuoFeng)
			createResultMap1(rsm,table,commnetsMap);
		else
			createResultMap(rsm,table,commnetsMap);
	}

	/**
	 * 生存mybatis配置文件
	 * @param rsm 结果集
	 * @param table 表明
	 * @throws Exception
	 */
	public static void createResultMap(ResultSetMetaData rsm,String table,Map<String,Object> commnetsMap) throws Exception {
		
		String url = DBHelper.class.getClassLoader().getResource("mybait.xml")
				.toURI().getPath();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(url));
		Element root=document.getRootElement();
		
//		Element mapper = root.addElement("mapper");
		
		Element date = root.addElement("WARNMING");
		date.addCDATA("请注意生成的insert语句中的date类型，要根据需要自行修改！");
		//生存所有的字段
		Element columns = root.addElement("sql");
		columns.addAttribute("id", "薛奎Column");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String column=rsm.getColumnName(i + 1);
			if(i<rsm.getColumnCount()-1){
				sb.append(column+",");
				if((i+1)%10==0)
					sb.append("\n");
			}else
				sb.append(column);
		}
		columns.addCDATA(sb.toString());
		
		//生成List返回类型
		Element resultMap = root.addElement("resultMap");
		resultMap.addAttribute("type", "薛奎");
		resultMap.addAttribute("id","薛奎" );
		
		int count = rsm.getColumnCount();
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			Element result=resultMap.addElement("result");
			result.addAttribute("column", rsm.getColumnName(i + 1).toLowerCase());
			result.addAttribute("property",rsm.getColumnName(i + 1).toLowerCase() );
		}
		
		//生成插入动态语句
		Element insert = root.addElement("insert");
		insert.addAttribute("parameterType", "****");
		insert.addAttribute("id","****" );
		insert.setText("INSERT INTO "+table.toUpperCase());
		
		Element trim=insert.addElement("trim");
		trim.addAttribute("prefix", "(");
		trim.addAttribute("suffix", ")");
		trim.addAttribute("suffixOverrides", ",");
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			Element ifs=trim.addElement("if");
			String column=rsm.getColumnName(i + 1).toLowerCase();
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				ifs.addAttribute("test",column+" !=null");
			else
				ifs.addAttribute("test",column+" !=null and "+column+" != ''");
			
			if(i+1!=rsm.getColumnCount()){
				ifs.setText(column.toUpperCase()+",");
			}else{
				ifs.setText(column.toUpperCase());
			}
			
		}
		
		Element trim1=insert.addElement("trim");
		trim1.addAttribute("prefix", "VALUES (");
		trim1.addAttribute("suffix", ")");
		trim1.addAttribute("suffixOverrides", ",");
		
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			Element ifs=trim1.addElement("if");
			String column=rsm.getColumnName(i + 1).toLowerCase();
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				ifs.addAttribute("test",column+" !=null");
			else
				ifs.addAttribute("test",column+" !=null and "+column+" != ''");
			
			if("NUMBER".equals(typename))
				typename = "DECIMAL";
			else if("VARCHAR2".equals(typename))
				typename = "VARCHAR";
			if(i+1!=rsm.getColumnCount()){
				ifs.setText("#{"+column+",jdbcType="+typename+"},");
			}else{
				ifs.setText("#{"+column+",jdbcType="+typename+"}");
			}
			
		}
		
		//生成动态更新语句
		Element update = root.addElement("update");
		update.setText("UPDATE "+table.toUpperCase());
		update.addAttribute("parameterType", "薛奎");
		update.addAttribute("id","薛奎" );
		Element set=update.addElement("set");
		
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			Element ifs=set.addElement("if");
			String column=rsm.getColumnName(i + 1).toLowerCase();
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				ifs.addAttribute("test",column+" !=null");
			else
				ifs.addAttribute("test",column+" !=null and "+column+" != ''");
			
			if(i+1!=rsm.getColumnCount()){
				ifs.setText(column.toUpperCase()+"=#{"+column+"},");
			}else{
				ifs.setText(column.toUpperCase()+"=#{"+column+"}");
			}
			
		}

		//生成插入动态语句
		StringBuffer sbb = new StringBuffer();
		Element insertother = root.addElement("insert");
		insertother.addAttribute("parameterType", "薛奎");
		insertother.addAttribute("id","薛奎" );
		
		sbb.append("INSERT INTO "+table.toUpperCase()+"(");

		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String column=rsm.getColumnName(i + 1).toUpperCase();
			if(i+1!=rsm.getColumnCount()){
				sbb.append(column+", ");
			}else{
				sbb.append(column+" )");
			}
		}
		sbb.append("VALUES (");
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String column=rsm.getColumnName(i + 1).toUpperCase();
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				typename = "DECIMAL";
			else if("VARCHAR2".equals(typename))
				typename = "VARCHAR";
				
			if(i+1!=rsm.getColumnCount()){
				sbb.append(" #{"+column.toLowerCase()+", jdbcType="+typename+"},");
			}else{
				sbb.append(" #{"+column.toLowerCase()+", jdbcType="+typename+"}");
			}
		}
		sbb.append(" )");
		insertother.setText(sbb.toString());
		
		
		//生成java文件内容
		StringBuffer javaSB = new StringBuffer();
		Element javaAttr = root.addElement("java");
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String columnv=rsm.getColumnName(i + 1).toLowerCase();
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				typename = "Integer";
			else if("VARCHAR2".equals(typename))
				typename = "String";
			else if("DATE".equals(typename))
				typename = "Date";
			javaSB.append("\n/**\n*"+commnetsMap.get(columnv.toUpperCase())+"\n*/\n");
			javaSB.append("private "+typename+" "+columnv+";\n");
		}
		javaAttr.addCDATA(javaSB.toString());
				
				
		//生产java属性带双引号
		Element javaAttrs = root.addElement("javaAttr");
		StringBuffer attrSB = new StringBuffer();
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String column=rsm.getColumnName(i + 1).toLowerCase();
			if(i<rsm.getColumnCount()-1){
				attrSB.append("\""+column+"\",");
				if((i+1)%10==0)
					attrSB.append("\n");
			}else
				attrSB.append("\""+column+"\"");
		}
		javaAttrs.addCDATA(attrSB.toString());
				
		
				
		org.dom4j.io.OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");//这就是已经设置了输出格式或编码集
		format.setSuppressDeclaration(true);
		format.setIndent(true);//是否缩进
		format.setIndent(" ");//以空格方式缩进
		format.setNewlines(true);//设置是否换行
	    //实例化MLWriter对象
		XMLWriter writer=new XMLWriter(new FileOutputStream(url), format);
        writer.write(document);
        writer.flush();
        writer.close();
        
        
        SAXReader saxReader1 = new SAXReader();
		Document document1 = saxReader1.read(url);
		System.out.println(document1.asXML());
        
	}
	
	/**
	 * 驼峰式属性生成方法
	 * @param rsm
	 * @param table
	 * @throws Exception
	 */
	public static void createResultMap1(ResultSetMetaData rsm,String table,Map<String,Object> commnetsMap) throws Exception {
		
		String url = DBHelper.class.getClassLoader().getResource("mybait.xml")
				.toURI().getPath();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(url));
		Element root=document.getRootElement();
		
//		Element mapper = root.addElement("mapper");
		
		Element date = root.addElement("WARNMING");
		date.addCDATA("请注意生成的insert语句中的date类型，要根据需要自行修改！");
		
		//生存所有的字段
		Element columns = root.addElement("sql");
		columns.addAttribute("id", "薛奎Column");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String column=rsm.getColumnName(i + 1).toUpperCase();
			if(i<rsm.getColumnCount()-1){
				sb.append(column+",");
				if((i+1)%10==0)
					sb.append("\n");
			}else
				sb.append(column);
		}
		columns.addCDATA(sb.toString());
		
		//生成List返回类型
		Element resultMap = root.addElement("resultMap");
		resultMap.addAttribute("type", "薛奎");
		resultMap.addAttribute("id","薛奎" );
		
		int count = rsm.getColumnCount();
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			Element result=resultMap.addElement("result");
			result.addAttribute("column", rsm.getColumnName(i + 1).toLowerCase());
			result.addAttribute("property",convert(rsm.getColumnName(i + 1).toLowerCase()));
		}
		
		
		//生成插入动态语句
		Element insert = root.addElement("insert");
		insert.addAttribute("parameterType", "****");
		insert.addAttribute("id","****" );
		insert.setText("INSERT INTO "+table.toUpperCase());
		
		Element trim=insert.addElement("trim");
		trim.addAttribute("prefix", "(");
		trim.addAttribute("suffix", ")");
		trim.addAttribute("suffixOverrides", ",");
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			Element ifs=trim.addElement("if");
			String column=convert(rsm.getColumnName(i + 1).toLowerCase());
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				ifs.addAttribute("test",column+" !=null");
			else
				ifs.addAttribute("test",column+" !=null and "+column+" != ''");
			
			if(i+1!=rsm.getColumnCount()){
				ifs.setText(rsm.getColumnName(i + 1).toUpperCase()+",");
			}else{
				ifs.setText(rsm.getColumnName(i + 1).toUpperCase());
			}
			
		}
		
		Element trim1=insert.addElement("trim");
		trim1.addAttribute("prefix", "VALUES (");
		trim1.addAttribute("suffix", ")");
		trim1.addAttribute("suffixOverrides", ",");
		
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			Element ifs=trim1.addElement("if");
			String column=convert(rsm.getColumnName(i + 1).toLowerCase());
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				ifs.addAttribute("test",column+" !=null");
			else
				ifs.addAttribute("test",column+" !=null and "+column+" != ''");
			
			if("NUMBER".equals(typename))
				typename = "DECIMAL";
			else if("VARCHAR2".equals(typename))
				typename = "VARCHAR";
			
			if(i+1!=rsm.getColumnCount()){
				ifs.setText("#{"+column+",jdbcType="+typename+"},");
			}else{
				ifs.setText("#{"+column+",jdbcType="+typename+"}");
			}
			
		}
		
		
		//生成动态更新语句
		Element update = root.addElement("update");
		update.setText("UPDATE "+table.toUpperCase());
		update.addAttribute("parameterType", "薛奎");
		update.addAttribute("id","薛奎 " );
		Element set=update.addElement("set");
		
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			Element ifs=set.addElement("if");
			String columnss=rsm.getColumnName(i + 1).toLowerCase();
			String column = convert(columnss);
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				ifs.addAttribute("test",column+" !=null");
			else
				ifs.addAttribute("test",column+" !=null and "+column+" != ''");
			
			if(i+1!=rsm.getColumnCount()){
				ifs.setText(columnss.toUpperCase()+"=#{"+column+"},");
			}else{
				ifs.setText(columnss.toUpperCase()+"=#{"+column+"}");
			}
			
		}

		//生成插入动态语句
		StringBuffer sbb = new StringBuffer();
		Element insertother = root.addElement("insert");
		insertother.addAttribute("parameterType", "薛奎");
		insertother.addAttribute("id","薛奎" );
		
		sbb.append("INSERT INTO "+table.toUpperCase()+"(");

		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String column=rsm.getColumnName(i + 1).toUpperCase();
			if(i+1!=rsm.getColumnCount()){
				sbb.append(column+", ");
			}else{
				sbb.append(column+" )");
			}
		}
		sbb.append("VALUES (");
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String columnv=rsm.getColumnName(i + 1).toLowerCase();
			columnv = convert(columnv);
			
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				typename = "DECIMAL";
			else if("VARCHAR2".equals(typename))
				typename = "VARCHAR";
				
			if(i+1!=rsm.getColumnCount()){
				sbb.append(" #{"+columnv+", jdbcType="+typename+"},");
			}else{
				sbb.append(" #{"+columnv+", jdbcType="+typename+"}");
			}
		}
		sbb.append(" )");
		insertother.setText(sbb.toString());
		
		//生成java文件内容
		StringBuffer javaSB = new StringBuffer();
		Element javaAttr = root.addElement("java");
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String columnv=rsm.getColumnName(i + 1).toLowerCase();
			columnv = convert(columnv);
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				typename = "Integer";
			else if("VARCHAR2".equals(typename))
				typename = "String";
			else if("DATE".equals(typename))
				typename = "Date";
			javaSB.append("\n/**\n*"+commnetsMap.get(rsm.getColumnName(i + 1).toUpperCase())+"\n*/\n");
			javaSB.append("private "+typename+" "+columnv+";\n");
		}
		javaAttr.addCDATA(javaSB.toString());
		
		//生产java属性带双引号
		Element javaAttrs = root.addElement("javaAttr");
		StringBuffer attrSB = new StringBuffer();
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String column=rsm.getColumnName(i + 1).toLowerCase();
			column = convert(column);
			if(i<rsm.getColumnCount()-1){
				attrSB.append("\""+column+"\",");
				if((i+1)%10==0)
					attrSB.append("\n");
			}else
				attrSB.append("\""+column+"\"");
		}
		javaAttrs.addCDATA(attrSB.toString());
		
		
		
		//生成hibernate映射文件
		for (int i = 0; i < rsm.getColumnCount(); i++) {
			String columnv=rsm.getColumnName(i + 1).toLowerCase();
			columnv = convert(columnv);
			String typename = rsm.getColumnTypeName(i + 1);//字段类型明
			if("NUMBER".equals(typename))
				typename = "java.lang.Double";
			else if("VARCHAR2".equals(typename)||"CHAR".equals(typename))
				typename = "java.lang.String";
			else if("DATE".equals(typename))
				typename = "java.util.Date";
			Element hibernateProperty = root.addElement("property");
			hibernateProperty.addAttribute("name", columnv);
			hibernateProperty.addAttribute("type", typename);
			hibernateProperty.addAttribute("column", rsm.getColumnName(i + 1).toUpperCase());
		}
				
		
		org.dom4j.io.OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");//这就是已经设置了输出格式或编码集
		format.setSuppressDeclaration(true);
		format.setIndent(true);//是否缩进
		format.setIndent(" ");//以空格方式缩进
		format.setNewlines(true);//设置是否换行
	    //实例化MLWriter对象
		XMLWriter writer=new XMLWriter(new FileOutputStream(url), format);
        writer.write(document);
        writer.flush();
        writer.close();
        
        
        SAXReader saxReader1 = new SAXReader();
		Document document1 = saxReader1.read(url);
		System.out.println(document1.asXML());
        
	}
	
	/**
	 * 驼峰式属性转换
	 * @param name
	 * @return
	 */
	public static String convert(String name){
		  String[] splists = Pattern.compile("[_]").split(name);
		  String ret = "";
		    for(int i=0;i<splists.length;i++){
		    	String a = splists[i];
		    	if(i==0){
		    		ret=a;
		    	}else{
		    		ret += a.substring(0,1).toUpperCase()+a.substring(1,a.length());
		    	}
		    }
		   return ret;	
	}
	
	/**
	 * 清除旧的生产文件
	 * @throws Exception
	 */
	public static void clearXml(String xmlPath) throws Exception{
		
		String url = DBHelper.class.getClassLoader().getResource(xmlPath).toURI().getPath();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(url));
		Element root=document.getRootElement();
		List<Element> lists = root.elements();
		for(Element element:lists)
			root.remove(element);
		org.dom4j.io.OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");//这就是已经设置了输出格式或编码集
		format.setSuppressDeclaration(true);
		format.setIndent(true);//是否缩进
		format.setIndent(" ");//以空格方式缩进
		format.setNewlines(true);//设置是否换行
	    //实例化MLWriter对象
		XMLWriter writer=new XMLWriter(new FileOutputStream(url), format);
        writer.write(document);
        writer.flush();
        writer.close();
		System.out.println(document.asXML());
	}
	
	

	public static void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData rmsd = rs.getMetaData();
		System.out.println(rmsd.getColumnCount() + ":------------------\n");
		for (int i = 0; i < rmsd.getColumnCount(); i++) {
			System.out.println(rmsd.getColumnName(i + 1) + "\t");
		}
		System.out.println("------------------\n");
	}

	/*
	 * @author:薛奎
	 * 
	 * @date:2013-12-12
	 * 
	 * @function：更新数据操作
	 */
	public static boolean exeuteUpdate(String[] sql, String[][] parameters) {
		boolean flag = true;
		try {
			conn = DriverManager.getConnection(dburl, name, password);
			conn.setAutoCommit(false);
			for (int i = 0; i < sql.length; i++) {
				ps = conn.prepareStatement(sql[i]);
				if (parameters != null) {
					for (int j = 0; j < parameters[i].length; j++)
						ps.setString(j + 1, parameters[i][j]);
				}
				ps.executeUpdate();
			}
			conn.commit();
		}
		catch (SQLException e) {
			try {
				flag = false;
				conn.rollback();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			close(null, ps, conn);
		}
		return flag;
	}

	/*
	 * @author:薛奎
	 * 
	 * @date:2013-12-12
	 * 
	 * @function：查询数据操作
	 */
	public static ResultSet exeuteQuery(String sql, String[] parameters) {
		try {
			conn = DriverManager.getConnection(dburl, name, password);
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++)
					ps.setString(i + 1, parameters[i]);
			}
			rs = ps.executeQuery();
			conn.commit();
		}
		catch (SQLException e) {
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			// close(rs, ps, conn);
		}
		return rs;
	}

	/*
	 * @author:薛奎
	 * 
	 * @date:2013-12-12
	 * 
	 * @function：调用存储过程
	 */
	public static void callProcedure(String sql, String[] parameters) {
		try {
			conn = DriverManager.getConnection(dburl, name, password);
			call = conn.prepareCall(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					call.setString(i + 1, parameters[i]);
				}
			}
			call.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (call != null) {
				try {
					call.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBHelper.close(null, null, conn);
		}
	}

	/*
	 * @author:薛奎
	 * 
	 * @date:2013-12-12
	 * 
	 * @function：关闭数据库
	 */
	public static void close(ResultSet rs, Statement ps, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		rs = null;
		if (ps != null) {
			try {
				ps.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ps = null;
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		conn = null;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void main(String[] args) throws Exception, Exception {
		
		
		
		clearXml("mybait.xml");//清除旧的生成文件
		productXML("SCL_USER",true);//是否生成驼峰式/数据源格式
		
		
		
		
		
		
		// String sql = "update emp set sal=sal-10 where empno=7369";
		// String sql1 = "update emp set sal=sal+10 where empno=7499";
		// String[] sqlarr = { sql, sql1 };
		// DBHelper.exeuteUpdate(sqlarr, null);

		// 调用存储过程
		// String callsql="{Call empnotoename("+1234+")}";
		// DBHelper.callProcedure(callsql, null);

		// 调用函数
		// String funsql = "select select1('XUEKUI') from dual";
		// ResultSet sss = DBHelper.exeuteQuery(funsql, null);
		// try {
		// if (sss.next()) {
		// System.out.println(sss.getDouble(1));
		// }
		// }
		// catch (SQLException e) {
		// e.printStackTrace();
		// }

		// 调用包体
		// String callsql="{call scott.pactest.updatesal('TURNER',2000)}";
		// DBHelper.callProcedure(callsql, null);

		// String callsql = "{call out_value(?,?)}";
		// String callcursor="{call out_cursor(?,?)}";
		// conn = DriverManager.getConnection(dburl, name, password);
		// call = conn.prepareCall(callsql);
		// call.setString(1, "7839");
		// call.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
		// call.execute();
		// 取出输出的值
		// String ename= call.getString(2);//获得存储过程输出的值，2为第二个？
		// System.out.println(ename);
		//		
		// call=conn.prepareCall(callcursor);
		// call.setInt(1, 20);
		// call.registerOutParameter(2, OracleTypes.CURSOR);
		// call.execute();
		// Object cursor=call.getObject(2);
		// ResultSet set=(ResultSet) cursor;
		// while(set.next()){
		// System.out.println(set.getString(1)+" : "+set.getString(2));
		// }

		// 分页存储过程
		// String callpage="{call fenye_page(?,?,?,?,?,?)}";
		// call=conn.prepareCall(callpage);
		// call.setString(1, "emp");
		// call.setInt(2, 2);
		// call.setInt(3, 5);
		// call.registerOutParameter(4, OracleTypes.CURSOR);
		// call.registerOutParameter(5, OracleTypes.INTEGER);
		// call.registerOutParameter(6, OracleTypes.INTEGER);
		// call.execute();
		// Object cursor=call.getObject(4);
		// ResultSet set=(ResultSet) cursor;
		// while(set.next()){
		// System.out.println(set.getString(1)+" : "+set.getString(2));
		// }
		// System.out.println("总页数："+call.getInt(6));
		// System.out.println("总记录数："+call.getInt(5));

//		String sqlgoods="insert into t_goods"
//				+ "(goods_id,cat_id,product_id,title,goods_name,relation"
//				+ ",edit_time,goods_type) values(?,?,?,?,?,?,sysdate,?)";
//		String sqlfinance="insert into t_finance"
//				+ "(goods_id,cat_id,product_id,title,goods_name,relation"
//				+ ",edit_time,goods_type) values(?,?,?,?,?,?,sysdate,?)";
//		
//		String selectgoods="select product_id from t_goods where goods_type='1'";
//		
//		Connection conn=DBHelper.getConnection();
//		PreparedStatement ps=conn.prepareStatement(selectgoods);
////		for(int i=1;i<10000;i++){
////			ps.setLong(1, i);
////			ps.setLong(2, i);
////			ps.setString(3, String.valueOf(i));
////			ps.setString(4, retString());
////			ps.setString(5, retString());
////			ps.setInt(6, new Random().nextInt(3)+1);
////			ps.setInt(7, new Random().nextInt(3)+1);
////			
////			
////			ps.addBatch();
////		}
////		ps.executeBatch();
//		
//		List list=new ArrayList();
//		ResultSet rs=ps.executeQuery();
//		while(rs.next()){
////			insertBill(rs.getString("product_id"));//添加票据模拟数据
//			list.add(rs.getString("product_id"));
//		}
//		
//		 ps.close();
//	     conn.close();
//	     
//	     insertFinance(list);//添加理财模拟数据

	}
}
