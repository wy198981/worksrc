package com.example.administrator.myparkingos.model.responseInfo;

import java.util.List;

/**
 * Created by Administrator on 2017-04-11.
 */
public class GetParkDiscountJHSetResp
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
        return "GetParkDiscountJHSetResp{" +
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
        private long ID;//           Y   自增长唯一标识
        private String Address;//      Y   优惠场所
        private double Favorable;//    Y   优惠数量
        private String Manner;//     Y   优惠方式。小时、元、%(表示打多少折)。
        // 如果为打折的方式则Favorable的值应控制在0到100之间。服务端在计算打折金额时，如果Favorable的值小于0则按0算，如果大于100则按100算。


        @Override
        public String toString()
        {
            return "DataBean{" +
                    "ID=" + ID +
                    ", Address='" + Address + '\'' +
                    ", Favorable=" + Favorable +
                    ", Manner='" + Manner + '\'' +
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

        public String getAddress()
        {
            return Address;
        }

        public void setAddress(String address)
        {
            Address = address;
        }

        public double getFavorable()
        {
            return Favorable;
        }

        public void setFavorable(double favorable)
        {
            Favorable = favorable;
        }

        public String getManner()
        {
            return Manner;
        }

        public void setManner(String manner)
        {
            Manner = manner;
        }
    }
}
