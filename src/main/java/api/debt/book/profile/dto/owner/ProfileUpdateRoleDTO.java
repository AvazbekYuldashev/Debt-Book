package api.debt.book.profile.dto.owner;

import api.debt.book.profile.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileUpdateRoleDTO {
    private String id;
    private ProfileRole role;
}
