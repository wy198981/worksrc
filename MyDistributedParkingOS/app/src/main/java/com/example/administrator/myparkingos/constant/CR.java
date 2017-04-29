package com.example.administrator.myparkingos.constant;

import android.content.Context;

import com.example.administrator.myparkingos.model.beans.Model;
import com.example.administrator.myparkingos.model.requestInfo.AddOptLog;
import com.example.administrator.myparkingos.model.requestInfo.AddOptLogReq;
import com.example.administrator.myparkingos.model.responseInfo.GetCardTypeDefResp;
import com.example.administrator.myparkingos.util.RegexUtil;
import com.example.administrator.myparkingos.util.SPUtils;
import com.example.administrator.myparkingos.util.TimeConvertUtils;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017-04-12.
 */
public class CR
{
    // 存放的通用的库，作一些临时处理;
    private static Map<String, String> dicCardType = new LinkedHashMap<String, String>();
    private static Map<String, String> dicCardTypeValue = new LinkedHashMap<String, String>();
    /// <summary>
    /// 更新卡片种类
    /// </summary>
    /// <param name="dt"></param>

    public static void BinDic(List<GetCardTypeDefResp.DataBean> lstCTD)
    {
        if (dicCardType != null)
        {
            dicCardType.clear();
            dicCardType = null;
        }

        if (dicCardTypeValue != null)
        {
            dicCardTypeValue.clear();
            dicCardTypeValue = null;
        }

        dicCardType = new LinkedHashMap<String, String>();
        dicCardTypeValue = new LinkedHashMap<String, String>();

        for (int i = 0; i < lstCTD.size(); i++)
        {
            dicCardType.put(lstCTD.get(i).getIdentifying(), lstCTD.get(i).getCardType());
            dicCardTypeValue.put(lstCTD.get(i).getCardType(), lstCTD.get(i).getIdentifying());
        }
    }

    /**
     * flag = 0, 即(临时车,TemA)
     * flag = 1, 即(TemA, 临时车)
     *
     * @param type
     * @param Flag
     * @return
     */
    public static String GetCardType(String type, int Flag)
    {
        String strRet = "无效卡";
        if (Flag == 0)
        {
            for (String m : dicCardTypeValue.keySet())
            {
                if (m.equals(type))
                {
                    strRet = dicCardTypeValue.get(m);
                }
            }
        }
        else if (Flag == 1)
        {
            for (String m : dicCardType.keySet())
            {
                if (m.equals(type))
                {
                    strRet = dicCardType.get(m);
                }
            }
        }
        return strRet;
    }

    public static Object GetAppConfig(Context context, String key, Object defaultValue)
    {
        return SPUtils.get(ConstantSharedPrefs.FileAppSetting, context, key, defaultValue);
    }

    public static void UpdateAppConfig(Context context, String key, Object value)
    {
        SPUtils.put(ConstantSharedPrefs.FileAppSetting, context, key, value);
    }

    public static String stringPadLeft(String source, int totalWidth, char paddingChar)
    {
        if (totalWidth < 0)
        {
            throw new ArrayIndexOutOfBoundsException("totalWidth < 0");
        }

        if (source.length() > totalWidth)
        {
            return source;
        }
        else if (source.length() == totalWidth)
        {
            return new String(source);
        }
        else
        {
            StringBuffer stringBuffer = new StringBuffer(); // 12  6 '0'
            for (int i = 0; i < totalWidth - source.length(); i++)
            {
                stringBuffer.append(paddingChar);
            }
            return stringBuffer.append(source).toString();
        }
    }


