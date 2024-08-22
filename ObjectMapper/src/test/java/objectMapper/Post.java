package objectMapper;


import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class Post {

    private String userId;
    private String id;
    private String title;
    private String body;

}
