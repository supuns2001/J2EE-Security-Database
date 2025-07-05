package lk.jiat.app.security.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import lk.jiat.app.security.service.LoginService;
import lk.jiat.app.security.servlet.Login;

@ApplicationScoped
public class AppIdentityStore implements IdentityStore {

    @Inject
    private LoginService loginService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        return IdentityStore.super.validate(credential);
    }
}
