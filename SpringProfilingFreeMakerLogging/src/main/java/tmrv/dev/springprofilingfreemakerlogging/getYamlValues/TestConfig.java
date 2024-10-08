package tmrv.dev.springprofilingfreemakerlogging.getYamlValues;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class TestConfig {
    private String profile;
    private String name;
    private String environment;
    private boolean enabled;
    private List<String> servers;
}
