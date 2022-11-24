package chap02;

import org.springframework.context.annotation.*;

public class Main {

	public static void main(String[] args) {
		// 설정 정보를 이용해서 빈 객체를 생성
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		// 빈 객체를 제공한다
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		System.out.println("(g1 == g2) = " + (g1 == g2));
		ctx.close();
	}
}

/*
실행 결과
(g1 == g2) = true

g1과 g2가 같은 객체라는 것을 의미
즉 getBean()메서드는 같은 객체를 리턴하는 것.

*/