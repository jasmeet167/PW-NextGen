package com.csc.fsg.life.common.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/* Modifications: T0166, ENH1019, ENH1220 */

public class ConfigNamespaceHandler extends NamespaceHandlerSupport {
    
    public void init() 
    {
        registerBeanDefinitionParser("usi", new USIBeanDefinitionParser());
        registerBeanDefinitionParser("environment", new EnvironmentBeanDefinitionParser());
        registerBeanDefinitionParser("avm", new AVMBeanDefinitionParser());
        registerBeanDefinitionParser("performanceLog", new PerformanceLogBeanDefinitionParser());
    }
}