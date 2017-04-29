package com.example.administrator.myparkingos.model.requestInfo;

/**
 * Created by Administrator on 2017-04-19.
 */
public class UploadCaptureImageReq
{
    private String token ;// Y 用户登录时候获取的token值
    private String StationId ;// Y 工作站编号
    private String Date ;// Y 抓拍日期。如20160910 格式
    private String CallBack ;// N 是否使用JSONP方式。关于JSONP方式请参考Javascript跨域访问一节。

    @Override
    public String toString()
    {
        return "UploadCaptureImageReq{" +
                "token='" + token + '\'' +
                ", StationId='" + StationId + '\'' +
                ", Date='" + Date + '\'' +
                ", CallBack='" + CallBack + '\'' +
                '}';
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getStationId()
    {
        return StationId;
    }

    public void setStationId(String stationId)
    {
        StationId = stationId;
    }

    public String getDate()
    {
        return Date;
    }

    public void setDate(String date)
    {
        Date = date;
    }

    public String getCallBack()
    {
        return CallBack;
    }

    public void setCallBack(String callBack)
    {
        CallBack = callBack;
    }
}
