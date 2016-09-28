package com.sunchao.study.slidingdissmisstest;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements SlidingPaneLayout.PanelSlideListener, View.OnClickListener {
    private Context context;
    private List<String> list;
    private RecyclerView recyclerView;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        ((SlidingPaneLayout) view).setPanelSlideListener(this);
        view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_item.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        holder.tv_item.setText(list.get(position));
        holder.btn_delete.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        View btn = ((View) panel.getParent()).findViewById(R.id.btn_delete);
        btn.setTranslationX(btn.getWidth() * (1 - slideOffset));
        Log.e("自定义标签", "类名==MyAdapter" + "方法名==onPanelSlide=====:" + slideOffset);
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        ((SlidingPaneLayout) holder.tv_item.getParent()).closePane();
    }


    @Override
    public void onPanelOpened(View panel) {

    }

    @Override
    public void onPanelClosed(View panel) {

    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(((View) v.getParent()));
        list.remove(position);
        notifyItemRemoved(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_item;
        private final Button btn_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item = ((TextView) itemView.findViewById(R.id.tv_item));
            btn_delete = ((Button) itemView.findViewById(R.id.btn_delete));
        }
    }
}
