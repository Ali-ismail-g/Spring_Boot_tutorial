package com.todoService.todoService.controllers;

import com.todoService.todoService.entity.Item;
import com.todoService.todoService.entity.ItemDetails;
import com.todoService.todoService.model.request.ItemDetailsRequest;
import com.todoService.todoService.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/rest/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(value ="/add")
    public ResponseEntity<ItemDetails> addNewItem(@RequestBody ItemDetailsRequest itemDetailsRequest){
        ItemDetails itemDetails = itemDetailsRequest.getItemDetails();
        Item item = itemDetailsRequest.getItem();
        return ResponseEntity.ok(itemService.save(itemDetails,item));
    }
    @GetMapping(value = "/")
    public ResponseEntity<Optional<ItemDetails>> getItem(@RequestParam("itemId") int id){
        return ResponseEntity.ok(itemService.findById(id));
    }
    @PutMapping(value = "/update")
    public ResponseEntity<ItemDetails> updateItem(@RequestBody ItemDetailsRequest itemDetailsRequest){
        ItemDetails itemDetails = itemDetailsRequest.getItemDetails();
        Item item = itemDetailsRequest.getItem();
        return ResponseEntity.ok(itemService.update(itemDetails,item));
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteItem(@RequestParam("itemId") int id){
        return ResponseEntity.ok(itemService.deleteById(id));
    }
}
