package api.debt.book.credit.controller.creditor;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.dto.core.CreditCreatedDTO;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.entity.CreditEntity;
import api.debt.book.credit.service.creditor.CreditCreditorService;
import api.debt.book.security.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditor/credit")
@PreAuthorize("hasRole('CREDITOR')")
public class CreditCreditorController {
    @Autowired
    private CreditCreditorService creditCreditorService;

    @PostMapping("")
    public ResponseEntity<CreditResponseDTO> create(@RequestBody CreditCreatedDTO dto,
                                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditCreditorService.create(dto, lang));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppResponse<String>> checkDebtor(@PathVariable String id,
                                                           @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditCreditorService.checkCreditor(id, lang));
    }


}
