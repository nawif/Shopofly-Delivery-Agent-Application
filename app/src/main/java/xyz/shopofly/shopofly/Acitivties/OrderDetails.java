package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.shopofly.shopofly.Adapters.ListingAdapter;
import xyz.shopofly.shopofly.Model.Customer;
import xyz.shopofly.shopofly.Model.Listing;
import xyz.shopofly.shopofly.Model.Order;
import xyz.shopofly.shopofly.R;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class OrderDetails extends AppCompatActivity {

    private String TAG = "OrderDetails";
    private Order order;

    TextView customerName;
    TextView customerNumber;
    TextView customerLocation;
    Button navigateToGoogleMaps;

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

    }

    private void fillCustomerDetails(Customer customer) {

    }
}
