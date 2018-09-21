package com.capone.transactions.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

@SpringBootApplication
@ComponentScan("com.capone.transactions")
public class Transactions extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		
		Logger logger = LoggerFactory.getLogger(Transactions.class);
		  System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");
        SpringApplication.run(Transactions.class, args);
        logger.debug("--Application Started--");
    }
	
	 @Override
	    public void configurePathMatch(PathMatchConfigurer configurer) {
	        UrlPathHelper urlPathHelper = new UrlPathHelper();
	        urlPathHelper.setUrlDecode(false);
	        configurer.setUrlPathHelper(urlPathHelper);
	    }
}
