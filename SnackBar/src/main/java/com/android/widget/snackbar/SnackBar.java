package com.android.widget.snackbar;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
 * date        ：2021/9/16
 * author      ：秦川小将
 * description ：自定义SnackBar
 */
public class SnackBar extends BaseTransientBottomBar<SnackBar> implements SnackBarDispatcher<SnackBar> {

    @ColorInt
    private int backgroundColor;
    private float backgroundCornerSize;
    private boolean backgroundRoundCorner;

    public static SnackBar make(@NonNull AppCompatActivity activity, @Nullable CharSequence msg) {
        return makeInternal(activity.getWindow().getDecorView(), msg);
    }

    public static SnackBar make(@NonNull Fragment fragment, @Nullable CharSequence msg) {
        return makeInternal(fragment.getView(), msg);
    }

    public static SnackBar make(@NonNull Dialog dialog, @Nullable CharSequence msg) {
        return makeInternal(dialog.getWindow().getDecorView(), msg);
    }

    public static SnackBar make(@NonNull View view, @Nullable CharSequence msg) {
        return makeInternal(view, msg);
    }

    public static SnackBar makeInternal(@Nullable View view, @Nullable CharSequence msg) {
        if (view == null) return null;
        ViewGroup parent = findSuitableParent(view);
        if (parent == null) return null;
        SnackBar snackBar = new SnackBar(parent, new SnackBarContentLayout(view.getContext()).setText(msg));
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackBar.getView();
        layout.setPadding(0, 0, 0, 0);
        layout.setBackgroundColor(Color.TRANSPARENT);
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        if (params instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) params).gravity = Gravity.CENTER;
        }
        return snackBar;
    }

    @Nullable
    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof CoordinatorLayout) {
                // We've found a CoordinatorLayout, use it
                return (ViewGroup) view;
            } else if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    // If we've hit the decor content view, then we didn't find a CoL in the
                    // hierarchy, so use it.
                    return (ViewGroup) view;
                } else {
                    // It's not the content view but we'll use it as our fallback
                    fallback = (ViewGroup) view;
                }
            }
            if (view != null) {
                // Else, we will loop and crawl up the view hierarchy and try to find a parent
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);
        // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
        return fallback;
    }

    private SnackBar(@NonNull ViewGroup parent, @NonNull SnackBarContentLayout content) {
        super(parent, content, content);
        this.backgroundColor = Color.BLACK;
        this.backgroundCornerSize = 10f;
    }

    public SnackBar setTextColor(@ColorInt int color) {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) contentLayout.setTextColor(color);
        return this;
    }

    @Override
    public SnackBar setTextSize(float size) {
        return setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    @Override
    public SnackBar setTextSize(int type, float size) {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) contentLayout.setTextSize(type, size);
        return this;
    }

    @Override
    public SnackBar setTypeface(Typeface typeface) {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) contentLayout.setTypeface(typeface);
        return this;
    }

    @Override
    public SnackBar setIcon(@DrawableRes int icon) {
        return setIcon(ContextCompat.getDrawable(getContext(), icon));
    }

    @Override
    public SnackBar setIcon(Drawable drawable) {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) contentLayout.setIcon(drawable);
        return this;
    }

    @Override
    public SnackBar setIconSize(float size) {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) contentLayout.setIconSize(size);
        return this;
    }

    @Override
    public SnackBar setIconColorTint(@ColorInt int color) {
        return setIconColorStateList(ColorStateList.valueOf(color));
    }

    @Override
    public SnackBar setIconColorStateList(ColorStateList colorStateList) {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) contentLayout.setIconColorStateList(colorStateList);
        return this;
    }

    @Override
    public SnackBar setIconGravity(@SnackBarIconGravity int gravity) {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) contentLayout.setIconGravity(gravity);
        return this;
    }

    @Override
    public SnackBar setIconMargin(int margin) {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) contentLayout.setIconMargin(margin);
        return this;
    }

    /**
     * 设置SnackBar背景色
     *
     * @param color
     * @return
     */
    public SnackBar setBackgroundColor(@ColorInt int color) {
        this.backgroundColor = color;
        return this;
    }

    /**
     * true：设置背景为圆角，false：当 {@link SnackBar#backgroundRoundCorner} 为false,背景四个角的弧度取决于 {@link SnackBar#backgroundCornerSize}
     *
     * @param roundCorner
     * @return
     */
    public SnackBar setBackgroundRoundCorner(boolean roundCorner) {
        this.backgroundRoundCorner = roundCorner;
        return this;
    }

    /**
     * 设置背景四个角的弧度
     *
     * @param cornerSize
     * @return
     */
    public SnackBar setAllCornerSize(float cornerSize) {
        this.backgroundCornerSize = cornerSize;
        return this;
    }

    /**
     * 给SnackBar设置一个Drawable背景
     *
     * @param shapeDrawable
     * @return
     */
    public SnackBar setBackgroundDrawable(MaterialShapeDrawable shapeDrawable) {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) contentLayout.setBackground(shapeDrawable);
        return this;
    }

    private MaterialShapeDrawable getBackgroundDrawable(@ColorInt int color, float cornerSize) {
        ShapeAppearanceModel shapeAppearanceModel = ShapeAppearanceModel.builder()
                .setAllCorners(new RoundedCornerTreatment())
                .setAllCornerSizes(cornerSize)
                .build();
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
        shapeDrawable.setTint(color);
        return shapeDrawable;
    }

    @Override
    public void updateView() {
        SnackBarContentLayout contentLayout = getContentLayout();
        if (contentLayout != null) {
            contentLayout.post(() -> {
                float cornerSize = backgroundCornerSize;
                if (backgroundRoundCorner) {
                    cornerSize = contentLayout.getMeasuredHeight() >> 1;
                }
                contentLayout.setBackground(getBackgroundDrawable(backgroundColor, cornerSize));
                contentLayout.updateView();
            });
        }
    }

    private SnackBarContentLayout getContentLayout() {
        try {
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) getView();
            if (layout.getChildCount() > 0 && layout.getChildAt(0) instanceof SnackBarContentLayout) {
                return (SnackBarContentLayout) layout.getChildAt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void show() {
        this.updateView();
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    protected void dispatchDismiss(int event) {
        super.dispatchDismiss(event);
    }
}