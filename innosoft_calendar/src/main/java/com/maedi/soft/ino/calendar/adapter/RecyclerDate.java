package com.maedi.soft.ino.calendar.adapter;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.maedi.soft.ino.calendar.R;
import com.maedi.soft.ino.calendar.model.ListObject;
import com.maedi.soft.ino.calendar.view.RoundRectLayout;

import timber.log.Timber;

public class RecyclerDate extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = " - CALENDAR_MONTH_RecyclerDate - ";
    private Context context;
    private int layoutResourceId;
    private ListObject data = null;
    private String tag;
    private FragmentActivity f;

    public interface CommGetRecyclerDate
    {
        void setVal(String val);
    }

    private CommGetRecyclerDate listener;

    public RecyclerDate(Context context, FragmentActivity f, int layoutResourceId, CommGetRecyclerDate listener, ListObject data, String tag) {
        if(layoutResourceId != 0)this.layoutResourceId = layoutResourceId;
        this.context = context;
        Timber.d(TAG+"SIZE_DATE CTX -> "+data.size());
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

        //Timber.d(TAG+"SIZE_DATE -> "+data.size());
        if (data.size() > 0) {
            final ViewHolder vholder = (ViewHolder) vvholder;
            final ListObject lm = (ListObject) data.get(position);
            vholder.text.setText(lm.s1);

            if(!"".equalsIgnoreCase(lm.s1)) {
                final boolean isdigit = Character.isDigit(lm.s1.charAt(0));
                //Timber.d(TAG+"DATE_VAL A -> "+lm.s1);
                vholder.layout1.setRadius(40);

                if (isdigit) {
                    if (lm.bool) {
                        vholder.layout1.setBackgroundColor(f.getColor(R.color.default_blue));
                        vholder.layout1.setBorderColor(f.getColor(R.color.default_blue));
                        vholder.text.setTextColor(f.getColor(R.color.white));
                    } else {
                        vholder.layout1.setBackgroundColor(f.getColor(R.color.window_background));
                        vholder.layout1.setBorderColor(f.getColor(R.color.window_background));
                        vholder.text.setTextColor(f.getColor(R.color.em_black_42));
                    }
                } else {
                    vholder.layout1.setBackgroundColor(f.getColor(R.color.em_transparent));
                    vholder.layout1.setBorderColor(f.getColor(R.color.em_transparent));
                    if (lm.bool) {
                        vholder.text.setTextColor(f.getColor(R.color.em_red_54));
                    } else {
                        vholder.text.setTextColor(f.getColor(R.color.news_hours));
                    }
                }

                vholder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isdigit)
                        {
                            vholder.layout1.setBackgroundColor(f.getColor(R.color.red));
                            vholder.layout1.setBorderColor(f.getColor(R.color.red));
                            vholder.text.setTextColor(f.getColor(R.color.white));

                            RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,          Animation.RELATIVE_TO_SELF, 0.5f);
                            rotate.setDuration(500);
                            rotate.setInterpolator(new LinearInterpolator());
                            vholder.text.startAnimation(rotate);

                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    listener.setVal(lm.s1);
                                }
                            }, 550);
                        }
                    }
                });
            }
            else
            {
                //Timber.d(TAG+"DATE_VAL B -> "+lm.s1);
                vholder.layout1.setBackgroundColor(f.getColor(R.color.em_transparent));
                vholder.layout1.setBorderColor(f.getColor(R.color.em_transparent));
                vholder.text.setTextColor(f.getColor(R.color.em_transparent));
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        RoundRectLayout layout1;
        TextView text;

        public ViewHolder(View view) {
            super(view);
            this.layout1 = (RoundRectLayout) view.findViewById(R.id.item_layout);
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
        //Timber.d(TAG+"SIZE_DATE getItemCount -> "+data.size());
        return data.size();
    }

    public void updateListItems(ListObject lo) {
        data = lo;
        notifyDataSetChanged();
    }

}