package com.example.administrator.myparkingos.model.requestInfo;

/**
 * Created by Administrator on 2017-04-14.
 */
public class UpdateChargeAsFreeReq
{
    private String token; // Y   用户登录时候获取的token值
    private String CardNO; // Y   车辆编号。
    private String CardType; // Y   车辆卡类
    private String OutTime; // Y   出场时间。
    private String FreeReason; // Y   免费原因
    private String Reserved; // N   备用字段
    private String CallBack; // N   是否使用JSONP方式。关于JSONP方式请参考Javascript跨域访问一节。

    @Override
    public String toString()
    {
        return "UpdateChargeAsFreeReq{" +
                "token='" + token + '\'' +
                ", CardNO='" + CardNO + '\'' +
                ", CardType='" + CardType + '\'' +
                ", OutTime='" + OutTime + '\'' +
                ", FreeReason='" + FreeReason + '\'' +
                ", Reserved='" + Reserved + '\'' +
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

    public String getCardNO()
    {
        return CardNO;
    }

    public void setCardNO(String cardNO)
    {
        CardNO = cardNO;
    }

    public String getCardType()
    {
        return CardType;
    }

    public void setCardType(String cardType)
    {
        CardType = cardType;
    }

    public String getOutTime()
    {
        return OutTime;
    }

    public void setOutTime(String outTime)
    {
        OutTime = outTime;
    }

    public String getFreeReason()
    {
        return FreeReason;
    }

    public void setFreeReason(String freeReason)
    {
        FreeReason = freeReason;
    }

    public String getReserved()
    {
        return Reserved;
    }

    public void setReserved(String reserved)
    {
        Reserved = reserved;
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
