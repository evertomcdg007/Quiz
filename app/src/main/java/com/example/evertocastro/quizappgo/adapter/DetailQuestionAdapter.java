package com.example.evertocastro.quizappgo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evertocastro.quizappgo.R;

public class DetailQuestionAdapter extends RecyclerView.Adapter<DetailQuestionAdapter.MyViewHolder>{

    private String items[] = new String[4];
    private int itemCorrect;
    public DetailQuestionAdapter(String items[], int itemCorrect) {
        this.items = items;
        this.itemCorrect = itemCorrect;
    }

    @Override
    public DetailQuestionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new DetailQuestionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailQuestionAdapter.MyViewHolder holder, int position) {
        String item = items[position];
        String numberItem = String.valueOf(position+1);
        holder.numberTextView.setText(numberItem);
        holder.itemTextView.setText(item);

        if(itemCorrect == position){
            holder.cardView.setBackgroundResource(R.color.green);
        }

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
