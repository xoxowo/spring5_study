package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc // 스프링 MVC 설정 활성화하는 애노테이션 다양한 설정을 생성한다.
public class MvcConfig implements WebMvcConfigurer {

    @Override // 
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override //jsp를 이용해서 컨트롤러의 실행 결과를 보여주기 위한 설정 추가
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/.", ".jsp");
    }
}
