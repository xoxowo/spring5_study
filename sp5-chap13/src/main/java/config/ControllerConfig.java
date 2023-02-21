package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.LogoutController;
import controller.LoginController;
import controller.RegisterController;
import spring.MemberRegisterService;
import spring.AuthService;

@Configuration
public class ControllerConfig {

    @Autowired
    private MemberRegisterService memberRegSvc;
    @Autowired
    private AuthService authService;

    @Bean
    public RegisterController registerController(){
        RegisterController controller = new RegisterController();
        controller.setMemberRegisterService(memberRegSvc);
        return controller;
    }
    @Bean
    public LoginController loginController() {
    	LoginController controller = new LoginController();
    	controller.setAuthService(authService);
    	return controller;
    }
    
    @Bean
    public LogoutController logoutController() {
    	return new LogoutController();
    }   
}
