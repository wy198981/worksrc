package com.example.administrator.myparkingos.httpUtil;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017-04-17.
 */
public interface HttpCallBackForFile
{
    void onSuccess(File file, Call call, Response response);
}
