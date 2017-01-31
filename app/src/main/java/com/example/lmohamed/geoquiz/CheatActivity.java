package com.example.lmohamed.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by lmohamed on 1/31/17.
 */

public class CheatActivity extends Activity {


    public static final  String EXTRA_ANSWER_IS_TRUE = "com.example.lmohamed.geoquiz.answer_is_true";  // extra for the intent
    public static final  String EXTRA_ANSWER_IS_SHOWN = "com.example.lmohamed.geoquiz.answer_shown";  // extra for the intent
    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    // Create an intent to tell Quiz activity if the user cheated
    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);

        setResult(RESULT_OK, data);
    }

    /**
     * pass the resource ID of the cheat layout to setContentView
     * @param savedInstanceState is the data to be passed to the Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.cheat_answer_textview);
        mShowAnswerButton = (Button)findViewById(R.id.cheat_answer_button);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                // the user saw the answer
                setAnswerShownResult(true);
            }
        });

    }

    public CheatActivity() {
        super();
    }
}
