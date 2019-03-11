package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.shopofly.shopofly.Adapters.OrderAdapter;
import xyz.shopofly.shopofly.Model.Customer;
import xyz.shopofly.shopofly.Model.Listing;
import xyz.shopofly.shopofly.Model.Order;
import xyz.shopofly.shopofly.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class OrdersList extends AppCompatActivity {

    @BindView(R.id.orders_list)
    ListView ordersList;

    ArrayList<Order> orders = new ArrayList<>();

    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
        init();

    }

    private void init() {
        fillArray(orders);
        ButterKnife.bind(this);
        orderAdapter = new OrderAdapter(this, orders);
        ordersList.setAdapter(orderAdapter);
        ordersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(OrdersList.this, OrderDetails.class);
                intent.putExtra("order",orders.get(i));
                startActivity(intent);
            }
        });
    }

    private void fillArray(ArrayList<Order> orders) {
        for (int i = 0; i < 20; i++) {
            orders.add(new Order((int)(Math.random()*1000000)+"", new Customer("Nawaf alquaid","0568484248", "Aqiq Zawr Harith 6262")));
            orders.get(i).addListing(new Listing("Nylon Braided Lightning Cable",12.99,99,"http://shopofly.xyz/storage/listingsImages/MTQ1Mjc4ODY.jpg"));
            orders.get(i).addListing(new Listing("Nylon Braided Lightning Cable",12.99,99,"http://shopofly.xyz/storage/listingsImages/MTQ1Mjc4ODY.jpg"));
        }
    }
}
