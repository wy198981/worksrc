package com.example.administrator.myparkingos.model.responseInfo;

import java.util.List;

/**
 * Created by Administrator on 2017-04-19.
 */
public class UploadCaptureImageResp2
{
    private String rcode; // Y 参考错误码列表
    private String msg; // Y 错误信息
    //    private String[] data; // N 文件路径列表
    private List<String> data;

    @Override
    public String toString()
    {
        return "UploadCaptureImageResp2{" +
                "rcode='" + rcode + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public List<String> getData()
    {
        return data;
    }

    public void setData(List<String> data)
    {
        this.data = data;
    }

    public String getRcode()
    {
        return rcode;
    }

    public void setRcode(String rcode)
    {
        this.rcode = rcode;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
