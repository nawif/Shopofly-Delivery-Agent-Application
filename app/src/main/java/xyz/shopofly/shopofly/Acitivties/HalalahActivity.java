package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.shopofly.shopofly.Model.Network.Order;
import xyz.shopofly.shopofly.R;
import xyz.shopofly.shopofly.Utils.Helpers;
import xyz.shopofly.shopofly.Utils.Injector;
import xyz.shopofly.shopofly.Utils.TokenNotFoundException;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.zxing.WriterException;

public class HalalahActivity extends AppCompatActivity {
    private static final String TAG = HalalahActivity.class.getSimpleName();
    Order order;

    @BindView(R.id.qr_code_anim)
    LottieAnimationView lottieAnimationView;

    @BindView(R.id.qr_code_image)
    ImageView qrCodeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halalah);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        order = (Order) getIntent().getSerializableExtra("order");
        try {
            qrCodeImg.setImageBitmap(Helpers.generateQRCode(order.getHalalahCode(), (int) getResources().getDimension(R.dimen.qr_code_width),(int) getResources().getDimension(R.dimen.qr_code_height)));
        } catch (WriterException e) {
            Log.e(TAG, "onCreate: WriterException", e);
        }
        handelLottieAnimationView();
    }

    private void handelLottieAnimationView() {
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieAnimationView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @OnClick(R.id.backImage)
    public void back(){
        finish();
    }

    @OnClick(R.id.check_payment)
    public void checkIfPaid(){
        try {
            Injector.provideOrderService().paymentStatus(Helpers.getToken(this),order.getOrderId()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d(TAG, "onResponse: " + response.code());
                    if(response.code() == 200){
                        showPaymentAnim(true);
                    }else
                        showPaymentAnim(false);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                }
            });
        } catch (TokenNotFoundException e) {
            Log.e(TAG, "checkIfPaid: ", e);
        }
    }

    private void showPaymentAnim(boolean status) {
        if(status)
            lottieAnimationView.setAnimation("check.json");
        else
            lottieAnimationView.setAnimation("error.json");
        handelLottieAnimationView();
    }
}
