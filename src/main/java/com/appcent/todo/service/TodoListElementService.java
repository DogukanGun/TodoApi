package com.appcent.todo.service;


import com.appcent.todo.exception.BusinessException;
import com.appcent.todo.model.dto.TodoListElementDto;
import com.appcent.todo.model.entity.TodoList;
import com.appcent.todo.model.entity.TodoListElement;
import com.appcent.todo.model.enums.TodoElementStatus;
import com.appcent.todo.model.request.CreateUpdateTodoListElementRequest;
import com.appcent.todo.repository.TodoListElementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.appcent.todo.exception.NotFoundException;

import java.util.List;

import static com.appcent.todo.model.mapper.TodoListElementMapper.TODO_LIST_ELEMENT_MAPPER;

@Service
@RequiredArgsConstructor
public class TodoListElementService {
    private final TodoListElementRepository todoListElementRepository;

    //create

    public TodoListElementDto createTodoListElement(CreateUpdateTodoListElementRequest createUpdateTodoListElementRequest){
        TodoListElement todoListElement = TODO_LIST_ELEMENT_MAPPER.createTodoListElement(createUpdateTodoListElementRequest);
        if (todoListElement.getStatus().equalsIgnoreCase(TodoElementStatus.DONE.toString())){
            todoListElement.setStatus(TodoElementStatus.DONE.toString());
        }
        else if (todoListElement.getStatus().equalsIgnoreCase(TodoElementStatus.IN_PROGRESS.toString())){
            todoListElement.setStatus(TodoElementStatus.IN_PROGRESS.toString());
        }else{
            todoListElement.setStatus(TodoElementStatus.PENDING.toString());
        }
        return TODO_LIST_ELEMENT_MAPPER.convertToTodoListElementDto(todoListElementRepository.save(todoListElement));
    }

    //update

    public TodoListElementDto updateTodoListElement(int id,CreateUpdateTodoListElementRequest createUpdateTodoListElementRequest){
        TodoListElement todoListElement = getTodoListElement(id);
        TODO_LIST_ELEMENT_MAPPER.updateTodoListElement(todoListElement,createUpdateTodoListElementRequest);
        return TODO_LIST_ELEMENT_MAPPER.convertToTodoListElementDto(todoListElementRepository.save(todoListElement));
    }

    public TodoListElementDto setStatusInProgress(int id){
        TodoListElement todoListElement = getTodoListElement(id);
        if (todoListElement.getStatus().equalsIgnoreCase(TodoElementStatus.IN_PROGRESS.toString())){
            throw new BusinessException("Status has already set");
        }
        todoListElement.setStatus(TodoElementStatus.IN_PROGRESS.toString());
        return TODO_LIST_ELEMENT_MAPPER.convertToTodoListElementDto(todoListElementRepository.save(todoListElement));
    }
    public TodoListElementDto setStatusDone(int id){
        TodoListElement todoListElement = getTodoListElement(id);
         if (todoListElement.getStatus().equalsIgnoreCase(TodoElementStatus.DONE.toString())){
            throw new BusinessException("Status has already set");
        }
        todoListElement.setStatus(TodoElementStatus.DONE.toString());
        return TODO_LIST_ELEMENT_MAPPER.convertToTodoListElementDto(todoListElementRepository.save(todoListElement));
    }
    public TodoListElementDto setStatusPending(int id){
        TodoListElement todoListElement = getTodoListElement(id);
        if (todoListElement.getStatus().equalsIgnoreCase(TodoElementStatus.PENDING.toString())){
            throw new BusinessException("Status has already set");
        }
        todoListElement.setStatus(TodoElementStatus.PENDING.toString());
        return TODO_LIST_ELEMENT_MAPPER.convertToTodoListElementDto(todoListElementRepository.save(todoListElement));
    }

    //get

    private TodoListElement getTodoListElement(int id){
        return todoListElementRepository.findById(id).orElseThrow(()->new NotFoundException("Todo List Element is not founded"));
    }
    public TodoListElementDto getTodoListElementDto(int id){
        return TODO_LIST_ELEMENT_MAPPER.convertToTodoListElementDto(getTodoListElement(id));
    }

    // TODO: 24.10.2021 Kullanisli olur mu dusun, yeride yanlis olabilir gibi
    public List<TodoListElementDto> getTodoListElementsByTodoList(int id){
        return TODO_LIST_ELEMENT_MAPPER.convertListOfTodoListElements(todoListElementRepository.findAllByTodoListIdContains(id)
                .orElseThrow(()->new NotFoundException("Todo List is not found")));
    }
    // TODO: 24.10.2021 delete soft mu olucak hard mi
}
