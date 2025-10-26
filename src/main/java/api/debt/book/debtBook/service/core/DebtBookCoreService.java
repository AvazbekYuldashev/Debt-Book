package api.debt.book.debtBook.service.core;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.debtBook.dto.DebtBookCreatedDTO;
import api.debt.book.debtBook.dto.DebtBookResponseDTO;
import api.debt.book.debtBook.entity.DebtBookEntity;
import api.debt.book.debtBook.mapper.DebtBookMapper;
import api.debt.book.debtBook.service.DebtBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class DebtBookCoreService extends DebtBookService {
    @Autowired
    private DebtBookMapper debtBookMapper;

    public DebtBookResponseDTO create(DebtBookCreatedDTO dto, AppLanguage lang) {
        DebtBookEntity entity = debtBookMapper.toCreatedEntity(dto);
        return debtBookMapper.toResponseDTO(save(entity));

    }

    public DebtBookResponseDTO getById(String id, AppLanguage lang) {
        return debtBookMapper.toResponseDTO(findById(id, lang));
    }

    public PageImpl<DebtBookResponseDTO> getAll(int page, int size, AppLanguage lang) {
        return findAll(page, size, lang);

    }
}
