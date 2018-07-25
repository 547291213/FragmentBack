package com.example.xkfeng.fragmentback.FragmentListBack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.List;

/**
 * Created by initializing on 2018/7/25.
 */

public class BackFragmnetHandle {

    private static final String TAG = "BackFragmnetHandle";
    /*

          当前处理back的时候，可能不只是一个fragmnet，而是一个fragment列表
          其中需要铭记的是：FragmengActivity会持有Activity和嵌套Fragment中的Fragment对象。所以我们通过遍历处理
          具体步骤：1 反向遍历FrgamentManager中的fragment对象。查询其是否处理了onBackPressed事件。
                   2 如果子fragment都没有处理，则需要调用
                          if (fragmentManager.getBackStackEntryCount() > 0) {
                               fragmentManager.popBackStack();
                               return true;
                             }
                  3 如果2仍然没有返回则返回false

           注：返回true 表示处理   返回false 表示未处理
     */
    public static boolean handleBackPress(FragmentManager fragmentManager)
    {
        Log.d(TAG, "handleBackPress: onexecute" );
        //通过fragmentManager获取fragment列表
        List<Fragment> fragmentList = fragmentManager.getFragments() ;

        //如果fragment列表为空，则直接返回false
        if (fragmentList == null)  return false ;

        for (int i = fragmentList.size() - 1 ; i>0 ; i--)
        {
            Fragment child = fragmentList.get(i) ;
            if (isHandledFragment(child))
            {
                Log.d(TAG, "handleBackPress: " + "isHandledFragment i : " + i);
                return true ;
            }
        }

        if (fragmentManager.getBackStackEntryCount() > 0)
        {
            fragmentManager.popBackStackImmediate();
            Log.d(TAG, "handleBackPress: " + "fragmentManager.getBackStackEntryCount() : " + fragmentManager.getBackStackEntryCount());
            return true ;
        }

        return false ;

    }

    /*
       处理fragment嵌套的情况
     */
    public static boolean handleBackPress(Fragment fragment)
    {
        return handleBackPress(fragment.getChildFragmentManager()) ;
    }

    /*
       处理Activity中存在多个fragment的情况
     */
    public static boolean handleBackPress(FragmentActivity activity)
    {
        return handleBackPress(activity.getSupportFragmentManager()) ;
    }


    /*
        判断F让干嘛呢他是否处理了back事件
        处理了返回 true
        没处理返回 false
     */
    public static boolean isHandledFragment(Fragment fragment)
    {
        return fragment != null       //fragment不能为空
                && fragment.isVisible()   //fragment可见，表示位于fragmnent栈顶
                && fragment.getUserVisibleHint()  //ViewPager属性
                && fragment instanceof FragmentBackHandler  //fragmnent继承自通用接口
                && ((FragmentBackHandler)fragment).onBackPressed() ; //调用fragment的onBackPressed方法，来处理返回事件
    }
}
