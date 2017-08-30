package ru.book.philips.geoquiz;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String KEY_INDEX="index";
    private static final String TAG="QuizActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mLandscapeNextButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]
            {
                    new Question(R.string.question_australia, true),
                    new Question(R.string.question_oceans, true),
                    new Question(R.string.question_mideast, false),
                    new Question(R.string.question_africa, false),
                    new Question(R.string.question_americas, true),
                    new Question(R.string.question_asia, true),
            };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState!=null) mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton=(Button) findViewById(R.id.true_button);
        mFalseButton=(Button) findViewById(R.id.false_button);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) mNextButton=(ImageButton) findViewById(R.id.next_button);
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            mLandscapeNextButton=(Button) findViewById(R.id.next_button);
        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);
        mPreviousButton=(ImageButton) findViewById(R.id.previous_button);
        View mNextView=mNextButton==null?mLandscapeNextButton:mNextButton;
        updateQuestion();

        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        if (mPreviousButton!=null) mPreviousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex=--mCurrentIndex==-1?mQuestionBank.length-1:mCurrentIndex;
                updateQuestion();
            }
        });
        mNextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
    }
    private void updateQuestion(){
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
    }
    private void checkAnswer(boolean userPressedTrue){
        Toast t;
        if (userPressedTrue) t=Toast.makeText(QuizActivity.this,mQuestionBank[mCurrentIndex].isAnswerTrue()?R.string.correct_toast:R.string.incorrect_toast,Toast.LENGTH_SHORT);
        else t=Toast.makeText(QuizActivity.this,!mQuestionBank[mCurrentIndex].isAnswerTrue()?R.string.correct_toast:R.string.incorrect_toast,Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP,0,150);
        t.show();
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX,mCurrentIndex);
    }
}
