package com.tutorialspoint;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class InitHelloWorld1 implements BeanPostProcessor, Ordered {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
					throws BeansException {
		System.out.println("BeforeInitialization 1: " + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
					throws BeansException {
		System.out.println("AfterInitialization 1: " + beanName);
		return bean;
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 100;
	}

}
