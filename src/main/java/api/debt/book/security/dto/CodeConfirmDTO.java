package api.debt.book.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeConfirmDTO {
    @NotBlank(message = "Code required")
    private String code;
}
