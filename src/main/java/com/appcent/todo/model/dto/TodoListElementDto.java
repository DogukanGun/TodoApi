package com.appcent.todo.model.dto;


 import com.appcent.todo.model.enums.TodoElementStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TodoListElementDto {
    private Integer id;

    private String text;

    private Integer todoListId;

    private TodoElementStatus status;
}
