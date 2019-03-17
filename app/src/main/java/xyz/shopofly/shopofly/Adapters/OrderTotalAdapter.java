package xyz.shopofly.shopofly.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import xyz.shopofly.shopofly.Model.Order;
import xyz.shopofly.shopofly.R;

public class OrderTotalAdapter extends ArrayAdapter<Order> {

    public OrderTotalAdapter(Context context, List<Order> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_text_far_apart ,parent ,false);

        TextView title = convertView.findViewById(R.id.title);
        TextView subtitle = convertView.findViewById(R.id.subtitle);

        return convertView;
    }
}
