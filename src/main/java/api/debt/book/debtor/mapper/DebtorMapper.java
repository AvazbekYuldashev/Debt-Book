package api.debt.book.debtor.mapper;
import api.debt.book.debtor.dto.core.DebtorResponseDTO;
import api.debt.book.debtor.entity.DebtorEntity;
import org.springframework.stereotype.Component;

@Component
public class DebtorMapper {
    public DebtorResponseDTO toResponseDTO(DebtorEntity entity) {
        DebtorResponseDTO dto = new DebtorResponseDTO();
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
