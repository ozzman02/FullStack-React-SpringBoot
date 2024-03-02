package net.ossant.service;

import net.ossant.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllDtos();

    TodoDto updateTodo(Long id, TodoDto todoDto);

    Boolean deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto incompleteTodo(Long id);

}
