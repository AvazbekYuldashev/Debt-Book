package api.debt.book.debt.mapper;

import api.debt.book.debt.dto.core.DebtResponseDTO;
import api.debt.book.debt.entity.DebtEntity;
import org.springframework.stereotype.Component;

@Component
public class DebtMapper {
    public DebtResponseDTO toResponseDTO(DebtEntity entity) {
        DebtResponseDTO dto = new DebtResponseDTO();
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
