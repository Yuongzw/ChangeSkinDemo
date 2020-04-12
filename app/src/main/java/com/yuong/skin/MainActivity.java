package com.yuong.skin;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;

import com.yuong.skinlibrary.BaseSkinActivity;
import com.yuong.skinlibrary.utils.PreferencesUtils;

public class MainActivity extends BaseSkinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        boolean isNight = PreferencesUtils.getBoolean(this, "isNight");
        if (isNight) {
            setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void skinChange(View view) {
        //固定的API
        int uiMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (uiMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                PreferencesUtils.putBoolean(this, "isNight", true);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                PreferencesUtils.putBoolean(this, "isNight", false);
                break;
            default:
                break;
        }
    }


    @Override
    protected boolean isNeedChangeSkin() {
        return true;
    }
}
