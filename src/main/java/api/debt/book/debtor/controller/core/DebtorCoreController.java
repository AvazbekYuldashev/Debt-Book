package api.debt.book.debtor.controller.core;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.debtor.dto.core.DebtorCreatedDTO;
import api.debt.book.debtor.dto.core.DebtorResponseDTO;
import api.debt.book.debtor.service.core.DebtorCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/debtor")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class DebtorCoreController {
    @Autowired
    private DebtorCoreService debtorCoreService;


    @PostMapping("")
    public ResponseEntity<DebtorResponseDTO> create(@RequestBody DebtorCreatedDTO dto,
                                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtorCoreService.create(dto, lang));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebtorResponseDTO> findById(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(debtorCoreService.getById(id, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<DebtorResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "15") int size,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtorCoreService.getAll(getCurrentPage(page), size, lang));
    }



    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
