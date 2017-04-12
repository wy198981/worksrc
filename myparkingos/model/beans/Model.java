package com.example.administrator.myparkingos.model.beans;

import com.example.administrator.myparkingos.constant.JsonSearchParam;
import com.example.administrator.myparkingos.constant.OrderField;
import com.example.administrator.myparkingos.model.GetServiceData;
import com.example.administrator.myparkingos.model.beans.gson.EntityRights;
import com.example.administrator.myparkingos.model.requestInfo.GetCheDaoSetReq;
import com.example.administrator.myparkingos.model.responseInfo.GetCheDaoSetResp;
import com.example.administrator.myparkingos.model.responseInfo.GetRightsByGroupIDResp;
import com.example.administrator.myparkingos.model.responseInfo.GetSysSettingObjectResp;
import com.example.administrator.myparkingos.util.L;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017-02-17.
 */
public class Model
{
    /**
     * 1, 服务器 IP 地址
     */
    public static String serverIP = "192.168.2.158";
    /**
     * 2，服务器的端口号
     */
    public static String serverPort = "9000";

    /// <summary>
    /// 权限分配List
    /// </summary>
    public static List<GetRightsByGroupIDResp.DataBean> lstRights = new ArrayList<GetRightsByGroupIDResp.DataBean>();

    /// <summary>
    /// token(用于访问服务器的唯一凭证)
    /// </summary>
    public static String token = "";

    /// <summary>
    /// 当前工作站的编号(唯一)
    /// </summary>
    public static int stationID = 1;

    /// <summary>
    /// 公司名称
    /// </summary>
    public static String sCompany = "";

    /// <summary>
    /// 用户名称
    /// </summary>
    public static String sUserName = "";

    /// <summary>
    /// 用户卡号
    /// </summary>
    public static String sUserCard = "";

    /// <summary>
    /// 用户权限组编号
    /// </summary>
    public static int sGroupNo = 0;

    /// <summary>
    /// 用户密码
    /// </summary>
    public static String sUserPwd = "";


    /// <summary>
    /// 车场编号(TTCBianHao)
    /// </summary>
    public static int iParkingNo = 0;

    /// <summary>
    ///登录时间 用长整形来表示
    /// </summary>
//    public static DateTime dLoginTime;//
    public static long dLoginTime;

    /// <summary>
    /// 当前电脑设置的车道数量
    /// </summary>
    public static int iChannelCount;

    /**
     * 对于 Model 数据初始化
     */
    public static void DataInit()
    {

    }

    /// <summary>
    /// 默认省份(可以为空)
    /// </summary>
    public static String LocalProvince = "粤";

    /// <summary>
    /// 保存详细日志（开闸窗口弹出、按开闸按钮、开闸记录等）
    /// </summary>
    public static int iDetailLog = 0;

    /// <summary>
    /// 启用掌上停车
    /// </summary>
    public static boolean bAppEnable = false;
    /// <summary>
    /// 启用特殊车牌处理
    /// </summary>
    public static boolean bSpecilCPH = false;
    /// <summary>
    /// 全字母车牌不处理
    /// </summary>
    public static boolean bCphAllEn = false;
    /// <summary>
    /// 车牌结果字符完全一样不处理
    /// </summary>
    public static boolean bCphAllSame = false;

    /// <summary>
    /// 网络摄像头
    /// </summary>
    public static int iEnableNetVideo = 0;

    public static int iVideo4 = 0;

    /// <summary>
    /// 人像抓拍
    /// </summary>
    public static int iPersonVideo;
    /// <summary>
    /// 证件抓拍(TCertificate)
    /// </summary>
    public static int iIDCapture = 0;

    /// <summary>
    /// 文件存盘路径
    /// </summary>
    public static String sImageSavePath = "D:";

    /// <summary>
    /// 图片自动删除
    /// </summary>
    public static int iImageAutoDel = 0;

    /// <summary>
    /// 图象存盘天数
    /// </summary>
    public static int iImageSaveDays = 120;

