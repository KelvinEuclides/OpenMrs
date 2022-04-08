package mz.co.kelvin.openmrschallenge.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mz.co.kelvin.openmrschallenge.R;
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
    private Call<Results> call;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        RecyclerView recyclerView = binding.rvPatients;
        recyclerView.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        binding.btSearch.setOnClickListener(v -> {
            binding.ivMessage.setVisibility(View.GONE);
            binding.tvMensagem.setVisibility(View.GONE);
            binding.progressBar.setVisibility(View.VISIBLE);
            String query = binding.tiletSearch.getText().toString();
            call = apiInterface.patient(query, "default", 100);
            call.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call1, retrofit2.Response<Results> response) {
                    if(response.isSuccessful()){
                        Results resource = response.body();
                        if(resource.getResults().size()>0){
                            recyclerView.setVisibility(View.VISIBLE);
                            recyclerView.setAdapter(new MySearchRecyclerViewAdapter(resource));
                            binding.progressBar.setVisibility(View.GONE);
                        }else{
                            recyclerView.setVisibility(View.GONE);
                            binding.ivMessage.setVisibility(View.VISIBLE);
                            binding.tvMensagem.setVisibility(View.VISIBLE);
                            binding.tvMensagem.setText(R.string.no_result);
                            binding.progressBar.setVisibility(View.GONE);
                        }

                    }

                }

                @Override
                public void onFailure(Call<Results> call1, Throwable t) {

                }
            });
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}