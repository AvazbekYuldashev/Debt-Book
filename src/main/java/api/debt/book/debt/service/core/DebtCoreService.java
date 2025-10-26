package api.debt.book.debt.service.core;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.debt.dto.core.DebtCreatedDTO;
import api.debt.book.debt.dto.core.DebtResponseDTO;
import api.debt.book.debt.mapper.DebtMapper;
import api.debt.book.debt.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class DebtCoreService extends DebtService {
    @Autowired
    private DebtMapper debtMapper;

    public DebtResponseDTO create(DebtCreatedDTO dto, AppLanguage lang) {
        return debtMapper.toResponseDTO(save(debtMapper.toCreatedEntity(dto)));
    }

    public DebtResponseDTO getById(String id, AppLanguage lang) {
        return debtMapper.toResponseDTO(findById(id, lang));
    }

    public PageImpl<DebtResponseDTO> getAll(int page, int size, AppLanguage lang) {
        return findAll(page, size, lang);
    }
}
