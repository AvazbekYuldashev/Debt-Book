package api.debt.book.security.service;



import api.debt.book.profile.entity.ProfileEntity;
import api.debt.book.profile.repository.ProfileRepository;
import api.debt.book.security.config.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername: " + username);
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        ProfileEntity profile = optional.get();
        return new CustomUserDetails(profile);
    }
}