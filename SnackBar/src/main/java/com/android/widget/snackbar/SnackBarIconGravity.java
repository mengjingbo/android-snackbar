package com.android.widget.snackbar;

import android.view.Gravity;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * date        ：2021/9/16
 * author      ：秦川小将
 * description ：设置SnackBar内容icon位置
 */
@IntDef(
        flag = true,
        value = {
                SnackBarIconGravity.START,
                SnackBarIconGravity.TOP,
                SnackBarIconGravity.END,
                SnackBarIconGravity.BOTTOM
        }
)
@Retention(RetentionPolicy.SOURCE)
public @interface SnackBarIconGravity {

    int START = Gravity.START;
    int TOP = Gravity.TOP;
    int END = Gravity.END;
    int BOTTOM = Gravity.BOTTOM;
}