package nbcc.auth.service;

import nbcc.auth.services.TokenRetrievalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TokenRetrievalServiceImpl implements TokenRetrievalService {

    @Override
    public String getToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() != null) {
            return authentication.getCredentials().toString();
        }
        return null;
    }
}