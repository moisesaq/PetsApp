package moises.com.petsapp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import moises.com.petsapp.R;
import moises.com.petsapp.model.Pet;

public class PetListAdapter extends ArrayAdapter<Pet>{

    public PetListAdapter(Context context, List<Pet> petList){
        super(context, R.layout.pet_item, petList);
    }

    public class ViewHolder{
        TextView mName;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item, parent, false);
            holder.mName = (TextView)view.findViewById(R.id.tv_name_pet);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        Pet pet = getItem(position);
        if(pet != null)
            holder.mName.setText(pet.getName());
        return view;
    }
}
