package com.example.administrator.myparkingos.volleyUtil.callback;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017-04-20.
 */
public class GsonCallback<T> extends LinkCallback
{
    private Object mData;
    private final Gson mGson;
    private final Class<T> mClass;
    private int mIntParam;

    public GsonCallback(Class<T> clazz, Listener listener, Object data)
    {
        mCallBack = listener;
        mGson = new Gson();
        mClass = clazz;
        mData = data;
    }

    public GsonCallback(Class<T> clazz, Listener listener, Object data, int intParam)
    {
        mCallBack = listener;
        mGson = new Gson();
        mClass = clazz;
        mData = data;
        mIntParam = intParam;
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
            mCallBack.success(mData, mGson.fromJson(s, mClass), mIntParam);
        }
    }

    @Override
    public void onError(String s)
    {
        super.onError(s);

        if (mCallBack != null)
        {
            mCallBack.error(mData, s);
        }
    }

    private Listener mCallBack;
    public interface Listener<T>
    {
        void success(Object data, T t, int paramInt);
        void error(Object data, String string);
    }

}
