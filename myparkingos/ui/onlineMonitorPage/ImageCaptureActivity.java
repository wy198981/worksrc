package com.example.administrator.myparkingos.ui.onlineMonitorPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.administrator.mydistributedparkingos.R;

/**
 * Created by Administrator on 2017-02-16.
 * 摄像头拍照,在收费时候利用usb摄像头抓拍图像，提前需要在车场设置中设置参数
 */
public class ImageCaptureActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btnCapture;
    private Button btnOK;
    private Button btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_capture);
        initView();

        Window window = getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 1 / 2); // 改变的是dialog框在屏幕中的位置而不是大小
        p.width = (int) (d.getWidth() * 1 / 2); // 宽度设置为屏幕的0.65
        p.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        window.setAttributes(p);

//        window.setBackgroundDrawableResource(R.drawable.parkdowncard_background);
        setTitle(getResources().getString(R.string.imageCapture_title));
    }

    private void initView()
    {
        btnCapture = (Button) findViewById(R.id.btnCapture);
        btnOK = (Button) findViewById(R.id.btnOK);
        btnCancel = (Button) findViewById(R.id.btnCancel);

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

                break;
            case R.id.btnCancel:

                break;
        }
    }
}
