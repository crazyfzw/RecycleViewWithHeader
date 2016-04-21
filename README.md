# RecycleViewWithHeader
为RecycleView添加其他View组件实现复杂布局

-运行截图

![images](https://github.com/crazyfzw/ProjectImages/blob/master/RecycleViewWithHeader/a.jpg)


-主要思想是根据不同的ItemType去加载不同的布局


    public int getItemViewType(int position){

        return position % 5 == 0 ? ITEM_TYPE.ITEM_TYPE_Theme.ordinal() : ITEM_TYPE.ITEM_TYPE_Video.ordinal();
    }


 public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.ITEM_TYPE_Theme.ordinal()){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tab1_themelist,parent,false);

            return new ThemeVideoHolder(view);

        }else if(viewType == ITEM_TYPE.ITEM_TYPE_Video.ordinal()){

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videocardview,parent,false);
            return new VideoViewHolder(view);

        }
          return null;
    }
    
    

    
    重写onAttachedToRecyclerView方法，在里面通过getSpanSize方法判断并设置当前item项应该占据多少个单元格
    从而避免添加的不同View在GridRecyclerView中以cell显示 
    
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
