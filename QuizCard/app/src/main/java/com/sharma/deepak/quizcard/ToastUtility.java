package com.sharma.deepak.quizcard;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by deepak on 24-06-2017.
 */

public class ToastUtility {
    private static Toast mToast;
    /*
       * @author deepak sharma
       * @date   24-6-2017
       * @description method to shoe the toast message
       *
       */
    public static void showToast(Context context, String text) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        mToast.show();
    }
}
