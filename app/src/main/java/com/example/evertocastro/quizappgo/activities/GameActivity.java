package com.example.evertocastro.quizappgo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evertocastro.quizappgo.R;
import com.example.evertocastro.quizappgo.adapter.GameActivityAdapter;
import com.example.evertocastro.quizappgo.db.DBQuestion;
import com.example.evertocastro.quizappgo.models.Question;
import com.example.evertocastro.quizappgo.util.Constant;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    int countNextQuestion = 0;
    int answersCorrects = 0;
    int categorySpinner = 0;
    String player = null;
    ImageView imageView;
    TextView questionTextView;
    RecyclerView itemsRecycleView;
    LinearLayout linearLayout;

    GameActivityAdapter gameActivityAdapter;
    View.OnClickListener mListener;
    DBQuestion dbQuestion;
    List<Question> questionList;
    Question question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initView();
        startAction();
        questionList = searchDatas();
        if(!questionList.isEmpty()){
            question = questionList.get(countNextQuestion);
            setView(question);
        }else{
            Snackbar snackbar = Snackbar
                    .make(linearLayout, getString(R.string.info), Snackbar.LENGTH_INDEFINITE);
            snackbar.setActionTextColor(Color.BLUE);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(Color.RED);
            TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }

    }

    private List<Question> searchDatas() {
        Bundle b = getIntent().getExtras();
        categorySpinner = b.getInt(Constant.KEY_SPINNER);
        player = b.getString(Constant.KEY_PLAYER);
        dbQuestion = new DBQuestion(getApplicationContext());
        return  dbQuestion.selectQuestion(categorySpinner*5);
    }

    private void startAction() {
        mListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer;

                if(question.getItemCorrect() == v.getTag()){
                    answer = getString(R.string.correct_answer);
                    answersCorrects++;
                }else{
                    answer = getString(R.string.wrong_answer);
                }
                Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_SHORT).show();

                if( countNextQuestion < questionList.size()){
                    question = questionList.get(countNextQuestion);
                    setView(question);
                }else{
                    finish();
                    Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt(Constant.KEY_RESULT, answersCorrects);
                    b.putInt(Constant.KEY_SPINNER, questionList.size());
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
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

    }

    private void setView(Question q){
        countNextQuestion++;
        URI uri = URI.create(q.getImage());
            Picasso.with(this)
                    .load(String.valueOf(uri))
                    .placeholder(R.drawable.profile_placeholder)
                    .resize(250, 200)                        // optional
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
