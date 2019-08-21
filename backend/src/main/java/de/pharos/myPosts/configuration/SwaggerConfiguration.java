package de.pharos.myPosts.configuration;

import com.google.common.base.Predicates;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public AlternateTypeRuleConvention pageableConvention(final TypeResolver resolver) {
        return new AlternateTypeRuleConvention() {

            @Override
            public int getOrder() {
                return Ordered.HIGHEST_PRECEDENCE;
            }

            @Override
            public List<AlternateTypeRule> rules() {
                return singletonList(
                        newRule(resolver.resolve(Pageable.class), resolver.resolve(pageableMixin()))
                );
            }
        };
    }

    @Bean
    public Docket authentication() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Authentication")
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("de.pharos.myPosts.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/v1/users/**"))
                .build();
    }

    @Bean
    public Docket posts() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Posts")
                .apiInfo(getApiInfo())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .select()
                .apis(RequestHandlerSelectors.basePackage("de.pharos.myPosts.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.ant("/v1/users/**")))
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "MyPosts",
                "MyPosts is a small web application and backend for mobile application where users can sign in create public and private posts and see other public posts.",
                "1.0",
                "TERMS OF SERVICE URL",
                new Contact("Omar Gamal", "", "omargsharaf94@gmail.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }

    private static List<ApiKey> securitySchemes() {
        return singletonList(new ApiKey("Bearer", "Authorization", "header"));
    }

    private static List<SecurityContext> securityContexts() {
        return singletonList(
                SecurityContext.builder()
                        .securityReferences(
                                singletonList(SecurityReference.builder()
                                        .reference("Bearer")
                                        .scopes(new AuthorizationScope[0])
                                        .build()
                                )
                        )
                        .forPaths(PathSelectors.ant("/v1/**"))
                        .build());
    }

    private Type pageableMixin() {
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(
                        String.format("%s.generated.%s",
                                Pageable.class.getPackage().getName(),
                                Pageable.class.getSimpleName()))
                .withProperties(Arrays.asList(
                        property(Integer.class, "page"),
                        property(Integer.class, "size"),
                        property(String.class, "sort")
                ))
                .build();
    }

    private AlternateTypePropertyBuilder property(Class<?> type, String name) {
        return new AlternateTypePropertyBuilder()
                .withName(name)
                .withType(type)
                .withCanRead(true)
                .withCanWrite(true);
    }
}