    /// <summary>
    /// 每天图片自动删除的时间（几点开始执行
    /// </summary>
    public static int iImageAutoDelTime = 1;

    /// <summary>
    /// 收费方式
    /// </summary>
    public static int iChargeType = 0;

    /// <summary>
    /// 1:小数点后一位   2：小数点后两位  (仅北京收费)
    /// </summary>
    public static int iXsdNum = 1;

    /// <summary>
    /// 允许折扣
    /// </summary>
    public static int iDiscount = 0;

    /// <summary>
    /// 带小数点(Txsd)
    /// </summary>
    public static int iXsd = 0;

    /// <summary>
    /// 允许免费车(TCFree)
    /// </summary>
    public static int iFreeCar = 0;

    public static int iSetTempMoney = 0;

    /// <summary>
    /// 月卡超时收费
    /// </summary>
    public static int iYKOverTimeCharge = 0;

    /// <summary>
    /// 是否启用每天最高限额
    /// </summary>
    public static int iZGXE = 0;

    public static int iModiTempType_VoiceSF = 0;   //收费可更改卡类时，弹出收费窗口播报收费 2016-06-15 zsd

    public static String strCarYellowTmpType = "Tmp";

    public static String sMonthOutChargeType = "";

    public static boolean bCarYellowTmp = false;

    /// <summary>
    /// 允许临免车(TTempFree)
    /// </summary>
    public static int iTempFree = 0;

    public static int iSetTempCardType = 0;

    /// <summary>
    /// 月卡过期多少天以后禁止入场
    /// </summary>
    public static int iMothOverDay = 0;

    /// <summary>
    /// 启用每天最高限额的方式
    /// </summary>
    public static int iZGXEType = 0;

    /// <summary>
    /// 设置最高限额的卡类 0:临时卡 1：储值卡 2：储值卡和临时卡
    /// </summary>
    public static int iZGType = 0;  //2016-03-21

    /// <summary>
    /// 月卡延期收费规则
    /// </summary>
    public static int iMonthRule = 0;

    /// <summary>
    /// 临时卡出场收费可取消
    /// </summary>
    public static int iSFCancel = 0;      //2016-02-19

    /// <summary>
    /// 时间加载间隔
    /// </summary>
    public static int iLoadTimeInterval = 20;

    public static int iDisplayTime = 0;

    /// <summary>
    /// 显示道闸状态  软件控制道闸开关
    /// </summary>
    public static int iShowGateState = 1;

    /// <summary>
    /// 凭密码退出在线监控  0无密码  1登录密码  2车场设置密码
    /// </summary>
    public static int iExitOnlineByPwd = 0;

    /// <summary>
    /// 软件开闸不输入车牌
    /// </summary>
    public static int iSoftOpenNoPlate = 0;

    /// <summary>
    /// 启用车队模式(道闸常开)
    /// </summary>
    public static int iCheDui = 0;

    /// <summary>
    /// 车辆异常处理/无卡放行
    /// </summary>
    public static int iExceptionHandle = 0;

    /// <summary>
    /// 显示发卡机卡箱内卡片数量
    /// </summary>
    public static int iShowBoxCardNum = 0;

    /// <summary>
    /// 自动弹出车牌预置窗口
    /// </summary>
    public static int iAutoPrePlate = 0;

    /// <summary>
    /// 多张卡共享同一个车位，基中一张卡入场后，其它同车位的卡不准入场
    /// </summary>
    public static int iForbidSamePosition = 0;

    /// <summary>
    /// 检测卡口优先提取记录 提取记录优先
    /// </summary>
    public static int iCheckPortFirst = 0;

    /// <summary>
    /// 满位灯箱(TFullslight) 1月卡 2临时车 3储值车 5所有车禁止 0所有可以
    /// </summary>
    public static int iFullLight = 0;

    /// <summary>
    /// 延时多少秒计算
    /// </summary>
    public static int iDelayed = 30;

    public static boolean bFullComfirmOpen = false;

    public static int iIDSoftOpen = 0;

