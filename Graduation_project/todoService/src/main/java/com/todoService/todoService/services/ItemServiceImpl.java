package com.todoService.todoService.services;

import com.todoService.todoService.entity.Item;
import com.todoService.todoService.entity.ItemDetails;
import com.todoService.todoService.repositories.ItemDetailsRepository;
import com.todoService.todoService.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemDetailsRepository itemDetailsRepository;
    @Transactional
    @Override
    public ItemDetails save(ItemDetails itemDetails,Item item) {
        if (itemDetails == null) {
            throw new IllegalArgumentException("ItemDetails must not be null");
        }
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }
        itemDetailsRepository.save(itemDetails);
        item.setItemDetails(itemDetails);
        itemRepository.save(item);
        System.out.println("Both item and itemDetails have been saved!!");
        return itemDetails;
    }

    @Override
    public Optional<ItemDetails> findById(int id) {
        Optional<ItemDetails> itemDetails = itemDetailsRepository.findById(id);
        return itemDetails;
    }

    @Override
    public String deleteById(int id) {
        itemDetailsRepository.deleteById(id);
        //itemRepository.deleteById(id);
        return "item has been deleted successfully!!";
    }

    @Override
    public ItemDetails update(ItemDetails itemDetails,Item item) {
        itemDetails.setId(itemDetails.getId());
        itemDetails.setStatus(itemDetails.getStatus());
        itemDetails.setPriority(itemDetails.getPriority());
        itemDetails.setCreated_at(itemDetails.getCreated_at());
        itemDetails.setDescription(itemDetails.getDescription());

        item.setTitle(item.getTitle());
        itemDetailsRepository.save(itemDetails);
        item.setItemDetails(itemDetails);
        itemRepository.save(item);
        System.out.println("Item has been updated successfully!!!");
        return itemDetails;
    }
}
