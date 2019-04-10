package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.shopofly.shopofly.Model.Network.Login;
import xyz.shopofly.shopofly.Model.Network.User;
import xyz.shopofly.shopofly.R;
import xyz.shopofly.shopofly.Utils.Helpers;
import xyz.shopofly.shopofly.Utils.Injector;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final boolean DEBUG_MOOD = false;
    @BindView(R.id.sign_in_button)
    Button loginBtn;

    @BindView(R.id.phone)
    EditText phoneInput;

    @BindView(R.id.password)
    EditText passwordInput;
    @BindView(R.id.login_progress)
    ProgressBar loginProgress;

    @BindView(R.id.animation_view)
    LottieAnimationView networkErrorAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        phoneInput.setText("0504244460");
        passwordInput.setText("12345678");


        loginBtn.setOnClickListener(view -> attemptLogin());

    }

    private void attemptLogin() {
        if(DEBUG_MOOD)
            startActivity(new Intent(MainActivity.this, OrdersList.class));
        String phone = phoneInput.getText().toString();
        if(phone.isEmpty()){
            phoneInput.setError("Phone number is required!");
            return;
        }
        String password = passwordInput.getText().toString();
        if(password.isEmpty()){
            passwordInput.setError("Password is Required");
            return;
        }
        loginBtn.setVisibility(View.GONE);
        Call<User> userAttempt = Injector.provideUserService().login(new Login(phone, password));
        loginProgress.setVisibility(View.VISIBLE);
        userAttempt.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                switch (response.code()){
                    case 200:
                        startActivity(new Intent(MainActivity.this, OrdersList.class));
                        break;
                    case 401:
                        Toast.makeText(MainActivity.this, "Wrong Phone number or Password", Toast.LENGTH_SHORT).show();
                        break;
                }
                loginProgress.setVisibility(View.INVISIBLE);
                loginBtn.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showNetworkError();
            }
        });
    }

    private void showNetworkError() {
        loginProgress.setVisibility(View.INVISIBLE);
        loginBtn.setVisibility(View.VISIBLE);
        networkErrorAnim.setVisibility(View.VISIBLE);
        networkErrorAnim.playAnimation();
        networkErrorAnim.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                networkErrorAnim.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


}
