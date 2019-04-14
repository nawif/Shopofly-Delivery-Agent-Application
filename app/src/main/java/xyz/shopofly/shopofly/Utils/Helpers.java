package xyz.shopofly.shopofly.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.util.Hashtable;

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

    public static void deleteToken(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.remove(Constants.PREFS_TOKEN_NAME);
        editor.apply();
    }

    public static Bitmap generateQRCode(String text, int width, int height) throws WriterException, NullPointerException {
        BitMatrix bitMatrix;
        try {
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,
                    width, height, hints);
        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        int colorWhite = 0xFFFFFFFF;
        int colorBlack = 0xFF000000;

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? colorBlack : colorWhite;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, width, 0, 0, bitMatrixWidth, bitMatrixHeight);


        return bitmap;
    }

}
