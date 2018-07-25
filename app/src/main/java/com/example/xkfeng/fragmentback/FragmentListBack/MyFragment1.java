package com.example.xkfeng.fragmentback.FragmentListBack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xkfeng.fragmentback.R;

/**
 * Created by initializing on 2018/7/25.
 */

public class MyFragment1 extends Fragment implements FragmentBackHandler {

   private boolean isExit ;
    private TextView textView ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item2 , container , false) ;
        textView =  view.findViewById(R.id.tv_tView);
        String str = getArguments().getString("key" , "TextView") ;
        textView.setText(str);

        return view;
    }

    @Override
    public boolean onBackPressed() {
        if (isExit)
        {
            return false ;
        }
        isExit = true ;
        Toast.makeText(getContext(), "退出Fragment", Toast.LENGTH_SHORT).show();
        return true;
    }
}
