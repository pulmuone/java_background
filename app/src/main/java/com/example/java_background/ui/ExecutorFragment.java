package com.example.java_background.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.java_background.MainViewModel;
import com.example.java_background.R;
import com.example.java_background.Result;
import com.example.java_background.databinding.FragmentExecutorBinding;
import com.example.java_background.repository.RepositoryCallback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExecutorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExecutorFragment extends Fragment {

    private static final String TAG = ExecutorFragment.class.getSimpleName();
    private MainViewModel viewModel;
    private FragmentExecutorBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExecutorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExecutorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExecutorFragment newInstance(String param1, String param2) {
        ExecutorFragment fragment = new ExecutorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(MainViewModel.class);

        viewModel.progressLiveData.observe(getViewLifecycleOwner(), progress -> {
            binding.progress.setProgress(progress);
        } );
        binding.button.setOnClickListener(v -> viewModel.longTask());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_executor, container, false);
        binding = FragmentExecutorBinding.bind(view);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}