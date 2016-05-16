package leonard.weather.module.city.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import leonard.weather.R;
import leonard.weather.databinding.CityListItemBinding;
import leonard.weather.model.CityModel;
import leonard.weather.module.city.viewModel.CityItemViewModel;

/**
 * 城市列表适配器
 * Created by leonard on 2016/05/16.
 */
public class CityItemAdapter extends RecyclerView.Adapter<CityItemAdapter.CityViewHolder> {

    private List<CityModel> mCities;

    public CityItemAdapter() {
        mCities = Collections.emptyList();
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.city_list_item, parent, false);
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bindCity(mCities.get(position));
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {
        private final CityListItemBinding mBinding;

        public CityViewHolder(CityListItemBinding binding) {
            super(binding.layoutCity);
            this.mBinding = binding;
        }

        private void bindCity(CityModel city) {
            if(mBinding.getViewModel() == null) {
               mBinding.setViewModel(new CityItemViewModel(itemView.getContext(), city));
            }
        }
    }
}
