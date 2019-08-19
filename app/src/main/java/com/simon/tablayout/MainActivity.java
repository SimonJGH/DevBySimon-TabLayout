package com.simon.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TabLayout mTablayout;
    ViewPager mVp_container;

    private String[] tabs = new String[]{"首页", "消息", "我的"};
    private int[] imgs = new int[]{R.drawable.selector_tab1, R.drawable.selector_tab2, R.drawable.selector_tab3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVp_container = findViewById(R.id.vp_container);
        mTablayout = findViewById(R.id.tablayout);

        initDatas();
    }

    private void initDatas() {
        // 设置TabLayout的模式
       // mTablayout.setTabMode(TabLayout.MODE_FIXED);//适合tab比较少的情况
        // mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合tab比较多的情况
        mVp_container.setOffscreenPageLimit(3);
        mVp_container.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new FirstFragment();
                    case 1:
                        return new SecondFragment();
                    case 2:
                        return new ThirdFragment();
                    default:
                        return new FirstFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }

            @Override
            public int getCount() {
                return tabs.length;
            }
        });
        mTablayout.setupWithViewPager(mVp_container);

        setDownTabs();
    }

    /**
     * 设置tab
     */
    private void setDownTabs() {
        mTablayout.getTabAt(0).setCustomView(getTabView(0));
        mTablayout.getTabAt(1).setCustomView(getTabView(1));
        mTablayout.getTabAt(2).setCustomView(getTabView(2));
    }

    /**
     * 自定义tab view
     *
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_custom_tab, null);
        ImageView iv_tab = (ImageView) inflate.findViewById(R.id.iv_tab);
        TextView tv_tab = (TextView) inflate.findViewById(R.id.tv_tab);
        iv_tab.setImageResource(imgs[position]);
        tv_tab.setText(tabs[position]);
        return inflate;
    }

}
