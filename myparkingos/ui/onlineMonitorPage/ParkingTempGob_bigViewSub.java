package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Handler;

import com.example.administrator.myparkingos.constant.CR;
import com.example.administrator.myparkingos.constant.GlobalParams;
import com.example.administrator.myparkingos.constant.JsonSearchParam;
import com.example.administrator.myparkingos.constant.PlateColorEnum;
import com.example.administrator.myparkingos.model.GetServiceData;
import com.example.administrator.myparkingos.model.beans.Model;
import com.example.administrator.myparkingos.model.requestInfo.GetCardTypeDefReq;
import com.example.administrator.myparkingos.model.requestInfo.GetFreeReasonReq;
import com.example.administrator.myparkingos.model.requestInfo.GetParkDiscountJHSetReq;
import com.example.administrator.myparkingos.model.responseInfo.GetCardTypeDefResp;
import com.example.administrator.myparkingos.model.responseInfo.GetFreeReasonResp;
import com.example.administrator.myparkingos.model.responseInfo.GetParkDiscountJHSetResp;
import com.example.administrator.myparkingos.model.responseInfo.GetSysSettingObjectResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutResp;
import com.example.administrator.myparkingos.util.L;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017-04-12.
 */
public class ParkingTempGob_bigViewSub extends ParkingTempGob_bigView
{
    private SetCarOutResp.DataBean mModel;
    private int mIndex;
    private PlateColorEnum plateColor;
    private String mFileJpg;
    private Handler mHandler;

    public ParkingTempGob_bigViewSub(
            Activity activity
            , ParkingTempGob_bigView.E_VIEW_TYPE viewType
            , PlateColorEnum colorEnum
            , int index
            , SetCarOutResp.DataBean model
            , String fileJpg
            , Handler handler
    )
    {
        super(activity, viewType);
        this.mModel = model;
        this.mIndex = index;
        this.plateColor = colorEnum;
        this.mFileJpg = fileJpg;
        this.mHandler = handler;
    }

