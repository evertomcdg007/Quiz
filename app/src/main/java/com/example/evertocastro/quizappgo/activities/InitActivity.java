package com.example.evertocastro.quizappgo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.evertocastro.quizappgo.R;
import com.example.evertocastro.quizappgo.util.Constant;

public class InitActivity extends AppCompatActivity {

    EditText nameEditText;
    Button goButton;
    Button createQuestionButton;
    Button listQuestionButton;
    Spinner optionQuestionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        startView();
        startActions();
    }

    private void startActions() {

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.option_spinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        optionQuestionSpinner.setAdapter(adapter);


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePlayer = nameEditText.getText().toString();
                int positionSpinner = optionQuestionSpinner.getSelectedItemPosition();

                if(namePlayer.equals("")){
                    Toast.makeText(InitActivity.this, getString(R.string.nick_name_required), Toast.LENGTH_SHORT).show();
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

    public void startView(){

        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        goButton     = (Button)   findViewById(R.id.go_button);
        createQuestionButton = (Button) findViewById(R.id.create_question_button);
        listQuestionButton = (Button) findViewById(R.id.list_question_button);
        optionQuestionSpinner = (Spinner) findViewById(R.id.option_spinner);
    }
}