    /// <summary>
    /// 进出时间限制
    /// </summary>
    public static int iInOutLimitSeconds = 0;

    /// <summary>
    /// 实时下载(TTimeLost)
    /// </summary>
    public static int iRealTimeDownLoad = 0;

    /// <summary>
    /// ID临时卡出场收费可取消
    /// </summary>
    public static int iIdSfCancel = 0;

    /// <summary>
    /// 下载有效卡号
    /// </summary>
    public static int iICCardDownLoad = 0;

    /// <summary>
    /// 远距离ID卡重复读卡优化处理
    /// </summary>
    public static int iIdReReadHandle = 0;         //bIdMemory

    /// <summary>
    /// ID卡脱机播报显示车牌
    /// </summary>
    public static int iIdPlateDownLoad = 0;

    /// <summary>
    /// 控制一进一出
    /// </summary>
    public static int iIDOneInOneOut = 0;

    /// <summary>
    /// ID分卡类确认开闸
    /// </summary>
    public static int iIDComfirmOpen = 0;

    /// <summary>
    /// ID分卡类一进一出连接字符串(01010101)，保存数据库
    /// </summary>
    public static String sID1In1OutCardType = "";

    /// <summary>
    /// 控制机语音播报并显示停车时间
    /// </summary>
    public static int iCtrlShowStayTime = 0;

    /// <summary>
    /// 控制机显示车位号
    /// </summary>
    public static int iCtrlShowCW = 0;

    /// <summary>
    /// 控制机显示屏显示用户加载的信息,实时更新
    /// </summary>
    public static int iCtrlShowInfo = 0;             //bShowInfo

    /// <summary>
    /// 控制机显示剩余车位,实时更新
    /// </summary>
    public static int iCtrlShowRemainPos = 0;        //bShowRemainCar

    /// <summary>
    /// 语音/显示屏（3.0 or 4.1）
    /// </summary>
    public static int iCtrlVoiceLedVersion = 1;         //iLsType

    /// <summary>
    /// 语音模式（欢迎光临/一路顺风
    /// </summary>
    public static int iCtrlVoiceMode = 0;

    /// <summary>
    /// 固定卡有效期剩余XX天提示
    /// </summary>
    public static int iIDNoticeDay = 10;

    /// <summary>
    /// 票据打印（TBillPrint）
    /// </summary>
    public static int iBillPrint = 0;

    public static int iBillPrintAuto = 0;

    /// <summary>
    /// 打印字体
    /// </summary>
    public static int iPrintFontSize = 8; //

    /// <summary>
    /// 车位串口(TCarPlcomm)
    /// </summary>
    public static int iCarPosCom = 0;

    /// <summary>
    /// 剩余车位显示屏字数
    /// </summary>
    public static int iCarPosLedLen = 0;

    /// <summary>
    /// 收费屏串口(TScreenComm)
    /// </summary>
    public static int iSFLedCom = 0;

    /// <summary>
    /// 外接收费显示屏
    /// </summary>
    public static int iSFLedType = 0;

    /// <summary>
    /// 剩余车位屏显示发布信息(自动发送语音)
    /// </summary>
    public static int iRemainPosLedShowInfo = 0;

    /// <summary>
    /// 剩余车位屏显示车牌
    /// </summary>
    public static int iRemainPosLedShowPlate = 0;

    /// <summary>
    /// 换班登录打印小票
    /// </summary>
    public static int iReLoginPrint = 0;

    public static boolean bOnlyLocation = false;              //只定位车牌

    /// <summary>
    /// 入场打印条码
    /// </summary>
    public static int iBarCodePrint = 0;

    /// <summary>
    /// 车牌识别功能启用
    /// </summary>
    public static int iAutoPlateEn = 0;

    /// <summary>
    /// 对比精度
    /// </summary>
    public static int iAutoPlateDBJD = 2;

    /// <summary>
    /// 入场开闸方式
    /// </summary>
    public static int iInAutoOpenModel = 0;

