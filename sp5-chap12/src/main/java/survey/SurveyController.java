package survey;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	@GetMapping
	public ModelAndView form() {
		List<Question> questions = createQuestions();
		ModelAndView mav = new ModelAndView();
		mav.addObject("question", questions);
		mav.setViewName("survey/surveyForm");
		return mav;
	}
	
	private List<Question> createQuestions(){
		Question q1 = new Question("����ϴ� ��Ȱ�� �����ΰ���?", Arrays.asList("����","����Ʈ","Ǯ����"));
		Question q2 = new Question("���� ����ϴ� ���ߵ����� �����ΰ���?", Arrays.asList("��Ŭ����","���ڸ�����","�����Ʃ���"));
		Question q3 = new Question("�ϰ���� ���� �����ּ���.");
		return Arrays.asList(q1, q2, q3);
	}
	
	@PostMapping
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
}
