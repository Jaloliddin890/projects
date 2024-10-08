package tmrv.dev.springprofilingfreemakerlogging.getYamlValues;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev")
@ConfigurationProperties(prefix = "spring.dev")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevProperties {
    private String profile;
    private String name;
    private String environment;
    private boolean enabled;
    private List<String> servers;

    public DevConfig get(){
        return new DevConfig(profile, name, environment, enabled, servers);
    }
}