    /// <summary>
    /// 出场开闸方式
    /// </summary>
    public static int iOutAutoOpenModel = 1;

    /// <summary>
    /// 月卡入场开闸方式  0自动开闸 1确认开闸
    /// </summary>
    public static int iInMothOpenModel = 0;

    /// <summary>
    /// 月卡出场开闸方式 0自动开闸 1确认开闸
    /// </summary>
    public static int iOutMothOpenModel = 0;

    /// <summary>
    /// 显示车牌号小图
    /// </summary>
    public static int iCPHPhoto = 0;

    /// <summary>
    /// 0.不删除 1.删除
    /// </summary>
    public static int iAutoDeleteImg = 0;

    /// <summary>
    /// 相同车牌  在限制时间内不处理
    /// </summary>
    public static String iSameCphDelay = "0";

    /// <summary>
    /// 图片不加水印(TCarPlace)
    /// </summary>
    public static int iCarPosLed = 1;

    /// <summary>
    /// 临时卡收费0元是否自动开闸
    /// </summary>
    public static int iAutoKZ = 0;

    /// <summary>
    /// 是否启用军警车自动开闸放行。0：不启用，1：启用
    /// </summary>
    public static int iAutoColorSet = 0;

    /// <summary>
    /// 0牌车自动放行 0：不启用 1：启用
    /// </summary>
    public static int iAuto0Set = 0;

    /// <summary>
    /// 无牌车入口自动开闸
    /// </summary>
    public static int iNoCPHAutoKZ = 0;  // 2016-02-24

    /// <summary>
    /// 临时车禁止驶入小车场
    /// </summary>
    public static int iTempCanNotInSmall = 0;

    /// <summary>
    /// 车牌识别打折
    /// </summary>
    public static int iAutoCPHDZ = 0;

    /// <summary>
    /// 启用中央收费
    /// </summary>
    public static int iCentralCharge = 0;

    /// <summary>
    /// 出口是否能收费
    /// </summary>
    public static int iOutCharge = 0;

    /// <summary>
    /// 启用多车位多车
    /// </summary>
    public static int iMorePaingCar = 0;

    /// <summary>
    /// 多车位多车的方式
    /// </summary>
    public static int iMorePaingType = 0;

    /// <summary>
    /// 车位机号(TCarPlNoNo)
    /// </summary>
    public static int iCarPosLedJH = 0;

    /// <summary>
    /// 识别车牌有效时间
    /// </summary>
    public static String iCphDelay = "";

    public static StructChannel[] Channels = new StructChannel[100]; //这里最大的空间是100个相机的车道?

    /// <summary>
    /// 加载时间方式
    /// </summary>
    public static int iLoadTimeType = 0;

    /// <summary>
    /// 控制机语音播报并显示车牌
    /// </summary>
    public static int iCtrlShowPlate = 1;

    /// <summary>
    /// 车道设置结构
    /// </summary>
    public static class StructChannel
    {
        public int iInOut;  //   入出类型。0为入，1为出
        public String sInOutName; //   车道名称
        public int iCtrlID; //   机号  等同于 CtrlNumber
        public int iOpenID; //   开闸机号
        public int iOpenType;   // 开闸方式
        public String sCarVideo;    //   相机IP
        public String sPersonVideo; //   人相视频
        public int iBigSmall;       //   大小车场。0为大车场，1为小车场。
        public int iCheckPortID;    //   检测口
        public int iOnLine;   //TempIn  //   是否在线
        public int iTempOut;        //   临时出口
        public int iOutCard;         //   出卡机
        public String sIDAddress;   //   相机IP
        public String sIDSignal;
        public String sSubJH;       //   子机号
        public int iXieYi;          //   协议。0为485，1为TCP/IP
        public String sIP;          //   IP地址
        public String sPCName;

