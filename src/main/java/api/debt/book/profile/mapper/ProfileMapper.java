package api.debt.book.profile.mapper;

import api.debt.book.attach.service.AttachService;
import api.debt.book.profile.dto.profile.ProfileDTO;
import api.debt.book.profile.dto.profile.ProfileResponseDTO;
import api.debt.book.profile.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    @Autowired
    private AttachService attachService;


    public ProfileEntity toEntity(ProfileDTO dto) {
        return null;
    }

    public ProfileDTO toInfoDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setUsername(entity.getUsername());
        dto.setPhoto(attachService.getDTOById(entity.getPhotoId()));
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        return dto;
    }


    public ProfileResponseDTO toallResponseDTO(ProfileEntity entity) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setUsername(entity.getUsername());
        dto.setPhotoId(entity.getPhotoId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        return dto;

    }
}
