package com.example.evertocastro.quizappgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView testTextView;
    TextView playerTextView;
    TextView resultTextView;

    static final String RESULT = "result";
    static final String PLAYER = "player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();

        Bundle b = getIntent().getExtras();
        int result = b.getInt(RESULT);
        String player = b.getString(PLAYER);

        resultTextView.setText(getString(R.string.result, player, result));

    }

    private void initView() {
        resultTextView = (TextView) findViewById(R.id.result_textView);
    }
}
