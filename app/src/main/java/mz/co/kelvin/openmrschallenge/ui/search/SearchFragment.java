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

import com.bumptech.glide.Glide;

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
        //Chamamos a Interface de apis do projeto
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        //Definimos a encadernação das paginas
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        RecyclerView recyclerView = binding.rvPatients;
        //Ocultamos a visibilidade da recycler view e da barra de progresso
        recyclerView.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        //Definimos o gestor do linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        //Colocamos ação ao butao de pesquisa
        binding.btSearch.setOnClickListener(v -> {
            //Ocultamos a visibilidde da Mensagem e imagem
            binding.ivMessage.setVisibility(View.GONE);
            binding.tvMensagem.setVisibility(View.GONE);
            binding.progressBar.setVisibility(View.VISIBLE);
            //Carregamos o texto inserido
            String query = binding.tiletSearch.getText().toString();
            //Fazemos a requisição a api
            call = apiInterface.patient(query, "default", 100);
            call.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call1, retrofit2.Response<Results> response) {
                    //caso haja uma resposta verificamos se a resposta contem resultados e exibimos no recycler view
                    if(response.isSuccessful()){
                        Results resource = response.body();
                        assert resource != null;
                        if(resource.getResults().size()>0){
                            recyclerView.setVisibility(View.VISIBLE);
                            recyclerView.setAdapter(new MySearchRecyclerViewAdapter(resource));
                            binding.progressBar.setVisibility(View.GONE);
                        }else{
                            //caso não mostramos aa imagem de pesquisa e a mensagem
                            recyclerView.setVisibility(View.GONE);
                            binding.ivMessage.setVisibility(View.VISIBLE);
                            Glide.with(binding.getRoot().getContext()).load(R.drawable.ic_searching_re_3ra9).into(binding.ivMessage);
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