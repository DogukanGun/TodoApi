package com.appcent.todo.model.entity;

import com.appcent.todo.model.enums.TodoElementStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TodoListElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;

    @Column(name = "todo_list_id",nullable = false)
    private Integer todoListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_list_id",insertable = false,updatable = false)
    private TodoList todoList;

    @Column(insertable = false,updatable = false)
    @Builder.Default
    private TodoElementStatus status = TodoElementStatus.PENDING;
}
