/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.md5;

/**
 *
 * @author zengzw
 * @date 2015年1月28日
 */
public class HttpConstant {
    
    /**
     * 字符集
     *
     * @author zengzw
     * @date 2015年1月28日
     */
    public interface CHARSET{
        String UTF8 = "utf-8";  
        
        String GBK = "gbk"; 
    }
    
    
    public interface METHOD{
        String GET = "GET";
        
        String POST = "POST";
        
        String PUT = "PUT";
        
        String DELETE = "DELETE";
    }
 
}
