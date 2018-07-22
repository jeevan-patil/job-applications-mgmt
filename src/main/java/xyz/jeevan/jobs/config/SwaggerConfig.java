package xyz.jeevan.jobs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import xyz.jeevan.jobs.util.AppConstants;

/**
 * Swagger configuration file.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("xyz.jeevan.jobs.controller"))
        .paths(PathSelectors.regex(AppConstants.ENDPOINTS_FOR_SWAGGER))
        .build().apiInfo(apiInformation());
  }

  private ApiInfo apiInformation() {
    Contact contact = new Contact("Jeevan Patil", "https://jeevan-patil.github.io",
        "jeevanpaatil@gmail.com");

    return new ApiInfoBuilder().title("Job Applications Management System")
        .description("APIs for Job Applications Management System.")
        .version(AppConstants.API_VERSION)
        .contact(contact)
        .license("Apache-2.0").build();
  }
}
