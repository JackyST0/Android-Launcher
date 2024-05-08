package com.example.launcher_demo.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.launcher_demo.R;

import java.util.List;

/**
 * @author tjx
 * @date 2024/5/6 11:42
 */
public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private final List<ResolveInfo> mList;
    private final Context mContext;

    public AppAdapter(List<ResolveInfo> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.rv_item, parent, false);
        //作为一个view填充
        View view = View.inflate(parent.getContext(), R.layout.rv_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mIcon.setImageDrawable(mList.get(position).loadIcon(mContext.getPackageManager()));
        holder.mTtile.setText(mList.get(position).loadLabel(mContext.getPackageManager()));
        holder.itemView.setOnClickListener(v -> {
            Intent launchIntent = new Intent();
            launchIntent.setComponent(new ComponentName(mList.get(position).activityInfo.packageName,
                    mList.get(position).activityInfo.name));
            mContext.startActivity(launchIntent);
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mIcon;
        private final TextView mTtile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.iv);
            mTtile = itemView.findViewById(R.id.tv);
        }
    }
}
