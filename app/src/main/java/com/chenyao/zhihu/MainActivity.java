package com.chenyao.zhihu;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 主界面Activity
 * 注意：MainActivity仅作为调度Fragment使用
 * 请不要向这里添加任何控件，因为一旦切换Fragment它们将会被覆盖。
 */
public class MainActivity extends AppCompatActivity implements
        MeFragment.OnFragmentInteractionListener ,
        NoticeFragment.OnFragmentInteractionListener,
        MainPageFragment.OnFragmentInteractionListener,
        HotSearchFragment.OnFragmentInteractionListener,
        RecommendFragment.OnFragmentInteractionListener,
        ConcernFragment.OnFragmentInteractionListener{

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                    MainPageFragment firstpageFragment = new MainPageFragment();
                    fragmentTransaction1.replace(R.id.fragment_container,firstpageFragment);
                    fragmentTransaction1.commit();
                    return true;
                case R.id.navigation_notice:
                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    NoticeFragment noticeFragment = new NoticeFragment();
                    fragmentTransaction2.replace(R.id.fragment_container, noticeFragment);
                    fragmentTransaction2.commit();
                    return true;
                case R.id.navigation_me:
                    FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                    MeFragment meFragment = new MeFragment();
                    fragmentTransaction3.replace(R.id.fragment_container, meFragment);
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
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        启动时自动加载首页Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainPageFragment firstpageFragment = new MainPageFragment();
        fragmentTransaction.replace(R.id.fragment_container,firstpageFragment);
        fragmentTransaction.commit();
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
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                ConcernFragment concernFragment = new ConcernFragment();
                fragmentTransaction1.replace(R.id.fragment_container, concernFragment);
                fragmentTransaction1.commit();
                return true;
            case R.id.action_bar_recommend:
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                RecommendFragment recommendFragment = new RecommendFragment();
                fragmentTransaction2.replace(R.id.fragment_container, recommendFragment);
                fragmentTransaction2.commit();
                return true;
            case R.id.action_bar_hotsearch:
                FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                HotSearchFragment hotsearchFragment = new HotSearchFragment();
                fragmentTransaction3.replace(R.id.fragment_container, hotsearchFragment);
                fragmentTransaction3.commit();
                return true;
        }
        return false;
    }

}

