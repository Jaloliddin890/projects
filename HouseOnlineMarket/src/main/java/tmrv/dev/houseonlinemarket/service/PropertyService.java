package tmrv.dev.houseonlinemarket.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tmrv.dev.houseonlinemarket.dto.PropertyDto;
import tmrv.dev.houseonlinemarket.dto.UserDtoForProperty;
import tmrv.dev.houseonlinemarket.entities.Property;
import tmrv.dev.houseonlinemarket.entities.User;
import tmrv.dev.houseonlinemarket.repository.PropertyRepository;
import tmrv.dev.houseonlinemarket.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public PropertyService(PropertyRepository propertyRepository, UserRepository userRepository, ObjectMapper objectMapper, UserService userService) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public PropertyDto createProperty(PropertyDto propertyDto) {
        Property property = new Property();
        User user = userRepository.findById(userService.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.isActive()) {

            property.setUser(user);
            SetterForProperty(propertyDto, property);
        } else {
            throw new RuntimeException("User is not active. That User cannot create Property");
        }

        Property savedProperty = propertyRepository.save(property);
        return new PropertyDto(
                savedProperty.getTitle(),
                savedProperty.getDescription(),
                savedProperty.getPrice(),
                savedProperty.getLocation(),
                savedProperty.getType(),
                savedProperty.isAvailable(),
                null,
                new UserDtoForProperty(user.getId(), user.getUsername())
        );
    }


    public String updateProperty(Long id, PropertyDto propertyDto) {
        propertyRepository.findById(id).ifPresent(property -> {
            User user = userRepository.findById(userService.getCurrentUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (user.isActive()) {
                property.setUser(user);
                SetterForProperty(propertyDto, property);
                propertyRepository.save(property);
            } else {
                throw new RuntimeException("User is not active. That User cannot update Property");
            }
        });
        return "Updated Property's id: " + id;
    }

    public void deleteProperty(Long id) {
        User user = userRepository.findById(userService.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.isActive()) {
            propertyRepository.deleteById(id);
        } else {
            throw new RuntimeException("User is not active. That User cannot delete Property");
        }
    }

    public List<PropertyDto> getAll() {

        List<Property> properties = propertyRepository.findAll();

        return properties.stream().map(property -> new PropertyDto(
                property.getTitle(),
                property.getDescription(),
                property.getPrice(),
                property.getLocation(),
                property.getType(),
                property.isAvailable(),
                null,
                new UserDtoForProperty(property.getUser().getId(), property.getUser().getUsername())
        )).collect(Collectors.toList());
    }




    private void SetterForProperty(PropertyDto propertyDto, Property property) {
        property.setTitle(propertyDto.title());
        property.setDescription(propertyDto.description());
        property.setPrice(propertyDto.price());
        property.setLocation(propertyDto.location());
        property.setType(propertyDto.type());
        property.setAvailable(propertyDto.available());
        MultipartFile imageFile = propertyDto.imageFile();


        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String uploadURL = "/home/jaloliddin/Desktop/HouseImages";
                Path filePath = Paths.get(uploadURL, fileName);
                Files.write(filePath, imageFile.getBytes());
                property.setImagePath(filePath.toString());
            } catch (IOException e) {
                throw new RuntimeException("Error saving image file", e);
            }
        }
    }

}
