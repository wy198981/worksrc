package com.example.administrator.myparkingos.model.requestInfo;

/**
 * Created by Administrator on 2017-04-19.
 */
public class SetCarOutWithoutCPHReq
{
    private String token; // Y 用户登录时候获取的token值
    private Integer CtrlNumber; // Y 车道机号
    private Integer StationId; // Y 工作站编号
    private String CardNO; // Y 车辆编号。由无牌车进场时生成。
    private String CardType; // Y 车辆卡类。场内记录的卡类，服务器根据此卡类找场内记录。
    private String InTime; // Y 进场时间。由无牌车进场时生成。
    private String NewCardType; // Y 新的车辆卡类。出场时指定的卡类，服务器根据此卡类计算费用。
    private Float YSJE; // Y 客户端计算出来的应收金额 (这里没有小数了吗? 原来的是integer)
    private Float SFJE; // Y 客户端计算出来的收费金额
    private Integer PayType; // Y 支付类型。0为现金，1为微信，2为支付宝。
    private String PayCode; // N 付款条形码数字
    private Integer DiscountSetId; // N 优惠记录Id
    private String Reserved; // N 备用字段
    private String CallBack; // N 是否使用JSONP方式。关于JSONP方式请参考Javascript跨域访问一节。

    // 文档中没有，自己添加的
    private String CPH; //

    public String getCPH()
    {
        return CPH;
    }

    public void setCPH(String CPH)
    {
        this.CPH = CPH;
    }

    @Override
    public String toString()
    {
        return "SetCarOutWithoutCPHReq{" +
                "token='" + token + '\'' +
                ", CtrlNumber=" + CtrlNumber +
                ", StationId=" + StationId +
                ", CardNO='" + CardNO + '\'' +
                ", CardType='" + CardType + '\'' +
                ", InTime='" + InTime + '\'' +
                ", NewCardType='" + NewCardType + '\'' +
                ", YSJE=" + YSJE +
                ", SFJE=" + SFJE +
                ", PayType=" + PayType +
                ", PayCode='" + PayCode + '\'' +
                ", DiscountSetId=" + DiscountSetId +
                ", Reserved='" + Reserved + '\'' +
                ", CallBack='" + CallBack + '\'' +
                ", CPH='" + CPH + '\'' +
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
        this.InTime = inTime;
    }

    public String getNewCardType()
    {
        return NewCardType;
    }

    public void setNewCardType(String newCardType)
    {
        NewCardType = newCardType;
    }

    public Float getYSJE()
    {
        return YSJE;
    }

    public void setYSJE(Float YSJE)
    {
        this.YSJE = YSJE;
    }

    public Float getSFJE()
    {
        return SFJE;
    }

    public void setSFJE(Float SFJE)
    {
        this.SFJE = SFJE;
    }

    public Integer getPayType()
    {
        return PayType;
    }

    public void setPayType(Integer payType)
    {
        PayType = payType;
    }

    public String getPayCode()
    {
        return PayCode;
    }

    public void setPayCode(String payCode)
    {
        PayCode = payCode;
    }

    public Integer getDiscountSetId()
    {
        return DiscountSetId;
    }

    public void setDiscountSetId(Integer discountSetId)
    {
        DiscountSetId = discountSetId;
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
