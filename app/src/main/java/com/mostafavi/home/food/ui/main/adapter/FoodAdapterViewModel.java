package com.mostafavi.home.food.ui.main.adapter;

import android.arch.lifecycle.ViewModel;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import android.view.View;

import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.R;
import com.mostafavi.home.food.ui.base.BaseAdapterViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FoodAdapterViewModel extends BaseAdapterViewModel<Food> implements View.OnClickListener {

    private final ObservableField<String> userName = new ObservableField<>("");
    private final ObservableField<String> date = new ObservableField<>("");
    private final ObservableField<String> like = new ObservableField<>("");
    private final ObservableField<String> description = new ObservableField<>("");
    private final ObservableField<String> userImage = new ObservableField<>("");
    private final ObservableField<String> image = new ObservableField<>("");

    public FoodAdapterViewModel(Food item, OnItemClickListener<Food> itemClickListener) {
        super(item, itemClickListener);
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

    public void init() {
        setDescription(item.getDescription());
        setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK).format(new Date(item.getDateTime())));
        setImage(item.getImage());
        setLike(String.valueOf(item.getLike()));
        setUserImage(item.getUser().getProfilePicture());
        setUserName(item.getUser().getName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.panelMain:
                itemClickListener.onClick(item, view.findViewById(R.id.imgImage));
                break;
        }
    }
}
