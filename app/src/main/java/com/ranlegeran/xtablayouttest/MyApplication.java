package com.ranlegeran.xtablayouttest;

import android.app.Application;

import org.xutils.x;

/**
 * 创建日期：2019/9/20 2:22
 * 作　者: RANLEGERAN 刘军龙
 * 类　名:
 * 简　述:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
