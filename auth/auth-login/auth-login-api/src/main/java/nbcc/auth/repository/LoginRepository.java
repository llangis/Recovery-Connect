package nbcc.auth.repository;

import nbcc.auth.domain.BearerToken;
import nbcc.auth.domain.LoginDetails;

public interface LoginRepository {

    BearerToken get(String token);

    BearerToken create(LoginDetails loginDetails);

    boolean isValid(String username, String password);

    boolean logout(String token);
}
