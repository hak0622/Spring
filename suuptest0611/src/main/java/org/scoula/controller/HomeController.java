package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.todo.domain.TodoDTO;
import org.scoula.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller // Spring MVC 컨트롤러로 등록
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    private  final TodoService todoService;

    //메인 페이지
    @GetMapping("/")
    public String home(Model model) {
        //전체 할 일 조회 서비스 호출
        List<TodoDTO>todos = todoService.selectAll();
        //조회 결과를 Model에 담아서 view로 전달 (todos)
        model.addAttribute("todos", todos);

        log.info("================> HomeController /");
        return "index"; // View의 이름 (ServletConfig의 ViewResolver에 의해 /WEB-INF/views/index.jsp로 변환)
    }
}
