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