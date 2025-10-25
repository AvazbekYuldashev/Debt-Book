package api.debt.book.credit.service.core;

import api.debt.book.credit.mapper.CreditMapper;
import api.debt.book.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCoreService extends CreditService {
    @Autowired
    private CreditMapper creditMapper;
}
