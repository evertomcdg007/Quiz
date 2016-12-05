package com.example.evertocastro.quizappgo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.evertocastro.quizappgo.R;
import com.example.evertocastro.quizappgo.util.Constant;

public class ResultActivity extends AppCompatActivity {

    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();

        Bundle b = getIntent().getExtras();
        int result = b.getInt(Constant.KEY_RESULT);
        String player = b.getString(Constant.KEY_PLAYER);
        int category = b.getInt(Constant.KEY_SPINNER);

        resultTextView.setText(getString(R.string.result, player, result, category));

    }

    private void initView() {
        resultTextView = (TextView) findViewById(R.id.result_textView);
    }
}
