package com.hoavtm.lab3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoavtm.lab3.models.Fruit;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Fruit> fruitList;

    public FruitAdapter(Context context, int layout, List<Fruit> fruitList) {
        this.context = context;
        this.layout = layout;
        this.fruitList = fruitList;
    }

    @Override
    public int getCount() {
        return fruitList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private int selectedPosition = -1;

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        //view mapping
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        ImageView ivThumbnail = (ImageView) view.findViewById(R.id.ivThumbnail);

        Fruit fruit = fruitList.get(i);

        tvName.setText(fruit.getName());
        tvDescription.setText(fruit.getDescription());
        if (fruit.getImageUri() != null && !fruit.getImageUri().isEmpty()) {
            ivThumbnail.setImageURI(android.net.Uri.parse(fruit.getImageUri()));
        } else {
            ivThumbnail.setImageResource(fruit.getImage());
        }

        if (i == selectedPosition) {
            view.setBackgroundColor(Color.parseColor("#FFE082"));
        } else {
            view.setBackgroundColor(Color.TRANSPARENT); // default
        }

        return view;
    }
}
