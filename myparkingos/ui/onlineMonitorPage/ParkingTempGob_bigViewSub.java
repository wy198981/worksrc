package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.administrator.myparkingos.constant.CR;
import com.example.administrator.myparkingos.constant.JsonSearchParam;
import com.example.administrator.myparkingos.constant.PlateColorEnum;
import com.example.administrator.myparkingos.constant.RCodeEnum;
import com.example.administrator.myparkingos.model.GetServiceData;
import com.example.administrator.myparkingos.model.beans.Model;
import com.example.administrator.myparkingos.model.requestInfo.CaclChargeAmountReq;
import com.example.administrator.myparkingos.model.requestInfo.CancelChargeReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCardTypeDefReq;
import com.example.administrator.myparkingos.model.requestInfo.GetFreeReasonReq;
import com.example.administrator.myparkingos.model.requestInfo.GetParkDiscountJHSetReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarOutReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarOutWithoutEntryRecordReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeAmountReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeAsFreeReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeInfoReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeWithCaptureImageReq;
import com.example.administrator.myparkingos.model.responseInfo.CaclChargeAmountResp;
import com.example.administrator.myparkingos.model.responseInfo.CancelChargeResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCardTypeDefResp;
import com.example.administrator.myparkingos.model.responseInfo.GetFreeReasonResp;
import com.example.administrator.myparkingos.model.responseInfo.GetParkDiscountJHSetResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutWithoutEntryRecordResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeAmountResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeAsFreeResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeInfoResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeWithCaptureImageResp;
import com.example.administrator.myparkingos.myUserControlLibrary.MessageBox;
import com.example.administrator.myparkingos.util.L;
import com.example.administrator.myparkingos.util.SDCardUtils;
import com.example.administrator.myparkingos.util.TimeConvertUtils;

import java.io.File;
import java.util.Date;
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
    private Activity mActivity;
    private List<GetFreeReasonResp.DataBean> reasonList;
    private List<GetParkDiscountJHSetResp.DataBean> discountJHSetList;
    private List<GetCardTypeDefResp.DataBean> typeDefRespData;

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
        this.mActivity = activity;
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
                reasonList = getFreeReasonResp.getData();
                reasonList.add(0, new GetFreeReasonResp.DataBean(0, ""));
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
                discountJHSetList = getParkDiscountJHSetResp.getData();
                discountJHSetList.add(0, new GetParkDiscountJHSetResp.DataBean(0, "", 0L, ""));
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        setDiscountAddressList(discountJHSetList);
                    }
                });
            }
        }).start();
    }

    @Override
    public void loadedDataWhenHaveInRecord()
    {
        super.loadedDataWhenHaveInRecord();
        InitDataSource(); // 更新原因和打折地点
        // 显示图片数据，输入默认，输出即为传递的图片
        if (new File(mFileJpg).exists())
        {
            setCarInPicture(BitmapFactory.decodeFile(mFileJpg)); // mFileJpg即为传入图片的路径
        }
        showDataToControl();

        setNeedMoneyOnFinal(String.valueOf(mModel.getYSJE()) != null ? String.valueOf(mModel.getYSJE()) : "0.0");

        String cardType = getCmdCardType();
        if ((CR.GetCardType(cardType, 0).substring(0, 3) == "Tmp" || CR.GetCardType(cardType, 0).substring(0, 3) == "Mtp") &&
                Model.Channels[mIndex].iBigSmall == 0 && Model.iBillPrint == 1 && Model.iBillPrintAuto > 0 && Double.parseDouble(getStopPayMoney()) > 0)
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
//        if (Model.iAutoMinutes > 0)
//        {
//            tmrCloseForm = new DispatcherTimer();
//            tmrCloseForm.Interval = new TimeSpan(0, 0, 1);
//            tmrCloseForm.Tick += tmrCloseForm_Tick;
//            tmrCloseForm.Start();
//        }

//        autoCloseWindowInMinutes();
    }

    public void autoCloseWindowInMinutes()
    {
        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                cancel();
            }
        }, 1 * 5000);
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

