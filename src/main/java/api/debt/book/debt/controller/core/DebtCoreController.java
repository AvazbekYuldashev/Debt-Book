package api.debt.book.debt.controller.core;


import api.debt.book.debt.service.core.DebtCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/debt")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class DebtCoreController {
    @Autowired
    private DebtCoreService debtCoreService;
}
