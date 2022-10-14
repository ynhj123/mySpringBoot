/** 
 * @fileName：SpringContextHolder.java  
 * 2016年5月5日 下午3:27:47
 * @version: ver 1.0
 * Copyright Corporation 2016   
 */
package com.test.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 初始化时持有applicationContext
 * @className: SpringContextHolder
 * @projectName: xzpxAdmin
 * @Description:以便在非spring管理的bean中使用spring管理的bean
 * @author: 王文彬
 * @date:2016年5月5日 下午3:27:47
 * @version: ver 1.0
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/** 取得存储在静态变量中的ApplicationContext
	 * @Description: 
	 * @author: 王文彬  
	 * @version: 2016年5月5日 下午3:29:10
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}
	/** 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 * @Description: 
	 * @author: 王文彬  
	 * @version: 2016年5月5日 下午3:29:20
	 * @param name
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/** 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 * @Description: 
	 * @author: 王文彬  
	 * @version: 2016年5月5日 下午3:29:38
	 * @param clazz
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}

	/** 清除applicationContext静态变量
	 * @Description: 
	 * @author: 王文彬  
	 * @version: 2016年5月5日 下午3:29:48
	 * @return void
	 */
	public static void cleanApplicationContext() {
		applicationContext = null;
	}

	/** 检查applicationContext
	 * @Description: 
	 * @author: 王文彬  
	 * @version: 2016年5月5日 下午3:29:57
	 * @return void
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}
}
