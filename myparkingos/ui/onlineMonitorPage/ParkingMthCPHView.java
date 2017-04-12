package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.app.Activity;
import android.app.Dialog;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mydistributedparkingos.R;

/**
 * Created by Administrator on 2017-04-10.
 */
public class ParkingMthCPHView implements View.OnClickListener
{
    private final Activity mActivity;
    private Dialog dialog;
    private TextView tvCarNo;
    private TextView tvExitOutName;
    private Button btnMthOk;
    private Button btnMthCancel;

    public ParkingMthCPHView(Activity activity)
    {
        this.mActivity = activity;
        prepare(activity);

    }

    private void prepare(Activity activity)
    {
        dialog = new Dialog(activity); // @android:style/Theme.Dialog
        dialog.setContentView(R.layout.activity_parkingmth_cph);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 1 / 3); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 1 / 3); // 宽度设置为屏幕的0.65
        window.setAttributes(p);

        initView();
        dialog.setTitle(activity.getResources().getString(R.string.tempPlate_title));
    }

    private void initView()
    {
        tvCarNo = (TextView) dialog.findViewById(R.id.tvMthCarNo);
        tvExitOutName = (TextView) dialog.findViewById(R.id.tvMthExitOutName);
        btnMthOk = (Button) dialog.findViewById(R.id.btnMthOk);
        btnMthCancel = (Button) dialog.findViewById(R.id.btnMthCancel);

        btnMthOk.setOnClickListener(this);
        btnMthCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

    }

    public void show()
    {
        if (dialog != null)
        {
            prepareLoadData();// 显示之前加载数据
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
            clearDataInView();
            dialog.dismiss();
        }
    }

    private void clearDataInView()
    {
        
    }
}
