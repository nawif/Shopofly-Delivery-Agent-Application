package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.shopofly.shopofly.Adapters.ListingAdapter;
import xyz.shopofly.shopofly.Model.Network.Address;
import xyz.shopofly.shopofly.Model.Network.Customer;
import xyz.shopofly.shopofly.Model.Network.Item;
import xyz.shopofly.shopofly.Model.Network.Order;
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
        fillCustomerDetails(order.getCustomer(), order.getAddress());
        listingAdapter = new ListingAdapter(this, order.getItems());
        listingListview.setAdapter(listingAdapter);
        fillTotal("Subtotal",order.getTotal().getTotal(),vSubtotal);
        fillTotal("VAT",order.getTotal().getVat(),vVat);
        fillTotal("Total",order.getTotal().getTotalWithVat(),vTotal);

    }

    private void fillTotal(String type,double total, View vTotal) {
        TextView paymentType = vTotal.findViewById(R.id.title);
        TextView paymentPrice = vTotal.findViewById(R.id.subtitle);

        paymentType.setText(type);
        paymentPrice.setText(String.format("%s", total));

    }

    private void fillCustomerDetails(Customer customer, Address address) {
        customerName.setText(customer.getFullName());
        customerNumber.setText(customer.getMobileNumber());
        customerLocation.setText(address.toString());
    }

    @OnClick(R.id.pay)
    public void toPayActivity(){
        Intent i = new Intent(this, PayActivity.class);
        i.putExtra("order",order);
        startActivity(i);
    }
}