package com.tmrv.SecurityJWT.controller;

import com.tmrv.SecurityJWT.model.Item;
import com.tmrv.SecurityJWT.repository.ItemRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/item/*")
@Tag(name = "Item Controller" , description = "This Controller for Item's service")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Item> create( @RequestBody Item item) {
        itemRepository.save(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Item> update(@RequestBody Item item) {
        itemRepository.save(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        itemRepository.findById(id).ifPresent(itemRepository::delete
        );
        return new ResponseEntity<>("Successfully Deleted - Item", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.POST)
    public ResponseEntity<Item> get(@PathVariable Long id) {
        Item item = itemRepository.findById(id).orElseThrow(()-> new RuntimeException("Item Not Found"));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}

