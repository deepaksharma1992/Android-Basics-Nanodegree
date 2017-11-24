package com.sharma.deepak.studentreportcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtName, mEtClass, mEtRollNo, mEtSub1, mEtSub2, mEtSub3, mEtSub4, mEtSub5;
    public static final String STUDENT_EXTRA_DATA = "extra_student";

    /*
   * @created by -deepak
   * @date 30-6-2017
   * @description  overriden lifecycle method
   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initViewComponents();
    }

    /*
    * @created by -deepak
    * @date 30-6-2017
    * @description  method to initialise the ui components
    */
    private void initViewComponents() {
        Button submitButton = (Button) findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(this);
        mEtName = (EditText) findViewById(R.id.et_student_name);
        mEtClass = (EditText) findViewById(R.id.et_student_class);
        mEtRollNo = (EditText) findViewById(R.id.et_student_roll_no);
        mEtSub1 = (EditText) findViewById(R.id.et_student_mark1);
        mEtSub2 = (EditText) findViewById(R.id.et_student_mark2);
        mEtSub3 = (EditText) findViewById(R.id.et_student_mark3);
        mEtSub4 = (EditText) findViewById(R.id.et_student_mark4);
        mEtSub5 = (EditText) findViewById(R.id.et_student_mark5);
    }

    /*
        * @created by -deepak
        * @date 30-6-2017
        * @description  method to perform the click event on view components
        */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                if (validateFields()) {
                    String name = mEtName.getEditableText().toString();
                    String classS = mEtClass.getEditableText().toString();
                    String rollNo = mEtRollNo.getEditableText().toString();
                    String sub1 = mEtSub1.getEditableText().toString();
                    String sub2 = mEtSub2.getEditableText().toString();
                    String sub3 = mEtSub3.getEditableText().toString();
                    String sub4 = mEtSub4.getEditableText().toString();
                    String sub5 = mEtSub5.getEditableText().toString();
                    ReportCard reportCard = new ReportCard(name, classS, rollNo, sub1, sub2, sub3, sub4, sub5);
                    Intent intentStudentData = new Intent();
                    intentStudentData.putExtra(STUDENT_EXTRA_DATA, reportCard);
                    setResult(MainActivity.ADD_STUDENT_ACTIVITY_REQUEST_CODE, intentStudentData);
                    finish();
                }
        }
    }

    /*
        * @created by -deepak
        * @date 30-6-2017
        * @description  method to validate all the edit text i activity
        */
    private boolean validateFields() {
        if (TextUtils.isEmpty(mEtName.getText().toString())) {
            Toast.makeText(this, R.string.name_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtClass.getText().toString())) {
            Toast.makeText(this, R.string.class_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtRollNo.getText().toString())) {
            Toast.makeText(this, R.string.roll_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtSub1.getText().toString())) {
            Toast.makeText(this, R.string.sub1_empty, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(mEtSub1.getText().toString()) > 100) {
            Toast.makeText(this, R.string.sub1_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtSub2.getText().toString())) {
            Toast.makeText(this, R.string.sub2_empty, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(mEtSub2.getText().toString()) > 100) {
            Toast.makeText(this, R.string.sub2_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtSub3.getText().toString())) {
            Toast.makeText(this, R.string.sub3_empty, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(mEtSub3.getText().toString()) > 100) {
            Toast.makeText(this, R.string.sub3_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtSub4.getText().toString())) {
            Toast.makeText(this, R.string.sub4_empty, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(mEtSub4.getText().toString()) > 100) {
            Toast.makeText(this, R.string.sub4_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mEtSub5.getText().toString())) {
            Toast.makeText(this, R.string.sub5_empty, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(mEtSub5.getText().toString()) > 100) {
            Toast.makeText(this, R.string.sub5_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
