package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.mydistributedparkingos.R;
import com.example.administrator.myparkingos.ParkingModel.Model;
import com.example.administrator.myparkingos.constant.GlobalParams;
import com.example.administrator.myparkingos.model.responseInfo.GetCardTypeDefResp;
import com.example.administrator.myparkingos.model.responseInfo.GetFreeReasonResp;
import com.example.administrator.myparkingos.model.responseInfo.GetParkDiscountJHSetResp;
import com.example.administrator.myparkingos.model.responseInfo.GetSysSettingObjectResp;
import com.example.administrator.myparkingos.model.responseInfo.SetCarOutResp;
import com.example.administrator.myparkingos.myUserControlLibrary.niceSpinner.NiceSpinner;
import com.example.administrator.myparkingos.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-02-16.
 * 在临时车收费时，弹出收费界面
 */
public class ParkingTempGob_bigView implements View.OnClickListener
{
    private ImageView ivChargePicture0;
    private TextView tvNoVideo0;
    private ImageView ivChargePicture1;
    private TextView tvNoVideo1;
    private TextView tvCPH;
    private TextView tvCarType;
    private TextView tvInDateTime;
    private TextView tvOutTime;
    private TextView tvTimeDuration;
    private EditText etStopCarMoney;
    private TextView needPayMoney;
    private NiceSpinner nsDiscountSpace;
    private NiceSpinner nSpFreeReason;
    private RadioButton rgPayWeixin;
    private RadioButton rgPayZhifubao;
    private NiceSpinner nsCarType;
    private Button btnCancelCharge;
    private Button btnVoicePay;
    private Button btnCutOff;
    private Button btnPicCapture;
    private Button btnChargeFree;
    private Button btnGiveUp;
    private LinearLayout llnormal;
    private EditText etCarPlate;
    private Button btnConfirmed;
    private ListView lvCarIssue;
    private ListView lvCarInPark;
    private Button btnNoRecordPass;
    private TextView tvWarmHint;
    private LinearLayout llNoCarRecord;


    private final Activity mActivity;
    private final Dialog dialog;
    private ArrayList<String> reasonList;
    private ArrayList<String> discountList;
    private Button btnPrintBill;
    private TextView tvPayText;
    private ArrayList<String> carIssuelist = new ArrayList<String>();
    private ArrayList<String> carInParkinglist = new ArrayList<String>();
    private ArrayAdapter<String> issueAdapter;
    private ArrayAdapter<String> carInParkingAdapter;
    private ArrayList<String> cardTypeList;
    private EditText etNeedPayMoney;

    public static enum E_VIEW_TYPE
    {
        E_VIEW_CHARGE_HAVA_RECORD, // view视图即为，有正常记录收费
        E_VIEW_CHARGE_NOT_RECORD,  // view视图即为，没有出场纪录的收费
        E_VIEW_CHARGE_UNKOWN, // 没有类型
    }


    private E_VIEW_TYPE mType = E_VIEW_TYPE.E_VIEW_CHARGE_UNKOWN;

