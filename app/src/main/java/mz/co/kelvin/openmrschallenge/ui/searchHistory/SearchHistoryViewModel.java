package mz.co.kelvin.openmrschallenge.ui.searchHistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchHistoryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SearchHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}