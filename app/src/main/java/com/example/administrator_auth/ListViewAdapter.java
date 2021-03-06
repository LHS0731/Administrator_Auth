package com.example.administrator_auth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<ListData> {
    private LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, int resource, List<ListData> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListData data = (ListData)getItem(position);
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.receipt_list, null);
        }

        TextView nameText;
        TextView priceText;
        TextView EAText;
        TextView amountText;

        nameText = (TextView)convertView.findViewById(R.id.name);
        priceText = (TextView)convertView.findViewById(R.id.price);
        EAText = (TextView)convertView.findViewById(R.id.EA);
        amountText = (TextView)convertView.findViewById(R.id.amount);

        nameText.setText(data.getname());
        priceText.setText(data.getprice());
        EAText.setText(data.getEA());
        amountText.setText(data.getamount());

        return convertView;
    }
}