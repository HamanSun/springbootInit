package cn.jjsunw.config;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.JSON;

import cn.jjsunw.common.Result;
import cn.jjsunw.common.ResultCode;
import cn.jjsunw.common.ServiceException;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedHeaders("*")
				.allowedMethods("GET", "POST", "DELETE", "PUT").maxAge(3600);
	}

	// Unified exception handling
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(new HandlerExceptionResolver() {
			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
				Result result = new Result();
				if (e instanceof ServiceException) {
					result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
				} else if (e instanceof NoHandlerFoundException) {
					result.setCode(ResultCode.NOT_FOUND).setMessage("Interface [" + request.getRequestURI() + "] not exist.");
				} else if (e instanceof ServletException) {
					result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
				} else if(e instanceof AuthenticationException){
					result.setCode(ResultCode.UNAUTHORIZED).setMessage("Bad credential.");
				} else {
					result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("Interface [" + request.getRequestURI() + "] internal error.");
					String message;
					if (handler instanceof HandlerMethod) {
						HandlerMethod handlerMethod = (HandlerMethod) handler;
						message = String.format("Interface [%s] error occured, method ：%s.%s，error msg ：%s", request.getRequestURI(), handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(), e.getMessage());
					} else {
						message = e.getMessage();
					}
					log.error(message, e);
				}
				log.error(result.getMessage());
				responseResult(response, result);
				return new ModelAndView();
			}

		});
	}

	private void responseResult(HttpServletResponse response, Result result) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setStatus(result.getCode());
		try {
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/database/initialize/**").addResourceLocations("classpath:/database/initialize/");
		super.addResourceHandlers(registry);
	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(50000);// ms
		factory.setConnectTimeout(150000);// ms
		return factory;
	}
	
	/**
	 * swagger configuration
	 */
	@Bean
	public Docket createRestApi() {
		// 添加head参数start
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		tokenPar.name("Authorization").description("Token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());
		return new Docket(DocumentationType.SWAGGER_2).groupName("Service API").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("cn.jjsunw.controller")).paths(PathSelectors.any()).build()
				.globalOperationParameters(pars);
	}

	@Bean
	public Docket authApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Auth API").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("cn.jjsunw.security.auth")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("springboot2.0.5").description("Initialization project based on springboot2.0.5")
				.termsOfServiceUrl("http://blog.52itstyle.com")
				.contact(new Contact("sunjunjie ", null, "3083996241@qq.com")).version("1.0").build();
	}

}
