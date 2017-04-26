package moises.com.petsapp.tools;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import moises.com.petsapp.ui.PetsApp;

public class Utils {

    public static int getColor(int id){
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(PetsApp.getContext(), id);
        } else {
            return PetsApp.getContext().getResources().getColor(id);
        }
    }

    public static void showToastMessage(int messageId){
        Toast.makeText(PetsApp.getContext(), PetsApp.getContext().getString(messageId), Toast.LENGTH_SHORT).show();
    }

    public static void showToastMessage(String message){
        Toast.makeText(PetsApp.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
