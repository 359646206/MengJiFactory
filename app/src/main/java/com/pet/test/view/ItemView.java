package com.pet.test.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pet.test.R;

/**
 * Created by yangzhengguang on 2018/9/18.
 */

public class ItemView extends FrameLayout{
    private TextView index;
    private TextView status;
    private TextView scan;
    private TextView stop;
    private TextView imei;
    private TextView time;
    private TextView csq;
    private TextView battery;
    private TextView jingdu;
    private TextView weidu;
    private TextView sw;
    private TextView hw;

    public ItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item, this);
        initView();
    }

    private void initView() {
        index = findViewById(R.id.index);
        status = findViewById(R.id.status);
        scan = findViewById(R.id.scan);
        stop = findViewById(R.id.stop);
        imei = findViewById(R.id.imei);
        time= findViewById(R.id.time);
        csq = findViewById(R.id.csq);
        battery= findViewById(R.id.battery);
        jingdu= findViewById(R.id.jingdu);
        weidu = findViewById(R.id.weidu);
        sw = findViewById(R.id.sw);
        hw = findViewById(R.id.hw);
    }
    public void setIndexText(String text) {
        index.setText(text);
    }

    public void setStatusText(String text) {
        status.setText(text);
    }

    public void setScanText(String text) {
        scan.setText(text);
    }

    public void setImeiText(String text) {
        imei.setText(text);
    }

    public void setTimeText(String text) {
        time.setText(text);
    }

    public void setCsqText(String text) {
        csq.setText(text);
    }
    public void setBatteryText(String text) {
        battery.setText(text);
    }

    public void setJingduText(String text) {
        jingdu.setText(text);
    }

    public void setWeiduText(String text) {
        weidu.setText(text);
    }

    public void setSwText(String text) {
        sw.setText(text);
    }

    public void setHwText(String text) {
        hw.setText(text);
    }

    public String getImeiText() {
        return imei.getText().toString();
    }

    public void setStatusTextColor(int color) {
        status.setTextColor(color);
    }

    public void setScanButtonListener(OnClickListener listener) {
        scan.setOnClickListener(listener);
    }

    public void setStopButtonEnable(boolean enable) {
        stop.setEnabled(enable);
    }

    public void setStopButtonListener(OnClickListener listener) {
        stop.setOnClickListener(listener);
    }

    public void setScanButtonEnable(boolean enable) {
        scan.setEnabled(enable);
    }

    public void resetAll(){
        status.setText("状态:");
        status.setTextColor(getResources().getColor(R.color.colorGray));
        imei.setText(null);
        time.setText(null);
        csq.setText(null);
        battery.setText(null);
        jingdu.setText(null);
        weidu.setText(null);
        sw.setText(null);
        hw.setText(null);
    }

}
