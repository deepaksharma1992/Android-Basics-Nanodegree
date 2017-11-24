package com.sharma.deepak.quizcard;

import android.content.Context;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Created by deepak on 24-06-2017.
 */

public class AnswersUtility {

    /*
    * @author deepak sharma
    * @date   24-6-2017
    * @description method to check answers for checkbox selection
    *
    */
    public static boolean checkAnswerForSelection(boolean answer, RadioGroup radioGroup) {
        if (answer != (MainActivity.CORRECT)) {
            radioGroup.clearCheck();
        }
        return answer;
    }

    /*
    * @author deepak sharma
    * @date   24-6-2017
    * @description method to check answers for entered text in editText
    *
    */
    public static boolean checkEnteredTextAnswer(Context context, EditText mEtAsyncAnswer) {
        String enteredAnswer = mEtAsyncAnswer.getEditableText().toString().trim();
        if (enteredAnswer.equalsIgnoreCase(context.getString(R.string.one))
                || enteredAnswer.equals(context.getString(R.string.one_num))
                || enteredAnswer.equalsIgnoreCase(context.getString(R.string.one_thread))
                || enteredAnswer.equalsIgnoreCase(context.getString(R.string.one_thread_num))) {

            return MainActivity.CORRECT;
        } else {
            return MainActivity.INCORRECT;
        }
    }
}