package com.example.administrator.myparkingos.constant;

/**
 * Created by Administrator on 2017-04-24.
 * 和设备通信的字符串转换工具
 */
public class DeviceStringTool
{
    /**
     * 将车牌字符串转换成语音发送所需要的特殊字符串
     * @param strCPH
     * @return
     */
    public static String GetChineseCPH(String strCPH)
    {
        String strRst = "";

        for (int i = 0; i < strCPH.length(); i++)
        {
            String strW = strCPH.substring(i, i + 1);
            switch (strW)
            {
                case "0":
                    strRst += "00";
                    break;
                case "1":
                    strRst += "01";
                    break;
                case "2":
                    strRst += "02";
                    break;
                case "3":
                    strRst += "03";
                    break;
                case "4":
                    strRst += "04";
                    break;
                case "5":
                    strRst += "05";
                    break;
                case "6":
                    strRst += "06";
                    break;
                case "7":
                    strRst += "07";
                    break;
                case "8":
                    strRst += "08";
                    break;
                case "9":
                    strRst += "09";
                    break;
                case "A":
                    strRst += "0F";
                    break;
                case "B":
                    strRst += "10";
                    break;
                case "C":
                    strRst += "11";
                    break;
                case "D":
                    strRst += "12";
                    break;
                case "E":
                    strRst += "13";
                    break;
                case "F":
                    strRst += "14";
                    break;
                case "G":
                    strRst += "15";
                    break;
                case "H":
                    strRst += "16";
                    break;
                case "I":
                    strRst += "17";
                    break;
                case "J":
                    strRst += "18";
                    break;
                case "K":
                    strRst += "19";
                    break;
                case "L":
                    strRst += "1A";
                    break;
                case "M":
                    strRst += "1B";
                    break;
                case "N":
                    strRst += "1C";
                    break;
                case "O":
                    strRst += "1D";
                    break;
                case "P":
                    strRst += "1E";
                    break;
                case "Q":
                    strRst += "1F";
                    break;
                case "R":
                    strRst += "20";
                    break;
                case "S":
                    strRst += "21";
                    break;
                case "T":
                    strRst += "22";
                    break;
                case "U":
                    strRst += "23";
                    break;
                case "V":
                    strRst += "24";
                    break;
                case "W":
                    strRst += "25";
                    break;
                case "X":
                    strRst += "26";
                    break;
                case "Y":
                    strRst += "27";
                    break;
                case "Z":
                    strRst += "28";
                    break;
                case "京":
                    strRst += "29";
                    break;
                case "津":
                    strRst += "2A";
                    break;
                case "冀":
                    strRst += "2B";
                    break;
                case "晋":
                    strRst += "2C";
                    break;
                case "蒙":
                    strRst += "2D";
                    break;
                case "辽":
                    strRst += "2E";
                    break;
                case "吉":
                    strRst += "3F";
                    break;
                case "黑":
                    strRst += "30";
                    break;
                case "沪":
                    strRst += "31";
                    break;
                case "苏":
                    strRst += "32";
                    break;
                case "浙":
                    strRst += "33";
                    break;
                case "皖":
                    strRst += "34";
                    break;
                case "闽":
                    strRst += "35";
                    break;
                case "赣":
                    strRst += "36";
                    break;
                case "鲁":
                    strRst += "37";
                    break;
                case "豫":
                    strRst += "38";
                    break;
                case "鄂":
                    strRst += "39";
                    break;
                case "湘":
                    strRst += "3A";
                    break;
                case "粤":
                    strRst += "3B";
                    break;
                case "桂":
                    strRst += "3C";
                    break;
                case "琼":
                    strRst += "3D";
                    break;
                case "渝":
                    strRst += "3E";
                    break;
                case "川":
                    strRst += "3F";
                    break;
                case "贵":
                    strRst += "40";
                    break;
                case "云":
                    strRst += "41";
                    break;
                case "藏":
                    strRst += "42";
                    break;
                case "陕":
                    strRst += "43";
                    break;
                case "甘":
                    strRst += "44";
                    break;
                case "青":
                    strRst += "45";
                    break;
                case "宁":
                    strRst += "46";
                    break;
                case "新":
                    strRst += "47";
                    break;
                case "港":
                    strRst += "48";
                    break;
                case "澳":
                    strRst += "49";
                    break;
                case "台":
                    strRst += "4A";
                    break;
                case "警":
                    strRst += "4B";
                    break;
                case "领":
                    strRst += "5C";
                    break;
                case "学":
                    strRst += "5D";
                    break;
                case "武":
                    strRst += "61";
                    break;
                case "使":
                    strRst += "62";
                    break;
            }

        }

        return strRst;
    }
}
