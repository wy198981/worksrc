package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.mydistributedparkingos.R;
import com.example.administrator.myparkingos.myUserControlLibrary.niceSpinner.NiceSpinner;

/**
 * Created by Administrator on 2017-04-07.
 */
public class ParkingTempGob_bigActivity extends AppCompatActivity implements View.OnClickListener
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
    private EditText etStopMoney;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkingtempgob_big);
        initView();

        Window window = getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 1 / 1.5); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 1 / 2); // 宽度设置为屏幕的0.65
        window.setAttributes(p);

//        window.setBackgroundDrawableResource(R.drawable.parkdowncard_background);
        setTitle(getResources().getString(R.string.charge_title));
    }

    private void initView()
    {
        ivChargePicture0 = (ImageView) findViewById(R.id.ivChargePicture0);
        tvNoVideo0 = (TextView) findViewById(R.id.tvNoVideo0);
        ivChargePicture1 = (ImageView) findViewById(R.id.ivChargePicture1);
        tvNoVideo1 = (TextView) findViewById(R.id.tvNoVideo1);
        tvCPH = (TextView) findViewById(R.id.tvCPH);
        tvCarType = (TextView) findViewById(R.id.tvCarType);
        tvInDateTime = (TextView) findViewById(R.id.tvInDateTime);
        tvOutTime = (TextView) findViewById(R.id.tvOutTime);
        tvTimeDuration = (TextView) findViewById(R.id.tvTimeDuration);
        etStopMoney = (EditText) findViewById(R.id.etStopCarMoney);
        needPayMoney = (TextView) findViewById(R.id.needPayMoney);
        nsDiscountSpace = (NiceSpinner) findViewById(R.id.nsDiscountSpace);
        nSpFreeReason = (NiceSpinner) findViewById(R.id.nSpFreeReason);
        rgPayWeixin = (RadioButton) findViewById(R.id.rgPayWeixin);
        rgPayZhifubao = (RadioButton) findViewById(R.id.rgPayZhifubao);
        nsCarType = (NiceSpinner) findViewById(R.id.nsCarType);
        btnCancelCharge = (Button) findViewById(R.id.btnCancelCharge);
        btnVoicePay = (Button) findViewById(R.id.btnVoicePay);
        btnCutOff = (Button) findViewById(R.id.btnCutOff);
        btnPicCapture = (Button) findViewById(R.id.btnPicCapture);
        btnChargeFree = (Button) findViewById(R.id.btnChargeFree);
        btnGiveUp = (Button) findViewById(R.id.btnGiveUp);
        llnormal = (LinearLayout) findViewById(R.id.llnormal);
        etCarPlate = (EditText) findViewById(R.id.etCarPlate);
        btnConfirmed = (Button) findViewById(R.id.btnConfirmed);
        lvCarIssue = (ListView) findViewById(R.id.lvCarIssue);
        lvCarInPark = (ListView) findViewById(R.id.lvCarInPark);
        btnNoRecordPass = (Button) findViewById(R.id.btnNoRecordPass);
        tvWarmHint = (TextView) findViewById(R.id.tvWarmHint);
        llNoCarRecord = (LinearLayout) findViewById(R.id.llNoCarRecord);

        btnCancelCharge.setOnClickListener(this);
        btnVoicePay.setOnClickListener(this);
        btnCutOff.setOnClickListener(this);
        btnPicCapture.setOnClickListener(this);
        btnChargeFree.setOnClickListener(this);
        btnGiveUp.setOnClickListener(this);
        btnConfirmed.setOnClickListener(this);
        btnNoRecordPass.setOnClickListener(this);
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
        }
    }

}
