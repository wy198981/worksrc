package com.example.administrator.myparkingos.model.requestInfo;

/**
 * Created by Administrator on 2017-04-21.
 */
public class GetCaptureImageReq
{
    private String token; // Y 用户登录时候获取的token值
    private String FileName; // Y 文件路径。通过上传接口返回的路径。

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getFileName()
    {
        return FileName;
    }

    public void setFileName(String fileName)
    {
        FileName = fileName;
    }
}
