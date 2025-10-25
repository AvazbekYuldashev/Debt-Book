package api.debt.book.creditor.service.core;

import api.debt.book.creditor.mapper.CreditorMapper;
import api.debt.book.creditor.service.CreditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditorCoreService extends CreditorService {
    @Autowired
    private CreditorMapper creditorMapper;
}
