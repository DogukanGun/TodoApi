package com.appcent.todo.model.mapper;


import com.appcent.todo.model.dto.TodoListDto;
import com.appcent.todo.model.dto.TodoListElementDto;
import com.appcent.todo.model.entity.TodoList;
import com.appcent.todo.model.entity.TodoListElement;
import com.appcent.todo.model.request.CreateUpdateTodoListElementRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoListElementMapper {
    TodoListElementMapper TODO_LIST_ELEMENT_MAPPER = Mappers.getMapper(TodoListElementMapper.class);

    TodoListElementDto convertToTodoListElementDto(TodoListElement todoListElement);

    TodoListElement convertToTodoListElement(TodoListElementDto todoListElementDto);

    List<TodoListElementDto> convertListOfTodoListElements(List<TodoListElement> todoListElements);
    void updateTodoListElement(@MappingTarget TodoListElement todoListElement, CreateUpdateTodoListElementRequest createUpdateTodoListElementRequest);

    TodoListElement createTodoListElement(CreateUpdateTodoListElementRequest createUpdateTodoListElementRequest);
}
