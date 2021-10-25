package com.appcent.todo.service;


import com.appcent.todo.model.dto.TodoListDto;
import com.appcent.todo.model.dto.TodoListElementDto;
import com.appcent.todo.model.entity.TodoList;
import com.appcent.todo.model.request.CreateUpdateTodoListRequest;
import com.appcent.todo.repository.TodoListRepository;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.appcent.todo.exception.NotFoundException;

import java.util.List;

import static com.appcent.todo.model.mapper.TodoListElementMapper.TODO_LIST_ELEMENT_MAPPER;
import static com.appcent.todo.model.mapper.TodoListMapper.TODO_LIST_MAPPER;

@Service
@RequiredArgsConstructor
public class TodoListService {
     private final TodoListRepository todoListRepository;


     //create
     public TodoListDto createTodoElement(CreateUpdateTodoListRequest createUpdateTodoListRequest){
         TodoList todoList = TODO_LIST_MAPPER.createTodoList(createUpdateTodoListRequest);
         return TODO_LIST_MAPPER.convertToTodoListDto(todoListRepository.save(todoList));
     }

     //update
     public TodoListDto updateTodoList(int id,CreateUpdateTodoListRequest createUpdateTodoListRequest){
         TodoList todoList = todoListRepository.findById(id).orElseThrow(()->new NotFoundException("TodoList is not found"));
         TODO_LIST_MAPPER.updateTodoList(todoList,createUpdateTodoListRequest);
         return TODO_LIST_MAPPER.convertToTodoListDto(todoListRepository.save(todoList));
     }
     // TODO: 24.10.2021 soft delete mi hard delete mi olucak

    //get
    public List<TodoListDto> getTodoListDtos(){
        return TODO_LIST_MAPPER.convertListOfTodoListDto(todoListRepository.findAll());
    }

    public List<TodoListDto> getTodoListDtoByName(String name){
        List<TodoList> todoLists = todoListRepository.findAllByListNameContains(name).orElseThrow(()->new NotFoundException("TodoList is not found"));
        return TODO_LIST_MAPPER.convertListOfTodoListDto(todoLists);
    }
    public TodoListDto getTodoListDtoById(int id){
        TodoList todoList = todoListRepository.findById(id).orElseThrow(()->new NotFoundException("TodoList is not found"));
        return TODO_LIST_MAPPER.convertToTodoListDto(todoList);
    }

    public List<TodoListElementDto> getElementsOfTodoList(int id) {
         TodoList todoList = todoListRepository.findById(id).orElseThrow(()->new NotFoundException("Todo list is not found"));
         return TODO_LIST_ELEMENT_MAPPER.convertListOfTodoListElements(todoList.getTodoElementList());
    }
}
