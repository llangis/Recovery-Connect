package nbcc.common.service;

public interface CurrentLoginService {
    boolean isLoggedIn();
    boolean isLoggedOut();
    String getCurrentUsername();
}
