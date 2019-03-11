package xyz.shopofly.shopofly.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import xyz.shopofly.shopofly.Model.Order;
import xyz.shopofly.shopofly.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {
    public OrderAdapter(@NonNull Context context, @NonNull List<Order> objects) {
        super(context, 0, objects);
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
        orderNumber.setText("#"+order.getCode());
        name.setText(order.getCustomer().getName());
        orderAddress.setText(order.getCustomer().getAddress());

        return convertView;
    }
}
