package api.debt.book.credit.service.owner;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.dto.core.CreditCreatedDTO;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.mapper.CreditMapper;
import api.debt.book.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class CreditOwnerService extends CreditService {
    @Autowired
    private CreditMapper creditMapper;

    public CreditResponseDTO create(CreditCreatedDTO dto, AppLanguage lang) {
        return creditMapper.toResponseDTO(save(creditMapper.toCreatedEntity(dto), lang));
    }

    public CreditResponseDTO getById(String id, AppLanguage lang) {
        return creditMapper.toResponseDTO(findById(id, lang));
    }

    public Page<CreditResponseDTO> getAll(int page, int size, AppLanguage lang) {
        return findAll(page, size, lang);
    }
}
