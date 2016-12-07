package com.example.evertocastro.quizappgo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evertocastro.quizappgo.R;
import com.example.evertocastro.quizappgo.models.Question;

import java.util.List;

public class ListQuestionAdapter extends RecyclerView.Adapter<ListQuestionAdapter.MyViewHolder> {


    private View.OnClickListener mListener;
    private List<Question> questionList;

    public ListQuestionAdapter(List<Question> questionList, View.OnClickListener mListener){
        this.questionList = questionList;
        this.mListener = mListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ListQuestionAdapter.MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String item = questionList.get(position).getQuestion();
        holder.itemTextView.setText(item);
        holder.itemCardView.setTag(position);
        holder.itemCardView.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;
        CardView itemCardView;

        MyViewHolder(View view){
            super(view);
            itemTextView = (TextView) view.findViewById(R.id.item_question);
            itemCardView = (CardView) view.findViewById(R.id.item_cardView);
        }


    }
}
