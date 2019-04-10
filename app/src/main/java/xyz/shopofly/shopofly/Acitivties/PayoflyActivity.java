package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.shopofly.shopofly.Model.Network.Order;
import xyz.shopofly.shopofly.Model.Network.Payment;
import xyz.shopofly.shopofly.R;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import xyz.shopofly.shopofly.Services.NFC.NdefMessageParser;
import xyz.shopofly.shopofly.Services.NFC.ParsedNdefRecord;
import xyz.shopofly.shopofly.Services.NFC.Utilities;
import xyz.shopofly.shopofly.Utils.Constants;
import xyz.shopofly.shopofly.Utils.Helpers;
import xyz.shopofly.shopofly.Utils.Injector;
import xyz.shopofly.shopofly.Utils.TokenNotFoundException;

public class PayoflyActivity extends AppCompatActivity {

    private static final String TAG = PayoflyActivity.class.getSimpleName();
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    Order order;

    @BindView(R.id.payofly_layout)
    View layoutContainer;

    @BindView(R.id.payment_progress)
    ProgressBar paymentProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payofly);
        ButterKnife.bind(this);
        order= (Order) getIntent().getSerializableExtra("order");
        initNFCReader();

        layoutContainer.setOnClickListener(view -> handelClick());


    }

    private void initNFCReader() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this, "No NFC", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, this.getClass())
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled())
                showWirelessSettings();

            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    private void showWirelessSettings() {
        Toast.makeText(this, "You need to enable NFC", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        resolveIntent(intent);
        super.onNewIntent(intent);
    }

    private void resolveIntent(Intent intent) {
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs;

            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];

                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }

            } else {
                byte[] empty = new byte[0];
                byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
                Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                byte[] payload = Utilities.dumpTagData(tag).getBytes();
                NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, id, payload);
                NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
                msgs = new NdefMessage[] {msg};
            }

            displayMsgs(msgs);
        }
    }

    private void displayMsgs(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0)
            return;

        StringBuilder builder = new StringBuilder();
        List<ParsedNdefRecord> records = NdefMessageParser.parse(msgs[0]);
        final int size = records.size();

        for (int i = 0; i < size; i++) {
            ParsedNdefRecord record = records.get(i);
            String str = record.str();
            builder.append(str).append("\n");
        }
        Log.d(TAG, "displayMsgs: "+builder.toString());
        Payment payment = new Payment(builder.toString(),order.getOrderId());
        fetch(payment);
    }

    private void fetch(Payment payment){
        paymentProgress.setVisibility(View.VISIBLE);
        try {
            Injector.provideOrderService().processPayment(Helpers.getToken(this),payment).enqueue(new Callback<Payment>() {
                @Override
                public void onResponse(Call<Payment> call, Response<Payment> response) {
                    if( response.code() == 200){
                        Toast.makeText(PayoflyActivity.this, "Payment accepted, thank you for using Payofly", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(PayoflyActivity.this, "Order has been already payed for!", Toast.LENGTH_LONG).show();
                    }
                    paymentProgress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<Payment> call, Throwable t) {
                    Toast.makeText(PayoflyActivity.this, "Payment failed, please try again later", Toast.LENGTH_LONG).show();
                    paymentProgress.setVisibility(View.GONE);


                }
            });
        } catch (TokenNotFoundException e) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }

    public void handelClick() {
        Payment payment = new Payment("kkkkkkkkkk",order.getOrderId());
        fetch(payment);


    }
}


