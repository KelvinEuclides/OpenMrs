package mz.co.kelvin.openmrschallenge.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mz.co.kelvin.openmrschallenge.databinding.ResultListItemBinding;
import mz.co.kelvin.openmrschallenge.model.Results;
import mz.co.kelvin.openmrschallenge.placeholder.PlaceholderContent.PlaceholderItem;



public class MySearchRecyclerViewAdapter extends RecyclerView.Adapter<MySearchRecyclerViewAdapter.ViewHolder> {


    private final Results mValues;

    public MySearchRecyclerViewAdapter(Results items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(ResultListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
          holder.tv_patient_name.setText(mValues.getResults().get(position).getDisplay());
          holder.tv_patient_birtdate.setText(String.format("Data Nasc:%s Idade:%d", mValues.getResults().get(position).getPerson().getBirthdate(), mValues.getResults().get(position).getPerson().getAge()));
          holder.tv_patient_sexo.setText("Sexo:"+mValues.getResults().get(position).getPerson().getGender());
          holder.tv_patient_hospital.setText(mValues.getResults().get(position).getPerson().getPreferredAddress().getDisplay());
    }

    @Override
    public int getItemCount() {
        if(mValues != null){
            return mValues.getResults().size();
        }
          return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tv_patient_name,tv_patient_birtdate,tv_patient_sexo,tv_patient_hospital;
        public PlaceholderItem mItem;

        public ViewHolder(ResultListItemBinding binding) {
            super(binding.getRoot());
            tv_patient_name = binding.tvPatientName;
            tv_patient_birtdate =binding.tvPatientBirtdate;
            tv_patient_sexo = binding.tvPatientSexo;
            tv_patient_hospital =binding.tvPatientSexo;
            Glide.with(binding.getRoot().getContext()).load("https://avatars.githubusercontent.com/u/20488304?s=96&v=4").circleCrop().into(binding.ivPatientPicture);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}