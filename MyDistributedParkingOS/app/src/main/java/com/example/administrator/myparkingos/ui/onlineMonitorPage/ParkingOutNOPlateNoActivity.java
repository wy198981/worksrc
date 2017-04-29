package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.mydistributedparkingos.R;
import com.example.administrator.myparkingos.myUserControlLibrary.niceSpinner.NiceSpinner;

/**
 * Created by Administrator on 2017-02-16.
 * 【在线监控】 -->>【无牌车出场】
 */
public class ParkingOutNOPlateNoActivity extends AppCompatActivity implements View.OnClickListener
{
    private NiceSpinner dtpStart;
    private NiceSpinner dtpEnd;
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
    private TextView txtAuth_code;
    private ListView lvNoPlate;
    private TextView tvMoney;
    private ImageView ivInPicture;
    private TextView tvInPicHint;
    private ImageView ivOutPicture;
    private TextView tvOutPicHint;

    //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parkingout_noplate);
        initView();
    }

    private void initView()
    {
        dtpStart = (NiceSpinner) findViewById(R.id.dtpStart);
        dtpStart.setOnClickListener(this);
        dtpEnd = (NiceSpinner) findViewById(R.id.dtpEnd);
        dtpEnd.setOnClickListener(this);
        cmbColor = (NiceSpinner) findViewById(R.id.cmbColor);
        cmbColor.setOnClickListener(this);
        cmbCardType = (NiceSpinner) findViewById(R.id.cmbCardType);
        cmbCardType.setOnClickListener(this);
        txtCPH = (EditText) findViewById(R.id.txtCPH);
        txtCPH.setOnClickListener(this);
        cmbCarType = (NiceSpinner) findViewById(R.id.cmbCarType);
        cmbCarType.setOnClickListener(this);
        cmbJHDZ = (NiceSpinner) findViewById(R.id.cmbJHDZ);
        cmbJHDZ.setOnClickListener(this);
        cmbGateName = (NiceSpinner) findViewById(R.id.cmbGateName);
        cmbGateName.setOnClickListener(this);
        cmbFree = (NiceSpinner) findViewById(R.id.cmbFree);
        cmbFree.setOnClickListener(this);
        tvNeedMoney = (TextView) findViewById(R.id.tvNeedMoney);
        tvNeedMoney.setOnClickListener(this);
        btnNoPlateOutSearch = (Button) findViewById(R.id.btnNoPlateOutSearch);
        btnNoPlateOutSearch.setOnClickListener(this);
        btnNoPlateOutCalc = (Button) findViewById(R.id.btnNoPlateOutCalc);
        btnNoPlateOutCalc.setOnClickListener(this);
        btnNoPlateOutOpen = (Button) findViewById(R.id.btnNoPlateOutOpen);
        btnNoPlateOutOpen.setOnClickListener(this);
        btnNoPlateOutFreeOpen = (Button) findViewById(R.id.btnNoPlateOutFreeOpen);
        btnNoPlateOutFreeOpen.setOnClickListener(this);
        btnNoPlateOutPrint = (Button) findViewById(R.id.btnNoPlateOutPrint);
        btnNoPlateOutPrint.setOnClickListener(this);
        btnNoPlateOutCancel = (Button) findViewById(R.id.btnNoPlateOutCancel);
        btnNoPlateOutCancel.setOnClickListener(this);
        radWX = (RadioButton) findViewById(R.id.radWX);
        radWX.setOnClickListener(this);
        radZFB = (RadioButton) findViewById(R.id.radZFB);
        radZFB.setOnClickListener(this);
        txtAuth_code = (TextView) findViewById(R.id.txtAuth_code);
        txtAuth_code.setOnClickListener(this);
        lvNoPlate = (ListView) findViewById(R.id.lvNoPlate);
        lvNoPlate.setOnClickListener(this);
        tvMoney = (TextView) findViewById(R.id.tvMoney);
        tvMoney.setOnClickListener(this);
        ivInPicture = (ImageView) findViewById(R.id.ivInPicture);
        ivInPicture.setOnClickListener(this);
        tvInPicHint = (TextView) findViewById(R.id.tvInPicHint);
        tvInPicHint.setOnClickListener(this);
        ivOutPicture = (ImageView) findViewById(R.id.ivOutPicture);
        ivOutPicture.setOnClickListener(this);
        tvOutPicHint = (TextView) findViewById(R.id.tvOutPicHint);
        tvOutPicHint.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnNoPlateOutSearch:

                break;
            case R.id.btnNoPlateOutCalc:

                break;
            case R.id.btnNoPlateOutOpen:

                break;
            case R.id.btnNoPlateOutFreeOpen:

                break;
            case R.id.btnNoPlateOutPrint:

                break;
            case R.id.btnNoPlateOutCancel:

                break;
        }
    }


}
