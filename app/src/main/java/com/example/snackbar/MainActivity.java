package com.example.snackbar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.widget.snackbar.SnackBar;
import com.android.widget.snackbar.SnackBarIconGravity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_1).setOnClickListener(v -> {
            SnackBar.make(v, "您还没有优惠券~")
                    .setTextColor(Color.WHITE)
                    .setDuration(SnackBar.LENGTH_SHORT)
                    .show();
        });
        findViewById(R.id.button_2).setOnClickListener(v -> {
            SnackBar.make(v, "您还没有优惠券~")
                    .setTextColor(Color.WHITE)
                    .setDuration(SnackBar.LENGTH_SHORT)
                    .setBackgroundColor(Color.RED)
                    .show();
        });
        findViewById(R.id.button_3).setOnClickListener(v -> {
            SnackBar.make(v, "您还没有优惠券~")
                    .setTextColor(Color.YELLOW)
                    .setDuration(SnackBar.LENGTH_SHORT)
                    .show();
        });
        findViewById(R.id.button_4).setOnClickListener(v -> {
            SnackBar.make(v, "您还没有优惠券~")
                    .setDuration(SnackBar.LENGTH_SHORT)
                    .setBackgroundRoundCorner(true)
                    .show();
        });
        findViewById(R.id.button_5).setOnClickListener(v -> {
            float iconSize = getResources().getDimension(R.dimen.icon_small_size);
            SnackBar.make(v, "抱歉！出现异常了哦~")
                    .setDuration(SnackBar.LENGTH_SHORT)
                    .setIcon(R.drawable.svg_circle_error)
                    .setIconSize(iconSize)
                    .setIconMargin(20)
                    .setIconGravity(SnackBarIconGravity.TOP)
                    .show();
        });
        findViewById(R.id.button_6).setOnClickListener(v -> {
            float iconSize = getResources().getDimension(R.dimen.icon_small_size);
            SnackBar.make(v, "下载完成")
                    .setDuration(SnackBar.LENGTH_SHORT)
                    .setIcon(R.drawable.svg_hook)
                    .setIconColorTint(Color.parseColor("#1AA01F"))
                    .setIconSize(iconSize)
                    .setIconMargin(20)
                    .setIconGravity(SnackBarIconGravity.TOP)
                    .show();
        });
        findViewById(R.id.button_7).setOnClickListener(v -> {
            SnackBar.make(v, "赠送您100万梦想代金券~")
                    .setDuration(SnackBar.LENGTH_SHORT)
                    .setIcon(R.drawable.svg_check_circle)
                    .setIconSize(48)
                    .setIconMargin(10)
                    .setIconGravity(SnackBarIconGravity.START)
                    .setBackgroundRoundCorner(true)
                    .show();
        });
    }
}