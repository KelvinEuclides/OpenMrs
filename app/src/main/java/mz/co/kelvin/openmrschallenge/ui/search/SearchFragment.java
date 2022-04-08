package mz.co.kelvin.openmrschallenge.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mz.co.kelvin.openmrschallenge.adapter.MySearchRecyclerViewAdapter;
import mz.co.kelvin.openmrschallenge.databinding.FragmentSearchBinding;
import mz.co.kelvin.openmrschallenge.model.Result;
import mz.co.kelvin.openmrschallenge.model.Results;
import mz.co.kelvin.openmrschallenge.network.ApiClient;
import mz.co.kelvin.openmrschallenge.network.ApiInterface;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private ApiInterface apiInterface;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel homeViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Results> call = apiInterface.patient("kelvin","default",100);


        binding = FragmentSearchBinding.inflate(inflater, container, false);
        RecyclerView recyclerView = binding.rvPatients;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        binding.btSearch.setOnClickListener(v -> call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call1, retrofit2.Response<Results> response) {
                Results resource = response.body();
                recyclerView.setAdapter(new MySearchRecyclerViewAdapter(resource));
            }

            @Override
            public void onFailure(Call<Results> call1, Throwable t) {

            }
        }));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}