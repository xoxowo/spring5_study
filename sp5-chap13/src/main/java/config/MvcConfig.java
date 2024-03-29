package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import interceptor.AuthCheakInterceptor;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }
    
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        registry.jsp("/WEB-INF/view/", ".jsp");
    }
    
    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}
    
    @Override
    public void addInterceptors(InterceptorRegistry register) {
    	register.addInterceptor(authCheckInterceptor()).addPathPatterns("/edit/**");
    }
    
    @Bean
    public MessageSource messageSource() {
    	ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
    	ms.setBasenames("message.label");
    	ms.setDefaultEncoding("UTF-8");
    	return ms;
    }
    
    @Bean
    public AuthCheakInterceptor authCheckInterceptor() {
    	return new AuthCheakInterceptor();
    }
    
}
