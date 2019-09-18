package com.toune.eleven.platenumber;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public abstract class PlateNumDialog {
    private Display display;
    TextView cancleTv;
    TextView sureTv;
    RecyclerView rcyView;
    private Dialog dialog;
    private int layoutId;
    private View view;

    private List<String> datas = new ArrayList<>();
    private AdapterPlateNum adapterPlateNum;
    private StringBuilder plateNum;
    private Context context;
    private TextView plateNumTv;

    public static String[] PROVINCE_CODE = new String[]{"京", "津", "沪", "渝", "冀", "豫", "鲁", "晋", "陕", "皖", "苏", "浙", "鄂", "湘", "赣", "闽", "粤", "桂", "琼", "川", "贵", "云", "辽", "吉", "黑", "蒙", "甘", "宁", "青", "新", "藏", "港", "澳", "台", "", "del"};
    public static String[] PLATE_NUM = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "挂", "del"};

    public PlateNumDialog(Context context, StringBuilder plateNum) {
        this.context = context;
        this.plateNum = plateNum;
        if (this.plateNum == null) {
            this.plateNum = new StringBuilder();
        }
        @SuppressLint("WrongConstant") WindowManager windowManager = (WindowManager) context.getSystemService("window");
        display = windowManager.getDefaultDisplay();
    }

    public PlateNumDialog(Context context, String plateNum) {
        this.context = context;
        if (plateNum == null) {
            this.plateNum = new StringBuilder();
        } else {
            this.plateNum = new StringBuilder(plateNum);
        }
        @SuppressLint("WrongConstant")
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        display = windowManager.getDefaultDisplay();
    }

    public PlateNumDialog builder() {
        view = LayoutInflater.from(this.context).inflate(R.layout.dialog_plate_num, (ViewGroup) null);
        view.setMinimumWidth(this.display.getWidth());
        rcyView = view.findViewById(R.id.rcy_view);
        cancleTv = view.findViewById(R.id.cancle_tv);
        sureTv = view.findViewById(R.id.sure_tv);
        plateNumTv = view.findViewById(R.id.plate_num_tv);
        if (plateNum.length() > 0) {
            plateNumTv.setText(plateNum);
        }
        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancle();
                dialog.dismiss();
            }
        });
        sureTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plateNum.length() != 7) {
                    Toast.makeText(context, "请输入正确的车牌号", Toast.LENGTH_LONG).show();
                } else {
//                    RxToast.info(plateNum.toString());
                    onDone(plateNum);
                    dialog.dismiss();
                }
            }
        });
        this.dialog = new Dialog(this.context);
        this.dialog.setContentView(view);
        Window dialogWindow = this.dialog.getWindow();
        dialogWindow.setGravity(83);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        initView();
        return this;
    }


    private void initView() {
        initDatas();
        notifyAdapter();
    }

    private void initDatas() {
        if (plateNum.length() == 0) {
            datas.clear();
            for (int i = 0; i < PROVINCE_CODE.length; i++) {
                datas.add(PROVINCE_CODE[i]);
            }
        } else if (plateNum.length() >= 1) {
            datas.clear();
            for (int i = 0; i < PLATE_NUM.length; i++) {
                datas.add(PLATE_NUM[i]);
            }
        }
    }

    private void notifyAdapter() {
        initDatas();
        if (adapterPlateNum == null) {
            adapterPlateNum = new AdapterPlateNum(R.layout.adapter_plate_num, datas);
            rcyView.setLayoutManager(new GridLayoutManager(context, 6));
//            rcyView.addItemDecoration(new DividerGridItemDecoration(getContext()),1);
            //添加Android自带的分割线
            rcyView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
            rcyView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
            rcyView.setAdapter(adapterPlateNum);
            adapterPlateNum.setOnCustomListener(new AdapterPlateNum.OnCustomListener() {
                @Override
                public void onAdd(String item) {
                    if (plateNum.length() < 7) {
                        plateNum.append(item);
                        onPlateNum(plateNum);
                    }
                    notifyAdapter();
                }

                @Override
                public void onDel() {
                    if (plateNum.length() > 0) {
                        plateNum.deleteCharAt(plateNum.length() - 1);
                        onPlateNum(plateNum);
                    }
                    notifyAdapter();
                }
            });
        } else {
            adapterPlateNum.notifyDataSetChanged();
        }
    }

    public PlateNumDialog show() {
        this.dialog.show();
        return this;
    }

    public void onPlateNum(StringBuilder str) {
        plateNumTv.setText(str);
    }

    public abstract void onDone(StringBuilder str);

    public abstract void onCancle();
}
