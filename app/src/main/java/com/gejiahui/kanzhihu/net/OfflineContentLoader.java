package com.gejiahui.kanzhihu.net;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.gejiahui.kanzhihu.model.Answer;
import com.gejiahui.kanzhihu.model.Content;
import com.orhanobut.logger.Logger;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created for modern Android compatibility when upstream API is unavailable.
 */
public class OfflineContentLoader {

    private static final String OFFLINE_ASSET_NAME = "offline_answers.json";

    private OfflineContentLoader() {
    }

    public static ArrayList<Answer> load(Context context) {
        ArrayList<Answer> result = new ArrayList<>();
        if (context == null) {
            return result;
        }

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(context.getAssets().open(OFFLINE_ASSET_NAME));
            String json = readFully(inputStream);
            Content content = JSON.parseObject(json, Content.class);
            if (content != null && content.getAnswers() != null) {
                result = Answer.parse(content.getAnswers());
            }
        } catch (Exception e) {
            Logger.e(e, "offline loader failed");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return result;
    }

    private static String readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, read);
        }
        return byteArrayOutputStream.toString("UTF-8");
    }
}
