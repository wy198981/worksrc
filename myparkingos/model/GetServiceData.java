package com.example.administrator.myparkingos.model;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;

import com.example.administrator.myparkingos.model.beans.Model;
import com.example.administrator.myparkingos.model.requestInfo.AddOptLog;
import com.example.administrator.myparkingos.model.requestInfo.AddOptLogReq;
import com.example.administrator.myparkingos.model.requestInfo.CaclChargeAmountReq;
import com.example.administrator.myparkingos.model.requestInfo.CancelChargeReq;
import com.example.administrator.myparkingos.model.requestInfo.CarPlateNumberLikeReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCarInReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCarOutReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCardIssueReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCardTypeDefReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCheDaoSetReq;
import com.example.administrator.myparkingos.model.requestInfo.GetFreeReasonReq;
import com.example.administrator.myparkingos.model.requestInfo.GetNetCameraSetReq;
import com.example.administrator.myparkingos.model.requestInfo.GetOperatorsReq;
import com.example.administrator.myparkingos.model.requestInfo.GetOperatorsWithoutLoginReq;
import com.example.administrator.myparkingos.model.requestInfo.GetParkDiscountJHSetReq;
import com.example.administrator.myparkingos.model.requestInfo.GetParkingInfoReq;
import com.example.administrator.myparkingos.model.requestInfo.GetRightsByGroupIDReq;
import com.example.administrator.myparkingos.model.requestInfo.GetServerTimeReq;
import com.example.administrator.myparkingos.model.requestInfo.GetSysSettingObjectReq;
import com.example.administrator.myparkingos.model.requestInfo.LoginUserReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarFreeOutWithoutCPHReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarInConfirmReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarInReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarInWithoutCPHReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarOutReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarOutWithoutCPHReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarOutWithoutEntryRecordReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeAmountReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeAsFreeReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeInfoReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeWithCaptureImageReq;
import com.example.administrator.myparkingos.model.requestInfo.UploadCaptureImageReq;
import com.example.administrator.myparkingos.model.responseInfo.AddOptLogResp;
import com.example.administrator.myparkingos.model.responseInfo.CaclChargeAmountResp;
import com.example.administrator.myparkingos.model.responseInfo.CancelChargeResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCarInResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCarOutResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCardIssueResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCardTypeDefResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCheDaoSetResp;
import com.example.administrator.myparkingos.model.responseInfo.GetFreeReasonResp;
import com.example.administrator.myparkingos.model.responseInfo.GetNetCameraSetResp;
import com.example.administrator.myparkingos.model.responseInfo.GetOperatorsResp;
import com.example.administrator.myparkingos.model.responseInfo.GetOperatorsWithoutLoginResp;
import com.example.administrator.myparkingos.model.requestInfo.GetStationSetWithoutLoginReq;
import com.example.administrator.myparkingos.model.responseInfo.GetParkDiscountJHSetResp;
import com.example.administrator.myparkingos.model.responseInfo.GetParkingInfoResp;
import com.example.administrator.myparkingos.model.responseInfo.GetRightsByGroupIDResp;
import com.example.administrator.myparkingos.model.responseInfo.GetServerTimeResp;
import com.example.administrator.myparkingos.model.responseInfo.GetStationSetWithoutLoginResp;
import com.example.administrator.myparkingos.model.responseInfo.GetSysSettingObjectResp;
import com.example.administrator.myparkingos.model.responseInfo.LoginUserResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarFreeOutWithoutCPHResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarInConfirmResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarInResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarInWithoutCPHResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutWithoutCPHResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutWithoutEntryRecordResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeAmountResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeAsFreeResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeInfoResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeWithCaptureImageResp;
import com.example.administrator.myparkingos.model.responseInfo.UploadCaptureImageResp;
import com.example.administrator.myparkingos.util.FileUploadUtil;
import com.example.administrator.myparkingos.util.HttpUtils;
import com.example.administrator.myparkingos.util.L;
import com.example.administrator.myparkingos.util.RegexUtil;
import com.example.administrator.myparkingos.util.TimeConvertUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017-03-27.
 */
public class GetServiceData
{
    /**
     * 获取服务器数据 使用静态内部类也可以实现单例模式
     */
    private static GetServiceData mGetServiceData;

    private static final String TAG = "RequestByURL";
    public static String address = "http://" + Model.serverIP + ":" + Model.serverPort + "/";


