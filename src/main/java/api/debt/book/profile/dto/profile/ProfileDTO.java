package api.debt.book.profile.dto.profile;

import api.debt.book.attach.dto.AttachDTO;
import api.debt.book.profile.enums.ProfileRole;
import api.debt.book.security.enums.GeneralStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String username;
    private AttachDTO photo;
    private GeneralStatus status;

    private ProfileRole role;
    private String jwt;
    private LocalDateTime createdDate;
}
