package com.example.evertocastro.quizappgo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.evertocastro.quizappgo.R;
import com.example.evertocastro.quizappgo.util.Constant;

public class InitActivity extends AppCompatActivity {

    EditText nameEditText;
    Button goButton;
    Button createQuestionButton;
    Button listQuestionButton;
    Spinner optionQuestionSpinner;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        startView();
        startActions();
    }

    public void startView(){

        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        goButton     = (Button)   findViewById(R.id.go_button);
        createQuestionButton = (Button) findViewById(R.id.create_question_button);
        listQuestionButton = (Button) findViewById(R.id.list_question_button);
        optionQuestionSpinner = (Spinner) findViewById(R.id.option_spinner);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    }

    private void startActions() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.option_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionQuestionSpinner.setAdapter(adapter);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePlayer = nameEditText.getText().toString();
                int positionSpinner = optionQuestionSpinner.getSelectedItemPosition();

                if(namePlayer.equals("")){
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, getString(R.string.nick_name_required), Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.BLUE);
                    View snackBarView = snackbar.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)snackBarView.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    snackBarView.setLayoutParams(params);
                    snackBarView.setBackgroundColor(Color.RED);
                    TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();
                }else{
                    Intent intent = new Intent(InitActivity.this,GameActivity.class);
                    Bundle b = new Bundle();
                    b.putString(Constant.KEY_PLAYER, namePlayer);
                    b.putInt(Constant.KEY_SPINNER, positionSpinner+1);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });

        createQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterQuestionActivity.class);
                startActivity(i);
            }
        });

        listQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ListQuestionActivity.class);
                startActivity(i);
            }
        });
    }

}
