package com.example.administrator.myparkingos.constant;

import android.content.Context;
import android.util.ArrayMap;

import com.example.administrator.myparkingos.model.responseInfo.GetCardTypeDefResp;
import com.example.administrator.myparkingos.util.RegexUtil;
import com.example.administrator.myparkingos.util.SPUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
            dicCardType.put(lstCTD.get(i).getCardType(), lstCTD.get(i).getIdentifying());
        }
    }

    public static String GetCardType(String type, int Flag)
    {
        String strRet = "无效卡";
        if (Flag == 0)
        {
            for (String m: dicCardTypeValue.keySet())
            {
                if (m == type)
                {
                    strRet = dicCardTypeValue.get(m);
                }
            }
        }
        else if (Flag == 1)
        {
            for (String m: dicCardType.keySet())
            {
                if (m == type)
                {
                    strRet = dicCardType.get(m);
                }
            }
        }
        return strRet;
    }

    public static Object GetAppConfig(Context context, String key, Object defaultValue)
    {
        return  SPUtils.get(ConstantSharedPrefs.FileAppSetting, context, key, defaultValue);
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
}