//        SetImageSource(imgIn, Properties.Resources.Car2);
//
//        if (System.IO.File.Exists(localImagePath))
//        {
//            SetImageSource(imgOut, localImagePath);
//        }
//        else
//            SetImageSource(imgOut, Properties.Resources.Car2);

    }

    private void ReportCharge()
    {
        String strTime;
        if (Model.iCtrlShowPlate == 1 || Model.iCtrlShowStayTime == 1)
        {
            String cardType = getCmdCardType();

//            outTime = outRecord.OutTime.ToString("yyyy-MM-dd HH:mm:ss");
//            inTime = (outRecord.InTime ?? outRecord.OutTime).ToString("yyyy-MM-dd HH:mm:ss");
//
//            stime = Convert.ToDateTime(outTime) - Convert.ToDateTime(inTime);
//            strTime = stime.Days.ToString("X4") + stime.Hours.ToString("X2") + stime.Minutes.ToString("00"); //计算两个时间的差
            if (Model.iCtrlShowStayTime == 0)
            {
                strTime = "FFFF";
            }

//            vSender.VoiceDisplay(ParkingCommunication.VoiceType.OutGateVoice, modulus, (null != cardType ? cardType.Identifying : outRecord.CardType),
//                    outRecord.CPH, 0, "", 0, Convert.ToDecimal(lblSFJE.Content), txtSFJE.Value, strTime);
        }
        else
        {
            String MyTempMoney = ""; // 按照指定方式来拼接字符串，发送到控制板
            if (Model.iXsd == 0)
            {
                if (Model.iChargeType == 3)
                {
                    if (Model.iXsdNum == 1)
                    {
//                        MyTempMoney = (Convert.ToInt32(txtSFJE.Value * 10)).ToString("X4");
                    }
                    else
                    {
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
                    }
                }
                else
                {
//                    MyTempMoney = Convert.ToInt32(txtSFJE.Value).ToString("X4");
                }
            }
            else
            {
//                MyTempMoney = (Convert.ToInt32(txtSFJE.Value * 10)).ToString("X4");
            }
            if (Model.bSfDec)
            {
//                vSender.LedShow2010znykt(modulus, MyTempMoney);
            }
            else
            {
//                vSender.LedShow2010znykt(modulus, MyTempMoney);
            }
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
                GetCardTypeDefResp typeDefResp = requestGetCarTypeDef(mModel.getCardType());
                if (typeDefResp == null || typeDefResp.getData() == null) return;

                typeDefRespData = typeDefResp.getData();
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        setCardTypeSpinner(typeDefRespData);
                    }
                });
            }


        }).start();

        setCPHInHaveCarInRecord(mModel.getCPH());
        setCPHInNoInRecord(mModel.getCPH());

        setInTime((null == mModel.getInTime()) ? "" : mModel.getInTime());
        setOutTime(mModel.getOutTime());
        setDuringTime(mModel.getStayTime());
        setChargeMoney(String.valueOf(mModel.getSFJE())); //设置停车收费
        setNeedMoneyOnFinal(String.valueOf(mModel.getYSJE()));// 应收车费

        if (TextUtils.isEmpty(mModel.getStayTime()))
        {
            if (TextUtils.isEmpty(mModel.getInTime()))
            {
                setDuringTime("0分");// inTime为空，且getStayTime还没有时间时,即stayTime设置为0;
            }
        }

        // 车辆类型
        String selectCardType = mModel.getCardType();
        setCardTypeSpinnerIndexByText(selectCardType);

        L.i("selectCardType:" + selectCardType + "CR.GetCardType(selectCardType, 0):" + CR.GetCardType(selectCardType, 1));
        setCarTypeByChinese(CR.GetCardType(selectCardType, 1));
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

    @Override
    public void onClickPrintBill()//打印小票
    {
    }

    @Override
    public void onClickNoRecordPass() //没有入场记录
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                SetCarOutWithoutEntryRecordReq req = new SetCarOutWithoutEntryRecordReq();
                req.setToken(Model.token);
                req.setCPH(getCPHInNotRecord());
                req.setCtrlNumber(mModel.getCtrlNumber());
                req.setStationId(Model.stationID);
                req.setCPColor(mModel.getCPColor());

                final SetCarOutWithoutEntryRecordResp resp = GetServiceData.getInstance().SetCarOutWithoutEntryRecord(req);
                if (resp == null || resp.getData() == null) return;
                RCodeEnum rCodeEnum = RCodeEnum.valueOf(Integer.parseInt(resp.getRcode()));
                resp.getRcode();
                switch (rCodeEnum)
                {
                    case OK:
                    case RepeatAdmission:
                    case MthBeOverdueToTmpCharge:
                        break;
                    default:
                        MessageBox.show(mActivity, resp.getMsg());
                        return;
                }
                mModel = resp.getData();

                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (resp.getData().getSFJE() > 0)
                        {

                            mType = E_VIEW_TYPE.E_VIEW_CHARGE_HAVA_RECORD;
                            showViewByType(E_VIEW_TYPE.E_VIEW_CHARGE_HAVA_RECORD);

                            initControlStatusBySysSetting();
                            showDataToControl();
                            InitDataSource(); // 更新原因和打折地点
                            // 显示图片数据，输入默认，输出即为传递的图片
                            if (new File(mFileJpg).exists())
                            {
                                setCarInPicture(BitmapFactory.decodeFile(mFileJpg)); // mFileJpg即为传入图片的路径
                            }

                            setNeedMoneyOnFinal(String.valueOf(mModel.getYSJE()) != null ? String.valueOf(mModel.getYSJE()) : "0.0");


                            String cardType = getCmdCardType();
                            if ((CR.GetCardType(cardType, 0).substring(0, 3) == "Tmp" || CR.GetCardType(cardType, 0).substring(0, 3) == "Mtp") &&
                                    Model.Channels[mIndex].iBigSmall == 0 && Model.iBillPrint == 1 && Model.iBillPrintAuto > 0 && Double.parseDouble(getStopPayMoney()) > 0)
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
//        if (Model.iAutoMinutes > 0)
//        {
//            tmrCloseForm = new DispatcherTimer();
//            tmrCloseForm.Interval = new TimeSpan(0, 0, 1);
//            tmrCloseForm.Tick += tmrCloseForm_Tick;
//            tmrCloseForm.Start();
//        }

//        autoCloseWindowInMinutes();
                            return;
                        }
                        String strRst = "";
