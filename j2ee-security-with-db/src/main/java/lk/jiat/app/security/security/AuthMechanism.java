package lk.jiat.app.security.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashSet;
import java.util.Set;

@AutoApplySession
@ApplicationScoped
public class AuthMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStore identityStore;

    private static final Set<String> WHITE_LIST=  Set.of(
            "/login",
            "/register",
            "/auth/login",
            "/auth/register",
            "/public"
    );

    private boolean isWhitelisted(String path){
        return WHITE_LIST.stream().anyMatch(path::startsWith);
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) throws AuthenticationException {


        String path = request.getRequestURI();
        System.out.println("path :"+path);
        if (isWhitelisted(path) ) {
            return context.doNothing(); // no authentication needed
        }
        System.out.println("AuthParameters ;"+context.getAuthParameters());



        return context.responseUnauthorized();
    }
}
