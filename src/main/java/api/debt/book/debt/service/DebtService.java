package api.debt.book.debt.service;

import api.debt.book.debt.mapper.DebtMapper;
import api.debt.book.debt.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtService {
    @Autowired
    private DebtMapper debtMapper;
    @Autowired
    private DebtRepository debtRepository;
}
