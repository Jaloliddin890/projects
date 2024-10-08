package tmrv.dev.houseonlinemarket.controller;
;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tmrv.dev.houseonlinemarket.dto.PropertyDto;
import tmrv.dev.houseonlinemarket.dto.UserDtoForProperty;
import tmrv.dev.houseonlinemarket.entities.domains.Type;
import tmrv.dev.houseonlinemarket.service.PropertyService;
import tmrv.dev.houseonlinemarket.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")

@Tag(name = "User Controller", description = "These Methods are belongs to User")
public class UserController {
    private final PropertyService propertyService;
    private final UserService userService;

    public UserController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }



    @Operation(summary = "Create a new property")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Property created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PropertyDto> createProperty(
            @Parameter(description = "Title of the property") @RequestParam String title,
            @Parameter(description = "Description of the property") @RequestParam String description,
            @Parameter(description = "Price of the property") @RequestParam double price,
            @Parameter(description = "Location of the property") @RequestParam String location,
            @Parameter(description = "Type of the property") @RequestParam Type type,
            @Parameter(description = "Availability status of the property") @RequestParam boolean available,
            @Parameter(description = "Image file for the property", required = true) @RequestParam MultipartFile imageFile) {


        UserDtoForProperty userDto = new UserDtoForProperty(userService.getCurrentUserId(), userService.getCurrentUsername());

        PropertyDto propertyDto = new PropertyDto(title, description, price, location, type, available, imageFile, userDto);

        PropertyDto createdProperty = propertyService.createProperty(propertyDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }


    @Operation(summary = "Update an existing property")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Property updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProperty(
            @Parameter(description = "Id of the property to be updated") @RequestParam Long id,
            @Parameter(description = "Title of the property") @RequestParam String title,
            @Parameter(description = "Description of the property") @RequestParam String description,
            @Parameter(description = "Price of the property") @RequestParam double price,
            @Parameter(description = "Location of the property") @RequestParam String location,
            @Parameter(description = "Type of the property") @RequestParam Type type,
            @Parameter(description = "Availability status of the property") @RequestParam boolean available,
            @Parameter(description = "Image file for the property", required = true) @RequestParam MultipartFile imageFile) {

        UserDtoForProperty userDto = new UserDtoForProperty(userService.getCurrentUserId(), userService.getCurrentUsername());
        PropertyDto propertyDto = new PropertyDto(title, description, price, location, type, available, imageFile, userDto);
        String updateResponse = propertyService.updateProperty(id, propertyDto);

        return ResponseEntity.status(HttpStatus.OK).body(updateResponse);
    }

    @Operation(summary = "Delete a property", description = "Deletes a property based on its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Property successfully deleted."),
            @ApiResponse(responseCode = "404", description = "Property not found.")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProperty(@Parameter(description = "Id of the property") @PathVariable Long id){
        propertyService.deleteProperty(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Get all properties", description = "Getting all properties")
    @ApiResponse(responseCode = "200", description = "List of properties retrieved successfully.")
    @GetMapping("/getAll")
    public ResponseEntity<List> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.getAll());
    }


}
