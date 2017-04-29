package com.example.administrator.myparkingos.model.responseInfo;

import java.util.List;

/**
 * Created by Administrator on 2017-04-25.
 */
public class GetLedSettingResp
{
    private String rcode;      // Y 参考错误码列表
    private String msg;        // Y 错误信息
    private int PageIndex; // N 当前页码。仅当查询时指定了分页参数才有此值。
    private int PageSize;  // N 分页大小。仅当查询时指定了分页参数才有此值。
    private int TotalRows; // N 总行数。仅当查询时指定了分页参数才有此值。
    //Data	参考说明中的描述	N	如果没有指定ExportFields参数则为数据Model数组，否则为下载导出文件的完整URL
    private List<DataBean> data;

    @Override
    public String toString()
    {
        return "GetLedSettingResp{" +
                "rcode='" + rcode + '\'' +
                ", msg='" + msg + '\'' +
                ", PageIndex=" + PageIndex +
                ", PageSize=" + PageSize +
                ", TotalRows=" + TotalRows +
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

    public int getPageIndex()
    {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex)
    {
        PageIndex = pageIndex;
    }

    public int getPageSize()
    {
        return PageSize;
    }

    public void setPageSize(int pageSize)
    {
        PageSize = pageSize;
    }

    public int getTotalRows()
    {
        return TotalRows;
    }

    public void setTotalRows(int totalRows)
    {
        TotalRows = totalRows;
    }

    public List<DataBean> getData()
    {
        return data;
    }

    public void setData(List<DataBean> data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        private long ID; // ID 自增长唯一标识
        private int StationID; // StationID 工作站编号
        private int CtrID; // CtrID 主板机号
        private String SurplusID; // SurplusID LED屏机号
        private String Speed; // Speed 移动速度
        private String StopTime; // StopTime 停止时间
        private String Color; // Color 颜色
        private String SumTime; // SumTime 显示时间
        private String CPHEndStr; // CPHEndStr 车牌后缀
        private String ShowWay; // ShowWay 显示方式
        private String Move; // Move 移动方式
        private String Pattern; // Pattern 模式（字数）
        private String StationName; // StationName 参考工作站设置Model同名字段
        private String CarparkNO; // CarparkNO 参考工作站设置Model CarparkNO字段

        @Override
        public String toString()
        {
            return "DataBean{" +
                    "ID=" + ID +
                    ", StationID=" + StationID +
                    ", CtrID=" + CtrID +
                    ", SurplusID='" + SurplusID + '\'' +
                    ", Speed='" + Speed + '\'' +
                    ", StopTime='" + StopTime + '\'' +
                    ", Color='" + Color + '\'' +
                    ", SumTime='" + SumTime + '\'' +
                    ", CPHEndStr='" + CPHEndStr + '\'' +
                    ", ShowWay='" + ShowWay + '\'' +
                    ", Move='" + Move + '\'' +
                    ", Pattern='" + Pattern + '\'' +
                    ", StationName='" + StationName + '\'' +
                    ", CarparkNO='" + CarparkNO + '\'' +
                    '}';
        }

        public long getID()
        {
            return ID;
        }

        public void setID(long ID)
        {
            this.ID = ID;
        }

        public int getStationID()
        {
            return StationID;
        }

        public void setStationID(int stationID)
        {
            StationID = stationID;
        }

        public int getCtrID()
        {
            return CtrID;
        }

        public void setCtrID(int ctrID)
        {
            CtrID = ctrID;
        }

        public String getSurplusID()
        {
            return SurplusID;
        }

        public void setSurplusID(String surplusID)
        {
            SurplusID = surplusID;
        }

        public String getSpeed()
        {
            return Speed;
        }

        public void setSpeed(String speed)
        {
            Speed = speed;
        }

        public String getStopTime()
        {
            return StopTime;
        }

        public void setStopTime(String stopTime)
        {
            StopTime = stopTime;
        }

        public String getColor()
        {
            return Color;
        }

        public void setColor(String color)
        {
            Color = color;
        }

        public String getSumTime()
        {
            return SumTime;
        }

        public void setSumTime(String sumTime)
        {
            SumTime = sumTime;
        }

        public String getCPHEndStr()
        {
            return CPHEndStr;
        }

        public void setCPHEndStr(String CPHEndStr)
        {
            this.CPHEndStr = CPHEndStr;
        }

        public String getShowWay()
        {
            return ShowWay;
        }

        public void setShowWay(String showWay)
        {
            ShowWay = showWay;
        }

        public String getMove()
        {
            return Move;
        }

        public void setMove(String move)
        {
            Move = move;
        }

        public String getPattern()
        {
            return Pattern;
        }

        public void setPattern(String pattern)
        {
            Pattern = pattern;
        }

        public String getStationName()
        {
            return StationName;
        }

        public void setStationName(String stationName)
        {
            StationName = stationName;
        }

        public String getCarparkNO()
        {
            return CarparkNO;
        }

        public void setCarparkNO(String carparkNO)
        {
            CarparkNO = carparkNO;
        }
    }

}
