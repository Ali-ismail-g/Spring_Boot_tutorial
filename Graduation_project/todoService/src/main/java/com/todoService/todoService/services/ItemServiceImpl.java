package com.todoService.todoService.services;

import com.todoService.todoService.config.RestTemplateInterceptor;
import com.todoService.todoService.entity.Item;
import com.todoService.todoService.entity.ItemDetails;
import com.todoService.todoService.model.request.ServiceRequest;
import com.todoService.todoService.model.response.UserDetailsResponse;
import com.todoService.todoService.repositories.ItemDetailsRepository;
import com.todoService.todoService.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{
    private String authServiceUrl="http://localhost:8080/rest/auth/details";
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemDetailsRepository itemDetailsRepository;
    private final String TOKEN_PREFIX = "Bearer ";

    private RestTemplate restTemplate = new RestTemplate();

    public String extractToken(String token){
        return token.substring(TOKEN_PREFIX.length());
    }
    public String getUserId(ServiceRequest serviceRequest){
    try {
        //restTemplate.getInterceptors().add(new RestTemplateInterceptor(jwtToken));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer "+serviceRequest.getJwtToken());
        //httpHeaders.setBearerAuth(jwtToken);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(serviceRequest.getApiUrl(),HttpMethod.GET,entity, UserDetailsResponse.class);
        System.out.println("res "+response);
        UserDetailsResponse userDetailsResponse = response.getBody();
        String id =userDetailsResponse.getId();
        System.out.println("id:  "+id);
        return id;
    }catch (HttpClientErrorException e){
        System.err.println("HTTP Status: " + e.getStatusCode());
        System.err.println("HTTP Response Body: " + e.getResponseBodyAsString());
        throw e;
    }
    }
    @Transactional
    @Override
    public ItemDetails save(ItemDetails itemDetails, Item item, ServiceRequest serviceRequest) {
        String id = getUserId(serviceRequest);
        if (itemDetails == null) {
            throw new IllegalArgumentException("ItemDetails must not be null");
        }
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }
        item.setUser_id(id);
        itemDetailsRepository.save(itemDetails);
        item.setItemDetails(itemDetails);
        itemRepository.save(item);
        System.out.println("Both item and itemDetails have been saved!!");
        return itemDetails;
    }

    @Override
    public Optional<ItemDetails> findById(int id,ServiceRequest serviceRequest) {
        String user_id = getUserId(serviceRequest);
        if(user_id == null || user_id == "0"){
            throw new RuntimeException("user is not exist!!");
        }
        Optional<ItemDetails> itemDetails = itemDetailsRepository.findById(id);
        return itemDetails;
    }

    @Override
    public String deleteById(int id,ServiceRequest serviceRequest) {
        String user_id = getUserId(serviceRequest);
        if(user_id == null || user_id == "0"){
            throw new RuntimeException("user does not exist!!");
        }
        itemDetailsRepository.deleteById(id);
        //itemRepository.deleteById(id);
        return "item has been deleted successfully!!";
    }

    @Override
    public ItemDetails update(ItemDetails itemDetails,Item item,ServiceRequest serviceRequest) {
        String id = getUserId(serviceRequest);
        if(id == null || id == "0"){
            throw new RuntimeException("user does not exist!!");
        }
        itemDetails.setId(itemDetails.getId());
        itemDetails.setStatus(itemDetails.getStatus());
        itemDetails.setPriority(itemDetails.getPriority());
        itemDetails.setCreated_at(itemDetails.getCreated_at());
        itemDetails.setDescription(itemDetails.getDescription());

        item.setTitle(item.getTitle());
        item.setUser_id(id);
        itemDetailsRepository.save(itemDetails);
        item.setItemDetails(itemDetails);
        itemRepository.save(item);
        System.out.println("Item has been updated successfully!!!");
        return itemDetails;
    }
}
