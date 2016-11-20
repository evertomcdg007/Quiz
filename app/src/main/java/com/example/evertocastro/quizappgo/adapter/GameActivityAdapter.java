package com.example.evertocastro.quizappgo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evertocastro.quizappgo.R;

/**
 * Created by Everto Castro on 19/11/2016.
 */

public class GameActivityAdapter  extends RecyclerView.Adapter<GameActivityAdapter.MyViewHolder> {
    private String items[] = new String[5];
    private View.OnClickListener mListener;

    public GameActivityAdapter(String items[], View.OnClickListener mListener){
        this.items = items;
        this.mListener = mListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String item = items[position];
        String numberItem = String.valueOf(position+1);
        holder.numberTextView.setText(numberItem);
        holder.itemTextView.setText(item);

        holder.itemLinearLayout.setTag(position);
        holder.itemLinearLayout.setOnClickListener(mListener);

    }

    @Override
    public int getItemCount() {
        return items.length;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView numberTextView;
        TextView itemTextView;
        CardView itemLinearLayout;

        public MyViewHolder(View view){
            super(view);
            numberTextView = (TextView) view.findViewById(R.id.number_question);
            itemTextView = (TextView) view.findViewById(R.id.item_question);
            itemLinearLayout = (CardView) view.findViewById(R.id.item_linearLayout);
        }


    }
}
