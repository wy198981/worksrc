package com.example.administrator.myparkingos.model.responseInfo;

import java.util.List;

/**
 * Created by Administrator on 2017-04-11.
 */
public class GetCardTypeDefResp
{
    private String rcode; // 参考错误码列表
    private String msg; // 错误信息
    private int PageIndex; // 当前页码。仅当查询时指定了分页参数才有此值。
    private int PageSize; // 分页大小。仅当查询时指定了分页参数才有此值。
    private int TotalRows; // 总行数。仅当查询时指定了分页参数才有此值。
    //    Data	参考说明中的描述	N	如果没有指定ExportFields参数则为数据Model数组，否则为下载导出文件的完整URL
    private List<DataBean> data;

    @Override
    public String toString()
    {
        return "GetCardTypeDefResp{" +
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
        private long ID; // Y   自增长唯一标识
        private String CardType; // Y   中文卡类
        private String Identifying; // Y   实际卡类
        private String Remarks; // N   英文卡类
        private boolean Enabled; // Y   是否启用

        public long getID()
        {
            return ID;
        }

        public void setID(long ID)
        {
            this.ID = ID;
        }

        public String getCardType()
        {
            return CardType;
        }

        public void setCardType(String cardType)
        {
            CardType = cardType;
        }

        public String getIdentifying()
        {
            return Identifying;
        }

        public void setIdentifying(String identifying)
        {
            Identifying = identifying;
        }

        public String getRemarks()
        {
            return Remarks;
        }

        public void setRemarks(String remarks)
        {
            Remarks = remarks;
        }

        public boolean isEnabled()
        {
            return Enabled;
        }

        public void setEnabled(boolean enabled)
        {
            Enabled = enabled;
        }

        @Override
        public String toString()
        {
            return "DataBean{" +
                    "ID=" + ID +
                    ", CardType='" + CardType + '\'' +
                    ", Identifying='" + Identifying + '\'' +
                    ", Remarks='" + Remarks + '\'' +
                    ", Enabled=" + Enabled +
                    '}';
        }
    }
}
