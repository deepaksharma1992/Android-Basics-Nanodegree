package com.sharma.deepak.studentreportcard;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class StudentDetailActivity extends AppCompatActivity {
    private TextView mTvName, mtvClass, mTvRollNo, mTvPercent, mTvSub1, mTvSub2, mTvSub3, mTvSub4, mTvSub5, mTvResult;

    /*
   * @created by -deepak
   * @date 30-6-2017
   * @description  overriden lifecycle method
   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        initViewComponents();
        ReportCard reportCard = (ReportCard) getIntent().getSerializableExtra(MainActivity.DETAIL_EXTRA);
        assignValuesToView(reportCard);
    }

    /*
    * @created by -deepak
    * @date 30-6-2017
    * @description  method to assign values to the views
    */
    private void assignValuesToView(ReportCard reportCard) {
        mTvName.setText(reportCard.getStudentName());
        mTvRollNo.setText(reportCard.getStudentRollNo());
        int standard = Integer.parseInt(reportCard.getStudentClass());
        int sub1 = Integer.parseInt(reportCard.getSubject1());
        int sub2 = Integer.parseInt(reportCard.getSubject2());
        int sub3 = Integer.parseInt(reportCard.getSubject3());
        int sub4 = Integer.parseInt(reportCard.getSubject4());
        int sub5 = Integer.parseInt(reportCard.getSubject5());
        mtvClass.setText(standard + " Standard");
        mTvSub1.setText(getString(R.string.sub1)+sub1);
        mTvSub2.setText(getString(R.string.sub2)+sub2);
        mTvSub3.setText(getString(R.string.sub3)+sub3);
        mTvSub4.setText(getString(R.string.sub4)+sub4);
        mTvSub5.setText(getString(R.string.sub5)+sub5);
        double percentage = (sub1 + sub2 + sub3 + sub4 + sub5) / 5;
        String formattedPercentage = String.format("%.1f", percentage);
        mTvPercent.setText(formattedPercentage+"%");
        if (percentage > 40) {
            mTvResult.setTextColor(ContextCompat.getColor(this, R.color.class_color));
            mTvResult.setText(getString(R.string.passed));
        } else {
            mTvResult.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
            mTvResult.setText(getString(R.string.failed));
        }
    }

    /*
        * @created by -deepak
        * @date 30-6-2017
        * @description  method to initialise the ui components
        */
    private void initViewComponents() {
        mTvName = (TextView) findViewById(R.id.tv_name);
        mtvClass = (TextView) findViewById(R.id.tv_class);
        mTvRollNo = (TextView) findViewById(R.id.tv_roll_no);
        mTvPercent = (TextView) findViewById(R.id.tv_percent);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mTvSub1 = (TextView) findViewById(R.id.tv_sub1);
        mTvSub2 = (TextView) findViewById(R.id.tv_sub2);
        mTvSub3 = (TextView) findViewById(R.id.tv_sub3);
        mTvSub4 = (TextView) findViewById(R.id.tv_sub4);
        mTvSub5 = (TextView) findViewById(R.id.tv_sub5);
    }
}
