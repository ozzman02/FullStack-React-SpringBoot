package net.ossant.controller;

import lombok.RequiredArgsConstructor;
import net.ossant.dto.TodoDto;
import net.ossant.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static net.ossant.constants.ApplicationConstants.*;

//@CrossOrigin(ALL)
@RestController
@RequiredArgsConstructor
@RequestMapping(TODO_BASE_URL)
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTodo(@RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.addTodo(todoDto), HttpStatus.CREATED);
    }

    @GetMapping(TODO_ID)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getAllTodos() {
        return new ResponseEntity<>(todoService.getAllDtos(), HttpStatus.OK);
    }

    @PutMapping(TODO_ID)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.updateTodo(id, todoDto), HttpStatus.OK);
    }

    @DeleteMapping(TODO_ID)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.deleteTodo(id), HttpStatus.NO_CONTENT);
    }

    @PatchMapping(COMPLETE_TODO_URL)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> completeTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.completeTodo(id), HttpStatus.OK);
    }

    @PatchMapping(INCOMPLETE_TODO_URL)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> incompleteTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.incompleteTodo(id), HttpStatus.OK);
    }

}
