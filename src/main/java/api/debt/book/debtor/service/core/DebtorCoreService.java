package api.debt.book.debtor.service.core;

import api.debt.book.debtor.mapper.DebtorMapper;
import api.debt.book.debtor.service.DebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtorCoreService extends DebtorService {
    @Autowired
    private DebtorMapper debtorMapper;
}
