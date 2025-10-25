package api.debt.book.credit.service;

import api.debt.book.credit.mapper.CreditMapper;
import api.debt.book.credit.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    @Autowired
    private CreditMapper creditMapper;
    @Autowired
    private CreditRepository creditRepository;
}
