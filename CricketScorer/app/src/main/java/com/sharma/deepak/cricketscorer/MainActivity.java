package com.sharma.deepak.cricketscorer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvOneBall, mTvSecondBall, mTvThirdBall, mTvFourthBall, mTvResult, mTvFifthBall, mTvSixthBall, mTvIndianScore, mTvPakScore;
    private Button mBtnOneRun, mBtnTwoRun, mBtnThreeRun, mBtnFourRun, mBtnSixRun, mBtnReset;
    private int ballCount, indiaScore, pakScore;
    boolean isIndiaBatting = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewComponents();
    }

    private void initViewComponents() {
        mTvOneBall = (TextView) findViewById(R.id.tv_first_ball);
        mTvSecondBall = (TextView) findViewById(R.id.tv_second_ball);
        mTvThirdBall = (TextView) findViewById(R.id.tv_third_ball);
        mTvFourthBall = (TextView) findViewById(R.id.tv_fourth_ball);
        mTvFifthBall = (TextView) findViewById(R.id.tv_five_ball);
        mTvSixthBall = (TextView) findViewById(R.id.tv_six_ball);
        mTvIndianScore = (TextView) findViewById(R.id.tv_ind_score);
        mTvPakScore = (TextView) findViewById(R.id.tv_pak_score);
        mTvResult = (TextView) findViewById(R.id.tv_match_result);

        mBtnOneRun = (Button) findViewById(R.id.btn_one);
        mBtnOneRun.setOnClickListener(this);
        mBtnTwoRun = (Button) findViewById(R.id.btn_two);
        mBtnTwoRun.setOnClickListener(this);
        mBtnThreeRun = (Button) findViewById(R.id.btn_three);
        mBtnThreeRun.setOnClickListener(this);
        mBtnFourRun = (Button) findViewById(R.id.btn_four);
        mBtnFourRun.setOnClickListener(this);
        mBtnSixRun = (Button) findViewById(R.id.btn_six);
        mBtnSixRun.setOnClickListener(this);
        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mBtnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btn_one:
                setScore(1, ++ballCount);
                break;
            case R.id.btn_two:
                setScore(2, ++ballCount);
                break;
            case R.id.btn_three:
                setScore(3, ++ballCount);
                break;
            case R.id.btn_four:
                setScore(4, ++ballCount);
                break;
            case R.id.btn_six:
                setScore(6, ++ballCount);
                break;
            case R.id.btn_reset:
                resetAllValues();
                break;
        }
    }

    private void resetAllValues() {
        resetBallValues();
        mTvIndianScore.setText(getString(R.string.zero));
        mTvPakScore.setText(getString(R.string.zero));
        isIndiaBatting = true;
        mTvResult.setText(getString(R.string.india_batting));
        indiaScore = 0;
        pakScore = 0;
    }


    private void setScore(int score, int ball) {
        if (isIndiaBatting) {
            indiaScore = indiaScore + score;
            mTvIndianScore.setText(Integer.toString(indiaScore));
        } else {
            pakScore = pakScore + score;
            mTvPakScore.setText(Integer.toString(pakScore));
        }


        if (ball == 1) {
            mTvOneBall.setText(Integer.toString(score) + ",");
        } else if (ball == 2) {
            mTvSecondBall.setText(Integer.toString(score) + ",");

        } else if (ball == 3) {
            mTvThirdBall.setText(Integer.toString(score) + ",");

        } else if (ball == 4) {
            mTvFourthBall.setText(Integer.toString(score) + ",");

        } else if (ball == 5) {
            mTvFifthBall.setText(Integer.toString(score) + ",");

        } else if (ball == 6) {
            mTvSixthBall.setText(Integer.toString(score));
            endOfInning();
        }


    }

    private void endOfInning() {
        if (isIndiaBatting) {
            isIndiaBatting = false;
            resetBallValues();
            mTvResult.setText(getString(R.string.pak_batting));
            Toast.makeText(this, "End Of Innings", Toast.LENGTH_SHORT).show();
        } else {
            isIndiaBatting = true;
            endOfMatch();
        }
    }

    private void endOfMatch() {
        int pakScore = Integer.parseInt(mTvPakScore.getText().toString());
        int indScore = Integer.parseInt(mTvIndianScore.getText().toString());
        if (pakScore > indScore) {
            Toast.makeText(this, getString(R.string.pak_won), Toast.LENGTH_LONG).show();
            mTvResult.setText(getString(R.string.pak_won));
        } else if (pakScore < indScore) {
            Toast.makeText(this, getString(R.string.ind_won), Toast.LENGTH_LONG).show();
            mTvResult.setText(getString(R.string.ind_won));
        } else {
            Toast.makeText(this, getString(R.string.match_draw), Toast.LENGTH_LONG).show();
            mTvResult.setText(getString(R.string.match_draw));
        }
        resetAllValues();
    }

    private void resetBallValues() {
        ballCount = 0;
        mTvOneBall.setText("-");
        mTvSecondBall.setText("-");
        mTvThirdBall.setText("-");
        mTvFourthBall.setText("-");
        mTvFifthBall.setText("-");
        mTvSixthBall.setText("-");
    }
}
