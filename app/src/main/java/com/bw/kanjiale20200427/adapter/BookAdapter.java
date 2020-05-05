package com.bw.kanjiale20200427.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.kanjiale20200427.R;
import com.bw.kanjiale20200427.entity.BookEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.jetbrains.annotations.Contract;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2020/04/27<p>
 * <p>更改时间：2020/04/27<p>
 * <p>版本号：1<p>
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.VH> {


    private final Context mContext;
    private final List<BookEntity.DataBean.ContentBean> mContent;

    public BookAdapter(Context context, List<BookEntity.DataBean.ContentBean> content) {
        mContext = context;
        mContent = content;

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(View.inflate(mContext, R.layout.item_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        BookEntity.DataBean.ContentBean contentBean = mContent.get(position);

        holder.mSdv.setImageURI(contentBean.getBook_cover());
        holder.mTvName.setText(contentBean.getBookname());
        holder.mTvPrice.setText("￥：" + contentBean.getPrice());
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        private SimpleDraweeView mSdv;
        private TextView mTvName;
        private TextView mTvPrice;

        public VH(@NonNull View itemView) {
            super(itemView);
            mSdv = itemView.findViewById(R.id.sdv);
            mTvName = itemView.findViewById(R.id.name);
            mTvPrice = itemView.findViewById(R.id.price);
        }
    }
}
