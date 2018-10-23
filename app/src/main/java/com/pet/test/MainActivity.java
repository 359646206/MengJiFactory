package com.pet.test;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pet.test.http.HttpMethods;
import com.pet.test.javabean.BaseEntity;
import com.pet.test.javabean.Pet;
import com.pet.test.utils.Const;
import com.pet.test.utils.TimeUtil;
import com.pet.test.view.ItemView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "yy";
    private static final int REQUEST_SCAN_CODE_01 = 101;
    private static final int REQUEST_SCAN_CODE_02 = 102;
    private static final int REQUEST_SCAN_CODE_03 = 103;
    private static final int REQUEST_SCAN_CODE_04 = 104;
    private static final int REQUEST_SCAN_CODE_05 = 105;
    private static final int REQEST_PERMISSION = 1000;
    private static final int LOOP_PERIOD = 30 * 1000;

    private boolean isOngoing01 = false;
    private boolean isOngoing02 = false;
    private boolean isOngoing03 = false;
    private boolean isOngoing04 = false;
    private boolean isOngoing05 = false;

    private String imei01Str;
    private String imei02Str;
    private String imei03Str;
    private String imei04Str;
    private String imei05Str;

    private ItemView item01;
    private ItemView item02;
    private ItemView item03;
    private ItemView item04;
    private ItemView item05;
    private TextView settingsText;

    private SharedPreferences mSharedPreferences;

    private Handler mHandler = new Handler();
    private Runnable mRunnable01 = new Runnable() {
        @Override
        public void run() {
            infoPet(REQUEST_SCAN_CODE_01, imei01Str);
            Log.i(TAG, "mRunnable--> isOngoing01=" + isOngoing01);
            if (isOngoing01) {
                mHandler.postDelayed(mRunnable01, LOOP_PERIOD);
            }
        }
    };
    private Runnable mRunnable02 = new Runnable() {
        @Override
        public void run() {
            infoPet(REQUEST_SCAN_CODE_02, imei02Str);
            Log.i(TAG, "mRunnable--> isOngoing02=" + isOngoing02);
            if (isOngoing02) {
                mHandler.postDelayed(mRunnable02, LOOP_PERIOD);
            }
        }
    };
    private Runnable mRunnable03 = new Runnable() {
        @Override
        public void run() {
            infoPet(REQUEST_SCAN_CODE_03, imei03Str);
            Log.i(TAG, "mRunnable--> isOngoing03=" + isOngoing03);
            if (isOngoing03) {
                mHandler.postDelayed(mRunnable03, LOOP_PERIOD);
            }
        }
    };
    private Runnable mRunnable04 = new Runnable() {
        @Override
        public void run() {
            infoPet(REQUEST_SCAN_CODE_04, imei04Str);
            Log.i(TAG, "mRunnable--> isOngoing04=" + isOngoing04);
            if (isOngoing04) {
                mHandler.postDelayed(mRunnable04, LOOP_PERIOD);
            }
        }
    };
    private Runnable mRunnable05 = new Runnable() {
        @Override
        public void run() {
            infoPet(REQUEST_SCAN_CODE_05, imei05Str);
            Log.i(TAG, "mRunnable--> isOngoing05=" + isOngoing05);
            if (isOngoing05) {
                mHandler.postDelayed(mRunnable05, LOOP_PERIOD);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
        initView();
        initSharedPreference();
    }

    private void initSharedPreference() {
        mSharedPreferences = getSharedPreferences(getPackageName() + "_preferences", Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        if (isOngoing01) {
            item01.setScanButtonEnable(false);
            item01.setStopButtonEnable(true);
        } else {
            item01.setScanButtonEnable(true);
            item01.setStopButtonEnable(false);
        }

        if (isOngoing02) {
            item02.setScanButtonEnable(false);
            item02.setStopButtonEnable(true);
        } else {
            item02.setScanButtonEnable(true);
            item02.setStopButtonEnable(false);
        }

        if (isOngoing03) {
            item03.setScanButtonEnable(false);
            item03.setStopButtonEnable(true);
        } else {
            item03.setScanButtonEnable(true);
            item03.setStopButtonEnable(false);
        }

        if (isOngoing04) {
            item04.setScanButtonEnable(false);
            item04.setStopButtonEnable(true);
        } else {
            item04.setScanButtonEnable(true);
            item04.setStopButtonEnable(false);
        }

        if (isOngoing05) {
            item05.setScanButtonEnable(false);
            item05.setStopButtonEnable(true);
        } else {
            item05.setScanButtonEnable(true);
            item05.setStopButtonEnable(false);
        }

//        String versionStr = mSharedPreferences.getString(Const.KEY_VERSION,Const.DEFAULT_VERSION);
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQEST_PERMISSION);
            return;
        }
    }

    private void initView() {
        settingsText = findViewById(R.id.settings);
        settingsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        item01 = findViewById(R.id.item_1);
        item01.setIndexText("#1");
        item01.setScanText("扫码1");
        item01.setScanButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQrCode(REQUEST_SCAN_CODE_01);
            }
        });
        item01.setStopButtonListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnable01);
                isOngoing01 = false;
                item01.resetAll();
                item01.setStopButtonEnable(false);
                item01.setScanButtonEnable(true);
            }
        });


        item02 = findViewById(R.id.item_2);
        item02.setIndexText("#2");
        item02.setScanText("扫码2");
        item02.setScanButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQrCode(REQUEST_SCAN_CODE_02);
            }
        });
        item02.setStopButtonListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnable02);
                isOngoing02 = false;
                item02.resetAll();
                item02.setStopButtonEnable(false);
                item02.setScanButtonEnable(true);
            }
        });

        item03 = findViewById(R.id.item_3);
        item03.setIndexText("#3");
        item03.setScanText("扫码3");
        item03.setScanButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQrCode(REQUEST_SCAN_CODE_03);
            }
        });
        item03.setStopButtonListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnable03);
                isOngoing03 = false;
                item03.resetAll();
                item03.setStopButtonEnable(false);
                item03.setScanButtonEnable(true);
            }
        });

        item04 = findViewById(R.id.item_4);
        item04.setIndexText("#4");
        item04.setScanText("扫码4");
        item04.setScanButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQrCode(REQUEST_SCAN_CODE_04);
            }
        });
        item04.setStopButtonListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnable04);
                isOngoing04 = false;
                item04.resetAll();
                item04.setStopButtonEnable(false);
                item04.setScanButtonEnable(true);
            }
        });

        item05 = findViewById(R.id.item_5);
        item05.setIndexText("#5");
        item05.setScanText("扫码5");
        item05.setScanButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQrCode(REQUEST_SCAN_CODE_05);
            }
        });
        item05.setStopButtonListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnable05);
                isOngoing05 = false;
                item05.resetAll();
                item05.setStopButtonEnable(false);
                item05.setScanButtonEnable(true);
            }
        });
    }

    private void startQrCode(int requestCode) {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, requestCode);
    }

    private void infoPet(final int index, String imei) {
        JSONObject root = new JSONObject();
        try {
            root.put("id", imei);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "root=" + root);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), root.toString());

        HttpMethods.getInstance().getApiService()
                .infoApi(requestBody)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<Pet>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseEntity<Pet> baseEntity) {
                        int status = baseEntity.getStatus();
                        Log.i(TAG, " info onNext() status=" + status);
                        if (status == 200) {
                            Pet pet = baseEntity.getData();
                            String csqStr = mSharedPreferences.getString(Const.KEY_CSQ, Const.DEFAULT_CSQ);
                            String sw = mSharedPreferences.getString(Const.KEY_SW_VERSION, Const.DEFAULT_SW_VERSION);
                            int minCsq = Integer.parseInt(csqStr);
                            Log.i(TAG, "minCsq=" + minCsq + ",sw=" + sw);
                            Log.i(TAG, "info onNext() pet=" + pet.toString());
                            switch (index) {
                                case REQUEST_SCAN_CODE_01:
                                    if (TextUtils.isEmpty(pet.getIMEI())) {//无数据，返回{}
                                        item01.setStatusText("状态: 等待数据...");
                                        item01.setStatusTextColor(getResources().getColor(R.color.colorGray));
                                    } else {//有数据
                                        if (pet.getStates().contains("poweroff")) {
                                            item01.setStatusText("状态: 关机");
                                            item01.setStatusTextColor(Color.LTGRAY);
                                        } else if (pet.getNbcsq() < minCsq ||
                                                pet.getBattery() == 0 ||
                                                pet.getLongitude() == 0 ||
                                                !(sw.equals(pet.getSVersion())) ||
                                                pet.getLatitude() == 0) {
                                            item01.setStatusText("状态: 失败");
                                            item01.setStatusTextColor(Color.RED);
                                        } else {
                                            item01.setStatusText("状态: 成功");
                                            item01.setStatusTextColor(Color.GREEN);
                                        }
                                        item01.setTimeText(TimeUtil.formatData("HH:mm:ss", pet.getTs()));
                                        item01.setCsqText("" + pet.getNbcsq());
                                        item01.setBatteryText("" + pet.getBattery());
                                        item01.setJingduText("" + pet.getLongitude());
                                        item01.setWeiduText("" + pet.getLatitude());
                                        item01.setSwText("" + pet.getSVersion());
                                        item01.setHwText("" + pet.getHVersion());
                                    }
                                    break;
                                case REQUEST_SCAN_CODE_02:
                                    if (TextUtils.isEmpty(pet.getIMEI())) {//无数据，返回{}
                                        item02.setStatusText("状态: 等待数据...");
                                        item02.setStatusTextColor(getResources().getColor(R.color.colorGray));
                                    } else {//有数据
                                        if (pet.getStates().contains("poweroff")) {
                                            item02.setStatusText("状态: 关机");
                                            item02.setStatusTextColor(Color.LTGRAY);
                                        } else if (pet.getNbcsq() < minCsq ||
                                                pet.getBattery() == 0 ||
                                                pet.getLongitude() == 0 ||
                                                !(sw.equals(pet.getSVersion())) ||
                                                pet.getLatitude() == 0) {
                                            item02.setStatusText("状态: 失败");
                                            item02.setStatusTextColor(Color.RED);
                                        } else {
                                            item02.setStatusText("状态: 成功");
                                            item02.setStatusTextColor(Color.GREEN);
                                        }
                                        item02.setTimeText(TimeUtil.formatData("HH:mm:ss", pet.getTs()));
                                        item02.setCsqText("" + pet.getNbcsq());
                                        item02.setBatteryText("" + pet.getBattery());
                                        item02.setJingduText("" + pet.getLongitude());
                                        item02.setWeiduText("" + pet.getLatitude());
                                        item02.setSwText("" + pet.getSVersion());
                                        item02.setHwText("" + pet.getHVersion());
                                    }
                                    break;
                                case REQUEST_SCAN_CODE_03:
                                    if (TextUtils.isEmpty(pet.getIMEI())) {//无数据，返回{}
                                        item03.setStatusText("状态: 等待数据...");
                                        item03.setStatusTextColor(getResources().getColor(R.color.colorGray));
                                    } else {//有数据
                                        if (pet.getStates().contains("poweroff")) {
                                            item03.setStatusText("状态: 关机");
                                            item03.setStatusTextColor(Color.LTGRAY);
                                        } else if (pet.getNbcsq() < minCsq ||
                                                pet.getBattery() == 0 ||
                                                pet.getLongitude() == 0 ||
                                                !(sw.equals(pet.getSVersion())) ||
                                                pet.getLatitude() == 0) {
                                            item03.setStatusText("状态: 失败");
                                            item03.setStatusTextColor(Color.RED);
                                        } else {
                                            item03.setStatusText("状态: 成功");
                                            item03.setStatusTextColor(Color.GREEN);
                                        }
                                        item03.setTimeText(TimeUtil.formatData("HH:mm:ss", pet.getTs()));
                                        item03.setCsqText("" + pet.getNbcsq());
                                        item03.setBatteryText("" + pet.getBattery());
                                        item03.setJingduText("" + pet.getLongitude());
                                        item03.setWeiduText("" + pet.getLatitude());
                                        item03.setSwText("" + pet.getSVersion());
                                        item03.setHwText("" + pet.getHVersion());
                                    }
                                    break;
                                case REQUEST_SCAN_CODE_04:
                                    if (TextUtils.isEmpty(pet.getIMEI())) {//无数据，返回{}
                                        item04.setStatusText("状态: 等待数据...");
                                        item04.setStatusTextColor(getResources().getColor(R.color.colorGray));
                                    } else {//有数据
                                        if (pet.getStates().contains("poweroff")) {
                                            item04.setStatusText("状态: 关机");
                                            item04.setStatusTextColor(Color.LTGRAY);
                                        } else if (pet.getNbcsq() < minCsq ||
                                                pet.getBattery() == 0 ||
                                                pet.getLongitude() == 0 ||
                                                !(sw.equals(pet.getSVersion())) ||
                                                pet.getLatitude() == 0) {
                                            item04.setStatusText("状态: 失败");
                                            item04.setStatusTextColor(Color.RED);
                                        } else {
                                            item04.setStatusText("状态: 成功");
                                            item04.setStatusTextColor(Color.GREEN);
                                        }
                                        item04.setTimeText(TimeUtil.formatData("HH:mm:ss", pet.getTs()));
                                        item04.setCsqText("" + pet.getNbcsq());
                                        item04.setBatteryText("" + pet.getBattery());
                                        item04.setJingduText("" + pet.getLongitude());
                                        item04.setWeiduText("" + pet.getLatitude());
                                        item04.setSwText("" + pet.getSVersion());
                                        item04.setHwText("" + pet.getHVersion());
                                    }
                                    break;
                                case REQUEST_SCAN_CODE_05:
                                    if (TextUtils.isEmpty(pet.getIMEI())) {//无数据，返回{}
                                        item05.setStatusText("状态: 等待数据...");
                                        item05.setStatusTextColor(getResources().getColor(R.color.colorGray));
                                    } else {//有数据
                                        if (pet.getStates().contains("poweroff")) {
                                            item05.setStatusText("状态: 关机");
                                            item05.setStatusTextColor(Color.LTGRAY);
                                        } else if (pet.getNbcsq() < minCsq ||
                                                pet.getBattery() == 0 ||
                                                pet.getLongitude() == 0 ||
                                                !(sw.equals(pet.getSVersion())) ||
                                                pet.getLatitude() == 0) {
                                            item05.setStatusText("状态: 失败");
                                            item05.setStatusTextColor(Color.RED);
                                        } else {
                                            item05.setStatusText("状态: 成功");
                                            item05.setStatusTextColor(Color.GREEN);
                                        }
                                        item05.setTimeText(TimeUtil.formatData("HH:mm:ss", pet.getTs()));
                                        item05.setCsqText("" + pet.getNbcsq());
                                        item05.setBatteryText("" + pet.getBattery());
                                        item05.setJingduText("" + pet.getLongitude());
                                        item05.setWeiduText("" + pet.getLatitude());
                                        item05.setSwText("" + pet.getSVersion());
                                        item05.setHwText("" + pet.getHVersion());
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, " info onError() e=" + e.toString());
                        Toast.makeText(MainActivity.this, "infoApi error:网络异常", Toast.LENGTH_SHORT).show();
                        disposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, " info onComplete()");
                        disposable.dispose();
                    }
                });
    }

    private void showClearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确定要清空吗");
        builder.setMessage("清空后数据将不保留");

        //监听下方button点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //设置对话框是可取消的
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        isOngoing01 = false;
        isOngoing02 = false;
        isOngoing03 = false;
        isOngoing04 = false;
        isOngoing05 = false;
        mHandler.removeCallbacks(mRunnable01);
        mHandler.removeCallbacks(mRunnable02);
        mHandler.removeCallbacks(mRunnable03);
        mHandler.removeCallbacks(mRunnable04);
        mHandler.removeCallbacks(mRunnable05);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null == data || null == data.getExtras()) {
            return;
        }
        Bundle bundle = data.getExtras();
        switch (requestCode) {
            case REQUEST_SCAN_CODE_01:
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    imei01Str = result;
                    item01.setImeiText(imei01Str);
                    isOngoing01 = true;
                    item01.setScanButtonEnable(false);
                    item01.setStopButtonEnable(true);
                    mHandler.post(mRunnable01);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_SCAN_CODE_02:
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    imei02Str = result;
                    item02.setImeiText(imei02Str);
                    isOngoing02 = true;
                    item02.setScanButtonEnable(false);
                    item02.setStopButtonEnable(true);
                    mHandler.post(mRunnable02);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_SCAN_CODE_03:
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    imei03Str = result;
                    item03.setImeiText(imei03Str);
                    isOngoing03 = true;
                    item03.setScanButtonEnable(false);
                    item03.setStopButtonEnable(true);
                    mHandler.post(mRunnable03);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_SCAN_CODE_04:
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    imei04Str = result;
                    item04.setImeiText(imei04Str);
                    isOngoing04 = true;
                    item04.setScanButtonEnable(false);
                    item04.setStopButtonEnable(true);
                    mHandler.post(mRunnable04);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_SCAN_CODE_05:
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    imei05Str = result;
                    item05.setImeiText(imei05Str);
                    isOngoing05 = true;
                    item05.setScanButtonEnable(false);
                    item05.setStopButtonEnable(true);
                    mHandler.post(mRunnable05);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQEST_PERMISSION:
                // 权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                } else {
                    // 被禁止授权
                    Toast.makeText(MainActivity.this, "请至权限中心打开本应用访问权限", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            //具体的操作代码
            new AlertDialog.Builder(this)
                    .setTitle("确定退出程序么")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish();//
                        }
                    }).show();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
