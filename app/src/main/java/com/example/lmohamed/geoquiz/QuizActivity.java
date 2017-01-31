package com.example.lmohamed.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    /**
     * member(instance) variables are prefixed with 'm'
     */
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
    private int mCurrentIndex = 0;

    private void updateQuestion() {

        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retrieve saved question index from savedInstanceState
        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        /**
         * @Log for current onCreate lifecycle method.
         */
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        // Get references to the inflated View objects (buttons, textviews)
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mTrueButton = (Button)findViewById(R.id.quiz_true_button);
        mFalseButton = (Button)findViewById(R.id.quiz_false_button);
        mNextButton = (ImageButton)findViewById(R.id.quiz_next_button);
        mPrevButton = (ImageButton)findViewById(R.id.quiz_previous_button);
        mCheatButton = (Button)findViewById(R.id.cheat_button);

        // Set listeners on the objects to respond to user actions
        /**
         * Anonymous Inner Class
         * Everything between the outermost parentheses
         * is passed into setOnClickListener(OnClickListener).
         *
         * This puts the implementation of the listener's method up front
         * and there is no need for the overhead of a named class because the class
         * will be used in one place only
         *
         * Just-In-Time (JIT) override of onClick()
         *
         * The anonymous class implements the listener OnClickListener() and
         * must implement that interface's sole method, onClick(View)
         */

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
                    updateQuestion();
                }
            }
        });
        mCheatButton.setOnClickListener(new View.OnClickListener()
        {

            boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

            @Override
            public void onClick(View v){
                Intent doCheat = new Intent(QuizActivity.this, CheatActivity.class);
                doCheat.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(doCheat, 0); // sending a request code to know when CheatActivity calls back
            }
        });

        updateQuestion();
    }

    /**
     * Overriding more lifecycle methods
     * @Log for lifecycle methods.
     */

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState(...) called");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    /**
     * Menu for the app - not yet created
     * TODO: create this menu
     * @param menu
     * @return
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

     */

}
