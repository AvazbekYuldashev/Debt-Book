package api.debt.book.credit.controller.core;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.service.core.CreditCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/core/credit")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'USER')")
public class CreditCoreController {
    @Autowired
    private CreditCoreService creditCoreService;

    @GetMapping("/{id}")
    public ResponseEntity<CreditResponseDTO> findById(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditCoreService.getById(id, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CreditResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "15") int size,
                                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(creditCoreService.getAll(getCurrentPage(page), size, lang));
    }
    @PatchMapping("/creditor/{id}")
    public ResponseEntity<AppResponse<String>> checkCreditor(@PathVariable String id,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditCoreService.checkCreditor(id, lang));
    }

    @PatchMapping("/debtor/{id}")
    public ResponseEntity<AppResponse<String>> checkDebtor(@PathVariable String id,
                                                           @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditCoreService.checkDebtor(id, lang));
    }

    @GetMapping("/creditor/{id}")
    public ResponseEntity<Page<CreditResponseDTO>> getAllByCreditorId(@PathVariable String id,
                                                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                                                      @RequestParam(value = "size", defaultValue = "15") int size,
                                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(creditCoreService.getAllByCreditorId(id, getCurrentPage(page), size, lang));
    }

    @GetMapping("/debtor/{id}")
    public ResponseEntity<Page<CreditResponseDTO>> getAllByDebtorId(@PathVariable String id,
                                                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                                                    @RequestParam(value = "size", defaultValue = "15") int size,
                                                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(creditCoreService.getAllByDebtorId(id, getCurrentPage(page), size, lang));
    }

    @GetMapping("/tootal-price")
    public ResponseEntity<AppResponse<BigDecimal>> getAllTootalPrice(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(creditCoreService.getAllTootalPrice(lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteById(@PathVariable("id") String id,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(creditCoreService.deleteSoft(id, lang));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
