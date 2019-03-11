package xyz.shopofly.shopofly.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import xyz.shopofly.shopofly.Model.Listing;
import xyz.shopofly.shopofly.R;

public class ListingAdapter extends ArrayAdapter<Listing> {

    public ListingAdapter(Context context, List<Listing> objects) {
        super(context, 0, objects);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_order_details,parent,false);
        ImageView image = convertView.findViewById(R.id.listingImage);
        TextView name = convertView.findViewById(R.id.listingName);
        TextView quantity = convertView.findViewById(R.id.listingQuantity);

        Listing listing = getItem(position);

        Glide.
                with(getContext()).
                load(listing.getImageURL()).
                into(image); // Parsing url into image
        name.setText(listing.getName());
        quantity.setText("Quantity: "+listing.getQuantity());

        return convertView;
    }
}
