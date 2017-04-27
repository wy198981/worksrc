package com.example.administrator.myparkingos.constant;

import com.example.administrator.myparkingos.util.L;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017-03-29.
 */
public class JsonSearchParam
{
    private static final String ENCODE_FORMAT = "UTF-8";
    private static Gson gson = new Gson();


    private static String getSearchParam(Map<String, String> dic, boolean isExclude, boolean isLike)
    {
        if (dic == null)
        {
            return null;
        }

        SelectModel selectModel = new SelectModel();
        Set<String> set = dic.keySet();
        for (String tempStr : set)
        {
            SelectModel.conditions conditions = selectModel.new conditions();
            conditions.setFieldName(tempStr);
            conditions.setOperator(isLike ? (isExclude ? "not like" : "like") : (isExclude ? "<>" : "="));
            conditions.setFieldValue(dic.get(tempStr));
            conditions.setCombinator("and");
            selectModel.getConditions().add(conditions);
        }
        selectModel.setCombinator("and");
        ArrayList<SelectModel> selectList = new ArrayList<>();
        selectList.add(selectModel);

        String s = gson.toJson(selectList); // 转换成String
        return s;
    }

    /**
     * 输入车牌，构建 JsonSearchParam 字符串
     * CPH lick %cph% and cardState=0 or CardState=2
     *
     * @param cph
     * @return
     */
    public static String getWhenGetCardIssue(String cph)
    {
        List<SelectModel> lstSM = new ArrayList<SelectModel>();
        SelectModel sm = new SelectModel();

        sm.getConditions().add(sm.new conditions("CPH", "like", "%" + cph + "%", "and"));
        lstSM.add(sm);

        sm = new SelectModel();
        sm.getConditions().add(sm.new conditions("CardState", "=", 0, "or"));
        sm.getConditions().add(sm.new conditions("CardState", "=", 2, "or"));
        lstSM.add(sm);

        String where = gson.toJson(lstSM);
        return URLEncoder.encode(where);
    }


    public static String getWhenGetOperators(String userNo)
    {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("UserNO", userNo);

        String toJson = getSearchParam(map, false, false);
        return URLEncoder.encode(toJson);
    }

    public static String getWhenGetCheDaoSet(String stationID)
    {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("StationID", stationID);
        String toJson = getSearchParam(map, false, false);
        return URLEncoder.encode(toJson);
    }

    public static String getWhenGetCameraSet(String cameraIp)
    {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("VideoIP", cameraIp);
        String toJson = getSearchParam(map, false, false);
        return URLEncoder.encode(toJson);
    }

    public static String getWhenGetCarOutAndIn(String carParkNo)
    {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("CarparkNO", carParkNo);
        String toJson = getSearchParam(map, false, true);
        return URLEncoder.encode(toJson);
    }

    public static String getWhenGetCarOutAndIn(Map<String, String> map)
    {
        String toJson = getSearchParam(map, false, true);
        return URLEncoder.encode(toJson);
    }

    public static String getWhenSelectComeCPH_Like(String cph, String parkingNO)
    {
        L.i("getWhenSelectComeCPH_Like: cph," + cph + "parkingNO," + parkingNO);
        List<SelectModel> lstSM = new ArrayList<SelectModel>();
        SelectModel sm = new SelectModel();

        sm.getConditions().add(sm.new conditions("CPH", "like", "%" + cph + "%", "and"));
        sm.getConditions().add(sm.new conditions("bigsmall", "=", 0, "and"));
        sm.getConditions().add(sm.new conditions("CarParkNo", "=", parkingNO, "and"));
        lstSM.add(sm);

        String where = gson.toJson(lstSM);
        return URLEncoder.encode(where);// 编码方式，会出现乱码?
    }

    public static String getWhenGetCarTypeDef(String cardType)
    {
        List<SelectModel> lstSM = new ArrayList<SelectModel>();
        SelectModel sm = new SelectModel();

        String expectedCardType = "";
        if (cardType == null || cardType.trim().length() <= 0)
        {
        }
        else
        {
            if (cardType.trim().length() > 3)
            {
                expectedCardType = cardType.trim().substring(0, 3);
            }
            else
            {
                expectedCardType = cardType.trim();
            }
        }
        sm.getConditions().add(sm.new conditions("Enabled", "=", 1, "and"));
        sm.getConditions().add(sm.new conditions("Identifying", "like", expectedCardType + "%", "and"));
        lstSM.add(sm);

        String where = gson.toJson(lstSM);
        return URLEncoder.encode(where);// 编码方式，会出现乱码?
    }

    public static String getTempWhenGetCarTypeDef()
    {
        //查询是否使能，且字段为Identifying的临时车
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("Enabled", "%1%");
        map.put("Identifying", "Tmp%");
        String toJson = getSearchParam(map, false, true);

        String encode;
        try
        {
            encode = URLEncoder.encode(toJson, ENCODE_FORMAT);
            return encode;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMonthWhenGetCarTypeDef()
    {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("Enabled", "1%");
        map.put("Identifying", "Mth%");
        String toJson = getSearchParam(map, false, true);

        String encode;
        try
        {
            encode = URLEncoder.encode(toJson, ENCODE_FORMAT);
            return encode;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 无牌车查询字段
     *
     * @param dtStart
     * @param dtEnd
     * @return
     */
    public static String getWhenGetInNoCPH(Map<String, String> map, String dtStart, String dtEnd, String fieldName)
    {
        List<SelectModel> lstSM = new ArrayList<SelectModel>();
        SelectModel sm = new SelectModel();

        // 且 0为大车场，1为小车场。

        // 查询无牌车 且是大车场 且满足一定的匹配规则 且在一定的时间范围内;
        sm.getConditions().add(sm.new conditions("IsUnlicensed", "=", 1, "and"));
        sm.getConditions().add(sm.new conditions("BigSmall", "=", 0, "and"));

        for (Map.Entry<String, String> temp : map.entrySet())
        {
            sm.getConditions().add(sm.new conditions(temp.getKey(), "like", "%" + temp.getValue() + "%", "and"));
        }

        sm.getConditions().add(sm.new conditions(fieldName, ">=", dtStart, "and"));
        sm.getConditions().add(sm.new conditions(fieldName, "<=", dtEnd, "and"));

        lstSM.add(sm);

        String where = gson.toJson(lstSM);
        return URLEncoder.encode(where);
    }
}
