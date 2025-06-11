package org.scoula.todo.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.todo.domain.TodoDTO;
import org.scoula.todo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;

    @Override
    public List<TodoDTO> selectAll() {
        List <TodoDTO> todos = todoMapper.selectAll();
        log.info("죄회된 todo 개수 : {} " + todos.size());
        return todos;
    }

    @Override
    public int insertTodo(TodoDTO todo) {
        int count = todoMapper.insertTodo(todo); //insert 수행 후 결과 행 개수 반환
        log.info("삽입된 행의 개수 : {}" + count);
        return count;
    }

    @Override
    public int updateTodo(Long id) {
        int count = todoMapper.updateTodo(id); //update 수행 후 결과 행 개수 반환
        log.info("수정된 행의 개수 : {}" + count);
        return count;
    }

    @Override
    public int deleteTodo(Long id) {
        int count = todoMapper.deleteTodo(id); //delete 수행 후 결과 행 개수 반환
        log.info("삭제된 행의 개수 : {}" + count);
        return count;
    }

}
