package com.kali_corporation.financual_study_guide.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.kali_corporation.financual_study_guide.R;
import com.kali_corporation.financual_study_guide.adapter.MyAdapter;
import com.kali_corporation.financual_study_guide.api.AppUitilities;
import com.kali_corporation.financual_study_guide.modle.MainModel;
import com.kali_corporation.financual_study_guide.modle.NewsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kali_corporation.financual_study_guide.MainActivity.searchBtn;
import static com.kali_corporation.financual_study_guide.MainActivity.searchEditText;
import static com.kali_corporation.financual_study_guide.MainActivity.isSearched;
import static com.kali_corporation.financual_study_guide.MainActivity.anInt;

public class HomeFragment extends Fragment {

    public static RecyclerView homeRecyclerView,homeRecyclerView2;
    ArrayList<NewsModel> newsModelArrayList,newsModelArrayList2;
    MyAdapter adapter,adapter2;
    String country="in";
    private String q="finance";
//    int pageNumber = 1;
    Boolean isScrolling=false;
    Boolean isAvalable=true;
    int currentItem,totalItem,scrollOutItem;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeRecyclerView=view.findViewById(R.id.home_recycler_view);
        homeRecyclerView2=view.findViewById(R.id.home_recycler_view2);
        if (isSearched){
            homeRecyclerView2.setVisibility(View.VISIBLE);
            homeRecyclerView.setVisibility(View.GONE);
        }else {
            homeRecyclerView2.setVisibility(View.GONE);
            homeRecyclerView.setVisibility(View.VISIBLE);
        }
        newsModelArrayList=new ArrayList<>();
        newsModelArrayList2=new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext());
        homeRecyclerView.setLayoutManager(linearLayoutManager);
        homeRecyclerView2.setLayoutManager(linearLayoutManager2);
        adapter=new MyAdapter(getContext(),newsModelArrayList);
        adapter2=new MyAdapter(getContext(),newsModelArrayList2);
        homeRecyclerView.setAdapter(adapter);
        homeRecyclerView2.setAdapter(adapter2);
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                    isScrolling=true;
//                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                currentItem=linearLayoutManager.getChildCount();
//                totalItem=linearLayoutManager.getItemCount();
//                scrollOutItem=linearLayoutManager.findFirstCompletelyVisibleItemPosition();
//                if (isScrolling&&(currentItem+scrollOutItem==totalItem)){
//                    if (isAvalable) {
//                        pageNumber++;
//                        findNews();
//                    }
//                }
//            }
//        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchEditText.getText().toString().trim()!=null) {
                    isSearched = true;
                    homeRecyclerView2.setVisibility(View.VISIBLE);
                    homeRecyclerView.setVisibility(View.GONE);
                    findSearchedNews(searchEditText.getText().toString().trim());
                }
            }
        });
        findNews();
    }

    private void findNews() {
        Log.e("xxxx","home");

        AppUitilities.getApiInterFace().getFinance(100,q,getString(R.string.api_key)).enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                Log.e("xxxx", String.valueOf(response));

                if (response.code()==426){
                   isAvalable=false;
                }else {
                    newsModelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                    isAvalable=true;
                    isSearched=false;
                }

            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void findSearchedNews(String s) {
        AppUitilities.getApiInterFace().getFinance(100,s,getString(R.string.api_key)).enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                Log.e("xxxx", String.valueOf(response));

                if (response.code()==426){
                   isAvalable=false;
                }else {
                    newsModelArrayList2.clear();
                    newsModelArrayList2.addAll(response.body().getArticles());
                    adapter2.notifyDataSetChanged();
                    isAvalable=true;
                    isSearched=true;
                    anInt=0;
                }

            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}