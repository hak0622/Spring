package org.scoula.todo.service;

import org.scoula.todo.domain.TodoDTO;

import java.util.List;

public interface TodoService {
    List<TodoDTO>selectAll();

    int insertTodo(TodoDTO todoDTO);

    //수정
    int updateTodo(Long id);

    //삭제
    int deleteTodo(Long id);
}
