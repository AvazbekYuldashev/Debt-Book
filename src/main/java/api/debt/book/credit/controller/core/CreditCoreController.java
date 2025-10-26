package api.debt.book.credit.controller.core;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.dto.core.CreditCreatedDTO;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.service.core.CreditCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/credit")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class CreditCoreController {
    @Autowired
    private CreditCoreService creditCoreService;


    @PostMapping("")
    public ResponseEntity<CreditResponseDTO> create(@RequestBody CreditCreatedDTO dto,
                                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditCoreService.create(dto, lang));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditResponseDTO> findById(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditCoreService.getById(id, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<CreditResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "15") int size,
                                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(creditCoreService.getAll(getCurrentPage(page), size, lang));
    }



    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }

}
