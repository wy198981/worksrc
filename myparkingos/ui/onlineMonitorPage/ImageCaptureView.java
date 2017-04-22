package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.app.Activity;
import android.app.Dialog;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.administrator.mydistributedparkingos.R;

/**
 * Created by Administrator on 2017-04-15.
 */
public class ImageCaptureView implements View.OnClickListener
{
    private Button btnCapture;
    private Button btnOK;
    private Button btnCancel;
    private  Dialog dialog;
    private Activity mActivity;
    public ImageCaptureView(Activity activity)
    {
        mActivity = activity;

        dialog = new Dialog(activity); // @android:style/Theme.Dialog
        dialog.setContentView(R.layout.image_capture);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 1 / 2); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 1 / 2); // 宽度设置为屏幕的0.65
        p.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        window.setAttributes(p);

        dialog.setTitle(activity.getResources().getString(R.string.tempPlate_title));

    }

    private void initView()
    {
        btnCapture = (Button) dialog.findViewById(R.id.btnCapture);
        btnOK = (Button) dialog.findViewById(R.id.btnOK);
        btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        btnCapture.setOnClickListener(this);
        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCapture:

                break;
            case R.id.btnOK:
                cancel();
                break;
            case R.id.btnCancel:
                cancel();
                break;
        }
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

    public void cancel()
    {
        if (dialog != null && dialog.isShowing())
        {
            dialog.cancel();
        }
    }

}
