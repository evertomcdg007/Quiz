package com.example.evertocastro.quizappgo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evertocastro.quizappgo.R;
import com.example.evertocastro.quizappgo.adapter.GameActivityAdapter;
import com.example.evertocastro.quizappgo.db.DBQuestion;
import com.example.evertocastro.quizappgo.models.Question;
import com.example.evertocastro.quizappgo.util.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    int countNextQuestion = 0;
    int answersCorrects = 0;
    int categorySpinner = 0;
    String player = null;
    ImageView imageView;
    TextView questionTextView;
    RecyclerView itemsRecycleView;

    GameActivityAdapter gameActivityAdapter;
    View.OnClickListener mListener;
    DBQuestion dbQuestion;
    List<Question> questionList;
    Question question = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initView();
        startAction();
        setView(question);



    }

    private void startAction() {
        mListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer;
                countNextQuestion++;
                if( countNextQuestion < questionList.size()){
                    Question question = questionList.get(countNextQuestion);
                    if(question.getItemCorrect() == v.getTag()){
                        answer = getString(R.string.correct_answer);
                        answersCorrects++;
                    }else{
                        answer = getString(R.string.wrong_answer);
                    }
                    Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_SHORT).show();
                    setView(question);
                }else{
                    finish();
                    Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt(Constant.KEY_RESULT, answersCorrects);
                    b.putInt(Constant.KEY_SPINNER, categorySpinner);
                    b.putString(Constant.KEY_PLAYER, player);
                    i.putExtras(b);
                    startActivity(i);
                }

            }
        };

    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.image_imageView);
        questionTextView = (TextView) findViewById(R.id.question_textView);
        itemsRecycleView = (RecyclerView) findViewById(R.id.items_listView);


        Bundle b = getIntent().getExtras();
        categorySpinner = b.getInt(Constant.KEY_SPINNER);
        player = b.getString(Constant.KEY_PLAYER);
        dbQuestion = new DBQuestion(getApplicationContext());
        questionList = dbQuestion.selectQuestion(categorySpinner*5);
        question = questionList.get(0);
    }

    private void setView(Question q){

        Picasso.with(this)
                .load(question.getImage())
                .placeholder(R.drawable.profile_placeholder)   // optional
//                .resize(250, 200)                        // optional
                .into(imageView);

        questionTextView.setText(q.getQuestion());
        String items[] = new String[4];
        items[0] = q.getItem1();
        items[1] = q.getItem2();
        items[2] = q.getItem3();
        items[3] = q.getItem4();

        gameActivityAdapter = new GameActivityAdapter( items, mListener);
        itemsRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        itemsRecycleView.setAdapter(gameActivityAdapter);

    }




}
