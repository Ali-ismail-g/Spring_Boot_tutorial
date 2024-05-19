package com.todoService.todoService.services;

import com.todoService.todoService.entity.Item;
import com.todoService.todoService.entity.ItemDetails;
import com.todoService.todoService.model.request.ServiceRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ItemService {
    public String getUserId(ServiceRequest serviceRequest);
    public ItemDetails save(ItemDetails itemDetails, Item item, ServiceRequest serviceRequest);
    public Optional<ItemDetails> findById(int id,ServiceRequest serviceRequest);
    public String deleteById(int id,ServiceRequest serviceRequest);
    public ItemDetails update(ItemDetails itemDetails,Item item,ServiceRequest serviceRequest);
}
