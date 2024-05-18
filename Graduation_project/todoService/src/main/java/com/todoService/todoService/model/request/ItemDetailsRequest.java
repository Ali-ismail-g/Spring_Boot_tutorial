package com.todoService.todoService.model.request;

import com.todoService.todoService.entity.Item;
import com.todoService.todoService.entity.ItemDetails;
import com.todoService.todoService.entity.Priority;
import com.todoService.todoService.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailsRequest {
    private ItemDetails itemDetails;
    private Item item;
}
