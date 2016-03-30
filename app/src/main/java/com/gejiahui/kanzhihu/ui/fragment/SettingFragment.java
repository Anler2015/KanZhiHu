package com.gejiahui.kanzhihu.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;

import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.net.RequestManager;
import com.gejiahui.kanzhihu.utils.AppInfoUtils;
import com.gejiahui.kanzhihu.utils.ToastUtils;

/**
 * Created by gejiahui on 2016/3/30.
 */
public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private Preference clearCache;
    private Preference aboutApp;
    private Preference appVersion;
    private Preference thanks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

        clearCache = findPreference("clear_cache");
        appVersion = findPreference("app_version");
        aboutApp = findPreference("about_app");
        thanks = findPreference("thanks");

        appVersion.setSummary(AppInfoUtils.getVersionName(getActivity()));
        clearCache.setOnPreferenceClickListener(this);
        aboutApp.setOnPreferenceClickListener(this);
        thanks.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        switch (preference.getKey()) {
            case "clear_cache":
                RequestManager.clearCache();
                ToastUtils.show("缓存清除成功");
                break;
            case "about_app":
                AlertDialog.Builder aboutMeDialog = new AlertDialog.Builder(getActivity());
                aboutMeDialog.setTitle("关于作者")
                        .setMessage("我是Anler，这是我的第一个android开源应用，欢迎Star。\n" +
                                    "欢迎联系作者，邮箱：hippogjh7758258@qq.com")
                        .setPositiveButton("GitHub", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Anler2015")));
                            }
                        })
                        .setNegativeButton("发送邮件", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri uri = Uri.parse("mailto:hippogjh7758258@qq.com");
                                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                                intent.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分"); // 主题
//                                intent.putExtra(Intent.EXTRA_TEXT, "这是邮件的正文部分"); // 正文
                                startActivity(Intent.createChooser(intent, "请选择邮件类应用"));

                            }
                        })
                        .show();
                break;
            case "thanks":
                AlertDialog.Builder thanksDialog = new AlertDialog.Builder(getActivity());
                thanksDialog.setTitle("感谢")
                        .setMessage("由衷感谢苏莉安提供的看知乎的API。")
                        .setPositiveButton("关闭", null)
                        .show();
                break;
        }

        return true;
    }
}
