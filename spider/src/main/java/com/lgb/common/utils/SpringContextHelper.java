package com.lgb.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Administrator
 *
 * @date 2017年11月14日
 */
@Component
@Lazy
public class SpringContextHelper implements ApplicationContextAware{

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context=applicationContext;
	}
	
	/**
	 *    
	 * @param beanName
	 * @return
	 */
    public static Object getBean(String beanName) {    
        return context.getBean(beanName);     
	}

}
