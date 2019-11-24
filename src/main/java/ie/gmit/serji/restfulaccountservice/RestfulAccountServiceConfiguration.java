package ie.gmit.serji.restfulaccountservice;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class RestfulAccountServiceConfiguration extends Configuration {

    @NotEmpty
    private String grpcHost;
    @NotNull
    private Integer grpcPort;


    public String getGrpcHost() {
        return grpcHost;
    }

    public Integer getGrpcPort() {
        return grpcPort;
    }

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

}
