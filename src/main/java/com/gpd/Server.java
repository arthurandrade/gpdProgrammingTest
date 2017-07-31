package com.gpd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

    /*@Configuration
    @EnableWebMvc
    public class WebConfig extends WebMvcConfigurerAdapter {
    
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
        }
    }*/
}
