package leonard.weather.module.city.viewModel;

import android.databinding.BaseObservable;

import leonard.weather.module.base.viewModel.BaseViewModel;

/**
 *
 * Created by leonard on 2016/05/16.
 */
public class CityListViewModel extends BaseObservable implements BaseViewModel {

    private boolean isRefreshing;

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void refreshCityList() {
        isRefreshing = false;
        notifyChange();
    }

    @Override
    public void destroy() {

    }
}
