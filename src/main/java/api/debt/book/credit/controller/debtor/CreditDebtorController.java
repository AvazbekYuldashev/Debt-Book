package api.debt.book.credit.controller.debtor;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.service.debtor.CreditDebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/debtor/credit")
@PreAuthorize("hasRole('DEBTOR')")
public class CreditDebtorController {
    @Autowired
    private CreditDebtorService creditDebtorService;


    @PatchMapping("/{id}")
    public ResponseEntity<AppResponse<String>> checkDebtor(@PathVariable String id,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditDebtorService.checkDebtor(id, lang));
    }

}
