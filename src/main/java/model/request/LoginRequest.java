package model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @JsonProperty("email")
    public String email;
    @JsonProperty("organisationId")
    public String organisationId;
    @JsonProperty("password")
    public String password;
}
