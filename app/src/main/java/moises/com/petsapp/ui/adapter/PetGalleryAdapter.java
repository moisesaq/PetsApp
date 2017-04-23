package moises.com.petsapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import moises.com.petsapp.R;
import moises.com.petsapp.model.Pet;

public class PetGalleryAdapter extends BaseAdapter{
    private Context mContext;
    private Pet mPet;
    //TODO: Replace Pet.petImageTest() with pet.getPhotoUrls()

    public PetGalleryAdapter(Context context, Pet pet){
        this.mContext = context;
        this.mPet = pet;
    }
    @Override
    public int getCount() {
        return Pet.petImagesTest().size();
    }

    @Override
    public Integer getItem(int i) {
        return Pet.petImagesTest().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.gallery_pet_item, viewGroup, false);
        }

        Integer petImage = getItem(i);
        if(petImage != null){
            ImageView mImageView = (ImageView)view.findViewById(R.id.imageView);
            Picasso.with(mContext)
                    .load(petImage)
                    .placeholder(R.mipmap.siberian_husky_2)
                    .error(R.mipmap.siberian_husky)
                    .into(mImageView);
        }
        return view;
    }
}
