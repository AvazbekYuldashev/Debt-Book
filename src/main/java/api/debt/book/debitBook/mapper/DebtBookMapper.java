package api.debt.book.debitBook.mapper;

import api.debt.book.debitBook.dto.DebtBookResponseDTO;
import api.debt.book.debitBook.entity.DebtBookEntity;
import org.springframework.stereotype.Component;

@Component
public class DebtBookMapper {

    public DebtBookResponseDTO toResponseDTO(DebtBookEntity entity) {
        DebtBookResponseDTO dto = new DebtBookResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        dto.setAmount(entity.getAmount());
        dto.setDebtorId(entity.getDebtorId());
        dto.setCreditorId(entity.getCreditorId());
        return dto;
    }

}
