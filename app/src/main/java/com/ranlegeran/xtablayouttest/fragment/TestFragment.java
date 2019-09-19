package com.ranlegeran.xtablayouttest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ranlegeran.xtablayouttest.R;

/**
 * 创建日期：2019/9/20 2:29
 * 作　者: RANLEGERAN 刘军龙
 * 类　名:
 * 简　述:
 */
public class TestFragment extends Fragment {
    private static final String KEY = "title";
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_layout,container,false);
        mTextView =  view.findViewById(R.id.textView);
        String str = getArguments().getString(KEY);
        mTextView.setText(str);
        return view;
    }

    public static TestFragment newInstance(String str){
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,str);
        fragment.setArguments(bundle);
        return fragment;
    }
}
