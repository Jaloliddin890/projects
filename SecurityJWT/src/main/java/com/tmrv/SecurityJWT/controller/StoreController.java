package com.tmrv.SecurityJWT.controller;

import com.tmrv.SecurityJWT.model.Store;
import com.tmrv.SecurityJWT.repository.StoreRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/store/*")
@Tag(name = "Store Controller" , description = "This Controller for Store's service")
public class StoreController {
    private final StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    @Operation(description = "This api for creating Store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            })
    })
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Store> create(@RequestBody Store entity) {
        storeRepository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
    @Operation(description = "This api for updating Store")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Store> update(@RequestBody Store entity) {
        storeRepository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
    @Operation(description = "This api for deleting Store ")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        storeRepository.findById(id)
                .ifPresentOrElse(storeRepository::delete, () -> {
                    throw new RuntimeException("Store not found");
                });
        return new ResponseEntity<>("Successfully Deleted - Store", HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "This api for getting Store", deprecated = false)
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Store> get(@PathVariable Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new RuntimeException("Store not found"));
        return new ResponseEntity<>(store, HttpStatus.OK);
    }
}