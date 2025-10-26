package api.debt.book.debtBook.mapper;

import api.debt.book.debtBook.dto.DebtBookCreatedDTO;
import api.debt.book.debtBook.dto.DebtBookResponseDTO;
import api.debt.book.debtBook.entity.DebtBookEntity;
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

    public DebtBookEntity toCreatedEntity(DebtBookCreatedDTO dto) {
        DebtBookEntity entity = new DebtBookEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreditorId(dto.getCreditorId());
        entity.setDebtorId(dto.getDebtorId());
        System.out.println("dto creditorid: " + dto.getCreditorId());
        System.out.println("entity creditorid: " + entity.getCreditorId());
        System.out.println("dto debitor: " + dto.getDebtorId());
        System.out.println("entity debitor: " + entity.getDebtorId());
        return entity;
    }
}
