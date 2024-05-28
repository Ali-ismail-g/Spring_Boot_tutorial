package com.todoService.todoService.controllers;

import com.todoService.todoService.config.Config;
import com.todoService.todoService.entity.Item;
import com.todoService.todoService.entity.ItemDetails;
import com.todoService.todoService.model.request.ItemDetailsRequest;
import com.todoService.todoService.model.request.ServiceRequest;
import com.todoService.todoService.services.ItemService;
import com.todoService.todoService.services.ItemServiceImpl;
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

    @Autowired
    private ItemServiceImpl itemServiceImpl;

    @Autowired
    private Config config;

    @PostMapping(value ="")
    public ResponseEntity<ItemDetails> addNewItem(@RequestBody ItemDetailsRequest itemDetailsRequest,@RequestHeader("Authorization")String token){
        ItemDetails itemDetails = itemDetailsRequest.getItemDetails();
        Item item = itemDetailsRequest.getItem();
        String accessToken = itemServiceImpl.extractToken(token);
        ServiceRequest serviceRequest = new ServiceRequest(config.authServiceUrl,accessToken);
        return ResponseEntity.ok(itemService.save(itemDetails,item,serviceRequest));
    }
    @GetMapping(value = "")
    public ResponseEntity<Optional<ItemDetails>> getItem(@RequestParam("itemId") int id,@RequestHeader("Authorization")String token){
        String accessToken = itemServiceImpl.extractToken(token);
        ServiceRequest serviceRequest = new ServiceRequest(config.authServiceUrl,accessToken);
        return ResponseEntity.ok(itemService.findById(id,serviceRequest));
    }
    @PutMapping(value = "")
    public ResponseEntity<ItemDetails> updateItem(@RequestBody ItemDetailsRequest itemDetailsRequest,@RequestHeader("Authorization")String token){
        ItemDetails itemDetails = itemDetailsRequest.getItemDetails();
        Item item = itemDetailsRequest.getItem();
        String accessToken = itemServiceImpl.extractToken(token);
        ServiceRequest serviceRequest = new ServiceRequest(config.authServiceUrl,accessToken);
        return ResponseEntity.ok(itemService.update(itemDetails,item,serviceRequest));
    }
    @DeleteMapping(value = "")
    public ResponseEntity<String> deleteItem(@RequestParam("itemId") int id,@RequestHeader("Authorization")String token){
        String accessToken = itemServiceImpl.extractToken(token);
        ServiceRequest serviceRequest = new ServiceRequest(config.authServiceUrl,accessToken);
        return ResponseEntity.ok(itemService.deleteById(id,serviceRequest));
    }
    @GetMapping(value="/user")
    public ResponseEntity<String> getUserId(@RequestHeader("Authorization")String token){
        String accessToken = itemServiceImpl.extractToken(token);
        ServiceRequest serviceRequest = new ServiceRequest(config.authServiceUrl,accessToken);
        System.out.println("accessToken:  "+accessToken);
        return ResponseEntity.ok(itemService.getUserId(serviceRequest));
    }
}
