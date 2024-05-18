package com.todoService.todoService.model.response;

import com.todoService.todoService.entity.Priority;
import com.todoService.todoService.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailsResponse {
    private int id;

    private String description;

    private Date created_at;

    private Priority priority;

    private Status status;

    private String title;

    private int user_id;
}
