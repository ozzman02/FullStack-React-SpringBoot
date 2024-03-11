package net.ossant.service.impl;

import lombok.RequiredArgsConstructor;
import net.ossant.dto.TodoDto;
import net.ossant.entity.Todo;
import net.ossant.exception.ResourceNotFoundException;
import net.ossant.repository.TodoRepository;
import net.ossant.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static net.ossant.utils.ErrorBuilderUtil.todoNotFoundErrorMessage;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        return modelMapper.map(todoRepository
                .save(modelMapper.map(todoDto, Todo.class)), TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {
        return todoRepository.findById(id)
                .map(existingTodo -> modelMapper.map(existingTodo, TodoDto.class))
                .orElseThrow(() -> new ResourceNotFoundException(todoNotFoundErrorMessage(id)));
    }

    @Override
    public List<TodoDto> getAllDtos() {
        return todoRepository.findAll().stream()
                .map(existingTodo -> modelMapper.map(existingTodo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        return todoRepository.findById(id).map(existingTodo -> {
            existingTodo.setTitle(todoDto.getTitle());
            existingTodo.setDescription(todoDto.getDescription());
            existingTodo.setCompleted(todoDto.isCompleted());
            todoRepository.save(existingTodo);
            return modelMapper.map(existingTodo, TodoDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException(todoNotFoundErrorMessage(id)));
    }

    @Override
    public Boolean deleteTodo(Long id) {
        return todoRepository.findById(id).map(existingTodo -> {
            todoRepository.deleteById(id);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException(todoNotFoundErrorMessage(id)));
    }

    @Override
    public TodoDto completeTodo(Long id) {
        return todoRepository.findById(id).map(existingTodo -> {
            existingTodo.setCompleted(true);
            todoRepository.save(existingTodo);
            return modelMapper.map(existingTodo, TodoDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException(todoNotFoundErrorMessage(id)));
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        return todoRepository.findById(id).map(existingTodo -> {
            existingTodo.setCompleted(false);
            todoRepository.save(existingTodo);
            return modelMapper.map(existingTodo, TodoDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException(todoNotFoundErrorMessage(id)));
    }

}
