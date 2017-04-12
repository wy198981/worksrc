package com.example.administrator.myparkingos.constant;

import com.example.administrator.myparkingos.util.L;

/**
 * Created by Administrator on 2017-04-12.
 */
public enum PlateColorEnum
{
    Blue(0),
    Yellow(1),
    White(2),
    Black(3),
    Unknown(4);

    private int colorValue;

    PlateColorEnum(int colorValue)
    {
        this.colorValue = colorValue;
    }

    public int getColorValue()
    {
        return colorValue;
    }

    public void setColorValue(int colorValue)
    {
        this.colorValue = colorValue;
    }

    public static PlateColorEnum valueOf(int value)
    {
        switch (value)
        {
            case 0:
                return Blue;
            case 1:
                return Yellow;
            case 2:
                return White;
            case 3:
                return Black;
            case 4:
                return Unknown;
            default:
                L.e("PlateColorEnum value:" + value + "出现错误");
                break;

        }
        return null;
    }
}
