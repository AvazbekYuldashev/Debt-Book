package api.debt.book.debtBook.controller.core;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.debtBook.dto.DebtBookCalculatorDTO;
import api.debt.book.debtBook.dto.DebtBookCreatedDTO;
import api.debt.book.debtBook.dto.DebtBookResponseDTO;
import api.debt.book.debtBook.service.core.DebtBookCoreService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<PageImpl<DebtBookResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "15") int size,
                                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtBookCoreService.getAll(getCurrentPage(page), size, lang));
    }

    @GetMapping("/calculator/{id}")
    public ResponseEntity<DebtBookCalculatorDTO> calculator(@PathVariable String id,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtBookCoreService.calculator(id, lang));
    }



    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
