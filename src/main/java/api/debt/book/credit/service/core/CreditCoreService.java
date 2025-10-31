package api.debt.book.credit.service.core;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.dto.core.CreditCreatedDTO;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.entity.CreditEntity;
import api.debt.book.credit.mapper.CreditMapper;
import api.debt.book.credit.service.CreditService;
import api.debt.book.debt.dto.core.DebtCreatedDTO;
import api.debt.book.debt.dto.core.DebtResponseDTO;
import api.debt.book.debt.entity.DebtEntity;
import api.debt.book.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditCoreService extends CreditService {
    @Autowired
    private CreditMapper creditMapper;

    public CreditResponseDTO create(CreditCreatedDTO dto, AppLanguage lang) {
        CreditEntity entity = creditMapper.toCreatedEntity(dto);
        entity.setCreditorId(SpringSecurityUtil.getCurrentUserId());
        entity.setCreditorCheck(true);
        return creditMapper.toResponseDTO(save(entity, lang));
    }

    public CreditResponseDTO getById(String id, AppLanguage lang) {
        return creditMapper.toResponseDTO(findById(id, lang));
    }

    public Page<CreditResponseDTO> getAll(int page, int size, AppLanguage lang) {
        return findAll(page, size, lang);
    }

    //

    public AppResponse<String> checkCreditor(String id, AppLanguage lang) {
        CreditEntity entity = findById(id, lang);
        if (entity.getCreditorCheck().equals(true)) {
            throw new AuthorizationDeniedException("asdasd");
        }
        if (!entity.getCreditorId().equals(SpringSecurityUtil.getCurrentUserId())){
            throw new AuthorizationDeniedException("asdasd");

        }
        return updateCreditorCheck(id, lang);
    }

    public AppResponse<String> checkDebtor(String id, AppLanguage lang) {
        CreditEntity entity = findById(id, lang);
        if (entity.getDebtorCheck().equals(true)) {
            throw new AuthorizationDeniedException("asdasd");
        }
        if (!entity.getDebtorId().equals(SpringSecurityUtil.getCurrentUserId())){
            throw new AuthorizationDeniedException("asdasd");
        }
        return updateDebtorCheck(id, lang);
    }


    public Page<CreditResponseDTO> getAllByCreditorId(String id, int page, int size, AppLanguage lang) {
        return findAllByCreditorId(id, page, size, lang);
    }

    public Page<CreditResponseDTO> getAllByDebtorId(String id, int page, int size, AppLanguage lang) {
        return findAllByDebtorId(id, page, size, lang);
    }

    public AppResponse<String> deleteSoft(String id, AppLanguage lang) {
        CreditEntity entity = findById(id, lang);
        if (!entity.getCreditorId().equals(SpringSecurityUtil.getCurrentUserId())) {
            throw new AuthorizationDeniedException("asdasd");
        }
        if (entity.getDebtorCheck().equals(true)) {
            throw new AuthorizationDeniedException("asdasd");
        }
        return deleteById(id, lang);
    }

    public AppResponse<BigDecimal> getAllTootalPrice(AppLanguage lang) {
        return new AppResponse<>(getTootalPrice(lang));
    }

}
