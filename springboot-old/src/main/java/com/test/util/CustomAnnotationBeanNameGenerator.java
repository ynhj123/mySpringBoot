package com.test.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.Assert;

import java.beans.Introspector;

/**
 * @className: CustomAnnotationBeanNameGenerator
 * @description:
 * @author: wwb
 * @date: 2018-08-18 10:40:59
 * @version: ver 1.0
 */
public class CustomAnnotationBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String beanClassName = definition.getBeanClassName();
        Assert.state(beanClassName != null, "No bean class name set");
        return Introspector.decapitalize(beanClassName);
    }
}
