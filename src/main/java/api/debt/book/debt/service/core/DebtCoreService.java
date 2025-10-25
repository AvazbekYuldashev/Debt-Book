package api.debt.book.debt.service.core;

import api.debt.book.debt.mapper.DebtMapper;
import api.debt.book.debt.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtCoreService extends DebtService {
    @Autowired
    private DebtMapper debtMapper;
}
