package api.debt.book.creditor.mapper;

import api.debt.book.creditor.dto.core.CreditorCreatedDTO;
import api.debt.book.creditor.dto.core.CreditorResponseDTO;
import api.debt.book.creditor.entity.CreditorEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditorMapper {
    public CreditorResponseDTO toResponseDTO(CreditorEntity entity) {
        CreditorResponseDTO dto = new CreditorResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPatronymic(entity.getPatronymic());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setBirthDate(entity.getBirthDate());
        dto.setProfileId(entity.getProfileId());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public CreditorEntity toCreatedEntity(CreditorCreatedDTO dto) {
        CreditorEntity entity = new CreditorEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPatronymic(dto.getPatronymic());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setBirthDate(dto.getBirthDate());
        entity.setProfileId(dto.getProfileId());
        return entity;
    }
}
