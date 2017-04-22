package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.mydistributedparkingos.R;
import com.example.administrator.myparkingos.constant.CR;
import com.example.administrator.myparkingos.constant.JsonSearchParam;
import com.example.administrator.myparkingos.constant.OrderField;
import com.example.administrator.myparkingos.constant.RCodeEnum;
import com.example.administrator.myparkingos.model.GetServiceData;
import com.example.administrator.myparkingos.model.beans.Model;
import com.example.administrator.myparkingos.model.beans.NoPlateCarItem;
import com.example.administrator.myparkingos.model.requestInfo.AddOptLog;
import com.example.administrator.myparkingos.model.requestInfo.AddOptLogReq;
import com.example.administrator.myparkingos.model.requestInfo.CaclChargeAmountReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCaptureImageReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCarInReq;
import com.example.administrator.myparkingos.model.requestInfo.GetCardTypeDefReq;
import com.example.administrator.myparkingos.model.requestInfo.GetFreeReasonReq;
import com.example.administrator.myparkingos.model.requestInfo.GetParkDiscountJHSetReq;
import com.example.administrator.myparkingos.model.requestInfo.GetXXXCommonReq;
import com.example.administrator.myparkingos.model.requestInfo.OnlinePayReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarFreeOutWithoutCPHReq;
import com.example.administrator.myparkingos.model.requestInfo.SetCarOutWithoutCPHReq;
import com.example.administrator.myparkingos.model.responseInfo.AddOptLogResp;
import com.example.administrator.myparkingos.model.responseInfo.CaclChargeAmountResp;
import com.example.administrator.myparkingos.model.responseInfo.GetBillPrintSetResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCarInResp;
import com.example.administrator.myparkingos.model.responseInfo.GetCardTypeDefResp;
import com.example.administrator.myparkingos.model.responseInfo.GetFreeReasonResp;
import com.example.administrator.myparkingos.model.responseInfo.GetParkDiscountJHSetResp;
import com.example.administrator.myparkingos.model.responseInfo.OnlinePayResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarFreeOutWithoutCPHResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutWithoutCPHResp;
import com.example.administrator.myparkingos.myUserControlLibrary.DateTimePicker;
import com.example.administrator.myparkingos.myUserControlLibrary.MessageBox;
import com.example.administrator.myparkingos.myUserControlLibrary.niceSpinner.NiceSpinner;
import com.example.administrator.myparkingos.util.L;
import com.example.administrator.myparkingos.util.T;
import com.example.administrator.myparkingos.util.TimeConvertUtils;
import com.example.administrator.myparkingos.volleyUtil.callback.GsonCallback;
import com.google.gson.Gson;
import com.jude.http.RequestManager;
import com.jude.volley.VolleyError;
import com.jude.volley.toolbox.ImageLoader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-10.
 */
public class ParkingOutNOPlateNoView implements View.OnClickListener, GsonCallback.Listener
{
    private final Activity mActivity;
    private Dialog dialog;

    private DateTimePicker dtpStart;
    private DateTimePicker dtpEnd;
    private NiceSpinner cmbColor;
    private NiceSpinner cmbCardType;
    private EditText txtCPH;
    private NiceSpinner cmbCarType;
    private NiceSpinner cmbJHDZ;
    private NiceSpinner cmbGateName;
    private NiceSpinner cmbFree;
    private TextView tvNeedMoney;
    private Button btnNoPlateOutSearch;
    private Button btnNoPlateOutCalc;
    private Button btnNoPlateOutOpen;
    private Button btnNoPlateOutFreeOpen;
    private Button btnNoPlateOutPrint;
    private Button btnNoPlateOutCancel;
    private RadioButton radWX;
    private RadioButton radZFB;
    private EditText txtAuth_code;
    private ListView lvNoPlate;
    private TextView tvMoney;
    private ImageView ivInPicture;
    private TextView tvInPicHint;
    private ImageView ivOutPicture;
    private TextView tvOutPicHint;

    private NoPlateOutListAdapter noPlateAdapter;
    private ArrayList<NoPlateCarItem> outList;
    private String[] arrayColor;
    private String[] arrayBrand;
    private Handler mHandler;
    private int mIndex;
    private ArrayList<String> gateNameList = new ArrayList<>();
    private String mStrPic;
    private LinearLayout llOnlinePayer;
    private List<GetParkDiscountJHSetResp.DataBean> parkDiscountJHSet;
    private List<GetCardTypeDefResp.DataBean> getCardTypeDefRespData;
    private List<GetFreeReasonResp.DataBean> freeReasonData;
    private List<GetCarInResp.DataBean> getCarInRespData;
    private boolean bLoadMouse;// ?

    // 共有11请求
    private static final String METHOD_GETCARDTYPEDEF = "GetCardTypeDef";
    private static final String METHOD_GETPARKDISCOUNTJHSET = "GetParkDiscountJHSet";
    private static final String METHOD_GETFREEREASON = "GetFreeReason";
    private static final String METHOD_GETCARIN = "GetCarIn";
    private static final String METHOD_CACLCHARGEAMOUNT = "CaclChargeAmount";
    private static final String METHOD_SETCAROUTWITHOUTCPH = "SetCarOutWithoutCPH";
    private static final String METHOD_SETCARFREEOUTWITHOUTCPH = "SetCarFreeOutWithoutCPH";
    private static final String METHOD_ONLINEPAY = "OnlinePay";
    private static final String METHOD_GETCAPTUREIMAGE = "GetCaptureImage";
    private static final String METHOD_ADDOPTLOG = "AddOptLog";
    private static final String METHOD_GETBILLPRINTSET = "GetBillPrintSet";

    private Button btnElectronicPay;

