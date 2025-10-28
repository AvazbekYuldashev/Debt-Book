package api.debt.book.debt.controller.creditor;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.debt.service.creditor.DebtCreditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditor/debt")
@PreAuthorize("hasRole('CREDITOR')")
public class DebtCreditorController {
    @Autowired
    private DebtCreditorService debtCreditorService;

    @PatchMapping("/{id}")
    public ResponseEntity<AppResponse<String>> checkCreditor(@PathVariable String id,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtCreditorService.checkCreditor(id, lang));
    }
}
