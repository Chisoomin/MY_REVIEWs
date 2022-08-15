package com.example.project_last;

public interface AsyncCallback {
    void onSuccess(String result);
    void onFailure(Exception e);
}
