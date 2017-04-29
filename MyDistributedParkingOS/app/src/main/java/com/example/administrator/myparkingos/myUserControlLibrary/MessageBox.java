package com.example.administrator.myparkingos.myUserControlLibrary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MessageBox
{

    public static void show(Activity activity, String message)
    {
        new AlertDialog.Builder(activity)
                .setTitle("提示")//设置对话框标题
                .setMessage(message)//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {//添加确定按钮

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {//确定按钮的响应事件

                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }

                }).setNegativeButton("取消", new DialogInterface.OnClickListener()
        {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which)
            {//响应事件
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        })
                .show();//在按键响应事件中显示此对话框
    }

    public interface IMessageBoxListener
    {
        void onClickPositive();

        void onClickNegative();
    }

    public static void showCanSetCallback(Activity activity, String message, final IMessageBoxListener listener)
    {
        new AlertDialog.Builder(activity)
                .setTitle("提示")//设置对话框标题
                .setMessage(message)//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {//添加确定按钮

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {//确定按钮的响应事件
                        if (listener != null)
                        {
                            listener.onClickPositive();
                        }

                    }

                }).setNegativeButton("取消", new DialogInterface.OnClickListener()
        {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which)
            {//响应事件
                if (listener != null)
                {
                    listener.onClickNegative();
                }
            }
        })
                .show();//在按键响应事件中显示此对话框
    }
}