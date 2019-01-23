package com.toune.platenum.platenumber;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class AdapterPlateNum extends BaseQuickAdapter<String, BaseViewHolder> {
    public AdapterPlateNum(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        LinearLayout linearLayout = helper.getView(R.id.root_layout);
        linearLayout.removeAllViews();
        if (!item.equals("del")) {
            TextView textView = new TextView(mContext);
            textView.setText(item);
            linearLayout.addView(textView);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCustomListener != null) {
                        onCustomListener.onAdd(item);
                    }
                }
            });
        } else {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.mipmap.ic_del);
            linearLayout.addView(imageView);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCustomListener != null) {
                        onCustomListener.onDel();
                    }
                }
            });
        }
    }

    OnCustomListener onCustomListener;

    public void setOnCustomListener(OnCustomListener onCustomListener) {
        this.onCustomListener = onCustomListener;
    }

    public interface OnCustomListener {
        void onAdd(String item);

        void onDel();
    }
}
