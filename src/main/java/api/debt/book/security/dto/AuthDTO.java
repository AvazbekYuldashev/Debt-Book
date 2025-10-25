package api.debt.book.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    @NotBlank(message = "Username required")
    private String username;

    @NotBlank(message = "New password required")
    private String password;
}
