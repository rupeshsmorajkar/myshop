package com.myshop.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Product microservice
 *
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ProductApp 
{
    public static void main(String[] args) {
    	/*String[] newArgs;
    	if(args == null || args.length <= 0) {
    		newArgs = new String[1];
    	} else {
    		newArgs = new String[args.length + 1];
    		System.arraycopy(args, 0, newArgs, 0, args.length);
    		newArgs[args.length] = "-Dspring.profiles.active=dev";
    	}*/

    	System.setProperty("spring.profiles.active", "dev");

    	SpringApplication.run(ProductApp.class, args);
    }
}
