package com.lgb.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
public class SpringContextHelper implements ApplicationContextAware{

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context=applicationContext;
	}
	
	/**    
     * 这是一个便利的方法，帮助我们快速得到一个BEAN    
     * @param beanName bean的名字    
     * @return 返回一个bean对象    
     * @author wangdf   
     */      
    public static Object getBean(String beanName) {    
        return context.getBean(beanName);     
    }   

}
