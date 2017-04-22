package com.example.administrator.myparkingos.httpUtil;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017-04-17.
 */
public interface HttpCallBackForString
{
    void onSuccess(String s, Call call, Response response);
}
