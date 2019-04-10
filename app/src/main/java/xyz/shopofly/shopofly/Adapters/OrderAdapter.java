package xyz.shopofly.shopofly.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import xyz.shopofly.shopofly.Model.Network.Order;
import xyz.shopofly.shopofly.R;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {
    private static final String TAG = OrderAdapter.class.getSimpleName();

    public OrderAdapter(@NonNull Context context, @NonNull List<xyz.shopofly.shopofly.Model.Network.Order> objects) {
        super(context, 0, objects);
    }

    public OrderAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_order_item, parent, false);
        }
        TextView orderNumber = convertView.findViewById(R.id.titleRight);
        TextView name = convertView.findViewById(R.id.title);
        TextView orderAddress = convertView.findViewById(R.id.subtitle);

        Order order = getItem(position);

        assert order != null;
        orderNumber.setText("#"+order.getOrderId());
        name.setText(order.getCustomer().getFullName());
        orderAddress.setText(order.getAddress().toString());
        String transactionStatus = order.getTransaction().getStatus();

        View container = convertView.findViewById(R.id.view_container);
        if(transactionStatus.equalsIgnoreCase("approved")){
            container.setBackgroundColor(Color.GREEN);
            Log.d(TAG, "getView: color is green now");
        }else
            container.setBackgroundColor(Color.WHITE);

        return convertView;
    }
}
