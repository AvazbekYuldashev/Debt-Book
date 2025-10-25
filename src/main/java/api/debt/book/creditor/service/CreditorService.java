package api.debt.book.creditor.service;

import api.debt.book.creditor.mapper.CreditorMapper;
import api.debt.book.creditor.repository.CreditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditorService {
    @Autowired
    private CreditorMapper creditorMapper;
    @Autowired
    private CreditorRepository creditorRepository;
}
