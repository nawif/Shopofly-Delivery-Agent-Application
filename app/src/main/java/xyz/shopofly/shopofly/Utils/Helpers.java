package xyz.shopofly.shopofly.Utils;

import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import java.io.IOException;

public class Helpers {

    public static boolean isInternetAvailable() {
        final String command = "ping -c 1 google.com";
        try {
            return Runtime.getRuntime().exec(command).waitFor() == 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void showLoadingProgress(boolean isVisible, LottieAnimationView loadingAnimation){
        if(isVisible){
            loadingAnimation.setVisibility(View.VISIBLE);
            loadingAnimation.playAnimation();
        }else{
            loadingAnimation.setVisibility(View.INVISIBLE);
            loadingAnimation.cancelAnimation();
        }
    }
}
