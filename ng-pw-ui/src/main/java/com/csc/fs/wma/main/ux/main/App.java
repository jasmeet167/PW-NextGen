package com.csc.fs.wma.main.ux.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */


@SpringBootApplication
@ComponentScan(basePackages = "com.csc.fs.ngpa.main.test.ux")
@EnableAutoConfiguration
//@EnableDiscoveryClient
public class App extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
	
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
    
}
