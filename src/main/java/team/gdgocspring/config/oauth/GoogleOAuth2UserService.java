package team.gdgocspring.config.oauth;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import team.gdgocspring.service.UserService;

@Service
public class GoogleOAuth2UserService extends OidcUserService {
    private final UserService users;

    public GoogleOAuth2UserService(UserService users) {
        this.users = users;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest request) {
        OidcUser account = super.loadUser(request);
        users.saveGoogleUser(account);
        return account;
    }
}
