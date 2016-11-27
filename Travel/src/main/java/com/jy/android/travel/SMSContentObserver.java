package com.jy.android.travel;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 乖宝宝 on 2016/10/3.
 */
public class SMSContentObserver extends ContentObserver {
    private Context mContext; // 上下文
    private Handler mHandler; // 更新UI线程
    private String code; // 验证码

    public SMSContentObserver(Context context, Handler handler)
    {
        super(handler);
        mContext = context;
        mHandler = handler;
    }
    @Override
    public void onChange(boolean selfChange, Uri uri) {
        if (uri.toString().equals("content://sms/raw"))
        {
            return;
        }

        // 第二次回调 查询收件箱里的内容
        Uri inboxUri = Uri.parse("content://sms/inbox");

        // 按时间顺序排序短信数据库
        Cursor c = mContext.getContentResolver().query(inboxUri, null, null,
                null, "date desc");
        if (c != null)
        {
            if (c.moveToFirst())
            {

                // 获取手机号
                String address = c.getString(c.getColumnIndex("address"));
                // 获取短信内容
                String body = c.getString(c.getColumnIndex("body"));
                // 判断手机号是否为目标号码
                if (!address.equals("1069058316450"))
                {
                    return;
                }

                // 正则表达式截取短信中的6位验证码
                Pattern pattern = Pattern.compile("(\\d{6})");
                Matcher matcher = pattern.matcher(body);

                // 如果找到通过Handler发送给主线程
                if (matcher.find())
                {
                    code = matcher.group(0);
                    mHandler.obtainMessage(1, code).sendToTarget();
                }
            }

        }
        c.close();
    }
}
