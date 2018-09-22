package com.mostafavi.home.food.api;

import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.http.HttpRequest;
import com.mostafavi.home.food.http.HttpResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class FoodApi {

    public static Observable<List<Food>> getFoodApi() {
        return Observable.create(emitter -> new HttpRequest().Get(Services.Urls.foods).setOnResultListener(new HttpRequest.OnResultListener() {
            @Override
            public void onFailure() {
                emitter.onError(new Exception("Network Error"));
            }

            @Override
            public void onPreRequest() {

            }

            @Override
            public void onResponse(HttpResponse response) {
                if (response.getStatus() == HttpResponse.Status.OK) {
                    List<Food> foods = Services.Parser.getFoods(response.getResult());
                    if (foods.isEmpty())
                        emitter.onComplete();
                    else
                        emitter.onNext(foods);
                } else
                    emitter.onError(new Exception(response.getResult()));
            }
        }).execute());
    }
}
