package shop.mtcoding.blog._core.error;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GlobalValidationHandler {

    @Before("@annotation(shop.mtcoding.blog._core.error.anno.MyBefore)")
    public void beforeAdvice(JoinPoint jp) {

        String name = jp.getSignature().getName();
        System.out.println("Before Advice : " + name);
    }

    @After("@annotation(shop.mtcoding.blog._core.error.anno.MyAfter)")
    public void afterAdvice(JoinPoint jp) {
        System.out.println("매개변수 크기 : " + jp.getArgs().length);
        String name = jp.getSignature().getName();
        System.out.println("After Advice : " + name);
    }

    @Around("@annotation(shop.mtcoding.blog._core.error.anno.MyAround)")
    public Object aroundAdvice(ProceedingJoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println("Around Advice 직전 : " + name);

        try {
            Object result = jp.proceed(); // 컨트롤러 함수가 호출
            System.out.println("Around Advice 직후 : " + name);
            System.out.println("result 값 : " + result);
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }
}
