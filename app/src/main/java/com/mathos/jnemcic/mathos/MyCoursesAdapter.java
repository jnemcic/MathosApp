package com.mathos.jnemcic.mathos;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCoursesAdapter extends BaseAdapter{
    List<Course> result;
    Context context;

    private static LayoutInflater inflater=null;

    public MyCoursesAdapter(Context context, List<Course> courses) {
        result=courses;
        this.context=context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder {
        TextView name;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int viewType = this.getItemViewType(position);

        View rowView;
        rowView = inflater.inflate(R.layout.mycourses_list_item, null);

        TextView name=(TextView) rowView.findViewById(R.id.course_name);
        name.setText(result.get(position).getName());

        ImageView courseImage = (ImageView) rowView.findViewById(R.id.mycourses_list_item_image);
        courseImage.setImageResource(R.drawable.background);

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return rowView;
    }
}
