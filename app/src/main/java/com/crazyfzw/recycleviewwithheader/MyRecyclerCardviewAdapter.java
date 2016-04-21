package com.crazyfzw.recycleviewwithheader;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Crazyfzw on 2016/4/16.
 */
public class MyRecyclerCardviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static enum ITEM_TYPE {
        ITEM_TYPE_Theme,
        ITEM_TYPE_Video
    }
    //数据集
    public List<Integer> mdatas;
    private TextView themeTitle;

    public MyRecyclerCardviewAdapter(List<Integer> datas){
        super();
        this.mdatas = datas;
    }


    public static interface  OnItemClickListener{
        void onItemClick(View view, int positon);
    }


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.ITEM_TYPE_Theme.ordinal()){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videothemelist,parent,false);

            return new ThemeVideoHolder(view);

        }else if(viewType == ITEM_TYPE.ITEM_TYPE_Video.ordinal()){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videocardview,parent,false);
            return new VideoViewHolder(view);

        }
          return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (holder instanceof ThemeVideoHolder){

           themeTitle.setText("励志");

        }else if (holder instanceof VideoViewHolder){
            ((VideoViewHolder)holder).videologo.setImageResource(R.drawable.lianzai_02);
            ((VideoViewHolder)holder).videovname.setText("励志，俄小伙练习街头健身一年的体型变化，Dear Hard Work！");
            ((VideoViewHolder)holder).videoviewed.setText("2780次");
            ((VideoViewHolder)holder).videocomment.setText("209条");

            if (mOnItemClickListener!=null){
                ((VideoViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(((VideoViewHolder)holder).videologo, position);
                    }
                });
            }
        }

    }


    public int getItemViewType(int position){

        return position % 5 == 0 ? ITEM_TYPE.ITEM_TYPE_Theme.ordinal() : ITEM_TYPE.ITEM_TYPE_Video.ordinal();
    }




    @Override
    public int getItemCount() {
        return mdatas.size();
    }


    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_Theme.ordinal()
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }


    public class ThemeVideoHolder extends RecyclerView.ViewHolder{

        public ThemeVideoHolder(View itemView) {
            super(itemView);
            themeTitle = (TextView) itemView.findViewById(R.id.hometab1_theme_title);
        }
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public ImageView videologo;
        public TextView videovname;
        public TextView videoviewed;
        public TextView videocomment;

        public VideoViewHolder(View itemView) {
            super(itemView);
            videologo = (ImageView) itemView.findViewById(R.id.videologo);
            videoviewed = (TextView) itemView.findViewById(R.id.videoviewed);
            videocomment = (TextView) itemView.findViewById(R.id.videocomment);
            videovname = (TextView) itemView.findViewById(R.id.videoname);
        }
    }
}

