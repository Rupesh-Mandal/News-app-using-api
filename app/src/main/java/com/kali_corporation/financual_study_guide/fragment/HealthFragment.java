package com.kali_corporation.financual_study_guide.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kali_corporation.financual_study_guide.R;
import com.kali_corporation.financual_study_guide.adapter.MyAdapter;
import com.kali_corporation.financual_study_guide.api.AppUitilities;
import com.kali_corporation.financual_study_guide.modle.MainModel;
import com.kali_corporation.financual_study_guide.modle.NewsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthFragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<NewsModel> newsModelArrayList;
    MyAdapter adapter;
    private String country="in";
    private String category="health";

    public HealthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.health_recycler_view);
        newsModelArrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new MyAdapter(getContext(),newsModelArrayList);
        recyclerView.setAdapter(adapter);

        findNews();
    }

    private void findNews() {
        Log.e("xxxx","health");

        AppUitilities.getApiInterFace().getCategoryNews(country,category,100,getString(R.string.api_key)).enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                newsModelArrayList.addAll(response.body().getArticles());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {

            }
        });

    }
}