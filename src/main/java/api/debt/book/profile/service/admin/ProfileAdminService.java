package api.debt.book.profile.service.admin;


import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.util.AppResponseUtil;
import api.debt.book.exception.exps.ProfileStatusException;
import api.debt.book.profile.dto.owner.ProfileOwnerChangeStatusDTO;
import api.debt.book.profile.dto.owner.ProfileOwnerFilterDTO;
import api.debt.book.profile.dto.owner.ProfileUpdateRoleDTO;
import api.debt.book.profile.dto.profile.ProfileResponseDTO;
import api.debt.book.profile.entity.ProfileEntity;
import api.debt.book.profile.enums.ProfileRole;
import api.debt.book.profile.service.core.ProfileCoreService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class ProfileAdminService extends ProfileCoreService {

    public AppResponse<String> changeRole(@Valid ProfileUpdateRoleDTO dto, AppLanguage lang) {
        if (dto.getRole().equals(ProfileRole.ROLE_OWNER)){
            throw new ProfileStatusException("asd");
        }
        ProfileEntity entity = findById(dto.getId(), lang);
        if (entity.getStatus().equals(ProfileRole.ROLE_OWNER)){
            throw new ProfileStatusException("asd");
        }
        return AppResponseUtil.chek(updateRole(dto.getId(), dto.getRole()));
    }

    public AppResponse<String> changeStatus(@Valid ProfileOwnerChangeStatusDTO dto, AppLanguage lang) {
        ProfileEntity entity = findById(dto.getId(), lang);
        if (entity.getStatus().equals(ProfileRole.ROLE_OWNER)){
            throw new ProfileStatusException("asd");
        }
        return AppResponseUtil.chek(updateStatus(dto.getId(), dto.getStatus()));
    }

    public Page<ProfileResponseDTO> filter(ProfileOwnerFilterDTO dto, int currentPage, Integer size) {
        return null;
    }

    public AppResponse<String> deleteById(String id, AppLanguage lang) {
        ProfileEntity profile = findById(id, lang);
        if (profile.getStatus().equals(ProfileRole.ROLE_OWNER)){
            throw new ProfileStatusException("asd");
        }
        return deletebyId(id, lang);
    }
}
