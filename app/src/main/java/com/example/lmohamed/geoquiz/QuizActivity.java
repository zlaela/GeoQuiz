package com.example.lmohamed.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    // logging
    private static final String TAG = "QuizActivity";

    /**
     * member(instance) variables are prefixed with 'm'
     */
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
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

        // Logging
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        // Get references to the inflated View objects (buttons, textviews)
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        mTrueButton = (Button)findViewById(R.id.quiz_true_button);
        mFalseButton = (Button)findViewById(R.id.quiz_false_button);
        mNextButton = (Button)findViewById(R.id.quiz_next_button);


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
                checkAnswer(true);
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
        updateQuestion();
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
