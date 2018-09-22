package com.mostafavi.home.food.ui.food;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mostafavi.home.food.BR;
import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.R;
import com.mostafavi.home.food.databinding.ActivityFoodBinding;
import com.mostafavi.home.food.ui.base.BaseActivity;
import com.mostafavi.home.food.util.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class FoodActivity extends BaseActivity<ActivityFoodBinding, FoodViewModel> implements FoodNavigator {

    @Inject
    FoodViewModel viewModel;
    ActivityFoodBinding binding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public FoodViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_food;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        viewModel.setNavigator(this);
        viewModel.init(getIntent());
        initToolbar();
        initTransitionSettings();
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ObjectAnimator.ofFloat(binding.tvDescription, "translationY", 0, Utils.getHeightScreen(this)).setDuration(500).start();
        binding.fab.setVisibility(View.GONE);
        super.onBackPressed();
    }

    private void initTransitionSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);
        }
    }

    private void init() {
        ObjectAnimator.ofFloat(binding.tvDescription, "translationY", Utils.getHeightScreen(this), 0).setDuration(500).start();
    }

    protected void initToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            changeStatusBarColor(ContextCompat.getColor(this, R.color.colorTransparentStatusBar));
            binding.toolbar.setPadding(0, Utils.getStatusBarHeight(this), 0, 0);
        }
    }

    private void changeStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(color);
        }
    }


}
