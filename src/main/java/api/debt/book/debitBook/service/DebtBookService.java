package api.debt.book.debitBook.service;

import api.debt.book.debitBook.mapper.DebtBookMapper;
import api.debt.book.debitBook.repository.DebtBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtBookService {
    @Autowired
    private DebtBookMapper debtBookMapper;
    @Autowired
    private DebtBookRepository debtBookRepository;


}
