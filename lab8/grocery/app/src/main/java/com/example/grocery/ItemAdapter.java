package com.example.grocery;

import android.content.Context;
import android.database.Cursor;
import android.view.*;
import android.widget.*;

public class ItemAdapter extends BaseAdapter {

    Context context;
    Cursor cursor;

    public ItemAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if (view == null)
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_row, parent, false);

        TextView name = view.findViewById(R.id.name);
        TextView cost = view.findViewById(R.id.cost);

        cursor.moveToPosition(i);

        name.setText(cursor.getString(1));
        cost.setText("₹" + cursor.getInt(2));

        return view;
    }
}