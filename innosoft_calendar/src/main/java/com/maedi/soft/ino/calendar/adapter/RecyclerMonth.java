package com.maedi.soft.ino.calendar.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maedi.soft.ino.calendar.R;
import com.maedi.soft.ino.calendar.model.ListObject;

public class RecyclerMonth extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int layoutResourceId;
    private ListObject data = null;
    private String tag;
    private FragmentActivity f;

    public interface CommGetRecyclerMonth
    {
        void setData(String data, String data2);
    }

    private CommGetRecyclerMonth listener;

    public RecyclerMonth(Context context, FragmentActivity f, int layoutResourceId, CommGetRecyclerMonth listener, ListObject data, String tag) {
        if(layoutResourceId != 0)this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.tag = tag;
        this.f = f;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder vvholder, final int position) {

        if (data.size() > 0) {
            final ViewHolder vholder = (ViewHolder) vvholder;
            final ListObject lm = (ListObject) data.get(position);

            vholder.text.setText(lm.s1);

            if(lm.bool)
            {
                vholder.text.setTextColor(f.getColor(R.color.blue_nstyle_ok));
            }
            else
            {
                vholder.text.setTextColor(f.getColor(R.color.news_text));
            }

            vholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setData(lm.s1, lm.s2);
                }
            });
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layout1;
        TextView text;

        public ViewHolder(View view) {
            super(view);
            this.layout1 = (LinearLayout) view.findViewById(R.id.item_parent_layout);
            this.text = (TextView) view.findViewById(R.id.item_text);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateListItems(ListObject lo) {
        data = lo;
        notifyDataSetChanged();
    }

}