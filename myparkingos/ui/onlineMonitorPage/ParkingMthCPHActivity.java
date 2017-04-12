package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mydistributedparkingos.R;


/**
 * Created by Administrator on 2017-02-16.
 * 【月租车入场确认开闸，弹出界面，提前在车场设置中设置开闸方式】 CPH (che pai hao缩写)
 */
public class ParkingMthCPHActivity extends AppCompatActivity implements View.OnClickListener
{

    private TextView tvMthCarNo;
    private TextView tvMthExitOutName;
    private Button btnMthOk;
    private Button btnMthCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkingmth_cph);
        initView();

        Window window = getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 1 / 3); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 1 / 3); // 宽度设置为屏幕的0.65
        window.setAttributes(p);

//        window.setBackgroundDrawableResource(R.drawable.parkdowncard_background);
        setTitle(getResources().getString(R.string.tempPlate_title));

    }


    private void initView()
    {
        tvMthCarNo = (TextView) findViewById(R.id.tvMthCarNo); // tvMthCarNo
        tvMthExitOutName = (TextView) findViewById(R.id.tvMthExitOutName);
        btnMthOk = (Button) findViewById(R.id.btnMthOk);
        btnMthCancel = (Button) findViewById(R.id.btnMthCancel);

        btnMthOk.setOnClickListener(this);
        btnMthCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnMthOk:

                break;
            case R.id.btnMthCancel:

                break;
        }
    }
}
