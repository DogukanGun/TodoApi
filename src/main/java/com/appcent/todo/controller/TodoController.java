package com.appcent.todo.controller;


import com.appcent.todo.model.dto.TodoListDto;
import com.appcent.todo.model.dto.TodoListElementDto;
import com.appcent.todo.model.entity.TodoList;
import com.appcent.todo.model.request.CreateUpdateTodoListRequest;
import com.appcent.todo.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("todo")
public class TodoController {
    private final TodoListService todoListService;

    @PostMapping
    public TodoListDto createTodoList(@RequestBody  CreateUpdateTodoListRequest createUpdateTodoListRequest){
        return todoListService.createTodoElement(createUpdateTodoListRequest);
    }

    @GetMapping("all")
    public List<TodoListDto> getAllLists(){
        return todoListService.getTodoListDtos();
    }

    @GetMapping("{id}")
    public TodoListDto getListById(@PathVariable int id){
        return todoListService.getTodoListDtoById(id);
    }

    @GetMapping
    public List<TodoListDto> getListsByName(@RequestParam String name){
        return todoListService.getTodoListDtoByName(name);
    }
    @GetMapping("{id}/elements")
    public List<TodoListElementDto> getElementsOfList(@PathVariable  int id){
        return todoListService.getElementsOfTodoList(id);
    }

    @PutMapping("{id}")
    public TodoListDto updateList(int id,CreateUpdateTodoListRequest createUpdateTodoListRequest){
        return todoListService.updateTodoList(id,createUpdateTodoListRequest);
    }

    // TODO: 24.10.2021 Delete eklenecek

}
