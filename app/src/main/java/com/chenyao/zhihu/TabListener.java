package com.chenyao.zhihu;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

public class TabListener<T extends Fragment> implements ActionBar.TabListener, android.app.ActionBar.TabListener {
    private Fragment mFragment;
    private  Activity mActivity;
    private  String mTag;
    private  Class<T> mClass;
    public TabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
    }
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Check if the fragment is already initialized
        if (mFragment == null) {
            // If not, instantiate and add it to the activity
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            fragmentTransaction.add(android.R.id.content, mFragment, mTag);
        } else {
            // If it exists, simply attach it in order to show it
            fragmentTransaction.attach(mFragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (mFragment != null) {
            // Detach the fragment, because another one is being attached
            fragmentTransaction.detach(mFragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }
}
