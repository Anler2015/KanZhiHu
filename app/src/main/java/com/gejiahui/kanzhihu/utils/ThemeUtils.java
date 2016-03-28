package com.gejiahui.kanzhihu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.gejiahui.kanzhihu.base.MyApplication;
import com.stylingandroid.prism.Prism;

/**
 * Created by gejiahui on 2016/3/25.
 */
public class ThemeUtils {

    private static int defalutThemeColor = Color.rgb(00,119,217);
    private static Context context = MyApplication.getContext();
    public static void setThemeColor( Prism prism,int color){
        prism.setColour(color);
        SharedPreferences.Editor editor = context.getSharedPreferences("ThemeColor",context.MODE_PRIVATE).edit();
        editor.putInt("themeColor",color);
        editor.commit();
    }

    public static int getThemeColor(){
        SharedPreferences pref = context.getSharedPreferences("ThemeColor",context.MODE_PRIVATE);
        return pref.getInt("themeColor",defalutThemeColor);
    }

}
