package com.example.administrator.myparkingos.model.responseInfo;

import java.util.Arrays;

/**
 * Created by Administrator on 2017-04-05.
 */
public class SetCarOutWithoutEntryRecordResp
{
    private String rcode;
    private String msg;


    private SetCarOutResp.DataBean data;

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("SetCarOutWithoutEntryRecordResp{");
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

    public SetCarOutResp.DataBean  getData()
    {
        return data;
    }

    public void setData(SetCarOutResp.DataBean  data)
    {
        this.data = data;
    }


}
