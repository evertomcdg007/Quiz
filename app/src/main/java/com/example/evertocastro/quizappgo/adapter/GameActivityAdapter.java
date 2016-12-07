package com.example.evertocastro.quizappgo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evertocastro.quizappgo.R;

public class GameActivityAdapter extends RecyclerView.Adapter<GameActivityAdapter.MyViewHolder>{

    private String items[];
    private View.OnClickListener mListener;
    public GameActivityAdapter(String items[], View.OnClickListener mListener){
        this.items = items;
        this.mListener = mListener;
    }

    @Override
    public GameActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new GameActivityAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameActivityAdapter.MyViewHolder holder, int position) {
        String item = items[position];
        String numberItem = String.valueOf(position+1);
        holder.cardView.setTag(position);
        holder.numberTextView.setText(numberItem);
        holder.cardView.setOnClickListener(mListener);
        holder.itemTextView.setText(item);

    }

    @Override
    public int getItemCount() {
        return items.length;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView numberTextView;
        TextView itemTextView;
        CardView cardView;

        MyViewHolder(View view){
            super(view);
            numberTextView = (TextView) view.findViewById(R.id.number_question);
            itemTextView = (TextView) view.findViewById(R.id.item_question);
            cardView = (CardView) view.findViewById(R.id.item_cardView);
        }


    }
}
