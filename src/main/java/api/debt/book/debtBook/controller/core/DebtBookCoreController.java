package api.debt.book.debtBook.controller.core;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.debtBook.dto.DebtBookCalculatorDTO;
import api.debt.book.debtBook.dto.DebtBookCreatedDTO;
import api.debt.book.debtBook.dto.DebtBookResponseDTO;
import api.debt.book.debtBook.service.core.DebtBookCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/debt-book")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'USER')")
public class DebtBookCoreController {
    @Autowired
    private DebtBookCoreService debtBookCoreService;

    @PostMapping("")
    public ResponseEntity<DebtBookResponseDTO> create(@RequestBody DebtBookCreatedDTO dto,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtBookCoreService.create(dto, lang));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebtBookResponseDTO> findById(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtBookCoreService.getById(id, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DebtBookResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "15") int size,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtBookCoreService.getAll(getCurrentPage(page), size, lang));
    }

    @GetMapping("/calculator/{id}")
    public ResponseEntity<DebtBookCalculatorDTO> calculator(@PathVariable("id") String id,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtBookCoreService.calculator(id, lang));
    }

    @GetMapping("/creditor/{id}")
    public ResponseEntity<Page<DebtBookResponseDTO>> findAllByCreditorId(@PathVariable("id") String id,
                                                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                                                         @RequestParam(value = "size", defaultValue = "15") int size,
                                                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtBookCoreService.findAllByCreditorId(id, getCurrentPage(page), size, lang));
    }

    @GetMapping("/debtor/{id}")
    public ResponseEntity<Page<DebtBookResponseDTO>> findAllByDebtorId(@PathVariable("id") String id,
                                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                                       @RequestParam(value = "size", defaultValue = "15") int size,
                                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtBookCoreService.findAllByDebtorId(id, getCurrentPage(page), size, lang));
    }

    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
