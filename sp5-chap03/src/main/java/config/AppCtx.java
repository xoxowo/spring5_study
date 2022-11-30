package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;
//@Configuration은 스프링 설정 클래스를 의미함
@Configuration
public class AppCtx {
    // @Bean 애노테이션은 해당 메서드가 생성한 객체를 스프링 빈이라고 설정한다.
    @Bean // 이 빈은 각각의 빈 객체를 생성 
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }
}
