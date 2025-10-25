package api.debt.book.profile.dto.profile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileUsernameUpdateDTO {
    @NotBlank(message = "Wrong username")
    private String username;
}
