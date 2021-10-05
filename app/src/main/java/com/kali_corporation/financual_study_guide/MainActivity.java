package com.kali_corporation.financual_study_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.kali_corporation.financual_study_guide.adapter.PagerAdapter;

import static com.kali_corporation.financual_study_guide.fragment.HomeFragment.homeRecyclerView;
import static com.kali_corporation.financual_study_guide.fragment.HomeFragment.homeRecyclerView2;

public class MainActivity extends AppCompatActivity {
    public static Boolean isSearched=false;
    public static int anInt=0;
    public static EditText searchEditText;
    public static ImageView searchBtn;
    TabLayout tabLayout;
    TabItem mHome,mScience,mHealth,mTech,mEntertainment,mSport;
    PagerAdapter pagerAdapter;
    Toolbar mToolbar;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        pagerAdapter=new PagerAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                pagerAdapter.notifyDataSetChanged();
                if (tab.getPosition()==0){
                    findViewById(R.id.search_layout).setVisibility(View.VISIBLE);
                    findViewById(R.id.text_layout).setVisibility(View.GONE);
                }else {
                    findViewById(R.id.search_layout).setVisibility(View.GONE);
                    findViewById(R.id.text_layout).setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initView() {
        mToolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mHome=findViewById(R.id.mHome);
        mScience=findViewById(R.id.mScience);
        mHealth=findViewById(R.id.mHealth);
        mTech=findViewById(R.id.mTech);
        mEntertainment=findViewById(R.id.mEntertainment);
        mSport=findViewById(R.id.mSport);

        tabLayout=findViewById(R.id.include);

        viewPager=findViewById(R.id.fragment_container);
        searchEditText=mToolbar.findViewById(R.id.search_edit_text);
        searchBtn=mToolbar.findViewById(R.id.search_btn);

    }

    @Override
    public void onBackPressed() {
        if (anInt==1){
            super.onBackPressed();
        }
        if (isSearched=true){
            homeRecyclerView2.setVisibility(View.GONE);
            homeRecyclerView.setVisibility(View.VISIBLE);
            isSearched=false;
            anInt=1;
        }
    }
}