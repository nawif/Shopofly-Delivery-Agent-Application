package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.shopofly.shopofly.Adapters.ListingAdapter;
import xyz.shopofly.shopofly.Model.Customer;
import xyz.shopofly.shopofly.Model.Listing;
import xyz.shopofly.shopofly.Model.Order;
import xyz.shopofly.shopofly.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class OrderDetails extends AppCompatActivity {

    private String TAG = "OrderDetails";
    private Order order;

    @BindView(R.id.customer_name)
    TextView customerName;
    @BindView(R.id.customer_phone)
    TextView customerNumber;
    @BindView(R.id.customer_location)
    TextView customerLocation;
    @BindView(R.id.total)
    View vTotal;
    @BindView(R.id.vat)
    View vVat;
    @BindView(R.id.subtotal)
    View vSubtotal;

    Button navigateToGoogleMaps;

//    @BindView(R.id.pay)
//    Button payBtn;


    @BindView(R.id.orders_list)
    ListView listingListview;

    ListingAdapter listingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        init();
    }

    private void init() {
        order = (Order) getIntent().getSerializableExtra("order");
        ButterKnife.bind(this);
        fillCustomerDetails(order.getCustomer());
        for (Listing listing: order.getListings()
             ) {
            Log.d(TAG, "init: "+listing.getName());
            Log.d(TAG, "init: "+listing.getImageURL());
        }
        listingAdapter = new ListingAdapter(this, order.getListings());
        listingListview.setAdapter(listingAdapter);
        fillTotal("Subtotal",order.getSubtotal(),vSubtotal);
        fillTotal("VAT",order.getVat(),vVat);
        fillTotal("Total",order.getTotal(),vTotal);

    }

    private void fillTotal(String type,double total, View vTotal) {
        TextView paymentType = vTotal.findViewById(R.id.title);
        TextView paymentPrice = vTotal.findViewById(R.id.subtitle);

        paymentType.setText(type);
        paymentPrice.setText(total+"");

    }

    private void fillCustomerDetails(Customer customer) {
        customerName.setText(customer.getName());
        customerNumber.setText(customer.getPhone());
        customerLocation.setText(customer.getAddress());
    }

    @OnClick(R.id.pay)
    public void toPayActivity(){
        Intent i = new Intent(this, PaymentActivity.class);
        startActivity(i);
    }
}
