package api.debt.book.jwt.dto;

import api.debt.book.profile.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtDTO {
    private String id;
    private String username;
    private List<ProfileRole> roleList;
}
