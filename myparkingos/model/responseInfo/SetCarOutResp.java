package com.example.administrator.myparkingos.model.responseInfo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017-04-01.
 */
public class SetCarOutResp
{
    private String rcode;
    private String msg;

    private DataBean data;

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("SetCarOutResp{");
        sb.append("rcode='").append(rcode).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
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

    public DataBean getData()
    {
        return data;
    }

    public void setData(DataBean data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        private String CPH;                  //  Y 车牌号
        private int CtrlNumber;          //  Y 车道机号
        private int StationId;           //  Y 工作站编号
        private int CPColor;             //  N 车牌颜色。0为蓝色，1为黄色，2为白色，3为黑色，4为未识别
        private String Reserved;             //  N 备用字段
        private String InOutName;            //  N 车道名称
        private String BlackReason;          //  N 黑名单原因
        private String UserNO;               //  N 用户编号
        private String UserName;             //  N 用户名称
        private String DeptName;             //  N 部门名称
        private double Balance;              //  N 余额
        private String CarValidEndDate;      //  N 有效期结束日
        private int RemainingDays;       //  N 剩余有效天数
        private String CarPlace;             //  N 车位
        private int PersonalPlaceCount;  //  N 个人车位数量
        private int RemainingPlaceCount;  //  N 剩余车位数
        private String OpenMode;             //  N 开闸方式。0为自动开闸；1为确认开闸；2为不开闸
        private String CardNO;               //  N 车辆编号
        private String CardType;             //  N 车辆卡类
        private String ImageName;            //  N 图片名称
        private String ImagePath;            //  N 图片路径(服务器上的相对路径)
        private String OutTime;              //  N 出场时间
        private String StayTime;             //  N 在车场内的停留时间
        private List<DataBeanInner> SimilarCPHInPark;   //  N 场内相似车牌记录的信息对象列表。当按参数CPH传入的车牌号找不到场内记录时，此字段中保存场内相似车牌记录的对象数组。数组中的对象结构参考下面的说明。
//        private String[] SimilarCPHIssued;   //  N 发行表类似车牌列表。当按参数CPH传入的车牌号找不到场内记录时，此字段中保存发行表中相似的车牌的数组。

        private List<String> SimilarCPHIssued;

        // 4_12自己添加的
        private String InTime;
        private double SFJE;

        public String getInTime()
        {
            return InTime;
        }

        public void setInTime(String inTime)
        {
            InTime = inTime;
        }

        public double getSFJE()
        {
            return SFJE;
        }

        public void setSFJE(double SFJE)
        {
            this.SFJE = SFJE;
        }

        public double getYSJE()
        {
            return YSJE;
        }

        public void setYSJE(double YSJE)
        {
            this.YSJE = YSJE;
        }

        public Object getDiscountSet()
        {
            return DiscountSet;
        }

        public void setDiscountSet(Object discountSet)
        {
            DiscountSet = discountSet;
        }

        private double YSJE;
        private Object DiscountSet;

        @Override
        public String toString()
        {
            return "DataBean{" +
                    "CPH='" + CPH + '\'' +
                    ", CtrlNumber=" + CtrlNumber +
                    ", StationId=" + StationId +
                    ", CPColor=" + CPColor +
                    ", Reserved='" + Reserved + '\'' +
                    ", InOutName='" + InOutName + '\'' +
                    ", BlackReason='" + BlackReason + '\'' +
                    ", UserNO='" + UserNO + '\'' +
                    ", UserName='" + UserName + '\'' +
                    ", DeptName='" + DeptName + '\'' +
                    ", Balance=" + Balance +
                    ", CarValidEndDate='" + CarValidEndDate + '\'' +
                    ", RemainingDays=" + RemainingDays +
                    ", CarPlace='" + CarPlace + '\'' +
                    ", PersonalPlaceCount=" + PersonalPlaceCount +
                    ", RemainingPlaceCount=" + RemainingPlaceCount +
                    ", OpenMode='" + OpenMode + '\'' +
                    ", CardNO='" + CardNO + '\'' +
                    ", CardType='" + CardType + '\'' +
                    ", ImageName='" + ImageName + '\'' +
                    ", ImagePath='" + ImagePath + '\'' +
                    ", OutTime='" + OutTime + '\'' +
                    ", StayTime='" + StayTime + '\'' +
                    ", SimilarCPHInPark=" + SimilarCPHInPark +
                    ", SimilarCPHIssued=" + SimilarCPHIssued +
                    ", InTime='" + InTime + '\'' +
                    ", SFJE=" + SFJE +
                    ", YSJE=" + YSJE +
                    ", DiscountSet=" + DiscountSet +
                    '}';
        }

        public String getCPH()
        {
            return CPH;
        }

        public void setCPH(String CPH)
        {
            this.CPH = CPH;
        }

        public int getCtrlNumber()
        {
            return CtrlNumber;
        }

        public void setCtrlNumber(int ctrlNumber)
        {
            CtrlNumber = ctrlNumber;
        }

        public int getStationId()
        {
            return StationId;
        }

        public void setStationId(int stationId)
        {
            StationId = stationId;
        }

        public int getCPColor()
        {
            return CPColor;
        }

        public void setCPColor(int CPColor)
        {
            this.CPColor = CPColor;
        }

        public String getReserved()
        {
            return Reserved;
        }

        public void setReserved(String reserved)
        {
            Reserved = reserved;
        }

        public String getInOutName()
        {
            return InOutName;
        }

        public void setInOutName(String inOutName)
        {
            InOutName = inOutName;
        }

        public String getBlackReason()
        {
            return BlackReason;
        }

        public void setBlackReason(String blackReason)
        {
            BlackReason = blackReason;
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

        public String getDeptName()
        {
            return DeptName;
        }

        public void setDeptName(String deptName)
        {
            DeptName = deptName;
        }

        public double getBalance()
        {
            return Balance;
        }

        public void setBalance(double balance)
        {
            Balance = balance;
        }

        public String getCarValidEndDate()
        {
            return CarValidEndDate;
        }

        public void setCarValidEndDate(String carValidEndDate)
        {
            CarValidEndDate = carValidEndDate;
        }

        public int getRemainingDays()
        {
            return RemainingDays;
        }

        public void setRemainingDays(int remainingDays)
        {
            RemainingDays = remainingDays;
        }

        public String getCarPlace()
        {
            return CarPlace;
        }

        public void setCarPlace(String carPlace)
        {
            CarPlace = carPlace;
        }

        public int getPersonalPlaceCount()
        {
            return PersonalPlaceCount;
        }

        public void setPersonalPlaceCount(int personalPlaceCount)
        {
            PersonalPlaceCount = personalPlaceCount;
        }

        public int getRemainingPlaceCount()
        {
            return RemainingPlaceCount;
        }

        public void setRemainingPlaceCount(int remainingPlaceCount)
        {
            RemainingPlaceCount = remainingPlaceCount;
        }

        public String getOpenMode()
        {
            return OpenMode;
        }

        public void setOpenMode(String openMode)
        {
            OpenMode = openMode;
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

        public String getImageName()
        {
            return ImageName;
        }

        public void setImageName(String imageName)
        {
            ImageName = imageName;
        }

        public String getImagePath()
        {
            return ImagePath;
        }

        public void setImagePath(String imagePath)
        {
            ImagePath = imagePath;
        }

        public String getOutTime()
        {
            return OutTime;
        }

        public void setOutTime(String outTime)
        {
            OutTime = outTime;
        }

        public String getStayTime()
        {
            return StayTime;
        }

        public void setStayTime(String stayTime)
        {
            StayTime = stayTime;
        }

        public List<DataBeanInner> getSimilarCPHInPark()
        {
            return SimilarCPHInPark;
        }

        public void setSimilarCPHInPark(List<DataBeanInner> similarCPHInPark)
        {
            SimilarCPHInPark = similarCPHInPark;
        }

        public List<String> getSimilarCPHIssued()
        {
            return SimilarCPHIssued;
        }

        public void setSimilarCPHIssued(List<String> similarCPHIssued)
        {
            SimilarCPHIssued = similarCPHIssued;
        }
    }

    public static class DataBeanInner
    {
        private String CPH;   // Y 车牌号
        private int CPColor;  // N 车牌颜色。0为蓝色，1为黄色，2为白色，3为黑色，4为未识别
        private String InPic; // N 入场图片路径

        @Override
        public String toString()
        {
            final StringBuffer sb = new StringBuffer("DataBeanInner{");
            sb.append("CPH='").append(CPH).append('\'');
            sb.append(", CPColor=").append(CPColor);
            sb.append(", InPic='").append(InPic).append('\'');
            sb.append('}');
            return sb.toString();
        }

        public String getCPH()
        {
            return CPH;
        }

        public void setCPH(String CPH)
        {
            this.CPH = CPH;
        }

        public int getCPColor()
        {
            return CPColor;
        }

        public void setCPColor(int CPColor)
        {
            this.CPColor = CPColor;
        }

        public String getInPic()
        {
            return InPic;
        }

        public void setInPic(String inPic)
        {
            InPic = inPic;
        }
    }
}
