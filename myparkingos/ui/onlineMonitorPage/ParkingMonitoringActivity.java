package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;

import com.example.administrator.mydistributedparkingos.R;
import com.example.administrator.myparkingos.constant.CR;
import com.example.administrator.myparkingos.constant.ColumnName;
import com.example.administrator.myparkingos.constant.ConstantSharedPrefs;
import com.example.administrator.myparkingos.constant.DeviceStringTool;
import com.example.administrator.myparkingos.constant.JsonSearchParam;
import com.example.administrator.myparkingos.constant.OpenWayEnum;
import com.example.administrator.myparkingos.constant.OrderField;
import com.example.administrator.myparkingos.constant.PlateColorEnum;
import com.example.administrator.myparkingos.constant.QueueMessageTypeEnum;
import com.example.administrator.myparkingos.constant.RCodeEnum;
import com.example.administrator.myparkingos.easythread.Callback;
import com.example.administrator.myparkingos.easythread.EasyThread;
import com.example.administrator.myparkingos.model.GetServiceData;
import com.example.administrator.myparkingos.model.MonitorRemoteRequest;
import com.example.administrator.myparkingos.model.RequestByURL;
import com.example.administrator.myparkingos.model.beans.BlackListOpt;
import com.example.administrator.myparkingos.model.beans.Model;
import com.example.administrator.myparkingos.model.beans.ModelNode;
import com.example.administrator.myparkingos.model.beans.gson.EntityBlackList;
import com.example.administrator.myparkingos.model.beans.gson.EntityCarTypeInfo;
import com.example.administrator.myparkingos.model.beans.gson.EntityCardIssue;
import com.example.administrator.myparkingos.model.beans.gson.EntityMoney;
import com.example.administrator.myparkingos.model.beans.gson.EntityParkJHSet;
import com.example.administrator.myparkingos.model.beans.gson.EntityPersonnelInfo;
import com.example.administrator.myparkingos.model.beans.gson.EntityUserInfo;
import com.example.administrator.myparkingos.model.requestInfo.CancelChargeReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCarInReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCarOutReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCardIssueReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCardTypeDefReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCheDaoSetReq;
import com.example.administrator.myparkingos.model.requestInfo.GetNetCameraSetReq;
import com.example.administrator.myparkingos.model.requestInfo.GetParkingInfoReq;
import com.example.administrator.myparkingos.model.requestInfo.GetXXXCommonReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarInConfirmReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarInReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarInWithoutCPHReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarOutReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarOutWithoutEntryRecordReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeAmountReq;
import com.example.administrator.myparkingos.model.requestInfo.UpdateChargeInfoReq;
import com.example.administrator.myparkingos.model.requestInfo.UploadCaptureImageReq;
import com.example.administrator.myparkingos.model.responseInfo.AddOptLogResp;
import com.example.administrator.myparkingos.model.responseInfo.CancelChargeResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCarInResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCarOutResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCardIssueResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCardTypeDefResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCheDaoSetResp;
import com.example.administrator.myparkingos.model.responseInfo.GetLedSettingResp;
import com.example.administrator.myparkingos.model.responseInfo.GetNetCameraSetResp;
import com.example.administrator.myparkingos.model.responseInfo.GetParkingInfoResp;
import com.example.administrator.myparkingos.model.responseInfo.GetRightsByGroupIDResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarInConfirmResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarInResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarInWithoutCPHResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutWithoutEntryRecordResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeAmountResp;
import com.example.administrator.myparkingos.model.responseInfo.UpdateChargeInfoResp;
import com.example.administrator.myparkingos.model.responseInfo.UploadCaptureImageResp2;
import com.example.administrator.myparkingos.myUserControlLibrary.MessageBox;
import com.example.administrator.myparkingos.ui.FragmentChargeManager;
import com.example.administrator.myparkingos.ui.FragmentDetailManager;
import com.example.administrator.myparkingos.ui.Summary;
import com.example.administrator.myparkingos.ui.onlineMonitorPage.report.ReportDealLineView;
import com.example.administrator.myparkingos.util.BitmapUtils;
import com.example.administrator.myparkingos.util.ConcurrentQueueHelper;
import com.example.administrator.myparkingos.util.ExeUtil;
import com.example.administrator.myparkingos.util.ImageUitls;
import com.example.administrator.myparkingos.util.L;
import com.example.administrator.myparkingos.util.SDCardUtils;
import com.example.administrator.myparkingos.util.T;
import com.example.administrator.myparkingos.util.TimeConvertUtils;
import com.example.administrator.myparkingos.volleyUtil.callback.GsonCallback;
import com.example.sfmudpsdk_android.ConstantClass;
import com.example.sfmudpsdk_android.UDPSendbll;
import com.example.sfmudpsdk_android.VoiceInYWModel;
import com.jude.http.RequestManager;
import com.jude.http.RequestMap;
import com.vz.tcpsdk;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017-02-16.
 * 【在线监控】主界面
 */
public class ParkingMonitoringActivity extends AppCompatActivity implements GsonCallback.Listener
{
    private FragmentChargeManager fragmentChargeManager = null;
    private FragmentDetailManager fragmentDetailManager = null;
    private ParkingMonitoringView parkingMonitoringView;
    private ParkingPlateNoInputView CarOutDialog;
    private ParkingInNoPlateView parkingInNoPlateView;
    private ParkingOutNOPlateNoView parkingOutNOPlateNoView;
    private ParkingPlateRegisterView parkingPlateRegisterView;
    private FormAddBlackListView formAddBlackListView;
    private ReportDealLineView reportDealLineView;
    private ParkingChangeView parkingChangeShifts;
    private QueueTask queueTask;
    private ExeUtil exe;

    // 请求数据
    private GetCheDaoSetResp getCheDaoSetResp;
    private GetCarOutResp getCarOutResp;
    private GetCarInResp getCarInResp;
    private GetParkingInfoResp getParkingInfoResp;
    private ParkingChannelSelectView parkingChannelSelectView;

    public static final String METHOD_GETCARDTYPEDEF = "GetCardTypeDef";
    public static final String METHOD_GETCHEDAOSET = "GetCheDaoSet";
    public static final String METHOD_GETNETCAMERASET = "GetNetCameraSet";
    public static final String METHOD_GETCARIN = "GetCarIn";
    public static final String METHOD_GETCAROUT = "GetCarOut";
    public static final String METHOD_GETPARKINGINFO = "GetParkingInfo";
    public static final String METHOD_UPLOADCAPTUREIMAGE = "UploadCaptureImage";
    public static final String METHOD_SETCARINWITHOUTCPH = "SetCarInWithoutCPH";
    public static final String METHOD_SETCARIN = "SetCarIn";
    public static final String METHOD_ADDOPTLOG = "AddOptLog";


