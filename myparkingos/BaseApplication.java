package com.example.administrator.myparkingos;

import android.app.Application;

import com.android.volley.utils.BuildConfig;
import com.example.administrator.myparkingos.httpUtil.OkGoUtil;
import com.jude.http.RequestManager;

/**
 * Created by Administrator on 2017-04-17.
 */
public class BaseApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        OkGoUtil.INSTATNCE.init(this);
        RequestManager.getInstance().init(this);
        RequestManager.getInstance().setDebugMode(BuildConfig.DEBUG, " net ");
    }
}
