package service;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application extends SpringBootServletInitializer {

	private static Class<Application> appClass = Application.class;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(appClass);
    }
    
//  @Bean
//  public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception {
//	  SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
//	  camelContext.addRoutes(routeBuilder());
//	  return camelContext;
//  }
//  
//  @Bean
//  public RouteBuilder routeBuilder() {
//	  return new SampleRoute();
//  }
}