    public void InitDataSource()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                GetFreeReasonResp getFreeReasonResp = requestGetFreeReason();
                if (getFreeReasonResp == null) return;
                final List<GetFreeReasonResp.DataBean> reasonList = getFreeReasonResp.getData();
                reasonList.add(0, new GetFreeReasonResp.DataBean());
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        setReasonList(reasonList);
                    }
                });

                GetParkDiscountJHSetResp getParkDiscountJHSetResp = requestGetParkDiscountJHSet();
                if (getFreeReasonResp == null) return;
                final List<GetParkDiscountJHSetResp.DataBean> data1 = getParkDiscountJHSetResp.getData();
                data1.add(0, new GetParkDiscountJHSetResp.DataBean());
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        setDiscountAddressList(data1);
                    }
                });
            }
        }).start();
    }

    @Override
    public void loadedDataWhenHaveInRecord()
    {
        InitDataSource(); // 更新原因和打折地点
        initControlStatusBySysSetting();
        // 显示图片数据，输入默认，输出即为传递的图片
        if (new File(mFileJpg).exists())
        {
            setCarInPicture(BitmapFactory.decodeFile(mFileJpg)); // mFileJpg即为传入图片的路径
        }
        showDataToControl();
    }

    @Override
    public void loadedDataWhenNotInRecord()
    {
        if (mModel == null)
        {
            L.i("mModel 为空");
            return;
        }
        updateViewBySetOutResp();

        setNeedMoneyOnFinal(String.valueOf(mModel.getYSJE()) !=null?String.valueOf(mModel.getYSJE()):"0.0");

        String cardType = getCmdCardType();
        if ((CR.GetCardType(cardType, 0).substring(0, 3) == "Tmp" || CR.GetCardType(cardType, 0).substring(0, 3) == "Mtp") &&
                Model.Channels[mIndex].iBigSmall == 0 && Model.iBillPrint == 1 && Model.iBillPrintAuto > 0 && Double.parseDouble(getSYJE()) > 0)
        {
//           btnPrint_Click(null, null);
        }

//        cmbCardType.SelectionChanged += cmbCardType_SelectionChanged;

        //没有启用收费窗口可更改卡类或者启用了且选择了播报语音则在打开收费窗口时报价
        if (Model.iSetTempCardType <= 0 || Model.iModiTempType_VoiceSF > 0)
        {
            ReportCharge();
        }
//
//        //如果启用了自动关闭收费窗口功能则启动定时器
//        if (Model. > 0)
//        {
//            tmrCloseForm = new DispatcherTimer();
//            tmrCloseForm.Interval = new TimeSpan(0, 0, 1);
//            tmrCloseForm.Tick += tmrCloseForm_Tick;
//            tmrCloseForm.Start();
//        }
    }

    private void ReportCharge()
    {
        if (Model.iCtrlShowPlate == 1 || Model.iCtrlShowStayTime == 1)
        {
            // 发送语音
//            cardType = cmbCardType.SelectedItem as CardTypeDef;
//            tmNow = DateTime.Now;
//
//            outTime = outRecord.OutTime.ToString("yyyy-MM-dd HH:mm:ss");
//            inTime = (outRecord.InTime ?? outRecord.OutTime).ToString("yyyy-MM-dd HH:mm:ss");
//
//            //outTime = (outRecord.OutTime ?? outRecord.InTime ?? tmNow).ToString("yyyy-MM-dd HH:mm");
//            //inTime = (outRecord.InTime ?? outRecord.OutTime ?? tmNow).ToString("yyyy-MM-dd HH:mm");
//            stime = Convert.ToDateTime(outTime) - Convert.ToDateTime(inTime);
//            strTime = stime.Days.ToString("X4") + stime.Hours.ToString("X2") + stime.Minutes.ToString("00");
//            if (Model.iCtrlShowStayTime == 0)
//            {
//                strTime = "FFFF";
//            }
//
//            cardType = cmbCardType.SelectedItem as CardTypeDef;
//            vSender.VoiceDisplay(ParkingCommunication.VoiceType.OutGateVoice, modulus, (null != cardType ? cardType.Identifying : outRecord.CardType),
//                    outRecord.CPH, 0, "", 0, Convert.ToDecimal(lblSFJE.Content), txtSFJE.Value, strTime);
        }
        else
        {
            String MyTempMoney = "";
            // 按照指定方式来拼接字符串，发送到控制板
//            if (Model.iXsd == 0)
//            {
//                if (Model.iChargeType == 3)
//                {
//                    if (Model.iXsdNum == 1)
//                    {
//                        MyTempMoney = (Convert.ToInt32(txtSFJE.Value * 10)).ToString("X4");
//                    }
//                    else
//                    {
//                        if (txtSFJE.Value > 655)
//                        {
//                            string strQIAN = CR.MoneyToChinese(txtSFJE.Text.ToString());
//                            string strsLoad = "73" + CR.GetChineseMoney(strQIAN) + "74";
//
//                            vSender.LoadLsNoX2010znykt(modulus, strsLoad);
//
//                            SurplusCPH();
//                            return;
//                        }
//                        MyTempMoney = (Convert.ToInt32(txtSFJE.Value * 100)).ToString("X4");
//                    }
//                }
//                else
//                {
//                    MyTempMoney = Convert.ToInt32(txtSFJE.Value).ToString("X4");
//                }
//            }
//            else
//            {
//                MyTempMoney = (Convert.ToInt32(txtSFJE.Value * 10)).ToString("X4");
//            }
//
//            if (Model.bSfDec)
//            {
//                //string strRst = "";
//                //// string strRst = sendbll.ShowLed(Model.Channels[modulus].iCtrlID, MyTempMoney, Model.Channels[modulus].iXieYi);//开闸
//                //CR.SendVoice.LedShow2010znykt(axznykt_1, Model.Channels[modulus].iCtrlID, Model.Channels[modulus].sIP, MyTempMoney, m_nSerialHandle, Model.Channels[modulus].iXieYi);
//
//                vSender.LedShow2010znykt(modulus, MyTempMoney);
//            }
//            else
//            {
//                //CR.SendVoice.LedShow2010znykt(axznykt_1, Model.Channels[modulus].iCtrlID, Model.Channels[modulus].sIP, MyTempMoney, m_nSerialHandle, Model.Channels[modulus].iXieYi);
//
//                vSender.LedShow2010znykt(modulus, MyTempMoney);
//            }
        }
    }

    private void showDataToControl()
    {
        if (mModel == null) return;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                final GetCardTypeDefResp typeDefResp = requestGetCarTypeDef(mModel.getCardType());
                if (typeDefResp == null) return;
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        setCardTypeSpinner(typeDefResp.getData());
                    }
                });
            }


        }).start();

        setCPHInHaveCarInRecord(mModel.getCPH());
        setCPHInHaveCarInRecord(mModel.getCPH());

        setInTime((null == mModel.getInTime()) ? "" : mModel.getInTime());
        setOutTime(mModel.getOutTime());
        setDuringTime(mModel.getStayTime());
        setChargeMoney(String.valueOf(mModel.getSFJE())); //设置停车收费
        setNeedMoneyOnFinal(String.valueOf(mModel.getYSJE()));// 应收车费

        // 车辆类型
        String selectCardType = mModel.getCardType();
        setCardTypeSpinnerIndexByText(selectCardType);

    }

    private void updateViewBySetOutResp()
    {
        setCPHInNoInRecord(mModel.getCPH());
        if (mModel.getSimilarCPHIssued() != null && mModel.getSimilarCPHIssued().size() > 0)
        {
            setListViewCarIssueData(mModel.getSimilarCPHIssued());
        }

        if (mModel.getSimilarCPHInPark() != null && mModel.getSimilarCPHInPark().size() > 0)
        {
            setListViewCarInPark(mModel.getSimilarCPHInPark());
        }
        if ((mModel.getSimilarCPHInPark() != null && mModel.getSimilarCPHInPark().size() > 0)
                || (mModel.getSimilarCPHIssued() != null && mModel.getSimilarCPHIssued().size() > 0))
        {
            setConfirmButtonEnable(true);
        }
        else
        {
            setConfirmButtonEnable(false);
        }
    }


    public void setCallback(ICallBack callback)
    {
        this.callback = callback;
    }

    private ICallBack callback;

    public interface ICallBack
    {
        void updateAppearanceRecord(SetCarOutResp.DataBean appearanceModel, int laneIndex, String localImagePath);

        void tempGob_big_Photo(String count);

        void binData(String CardType, double SFJE);
    }

    /**
     * 请求服务器获取免费原因
     *
     * @return
     */
    private GetFreeReasonResp requestGetFreeReason()
    {
        GetFreeReasonReq reasonReq = new GetFreeReasonReq();
        reasonReq.setToken(Model.token);
        GetFreeReasonResp freeReason = GetServiceData.getInstance().GetFreeReason(reasonReq, "ItemID");
        if (freeReason == null || freeReason.getData() == null)
            return null;
        return freeReason;
    }

    /**
     * 请求服务器获取打折地点
     *
     * @return
     */
    private GetParkDiscountJHSetResp requestGetParkDiscountJHSet()
    {
        GetParkDiscountJHSetReq jhSetReq = new GetParkDiscountJHSetReq();
        jhSetReq.setToken(Model.token);
        GetParkDiscountJHSetResp itemID = GetServiceData.getInstance().GetParkDiscountJHSet(jhSetReq, "Address");
        if (itemID == null || itemID.getData() == null)
            return null;
        return itemID;
    }

    /**
     * 请求服务器获取车辆类型
     *
     * @param cardType
     * @return
     */
    private GetCardTypeDefResp requestGetCarTypeDef(String cardType)
    {
        GetCardTypeDefReq typeDefReq = new GetCardTypeDefReq();
        typeDefReq.setToken(Model.token);
        typeDefReq.setJsonSearchParam(JsonSearchParam.getWhenGetCarTypeDef(cardType));
        GetCardTypeDefResp resp = GetServiceData.getInstance().GetCardTypeDef(typeDefReq, "Identifying");
        if (resp == null || resp.getData() == null)
            return null;
        return resp;
    }
}
