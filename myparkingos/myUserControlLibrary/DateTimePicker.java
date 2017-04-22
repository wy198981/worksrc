package com.example.administrator.myparkingos.myUserControlLibrary;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.mydistributedparkingos.R;
import com.example.administrator.myparkingos.util.TimeConvertUtils;

import java.util.Calendar;

/**
 * Created by Administrator on 2017-04-17.
 */
public class DateTimePicker extends FrameLayout
{
    private ImageButton imageButton;
    private TextView textView;
    private Calendar calendar;


    public DateTimePicker(Context context)
    {
        super(context);
        initView(context);
    }

    public DateTimePicker(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView(context);
    }

    public DateTimePicker(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context)
    {
        View.inflate(context, R.layout.popu_datetime_pickter, this);
        imageButton = (ImageButton) findViewById(R.id.bt_dropdown);
        textView = (TextView) findViewById(R.id.tv_value);
        textView.setText(TimeConvertUtils.longToString("yyyy/MM/dd", System.currentTimeMillis()));
        imageButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getCarlender(context);
            }
        });
    }

    private void getCarlender(Context context)
    {
        calendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day)
            {
                String result = new StringBuilder().append(year).append("/")
                        .append((month + 1) < 10 ? "0" + (month + 1) : (month + 1))
                        .append("/").append((day < 10) ? "0" + day : day).toString();
                textView.setText(result);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public String getDateTime()
    {
        return textView.getText().toString();
    }
}
