package com.example.administrator.myparkingos.httpUtil;

import android.graphics.Bitmap;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Administrator on 2017-04-17.
 */
public interface HttpCallBackForBitmap
{
    void onSuccess(Bitmap bitmap, Call call, Response response);
}
