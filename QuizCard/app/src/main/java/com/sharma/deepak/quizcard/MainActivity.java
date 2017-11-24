package com.sharma.deepak.quizcard;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private Button mBtnSubmitAllAnswers;
    private EditText mEtAsyncAnswer;
    private CheckBox mCbActivity, mCbFragment, mCbContentProvider, mCbNone;
    private RadioGroup mRgPaddingQuestion, mRgSingletonQuestion, mRgSyncQuestion;
    private boolean singleTonQuestionResult, synchronizedQuestionResult, componentsQuestionResult, paddingQuestionResult;
    public static final boolean INCORRECT = false;
    public static final boolean CORRECT = true;
    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewComponents();
    }

    /*
    * @author deepak sharma
    * @date   23-6-2017
    * @description method to initalise and find the view ids
    *
    */
    private void initViewComponents() {
        mBtnSubmitAllAnswers = (Button) findViewById(R.id.btn_submit);
        mBtnSubmitAllAnswers.setOnClickListener(this);
        mEtAsyncAnswer = (EditText) findViewById(R.id.et_async_task);
        mCbActivity = (CheckBox) findViewById(R.id.cb_q_one_op_one);
        mCbFragment = (CheckBox) findViewById(R.id.cb_q_one_op_two);
        mCbContentProvider = (CheckBox) findViewById(R.id.cb_q_one_op_three);
        mCbNone = (CheckBox) findViewById(R.id.cb_q_one_op_four);
        mRgPaddingQuestion = (RadioGroup) findViewById(R.id.rg_padding_margin);
        mRgSingletonQuestion = (RadioGroup) findViewById(R.id.rg_singleton);
        mRgSyncQuestion = (RadioGroup) findViewById(R.id.rg_syncronisation);
        mRgPaddingQuestion.setOnCheckedChangeListener(this);
        mRgSingletonQuestion.setOnCheckedChangeListener(this);
        mRgSyncQuestion.setOnCheckedChangeListener(this);
    }

    /*
    * @author deepak sharma
    * @date   24-6-2017
    * @description method implemented by interface OnClickListener to handle submit buttons click event
    *
    */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                displayQuizResult();
                break;
        }
    }

    private void displayQuizResult() {
        if (checkComponentsAnswers() == CORRECT) {
            ++finalScore;
        }
        if (AnswersUtility.checkAnswerForSelection(paddingQuestionResult, mRgPaddingQuestion) == CORRECT) {
            ++finalScore;
        }
        if (AnswersUtility.checkAnswerForSelection(singleTonQuestionResult, mRgSingletonQuestion) == CORRECT) {
            ++finalScore;
        }
        if (AnswersUtility.checkEnteredTextAnswer(this, mEtAsyncAnswer) == CORRECT) {
            ++finalScore;
        }
        if (AnswersUtility.checkAnswerForSelection(synchronizedQuestionResult, mRgSyncQuestion) == CORRECT) {
            ++finalScore;
        }
        ToastUtility.showToast(this, getString(R.string.final_result) + finalScore + "/5");
        resetAllValues();
    }

    private void resetAllValues() {
        finalScore = 0;
        mRgPaddingQuestion.clearCheck();
        mRgSyncQuestion.clearCheck();
        mRgSingletonQuestion.clearCheck();
        mEtAsyncAnswer.setText("");
        mCbActivity.setChecked(false);
        mCbFragment.setChecked(false);
        mCbContentProvider.setChecked(false);
        mCbNone.setChecked(false);

    }

    /*
    * @author deepak sharma
    * @date   24-6-2017
    * @description method to check answers for components question
    *
    */
    private boolean checkComponentsAnswers() {
        if (mCbActivity.isChecked() && mCbContentProvider.isChecked()) {
            if (mCbFragment.isChecked() || mCbNone.isChecked())
                componentsQuestionResult = INCORRECT;
            else
                componentsQuestionResult = CORRECT;
        } else {
            componentsQuestionResult = INCORRECT;
        }
        return componentsQuestionResult;
    }

    /*
     * @author deepak sharma
     * @date   24-6-2017
     * @description method implemented by interface OnCheckedChangeListener to handle radiogroup buttons check state
     *
     */
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_q_two_op_one:
                paddingQuestionResult = INCORRECT;
                break;
            case R.id.rb_q_two_op_two:
                paddingQuestionResult = INCORRECT;
                break;
            case R.id.rb_q_two_op_three:
                paddingQuestionResult = CORRECT;
                break;
            case R.id.rb_q_two_op_four:
                paddingQuestionResult = INCORRECT;
                break;
            case R.id.rb_q_three_op_one:
                singleTonQuestionResult = CORRECT;
                break;
            case R.id.rb_q_three_op_two:
                singleTonQuestionResult = INCORRECT;
                break;
            case R.id.rb_q_three_op_three:
                singleTonQuestionResult = INCORRECT;
                break;
            case R.id.rb_q_three_op_four:
                singleTonQuestionResult = INCORRECT;
                break;
            case R.id.rb_q_five_op_one:
                synchronizedQuestionResult = INCORRECT;
                break;
            case R.id.rb_q_five_op_two:
                synchronizedQuestionResult = INCORRECT;
                break;
            case R.id.rb_q_five_op_three:
                synchronizedQuestionResult = CORRECT;
                break;
            case R.id.rb_q_five_op_four:
                synchronizedQuestionResult = INCORRECT;
                break;
        }
    }
}
