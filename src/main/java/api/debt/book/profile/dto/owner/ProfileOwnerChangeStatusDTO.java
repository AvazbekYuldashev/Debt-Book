package api.debt.book.profile.dto.owner;

import api.debt.book.security.enums.GeneralStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileOwnerChangeStatusDTO {
    @NotNull
    private String id;
    @NotNull
    private GeneralStatus status;
}
