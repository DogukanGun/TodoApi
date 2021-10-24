package com.appcent.todo.model.mapper;


import com.appcent.todo.model.dto.TodoListDto;
import com.appcent.todo.model.entity.TodoList;
import com.appcent.todo.model.request.CreateUpdateTodoListRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoListMapper {

    TodoListMapper TODO_LIST_MAPPER = Mappers.getMapper(TodoListMapper.class);

    TodoListDto convertToTodoListDto(TodoList todoList);

    TodoList convertToTodoList(TodoListDto todoListDto);

    List<TodoListDto> convertListOfTodoListDto(List<TodoList> todoList);

    void updateTodoList(@MappingTarget TodoList todoList, CreateUpdateTodoListRequest createUpdateTodoListRequest);

    TodoList createTodoList(CreateUpdateTodoListRequest createUpdateTodoListRequest);


}
