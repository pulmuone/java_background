package com.example.java_background.repository;

import com.example.java_background.Result;

public interface RepositoryCallback<T> {
    void onComplete(Result<T> result);
}