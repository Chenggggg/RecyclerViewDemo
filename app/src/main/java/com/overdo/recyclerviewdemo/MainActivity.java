package com.overdo.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int WATERFALL_MODE = 1;
    @InjectView(R.id.btn_item)
    Button mBtnItem;
    @InjectView(R.id.btn_grid)
    Button mBtnGrid;
    @InjectView(R.id.btn_waterfall)
    Button mBtnWaterfall;
    @InjectView(R.id.btn_add)
    Button mBtnAdd;
    @InjectView(R.id.btn_delete)
    Button mBtnDelete;
    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @InjectView(R.id.activity_main)
    LinearLayout mActivityMain;
    private List mDatas;
    private RecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initData();

        mRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        /**
         * 如果要加分界线的话
         */
        mRecyclerview.addItemDecoration(
                new DividerGridItemDecoration(this));
        mRecyclerview.setAdapter(mAdapter = new RecyclerViewAdapter(this, mDatas,0));

        /**
         * 设置动画效果
         */
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

    }

    protected void initData() {
        mDatas = new ArrayList();
        for (int i = 0; i < Cheeses.sCheeseStrings.length; i++) {
            mDatas.add(Cheeses.sCheeseStrings[i]);
        }
    }


    @OnClick({R.id.btn_item, R.id.btn_grid, R.id.btn_waterfall, R.id.btn_add, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_item:

                mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                break;

            case R.id.btn_grid:

                mRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
                break;

            case R.id.btn_waterfall:

                mRecyclerview.setLayoutManager(new
                        StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                mRecyclerview.setAdapter(mAdapter = new RecyclerViewAdapter(this, mDatas, WATERFALL_MODE));

                break;

            case R.id.btn_add:

                mAdapter.addData(2);

                break;

            case R.id.btn_delete:

                mAdapter.removeData(3);
                break;
        }

    }
}