    public ParkingOutNOPlateNoView(Activity activity, Handler handler, String _strPic, int index)
    {
        mActivity = activity;
        mHandler = handler;
        mIndex = index;
        mStrPic = _strPic;

        dialog = new Dialog(activity); // @android:style/Theme.Dialog
        dialog.setContentView(R.layout.parkingout_noplate);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 1 / 1.5); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 1 / 1.5); // 宽度设置为屏幕的0.65
        window.setAttributes(p);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.parkdowncard_background);
        dialog.setTitle(activity.getResources().getString(R.string.parkMontior_unlicensedVehicleAppearance));

        initView();
    }

    private void initView()
    {
        dtpStart = (DateTimePicker) dialog.findViewById(R.id.dtpStart);
        dtpEnd = (DateTimePicker) dialog.findViewById(R.id.dtpEnd);

        txtCPH = (EditText) dialog.findViewById(R.id.txtCPH);
        txtCPH.setOnClickListener(this);

        tvNeedMoney = (TextView) dialog.findViewById(R.id.tvNeedMoney);
        btnNoPlateOutSearch = (Button) dialog.findViewById(R.id.btnNoPlateOutSearch);
        btnNoPlateOutSearch.setOnClickListener(this);
        btnNoPlateOutCalc = (Button) dialog.findViewById(R.id.btnNoPlateOutCalc);
        btnNoPlateOutCalc.setOnClickListener(this);
        btnNoPlateOutOpen = (Button) dialog.findViewById(R.id.btnNoPlateOutOpen);
        btnNoPlateOutOpen.setOnClickListener(this);
        btnNoPlateOutFreeOpen = (Button) dialog.findViewById(R.id.btnNoPlateOutFreeOpen);
        btnNoPlateOutFreeOpen.setOnClickListener(this);
        btnNoPlateOutPrint = (Button) dialog.findViewById(R.id.btnNoPlateOutPrint);
        btnNoPlateOutPrint.setOnClickListener(this);
        btnNoPlateOutCancel = (Button) dialog.findViewById(R.id.btnNoPlateOutCancel);
        btnNoPlateOutCancel.setOnClickListener(this);
        radWX = (RadioButton) dialog.findViewById(R.id.radWX);
        radWX.setOnClickListener(this);

        radZFB = (RadioButton) dialog.findViewById(R.id.radZFB);
        radZFB.setOnClickListener(this);
        txtAuth_code = (EditText) dialog.findViewById(R.id.txtAuth_code);
        txtAuth_code.setOnClickListener(this);
        lvNoPlate = (ListView) dialog.findViewById(R.id.lvNoPlate);
        tvMoney = (TextView) dialog.findViewById(R.id.tvMoney);
        ivInPicture = (ImageView) dialog.findViewById(R.id.ivInPicture);
        tvInPicHint = (TextView) dialog.findViewById(R.id.tvInPicHint);
        tvOutPicHint = (TextView) dialog.findViewById(R.id.tvOutPicHint);
        ivOutPicture = (ImageView) dialog.findViewById(R.id.ivOutPicture);
        llOnlinePayer = (LinearLayout) dialog.findViewById(R.id.llOnlinePayer);
        btnElectronicPay = (Button) dialog.findViewById(R.id.btnElectronicPay);
        btnElectronicPay.setOnClickListener(this);

        // 车牌颜色
        cmbColor = (NiceSpinner) dialog.findViewById(R.id.cmbColor);
        cmbColor.setSpinnerListener(new NiceSpinner.SpinnerListener()
        {
            @Override
            public void OnSpinnerItemClick(int pos)
            {
            }
        });

        // 临时车类型
        cmbCardType = (NiceSpinner) dialog.findViewById(R.id.cmbCardType);
        cmbCardType.setSpinnerListener(new NiceSpinner.SpinnerListener()
        {
            @Override
            public void OnSpinnerItemClick(int pos)
            {
                cmbCardType_SelectionChanged();
            }
        });

        // 免费类型
        cmbFree = (NiceSpinner) dialog.findViewById(R.id.cmbFree);
        cmbFree.setSpinnerListener(new NiceSpinner.SpinnerListener()
        {
            @Override
            public void OnSpinnerItemClick(int pos)
            {
            }
        });

        // 入场车道名
        cmbGateName = (NiceSpinner) dialog.findViewById(R.id.cmbGateName);
        cmbGateName.setSpinnerListener(new NiceSpinner.SpinnerListener()
        {
            @Override
            public void OnSpinnerItemClick(int pos)
            {
            }
        });

        //打折
        cmbJHDZ = (NiceSpinner) dialog.findViewById(R.id.cmbJHDZ);
        cmbJHDZ.setSpinnerListener(new NiceSpinner.SpinnerListener()
        {
            @Override
            public void OnSpinnerItemClick(int pos)
            {
                cmbJHDZ_SelectionChanged();
            }
        });

        //车辆品牌
        cmbCarType = (NiceSpinner) dialog.findViewById(R.id.cmbCarType);
        cmbCarType.setSpinnerListener(new NiceSpinner.SpinnerListener()
        {
            @Override
            public void OnSpinnerItemClick(int pos)
            {
            }
        });

        initListView();
    }

    private void cmbCardType_SelectionChanged()
    {
        if (getCardTypeDefRespData == null || TextUtils.isEmpty(cmbCardType.getCurrentText()))
        {
            L.e("没有车辆类型");
            return;
        }

        String resultUrl = GetServiceData.getResultUrl(METHOD_CACLCHARGEAMOUNT, initCaclChargeAmount());
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(CaclChargeAmountResp.class, new GsonCallback.Listener()
                {
                    @Override
                    public void success(String url, Object obj)
                    {
                        CaclChargeAmountResp caclChargeAmountResp = (CaclChargeAmountResp) obj;
                        updateMoney(caclChargeAmountResp);

                        if (Model.iModiTempType_VoiceSF == 1) //加了条件的判断
                        {
//                            TimeSpan tsTotal = cacl.OutTime - cacl.InTime;
//                            string stayTime = tsTotal.Days.ToString("X4") + tsTotal.Hours.ToString("X2") + tsTotal.Minutes.ToString("00");
//                            cmd.VoiceDisplay(VoiceType.OutGateVoice, imodulus, cacl.CardType ?? cmbCardType.SelectedValue.ToString(), cacl.CPH ?? selectItem.CPH, 0, "", cacl.RemainingPlaceCount, cacl.YSJE, cacl.SFJE, stayTime);
                        }
                        setViewStatusOnCaclCharge();
                        btnNoPlateOutOpen.setEnabled(false);
                        btnNoPlateOutPrint.setEnabled(false); //判断的地方有点不一样
                    }

                    @Override
                    public void error(String url, String string)
                    {

                    }
                }, resultUrl));
    }

    /**
     * 打折发生变化时
     */
    private void cmbJHDZ_SelectionChanged()
    {
        long discountID = -1;
        if (parkDiscountJHSet != null && parkDiscountJHSet.size() > 0)
        {
            discountID = parkDiscountJHSet.get(cmbJHDZ.getCurrentIndex()).getID();
        }

        if (discountID == -1) return;//没有打折,直接返回

        String resultUrl = GetServiceData.getResultUrl(METHOD_CACLCHARGEAMOUNT, initCaclChargeAmount());
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(CaclChargeAmountResp.class, new GsonCallback.Listener()
                {
                    @Override
                    public void success(String url, Object obj)
                    {
                        updateMoney((CaclChargeAmountResp) obj);
                    }

                    @Override
                    public void error(String url, String string)
                    {

                    }
                }
                        , resultUrl));
    }

    private void initNiceSpinnerView()
    {
        cmbColor.refreshData(Arrays.asList(arrayColor), 0, true);// 初始化车辆颜色
        cmbCarType.refreshData(Arrays.asList(arrayBrand), 0, true);// 初始化车辆品牌

        requestGetTempCardTypeDef();
        requestGetParkDiscountJHSet();
        requestGetFreeReason();

        gateNameList.clear();
        gateNameList.add(Model.Channels[mIndex].sInOutName);
        cmbGateName.refreshData(gateNameList, 0);

        if (mStrPic != null)
        {
            tvOutPicHint.setVisibility(View.INVISIBLE);
            ivOutPicture.setImageBitmap(BitmapFactory.decodeFile(mStrPic));
        }

        //设置view界面的状态
        setViewStatusOnStart();
    }

    private void requestGetFreeReason()
    {
        String resultUrl = GetServiceData.getResultUrl(METHOD_GETFREEREASON, initFreeReason());
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetFreeReasonResp.class, this, resultUrl));
    }

    @NonNull
    private GetFreeReasonReq initFreeReason()
    {
        GetFreeReasonReq reasonReq = new GetFreeReasonReq();
        reasonReq.setToken(Model.token);
        reasonReq.setOrderField(OrderField.getWhenGetReasonNOPlate("asc"));
        return reasonReq;
    }

    private void requestGetParkDiscountJHSet()
    {
        String resultUrl = GetServiceData.getResultUrl(METHOD_GETPARKDISCOUNTJHSET, initGetParkDiscountJHSet());
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetParkDiscountJHSetResp.class, this, resultUrl));
    }

    private void requestGetTempCardTypeDef()
    {
        String resultUrl = GetServiceData.getResultUrl(METHOD_GETCARDTYPEDEF, initGetTempCardTypeDef());
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetCardTypeDefResp.class, this, resultUrl));
    }

    @NonNull
    private GetParkDiscountJHSetReq initGetParkDiscountJHSet()
    {
        GetParkDiscountJHSetReq req = new GetParkDiscountJHSetReq();
        req.setToken(Model.token);
        return req;
    }

    private void setViewStatusOnStart()
    {
        btnNoPlateOutFreeOpen.setEnabled(false);
        cmbFree.setEnabled(false);
        btnNoPlateOutCalc.setEnabled(false);

        //是否可更改卡类
        if (Model.iSetTempCardType == 0)
        {
            cmbCardType.setEnabled(false);
        }

        if (Model.iOnlinePayEnabled == 1)
        {
            llOnlinePayer.setEnabled(true);
        }
        else
        {
            llOnlinePayer.setEnabled(false);
        }
    }

    @NonNull
    private GetCardTypeDefReq initGetTempCardTypeDef()
    {
        GetCardTypeDefReq req = new GetCardTypeDefReq();
        req.setToken(Model.token);
        req.setJsonSearchParam(JsonSearchParam.getTempWhenGetCarTypeDef());
        return req;
    }

    /**
     * 显示listView的列表信息,注意列表可以显示的左右滑动的数据显示，这里暂时使用ListView来进行显示
     */
    private int selectIndex = -1;

    private NoPlateCarItem initListHead()
    {
        NoPlateCarItem noPlateCarItem = new NoPlateCarItem();
//        CardNO
//        InUser
//        SFGate
//        CPH
//        ChineseName
//        InTime
//        InGateName
//        UserNO
//        UserName
//        Balance
//        CarparkNO
//        BigSmall
//        FreeReason
//        InPic
//        DeptName
//        ZJPic
//        InOperatorCard
//        InOperator

        noPlateCarItem.setCardNO(mActivity.getResources().getString(R.string.parkMontior_carId));
        noPlateCarItem.setInUser(mActivity.getResources().getString(R.string.noPlateOut_Color));
        noPlateCarItem.setSFGate(mActivity.getResources().getString(R.string.noPlateOut_Brand));
        noPlateCarItem.setCPH(mActivity.getResources().getString(R.string.blackList_carNo));
        noPlateCarItem.setChineseName(mActivity.getResources().getString(R.string.noPlate_carType));
        noPlateCarItem.setInTime(mActivity.getResources().getString(R.string.parkMontior_enterTime));
        noPlateCarItem.setInGateName(mActivity.getResources().getString(R.string.parkMontior_enterName));
        noPlateCarItem.setUserNO(mActivity.getResources().getString(R.string.dealLineQuery_personNo));
        return noPlateCarItem;
    }

    private void initListView()
    {
        outList = new ArrayList<>();
        outList.add(initListHead());
        noPlateAdapter = new NoPlateOutListAdapter(outList);
        lvNoPlate.setAdapter(noPlateAdapter);
        lvNoPlate.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                L.i("onItemClick" + ", postion:" + position); // 记录当前选择进行删除
                if (position == 0)
                {
                    selectIndex = -1; //设置无效
                    return;
                }

                selectIndex = position - 1;
                dgvCar_SelectionChanged(); //发生修改时，需要对于界面进行更新
            }
        });

        arrayColor = mActivity.getResources().getStringArray(R.array.noPlateColor);
        arrayBrand = mActivity.getResources().getStringArray(R.array.noPlateBrand);
    }


    public void setViewStatusInCarSelect()
    {
        btnNoPlateOutCalc.setEnabled(true);
        btnNoPlateOutFreeOpen.setEnabled(false);
        btnNoPlateOutOpen.setEnabled(false);
        btnNoPlateOutPrint.setEnabled(false);
    }

    private void dgvCar_SelectionChanged()
    {
        if (checkGridViewSelectInvalid()) return;

        String inPic = getCarInRespData.get(selectIndex).getInPic();
        String strInPic = inPic;
        String dtSartHF = getCarInRespData.get(selectIndex).getInTime();
//        lblInDateTime.Content = dr.InTime; // inTime这里隐藏了
        String inGateName = getCarInRespData.get(selectIndex).getInGateName();
        String strCardType = CR.GetCardType(getCarInRespData.get(selectIndex).getCardType(), 0);
        loadPicOnView(inPic); // 加载图片到控件上 picCarIn
        setViewStatusInCarSelect();
    }

    private void setOutPic(int visiable)
    {
        if (visiable == View.VISIBLE)
        {
            tvOutPicHint.setVisibility(View.INVISIBLE);
            ivOutPicture.setVisibility(View.VISIBLE);
        }
        else
        {
            tvOutPicHint.setVisibility(View.VISIBLE);
            ivOutPicture.setVisibility(View.INVISIBLE);
        }
    }

    private void setInPic(int visiable)
    {
        if (visiable == View.VISIBLE)
        {
            tvInPicHint.setVisibility(View.INVISIBLE);
            ivInPicture.setVisibility(View.VISIBLE);
        }
        else
        {
            tvInPicHint.setVisibility(View.VISIBLE);
            ivInPicture.setVisibility(View.INVISIBLE);
        }
    }

    private void loadPicOnView(String inPic)
    {
        if (TextUtils.isEmpty(inPic))
        {
            return;
        }

        L.e("Model.sImageSavePath:" + Model.sImageSavePath); // 注意路径

        //直接下载图片到指定的控件上需要先上传到文件中
        GetCaptureImageReq getCaptureImageReq = new GetCaptureImageReq();
        getCaptureImageReq.setToken(Model.token);
        getCaptureImageReq.setFileName(inPic);

        String resultUrl = GetServiceData.getInstance().getResultUrl(METHOD_GETCAPTUREIMAGE, getCaptureImageReq);
        RequestManager
                .getInstance()
                .img(resultUrl, new ImageLoader.ImageListener()
                {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate)
                    {
                        Bitmap bitmap = response.getBitmap();
                        setInPic(View.VISIBLE);
                        if (bitmap != null)
                        {
                            ivInPicture.setImageBitmap(bitmap);//显示图片
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        error.printStackTrace();
                    }
                });
    }

    private void cleanGridData()
    {
        outList.clear();
        noPlateAdapter.notifyDataSetChanged();
    }

    public void setGridData(List<NoPlateCarItem> inList)
    {
        if (inList == null)
        {
            return;
        }

        outList.clear();
        outList.add(initListHead());//加上头部
        outList.addAll(inList);
        noPlateAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnNoPlateOutSearch:
                btnSelect_Click();
                break;
            case R.id.btnNoPlateOutCalc:
                btnSF_Click();
                break;
            case R.id.btnNoPlateOutOpen:
                btnOpen_Click();
                break;
            case R.id.btnNoPlateOutFreeOpen:
                btnFree_Click();
                break;
            case R.id.btnNoPlateOutPrint:
                btnPrint_Click();
                break;
            case R.id.btnNoPlateOutCancel:
                dismiss();
                break;
            case R.id.btnElectronicPay:
                txtAuth_code_KeyDown();
                break;
        }
    }

    /**
     * 打印小票
     */
    private void btnPrint_Click()
    {
        if (checkGridViewSelectInvalid()) return;

        GetXXXCommonReq getXXXCommonReq = new GetXXXCommonReq();
        getXXXCommonReq.setToken(Model.token);
        String resultUrl = GetServiceData.getResultUrl(METHOD_GETBILLPRINTSET, getXXXCommonReq);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetBillPrintSetResp.class, this, resultUrl));
    }

    //免费放行
    private void btnFree_Click()
    {
        if (freeReasonData == null || freeReasonData.size() == 0 || TextUtils.isEmpty(cmbFree.getCurrentText()))
        {
            MessageBox.show(mActivity, "请选择免费原因！或者没有免费原因");
            return;
        }

        MessageBox.showCanSetCallback(mActivity, "请确认是否免费？\t\r若点击【是】，该车辆将被免费！", new MessageBox.IMessageBoxListener()
        {
            @Override
            public void onClickPositive()
            {
                if (checkGridViewSelectInvalid()) return;

                SetCarFreeOutWithoutCPHReq freeOutWithoutCPHReq = new SetCarFreeOutWithoutCPHReq();
                freeOutWithoutCPHReq.setToken(Model.token);
                freeOutWithoutCPHReq.setCtrlNumber(Model.Channels[mIndex].iCtrlID);
                freeOutWithoutCPHReq.setStationId(Model.stationID);
                freeOutWithoutCPHReq.setCardNO(getCarInRespData.get(selectIndex).getCardNO());
                freeOutWithoutCPHReq.setCardType(CR.GetCardType(cmbCardType.getCurrentText(), 0));
                freeOutWithoutCPHReq.setInTime(TimeConvertUtils.longToString("yyyyMMddHHmmss", TimeConvertUtils.stringToLong(getCarInRespData.get(selectIndex).getInTime())));
                freeOutWithoutCPHReq.setFreeReason(cmbFree.getCurrentText());

                String resultUrl = GetServiceData.getResultUrl(METHOD_SETCARFREEOUTWITHOUTCPH, freeOutWithoutCPHReq);
                RequestManager
                        .getInstance()
                        .get(resultUrl, new GsonCallback<>(SetCarFreeOutWithoutCPHResp.class, ParkingOutNOPlateNoView.this, resultUrl));
            }

            @Override
            public void onClickNegative()
            {

            }
        });
    }

    private SetCarOutWithoutCPHReq initSetCarOutWithoutCPH()
    {
        if (checkGridViewSelectInvalid()) return null;
        if (!bLoadMouse)
        {
            L.e("!bLoadMouse: " + bLoadMouse);
            return null;
        }

        SetCarOutWithoutCPHReq withoutCPHReq = new SetCarOutWithoutCPHReq();
        withoutCPHReq.setToken(Model.token);
//        String cph = "";
//        try
//        {
//            cph = URLEncoder.encode(getCarInRespData.get(selectIndex).getCPH(), "UTF-8");
//        }
//        catch (UnsupportedEncodingException e)
//        {
//            e.printStackTrace();
//        }
        withoutCPHReq.setCPH(getCarInRespData.get(selectIndex).getCPH());// 在C#代码中有cph,但是在文档中没有cph;

        withoutCPHReq.setCtrlNumber(Model.Channels[mIndex].iCtrlID);
        withoutCPHReq.setStationId(Model.stationID);
        withoutCPHReq.setCardNO(getCarInRespData.get(selectIndex).getCardNO());
        withoutCPHReq.setCardType(getCarInRespData.get(selectIndex).getCardType());
        withoutCPHReq.setInTime(TimeConvertUtils.longToString("yyyyMMddHHmmss", TimeConvertUtils.stringToLong(getCarInRespData.get(selectIndex).getInTime())));
        withoutCPHReq.setNewCardType(CR.GetCardType(cmbCardType.getCurrentText(), 0));
        withoutCPHReq.setSFJE(Float.parseFloat(tvNeedMoney.getText().toString()));
        withoutCPHReq.setYSJE(Float.parseFloat(tvMoney.getText().toString()));
        withoutCPHReq.setPayType(iPayType);
        String payCode = txtAuth_code.getText().toString();
        withoutCPHReq.setPayCode(payCode == null ? "" : payCode);// 这个字段一点要有，不然服务器会返回404;

        L.i("SetCarOutWithoutCPHReq:" + withoutCPHReq.toString());
        return withoutCPHReq;
    }

    /**
     * 开闸放行
     */
    private int iPayType = 0; //支付方式

    private void btnOpen_Click()
    {
        SetCarOutWithoutCPHReq carOutWithoutCPHReq = initSetCarOutWithoutCPH(); // 这里出现问题?
        if (carOutWithoutCPHReq == null) return;
        String resultUrl = GetServiceData.getResultUrl(METHOD_SETCAROUTWITHOUTCPH, carOutWithoutCPHReq);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(SetCarOutWithoutCPHResp.class, this, resultUrl));
    }


    private CaclChargeAmountReq initCaclChargeAmount()
    {
        if (checkGridViewSelectInvalid()) return null;

        long discountID = -1;
        if (parkDiscountJHSet != null && parkDiscountJHSet.size() > 0)
        {
            discountID = parkDiscountJHSet.get(cmbJHDZ.getCurrentIndex()).getID();
        }

        String getCardType = CR.GetCardType(cmbCardType.getCurrentText(), 0); // 车辆类型变化
        CaclChargeAmountReq amountReq = new CaclChargeAmountReq();
        amountReq.setToken(Model.token);
        amountReq.setCardNO(getCarInRespData.get(selectIndex).getCardNO());
        amountReq.setStationId(Model.stationID);
        amountReq.setCardType(getCardType.equals("无效卡") ? null : getCardType);
        amountReq.setDiscountSetId(discountID == -1 ? null : discountID);
        amountReq.setInTime(TimeConvertUtils.longToString("yyyyMMddHHmmss", TimeConvertUtils.stringToLong("yyyy/MM/dd", dtpStart.getDateTime())));
        amountReq.setCPH(getCarInRespData.get(selectIndex).getCPH());
        L.i("amountReq:" + amountReq);
        return amountReq;
    }

    private boolean checkGridViewSelectInvalid()
    {
        if (outList.size() == 0 || selectIndex < 0 || getCarInRespData == null || getCarInRespData.size() < 0)
        {
            T.showShort(mActivity, "没有选中的条目或条码无效 selectIndex:" + selectIndex);
            return true;
        }
        return false;
    }

    /**
     * 计算收费
     */
    private void btnSF_Click()
    {
        requestCaclChargeAmount();
    }

    private void requestCaclChargeAmount()
    {
        CaclChargeAmountReq chargeAmountReq = initCaclChargeAmount();
        if (chargeAmountReq == null) return;
        String resultUrl = GetServiceData.getResultUrl(METHOD_CACLCHARGEAMOUNT, chargeAmountReq);
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(CaclChargeAmountResp.class, this, resultUrl));
    }

    private void setViewStatusOnCaclCharge()
    {
        bLoadMouse = true;
        btnNoPlateOutOpen.setEnabled(true);
        btnNoPlateOutPrint.setEnabled(true);

        if (Model.iTempFree == 1)
        {
            btnNoPlateOutFreeOpen.setEnabled(true);
            cmbFree.setCanEnable(true);
        }
    }

    private void setViewStatusOnSelect()
    {
        btnNoPlateOutOpen.setEnabled(false);
        btnNoPlateOutFreeOpen.setEnabled(false);
        btnNoPlateOutPrint.setEnabled(false);
    }

    private GetCarInReq initGetCarInSelectInNoCPH()
    {
        Map<String, String> map = new LinkedHashMap<>();

        if (!TextUtils.isEmpty(cmbColor.getCurrentText()))
        {
            map.put("InUser", cmbColor.getCurrentText().trim()); // 颜色(这里是中文?)
        }

        if (!TextUtils.isEmpty(cmbCarType.getCurrentText()))//?
        {
            map.put("SFGate", cmbCarType.getCurrentText().trim()); //可能涉及到编码中文???
        }

        if (!TextUtils.isEmpty(txtCPH.getText().toString())) // 车牌第一个省份为中文
        {
            map.put("CPH", txtCPH.getText().toString().trim());
        }

        String startTime = TimeConvertUtils.longToString("yyyy-MM-dd 00:00:00", TimeConvertUtils.stringToLong("yyyy/MM/dd", dtpStart.getDateTime()));
        String endTime = TimeConvertUtils.longToString("yyyy-MM-dd 23:59:59", TimeConvertUtils.stringToLong("yyyy/MM/dd", dtpEnd.getDateTime()));

        GetCarInReq getCarInReq = new GetCarInReq();
        getCarInReq.setToken(Model.token);
        getCarInReq.setOrderField(OrderField.getSelectComeCPH_Like("desc"));
        getCarInReq.setJsonSearchParam(JsonSearchParam.getWhenGetInNoCPH(map, startTime, endTime, "InTime"));
        return getCarInReq;
    }

    // 点击查询
    private void btnSelect_Click()
    {
        String resultUrl = GetServiceData.getResultUrl(METHOD_GETCARIN, initGetCarInSelectInNoCPH());
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(GetCarInResp.class, this, resultUrl));

        //查询的时候，需要清空数据
        cleanGridData();
    }


    public void show()
    {
        if (dialog != null)
        {
            initNiceSpinnerView(); //重新更新界面
            dialog.show();
        }
    }


    public void cleanWhenDimiss()
    {
        radWX.setChecked(false);
        radZFB.setChecked(false);
        outList.clear();
        noPlateAdapter.notifyDataSetChanged();
        txtAuth_code.setText("");

        iPayType = 0;
        txtCPH.setText("");
        if (Model.iXsd == 0)
        {
            tvMoney.setText("0");
            tvNeedMoney.setText("0");
        }
        else
        {
            tvMoney.setText("0.0");
            tvNeedMoney.setText("0.0");
        }

        btnNoPlateOutCalc.setEnabled(false);
        btnNoPlateOutOpen.setEnabled(false);
        btnNoPlateOutFreeOpen.setEnabled(false);
        btnNoPlateOutPrint.setEnabled(false);

        setInPic(View.INVISIBLE);
        setOutPic(View.INVISIBLE);
    }

    public void dismiss()
    {
        if (dialog != null && dialog.isShowing())
        {
            cleanWhenDimiss();
            dialog.dismiss();
        }
    }

    /**
     * 更新cardType数据
     *
     * @param obj
     */
    private void updateCardType(Object obj)
    {
        GetCardTypeDefResp resp = (GetCardTypeDefResp) obj;
        if (resp.getData() == null) return;
        getCardTypeDefRespData = resp.getData();
        final LinkedList<String> cardTypelinkedList = new LinkedList<>();
        for (GetCardTypeDefResp.DataBean temp : getCardTypeDefRespData)
        {
            cardTypelinkedList.add(temp.getCardType());
        }
        cmbCardType.refreshData(cardTypelinkedList, 0);
    }

    private void updateParkDiscountJHSet(Object obj)
    {
        GetParkDiscountJHSetResp resp = (GetParkDiscountJHSetResp) obj;
        if (resp.getData() == null) return;
        parkDiscountJHSet = resp.getData();
        LinkedList<String> discountSpaceLinkedList = new LinkedList<>();
        for (GetParkDiscountJHSetResp.DataBean temp : parkDiscountJHSet)
        {
            discountSpaceLinkedList.add(temp.getAddress());
        }

        cmbJHDZ.refreshData(discountSpaceLinkedList, 0, true);
    }

    private void updateFreeReason(Object obj)
    {
        GetFreeReasonResp resp = (GetFreeReasonResp) obj;
        if (resp.getData() == null) return;
        freeReasonData = resp.getData();
        LinkedList<String> freeReasonList = new LinkedList<>();
        for (GetFreeReasonResp.DataBean temp : freeReasonData)
        {
            freeReasonList.add(temp.getItemDetail());
        }
        cmbFree.refreshData(freeReasonList, 0, true);
    }

    private void updateChargeAmountView(Object obj)
    {
        CaclChargeAmountResp caclChargeAmountResp = (CaclChargeAmountResp) obj;
        if (caclChargeAmountResp == null || caclChargeAmountResp.getData() == null) return;

        bLoadMouse = true;
        updateMoney(caclChargeAmountResp);
        setViewStatusOnCaclCharge();
        //发送语音
//                TimeSpan tsTotal = cacl.OutTime - cacl.InTime;
//                string stayTime = tsTotal.Days.ToString("X4") + tsTotal.Hours.ToString("X2") + tsTotal.Minutes.ToString("00");
//
//                cmd.VoiceDisplay(VoiceType.OutGateVoice, imodulus, cmbCardType.SelectedValue.ToString(), selectItem.CPH, 0, "", cacl.RemainingPlaceCount, cacl.YSJE, cacl.SFJE, stayTime);
    }

    private void updateWhenSetCarOutWithoutCPH(Object obj)
    {
        SetCarOutWithoutCPHResp resp = (SetCarOutWithoutCPHResp) obj;
        if (resp == null) return;
        RCodeEnum rCodeEnum = RCodeEnum.valueOf(Integer.parseInt(resp.getRcode()));
        switch (rCodeEnum)
        {
            case OK:
                break;
            default:
                MessageBox.show(mActivity, resp.getMsg());
                return;
        }

        //开闸
//        string strRst = cmd.SendOpen(imodulus);
//        if (strRst == "0")   //开闸成功
//        {
//            cmd.VoiceDisplay(VoiceType.TempOutOpen, imodulus);
//            InNoCPHHandler(imodulus, strPic, appearanceResult.Model.ImagePath);
//        }
//        this.Close();
        dismiss();
    }

    private void updateWhenSetCarOutFreeWithoutCPH(Object obj)
    {
        SetCarFreeOutWithoutCPHResp withoutCPHResp = (SetCarFreeOutWithoutCPHResp) obj;
        if (withoutCPHResp == null) return;

        RCodeEnum rCodeEnum = RCodeEnum.valueOf(Integer.parseInt(withoutCPHResp.getRcode()));
        switch (rCodeEnum)
        {
            case OK:
                break;
            default:
                MessageBox.show(mActivity, withoutCPHResp.getMsg());
                return;
        }
//            string strRst = cmd.SendOpen(imodulus);
//            if (strRst == "0")   //开闸成功
//            {
//                cmd.VoiceDisplay(VoiceType.TempOutOpen, imodulus);
//                InNoCPHHandler(imodulus, strPic, appearanceResult.Model.ImagePath);
//                this.Close();
//            }

        bLoadMouse = false;
        dismiss();
    }

    private void updateWhenOnlinePay(Object obj)
    {
        OnlinePayResp onlinePayResp = (OnlinePayResp) obj;

        if (onlinePayResp != null)
        {
            if (onlinePayResp.getRcode().equals("200"))
            {
                btnOpen_Click(); //直接开闸放行即可
            }
            else
            {
                String resultUrl = GetServiceData.getResultUrl(METHOD_ADDOPTLOG, initAddOptLog("在线支付失败：", onlinePayResp.getMsg()));
                RequestManager
                        .getInstance()
                        .get(resultUrl, new GsonCallback<>(AddOptLogResp.class, this, resultUrl));
//                    System.Diagnostics.Trace.WriteLine("在线支付失败：" + result.msg); // 提示信息

                MessageBox.show(mActivity, "支付失败！！");
                txtAuth_code.setText("");
                iPayType = 0;
            }
        }
    }

    private void updateWhenPrintBill(Object obj)
    {
        L.i("updateWhenPrintBill...");
        // 按照一定的方式来打印小票的信息即可;
//            try
//            {
//                if (dgvCar.Items.Count > 0 && dgvCar.SelectedIndex > -1)
//                {
//                    var vr = dgvCar.SelectedItem as CarIn;
//
//                    List<BillPrintSet> lstBPS = gsd.GetPrint();
//                    FlowDocument doc = new FlowDocument();
//                    Paragraph ph = new Paragraph();
//
//                    ph.Inlines.Add(new Run("              临时车收费票据"));
//                    if (lstBPS.Count > 0 && lstBPS != null)
//                    {
//                        if (lstBPS[0].Title != "")
//                        {
//                            ph.Inlines.Add(new Run("\r\n" + "              " + lstBPS[0].Title));
//                        }
//                        if (lstBPS[0].FTitle != "")
//                        {
//                            ph.Inlines.Add(new Run("\r\n" + "              " + lstBPS[0].FTitle));
//                        }
//                    }
//                    ph.Inlines.Add(new Run("\r\n-----------------------------------"));
//                    doc.Blocks.Add(ph);
//                    doc.Blocks.Add(new Paragraph(new Run("车牌号码: 无牌车")));
//                    doc.Blocks.Add(new Paragraph(new Run("车辆编号:" + vr.CardNO)));
//                    doc.Blocks.Add(new Paragraph(new Run("车辆类型:" + cmbCardType.Text)));
//                    doc.Blocks.Add(new Paragraph(new Run("入场时间:" + vr.InTime.ToString("yyyy-MM-dd HH:mm:ss"))));
//                    doc.Blocks.Add(new Paragraph(new Run("出场时间:" + DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss"))));
//                    doc.Blocks.Add(new Paragraph(new Run("应收金额:" + lblSFJE.Content ? ? "0.0" + "元")));
//                    doc.Blocks.Add(new Paragraph(new Run("收费金额:" + lblMoney.Content ? ? "0.0" + "元")));
//
//                    doc.Blocks.Add(new Paragraph(new Run("操作员姓名:" + Model.sUserName)));
//
//                    doc.Blocks.Add(new Paragraph(new Run("打折方式:" + cmbJHDZ.Text)));
//
//                    Paragraph ph1 = new Paragraph();
//                    ph1.Inlines.Add(new Run("-----------------------------------"));
//                    if (lstBPS.Count > 0 && lstBPS != null)
//                    {
//                        if (lstBPS[0].Footer != "")
//                        {
//                            ph1.Inlines.Add(new Run("\r\n" + lstBPS[0].Footer));
//                        }
//                    }
//
//                    doc.Blocks.Add(ph1);
//                    rtbPrint.Document = doc;
//                    var printDialog = new PrintDialog();
//                    printDialog.PrintQueue = GetPrinter();
//                    printDialog.PrintDocument(((IDocumentPaginatorSource) rtbPrint.Document).DocumentPaginator, "A Flow Document");
//                }
//            }
//            catch (Exception ex)
//            {
//                gsd.AddLog(this.Title + ":btnPrint_Click", ex.Message + "\r\n" + ex.StackTrace);
//                MessageBox.Show(ex.Message + ":btnPrint_Click", "错误", MessageBoxButton.OK, MessageBoxImage.Error);
//            }
    }

    @Override
    public void success(String url, Object obj)
    {
        L.e("success:" + url + "####" + obj.toString());
        if (obj == null) return;
        if (obj instanceof GetCardTypeDefResp)
        {
            updateCardType(obj);
        }
        else if (obj instanceof GetParkDiscountJHSetResp)
        {
            updateParkDiscountJHSet(obj);
        }
        else if (obj instanceof GetFreeReasonResp)
        {
            updateFreeReason(obj);
        }
        else if (obj instanceof GetCarInResp)
        {
            updateGridViewData((GetCarInResp) obj);
        }
        else if (obj instanceof CaclChargeAmountResp)
        {
            updateChargeAmountView(obj);
        }
        else if (obj instanceof SetCarOutWithoutCPHResp)
        {
            updateWhenSetCarOutWithoutCPH(obj);
        }
        else if (obj instanceof SetCarFreeOutWithoutCPHResp)
        {
            updateWhenSetCarOutFreeWithoutCPH(obj);
        }
        else if (obj instanceof OnlinePayResp)
        {
            updateWhenOnlinePay(obj);
        }
        else if (obj instanceof GetBillPrintSetResp)
        {
            updateWhenPrintBill(obj);
        }
    }

    private void updateGridViewData(GetCarInResp obj)
    {
        GetCarInResp getCarInResp = obj;
        if (getCarInResp == null || getCarInResp.getData() == null)
        {
            return;
        }
        getCarInRespData = getCarInResp.getData();
        ArrayList<NoPlateCarItem> inList = new ArrayList<>();
        for (GetCarInResp.DataBean temp : getCarInRespData)
        {
            NoPlateCarItem item = new NoPlateCarItem();
            item.setCardNO(temp.getCardNO());
            item.setInUser(temp.getInUser());
            item.setSFGate(temp.getSFGate());
            item.setCPH(temp.getCPH());
            item.setChineseName(temp.getChineseName());
            item.setInTime(temp.getInTime());
            item.setInGateName(temp.getInGateName());
            item.setUserNO(temp.getUserNO());
            item.setUserName(temp.getUserName());
            item.setBalance(temp.getBalance() + "");
            item.setCarparkNO(temp.getCarparkNO() + "");
            item.setBigSmall(temp.getBigSmall() + "");
            item.setFreeReason(temp.getInPic());
            item.setInPic(temp.getDeptName());
            item.setDeptName(temp.getZJPic());
            item.setZJPic(temp.getInOperatorCard());
            item.setInOperatorCard(temp.getInOperator());
            inList.add(item);
        }
        setGridData(inList);
        setViewStatusOnSelect();
    }

    @NonNull
    private AddOptLogReq initAddOptLog(String menu, String content)
    {
        AddOptLogReq req = new AddOptLogReq();
        req.setToken(Model.token);
        req.setJsonModel(getAddOptLogText(menu, content));
        return req;
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


    private void updateMoney(CaclChargeAmountResp caclChargeAmountResp)
    {
        tvNeedMoney.setText(String.valueOf(caclChargeAmountResp.getData().getYSJE()));
        tvMoney.setText(String.valueOf(caclChargeAmountResp.getData().getSFJE()));
    }

    @Override
    public void error(String url, String string)
    {

    }

    class NoPlateOutListAdapter extends BaseAdapter
    {
        private List<NoPlateCarItem> mList;

        public NoPlateOutListAdapter(List list)
        {
            mList = list;
        }

        @Override
        public int getCount()
        {
            if (mList == null)
                return 0;
            return mList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder;
            if (convertView == null)
            {
                holder = new ViewHolder();
                convertView = (mActivity).getLayoutInflater().inflate(
                        R.layout.noplate_item, null); // 需要使用getLayoutInflater方法，即需要在该context中
                holder.tv1 = (TextView) convertView.findViewById(R.id.tvText1);
                holder.tv2 = (TextView) convertView.findViewById(R.id.tvText2);
                holder.tv3 = (TextView) convertView.findViewById(R.id.tvText3);
                holder.tv4 = (TextView) convertView.findViewById(R.id.tvText4);
                holder.tv5 = (TextView) convertView.findViewById(R.id.tvText5);
                holder.tv6 = (TextView) convertView.findViewById(R.id.tvText6);
                holder.tv7 = (TextView) convertView.findViewById(R.id.tvText7);
                holder.tv8 = (TextView) convertView.findViewById(R.id.tvText8);
                convertView.setTag(holder);// 保存起来，设置标记
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();// 获取标记
            }

            holder.tv1.setText(mList.get(position).getCardNO());
            holder.tv2.setText(mList.get(position).getInUser());
            holder.tv3.setText(mList.get(position).getSFGate());
            holder.tv4.setText(mList.get(position).getCPH());
            holder.tv5.setText(mList.get(position).getChineseName());
            holder.tv6.setText(mList.get(position).getInTime());
            holder.tv8.setText(mList.get(position).getInGateName());
            holder.tv7.setText(mList.get(position).getUserNO());
            return convertView;
        }

        class ViewHolder
        {
            public TextView tv1;
            public TextView tv2;
            public TextView tv3;
            public TextView tv4;
            public TextView tv5;
            public TextView tv6;
            public TextView tv7;
            public TextView tv8;
        }
    }

    private CPHRefreshListener mListener;

    public void setListener(CPHRefreshListener listener)
    {
        this.mListener = listener;
    }

    public interface CPHRefreshListener
    {
        void InNoCPHRefresh(int laneIndex, String localPath, String networkPath);
    }

    /**
     * 即输入在线支付
     */
    private void txtAuth_code_KeyDown()
    {
//        if (e.Key != Key.Enter)//如果输入的是回车键(即对于回车键的判断)
//        {
//            return;
//        }
        if (TextUtils.isEmpty(txtAuth_code.getText().toString()))
        {
            MessageBox.show(mActivity, "请输入授权码！");
            return;
        }

        String money = tvMoney.getText().toString();
        float floatMoney = Float.parseFloat(money);

        if (floatMoney <= 0)
        {
            MessageBox.show(mActivity, "请输入大于0的收费金额！");
            txtAuth_code.setText("");
            return;
        }

        int iFlag = 0;
        String strContent = "无牌车---交费：" + tvMoney.getText().toString() + "元";

        if (radWX.isChecked() == false && radZFB.isChecked() == false)
        {
            MessageBox.show(mActivity, "请选择支付方式！");
            return;
        }

        // 获取支付的状态
        if (radWX.isChecked())
        {
            iFlag = 1;
        }

        if (radZFB.isChecked())
        {
            iFlag = 2;
        }

        String resultUrl = GetServiceData.getResultUrl(METHOD_ONLINEPAY, initOnlineReq(iFlag, strContent));
        RequestManager
                .getInstance()
                .get(resultUrl, new GsonCallback<>(OnlinePayResp.class, this, resultUrl));
    }

    @NonNull
    private OnlinePayReq initOnlineReq(int iFlag, String strContent)
    {
        OnlinePayReq onlinePayReq = new OnlinePayReq();
        onlinePayReq.setToken(Model.token);
        String authCode = txtAuth_code.getText().toString();
        onlinePayReq.setAuthCode(authCode);
        onlinePayReq.setPayType(iFlag);
        onlinePayReq.setMoney(Float.parseFloat(tvMoney.getText().toString()));
        onlinePayReq.setMessage(strContent);
        onlinePayReq.setCPH(getCarInRespData.get(selectIndex).getCPH()); //这里的中文字符在，volley在传输的时候默认
        onlinePayReq.setStationId(Model.stationID); //
        L.i("OnlinePayReq:" + onlinePayReq.toString());
        return onlinePayReq;
    }
}



