package moises.com.petsapp.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import moises.com.petsapp.R;

public class Pet implements Serializable{
    private String id;
    private String name;
    private String status;
    private List<String> photoUrls;

    @SerializedName("category")
    private Category category;

    @SerializedName("tags")
    private List<Tag> tagList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public static List<Integer> petImagesTest(){
        List<Integer> petList = new ArrayList<>();
        petList.add(R.mipmap.img_husky_siberiano_1);
        petList.add(R.mipmap.img_husky_siberiano_2);
        petList.add(R.mipmap.img_husky_siberiano_3);
        petList.add(R.mipmap.img_husky_siberiano_4);
        petList.add(R.mipmap.img_husky_siberiano_5);
        petList.add(R.mipmap.img_husky_siberiano_6);
        petList.add(R.mipmap.img_husky_siberiano_7);
        petList.add(R.mipmap.img_husky_siberiano_8);
        petList.add(R.mipmap.img_husky_siberiano_9);
        petList.add(R.mipmap.img_husky_siberiano_10);
        return petList;

    }
    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
