package com.topi.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

/**
 * Class created to configure the Swagger.
 *
 * @since 2021-03-14
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Context of the servlet.
     */
    @Autowired
    private ServletContext servletContext;

    /**
     * API configuration bean.
     *
     * @return {@link Docket}. Docket configuration ready to use.
     */
    @Bean
    public Docket api() {
        TypeResolver typeResolver = new TypeResolver();
        return new Docket(DocumentationType.OAS_30).alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(Map.class, String.class, WildcardType.class),
                typeResolver.resolve(Map.class, String.class, WildcardType.class), Ordered.HIGHEST_PRECEDENCE))
                .apiInfo(apiInfo())
                .host("localhost")
                .directModelSubstitute(LocalDate.class, Date.class)
                .pathProvider(new PathProvider() {
                    @Override
                    public String getOperationPath(String operationPath) {
                        return "/topi/api/";
                    }

                    @Override
                    public String getResourceListingPath(String groupName, String apiDeclaration) {
                        return servletContext.getContextPath();
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Method that returns the API info.
     *
     * @return {@link ApiInfo}. API info.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Configurations")
                .description("Endpoints list")
                .contact(new Contact("TOPi", "topi.io/en/", "rafael.colatusso@topi.io"))
                .version("1.0")
                .build();
    }
}
