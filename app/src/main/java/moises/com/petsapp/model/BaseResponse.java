package moises.com.petsapp.model;

import com.google.gson.Gson;

import java.io.Serializable;

public class BaseResponse implements Serializable{
    private String id;
    private String name;

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

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
}
