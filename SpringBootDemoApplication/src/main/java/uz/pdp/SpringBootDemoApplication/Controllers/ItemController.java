package uz.pdp.SpringBootDemoApplication.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.SpringBootDemoApplication.Domains.Item;
import uz.pdp.SpringBootDemoApplication.Domains.Upload;
import uz.pdp.SpringBootDemoApplication.Exceptions.NotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
public class ItemController {

    Environment environment;
    AtomicLong counter = new AtomicLong(0);

    Map<Upload, Item> items = new HashMap<Upload, Item>();


    @GetMapping(value = "/getItems")
    public ResponseEntity<Map<Upload, Item>> getItems() {
        return ResponseEntity.ok(items);
    }

    @GetMapping(value = "/getItems/{id}", produces = "application/xml")
    public ResponseEntity<Item> getItemsByID(@PathVariable Long id) {
        return ResponseEntity.ok().body(items.values().stream().filter(item -> item.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found")));

    }

    @PostMapping("/getItems/add")
    public ResponseEntity<Object> addItem(@Valid @RequestBody Item item, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        String randomName = UUID.randomUUID().toString();
        String path = environment.getProperty("uploads.path") + randomName + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());

        Upload upload = Upload.builder()
                .size(file.getSize())
                .originalName(file.getOriginalFilename())
                .generatedName(randomName)
                .uploadedPath(path)
                .build();

        item.setId(counter.getAndIncrement());
        item.setPath(path);
        items.put(upload, item);
        return new ResponseEntity<>(List.of(item, upload), HttpStatus.CREATED);
    }
        @PutMapping("/getItems/update/{id}")
        public ResponseEntity<Item> updateStore(@Valid @RequestBody Item item , @PathVariable Long id) {
            Item currentItem = items.values().stream()
                    .filter(v -> v.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException( "Item with id " + id + " not found"));
            currentItem.setName(item.getName());
            currentItem.setDescription(item.getDescription());
            currentItem.setPrice(item.getPrice());
            return ResponseEntity.ok().body(currentItem);
        }
        @DeleteMapping("/item/delete/{id}")
        public ResponseEntity<String> deleteStore(@PathVariable Long id) {
            boolean isRemoved = items.entrySet().removeIf(entry -> Objects.equals(entry.getValue().getId(), id));
            if (!isRemoved)
                throw new NotFoundException("Store not found");
            return ResponseEntity.ok().body("Store deleted successfully");
        }

}