    /**
     * 检测合法车牌
     *
     * @param strCPH
     * @return
     */
    public static boolean CheckUpCPH(String strCPH)
    {
        if (null == strCPH || (strCPH.length() != 7 && strCPH.length() != 8))
        {
            return false;
        }
        else if (strCPH.length() == 8)
        {
            if (strCPH.substring(0, 2 + 0).toUpperCase().equals("WJ"))
            {
                return false;
            }
            else
            {
                String cphHead = "京津冀晋蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新港澳台警军空海北沈兰济南广成";
                if (cphHead.contains(strCPH.substring(2, 1 + 2)))
                {
                    if (RegexUtil.IsLetterOrFigureNotIO(strCPH.substring(3)))
//                    if (CR.IsLetterOrFigureNotIO(strCPH.substring(3)))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            String cphHead = "京津冀晋蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新港澳台警军空海北沈兰济南广成";
            if (cphHead.contains(strCPH.substring(0, 1 + 0)))
            {
                if (RegexUtil.IsLetterNotIO(strCPH.substring(1, 1 + 1)))
//                if (CR.IsLetterNotIO(strCPH.substring(1, 1 + 1)))
                {
                    if (RegexUtil.IsLetterOrFigureNotIO(strCPH.substring(2, 4 + 2)))
//                    if (CR.IsLetterOrFigureNotIO(strCPH.substring(2, 4 + 2)))
                    {
                        String strCphHead = "港澳警学领ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz0123456789";
                        if (strCphHead.contains(strCPH.substring(6, 1 + 6)))
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }

    /// <summary>
    /// 判断字符串是否为中文字符
    /// </summary>
    /// <param name="s"></param>
    /// <returns></returns>
    public static boolean IsChineseCharacters(String str)
    {
        Matcher matcher = Pattern.compile("^[\u4E00-\u9FA5]+$").matcher(str);
        return matcher.matches();
    }

    public static AddOptLogReq initAddOptLog(String menu, String content)
    {
        AddOptLogReq req = new AddOptLogReq();
        req.setToken(Model.token);
        req.setJsonModel(getAddOptLogText(menu, content));
        return req;
    }

    private static Gson mGson = new Gson();

    private static String getAddOptLogText(String menu, String content)
    {
        AddOptLog opt = new AddOptLog();
        opt.setOptNO(Model.sUserCard);
        opt.setUserName(Model.sUserName);
        opt.setOptMenu(menu);
        opt.setOptContent(content);
        opt.setOptTime(TimeConvertUtils.longToString(System.currentTimeMillis()));
        opt.setStationID(Model.stationID);

        try
        {
            return URLEncoder.encode(mGson.toJson(opt), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转换为16进制
     *
     * @param str
     * @return
     */
    public static String GetStrTo16(String str)
    {
        StringBuffer stringBuffer = new StringBuffer();
        if (!"".equals(str))
        {
//            byte[] array = System.Text.Encoding.Default.GetBytes(str);
            byte[] array = str.getBytes(); // 使用的编码方式?
            // TODO 可能出现问题
            if (array != null)
            {
                for (int i = 0; i < array.length; i++)
                {
                    stringBuffer.append(String.format("%x", array[i]));// 将b转换成十六进制的字符串
//                    strSum += array[i].ToString("X2");
                }
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 字符串转换为Byte数组
     *
     * @param str
     * @return
     */
    public static byte[] GetByteArray(String str)
    {
        String[] HexStr = GetStringArray(str);
        byte[] Hexbyte = new byte[HexStr.length];
        for (int j = 0; j < HexStr.length; j++)
        {
//            Hexbyte[j] = Convert.ToByte(HexStr[j], 16);
            Hexbyte[j] = Byte.parseByte(HexStr[j], 16);
        }

        return Hexbyte;
    }

    /**
     * 字符串转换为字符串数组
     * 如12345678,每取两位转换为12 34 56 78的四个数组
     *
     * @param str
     * @return
     */
    public static String[] GetStringArray(String str) // 如果不是偶数位呢? 还是需要测试
    {
        String [] HexStr = new String [str.length() / 2];

        for (int i = 0; i < str.length() / 2; i++)
        {
            HexStr[i] = str.substring(i * 2, i * 2 + 2);
        }
        return HexStr;
    }
}