package com.appcent.todo.service;


import com.appcent.todo.exception.BusinessException;
import com.appcent.todo.model.dto.TodoListElementDto;
import com.appcent.todo.model.entity.TodoList;
import com.appcent.todo.model.entity.TodoListElement;
import com.appcent.todo.model.enums.TodoElementStatus;
import com.appcent.todo.model.request.CreateUpdateTodoListElementRequest;
import com.appcent.todo.repository.TodoListElementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoListElementServiceTest {
    private String [] status = {TodoElementStatus.DONE.toString(),TodoElementStatus.PENDING.toString(),TodoElementStatus.IN_PROGRESS.toString()};

    @InjectMocks
    private TodoListElementService sut;

    @Mock
    private TodoListElementRepository todoListElementRepository;

    @Captor
    private ArgumentCaptor<TodoListElement> todoListElementArgumentCaptor;

    private TodoListElementDto todoListElementDto;

    private CreateUpdateTodoListElementRequest createUpdateTodoListElementRequest;

    @BeforeEach
    void setUp(){
        todoListElementDto = TodoListElementDto.builder()
                .todoListId(1)
                .id(1)
                .text("Test")
                .status(TodoElementStatus.PENDING.toString())
                .build();

        createUpdateTodoListElementRequest = CreateUpdateTodoListElementRequest.builder()
                .todoListId(1)
                .text("Test is updated")
                .status(TodoElementStatus.IN_PROGRESS.toString())
                .build();

    }

    @Test
    void it_should_update_dto_when_everything_is_okay(){

        //Given
        when(todoListElementRepository.save(todoListElementArgumentCaptor.capture())).thenReturn(
                TodoListElement.builder()
                        .text(createUpdateTodoListElementRequest.getText())
                        .todoListId(createUpdateTodoListElementRequest.getTodoListId())
                        .id(1)
                        .status(createUpdateTodoListElementRequest.getStatus())
                        .build()
        );
        when(todoListElementRepository.findById(any())).thenReturn(
                Optional.of(TodoListElement.builder()
                        .todoListId(1)
                        .text("test")
                        .status(TodoElementStatus.PENDING.toString())
                        .id(1)
                        .build())
        );
        //When

        TodoListElementDto todoListElementDto = sut.updateTodoListElement(eq(1),createUpdateTodoListElementRequest);

        //Then
        Assertions.assertEquals(createUpdateTodoListElementRequest.getText(),todoListElementDto.getText());
        Assertions.assertEquals(createUpdateTodoListElementRequest.getStatus(),todoListElementDto.getStatus());
        Assertions.assertEquals(createUpdateTodoListElementRequest.getTodoListId(),todoListElementDto.getTodoListId());

        TodoListElement todoListElement = todoListElementArgumentCaptor.getValue();

        Assertions.assertEquals(todoListElement.getTodoListId(),todoListElementDto.getTodoListId());
        Assertions.assertEquals(todoListElement.getStatus(),todoListElementDto.getStatus());
        Assertions.assertEquals(todoListElement.getText(),todoListElementDto.getText());

    }

    @ParameterizedTest
    @ValueSource(strings = {"Pending","Done","In Progress"})
    void it_should_throw_exception_when_same_status_comes(String status){
        //Given
        createUpdateTodoListElementRequest.setStatus(status);
        when(todoListElementRepository.findById(any())).thenReturn(
                Optional.of(TodoListElement.builder()
                                .id(1)
                                .text("test")
                                .todoListId(1)
                                .status(status)
                                .build()));
        //When
        //Then
        Assertions.assertThrows(BusinessException.class,()->sut.setStatus(eq(1),createUpdateTodoListElementRequest));

    }
}