//        for (int i = 0; i <= 4; i++)  //2015-10-13
//        {
//            strRst = vSender.SendOpen(modulus);
//            if (strRst == "0")   //开闸成功
//            {
//                if (Model.bOut485)//不加延时摄像机播报不了这个语音 控制板正常
//                {
//                    System.Threading.Thread.Sleep(30);
//                }
//                vSender.VoiceDisplay(ParkingCommunication.VoiceType.TempOutOpen, modulus);
//                break;
//            }
//            ParkingInterface.CR.DelaySec(300);
//        }

                        if (!strRst.equals("2"))
                        {
                            if (callback != null)
                            {
                                mModel.setYSJE(Double.parseDouble(getStopPayMoney()));
                                mModel.setYSJE(Double.parseDouble(getNeedPayMoney()));
                                mModel.setCardType(CR.GetCardType(getCmdCardType(), 0));
                                callback.updateAppearanceRecord(mModel, mIndex, mFileJpg);
                            }
                            cancel();
                        }
                    }
                });

            }
        }).start();
    }

    @Override
    public void onClickConfirmed() // 确认按钮
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                SetCarOutReq req = new SetCarOutReq();
                req.setToken(Model.token);
                req.setCPH(getCPHInNotRecord());
                req.setCtrlNumber(Model.Channels[mIndex].iCtrlID);
                req.setCPColor(plateColor.getColorValue());
                req.setStationId(Model.stationID);
                SetCarOutResp setCarOutResp = GetServiceData.getInstance().SetCarOut(req); // 再次请求
                if (setCarOutResp == null || setCarOutResp.getData() == null)
                {
                    return;
                }

                int parseInt = Integer.parseInt(setCarOutResp.getRcode());
                RCodeEnum code = RCodeEnum.valueOf(parseInt);
                switch (code)
                {
                    case OK:
                        break;
                    default:
                        MessageBox.show(mActivity, setCarOutResp.getMsg());
                        return;
                }

                mModel = setCarOutResp.getData();
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        initControlStatusBySysSetting();
                        showDataToControl();
                        InitDataSource(); // 更新原因和打折地点


//                        setInTime((null == mModel.getInTime()) ? "" : mModel.getInTime());
//                        setOutTime(mModel.getOutTime());
//                        setDuringTime(mModel.getStayTime());
//                        setChargeMoney(String.valueOf(mModel.getSFJE())); //设置停车收费
//                        setNeedMoneyOnFinal(String.valueOf(mModel.getYSJE()));// 应收车费

                        //        lblCPH.Content = txtCPH0.Text;
