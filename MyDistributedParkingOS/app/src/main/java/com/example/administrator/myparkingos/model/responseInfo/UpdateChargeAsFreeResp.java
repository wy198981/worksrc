package com.example.administrator.myparkingos.model.responseInfo;

/**
 * Created by Administrator on 2017-04-14.
 */
public class UpdateChargeAsFreeResp
{
    private String rcode; // Y 参考错误码列表
    private String msg; // Y 错误信息
    private int data; // N 受影响的行数

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

    public int getData()
    {
        return data;
    }

    public void setData(int data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "UpdateChargeAsFreeResp{" +
                "rcode='" + rcode + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
