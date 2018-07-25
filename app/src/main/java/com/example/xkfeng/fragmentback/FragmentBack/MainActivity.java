package com.example.xkfeng.fragmentback.FragmentBack;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xkfeng.fragmentback.FragmentBack.BackFragment;
import com.example.xkfeng.fragmentback.FragmentBack.BackHandleFragment;
import com.example.xkfeng.fragmentback.FragmentBack.MyFragment;
import com.example.xkfeng.fragmentback.R;

public class MainActivity extends FragmentActivity implements BackHandleFragment {

    private BackFragment backFragment;
    private boolean intercept;
    private Button bt_Btn ;
    private FragmentManager fm ;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager() ;
        bt_Btn = (Button)findViewById(R.id.bt_Btn) ;
        bt_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyFragment myFragment = new MyFragment() ;
                FragmentTransaction transaction =  fm.beginTransaction() ;
                transaction.replace(R.id.fm_fragLayout , myFragment) ;
                transaction.addToBackStack("tag") ;
                transaction.commit() ;
            }
        });
    }

    /*
      复写Activity的onBackPress方法，该方法会在Activity返回的时候调用
     */
    @Override
    public void onBackPressed() {

        /*
           当Fragment对象为null ， 或者当前fragment把返回事件消费  的时候需要进行特殊处理。
         */
        if (backFragment == null || !backFragment.onBackPressed()) {
            /*
                  当前fragment栈中对象为0，此时可以直接调用Activity默认的onBackPressed方法来处理
             */
            Log.d(TAG, "onBackPressed: getSupportFragmentManager().popBackStack(); " + getSupportFragmentManager().getBackStackEntryCount() );

            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                Log.d(TAG, "onBackPressed: super.onBackPressed() ");
                super.onBackPressed();
                
            }
            //当前fragment栈中对象部位0 ， 此时只需要弹出栈挺度对象即可。
            else {
                getSupportFragmentManager().popBackStack();
                Log.d(TAG, "onBackPressed: getSupportFragmentManager().popBackStack(); " + getSupportFragmentManager().getBackStackEntryCount() );

            }
        }
    }

    /*
           绑定BackFragment对象
         */
    @Override
    public void setSelectedFragemnt(BackFragment backFragment) {
        this.backFragment = backFragment;
    }
}
