package com.example.lmohamed.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    /**
     * Constants
     */
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "StoredIndex";
    private static final int REQUEST_CODE_CHEAT = 0;

    /**
     * member(instance) variables are prefixed with 'm'
     */
    private boolean mIsCheater;
    private int mCurrentIndex = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * @Log for current onCreate lifecycle method.
         */
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);


        // Get references to the inflated View objects (buttons, views)
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mTrueButton = (Button)findViewById(R.id.quiz_true_button);
        mFalseButton = (Button)findViewById(R.id.quiz_false_button);
        mNextButton = (ImageButton)findViewById(R.id.quiz_next_button);
        mPrevButton = (ImageButton)findViewById(R.id.quiz_previous_button);
        mCheatButton = (Button)findViewById(R.id.cheat_button);

        // retrieve saved question index from savedInstanceState
        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();


        // Listeners
        mQuestionTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener()
        {  // This is the anonymous inner class
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex > 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                } else {
                    mCurrentIndex = mQuestionBank.length -1;
                    mIsCheater = false;
                    updateQuestion();
                }
            }
        });
        mCheatButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                Intent doCheat = new Intent(QuizActivity.this, CheatActivity.class);

                doCheat.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(doCheat, REQUEST_CODE_CHEAT); // sending a request code to know when CheatActivity calls back
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        //Log.i(TAG, "onSaveInstanceState(...) called");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        } else {
            mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_IS_SHOWN, false);
        }
    }

    private void updateQuestion() {

        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        Log.d("checkAnswer() method", "answer is: " + answerIsTrue);

        int messageResId;

        if(mIsCheater){
            messageResId = R.string.cheat_judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else { messageResId = R.string.incorrect_toast; }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    /**
     * Overriding more lifecycle methods
     * @Log for lifecycle methods.
     */
    @Override
    public void onStart() {
        super.onStart();
        //Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
       // Log.d(TAG, "OnPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
      ///  Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
       // Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // Log.d(TAG, "onDestroy() called");
    }

    /**
     * TODO: this menu
     * @param menu
     * @return
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

     */
}