//        lblInDateTime.Content = (null == outRecord.InTime ? "" : outRecord.InTime.Value.ToString("yyyy-MM-dd HH:mm:ss"));
//        lblOutDateTime.Content = outRecord.OutTime.ToString("yyyy-MM-dd HH:mm:ss");
//        cmbCardType.Text = CR.GetCardType(outRecord.CardType, 1);
//        lblSFJE.Content = outRecord.SFJE;
//        txtSFJE.Text = outRecord.YSJE.ToString();
//        lblTime.Content = outRecord.StayTime.ToString();
//        DateTime InTime = outRecord.InTime ??outRecord.OutTime;
//        DateTime OutTime = outRecord.OutTime != null ? outRecord.OutTime : InTime;
//        TimeSpan stime = Convert.ToDateTime(OutTime.ToString("yyyy-MM-dd HH:mm")) - Convert.ToDateTime(InTime.ToString("yyyy-MM-dd HH:mm"));
//        outRecord.StayTime = (stime.Days > 0 ? stime.Days + "天" : "") + (stime.Hours > 0 ? stime.Hours + "小时" : "") + (stime.Minutes > 0 ? stime.Minutes + "分" : "");
//        lblTime.Content = outRecord.StayTime;
                        // 计算两个时间相隔的时间

                        // 车辆类型
                        String selectCardType = mModel.getCardType();
                        setCardTypeSpinnerIndexByText(selectCardType);

                        L.i("selectCardType:" + selectCardType + "CR.GetCardType(selectCardType, 0):" + CR.GetCardType(selectCardType, 1));
                        setCarTypeByChinese(CR.GetCardType(selectCardType, 1));

                        boolean IsFoue = false;
                        if (mModel.getCardType().substring(0, 3) == "Tmp" || mModel.getCardType().substring(0, 3) == "Mtp")
                        {
                            if (IsFoue == false && (Model.iSetTempCardType == 0 || (Model.iSetTempCardType == 1 && Model.iModiTempType_VoiceSF == 1)))  //2016-05-07 zsd add Model.bSetTempCardType == false
                            {
                                ReportCharge();
                            }
                        }
                        else
                        {
                            //CPHDataHandler(txtCPH0.Text, Convert.ToInt32(modulus));
                        }
                        mType = E_VIEW_TYPE.E_VIEW_CHARGE_HAVA_RECORD;
                        showViewByType(E_VIEW_TYPE.E_VIEW_CHARGE_HAVA_RECORD);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClickGiveUp()
    {
        super.onClickGiveUp();
        MessageBox.showCanSetCallback(mActivity, "请确认是否放弃开闸？\t\r若点击【是】，道闸将不会打开，但收费记录仍将保存，放弃开闸不能作为未收费的依据！！", new MessageBox.IMessageBoxListener()
        {
            @Override
            public void onClickPositive()
            {
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (Model.iSetTempMoney == 1)
                        {
                            UpdateChargeAmountResp resp = requestUpdateChargeAmount();
                            if (resp == null) return;
                            if (resp.getData() <= 0)
                            {
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        MessageBox.show(mActivity, "提交服务失败");
                                    }
                                });
                            }
                        }
                        else if (Model.iSetTempCardType == 1 || Model.iDiscount == 1)
                        {
                            GetParkDiscountJHSetResp.DataBean dzItem = null;
                            GetCardTypeDefResp.DataBean cardItem = null;
                            if (Model.iDiscount == 1)
                            {
                                if (discountJHSetList != null && discountJHSetList.size() > 0)
                                {
                                    dzItem = discountJHSetList.get(getDiscountListIndex());
                                }
                            }
                            if (Model.iSetTempCardType == 1)
                            {
                                if (typeDefRespData != null && typeDefRespData.size() > 0)
                                {
                                    cardItem = typeDefRespData.get(getCardTypeListIndex());
                                }
                            }

                            UpdateChargeInfoResp infoResp = requestUpdateChargeInfo(dzItem, cardItem);
                            if (infoResp == null) return;
                            if (infoResp.getData() <= 0)
                            {
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        MessageBox.show(mActivity, "提交服务失败");
                                    }
                                });
                            }

                            String strRst = "0";
                            for (int i = 0; i <= 4; i++)  //2015-10-13
                            {
//                                    strRst = vSender.SendOpen(modulus);
//                                    if (strRst == "0")   //开闸成功
//                                    {
//                                        if (Model.bOut485)//不加延时摄像机播报不了这个语音 控制板正常
//                                        {
//                                            System.Threading.Thread.Sleep(30);
//                                        }
//                                        vSender.VoiceDisplay(ParkingCommunication.VoiceType.TempOutOpen, modulus);
//                                        break;
//                                    }
//                                    ParkingInterface.CR.DelaySec(300);
                            }

                            if (!strRst.equals("2"))
                            {
                                if (callback != null)
                                {
                                    mModel.setYSJE(Double.parseDouble(getStopPayMoney()));
                                    mModel.setYSJE(Double.parseDouble(getNeedPayMoney()));
                                    mModel.setCardType(CR.GetCardType(getCmdCardType(), 0));
                                    callback.updateAppearanceRecord(mModel, mIndex, mFileJpg);

                                }
                            }
                        }
                        mHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                cancel();
                            }
                        });

                    }
                }).start();
            }

            @Override
            public void onClickNegative()
            {
//                cancel();
            }
        });
    }

    @Override
    public void onClickChargeFree()// 免费放行
    {
        MessageBox.showCanSetCallback(mActivity, "请确认是否免费放行？\t\r若点击【是】，该车辆将被免费放行！", new MessageBox.IMessageBoxListener()
        {
            @Override
            public void onClickPositive()// 点击ok
            {
                String strRst = "0";
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        UpdateChargeAsFreeResp resp = requestUpdateChargeAsFree();
                        if (resp == null) return;
                        if (resp.getData() <= 0)
                        {
                            mHandler.post(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MessageBox.show(mActivity, "提交数据到服务器失败");
                                }
                            });
                        }
                    }
                }).start();

                for (int i = 0; i <= 4; i++)  //2015-10-13
                {
//                    strRst = vSender.SendOpen(modulus);
//                    if (strRst == "0")   //开闸成功
//                    {
//                        if (Model.bOut485)//不加延时摄像机播报不了这个语音 控制板正常
//                        {
//                            System.Threading.Thread.Sleep(30);
//                        }
//                        vSender.VoiceDisplay(ParkingCommunication.VoiceType.TempOutOpen, modulus);
//                        break;
//                    }
//                    ParkingInterface.CR.DelaySec(300);
                }

                if (!strRst.equals("2"))
                {
                    if (callback != null)
                    {
                        mModel.setYSJE(Double.parseDouble(getStopPayMoney()));
                        mModel.setYSJE(Double.parseDouble(getNeedPayMoney()));
                        mModel.setCardType(CR.GetCardType(getCmdCardType(), 0));
                        callback.updateAppearanceRecord(mModel, mIndex, mFileJpg);
                    }
                    cancel();
                }
            }

            @Override
            public void onClickNegative()
            {
                cancel();
            }
        });

    }

    private UpdateChargeAsFreeResp requestUpdateChargeAsFree()
    {
        UpdateChargeAsFreeReq req = new UpdateChargeAsFreeReq();
        req.setToken(Model.token);
        req.setCardNO(mModel.getCardNO());
        req.setCardType(CR.GetCardType(getTextViewCardType(), 0));

        String yyyyMMddHHmmss;
        if (TextUtils.isEmpty(getOutTime()))
        {
            yyyyMMddHHmmss = TimeConvertUtils.longToString("yyyyMMddHHmmss", System.currentTimeMillis());
        }
        else
        {
            yyyyMMddHHmmss = toNetNeedFormatString(getOutTime());
        }

        req.setOutTime(yyyyMMddHHmmss);
        req.setFreeReason(getSelectReason()); //文本为中文，需要进行字符编码;
        UpdateChargeAsFreeResp resp = GetServiceData.getInstance().UpdateChargeAsFree(req);
        return resp;
    }


    @Override
    public void onClickPicCapture() // 图像抓拍
    {
        super.onClickPicCapture();
        String strRst = "0";

        testUploadPicture();

//        testUploadPicture();
//        CardTypeDef ctDef;
//        ParkDiscountJHSet discountSet = null;
//        List<QueryConditionGroup> lstCondition;
//        Dictionary<string, object> dicFieldValue;


//        mHandler.post(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                MessageBox.show(mActivity, "请确认是否免费放行？\t\r若点击【是】，该车辆将被免费放行！");
//            }
//        });
//
//        if (callback != null)
//        {
//            callback.tempGob_big_Photo(Model.sIDCaptureCard); //构建上传的路径
//        }
//
//        //显示抓拍窗口
//        ImageCaptureView frm = new ImageCaptureView(mActivity); // 注意获取图片的数据Bitmap,应该是从相机中获取图片数据 bmpIDPhoto获取Bitmap数据
//        frm.show();

        // 这里使用上传图片的接口 将图片上传到服务器上;
//        int ret = req.SendIDPhoto(Model.token, bmpIDPhoto, outRecord.CardNO, CR.GetCardType(lblCardType.Content.ToString(), 0), Convert.ToDateTime(lblOutDateTime.Content));
//        if (ret <= 0)
//        {
//            MessageBox.show(mActivity, "提交数据到服务器失败");
//            return;
//        }

//        for (int i = 0; i <= 4; i++)  //2015-10-13
//        {
////            strRst = vSender.SendOpen(modulus);
////            if (strRst == "0")   //开闸成功
////            {
////                if (Model.bOut485)//不加延时摄像机播报不了这个语音 控制板正常
////                {
////                    System.Threading.Thread.Sleep(30);
////                }
////                vSender.VoiceDisplay(ParkingCommunication.VoiceType.TempOutOpen, modulus);
////                break;
////            }
////            ParkingInterface.CR.DelaySec(300);
//        }
//
//        cancel();
////        catch (Exception ex)
////        {
////            req.AddLog(this.Title + ":btnPapers_Click", ex.Message + "\r\n" + ex.StackTrace);
////            MessageBox.Show(ex.Message + "\r\n" + ":btnPapers_Click", "错误", MessageBoxButton.OK, MessageBoxImage.Error);
////        }
    }

    private void testUploadPicture()
    {
        //上传bitmap到服务器也是可以的
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String file = SDCardUtils.getSDCardPath() + "TESO_GetFaceFeature1111.jpg";
                Bitmap bitmap = BitmapFactory.decodeFile(file);
                requestUpdateChargeWithCaptureImage(bitmap);
            }
        }).start();
    }

    private void requestUpdateChargeWithCaptureImage(Bitmap bitmap)
    {
        UpdateChargeWithCaptureImageReq req = new UpdateChargeWithCaptureImageReq();
        req.setToken(Model.token);
        req.setCardNO(mModel.getCardNO());
        req.setCardType(CR.GetCardType(getTextViewCardType(), 0));
        req.setOutTime(toNetNeedFormatString(getOutTime()));
        UpdateChargeWithCaptureImageResp resp = GetServiceData.getInstance().UpdateChargeWithCaptureImage(req, bitmap);
        if (resp != null)
        {
            L.i("UpdateChargeWithCaptureImageResp:" + resp.toString());
        }
    }


    /// <summary>
    /// 支付方式，0现金，1微信，2支付宝
    /// </summary>
    private int iPayType = 0;// 从界面上可以获得

    @Override
    public void onClickCutOff()// 开闸放行
    {
        super.onClickCutOff();

//        if ((DateTime.Now - tmLastKeyUp).TotalMilliseconds < 100)    //扫描枪的输入速度才会这么快 ????
//        {
//            return;
//        }

        if (Model.iDetailLog == 1)
        {
            final String title = super.title;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    GetServiceData.getInstance().requestAddOptLog(title, "临时卡开闸发行！收费：" + getNeedPayMoney() + "元");
                }
            }).start();
        }

        if (Model.iSetTempMoney == 1)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    UpdateChargeAmountResp amountResp = requestUpdateChargeAmount();
                    if (amountResp == null) return;
                    int ret = amountResp.getData();
                    if (ret <= 0)
                    {
                        mHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                MessageBox.show(mActivity, "提交服务失败");
                            }
                        });
                        return;
                    }
                }
            }).start();
        }
        else if (Model.iSetTempCardType == 1 || Model.iDiscount == 1)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    GetParkDiscountJHSetResp.DataBean dzItem = null;
                    GetCardTypeDefResp.DataBean cardItem = null;
                    if (Model.iDiscount == 1)
                    {
                        if (discountJHSetList != null && discountJHSetList.size() > 0) // 地点
                        {
                            dzItem = discountJHSetList.get(getDiscountListIndex());
                        }
                    }
                    if (Model.iSetTempCardType == 1)
                    {
                        if (typeDefRespData != null && typeDefRespData.size() > 0)
                        {
                            cardItem = typeDefRespData.get(getCardTypeListIndex());
                        }
                    }

                    UpdateChargeInfoResp infoResp = requestUpdateChargeInfo(dzItem, cardItem);
                    if (infoResp == null) return;
                    if (infoResp.getData() <= 0)
                    {
                        mHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                MessageBox.show(mActivity, "提交服务失败");
                            }
                        });
                        return;
                    }
                }
            }).start();
        }

        String strRst = "0";
        for (int i = 0; i <= 4; i++)  //2015-10-13
        {
//            strRst = vSender.SendOpen(modulus);
//            if (strRst == "0")   //开闸成功
//            {
//                if (Model.bOut485)//不加延时摄像机播报不了这个语音 控制板正常
//                {
//                    System.Threading.Thread.Sleep(30);
//                }
//                vSender.VoiceDisplay(ParkingCommunication.VoiceType.TempOutOpen, modulus);
//                break;
//            }
//            ParkingInterface.CR.DelaySec(300);
        }

        if (!strRst.equals("2"))
        {
            if (callback != null)
            {
                mModel.setYSJE(Double.parseDouble(getStopPayMoney()));
                mModel.setYSJE(Double.parseDouble(getNeedPayMoney()));
                mModel.setCardType(CR.GetCardType(getCmdCardType(), 0));
                callback.updateAppearanceRecord(mModel, mIndex, mFileJpg);
            }
            cancel();
        }
    }

    private UpdateChargeInfoResp requestUpdateChargeInfo(GetParkDiscountJHSetResp.DataBean dzItem, GetCardTypeDefResp.DataBean cardItem)
    {
        UpdateChargeInfoReq req = new UpdateChargeInfoReq();
        req.setToken(Model.token);
        req.setCardNO(mModel.getCardNO());
        req.setCardType(CR.GetCardType(getTextViewCardType(), 0));

        String yyyyMMddHHmmss;
        if (mModel.getOutTime() == null)
        {
            yyyyMMddHHmmss = TimeConvertUtils.longToString("yyyyMMddHHmmss", System.currentTimeMillis());
        }
        else
        {
            yyyyMMddHHmmss = toNetNeedFormatString(mModel.getOutTime());
        }

        req.setOutTime(yyyyMMddHHmmss);
        req.setNewCardType((cardItem == null) ? mModel.getCardType() : cardItem.getCardType());
        req.setNewPayType(String.valueOf(iPayType));
        req.setYSJE(Double.parseDouble(getStopPayMoney())); // 停车收费
        req.setSFJE(Double.parseDouble(getNeedPayMoney())); // 收费金额
        req.setDiscountSetId(null == dzItem ? 0 : (int) dzItem.getID());

        UpdateChargeInfoResp updateChargeInfoResp = GetServiceData.getInstance().UpdateChargeInfo(req);
        if (updateChargeInfoResp == null)
        {
            return null;
        }
        return updateChargeInfoResp;
    }

    public String toNetNeedFormatString(String time)
    {
        if (time == null)
        {
            return null;
        }

        Date date = TimeConvertUtils.stringToDate(time);
        String yyyyMMddHHmmss = TimeConvertUtils.dateToString("yyyyMMddHHmmss", date);
        return yyyyMMddHHmmss;
    }

    private UpdateChargeAmountResp requestUpdateChargeAmount()
    {
        UpdateChargeAmountReq amountReq = new UpdateChargeAmountReq();
        amountReq.setToken(Model.token);
        amountReq.setCardNO(mModel.getCardNO());
        amountReq.setCardType(mModel.getCardType());

        //转换时间格式
        String yyyyMMddHHmmss = TimeConvertUtils.longToString("yyyyMMddHHmmss", System.currentTimeMillis());

        amountReq.setOutTime(mModel.getOutTime() == null ? yyyyMMddHHmmss : toNetNeedFormatString(mModel.getOutTime()));
        amountReq.setNewPayType(String.valueOf(iPayType));
        amountReq.setNewYSJE(Double.parseDouble(getStopPayMoney())); // 停车收费
        amountReq.setNewSFJE(Double.parseDouble(getNeedPayMoney())); // 收费金额

        return GetServiceData.getInstance().UpdateChargeAmount(amountReq);
    }

    @Override
    public void onClickVoicePay()// 语音报价
    {
        ReportCharge();
    }

    @Override
    public void onClickCancelCharge()
    {
        if (mModel == null)
        {
            L.i("onClickCancelCharge mModel == null");
            return;
        }

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                CancelChargeResp cancelChargeResp = requestCancelCharge();
                if (cancelChargeResp == null) return;

                int ret = cancelChargeResp.getData(); // 即只有当取消成功才会取消界面
                if (ret <= 0)
                {
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            MessageBox.show(mActivity, "取消收费失败！" + "\r\n" + "btnOK_Click");
                        }
                    });
                }
                else
                {
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            cancel();
                        }
                    });
                }
            }
        }).start();
    }

    @Nullable
    private CancelChargeResp requestCancelCharge()
    {
        CancelChargeReq cancelChargeReq = new CancelChargeReq();
        cancelChargeReq.setToken(Model.token);
        cancelChargeReq.setCardNO(mModel.getCardNO());

        String yyyyMMddHHmmss = TimeConvertUtils.dateToString("yyyyMMddHHmmss", TimeConvertUtils.stringToDate(mModel.getOutTime()));
        cancelChargeReq.setOutTime(yyyyMMddHHmmss);

        cancelChargeReq.setCardType(mModel.getCardType());
        CancelChargeResp cancelChargeResp = GetServiceData.getInstance().CancelCharge(cancelChargeReq);
        if (cancelChargeResp == null)
        {
            return null;
        }
        return cancelChargeResp;
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

    @Override
    public void onSelectCarTypeChange(int pos)
    {
        L.i("onSelectCarTypeChange ==========================");
//        CardTypeDef CardType;

        String CardType = getCmdCardType();
//        CardType = cmbCardType.SelectedItem as CardTypeDef;

        if (null == CardType || CardType.equals("无效卡") || !Model.bTemp8) // ?
        {
            if (null != callback)
            {
                callback.binData((null == CardType ? "" : CardType), Double.parseDouble(getNeedPayMoney()));
//                CardTypeSFJEHandler((null == CardType ? "" : CardType.CardType), txtSFJE.Value);
            }
            return;

        }

        CaclSFJE(true);

        if (null != callback)
        {
            callback.binData(CardType, Double.parseDouble(getNeedPayMoney()));
        }
    }

    private void CaclSFJE(boolean ReportChargeInSound)
    {
        String inTime = mModel.getInTime();
        String outTime = mModel.getOutTime();

        if (TextUtils.isEmpty(mModel.getInTime()))
        {
            inTime = mModel.getOutTime();
        }

        if (TextUtils.isEmpty(mModel.getOutTime()))
        {
            outTime = inTime;
        }

        long discountID = -1;
        if (discountJHSetList != null && discountJHSetList.size() > 1) // 地点
        {
            discountID = discountJHSetList.get(getDiscountListIndex()).getID();
        }

        final String getCardType = CR.GetCardType(getCmdCardType(), 0);
        final String cphInHaveRecord = getCPHInHaveRecord();

        final String tmpInTime = inTime;
        final String tmpOutTime = outTime;
        final long tmpDiscountID = discountID;

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                final CaclChargeAmountResp resp = requstCaclChargeAmount(tmpInTime, tmpOutTime, tmpDiscountID, getCardType, cphInHaveRecord);
                if (resp == null) return;

                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (resp.getData() == null)
                        {
                            setChargeMoney("0");
                            setNeedMoneyOnFinal("0");
                        }
                        else
                        {
                            setChargeMoney(String.valueOf(resp.getData().getSFJE()));
                            setNeedMoneyOnFinal(String.valueOf(resp.getData().getSFJE()));
                        }
                    }
                });
            }
        }).start();

        if (ReportChargeInSound)
        {
            ReportCharge();
        }
    }

    private CaclChargeAmountResp requstCaclChargeAmount(String inTime, String outTime, long discountID, String getCardType, String cphInHaveRecord)
    {
        CaclChargeAmountReq amountReq = new CaclChargeAmountReq();
        amountReq.setToken(Model.token);
        amountReq.setCardNO(mModel.getCardNO());
        amountReq.setStationId(Model.stationID);
        amountReq.setCardType(getCardType.equals("无效卡") ? null : getCardType);
        amountReq.setDiscountSetId(discountID == -1 ? null : discountID);
        amountReq.setInTime(toNetNeedFormatString(inTime));
        amountReq.setOutTime(toNetNeedFormatString(outTime));
        amountReq.setCPH(cphInHaveRecord == null ? null : cphInHaveRecord);
        CaclChargeAmountResp caclChargeAmountResp = GetServiceData.getInstance().CaclChargeAmount(amountReq); //没有填充车牌原因
        return caclChargeAmountResp;
    }
}
