package com.example.administrator.myparkingos.model.responseInfo;

import java.util.List;

/**
 * Created by Administrator on 2017-04-18.
 */
public class GetParkCPHDiscountSetResp
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
        return "GetParkCPHDiscountSetResp{" +
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
        private Long  ID; // Y 自增长唯一标识
        private Long  DeptId; // Y 部门Id。打折车牌只有本部门的人才能查看和修改。
        private String  DeptName; // N 参考部门数据结构同名字段
        private String  CPH; // Y 车牌号
        private String  Address; // Y 优惠场所
        private float  Favorable; // Y 优惠数量(金额或小时)
        private String  Manner; // Y 优惠方式。小时、元、%(表示打多少折)。如果为打折的方式则Favorable的值应控制在0到100之间。服务端在计算打折金额时，如果Favorable的值小于0则按0算，如果大于100则按100算。
        private int  Status; // Y 以打8.5折为例：
        private String  OptTime; // Y Manner = %
        private String  OperatorName; // N Favorable = 85
        private String  ValidEndTime; // N 状态。0为新增，1为已优惠，2为因互斥优惠生效而失效，3为因删除而失效(系统不会删除记录，只是打上删除标识)

        @Override
        public String toString()
        {
            return "DataBean{" +
                    "ID=" + ID +
                    ", DeptId=" + DeptId +
                    ", DeptName='" + DeptName + '\'' +
                    ", CPH='" + CPH + '\'' +
                    ", Address='" + Address + '\'' +
                    ", Favorable=" + Favorable +
                    ", Manner='" + Manner + '\'' +
                    ", Status=" + Status +
                    ", OptTime='" + OptTime + '\'' +
                    ", OperatorName='" + OperatorName + '\'' +
                    ", ValidEndTime='" + ValidEndTime + '\'' +
                    '}';
        }

        public Long getID()
        {
            return ID;
        }

        public void setID(Long ID)
        {
            this.ID = ID;
        }

        public Long getDeptId()
        {
            return DeptId;
        }

        public void setDeptId(Long deptId)
        {
            DeptId = deptId;
        }

        public String getDeptName()
        {
            return DeptName;
        }

        public void setDeptName(String deptName)
        {
            DeptName = deptName;
        }

        public String getCPH()
        {
            return CPH;
        }

        public void setCPH(String CPH)
        {
            this.CPH = CPH;
        }

        public String getAddress()
        {
            return Address;
        }

        public void setAddress(String address)
        {
            Address = address;
        }

        public float getFavorable()
        {
            return Favorable;
        }

        public void setFavorable(float favorable)
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

        public int getStatus()
        {
            return Status;
        }

        public void setStatus(int status)
        {
            Status = status;
        }

        public String getOptTime()
        {
            return OptTime;
        }

        public void setOptTime(String optTime)
        {
            OptTime = optTime;
        }

        public String getOperatorName()
        {
            return OperatorName;
        }

        public void setOperatorName(String operatorName)
        {
            OperatorName = operatorName;
        }

        public String getValidEndTime()
        {
            return ValidEndTime;
        }

        public void setValidEndTime(String validEndTime)
        {
            ValidEndTime = validEndTime;
        }
    }
}
