package api.debt.book.creditor.mapper;

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
}
