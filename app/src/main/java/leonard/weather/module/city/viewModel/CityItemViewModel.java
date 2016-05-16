package leonard.weather.module.city.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import leonard.weather.model.CityModel;
import leonard.weather.module.base.viewModel.BaseViewModel;

/**
 * 城市列表项View Model
 * Created by leonard on 2016/05/16.
 */
public class CityItemViewModel extends BaseObservable implements BaseViewModel {

    private CityModel city;

    private Context context;

    public CityItemViewModel(Context context, CityModel city) {
        this.context = context;
        this.city = city;
    }

    public String getCityName() {
        return city.getCityName();
    }

    public void onItemClick(View view) {
//        context.startActivity(RepositoryActivity.newIntent(context, repository));
    }

    public void setCity(CityModel city) {
        this.city = city;
        notifyChange();
    }

    @Override
    public void destroy() {

    }
}
