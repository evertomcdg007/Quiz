package com.example.evertocastro.quizappgo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.evertocastro.quizappgo.R;
import com.example.evertocastro.quizappgo.db.DBQuestion;
import com.example.evertocastro.quizappgo.models.Question;

public class RegisterQuestionActivity extends AppCompatActivity {

    EditText imageEditText;
    EditText questionEditText;
    EditText item1EditText;
    EditText item2EditText;
    EditText item3EditText;
    EditText item4EditText;
    Spinner itemCorrectSpinner;
    Button   saveButton;
    DBQuestion dbQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_question);
        dbQuestion = new DBQuestion(this);

        startView();
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_correct_spinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        itemCorrectSpinner.setAdapter(adapter);

        startAction();
    }

    private void startAction() {

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String image = imageEditText.getText().toString();
                String question = questionEditText.getText().toString();
                String item1 = item1EditText.getText().toString();
                String item2 = item2EditText.getText().toString();
                String item3 = item3EditText.getText().toString();
                String item4 = item4EditText.getText().toString();
                int itemCorrect = itemCorrectSpinner.getSelectedItemPosition();

                if(!image.equals("") && !question.equals("") && !item1.equals("")&& !item2.equals("") &&
                        !item3.equals("") && !item4.equals("") && itemCorrect != 0){

                    Question q = new Question(image, question, item1, item2, item3, item4, itemCorrectSpinner.getSelectedItemPosition());
                    dbQuestion.addQuestion(q);

                    imageEditText.setText("");
                    questionEditText.setText("");
                    item1EditText.setText("");
                    item2EditText.setText("");
                    item3EditText.setText("");
                    item4EditText.setText("");
                    itemCorrectSpinner.setSelection(0);

                    Toast.makeText(getApplicationContext(), getString(R.string.saveSuccess), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), getString(R.string.field_empty), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void startView() {

        imageEditText    = (EditText) findViewById(R.id.image_editText);
        questionEditText = (EditText) findViewById(R.id.question_editText);
        item1EditText    = (EditText) findViewById(R.id.item1_editText);
        item2EditText    = (EditText) findViewById(R.id.item2_editText);
        item3EditText    = (EditText) findViewById(R.id.item3_editText);
        item4EditText    = (EditText) findViewById(R.id.item4_editText);
        itemCorrectSpinner = (Spinner) findViewById(R.id.item_correct_editText);
        saveButton       = (Button)   findViewById(R.id.save_button);



    }
}
