package org.scoula.todo.controller;

import lombok.Data;
import org.scoula.todo.domain.TodoDTO;
import org.scoula.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //컨트롤러 명시 + Bean 등록
@RequestMapping("/todo")
@Data
public class TodoController {


    private final TodoService todoService; //의존성 주입

    @PostMapping("/insert")
    public String insert(TodoDTO todo) {
        todoService.insertTodo(todo);
        return "redirect:/"; //메인 페이지 리다이렉트
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Long id) {
        todoService.updateTodo(id);
        return "redirect:/"; //메인 페이지 리다이렉트
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/"; //메인 페이지 리다이렉트
    }
}
