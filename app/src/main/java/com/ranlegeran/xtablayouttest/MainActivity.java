package com.ranlegeran.xtablayouttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.androidkun.xtablayout.XTabLayout;
import com.ranlegeran.xtablayouttest.fragment.TestFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private XTabLayout mXTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mXTabLayout = (XTabLayout) findViewById(R.id.xtab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        initData();
    }

    private void initData() {
        RequestParams params = new RequestParams("https://www.apiopen.top/femaleNameApi?page=1");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG,result);
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONArray jsonArray = obj.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length();i++) {
                        obj = jsonArray.getJSONObject(i);
                        String femalename = obj.getString("femalename");
                        mTitleList.add(femalename);
                    }
                    mXTabLayout.setTabMode(XTabLayout.MODE_SCROLLABLE);
                    for (int i = 0; i < mTitleList.size(); i++) {
                        TestFragment fragment = TestFragment.newInstance(mTitleList.get(i));
                        mFragments.add(fragment);
                    }

                    for (int i = 0; i < mTitleList.size(); i++) {
                        mXTabLayout.addTab(mXTabLayout.newTab().setText(mTitleList.get(i)));
                    }
                    FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return mFragments.get(position);
                        }

                        @Override
                        public int getCount() {
                            return mFragments.size();
                        }

                        @Override
                        public CharSequence getPageTitle(int position) {
                            return mTitleList.get(position);
                        }
                    };
                    mViewPager.setAdapter(adapter);
                    mXTabLayout.setupWithViewPager(mViewPager);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
