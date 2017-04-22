package com.example.administrator.myparkingos.volleyUtil.callback;

import com.example.administrator.myparkingos.util.L;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2017-04-20.
 */
public class GsonCallback<T> extends LinkCallback
{
    private String mUrl;
    private final Gson mGson;
    private final Class<T> mClass;

    public GsonCallback(Class<T> clazz, Listener listener, String url)
    {
        mCallBack = listener;
        mGson = new Gson();
        mClass = clazz;
        mUrl = url;
    }

    @Override
    public void onRequest()
    {
        super.onRequest();
    }

    @Override
    public void onSuccess(String s)
    {
        super.onSuccess(s);
        if (s == null)
            return;
        if (mCallBack != null)
        {
            mCallBack.success(mUrl, mGson.fromJson(s, mClass));
        }
    }

    @Override
    public void onError(String s)
    {
        super.onError(s);

        if (mCallBack != null)
        {
            mCallBack.error(mUrl, s);
        }
    }

    private Listener mCallBack;
    public interface Listener<T>
    {
        void success(String url, T t);
        void error(String url, String string);
    }

}
