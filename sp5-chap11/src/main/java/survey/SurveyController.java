package survey;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	@GetMapping
	public String form(Model model) {
		List<Question> questions = createQuestions();
		model.addAttribute("question", questions);
		return "survey/surveyForm";
	}
	
	private List<Question> createQuestions(){
		Question q1 = new Question("희망하는 역활은 무엇인가요?", Arrays.asList("서버","프론트","풀스택"));
		Question q2 = new Question("자주 사용하는 개발도구는 무엇인가요?", Arrays.asList("이클립스","인텔리제이","비쥬얼스튜디오"));
		Question q3 = new Question("하고싶은 말을 적어주세요.");
		return Arrays.asList(q1, q2, q3);
	}
	
	@PostMapping
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
}
