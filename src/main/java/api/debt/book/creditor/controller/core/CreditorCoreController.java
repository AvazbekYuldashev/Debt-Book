package api.debt.book.creditor.controller.core;


import api.debt.book.creditor.service.core.CreditorCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core/creditor")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class CreditorCoreController {
    @Autowired
    private CreditorCoreService creditorCoreService;

}
