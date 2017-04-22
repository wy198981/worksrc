package com.example.administrator.myparkingos.model.responseInfo;

/**
 * Created by Administrator on 2017-04-19.
 */
public class SetCarFreeOutWithoutCPHResp
{
    private String rcode; // Y 参考错误码列表
    private String msg; // Y 错误信息
    private DataBean data; // N 出场信息

    @Override
    public String toString()
    {
        return "SetCarFreeOutWithoutCPHResp{" +
                "rcode='" + rcode + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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
        private String OpenMode; // Y 开闸方式。0为自动开闸；1为确认开闸；2为不开闸
        private String CardNO; // Y 车辆编号
        private String CardType; // Y 车辆卡类
        private String ImageName; // Y 图片名称
        private String CPH; // Y 车牌号
        private String InTime; // Y 进场时间
        private String OutTime; // Y 出场时间
        private float SFJE; // Y 实收金额
        private float YSJE; // Y 应收金额
        private String UserNO; // N 用户编号
        private String UserName; // N 用户名称
        private String DeptName; // N 部门名称
        private float Balance; // N 余额
        private String CarValidEndDate; // N 有效期结束日
        private String CarPlace; // N 车位
        private GetParkCPHDiscountSetResp.DataBean DiscountSet; // N 优惠信息。结构请参考车牌优惠数据结构。其中DeptId大于或等于0表示是车牌优惠，否则是优惠方式表中的优惠。

        @Override
        public String toString()
        {
            return "DataBean{" +
                    "OpenMode='" + OpenMode + '\'' +
                    ", CardNO='" + CardNO + '\'' +
                    ", CardType='" + CardType + '\'' +
                    ", ImageName='" + ImageName + '\'' +
                    ", CPH='" + CPH + '\'' +
                    ", InTime='" + InTime + '\'' +
                    ", OutTime='" + OutTime + '\'' +
                    ", SFJE=" + SFJE +
                    ", YSJE=" + YSJE +
                    ", UserNO='" + UserNO + '\'' +
                    ", UserName='" + UserName + '\'' +
                    ", DeptName='" + DeptName + '\'' +
                    ", Balance=" + Balance +
                    ", CarValidEndDate='" + CarValidEndDate + '\'' +
                    ", CarPlace='" + CarPlace + '\'' +
                    ", DiscountSet=" + DiscountSet +
                    '}';
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

        public String getCPH()
        {
            return CPH;
        }

        public void setCPH(String CPH)
        {
            this.CPH = CPH;
        }

        public String getInTime()
        {
            return InTime;
        }

        public void setInTime(String inTime)
        {
            InTime = inTime;
        }

        public String getOutTime()
        {
            return OutTime;
        }

        public void setOutTime(String outTime)
        {
            OutTime = outTime;
        }

        public float getSFJE()
        {
            return SFJE;
        }

        public void setSFJE(float SFJE)
        {
            this.SFJE = SFJE;
        }

        public float getYSJE()
        {
            return YSJE;
        }

        public void setYSJE(float YSJE)
        {
            this.YSJE = YSJE;
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

        public float getBalance()
        {
            return Balance;
        }

        public void setBalance(float balance)
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

        public String getCarPlace()
        {
            return CarPlace;
        }

        public void setCarPlace(String carPlace)
        {
            CarPlace = carPlace;
        }

        public GetParkCPHDiscountSetResp.DataBean getDiscountSet()
        {
            return DiscountSet;
        }

        public void setDiscountSet(GetParkCPHDiscountSetResp.DataBean discountSet)
        {
            DiscountSet = discountSet;
        }
    }
}
