package xyz.imxqd.homework4.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.imxqd.homework4.MyApp;
import xyz.imxqd.homework4.R;
import xyz.imxqd.homework4.net.base.HomeWorkService;
import xyz.imxqd.homework4.net.base.HttpObserver;
import xyz.imxqd.homework4.net.model.HomePage;


public class TplFragment extends Fragment {

    private static final String TAG = "TplFragment";

    private TplAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView.LayoutManager layoutManager;

    private boolean isLoading = false;

    public TplFragment() {
    }

    public static TplFragment newInstance() {
        return new TplFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TplAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tpl_list, container, false);

        layoutManager = new LinearLayoutManager(getContext());

        refreshLayout = view.findViewById(R.id.swipe_layout);
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                loadData();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1)) {
                    loadMore();
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loadData();
    }

    private void loadData() {
        isLoading = true;
        MyApp.get().getRetrofit().create(HomeWorkService.class).homePage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<HomePage>() {
                    @Override
                    public void onSuccess(HomePage data) {
                        Log.d(TAG, data.toString());
                        isLoading = false;
                        refreshLayout.setRefreshing(false);
                        adapter.clear();
                        adapter.addAll(data.list);
                        adapter.notifyDataSetChanged();

                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).setHotWord(data.headData.hotSearchKey);
                        }
                    }

                    @Override
                    public void onError(int errorCode, String errorMessage) {
                        Log.e(TAG, "errorCode: " + errorCode);
                        isLoading = false;
                        refreshLayout.setRefreshing(false);
                    }
                });
    }


    private void loadMore() {
        isLoading = true;
        MyApp.get().getRetrofit().create(HomeWorkService.class).homePage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<HomePage>() {
                    @Override
                    public void onSuccess(HomePage data) {
                        Log.d(TAG, data.toString());
                        isLoading = false;
                        refreshLayout.setRefreshing(false);
                        adapter.addAll(data.list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(int errorCode, String errorMessage) {
                        Log.e(TAG, "errorCode: " + errorCode);
                        isLoading = false;
                        refreshLayout.setRefreshing(false);
                    }
                });
    }

}
