package com.gejiahui.kanzhihu.utils;

import android.app.Activity;
import android.content.Intent;

import com.gejiahui.kanzhihu.R;

/**
 * Created by gejiahui on 2016/3/25.
 */
public class ThemeUtils {

    public static int sTheme = 1;

    public final static int THEME_DAY = 1;
    public final static int THEME_NIGHT = 2;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity
     * of the same type.
     */
    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {

            case THEME_DAY:
                activity.setTheme(R.style.AppTheme);
                break;
            case THEME_NIGHT:
                activity.setTheme(R.style.NightTheme);
                break;
            default:
                activity.setTheme(R.style.AppTheme);
                break;
        }
    }
}
