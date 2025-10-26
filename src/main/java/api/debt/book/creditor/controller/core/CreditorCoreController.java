package api.debt.book.creditor.controller.core;


import api.debt.book.app.enums.AppLanguage;
import api.debt.book.creditor.dto.core.CreditorCreatedDTO;
import api.debt.book.creditor.dto.core.CreditorResponseDTO;
import api.debt.book.creditor.service.core.CreditorCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/creditor")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'MANAGER', 'EMPLOYEE', 'USER')")
public class CreditorCoreController {
    @Autowired
    private CreditorCoreService creditorCoreService;


    @PostMapping("")
    public ResponseEntity<CreditorResponseDTO> create(@RequestBody CreditorCreatedDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditorCoreService.create(dto, lang));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditorResponseDTO> findById(@PathVariable String id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditorCoreService.getById(id, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<CreditorResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "size", defaultValue = "15") int size,
                                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(creditorCoreService.getAll(getCurrentPage(page), size, lang));
    }



    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }


}
