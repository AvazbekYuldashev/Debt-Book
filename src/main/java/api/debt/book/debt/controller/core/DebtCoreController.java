package api.debt.book.debt.controller.core;


import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.debt.dto.core.DebtCreatedDTO;
import api.debt.book.debt.dto.core.DebtResponseDTO;
import api.debt.book.debt.service.core.DebtCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/core/debt")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'USER')")
public class DebtCoreController {
    @Autowired
    private DebtCoreService debtCoreService;

    @PostMapping("")
    public ResponseEntity<DebtResponseDTO> create(@RequestBody DebtCreatedDTO dto,
                                                  @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtCoreService.create(dto, lang));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebtResponseDTO> findById(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtCoreService.getById(id, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DebtResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "15") int size,
                                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtCoreService.getAll(getCurrentPage(page), size, lang));
    }

    @PatchMapping("/creditor/{id}")
    public ResponseEntity<AppResponse<String>> checkCreditor(@PathVariable String id,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtCoreService.checkCreditor(id, lang));
    }

    @PatchMapping("/debtor/{id}")
    public ResponseEntity<AppResponse<String>> checkDebtor(@PathVariable String id,
                                                           @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtCoreService.checkDebtor(id, lang));
    }

    @GetMapping("/creditor/{id}")
    public ResponseEntity<Page<DebtResponseDTO>> getAllByCreditorId(@PathVariable String id,
                                                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                                                      @RequestParam(value = "size", defaultValue = "15") int size,
                                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtCoreService.getAllByCreditorId(id, getCurrentPage(page), size, lang));
    }

    @GetMapping("/debtor/{id}")
    public ResponseEntity<Page<DebtResponseDTO>> getAllByDebtorId(@PathVariable String id,
                                                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                                                    @RequestParam(value = "size", defaultValue = "15") int size,
                                                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtCoreService.getAllByDebtorId(id, getCurrentPage(page), size, lang));
    }

    @GetMapping("/tootal-price")
    public ResponseEntity<AppResponse<BigDecimal>> getAllTootalPrice(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtCoreService.getAllTootalPrice(lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteById(@PathVariable("id") String id,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtCoreService.deleteSoft(id, lang));
    }



    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
