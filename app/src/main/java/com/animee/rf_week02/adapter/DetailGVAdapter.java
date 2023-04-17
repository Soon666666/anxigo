package com.animee.rf_week02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.animee.rf_week02.R;
import com.animee.rf_week02.bean.ContentDatas;
import com.animee.rf_week02.bean.InfoBean;
import com.animee.rf_week02.view.AmountView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailGVAdapter extends BaseAdapter {
    Context context;
    List<InfoBean> mDatas;

    public DetailGVAdapter(Context context, List<InfoBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        VHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_detailgv, viewGroup, false);
            holder = new VHolder(view);
            view.setTag(holder);
        } else {
            holder = (VHolder) view.getTag();
        }
        InfoBean bean = mDatas.get(i);

        holder.titleTv.setText(bean.getTitle());
        holder.priceTv.setText("￥ " + bean.getPrice());

        holder.amountView.setShowCount(1);
        holder.amountView.setStorage(bean.getCount());
        // 设置按钮的监听事件
        VHolder finalHolder = holder;
        holder.buyBtn.setOnClickListener(view1 -> {
            int amountNum = finalHolder.amountView.getAmountNum();
            InfoBean newBean = InfoBean.copy(bean);
            newBean.setBuycount(amountNum);
            ContentDatas.addGoodsToBuyList(newBean);
        });
        return view;
    }

    class VHolder {
        TextView titleTv, priceTv;
        AmountView amountView;
        ImageView iv;
        Button buyBtn;

        public VHolder(View v) {
            titleTv = v.findViewById(R.id.item_dg_tv_title);
            priceTv = v.findViewById(R.id.item_dg_tv_price);
            amountView = v.findViewById(R.id.item_dg_av);
            iv = v.findViewById(R.id.item_dg_iv);
            buyBtn = v.findViewById(R.id.item_dg_btn_buy);
        }

    }
}
