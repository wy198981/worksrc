package com.example.administrator.myparkingos.model.requestInfo;

/**
 * Created by Administrator on 2017-04-21.
 */
public class OnlinePayReq
{
    private String  token; // Y 用户登录时候获取的token值
    private String  AuthCode; // Y 付款条形码数字
    private Integer  StationId; // Y 工作站编号
    private Integer  PayType; // Y 支付类型。0为微信，1为支付宝。
    private Float  Money; // Y 支付金额
    private String  Message; // Y 交易描述信息
    private String  CPH; // Y 车牌号
    private String  CallBack; // N 是否使用JSONP方式。关于JSONP方式请参考Javascript跨域访问一节。

    @Override
    public String toString()
    {
        return "OnlinePayReq{" +
                "token='" + token + '\'' +
                ", AuthCode='" + AuthCode + '\'' +
                ", StationId=" + StationId +
                ", PayType=" + PayType +
                ", Money=" + Money +
                ", Message='" + Message + '\'' +
                ", CPH='" + CPH + '\'' +
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

    public String getAuthCode()
    {
        return AuthCode;
    }

    public void setAuthCode(String authCode)
    {
        AuthCode = authCode;
    }

    public Integer getStationId()
    {
        return StationId;
    }

    public void setStationId(Integer stationId)
    {
        StationId = stationId;
    }

    public Integer getPayType()
    {
        return PayType;
    }

    public void setPayType(Integer payType)
    {
        PayType = payType;
    }

    public Float getMoney()
    {
        return Money;
    }

    public void setMoney(Float money)
    {
        Money = money;
    }

    public String getMessage()
    {
        return Message;
    }

    public void setMessage(String message)
    {
        Message = message;
    }

    public String getCPH()
    {
        return CPH;
    }

    public void setCPH(String CPH)
    {
        this.CPH = CPH;
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
