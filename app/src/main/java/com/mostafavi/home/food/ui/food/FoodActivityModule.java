package com.mostafavi.home.food.ui.food;

import com.mostafavi.home.food.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class FoodActivityModule {
    @Provides
    FoodViewModel provideFoodViewModel(SchedulerProvider schedulerProvider) {
        return new FoodViewModel(schedulerProvider);
    }
}
