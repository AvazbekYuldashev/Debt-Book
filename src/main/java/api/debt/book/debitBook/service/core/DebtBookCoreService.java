package api.debt.book.debitBook.service.core;

import api.debt.book.debitBook.mapper.DebtBookMapper;
import api.debt.book.debitBook.service.DebtBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtBookCoreService extends DebtBookService {
    @Autowired
    private DebtBookMapper debtBookMapper;
}
