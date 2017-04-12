package com.example.administrator.myparkingos.constant;

import com.example.administrator.myparkingos.util.L;

/**
 * Created by Administrator on 2017-04-10.
 */
public enum OpenWayEnum
{
    AutoCutOff(0),
    ConfirmCutOff(1),
    NoCutOff(2);


    private int value;

    private OpenWayEnum(int inValue)
    {
        value = inValue;
    }

    private OpenWayEnum()
    {

    }

    public static OpenWayEnum valueOf(int value)
    {
        switch (value)
        {
            case 0:
                return AutoCutOff;
            case 1:
                return ConfirmCutOff;
            case 2:
                return NoCutOff;
            default:
                L.e("OpenWayEnum value:" + value + "出现错误");
                break;

        }
        return null;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}