        @Override
        public String toString()
        {
            return "StructChannel{" +
                    "iInOut=" + iInOut +
                    ", sInOutName='" + sInOutName + '\'' +
                    ", iCtrlID=" + iCtrlID +
                    ", iOpenID=" + iOpenID +
                    ", iOpenType=" + iOpenType +
                    ", sCarVideo='" + sCarVideo + '\'' +
                    ", sPersonVideo='" + sPersonVideo + '\'' +
                    ", iBigSmall=" + iBigSmall +
                    ", iCheckPortID=" + iCheckPortID +
                    ", iOnLine=" + iOnLine +
                    ", iTempOut=" + iTempOut +
                    ", iOutCard=" + iOutCard +
                    ", sIDAddress='" + sIDAddress + '\'' +
                    ", sIDSignal='" + sIDSignal + '\'' +
                    ", sSubJH='" + sSubJH + '\'' +
                    ", iXieYi=" + iXieYi +
                    ", sIP='" + sIP + '\'' +
                    ", sPCName='" + sPCName + '\'' +
                    '}';
        }
    }

    public static void setSysSettingToPubVar(GetSysSettingObjectResp.DataBean dataBean)
    {
        if (dataBean == null) return;

        Map map = GetServiceData.getValue(dataBean);

        Model.bAppEnable = convertValueForBoolean(map, "bAppEnable");
        Model.bSpecilCPH = convertValueForBoolean(map, "bSpecilCPH");
        Model.bCphAllEn = convertValueForBoolean(map, "bCphAllEn");
        Model.bCphAllSame = convertValueForBoolean(map, "bCphAllSame");

        // 保存图像视频设置 (视频卡操作去掉)
        Model.iEnableNetVideo = convertValueForInt(map, "iEnableNetVideo");
        Model.iVideo4 = convertValueForInt(map, "bVideo4");
        Model.iPersonVideo = convertValueForInt(map, "iPersonVideo");
        Model.iIDCapture = convertValueForInt(map, "iIDCapture");
        Model.sImageSavePath = convertValueForString(map, "sImageSavePath") == null ? "" : convertValueForString(map, "sImageSavePath");

        Model.iImageAutoDel = convertValueForInt(map, "bImageAutoDel");
        Model.iImageSaveDays = convertValueForInt(map, "iImageSaveDays");
        Model.iImageAutoDelTime = convertValueForInt(map, "iImageAutoDelTime");

//        保存收费设置变量
        Model.iChargeType = convertValueForInt(map, "iChargeType");
        Model.iXsdNum = convertValueForInt(map, "iXsdNum");
        Model.iDiscount = convertValueForInt(map, "iDiscount");
        Model.iXsd = convertValueForInt(map, "iXsd");
        Model.iFreeCar = convertValueForInt(map, "iFreeCar");
        Model.iSetTempMoney = convertValueForInt(map, "bSetTempMoney");
        Model.iYKOverTimeCharge = convertValueForInt(map, "iYKOverTimeCharge");
        Model.iZGXE = convertValueForInt(map, "iZGXE");
        Model.iModiTempType_VoiceSF = convertValueForInt(map, "bModiTempType_VoiceSF");
        Model.iSFCancel = convertValueForInt(map, "bSFCancel");
        Model.iMonthRule = convertValueForInt(map, "bMonthRule");
        Model.iZGType = convertValueForInt(map, "iZGType");
        Model.iZGXEType = convertValueForInt(map, "iZGXEType");
        Model.iMothOverDay = convertValueForInt(map, "iMothOverDay");
        Model.iSetTempCardType = convertValueForInt(map, "bSetTempCardType");
        Model.iTempFree = convertValueForInt(map, "iTempFree");
        Model.bCarYellowTmp = convertValueForBoolean(map, "bCarYellowTmp");
        Model.strCarYellowTmpType = convertValueForString(map, "strCarYellowTmpType") == null ? "TmpB" : convertValueForString(map, "strCarYellowTmpType");
        Model.sMonthOutChargeType = convertValueForString(map, "sMonthOutChargeType") == null ? "TmpA" : convertValueForString(map, "sMonthOutChargeType");

        // 保存在线监控设置
        Model.iLoadTimeInterval = convertValueForInt(map, "iLoadTimeInterval");
        Model.iDisplayTime = convertValueForInt(map, "bDisplayTime");
        //软件控制道闸开关
        Model.iShowGateState = convertValueForInt(map, "iShowGateState");
        Model.iExitOnlineByPwd = convertValueForInt(map, "iExitOnlineByPwd");
        Model.iSoftOpenNoPlate = convertValueForInt(map, "bSoftOpenNoPlate");
        Model.iCheDui = convertValueForInt(map, "bCheDui");
        Model.iExceptionHandle = convertValueForInt(map, "bExceptionHandle");
        Model.iShowBoxCardNum = convertValueForInt(map, "bShowBoxCardNum");
        //Model.bOneKeyShortCut = chkOneKeyShortCut.Checked;

        Model.iAutoPrePlate = convertValueForInt(map, "bAutoPrePlate");
        Model.iForbidSamePosition = convertValueForInt(map, "bForbidSamePosition");
        Model.iCheckPortFirst = convertValueForInt(map, "bCheckPortFirst");
        Model.iFullLight = convertValueForInt(map, "iFullLight");
        Model.iDelayed = convertValueForInt(map, "iDelayed");
        Model.bFullComfirmOpen = convertValueForBoolean(map, "bFullComfirmOpen");

        // 保存ID卡功能设置
        Model.iIDSoftOpen = convertValueForInt(map, "bIDSoftOpen");
        Model.iInOutLimitSeconds = convertValueForInt(map, "iInOutLimitSeconds");
        Model.iRealTimeDownLoad = convertValueForInt(map, "iRealTimeDownLoad");
        Model.iIdSfCancel = convertValueForInt(map, "bIdSfCancel");
        Model.iICCardDownLoad = convertValueForInt(map, "iICCardDownLoad");
        Model.iIdReReadHandle = convertValueForInt(map, "bIdReReadHandle");
        Model.iIdPlateDownLoad = convertValueForInt(map, "bIdPlateDownLoad");
        Model.iIdPlateDownLoad = 1; //czh 2016-12-30
        Model.iIDOneInOneOut = convertValueForInt(map, "iIDOneInOneOut"); //ID控制一进一出有问题
        Model.iIDComfirmOpen = convertValueForInt(map, "iIDComfirmOpen");
        Model.sID1In1OutCardType = convertValueForString(map, "sID1In1OutCardType") == null ? "" : convertValueForString(map, "sID1In1OutCardType");

//        保存语音显示功能
        Model.iCtrlShowStayTime = convertValueForInt(map, "bCtrlShowStayTime");
        Model.iCtrlShowCW = convertValueForInt(map, "bCtrlShowCW");
        Model.iCtrlShowInfo = convertValueForInt(map, "bCtrlShowInfo");
        Model.iCtrlShowRemainPos = convertValueForInt(map, "bCtrlShowRemainPos");
        Model.iCtrlVoiceLedVersion = convertValueForInt(map, "iCtrlVoiceLedVersion");
        Model.iCtrlVoiceMode = convertValueForInt(map, "iCtrlVoiceMode");
        Model.iIDNoticeDay = convertValueForInt(map, "iIDNoticeDay");

//        外接附加设备设置(包含多功能语音模块)
        Model.iBillPrint = convertValueForInt(map, "iBillPrint");
        Model.iBillPrintAuto = convertValueForInt(map, "bBillPrintAuto");
        Model.iPrintFontSize = convertValueForInt(map, "iPrintFontSize");
        //Model.iCarPosLed = carShow.Checked ? 1 : 0; //出入场图片不加水印
        Model.iCarPosCom = convertValueForInt(map, "iCarPosCom");
        //Model.iCarPosLedJH = Convert.ToInt32(Combo5cwjh.Text);   //combo5cwjh 车牌识别
        Model.iCarPosLedLen = convertValueForInt(map, "iCarPosLedLen");
        //Model.iSFLed = ClientS.Checked ? 1 : 0;   //脱机车牌（车牌识别）
        Model.iSFLedCom = convertValueForInt(map, "iSFLedCom");
        Model.iSFLedType = convertValueForInt(map, "iSFLedType");
        Model.iRemainPosLedShowInfo = convertValueForInt(map, "bRemainPosLedShowInfo");
        Model.iRemainPosLedShowPlate = convertValueForInt(map, "bRemainPosLedShowPlate");
        Model.iReLoginPrint = convertValueForInt(map, "bReLoginPrint");
        Model.bOnlyLocation = convertValueForBoolean(map, "bOnlyLocation");
        Model.iBarCodePrint = convertValueForInt(map, "bBarCodePrint");
        //Model.IsCPHAuto = ckbIsCPHAuto.Checked ? 1 : 0;  在线识别月卡不开闸

//        其它设置
        Model.iBillPrint = convertValueForInt(map, "iBillPrint");
        Model.iBillPrintAuto = convertValueForInt(map, "bBillPrintAuto");
        Model.iPrintFontSize = convertValueForInt(map, "iPrintFontSize");
        //Model.iCarPosLed = carShow.Checked ? 1 : 0; //出入场图片不加水印
        Model.iCarPosCom = convertValueForInt(map, "iCarPosCom");
        //Model.iCarPosLedJH = Convert.ToInt32(Combo5cwjh.Text);   //combo5cwjh 车牌识别
        Model.iCarPosLedLen = convertValueForInt(map, "iCarPosLedLen");
        //Model.iSFLed = ClientS.Checked ? 1 : 0;   //脱机车牌（车牌识别）
        Model.iSFLedCom = convertValueForInt(map, "iSFLedCom");
        Model.iSFLedType = convertValueForInt(map, "iSFLedType");
        Model.iRemainPosLedShowInfo = convertValueForInt(map, "bRemainPosLedShowInfo");
        Model.iRemainPosLedShowPlate = convertValueForInt(map, "bRemainPosLedShowPlate");
        Model.iReLoginPrint = convertValueForInt(map, "bReLoginPrint");
        Model.bOnlyLocation = convertValueForBoolean(map, "bOnlyLocation");
        Model.iBarCodePrint = convertValueForInt(map, "bBarCodePrint");
        //Model.IsCPHAuto = ckbIsCPHAuto.Checked ? 1 : 0;  在线识别月卡不开闸
//        车牌参数设置
        Model.iAutoPlateEn = convertValueForInt(map, "bAutoPlateEn");
        Model.iAutoPlateDBJD = convertValueForInt(map, "iAutoPlateDBJD");
        Model.iInAutoOpenModel = convertValueForInt(map, "iInAutoOpenModel");
        Model.iOutAutoOpenModel = convertValueForInt(map, "iOutAutoOpenModel");
        Model.iInMothOpenModel = convertValueForInt(map, "iInMothOpenModel");
        Model.iOutMothOpenModel = convertValueForInt(map, "iOutMothOpenModel");
        Model.iCPHPhoto = convertValueForInt(map, "bCPHPhoto");
        Model.iAutoDeleteImg = convertValueForInt(map, "iAutoDeleteImg");


        Model.iSameCphDelay = convertValueForString(map, "iSameCphDelay") == null ? "0" : (convertValueForString(map, "iSameCphDelay").trim().length() <= 0 ? "0" : convertValueForString(map, "iSameCphDelay"));
        Model.iCarPosLed = convertValueForInt(map, "iCarPosLed") == 0 ? 1 : convertValueForInt(map, "iCarPosLed");


        Model.iAutoKZ = convertValueForInt(map, "iAutoKZ");
        Model.iAutoColorSet = convertValueForInt(map, "iAutoColorSet");
        Model.iAuto0Set = convertValueForInt(map, "iAuto0Set");
        Model.iNoCPHAutoKZ = convertValueForInt(map, "bNoCPHAutoKZ");
        Model.iTempCanNotInSmall = convertValueForInt(map, "bTempCanNotInSmall");
        Model.iAutoCPHDZ = convertValueForInt(map, "bAutoCPHDZ");
        Model.iCentralCharge = convertValueForInt(map, "bCentralCharge");
        Model.iOutCharge = convertValueForInt(map, "bOutCharge");
        Model.iMorePaingCar = convertValueForInt(map, "bMorePaingCar");
        Model.iMorePaingType = convertValueForInt(map, "bMorePaingType");
        Model.iCarPosLedJH = convertValueForInt(map, "iCarPosLedJH");

        Model.iCphDelay = convertValueForString(map, "iCphDelay") == null ? "0" : convertValueForString(map, "iCphDelay");

//        车道设置 --在这里来进行车道的设置，放到车道中即可；
        List<GetCheDaoSetResp.DataBean> cheDaoSet = requestGetCheDaoSet();
        if (cheDaoSet == null) return;
        Model.iChannelCount = cheDaoSet.size();

        for (int i = 0; i < cheDaoSet.size(); i++)
        {
            if (Model.Channels[i] == null)
            {
                Model.Channels[i] = new StructChannel();
            }
            Model.Channels[i].iInOut = cheDaoSet.get(i).getInOut();
            Model.Channels[i].sInOutName = cheDaoSet.get(i).getInOutName();
            Model.Channels[i].iCtrlID = cheDaoSet.get(i).getCtrlNumber();
            Model.Channels[i].iOpenID = cheDaoSet.get(i).getOpenID();
            Model.Channels[i].iOpenType = cheDaoSet.get(i).getOpenType();
            //Model.PubVal.Channels[i].sCarVideo = cheDaoSet.get(i).;
            Model.Channels[i].sCarVideo = cheDaoSet.get(i).getCameraIP();
            Model.Channels[i].sPersonVideo = String.valueOf(cheDaoSet.get(i).getPersonVideo());
            Model.Channels[i].iBigSmall = cheDaoSet.get(i).getBigSmall();
            Model.Channels[i].iCheckPortID = cheDaoSet.get(i).getCheckPortID();
            Model.Channels[i].iOnLine = cheDaoSet.get(i).getOnLine();
            Model.Channels[i].iTempOut = cheDaoSet.get(i).getTempOut();
            Model.Channels[i].iOutCard = cheDaoSet.get(i).getHasOutCard();
            Model.Channels[i].sIDAddress = cheDaoSet.get(i).getCameraIP();
            //Model.PubVal.Channels[i].sIDSignal = ;
            Model.Channels[i].sSubJH = cheDaoSet.get(i).getSubJH();
            Model.Channels[i].iXieYi = cheDaoSet.get(i).getXieYi();
            Model.Channels[i].sIP = cheDaoSet.get(i).getIP();
        }
    }


