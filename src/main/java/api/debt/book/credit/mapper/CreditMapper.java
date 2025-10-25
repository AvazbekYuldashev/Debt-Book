package api.debt.book.credit.mapper;

import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.entity.CreditEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {
    public CreditResponseDTO toResponseDTO(CreditEntity entity) {
        CreditResponseDTO dto = new CreditResponseDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setDebtorId(entity.getDebtorId());
        dto.setCreditorId(entity.getCreditorId());
        dto.setCreditorCheck(entity.getCreditorCheck());
        dto.setDebtorCheck(entity.getDebtorCheck());
        return dto;
    }
}
