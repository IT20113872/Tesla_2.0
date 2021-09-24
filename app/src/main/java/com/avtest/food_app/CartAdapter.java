package com.avtest.food_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CartAdapter extends ArrayAdapter<model> {

    private Context context;
    private int resource;
    List<model> mods;

    CartAdapter(Context context, int resource, List<model> mods){
        super(context, resource, mods);

        this.context = context;
        this.resource = resource;
        this.mods = mods;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent, false);

        TextView title = row.findViewById(R.id.singletitle);
        TextView description = row.findViewById(R.id.singletitile2);
        ImageView imageView = row.findViewById(R.id.imageView);

        //todos [obj1,obj2,obj3]
        model mod = mods.get(position);
        title.setText(mod.getTitle());
        description.setText(mod.getDescription());
      //  imageView.setVisibility(row.INVISIBLE);

      //  if(mod.getFinished() > 0){
      //      imageView.setVisibility(View.VISIBLE);
     //   }
        return row;
    }
}
