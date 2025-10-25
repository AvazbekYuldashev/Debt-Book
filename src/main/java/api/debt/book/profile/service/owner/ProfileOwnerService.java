package api.debt.book.profile.service.owner;


import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.dto.FilterResultDTO;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.service.ResourceBoundleService;
import api.debt.book.profile.dto.owner.*;
import api.debt.book.profile.dto.profile.ProfileResponseDTO;
import api.debt.book.profile.entity.ProfileEntity;
import api.debt.book.profile.mapper.ProfileMapper;
import api.debt.book.profile.repository.CustomProfileRepository;
import api.debt.book.profile.repository.ProfileRepository;
import api.debt.book.profile.service.profile.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfileOwnerService extends ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBoundleService boundleService;
    @Autowired
    private BCryptPasswordEncoder bc;
    @Autowired
    private CustomProfileRepository customProfileRepository;
    @Autowired
    private ProfileMapper profileMapper;


    /// Updates the status for the given user ID.
    public AppResponse<String> changeStatus(ProfileOwnerChangeStatusDTO dto, AppLanguage lang) {
        profileRepository.changeStatus(dto.getId(), dto.getStatus());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
    }

    public Page<ProfileResponseDTO> filter(ProfileOwnerFilterDTO dto, int page, int size) {
        FilterResultDTO<ProfileEntity> resultDTO = customProfileRepository.filter(dto, page, size);
        List<ProfileResponseDTO> dtoList = resultDTO.getList().stream()
                .map(profileMapper::toallResponseDTO).toList();
        return new PageImpl<>(dtoList, PageRequest.of(page, size), resultDTO.getCount());
    }

    ///  Update the role for the given user ID.
    public AppResponse<String> changeRole(@Valid ProfileOwnerChangeRoleDTO dto, AppLanguage lang) {
        profileRepository.changeRole(dto.getId(), dto.getRole());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
    }

    public AppResponse<String> changePassword(@Valid ProfileOwnerUpdatePassword dto, AppLanguage lang) {
        profileRepository.changePassword(dto.getId(), dto.getPassword());
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed", lang));
    }

}
