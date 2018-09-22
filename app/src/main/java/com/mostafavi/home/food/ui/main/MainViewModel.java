package com.mostafavi.home.food.ui.main;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.google.gson.Gson;
import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.R;
import com.mostafavi.home.food.api.FoodApi;
import com.mostafavi.home.food.ui.base.BaseAdapterViewModel;
import com.mostafavi.home.food.ui.base.BaseViewModel;
import com.mostafavi.home.food.util.rx.SchedulerProvider;

/**
 * Created by Reza on 9/16/2018.
 */

public class MainViewModel extends BaseViewModel<MainNavigator> implements BaseAdapterViewModel.OnItemClickListener<Food>, View.OnClickListener {

    private final ObservableField<String> error = new ObservableField<>("");
    private final ObservableBoolean isError = new ObservableBoolean(false);
    private final ObservableBoolean loading = new ObservableBoolean(false);
    private final ObservableBoolean isLoadingMode = new ObservableBoolean(false);

    public MainViewModel(SchedulerProvider schedulerProvider) {
        super(schedulerProvider);
        getFoodApi();
    }

    public ObservableField<String> getError() {
        return error;
    }

    public void setError(String error) {
        this.error.set(error);
    }

    public ObservableBoolean getIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError.set(isError);
    }

    public ObservableBoolean getLoading() {
        return loading;
    }

    public ObservableBoolean getIsLoadingMode() {
        return isLoadingMode;
    }

    public void setIsLoadingMode(boolean isLoadingMode) {
        this.isLoadingMode.set(isLoadingMode);
    }

    public void setLoading(boolean loading) {
        this.loading.set(loading);
        if (loading) {
            setIsLoadingMode(true);
            setIsError(false);
            setError("");
        }

    }

    public void getFoodApi() {
        setLoading(true);
        getCompositeDisposable().add(FoodApi.getFoodApi().subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui())
                .subscribe(foods -> {
                            setLoading(false);
                            setIsError(false);
                            setError("");
                            setIsLoadingMode(false);
                            getNavigator().refreshList(foods);
                        }, throwable -> {
                            setError(getNavigator().getRString(R.string.networkError));
                            setIsError(true);
                            setLoading(false);
                        }
                        , () -> {
                            setError(getNavigator().getRString(R.string.notFound));
                            setIsError(false);
                            setLoading(false);
                        }));
    }

    @Override
    public void onClick(Food item, View view) {
        getNavigator().OnItemLick(new Gson().toJson(item), view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTryAgain:
                getFoodApi();
                break;
        }
    }
}
