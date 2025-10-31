package api.debt.book.profile.controller.core;



import api.debt.book.app.enums.AppLanguage;
import api.debt.book.profile.dto.profile.ProfileDTO;
import api.debt.book.profile.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/profile")
@PreAuthorize("hasAnyRole('OWNER','ADMIN', 'USER')")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable String id,
                                              @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang) {
        return ResponseEntity.ok().body(profileService.getById(id, lang));
    }
    @GetMapping("/me")
    public ResponseEntity<ProfileDTO> getMe(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileService.getMe(lang));
    }


}
