package com.company.products.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.company.products.model.Item;
import com.company.products.repository.ItemRepository;

@RestController
@RequestMapping("/api/v1")
public class ItemController {
    @Autowired
    ItemRepository itemRepo;

    @RequestMapping("/items")
    @ResponseBody
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> items =  itemRepo.findAll();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @GetMapping("/item/{itemId}")
    @ResponseBody
    public ResponseEntity<Item> getItem(@PathVariable Long itemId){
        Optional<Item> item = itemRepo.findById(itemId);
        return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/add",consumes = {"application/json"},produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Item> addItem(@RequestBody Item item, UriComponentsBuilder builder){
        itemRepo.save(item);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/addItem/{id}").buildAndExpand(item.getId()).toUri());
        return new ResponseEntity<Item>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<Item> updateItem(@RequestBody Item item){
        if(item != null){
            itemRepo.save(item);
        }
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        Optional<Item> item = itemRepo.findById(id);
        itemRepo.delete(item.get());
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
