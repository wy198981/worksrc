package com.example.administrator.myparkingos.myUserControlLibrary.niceSpinner;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.administrator.mydistributedparkingos.R;

import java.util.List;

public class SpinnerPopWindow extends PopupWindow implements OnItemClickListener
{
    private Context mContext;
    private ListView mListView;
    private AbstractSpinerAdapter mAdapter;
    private AbstractSpinerAdapter.IOnItemSelectListener mItemSelectListener;


    public SpinnerPopWindow(Context context)
    {
        super(context);

        mContext = context;
        init();
    }


    public void setItemListener(AbstractSpinerAdapter.IOnItemSelectListener listener)
    {
        mItemSelectListener = listener;
    }

    public void setAdatper(AbstractSpinerAdapter adapter)
    {
        mAdapter = adapter;

        mListView.setAdapter(mAdapter);
    }


    private void init()
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popu_listview, null);
        setContentView(view);
        setWidth(LayoutParams.WRAP_CONTENT);
        setHeight(LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00);
        setBackgroundDrawable(dw);

        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
    }


    public <T> void refreshData(List<T> list, int selIndex)
    {
        if (list != null && selIndex != -1)
        {
            if (mAdapter != null)
            {
                mAdapter.refreshData(list, selIndex);
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3)
    {
        dismiss();
        if (mItemSelectListener != null)
        {
            mItemSelectListener.onItemClick(pos);
        }
    }


}
