package api.debt.book.credit.controller.owner;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.dto.core.CreditCreatedDTO;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.service.owner.CreditOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/credit")
@PreAuthorize("hasRole('OWNER')")
public class CreditOwnerController {
    @Autowired
    private CreditOwnerService creditOwnerService;

    @PostMapping("")
    public ResponseEntity<CreditResponseDTO> create(@RequestBody CreditCreatedDTO dto,
                                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditOwnerService.create(dto, lang));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditResponseDTO> findById(@PathVariable String id,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(creditOwnerService.getById(id, lang));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CreditResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "size", defaultValue = "15") int size,
                                                           @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(creditOwnerService.getAll(getCurrentPage(page), size, lang));
    }



    public static int getCurrentPage(Integer page) {
        return page > 0 ? page - 1 : 1;
    }


}
