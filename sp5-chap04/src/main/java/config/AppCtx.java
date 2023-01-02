package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;
import spring.MemberPrinter;
import spring.MemberListPrinter;
import spring.VersionPrinter;

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
//        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }
    // 추가
    @Bean
    public MemberPrinter memberPrinter() {
    	return new MemberPrinter();
    }
    
    @Bean
    public MemberListPrinter listPrinter() {
    	return new MemberListPrinter(memberDao(), memberPrinter());
    }
    // DI 세터 메서드 방식 추가 기입
    @Bean
    public MemberInfoPrinter infoPrinter() {
    	MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
    	infoPrinter.setMemberDao(memberDao());
    	infoPrinter.setPrinter(memberPrinter());
    	return infoPrinter;
    }
    // 기본 버전 타입값 추가 기입
    @Bean
    public VersionPrinter versionPrinter() {
    	VersionPrinter versionPrinter = new VersionPrinter();
    	versionPrinter.setMajorVersion(5);
    	versionPrinter.setMinorVersion(0);
    	return versionPrinter;
    }
    
}
