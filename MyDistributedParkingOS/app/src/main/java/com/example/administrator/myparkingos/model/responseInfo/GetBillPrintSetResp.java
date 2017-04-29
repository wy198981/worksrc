package com.example.administrator.myparkingos.model.responseInfo;

import java.util.List;

/**
 * Created by Administrator on 2017-04-21.
 */
public class GetBillPrintSetResp
{
    private String rcode;      // Y 参考错误码列表
    private String msg;        // Y 错误信息
    private int PageIndex; // N 当前页码。仅当查询时指定了分页参数才有此值。
    private int PageSize;  // N 分页大小。仅当查询时指定了分页参数才有此值。
    private int TotalRows; // N 总行数。仅当查询时指定了分页参数才有此值。
    //Data	参考说明中的描述	N	如果没有指定ExportFields参数则为数据Model数组，否则为下载导出文件的完整URL
    private List<DataBean> data;

    public List<DataBean> getData()
    {
        return data;
    }

    public void setData(List<DataBean> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "GetBillPrintSetResp{" +
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

    public static class DataBean
    {
        private long ID; // Y 自增长唯一标识
        private int Title; // Y 标题
        private String Ftitle; // Y 副标题
        private String Footer; // Y 表尾

        @Override
        public String toString()
        {
            return "DataBean{" +
                    "ID=" + ID +
                    ", Title=" + Title +
                    ", Ftitle='" + Ftitle + '\'' +
                    ", Footer='" + Footer + '\'' +
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

        public int getTitle()
        {
            return Title;
        }

        public void setTitle(int title)
        {
            Title = title;
        }

        public String getFtitle()
        {
            return Ftitle;
        }

        public void setFtitle(String ftitle)
        {
            Ftitle = ftitle;
        }

        public String getFooter()
        {
            return Footer;
        }

        public void setFooter(String footer)
        {
            Footer = footer;
        }
    }
}
