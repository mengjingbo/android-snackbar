package com.android.widget.snackbar;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

/**
 * date        ：2021/9/18
 * author      ：蒙景博
 * description ：
 */
public interface SnackBarDispatcher<T> {

    /**
     * 设置TextColor
     * @param color
     * @return
     */
    T setTextColor(@ColorInt int color);

    /**
     * 设置Text大小
     * @param size 默认为 {@link android.util.TypedValue#COMPLEX_UNIT_SP} 类型
     * @return
     */
    T setTextSize(float size);

    /**
     * 设置Text大小
     * @param type
     * {@link android.util.TypedValue#COMPLEX_UNIT_PX}
     * {@link android.util.TypedValue#COMPLEX_UNIT_DIP}
     * {@link android.util.TypedValue#COMPLEX_UNIT_SP}
     * {@link android.util.TypedValue#COMPLEX_UNIT_PT}
     * {@link android.util.TypedValue#COMPLEX_UNIT_IN}
     * {@link android.util.TypedValue#COMPLEX_UNIT_MM}
     * @param size
     * @return
     */
    T setTextSize(int type, float size);

    /**
     * 设置 Text Typeface
     * @param typeface
     * @return
     */
    T setTypeface(Typeface typeface);

    /**
     * 设置Icon
     * @param icon
     * @return
     */
    T setIcon(@DrawableRes int icon);

    /**
     * 设置Icon
     * @param drawable
     * @return
     */
    T setIcon(Drawable drawable);

    /**
     * 设置Icon View大小
     * @param size
     * @return
     */
    T setIconSize(float size);

    /**
     * 为Icon设置一个Color
     * @param color
     * @return
     */
    T setIconColorTint(@ColorInt int color);

    /**
     * 为Icon设置一个ColorStateList
     * @param colorStateList
     * @return
     */
    T setIconColorStateList(ColorStateList colorStateList);

    /**
     * 设置Icon位置，请查看{@link SnackBarIconGravity}
     * @param gravity
     * @return
     */
    T setIconGravity(@SnackBarIconGravity int gravity);

    /**
     * 设置Icon与Text间距
     * @param margin
     * @return
     */
    T setIconMargin(int margin);

    /**
     * 更新 {@link SnackBarContentLayout} 内容
     */
    void updateView();
}
