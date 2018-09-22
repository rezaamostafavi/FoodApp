package com.mostafavi.home.food.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;

import com.mostafavi.home.food.R;
import com.mostafavi.home.food.util.Utils;
import com.mostafavi.home.food.util.rx.SchedulerProvider;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Reza on 9/16/2018.
 */

public class BaseViewModel<N> extends ViewModel {

    private WeakReference<N> mNavigator;
    private final SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;

    @BindingAdapter({"bindImageLoader"})
    public static void bindImageLoader(ImageView view, String urlImage) {
        if (!Utils.isEmpty(urlImage))
            Picasso.with(view.getContext()).load(urlImage).into(view);
    }

    @BindingAdapter("bindSrcCompat")
    public static void bindSrcCompat(ImageView imageView, int drawable) {
        imageView.setImageResource(drawable);
    }

    public BaseViewModel(SchedulerProvider schedulerProvider) {
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public void setNavigator(N mNavigator) {
        this.mNavigator = new WeakReference<>(mNavigator);
    }
}
