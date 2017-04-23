package moises.com.petsapp.web;

public interface API {
    String API_BASE = "http://petstore.swagger.io";

    String VERSION = API_BASE + "/v2";
    String PET = VERSION + "/pet";
    String FIND_BY_STATUS = PET + "/findByStatus";
    String FIND_PET_BY_ID = PET + "/{petId}";
}
