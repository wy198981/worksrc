package com.example.administrator.myparkingos.model.responseInfo;

/**
 * Created by Administrator on 2017-04-21.
 */
public class OnlinePayResp
{
    private String rcode; // Y 参考错误码列表
    private String msg; // Y 错误信息
    private Object data; // N 此字段没有用途

    @Override
    public String toString()
    {
        return "OnlinePayResp{" +
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

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
