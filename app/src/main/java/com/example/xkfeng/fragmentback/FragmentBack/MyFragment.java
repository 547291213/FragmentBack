package com.example.xkfeng.fragmentback.FragmentBack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xkfeng.fragmentback.R;

/**
 * Created by initializing on 2018/7/21.
 */

/*
  Fragment返回实现
  实现方法：
    1  定义接口 ，接口指定当前栈顶的Fragment的对象，
    2  定义抽象类继承Fragment，
      （1），用getActivity和instanceof来判断该Activity是否继承自1中的接口
      （2），自定义抽象方法，用于处理在Fragment中的返回事件。
    3  Fragment的实现，需要继承2中的抽象类，实现其中的抽象方法，作为具体的返回操作。
    4  实现Activity，
      （1），继承FragmentActivity，继承1中接口。
      （2），重写onBackPressed方法，在该方法中，需要判断当前Fragment是否为空，
            我们需要调用Fragment中处理返回事件的方法。
            处理过程中需要调用getSupportFragmentManager().getBackStackEntryCount()来判断当前Fragment栈中对象数目，为0则直接调用Activity的默认方法，
            大于0则执行 弹出栈的方法。

   实现原理：
     1 ，利用Fragment的生命周期，再Fragmeng显示时通知到Activity，并且让Activity保持
     2 ，当用户按下Back按钮的时候，将返回事件交给Fragmeng处理
     3 ，如果Fagment没有处理则交给Activity处理

   存在的问题：
     1 ，只适用于一个Activity只有一个Fragmeng的情况
     2 ，只适用于Fragment没有嵌套的情况
 */
public class MyFragment extends BackFragment {

    private boolean isIncepted ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item , container , false);
    }

    @Override
    public boolean onBackPressed() {
        if(isIncepted){
            return false;
        }else{
            Toast.makeText(getActivity(), "Click From MyFragment", Toast.LENGTH_SHORT).show();
            isIncepted = true;
            return true;
        }
    }
}
