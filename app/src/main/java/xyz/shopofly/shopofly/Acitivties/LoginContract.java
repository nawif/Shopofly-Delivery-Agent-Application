package xyz.shopofly.shopofly.Acitivties;

public interface LoginContract {
    void attemptLogin();
    void showNetworkError();
    void checkIfAlreadyLoggedIn();

}
