package com.example.xkfeng.fragmentback.FragmentBack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by initializing on 2018/7/21.
 */

public abstract class BackFragment extends Fragment {

    protected BackHandleFragment backHandleFragment ;

    /*
        所有继承自BackFragment的类，都将在这方法中实现，返回按钮按下时应该实现的逻辑
        FragmentActivity捕捉到该事件后首先会询问Fragment是否消费该事件
        如果否，才会交给Activity处理
     */
    public abstract boolean onBackPressed() ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof BackHandleFragment))
        {
            throw new IllegalArgumentException("Activity is not instance of BackHandleFragment") ;
        }else {
            //动态绑定对象
            this.backHandleFragment = (BackHandleFragment)getActivity() ;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //告诉FragmentActivity  ，当前Fragment在栈顶
        backHandleFragment.setSelectedFragemnt(this);
    }
}
