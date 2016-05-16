package leonard.weather.module.city.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import leonard.weather.R;
import leonard.weather.databinding.CityListActivityBinding;
import leonard.weather.module.base.view.BaseActivity;
import leonard.weather.module.city.viewModel.CityListViewModel;

/**
 * 城市列表Activity
 * Created by leonard on 2016/05/16.
 */
public class CityListActivity extends BaseActivity {

    private CityListActivityBinding mBinding;
    private CityListViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.city_list_activity);
        mViewModel = new CityListViewModel();
        mBinding.setViewModel(mViewModel);

        initView();
    }

    private void initView() {
        mBinding.layoutCity.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mBinding.layoutCity.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mBinding.layoutCity.setSize(SwipeRefreshLayout.DEFAULT);
        mBinding.layoutCity.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.refreshCityList();
            }
        });

        mBinding.rvCity.setAdapter(new CityItemAdapter());
        mBinding.rvCity.setLayoutManager(new LinearLayoutManager(this));
    }
}
