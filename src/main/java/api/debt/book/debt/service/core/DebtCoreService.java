package api.debt.book.debt.service.core;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.debt.dto.core.DebtCreatedDTO;
import api.debt.book.debt.dto.core.DebtResponseDTO;
import api.debt.book.debt.entity.DebtEntity;
import api.debt.book.debt.mapper.DebtMapper;
import api.debt.book.debt.service.DebtService;
import api.debt.book.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DebtCoreService extends DebtService {
    @Autowired
    private DebtMapper debtMapper;

    public DebtResponseDTO create(DebtCreatedDTO dto, AppLanguage lang) {
        DebtEntity entity = debtMapper.toCreatedEntity(dto);
        entity.setDebtorId(SpringSecurityUtil.getCurrentUserId());
        entity.setDebtorCheck(true);
        return debtMapper.toResponseDTO(save(entity, lang));
    }

    public DebtResponseDTO getById(String id, AppLanguage lang) {
        return debtMapper.toResponseDTO(findById(id, lang));
    }

    public Page<DebtResponseDTO> getAll(int page, int size, AppLanguage lang) {
        return findAll(page, size, lang);
    }

    public AppResponse<String> checkCreditor(String id, AppLanguage lang) {
        DebtEntity entity = findById(id, lang);
        if (entity.getCreditorCheck().equals(true)) {
            throw new AuthorizationDeniedException("asdasd");
        }
        if (!entity.getCreditorId().equals(SpringSecurityUtil.getCurrentUserId())){
            throw new AuthorizationDeniedException("asdasd");
        }
        return updateCreditorCheck(id, lang);
    }

    public AppResponse<String> checkDebtor(String id, AppLanguage lang) {
        DebtEntity entity = findById(id, lang);
        if (entity.getDebtorCheck().equals(true)) {
            throw new AuthorizationDeniedException("asdasd");
        }
        if (!entity.getDebtorId().equals(SpringSecurityUtil.getCurrentUserId())){
            throw new AuthorizationDeniedException("asdasd");
        }
        return updateDebtorCheck(id, lang);
    }

    public Page<DebtResponseDTO> getAllByCreditorId(String id, int page, int size, AppLanguage lang) {
        return findAllByCreditorId(id, page, size, lang);
    }

    public Page<DebtResponseDTO> getAllByDebtorId(String id, int page, int size, AppLanguage lang) {
        return findAllByDebtorId(id, page, size, lang);
    }

    public AppResponse<String> deleteSoft(String id, AppLanguage lang) {
        DebtEntity entity = findById(id, lang);
        if (entity.getCreditorCheck().equals(true)) {
            throw new AuthorizationDeniedException("asdasd");
        }
        if (!entity.getDebtorId().equals(SpringSecurityUtil.getCurrentUserId())) {
            throw new AuthorizationDeniedException("asdasd");
        }
        return deleteById(id, lang);
    }

    public AppResponse<BigDecimal> getAllTootalPrice(AppLanguage lang) {
        return new AppResponse<>(getTootalPrice(lang));
    }
}
