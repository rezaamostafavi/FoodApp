package com.mostafavi.home.food.http;

public interface ProgressUIListener {
    void onProgressChanged(int d, long fileSize, float progress, int i);

    void onProgressFinish();
}
