package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.shopofly.shopofly.Model.Network.Customer;
import xyz.shopofly.shopofly.Model.Network.Order;
import xyz.shopofly.shopofly.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PayActivity extends AppCompatActivity {

    private static final String TAG = PayActivity.class.getName();
    @BindView(R.id.payofly_container)
    CardView payoflyAnimation;
    @BindView(R.id.payofly_title)
    TextView payoflyTitle;

    @BindView(R.id.animation_view2)
    ImageView halalahIcon;
    @BindView(R.id.halalah_title)
    TextView halalahTitle;

    @BindView(R.id.total)
    View vTotal;
    @BindView(R.id.vat)
    View vVat;
    @BindView(R.id.subtotal)
    View vSubtotal;

    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        order = (Order) getIntent().getSerializableExtra("order");
        if(order == null)
            finish();
        fillTotal("Subtotal",order.getTotal().getTotal(),vSubtotal);
        fillTotal("VAT",order.getTotal().getVat(),vVat);
        fillTotal("Total",order.getTotal().getTotalWithVat(),vTotal);
    }

    private void fillTotal(String type,double total, View vTotal) {
        TextView paymentType = vTotal.findViewById(R.id.title);
        TextView paymentPrice = vTotal.findViewById(R.id.subtitle);

        paymentType.setText(type);
        paymentPrice.setText(Double.toString(total));

    }

    @OnClick(R.id.payofly_container)
    public void gotoPayofly(){
        Log.d(TAG, "gotoPayofly: ");
        Intent intent = new Intent(this, PayoflyActivity.class);
        intent.putExtra("order", order);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Pair logo = Pair.create(payoflyAnimation, getString(R.string.payofly));
            Pair title = Pair.create(payoflyTitle, getString(R.string.payofly_title_transition));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this,title);
            startActivity(intent, options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }
    @OnClick(R.id.halalah_container)
    public void gotoHalalah(){
        Log.d(TAG, "gotoHalalah: ");
        Intent intent = new Intent(this, HalalahActivity.class);
        intent.putExtra("order", order);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) { //TODO:
            Pair logo = Pair.create(halalahIcon, getString(R.string.halalah_logo));
            Pair title = Pair.create(halalahTitle, getString(R.string.halalah_title));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this,logo, title);
            startActivity(intent, options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

    @OnClick(R.id.backImage)
    public void back(){
        finish();
    }
}
