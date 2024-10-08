package tmrv.dev.houseonlinemarket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tmrv.dev.houseonlinemarket.entities.User;
import tmrv.dev.houseonlinemarket.service.PropertyService;
import tmrv.dev.houseonlinemarket.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Controller", description = "These methods are available to Admin users")
public class AdminController {

    private final PropertyService propertyService;
    private final UserService userService;

    public AdminController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
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

    @Operation(summary = "Get all properties", description = "Retrieve all properties")
    @ApiResponse(responseCode = "200", description = "Properties retrieved successfully")
    @ApiResponse(responseCode = "204", description = "No properties available")
    @GetMapping("/getAll")
    public ResponseEntity<List> getAllProperties() {
        List properties = propertyService.getAll();
        if (properties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(properties);
    }

    @Operation(summary = "Get all users", description = "Retrieve all users")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    @ApiResponse(responseCode = "204", description = "No users available")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List> getAllUsers() {
        List users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Block User", description = "Block an existing user by ID")
    @ApiResponse(responseCode = "200", description = "User blocked successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "409", description = "User is already blocked")
    @GetMapping("/blockUser/{id}")
    public ResponseEntity<User> blockUser(@Parameter(description = "ID of the user to block") @PathVariable Long id) {
        User user = userService.blockActive(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Unblock User", description = "Unblock an existing user by ID")
    @ApiResponse(responseCode = "200", description = "User unblocked successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "409", description = "User is already unblocked")
    @GetMapping("/unblockUser/{id}")
    public ResponseEntity<User> unBlockUser(@Parameter(description = "ID of the user to unblock") @PathVariable Long id) {
        User user = userService.unblockActive(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }
}
