package api.debt.book.debt.controller.core;


import api.debt.book.app.enums.AppLanguage;
import api.debt.book.debt.dto.core.DebtCreatedDTO;
import api.debt.book.debt.dto.core.DebtResponseDTO;
import api.debt.book.debt.service.core.DebtCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/debt")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
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
    public ResponseEntity<PageImpl<DebtResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "15") int size,
                                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(debtCoreService.getAll(getCurrentPage(page), size, lang));
    }



    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
