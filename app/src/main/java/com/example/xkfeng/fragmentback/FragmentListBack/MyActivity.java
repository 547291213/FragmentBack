package com.example.xkfeng.fragmentback.FragmentListBack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.xkfeng.fragmentback.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by initializing on 2018/7/25.
 */

public class MyActivity extends FragmentActivity {

    private FragmentManager fragmentManager ;
    private static int count = 0 ;
    private List<Fragment> fragmentList ;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity_main);

        fragmentList = new ArrayList<>() ;
    }


    public void BtnClick(View view)
    {
        MyFragment1 myFragment1 = new MyFragment1() ;
        Bundle bundle = new Bundle() ;
        bundle.putString("key" ,"FRAGMENT"+count++ );
        myFragment1.setArguments(bundle);
        fragmentList.add(myFragment1) ;
        fragmentManager = getSupportFragmentManager() ;
        FragmentTransaction transaction = fragmentManager.beginTransaction() ;
        if (fragmentList.size() > 1 )
        transaction.hide(fragmentList.get(fragmentList.size()-2)) ;
        transaction.add(R.id.fm_fragLayout , myFragment1) ;
        transaction.commit() ;
    }


    @Override
    public void onBackPressed() {

        if (!BackFragmnetHandle.handleBackPress(this))
        {
            Log.d(TAG, "onBackPressed: ");
            super.onBackPressed();
        }

        if (fragmentList.size() > 0)
        {
            FragmentTransaction transaction = fragmentManager.beginTransaction() ;
            transaction.remove(fragmentList.get(fragmentList.size()-1)) ;
            fragmentList.remove(fragmentList.size()-1) ;
            transaction.show(fragmentList.get(fragmentList.size()-1)) ;

            transaction.commit() ;
        }
    }
}
