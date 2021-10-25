package com.appcent.todo.service;


import com.appcent.todo.model.dto.TodoListDto;
import com.appcent.todo.model.entity.TodoList;
import com.appcent.todo.model.request.CreateUpdateTodoListRequest;
import com.appcent.todo.repository.TodoListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoListServiceTest {
    @InjectMocks
    private TodoListService sut;

    @Mock
    private TodoListRepository todoListRepository;

    @Captor
    private ArgumentCaptor<TodoList> todoListArgumentCaptor;

    private CreateUpdateTodoListRequest createUpdateTodoListRequest;
    private TodoListDto todoListDto;

    @BeforeEach
    void setUp(){
        createUpdateTodoListRequest = CreateUpdateTodoListRequest.builder()
                .listName("Dersler")
                .build();

        todoListDto = TodoListDto.builder()
                .listName("Dersler")
                .id(1)
                .build();
    }


    @Test
    void it_should_return_dto_when_everything_is_ok(){

        //Given
        when(sut.createTodoElement(any())).thenReturn(todoListDto);
        when(todoListRepository.save(todoListArgumentCaptor.capture())).thenReturn(
                TodoList.builder()
                        .id(1)
                        .listName("Dersler")
                        .build()
        );

        //When

        TodoListDto todoListDtoFromService = sut.createTodoElement(createUpdateTodoListRequest);


        //Then

        Assertions.assertEquals(todoListDtoFromService.getListName(),todoListDto.getListName());

    }
}
