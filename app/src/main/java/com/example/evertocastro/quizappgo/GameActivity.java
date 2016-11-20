package com.example.evertocastro.quizappgo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evertocastro.quizappgo.adapter.GameActivityAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    int nextQuestion = 0;
    int correctAnswer = 1;
    int answerCorrect = 0;

    static String PLAYER = "player";
    String player;

    TextView playerTextView;
    ImageView imageView;
    TextView questionTextView;
    RecyclerView itemsListView;

    List<Integer> imageList = new ArrayList<>();
    HashMap<Integer, String> questionList = new HashMap<>();
    List<String[]> itemsQuestion = new ArrayList<>();
    GameActivityAdapter adapterGameActivity;
    View.OnClickListener mListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initView();

        player = getIntent().getStringExtra(PLAYER);
        playerTextView.setText(getString(R.string.player)+""+player);

        setQuestion();

        mListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(v.getTag().toString()) == correctAnswer){
                    answerCorrect++;
                    alertDialog("Certa resposta!!!");
                }else{
                    alertDialog("Desculpe, resposta errada!");
                }
            }
        };

        setView();



    }

    private void alertDialog(String title) {

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle(title);
        //define a mensagem
        builder.setMessage("Prepare-se para a próxima pergunta "+player+"?");
        //define um botão como positivo
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
//                Toast.makeText(GameActivity.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
                nextQuestion++;
                if(nextQuestion == 1){
                    correctAnswer = 4;
                    setView();
                }else if(nextQuestion == 2){
                    correctAnswer = 2;
                    setView();
                }else if(nextQuestion == 3){
                    correctAnswer = 3;
                    setView();
                }else if(nextQuestion == 4){
                    correctAnswer = 0;
                    setView();
                }else{
                    Intent i = new Intent(GameActivity.this, ResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("player", player);
                    bundle.putInt("result", answerCorrect);
                    i.putExtras(bundle);
                    startActivity(i);
                }


            }
        });

        //cria o AlertDialog
        AlertDialog alertDialog = builder.create();
        //Exibe
        alertDialog.show();

    }

    private void setView() {

        imageView.setImageResource(imageList.get(nextQuestion));
        questionTextView.setText(questionList.get(nextQuestion));

        adapterGameActivity = new GameActivityAdapter(itemsQuestion.get(nextQuestion), mListener);
        itemsListView.setAdapter(adapterGameActivity);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        playerTextView = (TextView) findViewById(R.id.player_textView);
        imageView = (ImageView) findViewById(R.id.image_imageView);
        questionTextView = (TextView) findViewById(R.id.question_textView);
        itemsListView = (RecyclerView) findViewById(R.id.items_listView);
    }


    private void setQuestion() {

        String question1 = getResources().getString(R.string.question1);
        String question2 = getResources().getString(R.string.question2);
        String question3 = getResources().getString(R.string.question3);
        String question4 = getResources().getString(R.string.question4);
        String question5 = getResources().getString(R.string.question5);

        questionList.put(0, question1);
        questionList.put(1, question2);
        questionList.put(2, question3);
        questionList.put(3, question4);
        questionList.put(4, question5);

        String itemsQuestion1[] = getResources().getStringArray(R.array.items_question1);
        String itemsQuestion2[] = getResources().getStringArray(R.array.items_question2);
        String itemsQuestion3[] = getResources().getStringArray(R.array.items_question3);
        String itemsQuestion4[] = getResources().getStringArray(R.array.items_question4);
        String itemsQuestion5[] = getResources().getStringArray(R.array.items_question5);


        itemsQuestion.add(itemsQuestion1);
        itemsQuestion.add(itemsQuestion2);
        itemsQuestion.add(itemsQuestion3);
        itemsQuestion.add(itemsQuestion4);
        itemsQuestion.add(itemsQuestion5);


        imageList.add(R.drawable.servia_montenegro);
        imageList.add(R.drawable.guerra_civil);
        imageList.add(R.drawable.margot_robbie);
        imageList.add(R.drawable.opera_sidney_australia);
        imageList.add(R.drawable.wartortle);



    }
}
