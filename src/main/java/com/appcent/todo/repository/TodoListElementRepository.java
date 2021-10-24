package com.appcent.todo.repository;

import com.appcent.todo.model.entity.TodoListElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoListElementRepository extends JpaRepository<TodoListElement,Integer> {

    Optional<List<TodoListElement>> findAllByTodoListIdContains(int id);
}
