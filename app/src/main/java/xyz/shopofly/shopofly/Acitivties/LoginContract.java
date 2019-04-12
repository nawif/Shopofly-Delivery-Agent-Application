package xyz.shopofly.shopofly.Acitivties;

import com.airbnb.lottie.LottieAnimationView;

public interface LoginContract {
    void attemptLogin();
    void showErrorAnimation(LottieAnimationView errorAnim);
    void checkIfAlreadyLoggedIn();

}
