package authservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdateRequestDTO {
    private String token;
    private String newPassword;
}
