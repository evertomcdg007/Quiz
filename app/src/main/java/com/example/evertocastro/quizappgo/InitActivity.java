package com.example.evertocastro.quizappgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitActivity extends AppCompatActivity {

    EditText nameEditText;
    Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        goButton     = (Button)   findViewById(R.id.go_button);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePlayer = nameEditText.getText().toString();

                if(namePlayer.equals("")){
                    Toast.makeText(InitActivity.this, "Preencha o campo com seu nick name", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(InitActivity.this,GameActivity.class);
                    intent.putExtra("player", namePlayer);
                    startActivity(intent);
                }
            }
        });
    }
}
