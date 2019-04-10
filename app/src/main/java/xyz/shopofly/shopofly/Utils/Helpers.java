package xyz.shopofly.shopofly.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

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

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static String getToken(Context context) throws TokenNotFoundException {

        String token =getPrefs(context).getString(Constants.PREFS_TOKEN_NAME,null);

        if(token == null)
            throw new TokenNotFoundException("token not found");
        return token;
    }

    public static void storeToken(String token, Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(Constants.PREFS_TOKEN_NAME, token);
        editor.apply();
    }

}
