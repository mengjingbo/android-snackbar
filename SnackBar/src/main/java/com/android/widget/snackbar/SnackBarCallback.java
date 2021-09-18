package com.android.widget.snackbar;

import com.google.android.material.snackbar.BaseTransientBottomBar;

/**
 * date        ：2021/9/18
 * author      ：蒙景博
 * description ：Callback中添加了SnackBar消失动作
 */
public abstract class SnackBarCallback extends BaseTransientBottomBar.BaseCallback<SnackBar> {
    /**
     * Indicates that the SnackBar was dismissed via a swipe.
     */
    public static final int DISMISS_EVENT_SWIPE = BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_SWIPE;
    /**
     * Indicates that the SnackBar was dismissed via an action click.
     */
    public static final int DISMISS_EVENT_ACTION = BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_ACTION;
    /**
     * Indicates that the SnackBar was dismissed via a timeout.
     */
    public static final int DISMISS_EVENT_TIMEOUT = BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_TIMEOUT;
    /**
     * Indicates that the SnackBar was dismissed via a call to {@link com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback#dismiss()}.
     */
    public static final int DISMISS_EVENT_MANUAL = BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_MANUAL;
    /**
     * Indicates that the SnackBar was dismissed from a new SnackBar being shown.
     */
    public static final int DISMISS_EVENT_CONSECUTIVE = BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_CONSECUTIVE;
}
