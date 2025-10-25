package api.debt.book.profile.controller.profile;


import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.profile.dto.profile.*;
import api.debt.book.profile.service.profile.ProfileService;
import api.debt.book.security.dto.CodeConfirmDTO;
import api.debt.book.security.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")

public class AccauntController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("")
    public ResponseEntity<ProfileDTO> getMe(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileService.getMe(lang));
    }

    @PutMapping("/detail")
    public ResponseEntity<AppResponse<String>> update(@Valid @RequestBody ProfileDetailUpdateDTO dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileService.updateDetail(dto, lang));
    }

    @PutMapping("/photo")
    public ResponseEntity<AppResponse<String>> updatePhoto(@Valid @RequestBody ProfilePhotoUpdate dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileService.updatePhoto(dto.getPhotoId(), lang));
    }

    @PutMapping("/password")
    public ResponseEntity<AppResponse<String>> updatePassword(@Valid @RequestBody ProfilePasswordUpdate dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileService.updatePassword(dto, lang));
    }

    @PutMapping("/username")
    public ResponseEntity<AppResponse<String>> updateUsername(@Valid @RequestBody ProfileUsernameUpdateDTO dto,
                                                              @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileService.updateUsername(dto, lang));
    }

    @PutMapping("/username/confirm")
    public ResponseEntity<AppResponse<String>> updateUsernameConfirm(@Valid @RequestBody CodeConfirmDTO dto,
                                                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang){
        return ResponseEntity.ok().body(profileService.updateUsernameConfirm(dto, lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> delete(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLanguage lang ) {
        return ResponseEntity.ok().body(profileService.deletebyId(SpringSecurityUtil.getCurrentUserId(), lang));
    }
}
