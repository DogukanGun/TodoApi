package com.appcent.todo.repository;

import com.appcent.todo.model.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoList,Integer> {

    Optional<List<TodoList>> findAllByListNameContains(String listName);
}
