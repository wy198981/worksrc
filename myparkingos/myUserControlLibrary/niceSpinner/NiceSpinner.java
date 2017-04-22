package com.example.administrator.myparkingos.myUserControlLibrary.niceSpinner;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.mydistributedparkingos.R;
import com.example.administrator.myparkingos.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-03-31.
 */
public class NiceSpinner extends FrameLayout implements View.OnClickListener
{

    private CustemSpinnerAdapter custemSpinerAdapter;
    private List<String> nameList = new ArrayList<String>();
    private SpinnerPopWindow spinerPopWindow;
    private ImageButton imageButton;
    private TextView textView;

    public NiceSpinner(Context context)
    {
        super(context);
        initView(context);
    }

    public NiceSpinner(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView(context);
    }

    public NiceSpinner(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context)
    {
        View.inflate(context, R.layout.popu_window, this);
        setFocusableInTouchMode(true);

        custemSpinerAdapter = new CustemSpinnerAdapter(context);
        custemSpinerAdapter.refreshData(nameList, 0);
        spinerPopWindow = new SpinnerPopWindow(context);
        spinerPopWindow.setAdatper(custemSpinerAdapter);

        imageButton = (ImageButton) findViewById(R.id.bt_Stationdropdown);
        imageButton.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.tv_Stationvalue);
    }

    private SpinnerListener mListener;

    public void setSpinnerListener(SpinnerListener listener)
    {
        mListener = listener;
        spinerPopWindow.setItemListener(new AbstractSpinerAdapter.IOnItemSelectListener()
        {
            @Override
            public void onItemClick(int pos)
            {
                String listDataByIndex = getListDataByIndex(pos);
                textView.setText(listDataByIndex);

                if (mListener != null)// 在构造函数时，mListener没有赋值;
                {
                    mListener.OnSpinnerItemClick(pos);
                }
            }
        });


    }

    public interface SpinnerListener
    {
        public void OnSpinnerItemClick(int pos);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_Stationdropdown:
                spinerPopWindow.setWidth(textView.getWidth());
                spinerPopWindow.showAsDropDown(textView);
                break;
        }
    }

    private String getListDataByIndex(int index)
    {
        if (!checkList(nameList, index))
            return null;

        return nameList.get(index);
    }

    public void refreshData(List<String> list, int index, boolean firstEmpty)
    {
        if (!checkList(list, index))
            return;

        nameList.clear();
        nameList.addAll(list);
        custemSpinerAdapter.refreshData(nameList, index);
        if (firstEmpty)
        {
            textView.setText("");// 设置为空
        }
        else
            textView.setText(getListDataByIndex(index));
    }


    public void refreshData(List<String> list, int index)
    {
        if (!checkList(list, index))
            return;

        nameList.clear();
        nameList.addAll(list);
        custemSpinerAdapter.refreshData(nameList, index);
        textView.setText(getListDataByIndex(index));
    }

    public void addEachData(String item, int index)
    {
        if (item == null)
            return;
        nameList.add(0, item);
        custemSpinerAdapter.refreshData(nameList, index);
        if (!checkList(nameList, index))
            return;

        textView.setText(getListDataByIndex(index));
    }

    public void addDataList(List<String> list, int index)
    {
        if (!checkList(list, index))
            return;

        custemSpinerAdapter.refreshData(nameList, index);
        textView.setText(getListDataByIndex(index));
    }

    class CustemSpinnerAdapter extends AbstractSpinerAdapter<String>
    {
        public CustemSpinnerAdapter(Context context)
        {
            super(context);
        }
    }

    public void setSelectIndex(int selectIndex)
    {
        if (!checkList(nameList, selectIndex))
            return;
        textView.setText(nameList.get(selectIndex));
    }

    private boolean checkList(List<String> list, int index)
    {
        if (list == null || list.size() == 0)
        {
            L.e("if (list == null || list.size() == 0)");
            return false;
        }

        int size = list.size();
        if (index >= 0 && index < size)
        {
            return true;
        }
        L.e("index < 0 || index >= size");
        return false;
    }

    public String getCurrentText()
    {
        return textView.getText().toString();
    }

    public void setCanEnable(boolean isEnable)
    {
        imageButton.setEnabled(isEnable);
    }


    public int getCurrentIndex()
    {
        String currentText = getCurrentText();
        if (TextUtils.isEmpty(currentText) || nameList.size() == 0)
        {
            return 0;
        }

        for (int i = 0; i < nameList.size(); i++)
        {
            String o = nameList.get(i);
            if (o.equals(currentText))
            {
                return i;
            }
        }
        L.e("no find " + currentText);
        return 0;
    }

    // bt_Stationdropdown
    public void resetDropDownIcon(int resourceID)
    {
        imageButton.setBackgroundResource(resourceID);
    }
}
