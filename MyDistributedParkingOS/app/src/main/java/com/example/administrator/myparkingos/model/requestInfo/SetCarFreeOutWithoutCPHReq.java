package com.example.administrator.myparkingos.model.requestInfo;

/**
 * Created by Administrator on 2017-04-19.
 */
public class SetCarFreeOutWithoutCPHReq
{
    private String token; // Y 用户登录时候获取的token值
    private Integer CtrlNumber; // Y 车道机号
    private Integer StationId; // Y 工作站编号
    private String CardNO; // Y 车辆编号。由无牌车进场时生成。
    private String CardType; // Y 车辆卡类。场内记录的卡类，服务器根据此卡类找场内记录。
    private String InTime; // Y 进场时间。由无牌车进场时生成。
    private String FreeReason; // N 免费原因
    private String Reserved; // N 备用字段
    private String CallBack; // N 是否使用JSONP方式。关于JSONP方式请参考Javascript跨域访问一节。

    @Override
    public String toString()
    {
        return "SetCarFreeOutWithoutCPHReq{" +
                "token='" + token + '\'' +
                ", CtrlNumber=" + CtrlNumber +
                ", StationId=" + StationId +
                ", CardNO='" + CardNO + '\'' +
                ", CardType='" + CardType + '\'' +
                ", InTime='" + InTime + '\'' +
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

    public Integer getCtrlNumber()
    {
        return CtrlNumber;
    }

    public void setCtrlNumber(Integer ctrlNumber)
    {
        CtrlNumber = ctrlNumber;
    }

    public Integer getStationId()
    {
        return StationId;
    }

    public void setStationId(Integer stationId)
    {
        StationId = stationId;
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

    public String getInTime()
    {
        return InTime;
    }

    public void setInTime(String inTime)
    {
        InTime = inTime;
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
