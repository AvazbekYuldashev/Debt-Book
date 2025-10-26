package api.debt.book.debtor.service.core;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.debtor.dto.core.DebtorCreatedDTO;
import api.debt.book.debtor.dto.core.DebtorResponseDTO;
import api.debt.book.debtor.entity.DebtorEntity;
import api.debt.book.debtor.mapper.DebtorMapper;
import api.debt.book.debtor.service.DebtorService;
import api.debt.book.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class DebtorCoreService extends DebtorService {
    @Autowired
    private DebtorMapper debtorMapper;

    public DebtorResponseDTO create(DebtorCreatedDTO dto, AppLanguage lang) {
        DebtorEntity entity = debtorMapper.toCreatedEntity(dto);
        entity.setProfileId(SpringSecurityUtil.getCurrentUserId());
        return debtorMapper.toResponseDTO(save(entity));
    }

    public DebtorResponseDTO getById(String id, AppLanguage lang) {
        return debtorMapper.toResponseDTO(findById(id, lang));
    }

    public PageImpl<DebtorResponseDTO> getAll(int page, int size, AppLanguage lang) {
        return findAll(page, size, lang);
    }
}
