package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    
    @GetMapping("/Hello")           // @RequestParam는 http 요청 파라미터 값을 메서드 파라미터로 전달할 때 사용
    public String hello(Model model, 
        @RequestParam(value = "name", required = false) String name) {
            model.addAttribute("greeting", "안녕," + name);
            return "hello";
        }
}
