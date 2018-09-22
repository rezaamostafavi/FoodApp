package com.mostafavi.home.food.ui.food;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.google.gson.Gson;
import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.R;
import com.mostafavi.home.food.ui.base.BaseViewModel;
import com.mostafavi.home.food.util.rx.SchedulerProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FoodViewModel extends BaseViewModel<FoodNavigator> implements View.OnClickListener {
    private Food food;

    private final ObservableField<String> userName = new ObservableField<>("");
    private final ObservableField<String> date = new ObservableField<>("");
    private final ObservableField<String> like = new ObservableField<>("");
    private final ObservableField<String> description = new ObservableField<>("");
    private final ObservableField<String> userImage = new ObservableField<>("");
    private final ObservableField<String> image = new ObservableField<>("");
    private final ObservableInt fabIcon = new ObservableInt(R.drawable.ic_disable_heart);

    public FoodViewModel(SchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public ObservableField<String> getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public ObservableField<String> getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like.set(like);
    }

    public ObservableField<String> getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public ObservableField<String> getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage.set(userImage);
    }

    public ObservableField<String> getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public ObservableInt getFabIcon() {
        return fabIcon;
    }

    public void setFabIcon(int drawable) {
        fabIcon.set(drawable);
    }

    public void init(Intent intent) {
        if (intent != null && intent.hasExtra("food")) {
            food = new Gson().fromJson(intent.getStringExtra("food"), Food.class);
            if (food != null) {
                setDescription(food.getDescription());
                setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK).format(new Date(food.getDateTime())));
                setImage(food.getImage());
                setLike(String.valueOf(food.getLike()));
                setUserImage(food.getUser().getProfilePicture());
                setUserName(food.getUser().getName());
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                food.setLiked(!food.isLiked());
                if (food.isLiked())
                    setFabIcon(R.drawable.ic_heart);
                else
                    setFabIcon(R.drawable.ic_disable_heart);
                break;
        }
    }
}
