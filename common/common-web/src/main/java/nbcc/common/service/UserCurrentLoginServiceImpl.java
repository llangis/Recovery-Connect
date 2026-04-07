package nbcc.common.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserCurrentLoginServiceImpl implements CurrentLoginService {
    @Override public boolean isLoggedIn() { var auth = SecurityContextHolder.getContext().getAuthentication(); return auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()); }
    @Override public boolean isLoggedOut() { return !isLoggedIn(); }
    @Override public String getCurrentUsername() { var auth = SecurityContextHolder.getContext().getAuthentication(); return isLoggedIn() ? auth.getName() : null; }
}