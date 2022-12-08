package com.example.projectfive_an;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlavorAdp extends RecyclerView.Adapter<FlavorAdp.FlavorHolder> {
    private static Context context;
    private List<Integer> imageList;
    private List<String> flavorLst;
    private onClickListener listener;
    private static final int DEX_STATE = 1;
    private static final int BBQ_STATE = 2;
    private static final int MEA_STATE = 3;
    private static final int BYO_STATE = 4;

    interface onClickListener{
        void clicked(String flavor);
    }

    public FlavorAdp(Context context, List<Integer> imageLst, List<String> flavorLst,
                     onClickListener listener) {
        this.context = context;
        this.imageList = imageLst;
        this.flavorLst = flavorLst;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FlavorAdp.FlavorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new FlavorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlavorAdp.FlavorHolder holder, int position) {
        holder.pizzaImage.setImageResource(imageList.get(position));
        holder.flavor.setText(flavorLst.get(position));
        holder.bind(flavorLst.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class FlavorHolder extends RecyclerView.ViewHolder {
        private TextView flavor;
        private ImageView pizzaImage;
        private LinearLayout row_item;

        public FlavorHolder(@NonNull View itemView) {
            super(itemView);
            flavor = itemView.findViewById(R.id.flavor);
            pizzaImage = itemView.findViewById(R.id.pizzaImg);
            row_item = itemView.findViewById(R.id.one_row);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if ("Deluxe".equals(flavor.getText().toString())){
                        Toast.makeText(itemView.getContext(),
                                "Deluxe Pizza selected", Toast.LENGTH_SHORT).show();

                    }else if("BBQ Chicken".equals(flavor.getText().toString())){
                        Toast.makeText(itemView.getContext(),
                                "BBQ Chicken Pizza selected", Toast.LENGTH_SHORT).show();
                    }else if("Meatzza".equals(flavor.getText().toString())){
                        Toast.makeText(itemView.getContext(),
                                "Meatzza flavor Pizza selected", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(itemView.getContext(),
                                "Build Your Own Pizza selected", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        public void bind(String flavor,onClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    listener.clicked(flavor);
                }
            });
        }
    }

}
