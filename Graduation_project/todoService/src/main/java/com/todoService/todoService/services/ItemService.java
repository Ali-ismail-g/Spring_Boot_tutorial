package com.todoService.todoService.services;

import com.todoService.todoService.entity.Item;
import com.todoService.todoService.entity.ItemDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ItemService {
    public ItemDetails save(ItemDetails itemDetails,Item item);
    public Optional<ItemDetails> findById(int id);
    public String deleteById(int id);
    public ItemDetails update(ItemDetails itemDetails,Item item);
}
