package com.wellknown.xiaozhuang.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.wellknown.xiaozhuang.utils.Logging;

public class HeaderRequest extends HttpServletRequestWrapper implements Enumeration<String>{
 
    private Map<String,String> headerMap;
    private HttpServletRequest oldrequest;
    
    public class HeaderEnumeration implements Enumeration {

    	int count; // 计数器
        int length; //存储的数组的长度
        Object[] dataArray; // 存储数据数组的引用
        //构造器
        HeaderEnumeration(int count, int length, Object[] dataArray){ 
              this.count = count;
              this.length = length;
              this.dataArray = dataArray;
        } 
       
		@Override
		public boolean hasMoreElements() {
			// TODO Auto-generated method stub
			return (count< length);
		}

		@Override
		public Object nextElement() {
			// TODO Auto-generated method stub
			return dataArray[count++];
		}
    	
    }
    
    public HeaderRequest(HttpServletRequest request) {
        super(request);
        oldrequest = request;
        // 提取原本的HEADER内容
        Enumeration enumeration = request.getHeaderNames();
        headerMap = new HashMap<String, String>();
        while(enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            headerMap.put(name, request.getHeader(name));
        }
    }
 
    public Map<String, String> getHeaderMap() {
        return headerMap;
    }
     
    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }
     
    public void addHeader(String name,String value) {
        headerMap.put(name, value);
    }
     
    /**
     * 从自己的MAP中返回HEADER内容
     * 这里只实现了getHeader,可以根据需要重写这2个函数
     */
    @Override
    public String getHeader(String name) {
        return headerMap.get(name);
    }
     
    @Override
    public Enumeration getHeaderNames() {
        return oldrequest.getHeaderNames();
    }
     
    /**
     * 未实现,根据需要重写
     */
    @Override
    public Enumeration getHeaders(String name) {
    	if ("content-type".equalsIgnoreCase(name)) {
    		return new HeaderEnumeration(0, 1, new String[] {headerMap.get(name)});
    		
    	}
        return oldrequest.getHeaders(name);
    };
 
    /**
     * 用于实现getHeaderNames
     * 只是简单实现,仅提供单次检索功能
     */
    Set<String> mapKeySet;
    Iterator<String> mapKeySetIt;
    public boolean hasMoreElements() {
        if(mapKeySet == null) {
            mapKeySet = headerMap.keySet();
            mapKeySetIt = mapKeySet.iterator();
        }
         
        return mapKeySetIt.hasNext();
    }
 
    public String nextElement() {
        return mapKeySetIt.next();
    }
}