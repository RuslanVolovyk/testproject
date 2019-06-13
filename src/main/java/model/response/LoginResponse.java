package model.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    @JsonProperty("phoneNumber")
    public String phoneNumber;
    @JsonProperty("verificationCode")
    public String verificationCode;

    public LoginResponse() {

    }
}
