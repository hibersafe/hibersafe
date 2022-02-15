package com.ufrn;

import java.io.IOException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.utils.HibernateUtil;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages="com.ufrn.api")
@Api(value = "Hibersafe API")
@EnableSwagger2
@EntityScan("com.ufrn.api")
@EnableJpaRepositories("com.ufrn.api")
public class HibersafeApp {
    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	
    	Session session = sessionFactory.openSession();
    	
    	session.doWork(connection -> {
    	    try(Statement statement = connection.createStatement()) {
    	        statement.execute("SET GLOBAL group_concat_max_len=100000000000");
    	    }
    	});
    	
        SpringApplication.run(HibersafeApp.class, args);
    }
    
    @Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		
		@SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo(
				"Hibersafe REST API",
				"REST API for Hibersafe.", 
				"VERSION 0.1", null, null, null, null);
		
		 return apiInfo;
	}
}