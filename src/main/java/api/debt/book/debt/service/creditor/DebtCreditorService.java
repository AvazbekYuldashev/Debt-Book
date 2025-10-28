package api.debt.book.debt.service.creditor;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.entity.CreditEntity;
import api.debt.book.debt.entity.DebtEntity;
import api.debt.book.debt.service.DebtService;
import api.debt.book.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class DebtCreditorService extends DebtService {



    public AppResponse<String> checkCreditor(String id, AppLanguage lang) {
        DebtEntity entity = findById(id, lang);
        if (!SpringSecurityUtil.getCurrentUserId().equals(entity.getCreditorId())){
            throw new AuthorizationDeniedException("asdasd");
        }
        return updateCreditorCheck(id,lang);
    }
}
