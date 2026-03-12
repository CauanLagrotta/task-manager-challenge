package com.cauanlagrotta.task_manager_challenge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocAPIConfiguration {

  @Bean
  public OpenAPI openAPI(){
    return new OpenAPI()
        .info(
            new Info()
                .title("Rest API - Task Manager")
                .description("API para gerenciador de tarefas")
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                .contact(new Contact().name("Cauan Lagrotta").email("cauanlagrotta.dev@gmail.com"))
        );
  }
}
