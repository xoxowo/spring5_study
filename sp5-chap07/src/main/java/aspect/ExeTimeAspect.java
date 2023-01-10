package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // 스프링 AOP 적용할 클래스에 붙이기
public class ExeTimeAspect {

        @Pointcut("execution(public * chap07..*(..))")
        private void publicTarget() {} // 공통 기능을 적용할 대상 설정  
        // chap07 패키지와 그 하위 패키지에 위치한 public 메서드를 적용할 대상으로 설정했다는 의미
        
        @Around("publicTarget()")
        // ()에 들어있는 의미는 해당 메서드에 정의한 Pointcut에 공통기능을 적용한다는 것을 의미
        public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
            long start = System.nanoTime();
            try {
                Object result = joinPoint.proceed();
                return result;
            } finally {
                long finish = System.nanoTime();
                Signature sig = joinPoint.getSignature();
                System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                joinPoint.getTarget().getClass().getSimpleName(), 
                    sig.getName(),Arrays.toString(joinPoint.getArgs()),(finish - start));
                //getSignature, getTarget, getArgs 등 각 메서드는 호출한 메서드의 시그니처, 대상 객체, 인자 목록을 구하는데 사용된다
                }
        }
}
