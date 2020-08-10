package com.example.java_background;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.concurrent.Executor;

import com.example.java_background.repository.NumberRepository;
import com.example.java_background.repository.RepositoryCallback;

public class MainViewModel extends AndroidViewModel {

    private final NumberRepository repository;

    public MutableLiveData<Integer> progressLiveData = new MutableLiveData<Integer>(0);

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new NumberRepository(
                ((App)application).mainThreadHandler,
                ((App)application).executorService
                );
    }

    public void longTask() {
        repository.longTask(result -> {
            if(result instanceof Result.Success) {
                //binding.progress.setProgress(((Result.Success<Integer>) result).data);
                progressLiveData.postValue(((Result.Success<Integer>) result).data);
            } else if(result instanceof  Result.Error){
                //Toast.makeText(TAG, ((Result.Error<Integer>) result).exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
