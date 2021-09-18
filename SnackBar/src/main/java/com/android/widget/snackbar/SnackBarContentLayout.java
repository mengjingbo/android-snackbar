package com.android.widget.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.ContentViewCallback;
import com.google.android.material.textview.MaterialTextView;

/**
 * date        ：2021/9/16
 * author      ：秦川小将
 * description ：自定义SnackBar内容Layout
 */
public class SnackBarContentLayout extends ConstraintLayout implements SnackBarDispatcher<SnackBarContentLayout>, ContentViewCallback {

    private static final int TEXT_ID = View.generateViewId();
    private static final int ICON_ID = View.generateViewId();

    private MaterialTextView textView;
    private AppCompatImageView iconView;

    private int iconMargin = 10;
    @SnackBarIconGravity
    private int iconGravity = SnackBarIconGravity.START;
    private float iconSize = ViewGroup.LayoutParams.WRAP_CONTENT; // 默认icon大小跟随内容

    public SnackBarContentLayout(@NonNull Context context) {
        this(context, null);
    }

    public SnackBarContentLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setContentView(context);
    }

    private void setContentView(@NonNull Context context) {
        int padding = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_horizontal);
        setPadding(padding, padding, padding, padding);
        this.textView = createContentTextView(context);
        addView(textView);
        this.iconView = new AppCompatImageView(context);
        this.iconView.setId(ICON_ID);
        addView(iconView);
    }

    private MaterialTextView createContentTextView(@NonNull Context context) {
        MaterialTextView view = new MaterialTextView(context);
        view.setAlpha(0.87f);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
        view.setIncludeFontPadding(false);
        view.setGravity(Gravity.CENTER);
        view.setTextColor(Color.WHITE);
        view.setId(TEXT_ID);
        return view;
    }

    @Override
    public void animateContentIn(int delay, int duration) {
    }

    @Override
    public void animateContentOut(int delay, int duration) {
    }

    public SnackBarContentLayout setText(CharSequence text) {
        this.textView.setText(text);
        return this;
    }

    @Override
    public SnackBarContentLayout setTextColor(@ColorInt int color) {
        this.textView.setTextColor(color);
        return this;
    }

    @Override
    public SnackBarContentLayout setTypeface(Typeface typeface) {
        this.textView.setTypeface(typeface);
        return this;
    }

    @Override
    public SnackBarContentLayout setTextSize(float size) {
        return setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public SnackBarContentLayout setTextSize(int type, float size) {
        this.textView.setTextSize(type, size);
        return this;
    }

    @Override
    public SnackBarContentLayout setIcon(@DrawableRes int icon) {
        return setIcon(ContextCompat.getDrawable(getContext(), icon));
    }

    @Override
    public SnackBarContentLayout setIcon(Drawable drawable) {
        this.iconView.setImageDrawable(drawable);
        return this;
    }

    @Override
    public SnackBarContentLayout setIconSize(float size) {
        this.iconSize = size;
        return this;
    }

    @Override
    public SnackBarContentLayout setIconColorTint(int color) {
        return setIconColorStateList(ColorStateList.valueOf(color));
    }

    @Override
    public SnackBarContentLayout setIconColorStateList(ColorStateList colorStateList) {
        this.iconView.setImageTintList(colorStateList);
        return this;
    }

    @Override
    public SnackBarContentLayout setIconGravity(@SnackBarIconGravity int gravity) {
        this.iconGravity = gravity;
        return this;
    }

    @Override
    public SnackBarContentLayout setIconMargin(int margin) {
        this.iconMargin = margin;
        return this;
    }

    @Override
    public void updateView() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        if (this.iconView.getDrawable() != null) {
            constraintSet.setVisibility(ICON_ID, View.VISIBLE);
            constraintSet.constrainWidth(ICON_ID, (int) iconSize);
            constraintSet.constrainHeight(ICON_ID, (int) iconSize);
            switch (iconGravity) {
                case SnackBarIconGravity.START:
                    // icon
                    constraintSet.connect(ICON_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(ICON_ID, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.connect(ICON_ID, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                    // text
                    constraintSet.constrainWidth(TEXT_ID, LayoutParams.WRAP_CONTENT);
                    constraintSet.constrainHeight(TEXT_ID, LayoutParams.WRAP_CONTENT);
                    constraintSet.connect(TEXT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(TEXT_ID, ConstraintSet.START, ICON_ID, ConstraintSet.END, iconMargin);
                    constraintSet.connect(TEXT_ID, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                    break;
                case SnackBarIconGravity.TOP:
                    // icon
                    constraintSet.connect(ICON_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(ICON_ID, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.connect(ICON_ID, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    // text
                    constraintSet.constrainWidth(TEXT_ID, LayoutParams.WRAP_CONTENT);
                    constraintSet.constrainHeight(TEXT_ID, LayoutParams.WRAP_CONTENT);
                    constraintSet.connect(TEXT_ID, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.connect(TEXT_ID, ConstraintSet.TOP, ICON_ID, ConstraintSet.BOTTOM, iconMargin);
                    constraintSet.connect(TEXT_ID, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    constraintSet.connect(TEXT_ID, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                    break;
                case SnackBarIconGravity.END:
                    // text
                    constraintSet.constrainWidth(TEXT_ID, LayoutParams.WRAP_CONTENT);
                    constraintSet.constrainHeight(TEXT_ID, LayoutParams.WRAP_CONTENT);
                    constraintSet.connect(TEXT_ID, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.connect(TEXT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(TEXT_ID, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                    // icon
                    constraintSet.connect(ICON_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(ICON_ID, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                    constraintSet.connect(ICON_ID, ConstraintSet.START, TEXT_ID, ConstraintSet.END, iconMargin);
                    break;
                case SnackBarIconGravity.BOTTOM:
                    // text
                    constraintSet.constrainWidth(TEXT_ID, LayoutParams.WRAP_CONTENT);
                    constraintSet.constrainHeight(TEXT_ID, LayoutParams.WRAP_CONTENT);
                    constraintSet.connect(TEXT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(TEXT_ID, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.connect(TEXT_ID, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    // icon
                    constraintSet.connect(ICON_ID, ConstraintSet.TOP, TEXT_ID, ConstraintSet.BOTTOM, iconMargin);
                    constraintSet.connect(ICON_ID, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.connect(ICON_ID, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    break;
            }
        } else {
            // icon
            constraintSet.setVisibility(ICON_ID, View.GONE);
            // text
            constraintSet.constrainWidth(TEXT_ID, LayoutParams.WRAP_CONTENT);
            constraintSet.constrainHeight(TEXT_ID, LayoutParams.WRAP_CONTENT);
            constraintSet.connect(TEXT_ID, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
            constraintSet.connect(TEXT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            constraintSet.connect(TEXT_ID, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
            constraintSet.connect(TEXT_ID, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        }
        constraintSet.applyTo(this);
        requestLayout();
    }
}