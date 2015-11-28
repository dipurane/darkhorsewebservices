package com.synerzip.billfold;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
 

/**
 * @author Dipesh
 * Swagger Configuration for Restful API Documentation.
 *
 * 
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {

    /** The spring swagger config. */
    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Sets the spring swagger config.
     *
     * @param springSwaggerConfig the new spring swagger config
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;        
    }

    /**
     * Custom implementation.
     *
     * @return the swagger spring mvc plugin
     */
    @Bean 
    public SwaggerSpringMvcPlugin customImplementation(){
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo()).apiVersion("1.0").includePatterns(".*apis.*").useDefaultResponseMessages(false);
    }

    
    /**
     * Configure default servlet handling.
     *
     * @param configurer the configurer
     */
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    /**
     * Api info.
     *
     * @return the api info
     */
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Billfold API Documentation",
                "API Documentation for Billfold Restful Web Services for Peer to peer mobile and consumer to merchant scenarios",
                "The APIs are currently in beta and only available through an invite",
                "dipesh.rane@synerzip.com",
                "Private Usage",
                "http://www.billfold.com"
        );
        return apiInfo;
    }
}