package api.debt.book.credit.service.creditor;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.dto.core.CreditCreatedDTO;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.entity.CreditEntity;
import api.debt.book.credit.mapper.CreditMapper;
import api.debt.book.credit.service.CreditService;
import api.debt.book.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class CreditCreditorService extends CreditService {
    @Autowired
    private CreditMapper creditMapper;

    public CreditResponseDTO create(CreditCreatedDTO dto, AppLanguage lang) {
        CreditEntity entity = creditMapper.toCreatedEntity(dto);
        entity.setCreditorId(SpringSecurityUtil.getCurrentUserId());
        return creditMapper.toResponseDTO(save(entity));
    }

    public AppResponse<String> checkCreditor(String id, AppLanguage lang) {
        CreditEntity entity = findById(id, lang);
        if (!SpringSecurityUtil.getCurrentUserId().equals(entity.getCreditorId())){
            throw new AuthorizationDeniedException("asdasd");
        }
        return updateCreditorCheck(id,lang);
    }

    public AppResponse<String> deleteCreditor(String id, AppLanguage lang) {
        CreditEntity entity = findById(id, lang);
        if (!SpringSecurityUtil.getCurrentUserId().equals(entity.getCreditorId())){
            throw new AuthorizationDeniedException("asdasd");
        }
        if (entity.getDebtorCheck().equals(Boolean.TRUE)){
            throw new AuthorizationDeniedException("asdasd");
        }
        return deleteById(id, lang);
    }
}