    public void setAddressAndPort(String serverIP, String port)
    {
        Model.serverIP = serverIP;
        Model.serverPort = port;

        address = "http://" + Model.serverIP + ":" + Model.serverPort + "/";
        L.i("Model.serverIP:" + Model.serverIP + ",Model.serverPort:" + Model.serverPort);
        L.i("address:" + address);
    }

    static public GetServiceData getInstance()
    {
        if (mGetServiceData == null)
        {
            synchronized (GetServiceData.class)
            {
                if (mGetServiceData == null)
                {
                    mGetServiceData = new GetServiceData();
                }
            }
        }
        return mGetServiceData;
    }

    public AddOptLogResp AddOptLog(AddOptLogReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJson commonJson = getData("AddOptLog", Integer.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }
        AddOptLogResp resp = new AddOptLogResp();
        resp.setRcode(commonJson.getRcode());
        resp.setMsg(commonJson.getMsg());
        resp.setData((Integer) commonJson.getData());
        return resp;
    }

    /**
     * 获取操作员信息
     */
    public GetOperatorsWithoutLoginResp GetOperatorsWithoutLogin(GetOperatorsWithoutLoginReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJsonList commonJsonList =
                getDataWithList("GetOperatorsWithoutLogin", GetOperatorsWithoutLoginResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }
        GetOperatorsWithoutLoginResp resp = new GetOperatorsWithoutLoginResp();
        resp.setRcode(commonJsonList.getRcode());
        resp.setMsg(commonJsonList.getMsg());
        resp.setData(commonJsonList.getData());
        L.i("GetOperatorsWithoutLogin" + resp.toString());
        return resp;
    }

    /**
     * 在没有登录情况下，获取工作站信息
     * <p/>
     * 例如：请求：http://192.168.2.158:9000/ParkAPI/GetStationSetWithoutLogin?token=&OrderField=StationId
     *
     * @return
     */
    public GetStationSetWithoutLoginResp GetStationSetWithoutLogin(GetStationSetWithoutLoginReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJsonList commonJsonList =
                getDataWithList("GetStationSetWithoutLogin", GetStationSetWithoutLoginResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }
        GetStationSetWithoutLoginResp resp = new GetStationSetWithoutLoginResp();
        resp.setRcode(commonJsonList.getRcode());
        resp.setMsg(commonJsonList.getMsg());
        resp.setData(commonJsonList.getData());
        L.i("GetStationSetWithoutLogin" + resp.toString());
        return resp;
    }

    public LoginUserResp LoginUser(LoginUserReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJson commonJson =
                getData("LoginUser", LoginUserResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }
        LoginUserResp resp = new LoginUserResp();
        resp.setRcode(commonJson.getRcode());
        resp.setMsg(commonJson.getMsg());
        resp.setData((LoginUserResp.DataBean) commonJson.getData());
        L.i("LoginUser" + resp.toString());
        return resp;
    }

    /**
     * 请求系统设置   http://192.168.2.158:9000/ParkAPI/GetSysSettingObject?token=af7ba4e7a0164b1582468612a18d9d57&StationID=2
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public GetSysSettingObjectResp GetSysSettingObject(GetSysSettingObjectReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJson commonJson = getData("GetSysSettingObject", GetSysSettingObjectResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }
        GetSysSettingObjectResp resp = new GetSysSettingObjectResp();
        resp.setRcode(commonJson.getRcode());
        resp.setMsg(commonJson.getMsg());
        resp.setData((GetSysSettingObjectResp.DataBean) commonJson.getData());
        return resp;
    }

    /**
     * 请求服务器时间 http://192.168.2.158:9000/ParkAPI/getServerTime?token=806f13c43e7044c1a268bc6a09e00c81
     */
    public GetServerTimeResp getServerTime(GetServerTimeReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJson commonJson =
                getData("getServerTime", GetServerTimeResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }
        GetServerTimeResp resp = new GetServerTimeResp();
        resp.setRcode(commonJson.getRcode());
        resp.setMsg(commonJson.getMsg());
        resp.setData((GetServerTimeResp.DataBean) commonJson.getData());
        L.i("getServerTime" + resp.toString());
        return resp;
    }

    /**
     * 请求操作员的详细信息  //    http://192.168.2.158:9000/ParkAPI/GetOperators?token=3b773b3f20f24260bf4d379ff09c3839
     * &jsonSearchParam=%5b%7b%22Conditions%22%3a%5b%7b%22FieldName%22%3a%22UserNO%22%2c%22Operator%22%3a%22%3d%22%2c%22FieldValue%22%3a%22888888%22%2c%22Combinator%22%3a%22and%22%7d%5d%2c%22Combinator%22%3a%22and%22%7d%5d
     */
    public GetOperatorsResp GetOperators(GetOperatorsReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJsonList commonJsonList = getDataWithList("GetOperators", GetOperatorsResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }
        GetOperatorsResp resp = new GetOperatorsResp();
        resp.setRcode(commonJsonList.getRcode());
        resp.setMsg(commonJsonList.getMsg());
        resp.setData(commonJsonList.getData());
        L.i("GetOperators" + resp.toString());
        return resp;
    }

