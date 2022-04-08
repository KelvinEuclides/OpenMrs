package mz.co.kelvin.openmrschallenge.ui.searchHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import mz.co.kelvin.openmrschallenge.databinding.FragmentSearchHistoryBinding;
import mz.co.kelvin.openmrschallenge.network.ApiClient;
import mz.co.kelvin.openmrschallenge.network.ApiInterface;

public class SearchHistoryFragment extends Fragment {

    private FragmentSearchHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchHistoryViewModel galleryViewModel =
                new ViewModelProvider(this).get(SearchHistoryViewModel.class);
      ;

        binding = FragmentSearchHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}