    private static List<GetCheDaoSetResp.DataBean> requestGetCheDaoSet()
    {
        GetCheDaoSetReq req = new GetCheDaoSetReq();
        req.setToken(Model.token);
        req.setJsonSearchParam(JsonSearchParam.getWhenGetCheDaoSet(String.valueOf(Model.stationID)));
        req.setOrderField(OrderField.getWhenGetCheDaoSet("desc", "asc", "asc"));
        GetCheDaoSetResp resp = GetServiceData.getInstance().GetCheDaoSet(req);
        if (resp == null || resp.getData() == null)
        {
            return null;
        }

        return resp.getData();
    }


    private static boolean convertValueForBoolean(Map<String, Objects> map, String key)
    {
        if (key == null || !map.containsKey(key))
        {
            return false;
        }
        Object o = map.get(key);
        if (Integer.parseInt((String) o) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static int convertValueForInt(Map<String, Objects> map, String key)
    {
        if (key == null || !map.containsKey(key))
        {
            return 0;
        }
        Object o = map.get(key);
        return Integer.parseInt((String) o);
    }

    private static String convertValueForString(Map<String, Objects> map, String key)
    {
        if (key == null || !map.containsKey(key))
        {
            return "";
        }
        Object o = map.get(key);
        return (String) o;
    }
}