    /**
     * 请求权限组详细信息    http://192.168.2.158:9000/ParkAPI/GetRightsByGroupID?token=ca30622e4a2d421483e5e5da95ba6fd1&GroupID=1
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public GetRightsByGroupIDResp GetRightsByGroupID(GetRightsByGroupIDReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJsonList commonJsonList = getDataWithList("GetRightsByGroupID", GetRightsByGroupIDResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }
        GetRightsByGroupIDResp resp = new GetRightsByGroupIDResp();
        resp.setRcode(commonJsonList.getRcode());
        resp.setMsg(commonJsonList.getMsg());
        resp.setData(commonJsonList.getData());
        L.i("GetRightsByGroupID" + resp.toString());
        return resp;
    }


    /**
     * 车辆进场 http://192.168.2.158:9000/ParkAPI/SetCarIn
     */
    public SetCarInResp SetCarIn(SetCarInReq setCarInReq)
    {
        String cph = setCarInReq.getCPH();
        if (cph != null) // 中文的车牌需要采用utf-8编码的格式
        {
            try
            {
                setCarInReq.setCPH(URLEncoder.encode(cph, "UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        Map value = getValue(setCarInReq);

        String convertString = mapToString(value);

        CommonJson setCarIn = getData("SetCarIn", SetCarInResp.DataBean.class, null, convertString);
        if (setCarIn == null)
        {
            return null; // 为空
        }

        SetCarInResp setCarInResp = new SetCarInResp();
        setCarInResp.setRcode(setCarIn.getRcode());
        setCarInResp.setMsg(setCarIn.getMsg());
        setCarInResp.setData((SetCarInResp.DataBean) setCarIn.getData());
        L.i("SetCarIn" + setCarInResp.toString());
        return setCarInResp;
    }

    /**
     * 无牌车进场  http://192.168.2.158:9000/ParkAPI/SetCarInWithoutCPH?token=b6018e51fe16417fb959f3a3383fccc7&CtrlNumber=3&StationId=2
     *
     * @param setCarInWithoutCPHReq
     * @return
     */
    public SetCarInWithoutCPHResp SetCarInWithoutCPH(SetCarInWithoutCPHReq setCarInWithoutCPHReq)
    {
        String cph = setCarInWithoutCPHReq.getCPH();
        if (cph != null) // 中文的车牌需要采用utf-8编码的格式
        {
            try
            {
                setCarInWithoutCPHReq.setCPH(URLEncoder.encode(cph, "UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }

        Map value = getValue(setCarInWithoutCPHReq);

        String convertString = mapToString(value);

        CommonJson commonJson = getData("SetCarInWithoutCPH", SetCarInWithoutCPHResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }

        SetCarInWithoutCPHResp setCarInWithoutCPHResp = new SetCarInWithoutCPHResp();
        setCarInWithoutCPHResp.setRcode(commonJson.getRcode());
        setCarInWithoutCPHResp.setMsg(commonJson.getMsg());
        setCarInWithoutCPHResp.setData((SetCarInWithoutCPHResp.DataBean) commonJson.getData());
        L.i("SetCarInWithoutCPH" + setCarInWithoutCPHResp.toString());
        return setCarInWithoutCPHResp;
    }

    /**
     * 车辆进场确认  http://192.168.2.158:9000/ParkAPI/SetCarInWithoutCPH?CPH=京a43234&CtrlNumber=9&StationId=1&Token=eb118c26689046498b8a00c635c61da6
     *
     * @param setCarInConfirmReq
     * @return
     */
    public SetCarInConfirmResp SetCarInConfirmed(SetCarInConfirmReq setCarInConfirmReq)
    {
        String cph = setCarInConfirmReq.getCPH();
        L.i("cph:" + cph);
        if (cph != null) // 中文的车牌需要采用utf-8编码的格式
        {
            try
            {
                setCarInConfirmReq.setCPH(URLEncoder.encode(cph, "UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }

        String cphConfirmed = setCarInConfirmReq.getCPHConfirmed();
        L.i("cphConfirmed:" + cphConfirmed);
        if (cphConfirmed != null) // 中文的车牌需要采用utf-8编码的格式
        {
            try
            {
                setCarInConfirmReq.setCPHConfirmed(URLEncoder.encode(cphConfirmed, "UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }


        Map value = getValue(setCarInConfirmReq);

        String convertString = mapToString(value);

        CommonJson commonJson = getData("SetCarInConfirmed", SetCarInConfirmResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }

        SetCarInConfirmResp setCarInConfirmResp = new SetCarInConfirmResp();
        setCarInConfirmResp.setRcode(commonJson.getRcode());
        setCarInConfirmResp.setMsg(commonJson.getMsg());
        setCarInConfirmResp.setData((SetCarInConfirmResp.DataBean) commonJson.getData());
        L.i("SetCarInConfirmed" + setCarInConfirmResp.toString());
        return setCarInConfirmResp;
    }

    /**
     * 获取模糊对比车牌数据
     */
    public void GetCarInByCarPlateNumberLike(CarPlateNumberLikeReq carPlateNumberLikeReq)
    {
        Map value = getValue(carPlateNumberLikeReq);

        String convertString = mapToString(value);

        CommonJson commonJson = getData("GetCarInByCarPlateNumberLike", Integer.class, null, convertString);
        if (commonJson == null)
        {
            return;
        }
    }

    /**
     * 当车辆手动进场时，查询获取发卡行信息，提供相应的提示
     */
    public GetCardIssueResp SelectFxCPH_Like(GetCardIssueReq getCardIssueReq)
    {
        Map value = getValue(getCardIssueReq);

        String convertString = mapToString(value);

        // 解析数据
        CommonJsonList commonJsonList = getDataWithList("GetCardIssue", GetCardIssueResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJsonList.getData());
        GetCardIssueResp getCardIssueResp = new GetCardIssueResp();
        getCardIssueResp.setMsg(commonJsonList.getMsg());
        getCardIssueResp.setRcode(commonJsonList.getRcode());
        getCardIssueResp.setData(commonJsonList.getData());
        return getCardIssueResp;
    }

//    /**
//     * 车辆出场 http://192.168.2.158:9000/ParkAPI/SetCarOut?&StationId=1&CPH=粤b55555&token=520d2e8492d1405baa2e25314a39541e&CtrlNumber=10
//     */
//    public SetCarOutResp SetCarOut(SetCarOutReq req)
//    {
//        Map value = getValue(req);
//
//        String convertString = mapToString(value);
//
//        // 解析数据
//        CommonJsonList commonJsonList = getDataWithList("SetCarOut", SetCarOutResp.DataBean.class, null, convertString);
//        if (commonJsonList == null)
//        {
//            return null;
//        }
//
//        L.i("commonJsonList data" + commonJsonList.getData());
//
//        SetCarOutResp setCarOutResp = new SetCarOutResp();
//
//        setCarOutResp.setMsg(commonJsonList.getMsg());
//        setCarOutResp.setRcode(commonJsonList.getRcode());
//        setCarOutResp.setGridData(commonJsonList.getData());
//        return setCarOutResp;
//    }

    /**
     * 获取车道信息
     */
    public GetCheDaoSetResp GetCheDaoSet(GetCheDaoSetReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJsonList commonJsonList = getDataWithList("GetCheDaoSet", GetCheDaoSetResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJsonList.getData());

        GetCheDaoSetResp getCheDaoSetResp = new GetCheDaoSetResp();

        getCheDaoSetResp.setMsg(commonJsonList.getMsg());
        getCheDaoSetResp.setRcode(commonJsonList.getRcode());
        getCheDaoSetResp.setData(commonJsonList.getData());
        return getCheDaoSetResp;

    }

    /**
     * 9, http://192.168.2.158:9000/ParkAPI/GetNetCameraSet?token=55b10f562a27406d921fccab85001ec9&jsonSearchParam=%5b%7b%22Conditions%22%3a%5b%7b%22FieldName%22%3a%22VideoIP%22%2c%22Operator%22%3a%22%3d%22%2c%22FieldValue%22%3a%22192.168.6.211%22%2c%22Combinator%22%3a%22and%22%7d%5d%2c%22Combinator%22%3a%22and%22%7d%5d
     * //通过camra IP来获取相机信息
     */
    public GetNetCameraSetResp GetNetCameraSet(GetNetCameraSetReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJsonList commonJsonList = getDataWithList("GetNetCameraSet", GetNetCameraSetResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJsonList.getData());
        GetNetCameraSetResp resp = new GetNetCameraSetResp();

        resp.setMsg(commonJsonList.getMsg());
        resp.setRcode(commonJsonList.getRcode());
        resp.setData(commonJsonList.getData());
        return resp;
    }

    public GetCarOutResp GetCarOut(GetCarOutReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJsonList commonJsonList = getDataWithList("GetCarOut", GetCarOutResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJsonList.getData());
        GetCarOutResp resp = new GetCarOutResp();

        resp.setMsg(commonJsonList.getMsg());
        resp.setRcode(commonJsonList.getRcode());
        resp.setData(commonJsonList.getData());
        return resp;
    }

    public GetCarInResp GetCarIn(GetCarInReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJsonList commonJsonList = getDataWithList("GetCarIn", GetCarInResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJsonList.getData());
        GetCarInResp resp = new GetCarInResp();

        resp.setMsg(commonJsonList.getMsg());
        resp.setRcode(commonJsonList.getRcode());
        resp.setData(commonJsonList.getData());
        return resp;
    }

    public GetParkingInfoResp GetParkingInfo(GetParkingInfoReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJson commonJson = getData("GetParkingInfo", GetParkingInfoResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJson.getData());
        GetParkingInfoResp resp = new GetParkingInfoResp();

        resp.setMsg(commonJson.getMsg());
        resp.setRcode(commonJson.getRcode());
        resp.setData((GetParkingInfoResp.DataBean) commonJson.getData());
        return resp;
    }

    /**
     * 车辆出场
     *
     * @param req
     * @return
     */
    public SetCarOutResp SetCarOut(SetCarOutReq req)
    {
        String cph = req.getCPH();
        L.i("cph:" + cph);
        if (cph != null) // 中文的车牌需要采用utf-8编码的格式
        {
            try
            {
                req.setCPH(URLEncoder.encode(cph, "UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }

        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJson commonJson = getData("SetCarOut", SetCarOutResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJson.getData());
        SetCarOutResp resp = new SetCarOutResp();

        resp.setMsg(commonJson.getMsg());
        resp.setRcode(commonJson.getRcode());
        resp.setData((SetCarOutResp.DataBean) commonJson.getData());
        return resp;
    }

    public SetCarOutWithoutEntryRecordResp SetCarOutWithoutEntryRecord(SetCarOutWithoutEntryRecordReq req)
    {
        String cph = req.getCPH();
        L.i("cph:" + cph);
        if (cph != null) // 中文的车牌需要采用utf-8编码的格式
        {
            try
            {
                req.setCPH(URLEncoder.encode(cph, "UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }

        Map value = getValue(req);

        String convertString = mapToString(value);
        // 解析数据
        CommonJson commonJson = getData("SetCarOutWithoutEntryRecord", SetCarOutResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJson.getData());
        SetCarOutWithoutEntryRecordResp resp = new SetCarOutWithoutEntryRecordResp();

        resp.setMsg(commonJson.getMsg());
        resp.setRcode(commonJson.getRcode());
        resp.setData((SetCarOutResp.DataBean) commonJson.getData());
        return resp;
    }

    public UpdateChargeAmountResp UpdateChargeAmount(UpdateChargeAmountReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJson commonJson = getData("UpdateChargeAmount", Integer.class, null, convertString);
        if (commonJson == null || commonJson.getData() == null)
        {
            return null;
        }

        L.i("commonJsonList data:" + commonJson.getData());
        UpdateChargeAmountResp resp = new UpdateChargeAmountResp();

        resp.setMsg(commonJson.getMsg());
        resp.setRcode(commonJson.getRcode());
        resp.setData((Integer) commonJson.getData());
        return resp;
    }

    public UpdateChargeInfoResp UpdateChargeInfo(UpdateChargeInfoReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJson commonJson = getData("UpdateChargeInfo", Integer.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJson.getData());
        UpdateChargeInfoResp resp = new UpdateChargeInfoResp();

        resp.setMsg(commonJson.getMsg());
        resp.setRcode(commonJson.getRcode());
        resp.setData((Integer) commonJson.getData());
        return resp;
    }

    public UpdateChargeAsFreeResp UpdateChargeAsFree(UpdateChargeAsFreeReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJson commonJson = getData("UpdateChargeAsFree", Integer.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }

        L.i("commonJsonList data:" + commonJson.getData());
        UpdateChargeAsFreeResp resp = new UpdateChargeAsFreeResp();

        resp.setMsg(commonJson.getMsg());
        resp.setRcode(commonJson.getRcode());
        resp.setData((Integer) commonJson.getData());
        return resp;
    }


    public CancelChargeResp CancelCharge(CancelChargeReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJson commonJson = getData("CancelCharge", Integer.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJson.getData());
        CancelChargeResp resp = new CancelChargeResp();

        resp.setMsg(commonJson.getMsg());
        resp.setRcode(commonJson.getRcode());
        resp.setData((Integer) commonJson.getData());
        return resp;
    }

    public GetFreeReasonResp GetFreeReason(GetFreeReasonReq req, String extendParam)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        convertString += (null == extendParam || extendParam.trim().length() <= 0 ? "" : (extendParam.trim().startsWith("&") ? "" : "&") + extendParam);
        // 解析数据
        CommonJsonList commonJsonList = getDataWithList("GetFreeReason", GetFreeReasonResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJsonList.getData());
        GetFreeReasonResp resp = new GetFreeReasonResp();

        resp.setMsg(commonJsonList.getMsg());
        resp.setRcode(commonJsonList.getRcode());
        resp.setData(commonJsonList.getData());
        return resp;
    }

    public GetParkDiscountJHSetResp GetParkDiscountJHSet(GetParkDiscountJHSetReq req, String extendParam)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        convertString += (null == extendParam || extendParam.trim().length() <= 0 ? "" : (extendParam.trim().startsWith("&") ? "" : "&") + extendParam);
        // 解析数据
        CommonJsonList commonJsonList = getDataWithList("GetParkDiscountJHSet", GetParkDiscountJHSetResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJsonList.getData());
        GetParkDiscountJHSetResp resp = new GetParkDiscountJHSetResp();

        resp.setMsg(commonJsonList.getMsg());
        resp.setRcode(commonJsonList.getRcode());
        resp.setData(commonJsonList.getData());
        return resp;
    }


    public GetCardTypeDefResp GetCardTypeDef(GetCardTypeDefReq req, String extendParam)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        convertString += (null == extendParam || extendParam.trim().length() <= 0 ? "" : (extendParam.trim().startsWith("&") ? "" : "&") + extendParam);
        // 解析数据
        CommonJsonList commonJsonList = getDataWithList("GetCardTypeDef", GetCardTypeDefResp.DataBean.class, null, convertString);
        if (commonJsonList == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJsonList.getData());
        GetCardTypeDefResp resp = new GetCardTypeDefResp();

        resp.setMsg(commonJsonList.getMsg());
        resp.setRcode(commonJsonList.getRcode());
        resp.setData(commonJsonList.getData());
        return resp;
    }

    public CaclChargeAmountResp CaclChargeAmount(CaclChargeAmountReq req)
    {
        String cph = req.getCPH();
        L.i("cph:" + cph);
        if (cph != null) // 中文的车牌需要采用utf-8编码的格式
        {
            try
            {
                req.setCPH(URLEncoder.encode(cph, "UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }

        Map value = getValue(req);

        String convertString = mapToString(value);

        // 解析数据
        CommonJson commonJson = getData("CaclChargeAmount", CaclChargeAmountResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }

        L.i("commonJsonList data" + commonJson.getData());
        CaclChargeAmountResp resp = new CaclChargeAmountResp();

        resp.setMsg(commonJson.getMsg());
        resp.setRcode(commonJson.getRcode());
        resp.setData((CaclChargeAmountResp.DataBean) commonJson.getData());
        return resp;
    }





    /**
     * 获取数据
     *
     * @param interfaceName
     * @param clazz
     * @param orderField
     * @param param
     * @return
     */
    public CommonJson getData(String interfaceName, Class clazz, String orderField, String param)
    {
        String data;
        CommonJson result = null;

        if (null == interfaceName || interfaceName.trim().length() <= 0)
        {
            return null;
        }

        /**
         * 表示url需要的参数序列
         */

        String URLParam = String.format("%1$s%2$s", // %3$s
                null == orderField ? "" : "&OrderField=" + orderField, // param2
                (null == param || param.trim().length() <= 0 ? "" : param)// param3
        );

        /**
         * 组合需要最后的 URL地址
         */
        String expectURL = String.format("%1$sParkAPI/%2$s%3$s%4$s"
                , address
                , interfaceName
                , (null == URLParam || "" == URLParam.trim() ? "" : "?")
                , URLParam);

        try
        {
//            String encode = URLEncoder.encode(expectURL); // URLEncoder.dfltEncName 默认的编码
            data = HttpUtils.doGet(expectURL);


            L.i("url:" + expectURL + "###, data:" + data);
            CommonJson commonJson = CommonJson.fromJson(data, clazz);// CommonJson cannot be cast to com.example.administrator.myparkingos.model.responseInfo.SetCarInResp

            return commonJson;
        }
        catch (Exception ex)
        {
            L.i(expectURL + " >>> 获取数据失败 " + ex);
        }

        return result;
    }


    /**
     * 获取带有列表的数据
     *
     * @param interfaceName
     * @param clazz
     * @param orderField
     * @param param
     * @return
     */
    public CommonJsonList getDataWithList(String interfaceName, Class clazz, String orderField, String param)
    {
        String data;
        CommonJsonList result = null;

        if (null == interfaceName || interfaceName.trim().length() <= 0)
        {
            return null;
        }

        /**
         * 表示url需要的参数序列
         */

        String URLParam = String.format("%1$s%2$s",
                (null == param || param.trim().length() <= 0 ? "" : param)// param2
                , null == orderField ? "" : "&OrderField=" + orderField// param3
        ); // 拼接字符

        /**
         * 组合需要最后的 URL地址
         */
        String expectURL = String.format("%1$sParkAPI/%2$s%3$s%4$s"
                , address
                , interfaceName
                , (null == URLParam || "" == URLParam.trim() ? "" : "?")
                , URLParam);

//        OkGoUtil.INSTATNCE.requestBitmapGet(mActivity, ); /// 直接进行替换

        try
        {
            data = HttpUtils.doGet(expectURL);
            L.i("url:" + expectURL + " ###, data:" + data);
            CommonJsonList commonJsonList = CommonJsonList.fromJson(data, clazz);
            return commonJsonList;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            L.i(expectURL + ", 获取数据失败" + ex.getMessage());
        }

        return result;
    }


    /**
     * 发送日记请求 较多的地方会用到
     */
    public void requestAddOptLog(String menu, String content)
    {
        AddOptLogReq req = new AddOptLogReq();
        req.setToken(Model.token);
        req.setJsonModel(getAddOptLogText(menu, content));
        GetServiceData.getInstance().AddOptLog(req);
    }

    private String getAddOptLogText(String menu, String content)
    {
        AddOptLog opt = new AddOptLog();
        opt.setOptNO(Model.sUserCard);
        opt.setUserName(Model.sUserName);
        opt.setOptMenu(menu);
        opt.setOptContent(content);
        opt.setOptTime(TimeConvertUtils.longToString(System.currentTimeMillis()));
        opt.setStationID(Model.stationID);

        Gson gson = new Gson();
        try
        {
            return URLEncoder.encode(gson.toJson(opt), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传文件
     *
     * @param req
     * @param fileName
     * @return
     */
    public UploadCaptureImageResp UploadCaptureImage(UploadCaptureImageReq req, String fileName)
    {
        if (TextUtils.isEmpty(fileName))
        {
            L.i("TextUtils.isEmpty(fileName)");
            return null;
        }
        Map value = getValue(req);

        String convertString = mapToString(value);

        File file = new File(fileName);

        String resultString;
        try
        {
            resultString = FileUploadUtil.uploadForm(null, "uploadFile", file, file.getName(), generateResultUrl("UploadCaptureImage", convertString));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        if (resultString == null) return null;

        CommonJsonList commonJsonList = CommonJsonList.fromJson(resultString, String.class); // 返回值的类型是String
        if (commonJsonList == null)
        {
            return null;
        }

        L.i("commonJson data" + commonJsonList.getData());
        UploadCaptureImageResp resp = new UploadCaptureImageResp();

        resp.setMsg(commonJsonList.getMsg());
        resp.setRcode(commonJsonList.getRcode());
        resp.setData(commonJsonList.getData());
        return resp;
    }

    public UpdateChargeWithCaptureImageResp UpdateChargeWithCaptureImage(UpdateChargeWithCaptureImageReq req, Bitmap bitmap)
    {
        if (bitmap == null) return null;
        Map value = getValue(req);

        String convertString = mapToString(value);
        String resultString;
        try
        {
            resultString = FileUploadUtil.uploadBitmap(null, bitmap, generateResultUrl("UpdateChargeWithCaptureImage", convertString));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        CommonJson commonJson = CommonJson.fromJson(resultString, Integer.class); // 返回值的类型是String
        if (commonJson == null)
        {
            return null;
        }

        L.i("commonJson data:" + commonJson.getData());
        UpdateChargeWithCaptureImageResp resp = new UpdateChargeWithCaptureImageResp();

        resp.setMsg(commonJson.getMsg());
        resp.setRcode(commonJson.getRcode());
        if (commonJson.getData() != null)
            resp.setData((Integer) commonJson.getData());
        return resp;
    }

    public SetCarOutWithoutCPHResp SetCarOutWithoutCPH(SetCarOutWithoutCPHReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJson commonJson =
                getData("SetCarOutWithoutCPH", SetCarOutWithoutCPHResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }
        SetCarOutWithoutCPHResp resp = new SetCarOutWithoutCPHResp();
        resp.setRcode(commonJson.getRcode());
        resp.setMsg(commonJson.getMsg());
        resp.setData((SetCarOutWithoutCPHResp.DataBean) commonJson.getData());
        L.i("SetCarOutWithoutCPH" + resp.toString());
        return resp;
    }

    public SetCarFreeOutWithoutCPHResp SetCarFreeOutWithoutCPH(SetCarFreeOutWithoutCPHReq req)
    {
        Map value = getValue(req);

        String convertString = mapToString(value);

        CommonJson commonJson =
                getData("SetCarFreeOutWithoutCPH", SetCarFreeOutWithoutCPHResp.DataBean.class, null, convertString);
        if (commonJson == null)
        {
            return null;
        }
        SetCarFreeOutWithoutCPHResp resp = new SetCarFreeOutWithoutCPHResp();
        resp.setRcode(commonJson.getRcode());
        resp.setMsg(commonJson.getMsg());
        resp.setData((SetCarFreeOutWithoutCPHResp.DataBean) commonJson.getData());
        L.i("SetCarFreeOutWithoutCPH" + resp.toString());
        return resp;
    }


    /**
     * 将实体类，转换成相应的map集合，但是需要注意的是，有些字段不是必须的；
     * int 默认时为0，0在字段中可以有意思的存在的;所以这里使用相应的封装类，封装类可以指定null,或者相应的基础类
     *
     * @param thisObj
     * @return
     */
    public static Map getValue(Object thisObj)
    {
        Map map = new LinkedHashMap<>();
        Class c;
        try
        {
            c = Class.forName(thisObj.getClass().getName()); // 获取object的class对象
            Method[] m = c.getMethods();
            for (int i = 0; i < m.length; i++)
            {
                String method = m[i].getName();// 遍历所有的方法，其getName后面即为相应的变量名字
                if (method.startsWith("get"))
                {
                    try
                    {
                        Object value = m[i].invoke(thisObj);
                        if (value != null && !"getClass".equals(method)) //value为空，则不存放
                        {
                            String key = method.substring(3); // 3即为get的长度
//                            key = key.substring(0, 1).toUpperCase() + key.substring(1);// 大小写的变化
//                            map.put(method.substring(3).toLowerCase(), value);
                            map.put(key, value);
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("error:" + method);
                    }
                }
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
        return map;
    }

    public static String getUTF8ForChinese(String srcString, String encodeFormat)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < srcString.length(); i++)
        {
            String valueOf = String.valueOf(srcString.charAt(i));
            if (RegexUtil.checkChinese(valueOf))
            {
                L.e("getUTF8ForChinese " + valueOf + "->是中文");
                String encode = "";
                try
                {
                    encode = URLEncoder.encode(valueOf, encodeFormat);
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                stringBuffer.append(encode);
            }
            else
            {
                stringBuffer.append(valueOf);
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 将map集合的字符对转换成字符串
     *
     * @param srcMap
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String mapToString(Map<String, Object> srcMap)
    {
        StringBuffer stringBuffer = new StringBuffer();
        if (srcMap == null || srcMap.size() <= 0)
        {
            return null;
        }

        Set<String> strings = srcMap.keySet();

        int i = 0;
        for (String str : strings)
        {
            Object o = srcMap.get(str);
            String eachString;
            if (!(o instanceof String)) // 不是String,转换成String
            {
                eachString = String.valueOf(o);
            }
            else
            {
                eachString = getUTF8ForChinese((String) o, "UTF-8");
            }
            stringBuffer.append(str).append("=").append(eachString);
            if (i != strings.size() - 1)
            {
                stringBuffer.append("&");
            }
            i++;
        }

//        L.i("stringBuffer.toString():" + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public static String getResultUrl(String interfaceName, Object thisObj)
    {
        return generateResultUrl(interfaceName, mapToString(getValue(thisObj)));
    }

    public static String generateResultUrl(String interfaceName, String param)
    {
        if (null == interfaceName || interfaceName.trim().length() <= 0)
        {
            return null;
        }

        /**
         * 组合需要最后的 URL地址
         */
        return String.format("%1$sParkAPI/%2$s%3$s"
                , address
                , interfaceName
                , null == param || "" == param.trim() ? "" : "?" + param);
    }

}
