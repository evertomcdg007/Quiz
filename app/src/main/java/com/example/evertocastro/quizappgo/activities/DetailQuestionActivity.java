package com.example.evertocastro.quizappgo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evertocastro.quizappgo.R;
import com.example.evertocastro.quizappgo.adapter.DetailQuestionAdapter;
import com.example.evertocastro.quizappgo.models.Question;
import com.squareup.picasso.Picasso;

public class DetailQuestionActivity extends AppCompatActivity {

    public static final String KEY_QUESTION = "question";

    ImageView imageView;
    TextView questionTextView;
    RecyclerView listRecyclerView;
    DetailQuestionAdapter detailQuestionAdapter;
    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_question);

        Bundle bundle = getIntent().getExtras();
        question = bundle.getParcelable(KEY_QUESTION);

        startView();
        getView(question);



    }

    private void getView(Question question) {

        questionTextView.setText(question.getQuestion());
        Picasso.with(this)
                .load(question.getImage())
                .placeholder(R.drawable.profile_placeholder)   // optional
                .resize(250, 200)                        // optional
                .into(imageView);
        String items[] = new String[4];
        items[0] = question.getItem1();
        items[1] = question.getItem2();
        items[2] = question.getItem3();
        items[3] = question.getItem4();

        detailQuestionAdapter = new DetailQuestionAdapter(items, question.getItemCorrect());
        listRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listRecyclerView.setAdapter(detailQuestionAdapter);
    }

    private void startView() {
        imageView = (ImageView) findViewById(R.id.image_imageView);
        questionTextView = (TextView) findViewById(R.id.question_textView);
        listRecyclerView = (RecyclerView) findViewById(R.id.items_recyclerView);
    }
}
