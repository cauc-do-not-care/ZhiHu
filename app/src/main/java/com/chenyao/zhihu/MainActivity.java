package com.chenyao.zhihu;

import android.app.ActionBar;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.chenyao.zhihu.R.id.fragment_container;

public class MainActivity extends AppCompatActivity implements
        MeFragment.OnFragmentInteractionListener ,
        NoticeFragment.OnFragmentInteractionListener,
        FirstpageFragment.OnFragmentInteractionListener,
        hotsearchFragment.OnFragmentInteractionListener,
        recommendFragment.OnFragmentInteractionListener,
        concernFragment.OnFragmentInteractionListener{
    private ActionBar actionBar;
    private EditText mTextMessage;


    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("首页");
                    FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                    FirstpageFragment firstpageFragment = new FirstpageFragment();
                    fragmentTransaction1.add(R.id.fragment_container,firstpageFragment);
                    fragmentTransaction1.replace(R.id.fragment_container,firstpageFragment);
                    fragmentTransaction1.addToBackStack(null);
                    fragmentTransaction1.commit();
                    return true;
                case R.id.navigation_notice:
                    mTextMessage.setText("通知");
                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    NoticeFragment noticeFragment = new NoticeFragment();
                    fragmentTransaction2.add(R.id.fragment_container, noticeFragment);
                    fragmentTransaction2.replace(R.id.fragment_container, noticeFragment);
                    fragmentTransaction2.addToBackStack(null);
                    fragmentTransaction2.commit();
                    return true;
                case R.id.navigation_me:
                    mTextMessage.setText("我的");
                    FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                    MeFragment meFragment = new MeFragment();
                    fragmentTransaction3.add(R.id.fragment_container, meFragment);
                    fragmentTransaction3.replace(R.id.fragment_container, meFragment);
                    fragmentTransaction3.addToBackStack(null);
                    fragmentTransaction3.commit();
                    return true;
            }
            return false;
        }
    };
    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(MainActivity.this,"this is:"+uri,Toast.LENGTH_LONG).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (EditText) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toplook,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.action_bar_concern:
                mTextMessage.setText("关注");
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                concernFragment concernFragment = new concernFragment();
                fragmentTransaction1.add(R.id.fragment_container, concernFragment);
                fragmentTransaction1.replace(R.id.fragment_container, concernFragment);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
                return true;
            case R.id.action_bar_recommend:
                mTextMessage.setText("推荐");
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                recommendFragment recommendFragment = new recommendFragment();
                fragmentTransaction2.add(R.id.fragment_container, recommendFragment);
                fragmentTransaction2.replace(R.id.fragment_container, recommendFragment);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                return true;
            case R.id.action_bar_hotsearch:
                mTextMessage.setText("热搜");
                FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                hotsearchFragment hotsearchFragment = new hotsearchFragment();
                fragmentTransaction3.add(R.id.fragment_container, hotsearchFragment);
                fragmentTransaction3.replace(R.id.fragment_container, hotsearchFragment);
                fragmentTransaction3.addToBackStack(null);
                fragmentTransaction3.commit();
                return true;
        }
        return false;
    }

}