    public ParkingTempGob_bigView(Activity activity, E_VIEW_TYPE type)
    {
        L.i("ParkingTempGob_bigView");
        this.mActivity = activity;
        this.mType = type;
        dialog = new Dialog(activity); // @android:style/Theme.Dialog
        dialog.setContentView(R.layout.activity_parkingtempgob_big);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 1 / 1.5); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 1 / 2); // 宽度设置为屏幕的0.65
        window.setAttributes(p);

        dialog.setTitle(mActivity.getResources().getString(R.string.charge_title));
        initView();
    }

    public void loadedDataWhenNotInRecord()
    {
    }

    public void loadedDataWhenHaveInRecord()
    {

    }

    /**
     * 初始化控件状态
     */
    public void initControlStatusBySysSetting()
    {
        if (GlobalParams.getSysSettingObjectResp() == null) return;
        GetSysSettingObjectResp.DataBean objectResp = GlobalParams.getSysSettingObjectResp();
        if (Integer.parseInt(objectResp.getiBillPrint()) == 1)
        {
            btnPrintBill.setVisibility(View.VISIBLE);
        }
        else
        {
            btnPrintBill.setVisibility(View.GONE);
        }

        if (Integer.parseInt(objectResp.getbSetTempMoney()) == 1)
        {
            etStopCarMoney.setFocusable(true);
        }
        else
        {
            etStopCarMoney.setFocusable(false);
        }

        if (Integer.parseInt(objectResp.getbSFCancel()) == 1)
        {
            btnCancelCharge.setVisibility(View.VISIBLE);
        }
        else
        {
            btnCancelCharge.setVisibility(View.GONE);
        }

        if (Integer.parseInt(objectResp.getiBillPrint()) > 0)
        {
            btnPrintBill.setVisibility(View.VISIBLE);
        }
        else
        {
            btnPrintBill.setVisibility(View.GONE);
        }

        if (Integer.parseInt(objectResp.getiTempFree()) > 0)
        {
            btnChargeFree.setEnabled(true);
            nSpFreeReason.setFocusable(true);
        }
        else
        {
            btnChargeFree.setEnabled(false);
            nSpFreeReason.setFocusable(false);
        }

        if (Integer.parseInt(objectResp.getiIDCapture()) > 0)
        {
            btnPicCapture.setEnabled(true);
        }
        else
        {
            btnPicCapture.setEnabled(false);
        }

        if (Integer.parseInt(objectResp.getbSetTempCardType()) > 0)
        {
            nsCarType.setFocusable(true);
        }
        else
        {
            nsCarType.setFocusable(false);
        }

        if (Integer.parseInt(objectResp.getbOnlinePayEnabled()) == 1)
        {
            rgPayWeixin.setEnabled(true);
            rgPayZhifubao.setEnabled(true);
            tvPayText.setEnabled(true);
        }
        else
        {
            rgPayWeixin.setEnabled(false);
            rgPayZhifubao.setEnabled(false);
            tvPayText.setEnabled(false);
        }
    }


    private void initView()
    {
        ivChargePicture0 = (ImageView) dialog.findViewById(R.id.ivChargePicture0);
        tvNoVideo0 = (TextView) dialog.findViewById(R.id.tvNoVideo0);
        ivChargePicture1 = (ImageView) dialog.findViewById(R.id.ivChargePicture1);
        tvNoVideo1 = (TextView) dialog.findViewById(R.id.tvNoVideo1);
        tvCPH = (TextView) dialog.findViewById(R.id.tvCPH);
        tvCarType = (TextView) dialog.findViewById(R.id.tvCarType);
        tvInDateTime = (TextView) dialog.findViewById(R.id.tvInDateTime);
        tvOutTime = (TextView) dialog.findViewById(R.id.tvOutTime);
        tvTimeDuration = (TextView) dialog.findViewById(R.id.tvTimeDuration);
        etStopCarMoney = (EditText) dialog.findViewById(R.id.etStopCarMoney);
        needPayMoney = (TextView) dialog.findViewById(R.id.needPayMoney);
        nsDiscountSpace = (NiceSpinner) dialog.findViewById(R.id.nsDiscountSpace);
        nSpFreeReason = (NiceSpinner) dialog.findViewById(R.id.nSpFreeReason);
        rgPayWeixin = (RadioButton) dialog.findViewById(R.id.rgPayWeixin);
        rgPayZhifubao = (RadioButton) dialog.findViewById(R.id.rgPayZhifubao);
        nsCarType = (NiceSpinner) dialog.findViewById(R.id.nsCarType);
        btnCancelCharge = (Button) dialog.findViewById(R.id.btnCancelCharge);
        btnVoicePay = (Button) dialog.findViewById(R.id.btnVoicePay);
        btnCutOff = (Button) dialog.findViewById(R.id.btnCutOff);
        btnPicCapture = (Button) dialog.findViewById(R.id.btnPicCapture);
        btnChargeFree = (Button) dialog.findViewById(R.id.btnChargeFree);
        btnGiveUp = (Button) dialog.findViewById(R.id.btnGiveUp);
        llnormal = (LinearLayout) dialog.findViewById(R.id.llnormal);
        etCarPlate = (EditText) dialog.findViewById(R.id.etCarPlate);
        btnConfirmed = (Button) dialog.findViewById(R.id.btnConfirmed);
        lvCarIssue = (ListView) dialog.findViewById(R.id.lvCarIssue);
        lvCarInPark = (ListView) dialog.findViewById(R.id.lvCarInPark);
        btnNoRecordPass = (Button) dialog.findViewById(R.id.btnNoRecordPass);
        tvWarmHint = (TextView) dialog.findViewById(R.id.tvWarmHint);
        llNoCarRecord = (LinearLayout) dialog.findViewById(R.id.llNoCarRecord);
        btnPrintBill = (Button) dialog.findViewById(R.id.btnPrintBill);
        tvPayText = (TextView) dialog.findViewById(R.id.tvPayText);
        etNeedPayMoney = (EditText) dialog.findViewById(R.id.etNeedPayMoney);

        btnCancelCharge.setOnClickListener(this);
        btnVoicePay.setOnClickListener(this);
        btnCutOff.setOnClickListener(this);
        btnPicCapture.setOnClickListener(this);
        btnChargeFree.setOnClickListener(this);
        btnGiveUp.setOnClickListener(this);
        btnConfirmed.setOnClickListener(this);
        btnNoRecordPass.setOnClickListener(this);
        btnPrintBill.setOnClickListener(this);

        showViewByType();

        reasonList = new ArrayList<>();
        nSpFreeReason.refreshData(reasonList, 0);

        discountList = new ArrayList<>();
        nsDiscountSpace.refreshData(discountList, 0);

        issueAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, carIssuelist);
        lvCarIssue.setAdapter(issueAdapter);
        lvCarIssue.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String cph = carIssuelist.get(position);
                etCarPlate.setText(cph);
                etCarPlate.setSelection(cph.length());
            }
        });

        carInParkingAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, carInParkinglist);
        lvCarInPark.setAdapter(carInParkingAdapter);
        lvCarInPark.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String cph = carInParkinglist.get(position);
                etCarPlate.setText(cph);
                etCarPlate.setSelection(cph.length());
            }
        });


        cardTypeList = new ArrayList<>();
        nsCarType.refreshData(cardTypeList, 0);
    }

    private void showViewByType()
    {
        if (mType == E_VIEW_TYPE.E_VIEW_CHARGE_HAVA_RECORD)
        {
            if (llnormal.getVisibility() != View.VISIBLE)
            {
                llnormal.setVisibility(View.VISIBLE);
            }

            if (llNoCarRecord.getVisibility() != View.GONE)
            {
                llNoCarRecord.setVisibility(View.GONE);
            }
        }
        else if (mType == E_VIEW_TYPE.E_VIEW_CHARGE_NOT_RECORD)
        {
            if (llnormal.getVisibility() != View.GONE)
            {
                llnormal.setVisibility(View.GONE);
            }

            if (llNoCarRecord.getVisibility() != View.VISIBLE)
            {
                llNoCarRecord.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCancelCharge:

                break;
            case R.id.btnVoicePay:

                break;
            case R.id.btnCutOff:

                break;
            case R.id.btnPicCapture:

                break;
            case R.id.btnChargeFree:

                break;
            case R.id.btnGiveUp:

                break;
            case R.id.btnConfirmed:

                break;
            case R.id.btnNoRecordPass:

                break;
            case R.id.btnPrintBill:
                break;
        }
    }

    public void show()
    {
        if (dialog != null)
        {
            prepareLoadData();// 显示之前加载数据
            if (mType == E_VIEW_TYPE.E_VIEW_CHARGE_HAVA_RECORD)
            {
                L.i("mType == E_VIEW_TYPE.E_VIEW_CHARGE_HAVA_RECORD");
                loadedDataWhenHaveInRecord();
            }
            else if (mType == E_VIEW_TYPE.E_VIEW_CHARGE_NOT_RECORD)
            {
                L.i("mType == E_VIEW_TYPE.E_VIEW_CHARGE_NOT_RECORD");
                loadedDataWhenNotInRecord();
            }
            dialog.show();
        }
    }

    public void prepareLoadData()
    {

    }

    public void dismiss()
    {
        if (dialog != null && dialog.isShowing())
        {
            dialog.dismiss();
        }
    }

    public void close()
    {
        if (dialog != null)
        {
            dialog.cancel();
        }
    }

    public void setReasonList(List<GetFreeReasonResp.DataBean> data)
    {
        if (data == null || data.size() == 0) return;

        reasonList.clear();
        for (int i = 0; i < data.size(); i++)
        {
            reasonList.add(data.get(i).getItemDetail());
        }

        nSpFreeReason.refreshData(reasonList, 0);
    }

    public void setDiscountAddressList(List<GetParkDiscountJHSetResp.DataBean> data)
    {
        if (data == null || data.size() == 0) return;

        discountList.clear();
        for (GetParkDiscountJHSetResp.DataBean o : data)
        {
            discountList.add(o.getAddress());
        }
        nSpFreeReason.refreshData(discountList, 0);
    }

    public void setListViewCarInPark(List<SetCarOutResp.DataBeanInner> data)
    {
        carInParkinglist.clear();
        for (SetCarOutResp.DataBeanInner o : data)
        {
            carInParkinglist.add(o.getCPH());
        }
        carInParkingAdapter.notifyDataSetChanged();
    }

    public void setListViewCarIssueData(List<String> list)
    {
        carIssuelist.clear();
        carIssuelist.addAll(list);
        issueAdapter.notifyDataSetChanged();


    }

    public void setConfirmButtonEnable(boolean type)
    {
        btnConfirmed.setEnabled(type);
    }

    public void setCarInPicture(Bitmap bitmap)
    {
        if (bitmap != null)
        {
            if (ivChargePicture0.getVisibility() != View.VISIBLE)
                ivChargePicture0.setVisibility(View.VISIBLE);
            ivChargePicture0.setImageBitmap(bitmap);
        }
    }

    public void setCarOutPicture(Bitmap bitmap)
    {
        if (bitmap != null)
        {
            if (ivChargePicture1.getVisibility() != View.VISIBLE)
                ivChargePicture1.setVisibility(View.VISIBLE);
            ivChargePicture1.setImageBitmap(bitmap);
        }
    }

    public void setCardTypeSpinner(List<GetCardTypeDefResp.DataBean> data)
    {
        if (data == null || data.size() <= 0)
        {
            return;
        }
        cardTypeList.clear();
        for (GetCardTypeDefResp.DataBean o : data)
        {
            if (Model.Language.equals("Chinese"))
            {
                cardTypeList.add(o.getCardType());
            }
            else
            {
                cardTypeList.add(o.getRemarks());
            }
        }

        nsCarType.refreshData(cardTypeList, 0);
    }

    public void setCardTypeSpinnerIndexByText(String inCardType)
    {
        if (inCardType == null || cardTypeList.size() <= 0)
            return;
        for (int i = 0; i < cardTypeList.size(); i++)
        {
            if (cardTypeList.get(i).equals(inCardType))
            {
                nsCarType.setSelectIndex(i);
                return;
            }
        }
    }

    public void setCPHInHaveCarInRecord(String cph)
    {
        tvCPH.setText(cph);
    }

    public void setCPHInNoInRecord(String cph)
    {
        etCarPlate.setText(cph);
        etCarPlate.setSelection((cph != null) ? cph.length() : 0);
    }

    public void setInTime(String time)
    {
        tvInDateTime.setText(time);
    }

    public void setOutTime(String outTime)
    {
        tvOutTime.setText(outTime);
    }

    public void setDuringTime(String intervalTime)
    {
        tvTimeDuration.setText(intervalTime);
    }

    public void setChargeMoney(String money)
    {
        etStopCarMoney.setText(money);
    }

    public void setNeedMoneyOnFinal(String money)
    {
        etStopCarMoney.setText(money);
    }

    public String getCmdCardType()
    {
        return nsCarType.getCurrentText();
    }

    public String getSYJE()
    {
        return etStopCarMoney.getEditableText().toString();
    }
}
