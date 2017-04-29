package com.example.administrator.myparkingos.model.beans;

/**
 * Created by Administrator on 2017-04-21.
 */
public class NoPlateCarItem //方便无牌车数据显示
{
/*
    {Binding CardNO                 }" Header="车辆编号">
    {Binding InUser                 }" Header="车辆颜色">
    {Binding SFGate                 }" Header="车辆品牌">
    {Binding CPH                    }" Header="车牌号码">
    {Binding ChineseName            }" Header="车辆类型">
    {Binding InTime                 ,StringFormat=yyyy-MM-dd HH:mm:ss}" Header="入场时间">
    {Binding InGateName             }" Header="入场名称">
    {Binding UserNO                 }" Header="人员编号">
    {Binding UserName               }" Header="人员姓名">
    {Binding Balance                }" Header="余额">
    {Binding CarparkNO              }" Header="车场编号">
    {Binding BigSmall               , Converter={StaticResource cvtBS}, Mode=TwoWay}"  Header="大小标识">
    {Binding FreeReason             }" Header="免费原因">
    {Binding InPic                  }" Header="入场图片">
    {Binding DeptName               }" Header="部门名称">
    {Binding ZJPic                  }" Header="证件图片">
    {Binding InOperatorCard         }" Header="入场操作编号">
    {Binding InOperator             }" Header="入场操作员">
 */
    private String CardNO;
    private String InUser;
    private String SFGate;
    private String CPH;
    private String ChineseName;
    private String InTime;
    private String InGateName;
    private String UserNO;
    private String UserName;
    private String Balance;
    private String CarparkNO;
    private String BigSmall;
    private String FreeReason;
    private String InPic;
    private String DeptName;
    private String ZJPic;
    private String InOperatorCard;
    private String InOperator;

    @Override
    public String toString()
    {
        return "NoPlateCarItem{" +
                "CardNO='" + CardNO + '\'' +
                ", InUser='" + InUser + '\'' +
                ", SFGate='" + SFGate + '\'' +
                ", CPH='" + CPH + '\'' +
                ", ChineseName='" + ChineseName + '\'' +
                ", InTime='" + InTime + '\'' +
                ", InGateName='" + InGateName + '\'' +
                ", UserNO='" + UserNO + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Balance='" + Balance + '\'' +
                ", CarparkNO='" + CarparkNO + '\'' +
                ", BigSmall='" + BigSmall + '\'' +
                ", FreeReason='" + FreeReason + '\'' +
                ", InPic='" + InPic + '\'' +
                ", DeptName='" + DeptName + '\'' +
                ", ZJPic='" + ZJPic + '\'' +
                ", InOperatorCard='" + InOperatorCard + '\'' +
                ", InOperator='" + InOperator + '\'' +
                '}';
    }

    public String getCardNO()
    {
        return CardNO;
    }

    public void setCardNO(String cardNO)
    {
        CardNO = cardNO;
    }

    public String getInUser()
    {
        return InUser;
    }

    public void setInUser(String inUser)
    {
        InUser = inUser;
    }

    public String getSFGate()
    {
        return SFGate;
    }

    public void setSFGate(String SFGate)
    {
        this.SFGate = SFGate;
    }

    public String getCPH()
    {
        return CPH;
    }

    public void setCPH(String CPH)
    {
        this.CPH = CPH;
    }

    public String getChineseName()
    {
        return ChineseName;
    }

    public void setChineseName(String chineseName)
    {
        ChineseName = chineseName;
    }

    public String getInTime()
    {
        return InTime;
    }

    public void setInTime(String inTime)
    {
        InTime = inTime;
    }

    public String getInGateName()
    {
        return InGateName;
    }

    public void setInGateName(String inGateName)
    {
        InGateName = inGateName;
    }

    public String getUserNO()
    {
        return UserNO;
    }

    public void setUserNO(String userNO)
    {
        UserNO = userNO;
    }

    public String getUserName()
    {
        return UserName;
    }

    public void setUserName(String userName)
    {
        UserName = userName;
    }

    public String getBalance()
    {
        return Balance;
    }

    public void setBalance(String balance)
    {
        Balance = balance;
    }

    public String getCarparkNO()
    {
        return CarparkNO;
    }

    public void setCarparkNO(String carparkNO)
    {
        CarparkNO = carparkNO;
    }

    public String getBigSmall()
    {
        return BigSmall;
    }

    public void setBigSmall(String bigSmall)
    {
        BigSmall = bigSmall;
    }

    public String getFreeReason()
    {
        return FreeReason;
    }

    public void setFreeReason(String freeReason)
    {
        FreeReason = freeReason;
    }

    public String getInPic()
    {
        return InPic;
    }

    public void setInPic(String inPic)
    {
        InPic = inPic;
    }

    public String getDeptName()
    {
        return DeptName;
    }

    public void setDeptName(String deptName)
    {
        DeptName = deptName;
    }

    public String getZJPic()
    {
        return ZJPic;
    }

    public void setZJPic(String ZJPic)
    {
        this.ZJPic = ZJPic;
    }

    public String getInOperatorCard()
    {
        return InOperatorCard;
    }

    public void setInOperatorCard(String inOperatorCard)
    {
        InOperatorCard = inOperatorCard;
    }

    public String getInOperator()
    {
        return InOperator;
    }

    public void setInOperator(String inOperator)
    {
        InOperator = inOperator;
    }
}