    private UDPSendbll udpSendbll;
    private EasyThread executor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        parkingMonitoringView = new ParkingMonitoringView(this, R.layout.activity_parkingmonitor, mHandler)
        {
            @Override
            public void chargeInfoToFragmentChange() //点击收费信息，切换Fragment
            {
                fragmentChargeManager.showFragment(0);
                mHandler.sendEmptyMessage(MSG_ChargeInfo);
            }

            @Override
            public void carSpaceInfoToFragmentChange()// 点击车位信息，切换fragment
            {
                fragmentChargeManager.showFragment(1);
                mHandler.sendEmptyMessage(MSG_ParkingInfo);
            }

            @Override
            public void carInParkingDetailToFragmentChange()//点击场内车辆明细
            {
                fragmentDetailManager.showFragment(0);
                mHandler.sendEmptyMessage(MSG_GetCarIn);
            }

            @Override
            public void chargeDetailToFragmentChange()//点击车辆收费明细
            {
                fragmentDetailManager.showFragment(1);
                mHandler.sendEmptyMessage(MSG_GetCarOut);
            }

            /**
             * 点击车入场按钮
             */
            @Override
            public void onClickCarInBtn()
            {
                // 当面对多个通道时，出现通道的选择
                if (inChannelNum >= 2)
                {
                    if (parkingChannelSelectView == null)
                    {
                        parkingChannelSelectView = new ParkingChannelSelectView(ParkingMonitoringActivity.this, CAR_CHANNEL_IN)
                        {
                            @Override
                            public void onSelectInOutName(String currentText)
                            {
                                if (checkCheDaoSetRespDataInvalid()) return;

                                int channelIndex = getCtrlIndexByInoutName(currentText);
                                if (channelIndex < 0)
                                    L.i("getCtrlIndexByInoutName" + currentText + "no find");
                                else
                                    popuCarInDialog(channelIndex, getCheDaoSetResp.getData().get(CheDaoIndex[channelIndex]).getCtrlNumber());
                            }

                            @Override
                            public void prepareLoadData()
                            {
                                if (checkCheDaoSetRespDataInvalid()) return;
                                List<String> data = getInOutListNameByType(CAR_CHANNEL_IN);
                                parkingChannelSelectView.setSpinnerData(data);
                            }
                        };
                    }
                    parkingChannelSelectView.show();
                }
                else if (inChannelNum == 1)
                {
                    int channelIndex = getChannelIndex(CAR_CHANNEL_IN);
                    if (channelIndex < 0)
                        L.i("getChannelIndex " + CAR_CHANNEL_IN + " no find");
                    else
                        popuCarInDialog(channelIndex, getCheDaoSetResp.getData().get(CheDaoIndex[channelIndex]).getCtrlNumber());
                }
                else
                {
                    T.showShort(ParkingMonitoringActivity.this, "没有车辆入场通道");
                }
            }

            /**
             * 弹出车辆进场的画面
             */
            private void popuCarInDialog(final int index, final int ctrlNumber)
            {
                ParkingPlateNoInputView carInDialog = new ParkingPlateNoInputView(ParkingMonitoringActivity.this, CAR_CHANNEL_IN)
                {
                    /**
                     * 提前加载数据
                     */
                    @Override
                    public void prepareLoadData()
                    {
                        super.prepareLoadData();
                        setProvince(Model.LocalProvince);
                    }

                    @Override
                    protected void onCarInBtnOk(final String CPH)
                    {
                        /**
                         * 获取进场的数据返回，然后将数据放到界面上
                         */
                        SetCarInReq setCarInReq = initSetCarIn(CPH, ctrlNumber);
                        sendModeToQueue(QueueMessageTypeEnum.QUEUE_CAR_IN_TYPE_AUTO, CPH, setCarInReq, index);
                    }

                    /**
                     * 当text查询时，出现模糊查找
                     * @param Precision
                     */
                    @Override
                    public void onClickInTextInput(final String cph, final int Precision)
                    {
                        new Thread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                final GetCardIssueResp respList = requestGetCarIssueWhenCPH_Like(cph);
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        ArrayList<String> strings = new ArrayList<>();
                                        if (respList != null && respList.getData() != null && respList.getData().size() > 0)
                                        {
                                            for (GetCardIssueResp.DataBean o : respList.getData())
                                            {
                                                strings.add(o.getCPH());
                                            }
                                            showPopWindow();
                                            setCompleteCPHText(strings);
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                };
                carInDialog.show();
            }

            /**
             * 点击出场按钮
             */
            @Override
            public void onClickCarOutBtn()
            {
                if (outChannelNum >= 2)
                {
                    if (parkingChannelSelectView == null)
                    {
                        parkingChannelSelectView = new ParkingChannelSelectView(ParkingMonitoringActivity.this, CAR_CHANNEL_OUT)
                        {
                            @Override
                            public void onSelectInOutName(String currentText)
                            {
                                if (checkCheDaoSetRespDataInvalid()) return; // 获取具体选中 13

                                int channelIndex = getCtrlIndexByInoutName(currentText);
                                if (channelIndex < 0)
                                    L.i("getCtrlIndexByInoutName" + currentText + "no find");
                                else
                                    popuSetCarOut(channelIndex, getCheDaoSetResp.getData().get(CheDaoIndex[channelIndex]).getCtrlNumber());
                            }

                            @Override
                            public void prepareLoadData()
                            {
                                if (checkCheDaoSetRespDataInvalid()) return;
                                List<String> data = getInOutListNameByType(CAR_CHANNEL_OUT);
                                parkingChannelSelectView.setSpinnerData(data);
                            }
                        };
                    }
                    parkingChannelSelectView.show();
                }
                else if (outChannelNum == 1)
                {
                    int channelIndex = getChannelIndex(CAR_CHANNEL_OUT);
                    if (channelIndex < 0)
                        L.i("getChannelIndex " + CAR_CHANNEL_OUT + " no find");
                    else
                        popuSetCarOut(channelIndex, getCheDaoSetResp.getData().get(CheDaoIndex[channelIndex]).getCtrlNumber());
                }
                else
                {
                    T.showShort(ParkingMonitoringActivity.this, "没有车辆出场通道");
                }
            }

            /**
             * 弹出车辆出场画面
             * @param index
             * @param ctrlNumber
             */
            private void popuSetCarOut(final int index, final int ctrlNumber)
            {
                ParkingPlateNoInputView carOutDialog = new ParkingPlateNoInputView(ParkingMonitoringActivity.this, CAR_CHANNEL_OUT)
                {
                    @Override
                    public void prepareLoadData()
                    {
                        super.prepareLoadData();
                        setProvince(Model.LocalProvince);
                    }

                    @Override
                    public void onCarOutBtnOk(final String CPH) // 车辆出场，直接发送出场消息
                    {
                        SetCarOutReq req = initSetCarOutReq(CPH, ctrlNumber);

                        sendModeToQueue(QueueMessageTypeEnum.QUEUE_CAR_OUT_TYPE_AUTO, CPH, req, index);
                    }

                    @Override
                    public void onClickOutTextInput(final String resultCPH, int length)
                    {
                        new Thread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                final GetCarInResp getCarInResp = requestSelectComeCPH_Like(resultCPH);
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        ArrayList<String> strings = new ArrayList<>();
                                        if (getCarInResp != null && getCarInResp.getData() != null && getCarInResp.getData().size() > 0)
                                        {
                                            for (GetCarInResp.DataBean o : getCarInResp.getData())
                                            {
                                                strings.add(o.getCPH());
                                            }
                                            showPopWindow();
                                            setCompleteCPHText(strings);
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                };
                carOutDialog.show();
            }

            /**
             * 点击无牌车入场按钮
             */
            @Override
            public void onClickNoPlateCarInBtn()
            {
                if (inChannelNum >= 2)
                {
                    if (parkingChannelSelectView == null)
                    {
                        parkingChannelSelectView = new ParkingChannelSelectView(ParkingMonitoringActivity.this, CAR_CHANNEL_IN)
                        {
                            @Override
                            public void onSelectInOutName(String currentText)
                            {
                                if (checkCheDaoSetRespDataInvalid()) return;

                                int channelIndex = getCtrlIndexByInoutName(currentText);
                                if (channelIndex < 0)
                                    L.i("getCtrlIndexByInoutName" + currentText + "no find");
                                else
                                    popuNoPlateSetCarIn(channelIndex, getCheDaoSetResp.getData().get(CheDaoIndex[channelIndex]).getCtrlNumber());
                            }

                            @Override
                            public void prepareLoadData()
                            {
                                if (checkCheDaoSetRespDataInvalid()) return;
                                List<String> data = getInOutListNameByType(CAR_CHANNEL_IN);
                                parkingChannelSelectView.setSpinnerData(data);
                            }
                        };
                    }
                    parkingChannelSelectView.show();
                }
                else if (inChannelNum == 1)
                {
                    int channelIndex = getChannelIndex(CAR_CHANNEL_IN);
                    if (channelIndex < 0)
                        L.i("getChannelIndex " + CAR_CHANNEL_IN + " no find");
                    else
                        popuNoPlateSetCarIn(channelIndex, getCheDaoSetResp.getData().get(CheDaoIndex[channelIndex]).getCtrlNumber());
                }
                else
                {
                    T.showShort(ParkingMonitoringActivity.this, "没有车辆入场通道");
                }
            }

            /**
             * 弹出无牌车入场界面
             */
            private void popuNoPlateSetCarIn(final int index, final int ctrlNumber)
            {
                if (parkingInNoPlateView == null)
                {
                    parkingInNoPlateView = new ParkingInNoPlateView(ParkingMonitoringActivity.this, index, ctrlNumber)
                    {
                        @Override
                        public void prepareLoadData()
                        {
                            if (checkCheDaoSetRespDataInvalid()) return;

                            List<String> data = getInOutListNameByType(CAR_CHANNEL_IN);
                            parkingInNoPlateView.setRoadNameData(data);//显示下拉列表

                            String fileName = SDCardUtils.getSDCardPath() + "picture1.jpg";
                            L.i("获取到的fileName:" + fileName);
                            parkingMonitoringView.saveImage(fileName, index);
                            parkingInNoPlateView.setImage(fileName); //显示图像

                        }
                    };

                    parkingInNoPlateView.setListener(new ParkingInNoPlateView.NoPlateListener()
                    {
                        @Override
                        public void saveImageCallBack(String fileName, int index)
                        {
                            parkingMonitoringView.saveImage(fileName, index);
                        }
                    });
                }
                parkingInNoPlateView.show();
            }

            /**
             * 弹出无牌车出场界面
             */
            @Override
            public void onClickNoPlateCarOutBtn()
            {
                if (parkingOutNOPlateNoView == null)
                {
                    parkingOutNOPlateNoView = new ParkingOutNOPlateNoView(ParkingMonitoringActivity.this, mHandler, null, getChannelIndex(CAR_CHANNEL_OUT));
                }
                parkingOutNOPlateNoView.setListener(new ParkingOutNOPlateNoView.CPHRefreshListener()
                {
                    @Override
                    public void InNoCPHRefresh(int laneIndex, String localPath, String networkPath)
                    {
                        GetBinInOut();
                        //更新界面的数据
                    }
                });
                parkingOutNOPlateNoView.show();
            }

            /**
             * 弹出车辆注册界面
             */
            @Override
            public void onClickCarRegisterBtn()
            {
                if (parkingPlateRegisterView == null) // TODO  parkingPlateRegisterView
                {
                    parkingPlateRegisterView = new ParkingPlateRegisterViewSub(ParkingMonitoringActivity.this);
                }
                parkingPlateRegisterView.show();
            }

            /**
             * 点击刷新重新获取数据
             */
            @Override
            public void onClickRefreshDetail()
            {
                requestGetCarIn();
                requestGetCarOut();
            }

            /**
             * 点击黑名单按钮
             */
            @Override
            public void onClickBlackListBtn()
            {
                if (formAddBlackListView == null)
                {
                    formAddBlackListView = new FormAddBlackListView(ParkingMonitoringActivity.this)
                    {
                        @Override
                        public void prepareLoadData() //提前加载数据
                        {
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    List<EntityBlackList> entityBlackLists = MonitorRemoteRequest.GetBlacklist(Model.token);
                                    Message message = mHandler.obtainMessage();
                                    message.obj = entityBlackLists;
                                    message.what = MSG_UpdateBlackListData;
                                    mHandler.sendMessage(message);
                                }
                            }).start();
                        }

                        @Override
                        public void onClickBlackListQuit()
                        {
                            formAddBlackListView.dismiss();
                        }

                        @Override
                        public void onClickBlackListDelEach(final List<EntityBlackList> param)//一个个的进行删除
                        {
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    int returnValue = -1;
                                    for (int i = 0; i < param.size(); i++)
                                    {
                                        if (param.get(i).getDownloadSignal().equals("000000000000000"))
                                        {
                                            returnValue = MonitorRemoteRequest.DeleteBlacklistBy(Model.token, String.valueOf(param.get(i).getID()));
                                        }
                                        else
                                        {
                                            returnValue = MonitorRemoteRequest.UpdateBlacklist(Model.token, String.valueOf(param.get(i).getID()));
                                        }
                                        if (returnValue > 0)
                                        {
                                            MonitorRemoteRequest.AddOptLog(Model.token, "黑名单车辆", Model.sUserName + ":删除黑名单车辆：" + param.get(i).getCPH()
                                                    , Model.sUserCard, Model.sUserName, String.valueOf(Model.stationID));// 添加成功
                                        }
                                    }

                                    if (returnValue > 0)
                                    {
                                        List<EntityBlackList> entityBlackLists = MonitorRemoteRequest.GetBlacklist(Model.token);
                                        Message message = mHandler.obtainMessage();
                                        message.obj = entityBlackLists;
                                        message.what = MSG_UpdateBlackListData;
                                        mHandler.sendMessage(message);
                                    }
                                }
                            }).start();
                        }

                        @Override
                        public void onClickBlackListDelBtn(final EntityBlackList param)//删除
                        {
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    int returnValue = -1;
                                    L.i("param.getDownloadSignal().equals:" + param.getDownloadSignal());
                                    if (param.getDownloadSignal().equals("000000000000000"))
                                    {
                                        returnValue = MonitorRemoteRequest.DeleteBlacklistBy(Model.token, String.valueOf(param.getID()));
                                    }
                                    else
                                    {
                                        returnValue = MonitorRemoteRequest.UpdateBlacklist(Model.token, String.valueOf(param.getID()));
                                    }

                                    if (returnValue > 0)
                                    {
                                        List<EntityBlackList> entityBlackLists = MonitorRemoteRequest.GetBlacklist(Model.token);
                                        MonitorRemoteRequest.AddOptLog(Model.token, "黑名单车辆", Model.sUserName + ":删除黑名单车辆：" + param.getCPH()
                                                , Model.sUserCard, Model.sUserName, String.valueOf(Model.stationID));// 添加成功

                                        Message message = mHandler.obtainMessage();
                                        message.obj = entityBlackLists;
                                        message.what = MSG_UpdateBlackListData;
                                        mHandler.sendMessage(message);
                                    }
                                    else
                                    {// 删除
                                        List<EntityBlackList> entityBlackLists = MonitorRemoteRequest.GetBlacklist(Model.token);
                                        Message message = mHandler.obtainMessage();
                                        message.obj = entityBlackLists;
                                        message.what = MSG_UpdateBlackListData;
                                        mHandler.sendMessage(message);
                                    }
                                }
                            }).start();
                        }

                        @Override
                        public void onClickBlackListQueryBtn()//查询
                        {
                            final String cphDataFromUI = formAddBlackListView.getCPHDataFromUI();
                            if (cphDataFromUI == null)
                            {
                                return;
                            }

                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    List<EntityBlackList> entityBlackLists = MonitorRemoteRequest.GetBlacklistWhenSelect(Model.token, cphDataFromUI);
                                    if (entityBlackLists != null)
                                    {
                                        Message message = mHandler.obtainMessage();
                                        message.obj = entityBlackLists;
                                        message.what = MSG_UpdateBlackListData;
                                        mHandler.sendMessage(message);
                                    }
                                }
                            }).start();
                        }

                        @Override
                        public void onClickBlackListAddBtn()//添加
                        {
                            final BlackListOpt listOpt = formAddBlackListView.getAllDataFromUI();
                            if (listOpt == null)
                            {
                                return;
                            }
                            L.i("onClickBlackListAddBtn:" + formAddBlackListView.getAllDataFromUI());

                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MonitorRemoteRequest.DeleteBlacklist(Model.token, listOpt.getCPH());
                                    MonitorRemoteRequest.AddBlacklist(Model.token, listOpt);  //先删除后添加数据

                                    /*
                                    更新界面数据
                                    * */
                                    List<EntityBlackList> entityBlackLists = MonitorRemoteRequest.GetBlacklist(Model.token);
                                    if (entityBlackLists != null)
                                    {
                                        MonitorRemoteRequest.AddOptLog(Model.token, "黑名单车辆", Model.sUserName + ":添加黑名单车辆：" + listOpt.getCPH()
                                                , Model.sUserCard, Model.sUserName, String.valueOf(Model.stationID));// 添加成功
                                        Message message = mHandler.obtainMessage();
                                        message.obj = entityBlackLists;
                                        message.what = MSG_UpdateBlackListData;
                                        mHandler.sendMessage(message);

                                        // 表示添加成功
                                    }

                                }
                            }).start();
                        }
                    };
                }
                formAddBlackListView.show();
            }

            /**
             * 点击弹出期限查询按钮
             */
            @Override
            public void onClickDealLineQueryBtn()
            {
                if (reportDealLineView == null)
                {
                    reportDealLineView = new ReportDealLineView(ParkingMonitoringActivity.this);
                }
                reportDealLineView.show();
            }

            /**
             * 点击弹出换班登录按钮
             */
            @Override
            public void onClickShiftLoginBtn()
            {
                if (parkingChangeShifts == null)
                {
                    parkingChangeShifts = new ParkingChangeView(ParkingMonitoringActivity.this);
                }
                parkingChangeShifts.show();
            }

            public void testUploadPicture() // 可以直接上传到服务器上
            {
                UploadCaptureImageReq req = new UploadCaptureImageReq(); // 为图片下载提供支持
                req.setToken(Model.token);
                req.setStationId(String.valueOf(Model.stationID));
                req.setDate(TimeConvertUtils.longToString("yyyyMMdd", System.currentTimeMillis()));
                String resultUrl = GetServiceData.getResultUrl(METHOD_UPLOADCAPTUREIMAGE, req);

                // 直接上传文件
                Bitmap bitmap = BitmapFactory.decodeFile(SDCardUtils.getSDCardPath() + "picture.jpg");
                InputStream inputStream = ImageUitls.bitmapToInputStream(bitmap, false);

                RequestMap params = new RequestMap();
//                params.put("file", new File(SDCardUtils.getSDCardPath() + "picture.jpg"));
                params.put("bitmap", inputStream, "mypicture.jpg");
                RequestManager
                        .getInstance()
                        .post(resultUrl, params, new GsonCallback<>(UploadCaptureImageResp2.class, ParkingMonitoringActivity.this, resultUrl));
            }

            /**
             * 点击弹出收费车辆按钮
             */
            @Override
            public void onClickChargeRecordBtn()
            {
//                testUploadPicture();
//                udpSendbll.OpenGate("192.168.2.250", 9);
//                udpSendbll.VoiceLoad("192.168.2.250", 9, ConstantClass.VoiceEnum.WelCome);
            }

            /**
             * 点击弹出场内车辆按钮
             */
            @Override
            public void onClickGroundVehicleBtn()
            {

            }
        };

        udpSendbll = new UDPSendbll();

        tcpsdk.getInstance().setup();
        Model.sImageSavePath = SDCardUtils.getDiskCacheDirPath(this, ""); // "D:\\CaptureImage\\1\\20170410\\612f52e2-eb20-4224-9aeb-3bfed799e35d20170410191108cgo.jpg

        filesJpg = SDCardUtils.getDiskCacheDirPath(this, "CaptureImage"); // filesJpg:/mnt/internal_sd/Android/data/com.example.administrator.mydistributedparkingos/cacheCaptureImage
        L.e("filesJpg:" + filesJpg);
        initView(savedInstanceState);

        initFields();
        initControl();

        startAliveTime = System.currentTimeMillis();
        mHandler.sendEmptyMessage(MSG_KeppAlive);

        exe = new ExeUtil();
        queueTask = new QueueTask(true);
        queueTask.start();

        initEasyThread();
    }

    private void executeByThreadPools(String threadName, ThreadCallback threadCallback, Runnable runnable)
    {
        if (executor == null) return;
        executor.name(threadName)
                .callback(threadCallback)
                .execute(runnable);
    }

    private void initEasyThread()
    {
        executor = EasyThread.Builder
                .fixed(Runtime.getRuntime().availableProcessors() * 2 + 1) // cpu的效率得到最大程度执行
                .priority(Thread.MAX_PRIORITY)
                .name("thread name")
                .build();
    }

    private int getCtrlIndexByInoutName(String currentText)
    {
        L.i("currentText:" + currentText);
        int resultValue = -1;
        for (int i = 0; i < CheDaoIndex.length; i++)
        {

            int tempI = CheDaoIndex[i]; // CheDaoIndex 重新整理的通道的下标
            if (getCheDaoSetResp.getData().get(tempI).getInOutName().equals(currentText))
            {
                L.i("tempI:" + tempI + ",i:" + i);
                resultValue = i;
                break;
            }
        }
        return resultValue;
    }

    @NonNull
    private List<String> getInOutListNameByType(int type)
    {
        if (checkCheDaoSetRespDataInvalid()) return null;
        List<GetCheDaoSetResp.DataBean> data = getCheDaoSetResp.getData();

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < CheDaoIndex.length; i++)
        {
            int tempI = CheDaoIndex[i];
            if (data.get(tempI).getInOut() == type)
            {
                strings.add(data.get(tempI).getInOutName());
            }
        }
        return strings;
    }

    private SetCarOutReq initSetCarOutReq(String cph, int ctrlNumber)
    {
        SetCarOutReq req = new SetCarOutReq(); // 发送进场数据
        req.setCPH(cph);
        req.setToken(Model.token);
        req.setCtrlNumber(ctrlNumber);
        req.setStationId(Model.stationID);
        return req;
    }

    private void sendModeToQueue(QueueMessageTypeEnum type, Object data, int index)
    {
        ModelNode modelNode = new ModelNode();
        modelNode.data = data;
        modelNode.type = type;
        modelNode.setiDzIndex(index);
        ConcurrentQueueHelper.getInstance().put(modelNode);
    }


    private void sendModeToQueue(QueueMessageTypeEnum type, String cph, Object data, int index)
    {
        ModelNode modelNode = new ModelNode();
        modelNode.data = data;

        modelNode.type = type;
        modelNode.setsDzScan("");
        modelNode.setiDzIndex(index);
        String fileName = SDCardUtils.getSDCardPath() + "picture.jpg";
        L.i("获取到的fileName:" + fileName);
        parkingMonitoringView.saveImage(fileName, index);
        modelNode.setStrFileJpg(fileName);
        modelNode.setStrCPH(cph);
        ConcurrentQueueHelper.getInstance().put(modelNode);
    }

    private int getChannelIndex(int type)
    {
        if (checkCheDaoSetRespDataInvalid()) return -1;
        List<GetCheDaoSetResp.DataBean> data = getCheDaoSetResp.getData();
        int resultValue = -1;
        for (int i = 0; i < CheDaoIndex.length; i++)
        {
            int tempI = CheDaoIndex[i]; // CheDaoIndex 重新整理的通道的下标
            if (data.get(tempI).getInOut() == type)
            {
                resultValue = i;
                break;
            }
        }
        return resultValue;
    }

    private void initView(@Nullable Bundle savedInstanceState)
    {
        // 初始化按钮颜色
        parkingMonitoringView.onClickInCarChargeInfo();
        parkingMonitoringView.onClickInCarInParkingDetail();

        // 初始化fragment
        initFragment(null); // 这个地方还是影响！！！
    }


    /**
     * 根据无牌车返回的数据，提示发送语音
     */
    private void dealCarInWithOutCPH(SetCarInWithoutCPHResp setCarInWithoutCPHResp)
    {
        /**
         * 发送开闸指令
         */

        /**
         * 发送语音
         */

        /**
         * 结束
         */
    }

    /**
     * 预先检测字符串
     *
     * @return
     */
    private String prepareDetectString(String srcStr, String defaultStr)
    {
        if (TextUtils.isEmpty(srcStr))
        {
            return defaultStr;
        }
        return srcStr;
    }

    /**
     * 根据车辆进场，提示发送语音数据和开闸
     * 注意返回数据的车牌和原始的车牌号可能不同
     *
     * @param setCarInResp
     */
    private boolean dealSetCarInResponse(final SetCarInResp setCarInResp, final String srcCPH, final int index, final PlateColorEnum colorType)
    {
        RCodeEnum rCodeEnum = RCodeEnum.valueOf(Integer.parseInt(setCarInResp.getRcode()));
        final SetCarInResp.DataBean carIn = setCarInResp.getData();

        boolean isMonthBeOverdue = false;
        boolean isMonthFull = false;

        switch (rCodeEnum)
        {
            case BlackList:// 车牌号 + 禁止入场请与管理处联系
            {
                String detectString = prepareDetectString(carIn.getCPH(), srcCPH);
                updateCarHintToFragment(MSG_CarHintInfoAfterResume
                        , detectString + ":" + prepareDetectString(carIn.getBlackReason(), ""));
                String chineseCPH = DeviceStringTool.GetChineseCPH(detectString) + "D2AF";
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_BLACKLIST, chineseCPH, index);
                return false;
            }
            case NoThisLanePermission:// 无授权请与管理处联系
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "在 " + carIn.getCtrlNumber() + " 号机上无权限！");
                parkingMonitoringView.setOperatorHintInfo(carIn.getCtrlNumber() + " 号机上无权限!");
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_NOTHISLANEPERMISSION, ConstantClass.VoiceEnum.Invalid, index);
                return false;
            }
            case BeOverdue://已过期请与管理处联系
            {
                parkingMonitoringView.setCPHText(index, prepareDetectString(carIn.getCPH(), srcCPH));
                parkingMonitoringView.setOperatorHintInfo("已过期，请到管理处延期!");
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "已过期，请到管理处延期!");
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_BEOVERDUE, ConstantClass.VoiceEnum.Overstayed, index);
                return false;
            }
            case PersonalFull:
            {
                parkingMonitoringView.setOperatorHintInfo("此车禁止入场，入场车辆数已经超过车位个数");
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "此车禁止入场，入场车辆数已经超过车位个数。");
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_PERSONALFULL, "9ED2", index);
                return false;
            }
            case ProhibitCurrent://已经入场
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "此车已入场[" + carIn.getInOutName() + "]");
                parkingMonitoringView.setOperatorHintInfo("此车已入场[" + carIn.getInOutName() + "]");
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_PROHIBITCURRENT, ConstantClass.VoiceEnum.Alreadyadmission, index);
                return false;
            }
            case ProhibitCutOff:
            {
                String strsLoad;
                if (Model.Channels[index].iInOut == 0)
                {
                    if (Model.bAutoTemp)
                    {
                        strsLoad = "ADB6";
                    }
                    else
                    {
                        strsLoad = "ADD2";
                    }
                }
                else
                {
                    strsLoad = "ADD4";
                }

                sendModeToQueue(QueueMessageTypeEnum.QUEUE_PROHIBITCUTOFF, strsLoad, index);
                return false;
            }
            case SummaryCarFull:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "车位已满!");
                parkingMonitoringView.setOperatorHintInfo("车位已满!");
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CARFULL, 5, index);
                return false;
            }
            case TemporaryCarFull:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "临时车位已满!");
                parkingMonitoringView.setOperatorHintInfo("临时车位已满!");
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CARFULL, 5, index);
                return false;
            }
            case MonthCarFull:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "月租车位已满!");
                parkingMonitoringView.setOperatorHintInfo("月租车位已满!");
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CARFULL, 5, index);
                return false;
            }
            case PrepaidCarFull:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "储值车位已满!");
                parkingMonitoringView.setOperatorHintInfo("储值车位已满!");
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CARFULL, 5, index);

                return false;
            }
            case BalanceNotEnough:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "余额不足，请先充值!");
                parkingMonitoringView.setOperatorHintInfo("余额不足，请先充值!");
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_BALANCENOTENOUGH, null, index);
                return false;
            }
            case AllCharacterSamePlateNoHandle:
            {
                parkingMonitoringView.setOperatorHintInfo("字符相同的车牌不处理!");
                return false;
            }
            case AllLetterPlateNoHandle:
            {
                parkingMonitoringView.setOperatorHintInfo("全字母车牌不处理!");
                return false;
            }
            case TemporaryCarNotInSmall:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "临时车禁止驶入小车场!");
                parkingMonitoringView.setOperatorHintInfo("临时车禁止驶入小车场!");

                sendModeToQueue(QueueMessageTypeEnum.QUEUE_TEMPORARYCARNOTINSMALL, "ADD2", index);

                String resultUrl = GetServiceData.getResultUrl(METHOD_ADDOPTLOG, CR.initAddOptLog("在线监控:FillOutData", "临时车禁止驶入小车场" + carIn.getCardNO()));
                RequestManager
                        .getInstance()
                        .get(resultUrl, new GsonCallback<>(AddOptLogResp.class, this, resultUrl));
                return false;
            }
            case ConfirmCutOff: // 确认开闸模式
            {
                // 显示Image图像  // 这里的弹出框，有多少就弹出多少个
                popuTempCPHView(setCarInResp, srcCPH, "确定开闸", index);
                // 发送语音
                String substring = carIn.getCardType().substring(0, 3);
                String loadField = "";
                if (substring.equals("Mth"))
                {
                    loadField = "ABD3";
                }
                else if (substring.equals("Tmp") || substring.equals("Mtp"))
                {
                    loadField = "ADD3";
                }

                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CONFIRMCUTOFF, loadField, index);
                return false;
            }
            case MonthCarFullConfirmCutOff:
            {
                // 显示Image图像
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "月租车位已满!");
                parkingMonitoringView.setOperatorHintInfo("月租车位已满!");
                popuTempCPHView(setCarInResp, srcCPH, "车场满位，确定开闸", index);

                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CARFULLCONFIRMCUTOFF, 5, index);
                return false;
            }
            case TemporaryCarFulllConfirmCutOff:
            {
                // 显示Image图像
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "临时车位已满!");
                parkingMonitoringView.setOperatorHintInfo("临时车位已满!");

                popuTempCPHView(setCarInResp, srcCPH, "车场满位，确定开闸", index);
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CARFULLCONFIRMCUTOFF, 5, index);
                return false;
            }
            case PrepaidCarFullConfirmCutOff:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "储值车位已满!");
                parkingMonitoringView.setOperatorHintInfo("储值车位已满!");

                popuTempCPHView(setCarInResp, srcCPH, "车场满位，确定开闸", index);
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CARFULLCONFIRMCUTOFF, 5, index);
                return false;
            }
            case SummaryCarFullConfirmCutOff:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "车位已满!");
                parkingMonitoringView.setOperatorHintInfo("车位已满!");

                popuTempCPHView(setCarInResp, srcCPH, "车场满位，确定开闸", index);
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CARFULLCONFIRMCUTOFF, 5, index);
                return false;
            }
            case MthBeOverdueToTmpCharge:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "已过期,临时车,请通行");
                parkingMonitoringView.setOperatorHintInfo("已过期,临时车,请通行");

                sendModeToQueue(QueueMessageTypeEnum.queue_MthBeOverdueToTmpCharge, "6CADAC", index);
                isMonthBeOverdue = true;
                break;
            }
            case MthFullToTmpCharge:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "车位占用，临时车，请通行");
                parkingMonitoringView.setOperatorHintInfo("车位占用，临时车，请通行");
                // 发送语音
                isMonthFull = true;
                String strsLoad = DeviceStringTool.GetChineseCPH(carIn.getCPH()) + "9EADAC";
                sendModeToQueue(QueueMessageTypeEnum.queue_MthBeOverdueToTmpCharge, strsLoad, index);
                break;
            }
            case RepeatAdmission:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "重复入场");
                break;
            }
            case OK:
            {
                break;
            }
            default:
                L.i("SetCarInResp 返回信息:" + setCarInResp.getMsg());
                return false;
        }

        final String cardType = carIn.getCardType();
        OpenWayEnum openWayEnum = OpenWayEnum.valueOf(Integer.parseInt(carIn.getOpenMode()));
        if (cardType.length() > 3)
        {
            String strCardCW = carIn.getCarPlace() == null ? "" : carIn.getCarPlace();
            if ((
                    cardType.substring(0, 3).equals("Mth")
                            || cardType.substring(0, 3).equals("Str")
                            || cardType.substring(0, 3).equals("Fre")
            ) && openWayEnum == OpenWayEnum.AutoCutOff)
            {
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_OPENGATE, null, index);

                if (isMonthBeOverdue || isMonthFull)
                {

                }
                else
                {
                    if (strCardCW.equals(""))
                    {
                        strCardCW = "FFFF";
                    }
                    if (strCardCW.length() != 4)
                    {
                        String CarCW = strCardCW;
                        for (int i = 0; i < 4 - strCardCW.length(); i++)
                        {
                            CarCW = "0" + CarCW;
                        }
                        strCardCW = CarCW.substring(CarCW.length() - 4, CarCW.length());
                    }

                    sendModeToQueue(QueueMessageTypeEnum.QUEUE_VOICEINYW, initVoiceInYW(carIn, cardType, strCardCW, (int) carIn.getBalance()), index);
                }
            }
            else if (cardType.substring(0, 3).equals("Tmp")
                    || cardType.substring(0, 3).equals("Mtp") // 月临车
                    /*|| bReadAuto == true*/) // bReadAuto 表示读卡还是识别???

            {
                switch (openWayEnum)
                {
                    case AutoCutOff:
                        strCardCW = "FFFF";
                        sendModeToQueue(QueueMessageTypeEnum.QUEUE_OPENGATE, null, index);
                        sendModeToQueue(QueueMessageTypeEnum.QUEUE_VOICEINYW, initVoiceInYW(carIn, cardType, strCardCW, 0), index);
                        break;
                    case ConfirmCutOff:
                        sendModeToQueue(QueueMessageTypeEnum.QUEUE_CONFIRMCUTOFF, "ADD3", index);
                        popuTempCPHView(setCarInResp, srcCPH, "临时车确定开闸", index);
                        break;
                    default:
                        break;
                }
            }
            else if (cardType.substring(0, 3).equals("Mth")
                    && openWayEnum == OpenWayEnum.ConfirmCutOff)
            {
                //显示图片
                popuTempCPHView(setCarInResp, srcCPH, "月租车确定开闸", index);
                sendModeToQueue(QueueMessageTypeEnum.QUEUE_CONFIRMCUTOFF, "ABD3", index);
            }
            if (openWayEnum == OpenWayEnum.NoCutOff && cardType.substring(0, 3).equals("Tmp"))
            {
            }
            else
            {
                SurplusCPH(index, carIn.getCPH(), carIn.getCardType(), carIn.getRemainingPlaceCount(), carIn.getBalance(), 0);
            }
