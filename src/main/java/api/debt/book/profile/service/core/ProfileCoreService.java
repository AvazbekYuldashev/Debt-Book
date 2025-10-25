package api.debt.book.profile.service.core;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.service.ResourceBoundleService;
import api.debt.book.attach.service.AttachService;
import api.debt.book.exception.exps.ResourceNotFoundException;
import api.debt.book.profile.dto.profile.*;
import api.debt.book.profile.entity.ProfileEntity;
import api.debt.book.profile.enums.ProfileRole;
import api.debt.book.profile.mapper.ProfileMapper;
import api.debt.book.profile.repository.ProfileRepository;
import api.debt.book.security.enums.GeneralStatus;
import api.debt.book.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileCoreService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBoundleService boundleService;
    @Autowired
    private AttachService attachService;
    @Autowired
    private ProfileMapper profileMapper;

    public ProfileDTO getMe(AppLanguage lang) {
        String id = SpringSecurityUtil.getCurrentUserId();
        ProfileEntity profile = findById(id, lang);
        return profileMapper.toInfoDTO(profile);
    }

    public ProfileDTO getById(String id, AppLanguage lang) {
        return profileMapper.toInfoDTO(findById(id, lang));
    }
    /// Finds the profile by the given ID.
    /// Returns an error if not found.
    public ProfileEntity findById(String id, AppLanguage lang) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException(boundleService.getMessage("profile.not.found", lang) + ": " + id);
        }
        return optional.get();
    }

    public AppResponse<String> updatePhoto(String photoId, AppLanguage lang) {
        ProfileEntity profile = findById(SpringSecurityUtil.getCurrentUserId(), lang);
        if (profile.getPhotoId() != null && !profile.getPhotoId().equals(photoId)){
            attachService.deleteSoft(profile.getPhotoId());
        }
        profileRepository.updatePhoto(profile.getId(), photoId);

        return new AppResponse<>(boundleService.getMessage("update.successfully.completed",lang));
    }

    public AppResponse<String> deletebyId(String id, AppLanguage lang) {
        ProfileEntity profile = findById(id, lang);
        profileRepository.deleteSoftById(profile.getId(), false);
        return new AppResponse<>(boundleService.getMessage("update.successfully.completed",lang));
    }

    public ProfileEntity findByUsername(String username, AppLanguage lang) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(username);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(boundleService.getMessage("username.not.found", lang));
        }
        return optional.get();
    }

    public PageImpl<ProfileResponseDTO> getAll(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProfileEntity> pageObj = profileRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<ProfileResponseDTO> response = pageObj.getContent().stream().map(profileMapper::toallResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public boolean updateRole(String id, ProfileRole role){
        return profileRepository.changeRole(id, role) > 0;
    }

    public boolean updateStatus(String id, GeneralStatus status){
        return profileRepository.changeStatus(id, status) > 0;
    }
}
