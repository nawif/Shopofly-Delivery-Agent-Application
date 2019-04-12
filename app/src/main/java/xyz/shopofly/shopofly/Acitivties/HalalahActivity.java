package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.shopofly.shopofly.Model.Network.Order;
import xyz.shopofly.shopofly.R;
import xyz.shopofly.shopofly.Utils.Helpers;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.WriterException;

public class HalalahActivity extends AppCompatActivity {
    private static final String TAG = HalalahActivity.class.getSimpleName();
    Order order;

    @BindView(R.id.qr_code_image)
    ImageView qrCodeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halalah);
        ButterKnife.bind(this);
        order = (Order) getIntent().getSerializableExtra("order");
        try {
            qrCodeImg.setImageBitmap(Helpers.generateQRCode(order.getHalalahCode(), (int) getResources().getDimension(R.dimen.qr_code_width),(int) getResources().getDimension(R.dimen.qr_code_height)));
        } catch (WriterException e) {
            Log.e(TAG, "onCreate: WriterException", e);
        }
    }

    @OnClick(R.id.backImage)
    public void back(){
        finish();
    }
}
