package com.ledboot.nicechat.launcher;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ledboot.nicechat.launcher.bean.SessionBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public class SessionTab extends HomeTab {

    Toolbar mToolbar;
    RecyclerView mRecycler;

    public static final String TAG = MainPanel.TAG_SESSION;

    private static RecycAdapter mAdapter;


    public SessionTab(Context context) {
        super(context);
    }

    @Override
    protected void setFacade() {
        mLabel = "聊天";
    }

    @Override
    protected String getTabTag() {
        return TAG;
    }

    @Override
    protected void onCreate() {
        ButterKnife.bind((Activity) mContext);
    }

    @Override
    protected View onCreateTitleView(ViewGroup parent, LayoutInflater inflater) {
        setTitleVisible(true);
        View view = inflater.inflate(R.layout.session_title, null);
        mToolbar = (Toolbar) view.findViewById(R.id.id_toolbar);
        Menu menu = mToolbar.getMenu();
        menu.add(1, 1, 1, "setting").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        mToolbar.setTitle("聊天（20）");
        mToolbar.setNavigationIcon(null);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return view;
    }

    @Override
    protected View onCreateContentView(ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.session_tab, null);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler);
        List<SessionBean> list = createData();
        if(mAdapter == null){
            mAdapter = new RecycAdapter(list,mContext);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(layoutManager);
        return view;
    }

    private List<SessionBean> createData(){
        List<SessionBean> list = new ArrayList<>();
        list.add(new SessionBean("title1","","content1","2010-10-10"));
        list.add(new SessionBean("title2","","content2","9:10 AM"));
        list.add(new SessionBean("title3","","content3","12:10 AM"));
        list.add(new SessionBean("title4","","content4","11:10 PM"));
        return list;
    }

    @Override
    protected void recycle() {

    }

    class RecycAdapter extends RecyclerView.Adapter<SessionHolder> {

        private List<SessionBean> mList;
        private Context mContext;

        private RecycAdapter(List<SessionBean> list, Context context) {
            this.mList = list;
            this.mContext = context;
        }


        @Override
        public SessionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.session_item, null);
            SessionHolder holder = new SessionHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(SessionHolder holder, int position) {
            SessionBean bean = mList.get(position);
            holder.contentTv.setText(bean.getContent());
            holder.timeTv.setText(bean.getShowTime());
            holder.titleTv.setText(bean.getTitle());
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }
    }

    class SessionHolder extends RecyclerView.ViewHolder {

        ImageView iconIv;
        TextView titleTv;
        TextView contentTv;
        TextView timeTv;


        public SessionHolder(View itemView) {
            super(itemView);
            iconIv = (ImageView) itemView.findViewById(R.id.icon);
            titleTv = (TextView) itemView.findViewById(R.id.title);
            contentTv = (TextView) itemView.findViewById(R.id.content);
            timeTv = (TextView) itemView.findViewById(R.id.time);
        }
    }

}
