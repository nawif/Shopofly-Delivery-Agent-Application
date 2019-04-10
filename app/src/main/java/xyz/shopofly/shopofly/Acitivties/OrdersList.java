package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.shopofly.shopofly.API.OrderService;
import xyz.shopofly.shopofly.Adapters.OrderAdapter;
import xyz.shopofly.shopofly.Model.Network.Order;
import xyz.shopofly.shopofly.R;
import xyz.shopofly.shopofly.Utils.Constants;
import xyz.shopofly.shopofly.Utils.Injector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrdersList extends AppCompatActivity {

    private static final String TAG = OrdersList.class.getSimpleName();
    @BindView(R.id.orders_list)
    ListView ordersList;

    @BindView(R.id.swipe)
    SwipeRefreshLayout pullToRefresh;


    List<Order> orders = new ArrayList<>();

    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
        init();

    }

    private void init() {
        fetchData();
        ButterKnife.bind(this);
        orderAdapter = new OrderAdapter(this, orders);
        ordersList.setAdapter(orderAdapter);
        ordersList.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(OrdersList.this, OrderDetails.class);
            intent.putExtra("order",orders.get(i));
            startActivity(intent);
        });
        pullToRefresh.setOnRefreshListener(this::fetchData);
    }

    private void fetchData() {
        OrderService orderService = Injector.provideOrderService();
        Call<List<Order>> ordersCall = orderService.getOrderList(Constants.TOKEN);

        ordersCall.enqueue(new Callback<List<xyz.shopofly.shopofly.Model.Network.Order>>() {
            @Override
            public void onResponse(Call<List<xyz.shopofly.shopofly.Model.Network.Order>> call, Response<List<xyz.shopofly.shopofly.Model.Network.Order>> response) {
                Log.d(TAG, "onResponse: "+response.message());
                orders=response.body();
                if (orders != null) {
                    orderAdapter.clear();
                    orderAdapter.addAll(orders);
                }
                pullToRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<xyz.shopofly.shopofly.Model.Network.Order>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage(),t );
                Toast.makeText(OrdersList.this, "Error, check your network access", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
