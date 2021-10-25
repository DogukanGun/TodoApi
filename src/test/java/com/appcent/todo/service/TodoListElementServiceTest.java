package com.appcent.todo.service;


import com.appcent.todo.model.dto.TodoListElementDto;
import com.appcent.todo.model.entity.TodoListElement;
import com.appcent.todo.model.request.CreateUpdateTodoListElementRequest;
import com.appcent.todo.repository.TodoListElementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TodoListElementServiceTest {
    private TodoListElementService sut;

    private TodoListElementRepository todoListElementRepository;

    private TodoListElementDto todoListElementDto;

    private CreateUpdateTodoListElementRequest createUpdateTodoListElementRequest;

    @BeforeEach
    void setUp(){

    }
}
