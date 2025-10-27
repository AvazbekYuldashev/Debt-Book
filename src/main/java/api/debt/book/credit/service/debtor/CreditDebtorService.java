package api.debt.book.credit.service.debtor;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.entity.CreditEntity;
import api.debt.book.credit.service.CreditService;
import api.debt.book.security.util.SpringSecurityUtil;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

@Service
public class CreditDebtorService extends CreditService {

    public AppResponse<String> checkDebtor(String id, AppLanguage lang) {
        CreditEntity entity = findById(id, lang);
        if (!SpringSecurityUtil.getCurrentUserId().equals(entity.getDebtorId())){
            throw new  AuthorizationDeniedException("asdasd");
        }
        return updateDebtorCheck(entity.getId(), lang);

    }
}
