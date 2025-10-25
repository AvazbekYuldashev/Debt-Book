package api.debt.book.debitBook.controller.core;

import api.debt.book.debitBook.service.core.DebtBookCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/debt-book")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class DebtBookCoreController {
    @Autowired
    private DebtBookCoreService debtBookCoreService;
}
