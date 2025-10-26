package api.debt.book.creditor.service.core;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.creditor.dto.core.CreditorCreatedDTO;
import api.debt.book.creditor.dto.core.CreditorResponseDTO;
import api.debt.book.creditor.entity.CreditorEntity;
import api.debt.book.creditor.mapper.CreditorMapper;
import api.debt.book.creditor.service.CreditorService;
import api.debt.book.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class CreditorCoreService extends CreditorService {
    @Autowired
    private CreditorMapper creditorMapper;

    public CreditorResponseDTO create(CreditorCreatedDTO dto, AppLanguage lang) {
        CreditorEntity entity = creditorMapper.toCreatedEntity(dto);
        if (dto.getProfileId() == null) {
            entity.setProfileId(SpringSecurityUtil.getCurrentUserId());
        }
        return creditorMapper.toResponseDTO(save(entity));
    }

    public CreditorResponseDTO getById(String id, AppLanguage lang) {
        return creditorMapper.toResponseDTO(findById(id, lang));
    }

    public PageImpl<CreditorResponseDTO> getAll(int page, int size, AppLanguage lang) {
        return findAll(page, size, lang);
    }
}
