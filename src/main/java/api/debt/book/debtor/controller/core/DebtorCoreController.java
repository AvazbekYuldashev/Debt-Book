package api.debt.book.debtor.controller.core;

import api.debt.book.debtor.service.core.DebtorCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/debtor")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class DebtorCoreController {
    @Autowired
    private DebtorCoreService debtorCoreService;
}
