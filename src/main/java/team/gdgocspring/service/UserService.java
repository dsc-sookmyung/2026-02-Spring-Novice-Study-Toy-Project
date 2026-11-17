package team.gdgocspring.service;

import team.gdgocspring.domain.User;
import team.gdgocspring.repository.UserRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    @Transactional
    public void saveGoogleUser(OidcUser account) {
        User user = users.findById(account.getSubject())
                .orElseGet(() -> new User(account.getSubject()));
        user.updateProfile(account.getEmail(), account.getFullName());
        users.save(user);
    }
}
