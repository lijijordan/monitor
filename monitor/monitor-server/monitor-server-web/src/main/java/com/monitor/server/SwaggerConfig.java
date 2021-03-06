package com.monitor.server;

import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.monitor.server.comm.MessageUtil;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

  /**
   * @Description: 使用SwaggerUI创建应用自己的ApiInfo
   * @return: Docket
   */
  @SuppressWarnings("deprecation")
  @Bean
  public Docket createRestApi() {

    Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any())
        .build().pathMapping("/").directModelSubstitute(LocalDate.class, Date.class)
        .useDefaultResponseMessages(false);

    ApiInfo apiInfo = new ApiInfoBuilder()
        .title(MessageUtil.getMessage("message.swaggerui.title", null, "", null))
        .description(MessageUtil.getMessage("message.swaggerui.description", null, "", null))
        .license("").licenseUrl("")
        .contact(MessageUtil.getMessage("message.swaggerui.creator", null, "", null))
        .version(MessageUtil.getMessage("message.comm.version", null, "", null)).build();

    if (apiInfo != null) {
      docket.apiInfo(apiInfo);
    }

    return docket;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

}