//            ShowImage(index, carIn.getImagePath());
            if (Model.Channels[index].iInOut == 0)
            {
                ImageProcessing(filesJpg, carIn.getImagePath(), index, true, false);//上传图片到服务器
            }
            else
            {
                ImageProcessing(filesJpg, carIn.getImagePath(), index, true, false);
            }
            // 更新收费数据
            updateSetCarIn(carIn);
        }
        return true;
    }

    private void SurplusCPH(int laneIndex, String plateNumber, String cardType, int surplusCarCount, float balance, float charge)
    {
        //请求SurplusCar的数据
        GetXXXCommonReq req = new GetXXXCommonReq();
        JsonSearchParam.get

        List<LedSetting> lstLS = gsd.GetSurplusCar(Model.Channels[laneIndex].iCtrlID);


        foreach (var ls in lstLS)
        {
            string showWay = ls.ShowWay;
            string SendSum, StrSum = "";
            bool bMW = false;
            if (showWay.Contains("3"))
            {
                if (showWay.Contains("4"))
                {
                    if (plateNumber.Length == 7 && plateNumber != "0000000" && plateNumber != "6666666" && plateNumber != "京000000" && plateNumber != "8888888" && plateNumber != "")
                    {
                        StrSum = plateNumber;
                    }
                }
                //不显示车牌
                else
                {
                    int iCoutRemainCar = 0;
                    if (Model.iFreeCardNoInPlace == 1 && cardType.Substring(0, 3) == "Fre")
                    {
                        iCoutRemainCar = surplusCarCount;
                    }
                    else
                    {
                        if (Model.Channels[laneIndex].iInOut == 0)
                        {
                            iCoutRemainCar = surplusCarCount - 1;
                        }
                        else
                        {
                            iCoutRemainCar = surplusCarCount + 1;
                        }
                    }

                    if (iCoutRemainCar < 1)
                    {
                        if (ls.CPHEndStr == "")
                        {
                            if (ls.Pattern == "2")
                            {
                                StrSum = iCoutRemainCar.ToString("0000");
                            }
                            else if (ls.Pattern == "8")
                            {
                                StrSum = "剩余车位:" + iCoutRemainCar.ToString("000");
                            }
                            else
                            {
                                StrSum = "空车位" + iCoutRemainCar.ToString("000");
                            }
                        }
                        else
                        {
                            StrSum = ls.CPHEndStr + iCoutRemainCar.ToString("000");
                        }
                    }
                }
            }
            else
            {
                if (showWay.Contains("4"))
                {
                    if (plateNumber.Length == 7 && plateNumber != "0000000" && plateNumber != "6666666" && plateNumber != "京000000" && plateNumber != "8888888" && plateNumber != "")
                    {
                        StrSum = plateNumber;
                    }
                }
            }

            if (showWay.Contains("6"))
            {
                if ((cardType.Substring(0, 3) == "Tmp" || cardType.Substring(0, 3) == "Str") && Model.Channels[laneIndex].iInOut == 1)
                {
                    string money = "此次收费" + charge + "元";
                    StrSum += StrSum != "" ? " " + money : money;
                    if (cardType.Substring(0, 3) == "Str")
                    {
                        StrSum += "余额" + balance + "元";
                    }
                }
            }
            if (showWay.Contains("5") && (!showWay.Contains("6")
                    || (cardType.Substring(0, 3) != "Tmp"
                    && cardType.Substring(0, 3) != "Str")))
            {
                StrSum += StrSum != "" ? " " + ls.CPHEndStr : ls.CPHEndStr;
            }
            if (StrSum != "")
            {
                string Jstrs = "";
                if (bMW)
                {
                    Jstrs = "01" + ls.Speed + "00" + ls.Color + ls.SumTime + CR.GetStrTo16(StrSum);
                }
                else
                {
                    Jstrs = ls.Move + ls.Speed + ls.StopTime + ls.Color + ls.SumTime + CR.GetStrTo16(StrSum);
                }

                int sum = 0;
                byte[] array = CR.GetByteArray(Jstrs);
                foreach (byte by in array)
                {
                    sum += by;
                }
                sum = sum % 256;

                SendSum = "CC" + Convert.ToInt32(ls.SurplusID).ToString("X2") + "BB5154" + sum.ToString("X2") + Jstrs + "FF";
                if (Model.Channels[laneIndex].iXieYi == 1)
                {
                    SedBll sedBll = new SedBll(Model.Channels[laneIndex].sIP, 1007, 1005);
                    sedBll.SurplusCtrlLedShow(Convert.ToByte(Model.Channels[laneIndex].iCtrlID), SendSum, 1);
                }
            }
        }

        lostFlag = 0;
    }

    @NonNull
    private VoiceInYWModel initVoiceInYW(SetCarInResp.DataBean data, String cardType, String strCardCW, int balance)
    {
        VoiceInYWModel inGateModel = new VoiceInYWModel();
        inGateModel.CarTypeenum = getCardTypeEnum(cardType.substring(0, 3));
        inGateModel.strCPH = data.getCPH();
        inGateModel.CYkDay = data.getRemainingDays() < 0 ? 0 : data.getRemainingDays();
        inGateModel.strCardCW = strCardCW;
        inGateModel.iSurplus = summary0.getSurplusCarCount();
        inGateModel.CCzkMoney = balance;
        return inGateModel;
    }

    private ConstantClass.CarType getCardTypeEnum(String cardTypeStr)
    {
        if (cardTypeStr.equals("Mth"))
        {
            return ConstantClass.CarType.Month;
        }
        else if (cardTypeStr.equals("Tmp"))
        {
            return ConstantClass.CarType.Temp;
        }
        else if (cardTypeStr.equals("Fre"))
        {
            return ConstantClass.CarType.Free;
        }
        else if (cardTypeStr.equals("Str"))
        {
            return ConstantClass.CarType.Store;
        }
        else if (cardTypeStr.equals("Mtp"))
        {
            return ConstantClass.CarType.MonthTemp;
        }
        else
        {
            L.e("getCardTypeEnum(String cardTypeStr) 出现错误:" + cardTypeStr);
            return ConstantClass.CarType.Temp;
        }
    }

    private void popuTempCPHView(final SetCarInResp setCarInResp, final String srcCPH, String title, final int index)
    {
        Map<String, Object> map = new ArrayMap<String, Object>();
        map.put("laneIndex", index);
        map.put("plateNumber", setCarInResp.getData().getCPH());
        map.put("newPlateNumber", setCarInResp.getData().getCPH());
        map.put("title", title);
        map.put("cPColoar", setCarInResp.getData().getCPH());
//        map.put("plateNumber", setCarInResp.getData().getCPH());

        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                ParkingTempCPHView parkingTempCPHView = new ParkingTempCPHView(ParkingMonitoringActivity.this)
                {
                    @Override
                    public void onClickOk(final SetCarInConfirmReq setCarInConfirmReq, final String inOutName)
                    {
                        new Thread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                final SetCarInConfirmResp setCarInConfirmResp = requestSetCarInConfirm(setCarInConfirmReq, srcCPH, inOutName);
                                if (setCarInConfirmResp == null) return;

                                // 更新界面提示信息
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        updateCarHintToFragment(MSG_CarHintInfoAfterResume, setCarInConfirmResp.getMsg());
                                    }
                                });

                                if (setCarInConfirmResp.getData() == null)
                                    return;
                                // 更新收费信息
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        updateCarChargeToFragment(MSG_SETCarInComfirmed, setCarInConfirmResp.getData());
                                    }
                                });


                                String fileName = SDCardUtils.getSDCardPath() + "picture.jpg";
                                parkingMonitoringView.saveImage(fileName, index);
                                final Bitmap bitmap = BitmapUtils.fileToBitmap(fileName);
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        dealCPHInfoFromCamera(index, setCarInConfirmResp.getData().getCPH(), bitmap, CAR_CHANNEL_IN);
                                    }
                                });
                            }
                        }).start();
                    }

                    @Override
                    public void onClickCancel()
                    {
                        cancel();
                    }

                    @Override
                    public void prepareLoadData()
                    {
                        L.i("prepareLoadData srcCPH:" + srcCPH);
                        setCPH(srcCPH);
                        String inOutName = setCarInResp.getData().getInOutName();
                        List<String> inOutListNameByType = getInOutListNameByType(CAR_CHANNEL_IN);

                        int selectIndex = 0;
                        for (int i = 0; i < inOutListNameByType.size(); i++)
                        {
                            if (inOutListNameByType.get(i).equals(inOutName))
                            {
                                selectIndex = i;
                                break;
                            }
                        }
                        setSpinnerRoadName(inOutListNameByType, selectIndex);
                    }
                };
                parkingTempCPHView.show();
            }
        });
    }

    private void updateCarChargeToFragment(int what, Object obj)
    {
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = obj;
        mHandler.sendMessage(message);
    }

    private void updateCarHintToFragment(int what, String msg)
    {
        int currentIndex = fragmentChargeManager.getCurrentIndex();
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.arg1 = currentIndex;
        message.obj = msg;
        mHandler.sendMessage(message);
    }

    /**
     * 通过网络上获取相应的权限来获取当前按钮的使能情况;
     */
    private void dealRights()
    {
        boolean[] booleen = new boolean[]{false, true, false, false, true};//五个按钮
        for (GetRightsByGroupIDResp.DataBean o : Model.lstRights)
        {
            if (o.getFormName().equals(getResources().getString(R.string.parkMontior_licenseVehicleRegister)))
            {
                String[] btnTagText = parkingPlateRegisterView.getBtnTagText();
                for (int i = 0; i < btnTagText.length; i++)
                {
                    if (o.getItemName().equals(btnTagText[i]))
                    {
                        booleen[i] = o.isCanOperate();
                        L.i("dealRights:" + "itemName:" + o.getItemName());

                        if (o.getItemName().equals("btnAdd"))
                        {
                            parkingPlateRegisterView.setIsAdd(o.isCanOperate());
                        }

                        if (o.getItemName().equals("btnDelete"))
                        {
                            parkingPlateRegisterView.setIsDelete(o.isCanOperate());
                        }
                        break;
                    }
                }
            }
        }

        parkingPlateRegisterView.setBtnEnable(booleen);
    }

    private String[] dealCarType()
    {
        final List<EntityCarTypeInfo> entityCarTypeInfos = MonitorRemoteRequest.GetGetFXCardTypeToTrue(Model.token);
        String[] data = new String[entityCarTypeInfos.size()];
        for (int i = 0; i < entityCarTypeInfos.size(); i++)
        {
            data[i] = entityCarTypeInfos.get(i).getCardType();
        }

        return data;
    }

    private List<String> dealUserNo()
    {
        final List<EntityPersonnelInfo> entityUserInfoList = MonitorRemoteRequest.GetPersonnel(Model.token);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < entityUserInfoList.size(); i++)
        {
            list.add(entityUserInfoList.get(i).getUserNO());
        }
        return list;
    }

    private String checkStringIsNull(String valueStr)
    {
        if (valueStr == null)
            return "";
        else
            return valueStr;
    }

    private ArrayList<HashMap<String, String>> dealDataGridView()
    {
        final List<EntityCardIssue> EntityCardIssues = MonitorRemoteRequest.GetCarChePIss(Model.token, null);
        if (EntityCardIssues != null && EntityCardIssues.size() >= 0)
        {
            final ArrayList<HashMap<String, String>> items = new ArrayList<HashMap<String, String>>();
            for (EntityCardIssue carIssue : EntityCardIssues)
            {
                HashMap<String, String> item = new HashMap<String, String>();
                item.put(ColumnName.c1, checkStringIsNull(carIssue.getCPH()));
                item.put(ColumnName.c2, checkStringIsNull(carIssue.getCardNO()));
                item.put(ColumnName.c3, checkStringIsNull(carIssue.getCardType()));
                item.put(ColumnName.c4, checkStringIsNull(carIssue.getCardState()));
                item.put(ColumnName.c5, checkStringIsNull(carIssue.getUserNO()));
                item.put(ColumnName.c6, checkStringIsNull(carIssue.getUserName()));
                item.put(ColumnName.c7, checkStringIsNull(carIssue.getCarValidStartDate()));
                item.put(ColumnName.c8, checkStringIsNull(carIssue.getCarValidEndDate()));
                item.put(ColumnName.c9, checkStringIsNull(carIssue.getMobNumber()));
                item.put(ColumnName.c10, checkStringIsNull(carIssue.getHomeAddress()));
                item.put(ColumnName.c11, String.valueOf(carIssue.getBalance())); // 充值余额
                item.put(ColumnName.c12, String.valueOf(carIssue.getCardYJ())); // 车辆押金
                item.put(ColumnName.c13, checkStringIsNull(carIssue.getCarType()));
                item.put(ColumnName.c14, checkStringIsNull(carIssue.getCarPlace())); //车位
                item.put(ColumnName.c15, checkStringIsNull(carIssue.getCarIssueDate()));
                item.put(ColumnName.c16, checkStringIsNull(carIssue.getCarIssueUserCard()));
                item.put(ColumnName.c17, checkStringIsNull(carIssue.getCarValidZone()));//下载标识
                item.put(ColumnName.c18, checkStringIsNull(carIssue.getCarMemo()));// 车场备注
                item.put(ColumnName.c19, String.valueOf(carIssue.getCarPlaceNo()));
                items.add(item);
            }
            return items;
        }
        return null;
    }

    /**
     * 发送心跳消息，不然30秒就会出现过期的情况
     */
    private void sendKeepAlive()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int retValue = MonitorRemoteRequest.KeepAlive(Model.token);
                if (retValue == RequestByURL.ERROR_BASE_CODE
                        || RequestByURL.TOKEN_OVERDUE == retValue)
                {
                    mHandler.sendEmptyMessage(MSG_TokenFailed);
                }
                else
                {
                    mHandler.sendEmptyMessageDelayed(MSG_KeppAlive, 5 * 1000);
                }
            }
        }).start();
    }


    private int[] CheDaoIndex; // 按照一定的规则将重新组织视频画面的显示
    private int inChannelNum = 0;// 表示当前入口车道的个数
    private int outChannelNum = 0;// 表示当前出口车道的个数

    /**
     * 重新组织下车道下标
     *
     * @param data
     * @param size
     */
    public void orderCheDaoIndex(List<GetCheDaoSetResp.DataBean> data, int size)
    {
        CheDaoIndex = new int[size]; // CheDaoIndex存放着视频画面对应的下标
        int tempIndex = 0;
        for (int i = 0; i < size; i++)
        {
            /**
             * 1,先找入场的通道下标放到数组中;
             */
            if (data.get(i).getInOut() == CAR_CHANNEL_IN)
            {
                CheDaoIndex[tempIndex] = i;
                tempIndex++;
                inChannelNum++;
            }
        }

        for (int i = 0; i < size; i++)
        {
            /**
             * 2,再找出口的通道下标放到数组中;
             */
            if (data.get(i).getInOut() == CAR_CHANNEL_OUT)
            {
                CheDaoIndex[tempIndex] = i;
                tempIndex++;
                outChannelNum++;
            }
        }

        L.i("orderCheDaoIndex" + "inChannelNum:" + inChannelNum + ",outChannelNum:" + outChannelNum);

        if (tempIndex != size)
        {
            L.e("reorderCheDaoIndex", "数据出现问题");
        }
    }

    private void initFields()
    {
        requestGetCardTypeDef();// 获取车辆类型 即固定车和储值车，临时车

        // 判断是否换班
        if (CR.GetAppConfig(getApplicationContext(), ConstantSharedPrefs.UserCode, "").equals(Model.sUserCard))
        {
            Model.dLoginTime = (long) CR.GetAppConfig(getApplicationContext(), ConstantSharedPrefs.LoginDate, 0L);
        }
        else
        {
            CR.UpdateAppConfig(getApplicationContext(), ConstantSharedPrefs.UserCode, Model.sUserCard);
            CR.UpdateAppConfig(getApplicationContext(), ConstantSharedPrefs.LoginDate, System.currentTimeMillis());
            Model.dLoginTime = System.currentTimeMillis();
        }
        Model.iLoadTimeType = 0;
        requestGetCheDaoSet(); // 获取车道信息，来播放视频数据
    }

    private void requestGetCheDaoSet()
    {
        GetCheDaoSetReq req = new GetCheDaoSetReq();
        req.setToken(Model.token);
        req.setJsonSearchParam(JsonSearchParam.getWhenGetCheDaoSet(String.valueOf(Model.stationID)));
        req.setOrderField(OrderField.getWhenGetCheDaoSet("desc", "asc", "asc"));
        String resultUrl = GetServiceData.getInstance().getResultUrl(METHOD_GETCHEDAOSET, req);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetCheDaoSetResp.class, this, resultUrl));
    }

    private void requestGetCardTypeDef()
    {
        GetCardTypeDefReq cardTypeDefReq = new GetCardTypeDefReq();
        cardTypeDefReq.setToken(Model.token);

        String resultUrl = GetServiceData.getInstance().getResultUrl(METHOD_GETCARDTYPEDEF, cardTypeDefReq);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetCardTypeDefResp.class, this, resultUrl));
    }

    /**
     * 在3路 ~4路的车辆车道
     */
    private void playVideoInGreaterThan2Video()
    {
        if (checkCheDaoSetRespDataInvalid()) return;
        final List<GetCheDaoSetResp.DataBean> data = getCheDaoSetResp.getData();
        int getDataSize = -1;
        if (data.size() > 4)
        {
            orderCheDaoIndex(data, 4);
            getDataSize = 4;
        }
        else
        {
            orderCheDaoIndex(data, data.size());
            getDataSize = data.size();
        }

        for (int i = 0; i < getDataSize; i++)
        {
            final GetCheDaoSetResp.DataBean dataBean = data.get(CheDaoIndex[i]);
            final int tempI = i;
            final int totalSize = getDataSize;
            parkingMonitoringView.setChannelText(tempI, dataBean.getInOutName());

            requestGetNetCameraSet(i, dataBean);
        }
    }

    private void requestGetNetCameraSet(int i, GetCheDaoSetResp.DataBean dataBean)
    {
        GetNetCameraSetReq req = new GetNetCameraSetReq();
        req.setToken(Model.token);
        req.setJsonSearchParam(JsonSearchParam.getWhenGetCameraSet(dataBean.getCameraIP()));

        String resultUrl = GetServiceData.getInstance().getResultUrl(METHOD_GETNETCAMERASET, req);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetNetCameraSetResp.class, this, resultUrl, i));
    }

    /**
     * 在两路以下的车辆车道
     */
    private void playVideoIn2Video()
    {
        if (checkCheDaoSetRespDataInvalid()) return;
        final List<GetCheDaoSetResp.DataBean> data = getCheDaoSetResp.getData();
        int getDataSize = -1;
        if (data.size() > 2)
        {
            orderCheDaoIndex(data, 2);
            getDataSize = 2;
        }
        else
        {
            orderCheDaoIndex(data, data.size());
            getDataSize = data.size();
        }

        for (int i = 0; i < getDataSize; i++)
        {
            GetCheDaoSetResp.DataBean dataBean = data.get(CheDaoIndex[i]);
            int tempI = i;
            int totalSize = getDataSize;
            parkingMonitoringView.setChannelText(tempI, dataBean.getInOutName());
            if (dataBean.getInOut() == CAR_CHANNEL_IN)
            {
                parkingMonitoringView.setChannelText(tempI + 2, "入口图片显示");
            }
            else if (dataBean.getInOut() == CAR_CHANNEL_OUT)
            {
                parkingMonitoringView.setChannelText(tempI + 2, "出口图片显示");
            }

            requestGetNetCameraSet(i, dataBean);
        }
    }

    private void initControl()
    {
        // 这里的设置，可以更加有意思,即用来控制界面的显示;

        // 显示状态栏
        parkingMonitoringView.showStatusBar(Model.sUserName, Model.sUserCard,
                TimeConvertUtils.longToString(Model.dLoginTime), TimeConvertUtils.longToString(System.currentTimeMillis()));

//        获取进场和出场信息对应着 场内车辆明细和车辆收费明细
        requestGetCarIn();
        requestGetCarOut();

//       统计获取车位信息，显示到界面即可
        requestGetParkingInfo();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        //“内存重启”时保存当前的fragment名字
        super.onSaveInstanceState(outState);

        outState.putInt(FragmentChargeManager.CURRENT_FRAGMENT, fragmentChargeManager.getCurrentIndex());
        outState.putInt(FragmentDetailManager.CURRENT_FRAGMENT, fragmentDetailManager.getCurrentIndex());
    }

    private void initFragment(Bundle savedInstanceState)
    {
        fragmentChargeManager = new FragmentChargeManager(getSupportFragmentManager());
        int anInt = -1;
        if (savedInstanceState != null)
        {
            anInt = savedInstanceState.getInt(FragmentChargeManager.CURRENT_FRAGMENT, 0);
        }
        fragmentChargeManager.init(anInt);

        fragmentDetailManager = new FragmentDetailManager(getSupportFragmentManager());
        int index = -1;
        if (savedInstanceState != null)
        {
            index = savedInstanceState.getInt(FragmentChargeManager.CURRENT_FRAGMENT, 0);
        }
        fragmentDetailManager.init(index);
    }


    public static final int MSG_GetCarIn = 0x01;
    public static final int MSG_GetCarOut = 0x02;
    public static final int MSG_ParkingInfo = 0x3;
    public static final int MSG_ChargeInfo = 0x04;
    public static final int MSG_SETCarIn = 0X05;
    public static final int MSG_SETCarOut = 0X06;
    public static final int MSG_KeppAlive = 0x07;
    public long startAliveTime;

    public static final int MSG_UpdateBlackListData = 0x08;
    public static final int MSG_SET_CarChannel = 0x11;

    public static final int MSG_START_VIDEO_PLAY = 0x09;
    public static final int MSG_STOIP_VIDEO_PLAY = 0x10;

    public int CAR_CHANNEL_OUT = 1; // 表示车辆出口标记
    public int CAR_CHANNEL_IN = 0; // 表示车辆入口标记

    public final static int MSG_TokenFailed = 0x12;
    public final static int MSG_CarHintInfoAfterResume = 013; // 恢复到之前的收费信息
    public static final int MSG_SETCarInWithOutCPH = 0x14;
    public static final int MSG_SETCarInComfirmed = 0x15;
    public static final int MSG_updateInfoWhenRecvRecognitionCPH = 0x16; // 当相机识别到车牌数据后，开始进场

    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(final Message msg)
        {
            switch (msg.what)
            {
                case MSG_GetCarIn: // 场内车辆
                {
                    updateCarInParkingData();
                    break;
                }
                case MSG_GetCarOut: // 车辆收费明细
                {
                    updateCarDetailData();
                    break;
                }
                case MSG_ParkingInfo: // 车场信息
                {
                    updateParkingInfo();
                    break;
                }
                case MSG_ChargeInfo:// 收费信息
                {
                    updateChargeInfo();
                    break;
                }
                case MSG_SETCarIn: // 车辆入场
                {
                    SetCarInResp.DataBean entitySetCarIn = (SetCarInResp.DataBean) msg.obj;
                    updateSetCarIn(entitySetCarIn);
                    break;
                }
                case MSG_KeppAlive:
                {
                    sendKeepAlive();
                    break;
                }
                case MSG_SETCarOut:
                {
//                    updateSetCarInByOut(setCarOutResp.getData());
                    break;
                }
                case MSG_UpdateBlackListData:
                {
                    List<EntityBlackList> entityBlackList = (List<EntityBlackList>) msg.obj;
                    formAddBlackListView.setData(entityBlackList);
                    break;
                }
                case MSG_START_VIDEO_PLAY:
                {
                    String objIP = (String) msg.obj;
                    L.i("MSG_START_VIDEO_PLAY ### ip==>>", objIP);

                    if (checkCheDaoSetRespDataInvalid()) return;
                    List<GetCheDaoSetResp.DataBean> data = getCheDaoSetResp.getData();
                    for (int channel = 0; channel < data.size(); channel++)
                    {
                        if (data.get(channel).getCameraIP().equals(objIP))
                        {
                            L.i("MSG_START_VIDEO_PLAY find index:" + channel + ", ip:" + objIP);
                            parkingMonitoringView.setSurfaceEnableWhenPlayVideo(channel);
                            break;
                        }
                    }
                    break;
                }
                case MSG_STOIP_VIDEO_PLAY:
                {
                    String objIP = (String) msg.obj;
                    L.i("MSG_STOIP_VIDEO_PLAY ### ip==>>", objIP);
                    if (checkCheDaoSetRespDataInvalid()) return;
                    List<GetCheDaoSetResp.DataBean> data = getCheDaoSetResp.getData();
                    for (int channel = 0; channel < data.size(); channel++)
                    {
                        if (data.get(channel).getCameraIP().equals(objIP))
                        {
                            L.i("MSG_STOIP_VIDEO_PLAY find index:" + channel + ", ip:" + objIP);
                            parkingMonitoringView.setTextEnableWhenStopVideo(channel);
                            break;
                        }
                    }
                    break;
                }
                case MSG_TokenFailed:
                {
                    finish();
                    break;
                }
                case MSG_CarHintInfoAfterResume:
                {
                    final int arg1 = msg.arg1;
                    String prompt = (String) msg.obj;

                    parkingMonitoringView.showInChargeFragment(prompt);
//                    fragmentChargeManager.setTextData(prompt);

                    mHandler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
//                            fragmentChargeManager.showFragment(arg1);
                            parkingMonitoringView.resumeInChargeFragment();
                        }
                    }, 1000 * 2);
                    break;
                }
                case MSG_SETCarInWithOutCPH:
                {
                    SetCarInWithoutCPHResp.DataBean entitySetCarInWithoutCPH = (SetCarInWithoutCPHResp.DataBean) msg.obj;
                    updateSetCarInWithoutCPH(entitySetCarInWithoutCPH);
                    break;
                }
                case MSG_SETCarInComfirmed:
                {
                    updateSetCarInConfirmed((SetCarInConfirmResp.DataBean) msg.obj);
                    break;
                }
                default:
                {
                    break;
                }
            }
        }
    };

    /**
     * 检测数据的是否无效
     *
     * @return
     */
    private boolean checkCheDaoSetRespDataInvalid()
    {
        if (getCheDaoSetResp == null || getCheDaoSetResp.getData() == null || getCheDaoSetResp.getData().size() == 0)
        {
            return true;
        }
        return false;
    }

    private String checkValue(String value)
    {
        if (value == null)
        {
            return "";
        }
        return value;
    }

    private void updateSetCarInWithoutCPH(SetCarInWithoutCPHResp.DataBean carInWithoutCPH)
    {
        if (carInWithoutCPH == null) return;
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(checkValue(""));// 11行数据
        arrayList.add(checkValue(""));
        arrayList.add(checkValue(carInWithoutCPH.getCardNO()));
        arrayList.add(checkValue(""));
        arrayList.add(checkValue(carInWithoutCPH.getCardType()));
        arrayList.add(checkValue("0.00")); //免费金额
        if (carInWithoutCPH.getInTime() == null || carInWithoutCPH.getInTime().equals(""))
        {
            arrayList.add(TimeConvertUtils.longToString(System.currentTimeMillis()));
        }
        else
        {
            arrayList.add(carInWithoutCPH.getInTime());
        }
        arrayList.add(checkValue("")); // 出场时间
        arrayList.add(checkValue("0.00"));//收费金额
        arrayList.add(checkValue("0.00"));//累计金额
        arrayList.add(checkValue("0.00"));//剩余金额
        fragmentChargeManager.setData(arrayList, null);
    }

    private void updateSetCarInConfirmed(SetCarInConfirmResp.DataBean carInConfirmed)
    {
        if (carInConfirmed == null)
            return;
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(checkValue(carInConfirmed.getUserNO()));// 11行数据
        arrayList.add(checkValue(carInConfirmed.getUserName()));
        arrayList.add(checkValue(carInConfirmed.getCardNO()));
        arrayList.add(checkValue(carInConfirmed.getDeptName()));
        arrayList.add(checkValue(carInConfirmed.getCardType()));
        arrayList.add(checkValue("0.00")); //免费金额
        if (carInConfirmed.getInTime() == null || carInConfirmed.getInTime().equals(""))
        {
            arrayList.add(TimeConvertUtils.longToString(System.currentTimeMillis()));
        }
        else
        {
            arrayList.add(carInConfirmed.getInTime());
        }
        arrayList.add(checkValue(carInConfirmed.getCarValidEndDate())); // 出场时间
        arrayList.add(checkValue("0.00"));//收费金额
        arrayList.add(checkValue("0.00"));//累计金额
        arrayList.add(checkValue("0.00"));//剩余金额
        fragmentChargeManager.setData(arrayList, null);
    }

    private void updateSetCarIn(SetCarInResp.DataBean data)
    {
        if (data == null)
            return;
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(checkValue(data.getUserNO()));// 11行数据
        arrayList.add(checkValue(data.getUserName()));
        arrayList.add(checkValue(data.getCardNO()));
        arrayList.add(checkValue(data.getDeptName()));
        arrayList.add(checkValue(data.getCardType()));
        arrayList.add(checkValue("0.00")); //免费金额
        if (data.getIntime() == null || data.getIntime().equals(""))
        {
            arrayList.add(TimeConvertUtils.longToString(System.currentTimeMillis()));
        }
        else
        {
            arrayList.add(data.getIntime());
        }
        arrayList.add(checkValue(data.getCarValidEndDate())); // 出场时间
        arrayList.add(checkValue("0.00"));//收费金额
        arrayList.add(checkValue("0.00"));//累计金额
        arrayList.add(checkValue("0.00"));//剩余金额
        fragmentChargeManager.setData(arrayList, null);
    }

    private void updateSetCarInByOut(SetCarOutResp.DataBean data)
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(checkValue(data.getUserNO()));
        arrayList.add(checkValue(data.getUserName()));
        arrayList.add(checkValue(data.getCardNO()));
        arrayList.add(checkValue(data.getDeptName()));
        arrayList.add(checkValue(data.getCardType()));

        arrayList.add(checkValue("0.00")); //
        arrayList.add(checkValue(data.getInTime())); //
        arrayList.add(checkValue(data.getOutTime()));
        arrayList.add(checkValue("0.00"));//收费金额
        arrayList.add(checkValue("0.00")); //累计金额
        arrayList.add(checkValue(data.getBalance() + ""));
        fragmentChargeManager.setData(arrayList, null);
    }

    /**
     * 更新收费信息
     */
    private void updateChargeInfo()
    {

    }


    private boolean checkCarParkingInfoInvalid()
    {
        if (getParkingInfoResp == null || getParkingInfoResp.getData() == null)
        {
            return true;
        }
        return false;
    }

    /**
     * 更新车场数据
     */
    private void updateParkingInfo()
    {
        if (checkCarParkingInfoInvalid()) return;
        ArrayList<String> arrayList = new ArrayList<String>();

        GetParkingInfoResp.DataBean data = getParkingInfoResp.getData();
        arrayList.add(data.getMonthCarCountInPark() + "");
        arrayList.add(data.getFreeCarCountInPark() + "");
        arrayList.add(data.getTempCarCountInPark() + "");
        arrayList.add(data.getStrCarCountInPark() + "");
        arrayList.add(data.getManualOpenCarCount() + "");

        fragmentChargeManager.setData(null, arrayList);
    }

    private void updateCarDetailData()
    {
        if (!checkCarInRespValid()) return;
        List<GetCarOutResp.DataBean> data = getCarOutResp.getData();

//        if (entityCarOuts != null && entityCarOuts.size() >= 0)
        {
            L.i("entityCarOuts.size():" + data.size());
            ArrayList<HashMap<String, String>> items = new ArrayList<HashMap<String, String>>();
            for (int i = 0; i < data.size(); i++)
            {
                HashMap<String, String> item = new HashMap<String, String>();
                item.put(ColumnName.c1, checkStringIsNull(data.get(i).getCPH()));
                item.put(ColumnName.c2, checkStringIsNull(data.get(i).getChineseName()));
                item.put(ColumnName.c3, checkStringIsNull(data.get(i).getInTime()));
                item.put(ColumnName.c4, checkStringIsNull(data.get(i).getOutTime()));
                item.put(ColumnName.c5, String.valueOf(data.get(i).getSFJE()));
                item.put(ColumnName.c6, checkStringIsNull(data.get(i).getInGateName()));
                item.put(ColumnName.c7, checkStringIsNull(data.get(i).getOutGateName()));
                item.put(ColumnName.c8, checkStringIsNull(data.get(i).getUserNO()));
                item.put(ColumnName.c9, checkStringIsNull(data.get(i).getUserName()));
                item.put(ColumnName.c10, checkStringIsNull(data.get(i).getCardNO()));
                item.put(ColumnName.c11, checkStringIsNull(data.get(i).getBalance() + ""));//免费原因 FreeReason
                item.put(ColumnName.c12, String.valueOf(data.get(i).getYSJE()));
                item.put(ColumnName.c13, checkStringIsNull(data.get(i).getSFTime()));
                item.put(ColumnName.c14, checkStringIsNull(data.get(i).getInOperator())); // 收费人员 SFOperator
                item.put(ColumnName.c15, checkStringIsNull(data.get(i).getInOperatorCard()));//收费人员编号
                item.put(ColumnName.c16, checkStringIsNull(data.get(i).getInGateName()));//收费口名 SFGate

                item.put(ColumnName.c17, checkStringIsNull(""));//超时标志 OvertimeSymbol
                item.put(ColumnName.c18, checkStringIsNull(data.get(i).getOvertimeSFTime()));//超时收费时间 OvertimeSFTime
                item.put(ColumnName.c29, String.valueOf(data.get(i).getOvertimeSFJE()));//超时收费金额 OvertimeSFJE
                item.put(ColumnName.c20, String.valueOf(data.get(i).getCarparkNO()));//车场编号 CarparkNO
                item.put(ColumnName.c21, String.valueOf(data.get(i).getBigSmall()));//大小标识 BigSmall
                item.put(ColumnName.c22, checkStringIsNull(data.get(i).getFreeReason()));//免费原因 FreeReason
                item.put(ColumnName.c23, checkStringIsNull(data.get(i).getInUser()));//人场人员 InUser
                item.put(ColumnName.c24, checkStringIsNull(data.get(i).getOutUser()));//出场人员 OutUser
                item.put(ColumnName.c25, checkStringIsNull(data.get(i).getInPic()));//入场图片 InPic
                item.put(ColumnName.c26, checkStringIsNull(data.get(i).getOutPic()));//出场图片 OutPic
                item.put(ColumnName.c27, checkStringIsNull(data.get(i).getDeptName()));//部门名称 DeptName
                item.put(ColumnName.c28, "");//证件图片 ZJPic
                item.put(ColumnName.c29, checkStringIsNull(data.get(i).getInOperatorCard()));//入场操作编号 InOperatorCard
                item.put(ColumnName.c30, checkStringIsNull(data.get(i).getOutOperatorCard()));//出场操作编号 OutOperatorCard
                item.put(ColumnName.c31, checkStringIsNull(data.get(i).getInOperator()));//入场操作员 InOperator
                item.put(ColumnName.c32, checkStringIsNull(data.get(i).getOutOperator()));//出场操作员 OutOperator
                items.add(item);
            }
            fragmentDetailManager.setData(null, null, items, null);
        }
    }

    private void updateCarInParkingData()
    {
        if (!checkCarInRespValid()) return;
        List<GetCarInResp.DataBean> data = getCarInResp.getData();

//        if (entityCarIns != null && entityCarIns.size() >= 0)
        {
            int maxTextWidth[] = new int[16];
            for (int m = 0; m < 16; m++)
            {
                maxTextWidth[m] = -1;
            }

            ArrayList<HashMap<String, String>> items = new ArrayList<HashMap<String, String>>();
            for (int i = 0; i < data.size(); i++)
            {
                HashMap<String, String> item = new HashMap<String, String>();
                item.put(ColumnName.c1, checkStringIsNull(data.get(i).getCPH()));
                item.put(ColumnName.c2, checkStringIsNull(data.get(i).getChineseName()));
                item.put(ColumnName.c3, checkStringIsNull(data.get(i).getInTime()));
                item.put(ColumnName.c4, checkStringIsNull(data.get(i).getInGateName()));
                item.put(ColumnName.c5, checkStringIsNull(data.get(i).getUserNO()));
                item.put(ColumnName.c6, checkStringIsNull(data.get(i).getUserName()));
                item.put(ColumnName.c7, String.valueOf(data.get(i).getBalance()));
                item.put(ColumnName.c8, checkStringIsNull(data.get(i).getCardNO()));
                item.put(ColumnName.c9, String.valueOf(data.get(i).getCarparkNO()));
                item.put(ColumnName.c10, String.valueOf(data.get(i).getBigSmall()));
                item.put(ColumnName.c11, "");//免费原因 FreeReason
                item.put(ColumnName.c12, checkStringIsNull(data.get(i).getInPic()));
                item.put(ColumnName.c13, checkStringIsNull(data.get(i).getDeptName()));
                item.put(ColumnName.c14, ""); // ZJPic
                item.put(ColumnName.c15, checkStringIsNull(data.get(i).getInOperatorCard()));
                item.put(ColumnName.c16, checkStringIsNull(data.get(i).getInOperator()));
                items.add(item);

//                maxTextWidth[0] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[0]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[0]);
//                maxTextWidth[1] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[1]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[1]);
                maxTextWidth[2] = ((data.get(i).getInTime().length() > maxTextWidth[2]) ? data.get(i).getInTime().length() : maxTextWidth[2]);
//                maxTextWidth[3] = ((entityCarIns.get(i).getInTime().length() > maxTextWidth[3]) ? entityCarIns.get(i).getInTime().length() : maxTextWidth[3]);
//                maxTextWidth[4] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[4]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[4]);
//                maxTextWidth[5] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[5]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[5]);
//                maxTextWidth[6] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[6]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[6]);
//                maxTextWidth[7] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[7]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[7]);
//                maxTextWidth[8] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[8]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[8]);
//                maxTextWidth[9] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[9]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[9]);
//                maxTextWidth[10] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[10]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[10]);
//                maxTextWidth[11] = ((entityCarIns.get(i).getInPic().length() > maxTextWidth[11]) ? entityCarIns.get(i).getInPic().length() : maxTextWidth[11]);
//                maxTextWidth[12] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[12]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[12]);
//                maxTextWidth[13] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[13]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[13]);
//                maxTextWidth[14] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[14]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[14]);
//                maxTextWidth[15] = ((entityCarIns.get(i).getCPH().length() > maxTextWidth[15]) ? entityCarIns.get(i).getCPH().length() : maxTextWidth[15]);
            }

            // 每一列的长度是不同的,取最长的宽度然后设置相应的界面
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < maxTextWidth[2]; i++)
            {
                stringBuffer.append(" ");
            }

            TextPaint paint = new TextPaint();
            float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
            paint.setTextSize(scaledDensity * 12); // 12sp
            int textSize = (int) paint.measureText(stringBuffer.toString());
//            L.i("maxTextWidth[2]:" + maxTextWidth[2] + ",maxTextWidth[11]:" + maxTextWidth[11] + ",textSize:" + textSize);
            maxTextWidth[2] = textSize;
            fragmentDetailManager.setData(items, maxTextWidth, null, null);
        }
    }

    @Override
    protected void onDestroy()
    {
        for (int i = 0; i < Model.iChannelCount; i++)
        {
            L.i("debug," + "   stopVideoByIndex-----------------------------------i:----" + i);
            parkingMonitoringView.stopVideoByIndex(i); // 这里的控件出现泄漏，如果取消视频播放呢?
        }

        mHandler.removeMessages(MSG_GetCarIn);
        mHandler.removeMessages(MSG_GetCarOut);
        mHandler.removeMessages(MSG_ParkingInfo);
        mHandler.removeMessages(MSG_SETCarIn);
        mHandler.removeMessages(MSG_SETCarOut);
        mHandler.removeMessages(MSG_KeppAlive);
        mHandler.removeMessages(MSG_UpdateBlackListData);

        mHandler.removeMessages(MSG_SET_CarChannel);
        mHandler.removeMessages(MSG_START_VIDEO_PLAY);
        mHandler.removeMessages(MSG_STOIP_VIDEO_PLAY);
        mHandler.removeMessages(MSG_TokenFailed);

        tcpsdk.getInstance().cleanup();
        mHandler.removeCallbacksAndMessages(null); // 清除所有的handler消息和runnable等;

        if (queueTask != null)
            queueTask.end();
        ConcurrentQueueHelper.getInstance().destory();
        super.onDestroy();

        executor.shutdown();
    }


    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    // 处理无牌车入场的服务器数据应答
    private void dealSetCarInSetCarInWithoutCPHResponse(Object obj)
    {
        SetCarInWithoutCPHResp setCarInWithoutCPHResp = (SetCarInWithoutCPHResp) obj;

        dealCarInWithOutCPH(setCarInWithoutCPHResp);
        // 更新画面提示信息
        updateCarHintToFragment(MSG_CarHintInfoAfterResume, setCarInWithoutCPHResp.getMsg());
        updateCarChargeToFragment(MSG_SETCarInWithOutCPH, setCarInWithoutCPHResp.getData());
    }

    @Override
    public void success(Object data, Object obj, int intParam)
    {
        if (obj == null) return;
        L.e("success" + data + "<--->" + obj.toString());
        if (obj instanceof SetCarInWithoutCPHResp) //无牌车入场
        {
            dealSetCarInSetCarInWithoutCPHResponse(obj);
        }
        else if (obj instanceof GetCardTypeDefResp)
        {
            GetCardTypeDefResp resp = (GetCardTypeDefResp) obj;
            CR.BinDic(resp.getData());
        }
        else if (obj instanceof GetCheDaoSetResp)
        {
            getCheDaoSetResp = (GetCheDaoSetResp) obj;
            L.i("getCheDaoSetResp:" + getCheDaoSetResp.getData().size());
            /**
             * 4_06 视频显示的画面的逻辑：必定是先显示入场，然后显示出场；
             *                         入场的图片是在视频播放下面;
             *      存在的情况：
             *          前提：最多只有4路显示视频；且不是副摄像头拍摄；
             *          情况的处理情况如下：
             *              1，如果入场和出场的总数大于4，即只取前面四路；
             *              2，如果总通道是1，或入场或出场 且左上角和左下角分别显示视频和图片； 右侧的画面显示数据不存在；
             *              3，如果总通道是2，且一进一出，那么显示入场再显示出场；
             *              4，             且两个入场或出场，则分开显示，且下面显示图片；
             *              5，如果总通数是3，那么全部显示视频，不显示图片；且同样是先入场后出场的顺序
             *              6，如果总通道是4，那么也是先入口，后出口的方式显示；
             *         怎么逻辑封装
             *              1,影响因子，总通道数，通道数 > 2,则不显示图片；
             *              2,优先显示入场视频；
             */

            playVideoIn2Video();
//                if (getCheDaoSetResp.getData().size() <= 2)
//                {
//                    playVideoIn2Video();
//                }
//                else if (getCheDaoSetResp.getData().size() > 2)
//                {
//                    playVideoInGreaterThan2Video();
//                }
        }
        else if (obj instanceof GetNetCameraSetResp)//播放相应的视频数据即可
        {
            GetNetCameraSetResp getNetCameraSetResp = (GetNetCameraSetResp) obj;
            int index = intParam;

            List<GetNetCameraSetResp.DataBean> getNetCameraSetRespData = getNetCameraSetResp.getData();
            if (data == null || getNetCameraSetRespData.size() == 0) return; // 数据可能为空

            String videoType = getNetCameraSetRespData.get(0).getVideoType();
            switch (videoType)
            {
                case "ZNYKTY5":
                {
                    L.i("videoType:" + videoType + " index:" + index);
                    parkingMonitoringView.playVideoByIndex(index, getNetCameraSetRespData.get(0).getVideoIP(), mHandler);
                    break;
                }
            }
        }
        else if (obj instanceof GetCarInResp)
        {
            getCarInResp = (GetCarInResp) obj;
            mHandler.sendEmptyMessage(MSG_GetCarIn);
        }
        else if (obj instanceof GetCarOutResp)
        {
            getCarOutResp = (GetCarOutResp) obj;
            mHandler.sendEmptyMessage(MSG_GetCarOut);
        }
        else if (obj instanceof GetParkingInfoResp)
        {
            getParkingInfoResp = (GetParkingInfoResp) obj;
        }
        else if (obj instanceof SetCarInResp)
        {
            SetCarInResp setCarInResp = (SetCarInResp) obj;
            if (setCarInResp == null || setCarInResp.getData() == null)
            {
                return;
            }

            SetCarInReq setCarInReq = (SetCarInReq) data;
            dealSetCarInResponse(setCarInResp, setCarInReq.getCPH(), intParam, PlateColorEnum.valueOf(setCarInReq.getCPColor()));
        }
    }


    @Override
    public void error(Object url, String string)
    {

    }

    /**
     * 车牌登记的view
     */
    public class ParkingPlateRegisterViewSub extends ParkingPlateRegisterView
    {
        public ParkingPlateRegisterViewSub(ParkingMonitoringActivity activity)
        {
            super(activity);
        }

        @Override
        public void startLoadData()
        {
            super.startLoadData();
//            exe.post(new Runnable()
            new Thread(new Runnable()
            {
                @Override
                public void run() // 获取机号
                {
                    final List<EntityParkJHSet> entityParkJHSets = MonitorRemoteRequest.GetCCJiHao(Model.token);
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            parkingPlateRegisterView.setJiHaoData(entityParkJHSets);
                        }
                    });

                }
            }).start();

            new Thread(new Runnable()
            {
                @Override
                public void run()// 更新列表数据
                {
                    final ArrayList<HashMap<String, String>> hashMaps = dealDataGridView();
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            parkingPlateRegisterView.setGridViewData(hashMaps);
                        }
                    });

                }
            }).start();

            new Thread(new Runnable()
            {
                @Override
                public void run()// 更新列表数据
                {
                    final List<String> strings = dealUserNo();
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            parkingPlateRegisterView.setSpinnerUserNO(strings);
                        }
                    });

                }
            }).start();

            new Thread(new Runnable()
            {
                @Override
                public void run()// 更新列表数据
                {
                    final List<EntityCarTypeInfo> entityCarTypeInfos = MonitorRemoteRequest.GetGetFXCardTypeToTrue(Model.token);
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            parkingPlateRegisterView.setSpinnerCarType(entityCarTypeInfos);
                        }
                    });

                }
            }).start();

            dealRights();
        }

        @Override
        public void onSelectOnCarAutoNo()
        {
            showCarTextByNetRequest();
        }

        @Override
        public void onSelectOnUserAutoNo()
        {
            showUserNoByNetRequest();
        }

        @Override
        public void onPlateRegisterQuitBtn()
        {
            parkingPlateRegisterView.dismiss();
        }

        @Override
        public void onPlateRegisterPrintBtn()
        {
        }

        @Override
        public void onPlateRegisterDeleteBtn()
        {
            List<String> selectJiHao = parkingPlateRegisterView.getSelectJiHao();
            for (String o : selectJiHao)
            {
                L.i("" + "获取点击的机号为:" + o.toString());
            }
        }

        @Override
        public void onPlateRegisterSaveWhenCheckMultiPace(final EntityCardIssue carIssue)
        {
            if (carIssue == null)
                return;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    String strCPHS = "";
                    String strCPHR = "";
                    List<String> mutliCarCPH = parkingPlateRegisterView.getMutliCarCPH();
                    for (String item : mutliCarCPH)
                    {
                        carIssue.setCPH(item);
                        strCPHS = strCPHS == "" ? carIssue.getCPH() : strCPHS + "," + carIssue;

                        List<EntityCardIssue> lstCI = MonitorRemoteRequest.GetAutoCardNo(Model.token);
                        if (lstCI.size() > 0)
                        {
                            int cardnomax = Integer.parseInt(lstCI.get(0).getCardNO().substring(2, 2 + 6));
                            carIssue.setCardNO("88" + CR.stringPadLeft(String.valueOf(cardnomax + 1), 6, '0'));
                        }
                        else
                        {
                            carIssue.setCardNO("88000001");
                        }


                        boolean b = MonitorRemoteRequest.IfCardNOExitsbt(Model.token, item);
                        if (b)
                        {
                            mHandler.post(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    T.showShort(ParkingMonitoringActivity.this, "车牌已重复是否覆盖添加"); // 需要对话框显示是否覆盖
                                }
                            });
                        }

                        String carType = parkingPlateRegisterView.GetCarType(0);
                        L.i("carType:" + carType);
                        //判断车牌是否是已入场的临时车,如果是,则把入场记录改为发行卡类  2015-12-26  2016-06-21
                        if (carType.substring(0, 3 - 0).equals("Mth")
                                || carType.substring(0, 3 - 0).equals("Fre")
                                || carType.substring(0, 3 - 0).equals("Str"))
                        {
                            int value = MonitorRemoteRequest.GetInRecordIsTmp(Model.token, item);
                            if (value > 0)
                            {// 确认三次
//                                if (System.Windows.Forms.MessageBox.Show("该车是【已入场的临时车】，若继续操作，则此车出场时将不按临时车收费！\n请确认是否继续？", "重要提示", System.Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Exclamation) == System.Windows.Forms.DialogResult.No)
//                                {
//                                    return;
//                                }
//                                if (System.Windows.Forms.MessageBox.Show("该车是【已入场的临时车】，若继续操作，则此车出场时将不按临时车收费！\n请【再次】确认是否继续？", "重要提示", System.Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Exclamation) == System.Windows.Forms.DialogResult.No)
//                                {
//                                    return;
//                                }
//                                if (System.Windows.Forms.MessageBox.Show("该车是【已入场的临时车】，若继续操作，则此车出场时将不按临时车收费！\n请【最后】确认是否继续？", "重要提示", System.Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Exclamation) == System.Windows.Forms.DialogResult.No)
//                                {
//                                    return;
//                                }
                            }
                        }


                    }
                }
            }).start();
        }

        @Override
        public void onPlateRegisterSaveNormal(final EntityCardIssue carIssue)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    //1,判断卡片号码是否重复  2016-06-28 th
                    boolean value = MonitorRemoteRequest.IfCardNOExitsbt(Model.token, carIssue.getCardNO());
                    if (value)
                    {
                        mHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                T.showShort(ParkingMonitoringActivity.this, "车辆编号已重复是否覆盖添加！\n\n【" + carIssue.getCardNO() + "】");
                            }
                        });
                    }

                    //2,判断车牌是否重复
                    if (MonitorRemoteRequest.IfCPHExitsbt(Model.token, carIssue.getCPH()))
                    {
                        mHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                T.showShort(ParkingMonitoringActivity.this, "车牌已重复是否覆盖添加！\n\n【" + carIssue.getCPH() + "】");
                            }
                        });
                    }
                    String carType = parkingPlateRegisterView.GetCarType(0);
                    L.i("carType:" + carType + "," + carIssue.getCPH());
                    //判断车牌是否是已入场的临时车,如果是,则把入场记录改为发行卡类  2015-12-26  2016-06-21
                    if (carType.substring(0, 3 - 0).equals("Mth")
                            || carType.substring(0, 3 - 0).equals("Fre")
                            || carType.substring(0, 3 - 0).equals("Str"))

                    {
                        int iRet = MonitorRemoteRequest.GetInRecordIsTmp(Model.token, carIssue.getCPH());
                        if (iRet > 0)
                        {
//                            if (System.Windows.Forms.MessageBox.Show("该车是【已入场的临时车】，若继续操作，则此车出场时将不按临时车收费！\n请确认是否继续？", "重要提示", System.Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Exclamation) == System.Windows.Forms.DialogResult.No)
//                            {
//                                return;
//                            }
//                            if (System.Windows.Forms.MessageBox.Show("该车是【已入场的临时车】，若继续操作，则此车出场时将不按临时车收费！\n请【再次】确认是否继续？", "重要提示", System.Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Exclamation) == System.Windows.Forms.DialogResult.No)
//                            {
//                                return;
//                            }
//                            if (System.Windows.Forms.MessageBox.Show("该车是【已入场的临时车】，若继续操作，则此车出场时将不按临时车收费！\n请【最后】确认是否继续？", "重要提示", System.Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Exclamation) == System.Windows.Forms.DialogResult.No)
//                            {
//                                return;
//                            }
                        }
                        /**
                         * 有入场记录，//则把入场记录改为发行卡类
                         */
                        MonitorRemoteRequest.UpdateInCPHCardType(Model.token, carIssue.getCPH(), parkingPlateRegisterView.GetCarType(0), carIssue.getCardNO());
                        MonitorRemoteRequest.AddOptLog(Model.token, "车牌登记", carIssue.getCPH() + " 已入场临时车，改为" + carIssue.getCardType()
                                , Model.sUserCard, Model.sUserName, String.valueOf(Model.stationID));
                        carIssue.setID(0);
                    }

                    if (parkingPlateRegisterView.getCheckAutoCarNoIsChecked())
                    {
                        EntityUserInfo entityUserInfo = parkingPlateRegisterView.GetPersonnel();
                        if (entityUserInfo == null)
                        {
                            return;
                        }

                        MonitorRemoteRequest.PersonnelAddCpdj(Model.token, entityUserInfo, MonitorRemoteRequest.IsExsits(Model.token, entityUserInfo.getUserNO()));
                    }

                    if (carIssue.getID() != 0)
                    {
                        MonitorRemoteRequest.UpdateCPdjfx(Model.token, carIssue);
                    }
                    else
                    {
                        MonitorRemoteRequest.DeleteFaXing(Model.token, carIssue.getCardNO());

                        EntityMoney money = new EntityMoney();
                        money.setCardNO(carIssue.getCardNO());
                        money.setOptDate(TimeConvertUtils.longToString(System.currentTimeMillis()));
                        money.setOperatorNO(Model.sUserCard);
                        money.setSFJE(carIssue.getBalance());
                        money.setOptType("1");

                        money.setNewStartDate(carIssue.getCarValidStartDate());
                        money.setNewEndDate(carIssue.getCarValidEndDate());
                        money.setLastEndDate(carIssue.getCarValidStartDate());

                        MonitorRemoteRequest.AddMoney(Model.token, money);

                        if (carIssue.getCardYJ() > 0)
                        {
                            money.setSFJE(carIssue.getCardYJ());
                            money.setOptType("E");
                            MonitorRemoteRequest.AddMoney(Model.token, money);
                        }

                        //2015-07-29
                        if (carIssue.getCarCardType().equals("Opt"))
                        {
                            MonitorRemoteRequest.AddOperator(Model.token, carIssue);//插入操作员表
                        }

                        if (carIssue.getCarCardType().substring(0, 3 + 0).equals("Str"))
                        {
                            carIssue.setBalance(0);
                        }

                        MonitorRemoteRequest.Addchdj(Model.token, carIssue);

                        // 更新界面数据重新获取数据即可
                        final ArrayList<HashMap<String, String>> hashMaps = dealDataGridView();
                        mHandler.post(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                parkingPlateRegisterView.setGridViewData(hashMaps);
                                T.showShort(ParkingMonitoringActivity.this, "车牌添加成功!");
                                parkingPlateRegisterView.setUIEnableWhenAddSuccess();
                            }
                        });

                        MonitorRemoteRequest.AddOptLog(Model.token, "车牌登记", carIssue.getCPH() + " 车牌添加成功---车辆号码:"
                                , Model.sUserCard, Model.sUserName, String.valueOf(Model.stationID));
                    }
                }
            }).start();
        }

        /**
         * 点击了增加按钮
         */
        @Override
        public void onPlateRegisterAddBtn()
        {
            showCarTextByNetRequest();//重新获取编号
            showUserNoByNetRequest();
        }

        private void showCarTextByNetRequest()
        {
            new PlateRegisterAsyncTask(parkingPlateRegisterView)
            {
                @Override
                public Object onExecuteExpectedTask() // 开始执行
                {
                    String objectText = "";
                    List<EntityCardIssue> EntityCardIssues = MonitorRemoteRequest.GetAutoCardNo(Model.token);
                    if (EntityCardIssues.size() > 0)
                    {
                        int max = Integer.parseInt(EntityCardIssues.get(0).getCardNO().substring(2, 2 + 6));
                        objectText = ParkingPlateRegisterView.CONST_CAR_NO_PREFIX + CR.stringPadLeft(String.valueOf(max + 1), 6, '0');
                    }
                    else
                    {
                        objectText = ParkingPlateRegisterView.CONST_CAR_NO;
                    }
                    return objectText;
                }

                @Override
                public void onEndExecuteTask(Object o)
                {
                    parkingPlateRegisterView.setCarNoText((String) o);
                }
            }.execute("获取车辆自动编号自动-_- -_-");
        }

        private void showUserNoByNetRequest()
        {
            new PlateRegisterAsyncTask(parkingPlateRegisterView)
            {
                @Override
                public Object onExecuteExpectedTask() // 开始执行
                {
                    String objectText = "";
                    List<EntityUserInfo> entityUserInfo = MonitorRemoteRequest.GetAutoUsernoPersonnel(Model.token);
                    if (entityUserInfo.size() > 0)
                    {
                        int max = Integer.parseInt(entityUserInfo.get(0).getUserNO().substring(1, 1 + 5));
                        objectText = ParkingPlateRegisterView.CONST_USER_NO_PREFIX + CR.stringPadLeft(String.valueOf(max + 1), 5, '0');
                    }
                    else
                    {
                        objectText = ParkingPlateRegisterView.CONST_USER_NO;
                    }
                    return objectText;
                }

                @Override
                public void onEndExecuteTask(Object o)
                {
                    parkingPlateRegisterView.setUserNoText((String) o);
                }
            }.execute("获取人员自动编号自动-_- -_-");

        }
    }

    /**
     * 从 Model rights中获取指定的权限
     *
     * @param title
     * @param itemName
     * @return
     */
    public List<GetRightsByGroupIDResp.DataBean> GetRightsByName(String title, String itemName)
    {
        if (Model.lstRights == null || Model.lstRights.size() == 0)
        {
            return null;
        }

        List<GetRightsByGroupIDResp.DataBean> temp = new ArrayList<GetRightsByGroupIDResp.DataBean>();
        for (GetRightsByGroupIDResp.DataBean val : Model.lstRights)
        {
            if (val.getFormName().equals(title) && val.getItemName().equals(itemName))
            {
                temp.add(val);
            }
        }
        return temp;
    }

    class QueueTask extends Thread
    {
        private boolean taskFlag;

        public QueueTask(boolean runningFlag)
        {
            taskFlag = runningFlag;
        }

        @Override
        public void run()
        {
            try
            {
                while (taskFlag)
                {
                    final ModelNode modelNode = ConcurrentQueueHelper.getInstance().get();
                    if (modelNode != null)
                    {
                        L.i("###　接受到车牌数据 ###" + modelNode.toString());
                        switch (modelNode.type)
                        {
                            case QUEUE_CAR_IN_TYPE_AUTO: // 手动输入车牌

                                requestUrlUpdateUiWhenSetIn((SetCarInReq) modelNode.data, modelNode.getiDzIndex(), PlateColorEnum.Unknown);
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        dealCPHInfoFromCamera(modelNode.getiDzIndex(), modelNode.getStrCPH(), BitmapUtils.fileToBitmap(modelNode.getStrFileJpg()), CAR_CHANNEL_IN);
                                    }
                                });
                                break;
                            case QUEUE_CAR_IN_TYPE_AUTO_NOPLATE:// 无牌车手动输入车牌

                                requestUrlRequestWhenNoPlateIn((SetCarInWithoutCPHReq) modelNode.data);

                                final Bitmap inNoPlateBitmap = BitmapUtils.fileToBitmap(modelNode.getStrFileJpg());
                                mHandler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        dealCPHInfoFromCamera(modelNode.getiDzIndex(), modelNode.getStrCPH(), inNoPlateBitmap, CAR_CHANNEL_IN);
                                    }
                                });
                                break;
                            case QUEUE_CAR_OUT_TYPE_AUTO: // 手动接收的车辆出场
                                if (requestUrlUpdateUIWhenSetCarOut((SetCarOutReq) modelNode.data, modelNode.getiDzIndex(), PlateColorEnum.Unknown) >= 0)
                                {
                                    String strFileJpg = modelNode.getStrFileJpg();
                                    final Bitmap bitmap = BitmapUtils.fileToBitmap(strFileJpg);
                                    mHandler.post(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            dealCPHInfoFromCamera(modelNode.getiDzIndex(), modelNode.getStrCPH(), bitmap, CAR_CHANNEL_OUT);
                                        }
                                    });
                                }
                                break;
                            case QUEUE_CAR_INOUT_TYPE_RECOGNITION: // 相机的主动识别
                                int index = modelNode.getiDzIndex();
                                if (checkCheDaoSetRespDataInvalid()) return;

                                List<GetCheDaoSetResp.DataBean> data = getCheDaoSetResp.getData();
                                if (data.get(CheDaoIndex[index]).getInOut() == CAR_CHANNEL_IN)
                                {
                                    L.i("入场:" + data.get(CheDaoIndex[index]).getInOutName());
                                    SetCarInReq req = new SetCarInReq(); // 发送进场数据
                                    req.setCPH(modelNode.getStrCPH());
                                    req.setToken(Model.token);
                                    req.setCtrlNumber(data.get(CheDaoIndex[modelNode.getiDzIndex()]).getCtrlNumber());
                                    req.setStationId(Model.stationID);
                                    requestUrlUpdateUiWhenSetIn(req, index, PlateColorEnum.Unknown);
                                    mHandler.post(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            dealCPHInfoFromCamera(modelNode.getiDzIndex(), modelNode.getStrCPH(), modelNode.picture, CAR_CHANNEL_IN);
                                        }
                                    });
                                }
                                else if (data.get(CheDaoIndex[index]).getInOut() == CAR_CHANNEL_OUT)
                                {
                                    L.i("出场:" + data.get(CheDaoIndex[index]).getInOutName());
                                    SetCarOutReq req = new SetCarOutReq();
                                    req.setCPH(modelNode.getStrCPH());
                                    req.setToken(Model.token);
                                    req.setCtrlNumber(data.get(CheDaoIndex[modelNode.getiDzIndex()]).getCtrlNumber());
                                    req.setStationId(Model.stationID);

                                    if (requestUrlUpdateUIWhenSetCarOut(req, modelNode.getiDzIndex(), PlateColorEnum.Unknown) >= 0)
                                    {
                                        final Bitmap bitmap = modelNode.picture;
                                        mHandler.post(new Runnable()
                                        {
                                            @Override
                                            public void run()
                                            {
                                                dealCPHInfoFromCamera(modelNode.getiDzIndex(), modelNode.getStrCPH(), bitmap, CAR_CHANNEL_OUT);
                                            }
                                        });
                                    }
                                }
                                break;
                            case QUEUE_BLACKLIST:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.SendCombinationVioce(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (String) modelNode.data);
                                break;
                            }
                            case QUEUE_NOTHISLANEPERMISSION:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.VoiceLoad(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (ConstantClass.VoiceEnum) modelNode.data);
                                break;
                            }
                            case QUEUE_BEOVERDUE:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.VoiceLoad(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (ConstantClass.VoiceEnum) modelNode.data);
                                break;
                            }
                            case QUEUE_OPENGATE:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.OpenGate(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID);//自动开闸
                                break;
                            }
                            case QUEUE_VOICEINYW:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.VoiceInYW(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (VoiceInYWModel) modelNode.data);
                                break;
                            }
                            case QUEUE_CONFIRMCUTOFF:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.SendCombinationVioce(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (String) modelNode.data);
                                break;
                            }
                            case QUEUE_PERSONALFULL:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.SendCombinationVioce(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (String) modelNode.data);
                                break;
                            }
                            case QUEUE_PROHIBITCURRENT:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.ParkinglotFull(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (Integer) modelNode.data);
                                break;
                            }
                            case QUEUE_PROHIBITCUTOFF:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.SendCombinationVioce(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (String) modelNode.data);
                                break;
                            }
                            case QUEUE_CARFULL:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.ParkinglotFull(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (Integer) modelNode.data);
                                break;
                            }
                            case QUEUE_BALANCENOTENOUGH:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.NoMoneyInOut(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID);
                                break;
                            }
                            case QUEUE_TEMPORARYCARNOTINSMALL:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.SendCombinationVioce(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (String) modelNode.data);
                                break;
                            }
                            case QUEUE_CARFULLCONFIRMCUTOFF:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.ParkinglotFull(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (Integer) modelNode.data);
                                break;
                            }
                            case queue_MthBeOverdueToTmpCharge:
                            {
                                int dzIndex = modelNode.getiDzIndex();
                                udpSendbll.SendCombinationVioce(Model.Channels[dzIndex].sIP, Model.Channels[dzIndex].iCtrlID, (String) modelNode.data);
                                break;
                            }
                            default:
                                break;
                        }

                    }
                    Thread.sleep(30);
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                L.i("结束：" + ex.toString());
            }
        }

        public void end()
        {
            taskFlag = false;
            try
            {
                queueTask.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 请求服务器数据，然后更新ui界面
     */
    private int requestUrlUpdateUIWhenSetCarOut(final SetCarOutReq req, final int index, PlateColorEnum color)
    {
        final String srcCPH = req.getCPH(); // srcCPH 在请求到服务器时
        SetCarOutResp setCarOutResp = GetServiceData.getInstance().SetCarOut(req);
        if (setCarOutResp == null)
        {
            return -1;
        }

        if (index < 0 || index > Model.iChannelCount - 1)
        {
            return -1;
        }


        if (!CR.CheckUpCPH(srcCPH))
        {
            return -1;
        }

        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                parkingMonitoringView.setCPHText(index, srcCPH);
            }
        });

        dealSetCarOutRcode(setCarOutResp, srcCPH, index, color);

        return 0;
    }

    /**
     * 更新左侧画面的数据
     *
     * @param index  表示那个画面
     * @param CPH    表示车牌
     * @param bitmap 表示图像数据 只考虑两个图片
     */
    private void dealCPHInfoFromCamera(int index, String CPH, Bitmap bitmap, int type)
    {
        parkingMonitoringView.setCPHText(index, CPH);
        if (type == CAR_CHANNEL_IN)
            parkingMonitoringView.setInPicture(index, bitmap);
        else
            parkingMonitoringView.setOutPicture(index, bitmap);
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @NonNull
    private SetCarInReq initSetCarIn(String CPH, int ctrlNumber)
    {
        SetCarInReq setCarInReq = new SetCarInReq(); // 发送进场数据
        setCarInReq.setCPH(CPH);
        setCarInReq.setToken(Model.token);
        setCarInReq.setCtrlNumber(ctrlNumber);
        setCarInReq.setStationId(Model.stationID);
        return setCarInReq;
    }

    // 把camera信息放到相应的列表中
    private List<GetNetCameraSetResp> netCameraList = new ArrayList<GetNetCameraSetResp>();

    private boolean checkCarInRespValid()
    {
        if (getCarInResp == null
                || getCarInResp.getData() == null
                || getCarInResp.getData().size() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean checkCarOutRespValid()
    {
        if (getCarOutResp == null
                || getCarOutResp.getData() == null
                || getCarOutResp.getData().size() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 处理出场的返回值
     *
     * @param resp
     */
    public boolean dealSetCarOutRcode(final SetCarOutResp resp, String srcCPH, final int index, final PlateColorEnum color)
    {
        RCodeEnum rCodeEnum = RCodeEnum.valueOf(Integer.parseInt(resp.getRcode()));
        final SetCarOutResp.DataBean data = resp.getData();
        L.i("dealSetCarOutRcode:" + data);
        switch (rCodeEnum)
        {
            case BlackList:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume
                        , prepareDetectString(data.getCPH(), srcCPH) + ":" + prepareDetectString(data.getBlackReason(), ""));
                /**
                 * 发送语音
                 */
                return false;
            }
            case NoThisLanePermission:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "在 " + getCheDaoSetResp.getData().get(CheDaoIndex[index]).getCtrlNumber() + " 号机上无权限！");
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        parkingMonitoringView.setOperatorHintInfo(getCheDaoSetResp.getData().get(CheDaoIndex[index]).getCtrlNumber() + " 号机上无权限!");
                    }
                });
                /**
                 * 发送语音
                 */
                return false;
            }
            case BeOverdue:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "已过期，请到管理处延期!");
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        parkingMonitoringView.setOperatorHintInfo(getCheDaoSetResp.getData().get(CheDaoIndex[index]).getCtrlNumber() + "已过期，请到管理处延期!");
                    }
                });
                /**
                 * 发送语音
                 */
                GetServiceData.getInstance().requestAddOptLog("在线监控:FlowProcessing", "已过期，请联系管理处" + data.getCardNO());
                return false;
            }
            case BalanceNotEnough:
            {
                updateCarHintToFragment(MSG_CarHintInfoAfterResume, "余额不足，请先充值!");
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        parkingMonitoringView.setOperatorHintInfo(getCheDaoSetResp.getData().get(CheDaoIndex[index]).getCtrlNumber() + "余额不足，请先充值!");
                    }
                });
                /**
                 * 发送语音
                 */
                return false;
            }
            case NotFoundApproachRecord: // 没有发卡行记录，弹出入场的画面;
            {
                L.i("data.getImagePath():" + data.getImagePath());
//                ShowImage(index, data.getImagePath());
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ParkingTempGob_bigViewSub parkingTempGob_bigViewSub = new ParkingTempGob_bigViewSub(ParkingMonitoringActivity.this
                                , ParkingTempGob_bigView.E_VIEW_TYPE.E_VIEW_CHARGE_NOT_RECORD
                                , PlateColorEnum.Unknown
                                , index
                                , data
                                , filesJpg
                                , mHandler);
                        parkingTempGob_bigViewSub.setCallback(new RefreshParkingMonitorView());
                        parkingTempGob_bigViewSub.show();
                    }
                });
                return false;
            }
            case ProhibitCutOff:
            {
                // cmd.LoadLsNoX2010znykt(laneIndex, "ADD4"); 发送语音
                return false;
            }
            case OK: //继续进行处理
            {
                break;
            }
            default:
            {
                // 弹出对话框方便进行显示信息
                L.i("rCodeEnum:" + "no find valid type");
                return false;
            }
        }
        String cardType = data.getCardType();
        if (cardType.length() > 3)
        {
            data.getCarPlace();
            String carPlace = prepareDetectString(data.getCarPlace(), "");
            int openMode = Integer.parseInt(data.getOpenMode());
            if (openMode == OpenWayEnum.ConfirmCutOff.getValue())
            {
                if (cardType.substring(0, 3).equals("Mth")
                        || cardType.substring(0, 3).equals("Str")
                        || cardType.substring(0, 3).equals("Fre")) // 固定车确认开闸
                {
                    final Map<String, Object> objectObjectTreeMap = new TreeMap<>();
                    objectObjectTreeMap.put("laneIndex", index);
                    objectObjectTreeMap.put("CardNO", data.getCardNO());
                    objectObjectTreeMap.put("CPH", data.getCPH());
                    objectObjectTreeMap.put("CardType", data.getCardType());
                    objectObjectTreeMap.put("InTime", data.getInTime());
                    objectObjectTreeMap.put("OutTime", data.getOutTime());
                    objectObjectTreeMap.put("InOutName", data.getInOutName());
                    objectObjectTreeMap.put("RemainingDays", data.getRemainingDays());
                    objectObjectTreeMap.put("RemainingPlaceCount", data.getRemainingPlaceCount());

                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ParkingMthCPHView parkingMthCPHView = new ParkingMthCPHView(ParkingMonitoringActivity.this, objectObjectTreeMap)
                            {
                                @Override
                                public void onCancelChargeClick()
                                {
                                    new Thread(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            requestCancelCharge(data);
                                        }
                                    }).start();
                                }
                            };
                            parkingMthCPHView.show();
                        }
                    });

                }
                else if (cardType.substring(0, 3).equals("Tmp")
                        || cardType.substring(0, 3).equals("Mtp"))//临时车和月临车，开闸确认弹出收费窗口
                {
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ParkingTempGob_bigViewSub parkingTempGob_bigViewSub = new ParkingTempGob_bigViewSub(ParkingMonitoringActivity.this
                                    , ParkingTempGob_bigView.E_VIEW_TYPE.E_VIEW_CHARGE_HAVA_RECORD
                                    , PlateColorEnum.Unknown
                                    , index
                                    , data
                                    , filesJpg
                                    , mHandler);
                            parkingTempGob_bigViewSub.setCallback(new RefreshParkingMonitorView());
                            parkingTempGob_bigViewSub.show();
                        }
                    });
                }
                else if (cardType.substring(0, 3).equals("Mth"))
                {
                    final Map<String, Object> objectObjectTreeMap = new TreeMap<>();
                    objectObjectTreeMap.put("laneIndex", index);
                    objectObjectTreeMap.put("CardNO", data.getCardNO());
                    objectObjectTreeMap.put("CPH", data.getCPH());
                    objectObjectTreeMap.put("CardType", data.getCardType());
                    objectObjectTreeMap.put("OutTime", data.getOutTime());
                    objectObjectTreeMap.put("InOutName", data.getInOutName());
                    objectObjectTreeMap.put("RemainingDays", data.getRemainingDays());
                    objectObjectTreeMap.put("RemainingPlaceCount", data.getRemainingPlaceCount());

                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ParkingMthCPHView parkingMthCPHView = new ParkingMthCPHView(ParkingMonitoringActivity.this, objectObjectTreeMap)
                            {
                                @Override
                                public void onCancelChargeClick()
                                {
                                    new Thread(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            requestCancelCharge(data);
                                        }
                                    }).start();
                                }
                            };
                            parkingMthCPHView.show();
                        }
                    });
                }
            }
            else// 不确认开闸
            {
                if (cardType.substring(0, 3).equals("Mth")
                        || cardType.substring(0, 3).equals("Str")
                        || cardType.substring(0, 3).equals("Fre"))
                {
                    // 发送一堆语音
                }
                else if (cardType.substring(0, 3).equals("Tmp")
                        || cardType.substring(0, 3).equals("Mtp"))
                {
                    if (Model.iAutoKZ == 1 && data.getSFJE() == 0)
                    {
//                        cmd.SendOpen(modulus);
//                        cmd.VoiceDisplay(VoiceType.TempOutOpen, laneIndex);
                    }
                    else
                    {
                        //发送语音
                    }
                }
            }

            if (openMode == OpenWayEnum.NoCutOff.getValue() && cardType.substring(0, 3).equals("Tmp"))
            {
                // 临时车不开闸
//                cmd.LoadLsNoX2010znykt(laneIndex, "ADD4");
                return false;
            }
            else
            {
                // 发送数据到控制板
//                SurplusCPH
            }
            // ShowImage(laneIndex, appearanceResult.Model.ImagePath);
            // 显示picture 进出口图片
        }
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                updateSetCarInByOut(data);
                if (data.getCardType().substring(0, 3).equals("Mth") || data.getCardType().substring(0, 3).equals("Fre"))
                {
                    fragmentChargeManager.setChargeInfoValidMoneyVisiable(View.INVISIBLE);
                    fragmentChargeManager.setChargeInfoReminderValueVisiable(View.INVISIBLE);
                }
                else
                {
                    fragmentChargeManager.setChargeInfoValidMoneyVisiable(View.VISIBLE);
                    fragmentChargeManager.setChargeInfoReminderValueVisiable(View.VISIBLE);

                    fragmentChargeManager.setChargeInfoReminderValue(String.valueOf(data.getBalance()));
                    fragmentChargeManager.setChargeInfoValidMoneyValue("剩余金额:");
                }
            }
        });
        return true;
    }


    /**
     * 3, 获取在 车场内车辆明细信息
     */
    private void requestGetCarIn()
    {
        GetCarInReq req = new GetCarInReq();
        req.setToken(Model.token);
        req.setOrderField(OrderField.getWhenGetCarIn("desc"));
        req.setJsonSearchParam(JsonSearchParam.getWhenGetCarOutAndIn(String.valueOf(Model.iParkingNo)));

        String resultUrl = GetServiceData.getInstance().getResultUrl(METHOD_GETCARIN, req);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetCarInResp.class, this, resultUrl));
    }

    private void requestGetCarIn(String cph, String inName, String inOpeator)
    {
        GetCarInReq req = new GetCarInReq();
        req.setToken(Model.token);
        req.setOrderField(OrderField.getWhenGetCarIn("desc"));

        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("CarparkNO", String.valueOf(Model.iParkingNo));
        map.put("CPH", cph);
        map.put("InGateName", inName);
        map.put("InOperator", inOpeator);

        req.setJsonSearchParam(JsonSearchParam.getWhenGetCarOutAndIn(map));
        getCarInResp = GetServiceData.getInstance().GetCarIn(req);
    }

    /**
     * 4，获取 车场内车辆收费明细信息
     */
    private void requestGetCarOut()
    {
        GetCarOutReq req = new GetCarOutReq();
        req.setToken(Model.token);
        req.setOrderField(OrderField.getWhenGetCarOut("desc"));
        req.setJsonSearchParam(JsonSearchParam.getWhenGetCarOutAndIn(String.valueOf(Model.iParkingNo)));

        String resultUrl = GetServiceData.getInstance().getResultUrl(METHOD_GETCAROUT, req);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetCarOutResp.class, this, resultUrl));
    }


    private void requestGetCarOut(String cph, String outName, String outOpeator)
    {
        GetCarOutReq req = new GetCarOutReq();
        req.setToken(Model.token);
        req.setOrderField(OrderField.getWhenGetCarOut("desc"));

        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("CarparkNO", String.valueOf(Model.iParkingNo));
        map.put("CPH", cph);
        map.put("OutGateName", outName);
        map.put("OutOperator", outOpeator);

        req.setJsonSearchParam(JsonSearchParam.getWhenGetCarOutAndIn(map));
        getCarOutResp = GetServiceData.getInstance().GetCarOut(req);
    }

    /**
     * 5, 获取车场的车位信息
     */
    private void requestGetParkingInfo()
    {
        GetParkingInfoReq req = new GetParkingInfoReq();
        req.setToken(Model.token);
        req.setStationId(Model.stationID);
        req.setStartTime(TimeConvertUtils.longToString("yyyyMMddHHmmss", System.currentTimeMillis()));
        String resultUrl = GetServiceData.getInstance().getResultUrl(METHOD_GETPARKINGINFO, req);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetParkingInfoResp.class, this, resultUrl));
    }

    /**
     * 6，获取模糊对比车牌信息
     *
     * @param cph
     * @return
     */
    private GetCardIssueResp requestGetCarIssueWhenCPH_Like(String cph)
    {
        GetCardIssueReq getCardIssueReq = new GetCardIssueReq(); // 请求发卡行信息
        getCardIssueReq.setToken(Model.token);
        getCardIssueReq.setJsonSearchParam(JsonSearchParam.getWhenGetCardIssue(cph.substring(1)));  // 过滤掉第一个省份简称
        getCardIssueReq.setOrderField(OrderField.getWhenGetCardIssue("asc"));
        return GetServiceData.getInstance().SelectFxCPH_Like(getCardIssueReq);
    }

    /**
     * 7, 设置车辆入场操作
     *
     * @param setCarInReq
     * @return
     */
    private void requestUrlUpdateUiWhenSetIn(SetCarInReq setCarInReq, int index, PlateColorEnum color)
    {
        setCarInReq.setCPColor(color.getColorValue());
        String resultUrl = GetServiceData.getResultUrl(METHOD_SETCARIN, setCarInReq);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(SetCarInResp.class, this, setCarInReq, index));
    }

    /**
     * 8，设置车辆确认入场操作
     *
     * @param setCarInConfirmReq
     * @param srcCPH
     * @param roadName
     * @return
     */
    @Nullable
    private SetCarInConfirmResp requestSetCarInConfirm(SetCarInConfirmReq setCarInConfirmReq, String srcCPH, String roadName)
    {
        setCarInConfirmReq.setStationId(Model.stationID);
        setCarInConfirmReq.setToken(Model.token);
        setCarInConfirmReq.setCPH(srcCPH);

        // 通过入口车道名，来获取相应的机号

        int ctrlIndexByInoutName = getCtrlIndexByInoutName(roadName);
        setCarInConfirmReq.setCtrlNumber(getCheDaoSetResp.getData().get(CheDaoIndex[ctrlIndexByInoutName]).getCtrlNumber());

        final SetCarInConfirmResp setCarInConfirmResp = GetServiceData.getInstance().SetCarInConfirmed(setCarInConfirmReq);
        if (setCarInConfirmResp == null)
            return null;
        L.i("确认车牌信息:" + setCarInConfirmReq);
        return setCarInConfirmResp;
    }

    /**
     * 9, 设置无牌车车辆进场设置
     *
     * @param carInWithoutCPHReq
     * @return
     */
    @Nullable
    private void requestUrlRequestWhenNoPlateIn(SetCarInWithoutCPHReq carInWithoutCPHReq)
    {
        String resultUrl = GetServiceData.getResultUrl(METHOD_SETCARINWITHOUTCPH, carInWithoutCPHReq);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(SetCarInWithoutCPHResp.class, this, resultUrl));
    }

    /**
     * 10, 车辆出场的模糊获取
     */
    private GetCarInResp requestSelectComeCPH_Like(String CPH)
    {
        GetCarInReq req = new GetCarInReq();
        req.setToken(Model.token);
        req.setJsonSearchParam(JsonSearchParam.getWhenSelectComeCPH_Like(CPH.substring(1), String.valueOf(Model.iParkingNo)));
        req.setOrderField(OrderField.getSelectComeCPH_Like("desc"));

        GetCarInResp getCarInResp = GetServiceData.getInstance().GetCarIn(req);
        if (getCarInResp == null || getCarInResp.getData() == null || getCarInResp.getData().size() == 0)
        {
            L.i("return null");
            return null;
        }
        else
        {
            L.i("getCarInResp.getData().size()" + getCarInResp.getData().size());
            return getCarInResp;
        }
    }

    /**
     * 11, 车辆出场
     *
     * @param CPH
     * @return
     */
    private SetCarOutResp requestSetCarOut(String CPH, int CtrlNumber, int StationId, int CPColor)
    {
        SetCarOutReq req = new SetCarOutReq();
        req.setToken(Model.token);
        req.setCtrlNumber(CtrlNumber);
        req.setCPH(CPH);
        req.setStationId(StationId);
        if (CPColor != -1)
        {
            req.setCPColor(CPColor);
        }

        SetCarOutResp setCarOutResp = GetServiceData.getInstance().SetCarOut(req);
        if (setCarOutResp == null)
        {
            return null;
        }
        else
        {
            return setCarOutResp;
        }
    }


    /**
     * 无入场记录的车辆出场
     */
    private SetCarOutWithoutEntryRecordResp requestSetCarOutWithoutEntryRecord(String cph, String ctrlNumber, String StationId, int cpColor)
    {
        SetCarOutWithoutEntryRecordReq req = new SetCarOutWithoutEntryRecordReq();
        req.setToken(Model.token);
        req.setCPH(cph);
        req.setCtrlNumber(Integer.parseInt(ctrlNumber));
        req.setStationId(Integer.parseInt(StationId));
        if (cpColor != -1)
        {
            req.setCPColor(cpColor);
        }

        SetCarOutWithoutEntryRecordResp resp = GetServiceData.getInstance().SetCarOutWithoutEntryRecord(req);
        if (resp == null)
        {
            return null;
        }
        else
        {
            return resp;
        }
    }

    public UpdateChargeAmountReq initUpdateChargeAmountReq()
    {
        UpdateChargeAmountReq req = new UpdateChargeAmountReq();
        return req;
    }


    /**
     * 更改收费金额
     */
    private UpdateChargeAmountResp requestUpdateChargeAmount(UpdateChargeAmountReq req)
    {
        UpdateChargeAmountResp resp = GetServiceData.getInstance().UpdateChargeAmount(req);
        if (resp == null)
        {
            return null;
        }
        else
        {
            return resp;
        }
    }

    /**
     * 更新收费信息接口
     */
    private UpdateChargeInfoResp UpdateChargeInfo()
    {
        UpdateChargeInfoReq req = new UpdateChargeInfoReq(); // 填充相应的字段数据
        UpdateChargeInfoResp resp = GetServiceData.getInstance().UpdateChargeInfo(req);
        if (resp == null)
        {
            return null;
        }
        else
        {
            return resp;
        }
    }

    /**
     * 取消收费接口
     */
    private CancelChargeResp requestCancelCharge()
    {
        CancelChargeReq chargeReq = new CancelChargeReq();
        CancelChargeResp cancelChargeResp = GetServiceData.getInstance().CancelCharge(chargeReq);
        if (cancelChargeResp == null)
        {
            return null;
        }
        else
        {
            return cancelChargeResp;
        }
    }


    private void requestCancelCharge(SetCarOutResp.DataBean data)
    {
        CancelChargeReq req = new CancelChargeReq();
        req.setToken(Model.token);
        req.setCardNO(data.getCardNO());
        req.setCardType(data.getCardType());
        req.setOutTime(data.getOutTime());
        CancelChargeResp resp = GetServiceData.getInstance().CancelCharge(req);
        if (resp == null) return;
        if (resp.getData() <= 0)
        {
            L.i("CancelCharge........" + resp.getData());
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    MessageBox.show(ParkingMonitoringActivity.this, "取消收费失败！" + "\r\n" + "btnOK_Click");
                }
            });
        }
    }

    private String filesJpg; //表示当前图片所有的路径

    private void ShowImage(int laneIndex, String networkPath)
    {
        L.i("filesJpg:" + filesJpg);
        if (Model.iEnableNetVideo == 1)
        {
            if (null != networkPath && networkPath.length() > 0)
            {
                filesJpg = Model.sImageSavePath + File.separator + networkPath;
            }
            parkingMonitoringView.saveImage(filesJpg, laneIndex);

            int inOut = getCheDaoSetResp.getData().get(CheDaoIndex[laneIndex]).getInOut(); // 注意数据的检测
            if (inOut == 0)
            {
                if (new File(filesJpg).exists())
                    parkingMonitoringView.setInPicture(laneIndex, BitmapFactory.decodeFile(filesJpg));
            }
            else if (inOut == 1)
            {
                if (new File(filesJpg).exists())
                    parkingMonitoringView.setOutPicture(laneIndex, BitmapFactory.decodeFile(filesJpg));
            }
        }
    }

    public class RefreshParkingMonitorView implements ParkingTempGob_bigViewSub.ICallBack
    {
        @Override
        public void updateAppearanceRecord(SetCarOutResp.DataBean appearanceModel, int laneIndex, String localImagePath)// 车辆开闸的时候触发
        {
            parkingMonitoringView.setCPHText(laneIndex, appearanceModel.getCPH()); // 设置视频显示下的车牌号
            updateSetCarInByOut(appearanceModel);
            parkingMonitoringView.setSurplusCarCount(String.valueOf(appearanceModel.getRemainingPlaceCount()));

            // 处理图片 --

            GetBinInOut();
            loadCar();
        }

        @Override
        public void tempGob_big_Photo(String count)//点击照片抓拍时触发
        {//最后构建File路径
//            string Filebmps = "", PathStr = "";
//            DateTime MyCapDateTime;
//
//            if (Model.sImageSavePath.Substring(Model.sImageSavePath.Length - 1) != @"\") // @在前表示不要转义;
//            {
//                Model.sImageSavePath = Model.sImageSavePath + @"\";
//            }
//            MyCapDateTime = DateTime.Now;
//            PathStr = Model.sImageSavePath + MyCapDateTime.ToString("yyyyMMdd");
//            if (System.IO.Directory.Exists(PathStr) == false)
//            {
//                System.IO.Directory.CreateDirectory(PathStr);//创建路径
//            }
//            Filebmps = PathStr + @"\" + monitor.CardNo + MyCapDateTime.ToString("yyyyMMddHHmmss") + "证件" + ".bmp";
//            File = PathStr + @"\" + monitor.CardNo + MyCapDateTime.ToString("yyyyMMddHHmmss") + "证件" + ".jpg";
        }

        @Override
        public void binData(String CardType, double SFJE)//在改变车辆类型时，触发回调
        {
            if (CR.IsChineseCharacters(CardType))
            {
                fragmentChargeManager.setChargeInfoCardType(CardType);
            }
            else
                fragmentChargeManager.setChargeInfoCardType(CR.GetCardType(CardType, 1));
            fragmentChargeManager.setChargeInfoPayMoney(String.valueOf(SFJE));
        }
    }

    /**
     * 更新详细信息
     */
    private void GetBinInOut()
    {
        // 更新进场出场数据
        final String searchCPHText = parkingMonitoringView.getSearchCPHText();
        final String inName = parkingMonitoringView.getInOutName(0);
        final String inOpeator = parkingMonitoringView.getInOutOperator(0);

        final String outName = parkingMonitoringView.getInOutName(1);
        final String outOpeator = parkingMonitoringView.getInOutOperator(1);

        new Thread(new Runnable()
        {
            @Override
            public void run() // 更新界面数据
            {
                requestGetCarOut(searchCPHText, outName, outOpeator);
                if (checkCarOutRespValid())
                {
                    mHandler.sendEmptyMessage(MSG_GetCarOut);
                }

                requestGetCarIn(searchCPHText, inName, inOpeator);
                if (checkCarInRespValid())
                {
                    mHandler.sendEmptyMessage(MSG_GetCarIn);
                }
            }
        }).start();
    }

    /**
     * 统计车位信息
     */
    private int outCarCount;
    public Summary summary0 = new Summary();

    private void loadCar()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                requestGetParkingInfo();
                if (checkCarParkingInfoInvalid())
                {
                    return;
                }

                GetParkingInfoResp.DataBean data = getParkingInfoResp.getData();

                summary0.setMthCount(data.getMonthCarCountInPark());
                summary0.setTmpCount(data.getTempCarCountInPark());
                summary0.setFreCount(data.getFreeCarCountInPark());
                summary0.setStrCount(data.getStrCarCountInPark());
                outCarCount = 0;
                if (Model.bPaiChe)
                {
                    summary0.setOutCount(outCarCount);
//                    UpdateUiText(lblOutCount, summary0.OutCount.ToString());lblOutCount没有显示 Collapsed没有显示
                }

                if (Model.iFreeCardNoInPlace == 1)
                {
                    summary0.setSurplusCarCount((Model.iParkTotalSpaces - data.getTotalCarCountInPark() + data.getFreeCarCountInPark()));
                    //免费车不计入车位数 （iModifyCarPos没用到）
                    //summary.SurplusCarCount = (Model.iParkTotalSpaces - pi.TotalCarCountInPark + pi.FreeCarCountInPark).ToString();
                }
                else
                {
                    summary0.setSurplusCarCount(Model.iParkTotalSpaces - data.getTotalCarCountInPark());
                    //summary.SurplusCarCount = (Model.iParkTotalSpaces - pi.TotalCarCountInPark).ToString();
                }

                if (Model.bTempCarPlace)
                {
                    summary0.setSurplusCarCount(Model.iTempCarPlaceNum - data.getTempCarCountInPark());
                    summary0.setOutCount(Model.iMonthCarPlaceNum - data.getMonthCarCountInPark());
                    //summary.SurplusCarCount = (Model.iTempCarPlaceNum - pi.TempCarCountInPark).ToString();
                    //lblOutCount.Content = (Model.iMonthCarPlaceNum - pi.MonthCarCountInPark).ToString();

                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            fragmentChargeManager.setCarSpaceOutCountHint("固定车位:");
                            fragmentChargeManager.setCarSpaceOutCountValue(String.valueOf(summary0.getOutCount()));
                            parkingMonitoringView.setSurplusCarCountHint("临时车位");
                        }
                    });
                    //grpSurplusCarCount.Header = "临时车位";
                    //lblOutCount.Visibility = Visibility.Visible;
                    //lblOut.Content = "固定车位:";
                }
                else if (Model.bMonthCarPlace)
                {
                    summary0.setSurplusCarCount(Model.iMonthCarPlaceNum - data.getMonthCarCountInPark());
                    summary0.setOutCount(Model.iMonthCarPlaceNum - data.getMonthCarCountInPark());

                    //summary.SurplusCarCount = (Model.iMonthCarPlaceNum - pi.MonthCarCountInPark).ToString();
                    //lblOutCount.Content = (Model.iTempCarPlaceNum - pi.TempCarCountInPark).ToString();
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            fragmentChargeManager.setCarSpaceOutCountHint("固定车位:");
                            fragmentChargeManager.setCarSpaceOutCountValue(String.valueOf(summary0.getOutCount()));
                            parkingMonitoringView.setSurplusCarCountHint("临时车位");
                        }
                    });
                    //grpSurplusCarCount.Header = "固定车位：";
                    //lblOutCount.Visibility = Visibility.Visible;
                    //lblOut.Content = "临时车位：";
                }
                else if (Model.bMoneyCarPlace)
                {
                    summary0.setSurplusCarCount(Model.iMoneyCarPlaceNum - data.getStrCarCountInPark());
                    summary0.setOutCount(Model.iMonthCarPlaceNum - data.getMonthCarCountInPark());

                    //summary.SurplusCarCount = (Model.iMoneyCarPlaceNum - pi.PrepaidCarCountInPark).ToString();
                    //lblOutCount.Content = (Model.iMonthCarPlaceNum - pi.MonthCarCountInPark).ToString();
                    mHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            fragmentChargeManager.setCarSpaceOutCountHint("固定车位:");
                            fragmentChargeManager.setCarSpaceOutCountValue(String.valueOf(summary0.getOutCount()));
                            parkingMonitoringView.setSurplusCarCountHint("储值车位");
                        }
                    });

                    //grpSurplusCarCount.Header = "储值车位：";
                    //lblOutCount.Visibility = Visibility.Visible;
                    //lblOut.Content = "固定车位：";
                }

            }
        }).start();
    }

    private void requestUploadCaptureImage()
    {
        UploadCaptureImageReq req = new UploadCaptureImageReq(); // 为图片下载提供支持
        req.setToken(Model.token);
        req.setStationId(String.valueOf(Model.stationID));
        req.setDate(TimeConvertUtils.longToString("yyyyMMdd", System.currentTimeMillis()));
        String resultUrl = GetServiceData.getResultUrl(METHOD_UPLOADCAPTUREIMAGE, req);

        // 直接上传文件
        Bitmap bitmap = BitmapFactory.decodeFile(SDCardUtils.getSDCardPath() + "picture.jpg");
        InputStream inputStream = ImageUitls.bitmapToInputStream(bitmap, false);

        RequestMap params = new RequestMap();
//                params.put("file", new File(SDCardUtils.getSDCardPath() + "picture.jpg"));
        params.put("bitmap", inputStream, "mypicture.jpg");
        RequestManager
                .getInstance()
                .post(resultUrl, params, new GsonCallback<>(UploadCaptureImageResp2.class, ParkingMonitoringActivity.this, resultUrl));

    }


    private void ImageProcessing(
            String localImagePath // 本地存放路径
            , String networkImagePath    // 服务器请求数据返回的路径
            , int laneIndex
            , boolean isUpload // 上传
            , boolean isDown // 下载
    ) //下载
    {

    }


    private void test()
    {

    }

    //线程的回调
    private class ThreadCallback implements Callback
    {

        @Override
        public void onError(Thread thread, Throwable t)
        {
            L.e(String.format("线程%s运行出现异常，异常信息为：%s", thread, t.getMessage()));
        }

        @Override
        public void onCompleted(Thread thread)
        {
            L.e(String.format("线程%s运行完毕", thread));
        }

        @Override
        public void onStart(Thread thread)
        {

        }
    }

}

