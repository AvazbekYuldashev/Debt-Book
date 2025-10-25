package api.debt.book.debtor.service;

import api.debt.book.debtor.mapper.DebtorMapper;
import api.debt.book.debtor.repository.DebtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtorService {
    @Autowired
    private DebtorMapper debtorMapper;
    @Autowired
    private DebtorRepository debtorRepository;

}
