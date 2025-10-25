package api.debt.book.profile.dto.profile;

import api.debt.book.profile.enums.ProfileRole;
import api.debt.book.security.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileResponseDTO {
    private String id;
    private String name;
    private String surname;
    private String username;
    private String photoId;
    private GeneralStatus status;

    private ProfileRole role;
    private LocalDateTime createdDate;
}
