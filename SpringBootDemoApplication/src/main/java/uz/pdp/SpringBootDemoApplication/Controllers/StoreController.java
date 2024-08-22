package uz.pdp.SpringBootDemoApplication.Controllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.SpringBootDemoApplication.Domains.Store;
import uz.pdp.SpringBootDemoApplication.Exceptions.NotFoundException;


import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StoreController {
    AtomicLong counter = new AtomicLong(0);

    List<Store> stores = new ArrayList<>(){{
        add(new Store(counter.incrementAndGet(),"Just","There is, you can find clothes"));
        add(new Store(counter.incrementAndGet(),"Terra Pro","Like Just"));
    }};


    @GetMapping(value = "/get")
    public ResponseEntity<List<Store>> getAll(){
        return ResponseEntity.ok(stores);
    }

    @GetMapping(value = "/get/{id}", produces = "application/xml" )
    public ResponseEntity<Store> getById(@PathVariable long id){
        return ResponseEntity.ok(stores.stream().filter(t->t.getId() == id).findFirst()
                .orElseThrow(()-> new NotFoundException("Store with id " + id + " not found")));
    }

    @PostMapping("/get/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Store addStore(@Valid @RequestBody Store store) {
        store.setId(counter.incrementAndGet());
        stores.add(store);
        return store;
    }
    @PutMapping("/get/update/{id}")
    public ResponseEntity<Store> updateStore(@Valid @RequestBody Store store, @PathVariable long id){
        Store curStore = stores.stream().filter(t -> t.getId() == id).findFirst()
                .orElseThrow(() -> new NotFoundException("Store with id " + id + " not found"));
        curStore.setName(store.getName());
        curStore.setDesc(store.getDesc());
        return ResponseEntity.ok().body(store);
    }


    @DeleteMapping("/get/delete/{id}")
    public ResponseEntity<String> deleteStore(@Valid @PathVariable long id){
        boolean isDeleted = stores.removeIf(f -> f.getId() == id);
        if(!isDeleted){
            throw new NotFoundException("Store with id " + id + " not found");
        }
        return ResponseEntity.ok().body("Store with id " + id + " is deleted");
    }


}
