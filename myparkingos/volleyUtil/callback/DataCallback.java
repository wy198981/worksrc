package com.example.administrator.myparkingos.volleyUtil.callback;


import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

/**
 * Created by zhuchenxi on 15/5/11.
 */
public abstract class DataCallback<T> extends LinkCallback
{

    @Override
    public void onRequest()
    {
        super.onRequest();
    }

    @Override
    public void onSuccess(String s)
    {
        JSONObject jsonObject;
        int status = 0;
        String info = "";
        T data = null;
        try
        {
            Log.i("debug", "debug aaaaaaaaaaaaa" + s);
            jsonObject = new JSONObject(s);
            Log.i("debug", "jsonObject aaaaaaaaaaaaa" + jsonObject.toString());

            status = jsonObject.getInt("status");
            info = jsonObject.getString("info"); // 直接阻塞了

            Log.i("debug", "debug aaaaaaaaaaaaa status:" + status + ",info" + info);
//            if (status == 200)
            {
                Log.i("debug", "debug aaaaaaaaaaaaa");
                Gson gson = new Gson();
                data = gson.fromJson(jsonObject.getString("data"), ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            }
        }
        catch (Exception e)
        {
            error("数据解析错误");
            return;
        }
        result(status, info);
        if (status == 200)
        {
            success(info, data);
        }
        else if (status == 400)
        {
            authorizationFailure();
        }
        else if (status == 0)
        {
            failure(info);
        }
        else
        {
            error(info);
        }
        super.onSuccess(s);
    }

    @Override
    public void onError(String s)
    {
        result(-1, "网络错误");
        error("网络错误");
        super.onError(s);
    }

    public void result(int status, String info)
    {
    }

    public abstract void success(String info, T data);

    public void failure(String info)
    {

    }

    public void authorizationFailure()
    {
    }

    public void error(String errorInfo)
    {

    }

}
