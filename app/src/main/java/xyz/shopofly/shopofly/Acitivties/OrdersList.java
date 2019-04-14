package xyz.shopofly.shopofly.Acitivties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.shopofly.shopofly.API.OrderService;
import xyz.shopofly.shopofly.Adapters.OrderAdapter;
import xyz.shopofly.shopofly.Model.Network.Order;
import xyz.shopofly.shopofly.R;
import xyz.shopofly.shopofly.Utils.Constants;
import xyz.shopofly.shopofly.Utils.Helpers;
import xyz.shopofly.shopofly.Utils.Injector;
import xyz.shopofly.shopofly.Utils.TokenNotFoundException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrdersList extends AppCompatActivity {

    private static final String TAG = OrdersList.class.getSimpleName();
    @BindView(R.id.orders_list)
    ListView ordersList;

    @BindView(R.id.swipe)
    SwipeRefreshLayout pullToRefresh;

    @BindView(R.id.loading_progress)
    LottieAnimationView loadingAnimation;

    @BindView(R.id.backImage)
    ImageView logoutIcon;


    List<Order> orders = new ArrayList<>();

    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
        init();

    }

    private void init() {
        ButterKnife.bind(this);
        logoutIcon.setImageResource(R.drawable.ic_002_logout);
        fetchData();
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
        Helpers.showLoadingProgress(true, loadingAnimation);
        OrderService orderService = Injector.provideOrderService();
        Call<List<Order>> ordersCall = null;
        try {
            ordersCall = orderService.getOrderList(Helpers.getToken(this));
        } catch (TokenNotFoundException e) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        ordersCall.enqueue(new Callback<List<xyz.shopofly.shopofly.Model.Network.Order>>() {
            @Override
            public void onResponse(Call<List<xyz.shopofly.shopofly.Model.Network.Order>> call, Response<List<xyz.shopofly.shopofly.Model.Network.Order>> response) {
                Log.d(TAG, "onResponse: "+response.message());
                orders=sortedOrders(response.body());
                if (orders != null) {
                    orderAdapter.clear();
                    orderAdapter.addAll(orders);
                    Log.d(TAG, "onResponse: "+orders.get(0).getHalalahCode());
                }
                pullToRefresh.setRefreshing(false);
                Helpers.showLoadingProgress(false, loadingAnimation);

            }

            @Override
            public void onFailure(Call<List<xyz.shopofly.shopofly.Model.Network.Order>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage(),t );
                Toast.makeText(OrdersList.this, "Error, check your network access", Toast.LENGTH_SHORT).show();
                Helpers.showLoadingProgress(false, loadingAnimation);
                pullToRefresh.setRefreshing(false);
            }
        });
    }

    private List<Order> sortedOrders(List<Order> body) {
        List<Order> payedDeliveries = new ArrayList<>();
        List<Order> unPaidDeliveries = new ArrayList<>();
        for (Order order:body) {
            if(order.getTransaction().getStatus().equalsIgnoreCase("pending")){
                unPaidDeliveries.add(order);
            }else
                payedDeliveries.add(order);
        }
        List<Order> sortedOrders = new LinkedList<>();
        sortedOrders.addAll(unPaidDeliveries);
        sortedOrders.addAll(payedDeliveries);
        return sortedOrders;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
    @OnClick(R.id.backImage)
    public void logout(){
        showLogoutDialog();
    }

    private void showLogoutDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Helpers.deleteToken(OrdersList.this);
                        Intent i = new Intent(OrdersList.this, MainActivity.class);
                        startActivity(i);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.ic_warning)
                .show();
    }

}
