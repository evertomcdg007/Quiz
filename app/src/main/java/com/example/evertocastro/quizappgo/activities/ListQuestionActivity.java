package com.example.evertocastro.quizappgo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.evertocastro.quizappgo.R;
import com.example.evertocastro.quizappgo.adapter.ListQuestionAdapter;
import com.example.evertocastro.quizappgo.db.DBQuestion;
import com.example.evertocastro.quizappgo.models.Question;

import java.util.List;

public class ListQuestionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListQuestionAdapter listQuestionAdapter;
    DBQuestion dbQuestion;
    List<Question> listQuestion;
    View.OnClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);

        startView();

    }

    private void startView() {
        recyclerView = (RecyclerView) findViewById(R.id.list_recyclerView);
        dbQuestion = new DBQuestion(getApplicationContext());
        listQuestion = dbQuestion.getAllQuestion();

        mListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putParcelable("question", listQuestion.get(Integer.parseInt(view.getTag().toString())));
                Intent i = new Intent(getApplicationContext(), DetailQuestionActivity.class);
                i.putExtras(b);
                startActivity(i);
            }
        };

        listQuestionAdapter = new ListQuestionAdapter(listQuestion, mListener);
        recyclerView.setAdapter(listQuestionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
