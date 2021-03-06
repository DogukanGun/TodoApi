package com.appcent.todo.service;


 import com.appcent.todo.exception.NotFoundException;
import com.appcent.todo.model.dto.TodoListDto;
import com.appcent.todo.model.entity.TodoList;
import com.appcent.todo.model.request.CreateUpdateTodoListRequest;
import com.appcent.todo.repository.TodoListRepository;
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

import java.util.List;
 import java.util.Optional;

 import static org.mockito.ArgumentMatchers.any;
 import static org.mockito.ArgumentMatchers.eq;
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
    void it_should_create_dto_when_everything_is_ok(){

        //Given
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
        Assertions.assertEquals(todoListDtoFromService.getId(),todoListDto.getId());

    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -1000})
    void it_should_throw_exception_when_todo_list_not_available(int id){
        //Given
        //When
        //Then
        Assertions.assertThrows(NotFoundException.class,()->sut.getTodoListDtoById(id));
    }

    @Test
    void it_should_update_todo_list_and_return_new_dto(){
        //Given

        when(todoListRepository.findById(any())).thenReturn(Optional.of(TodoList.builder()
                .id(1)
                .listName("deneme")
                .build()));
        when(todoListRepository.save(todoListArgumentCaptor.capture())).thenReturn(TodoList.builder()
                .listName(createUpdateTodoListRequest.getListName())
                .id(1)
                .build());

        //When
        TodoListDto todoListDto= sut.updateTodoList(1,createUpdateTodoListRequest);

        //Then
        Assertions.assertEquals(todoListDto.getListName(),createUpdateTodoListRequest.getListName());
        Assertions.assertEquals(todoListArgumentCaptor.getValue().getListName(),todoListDto.getListName());

    }


}
