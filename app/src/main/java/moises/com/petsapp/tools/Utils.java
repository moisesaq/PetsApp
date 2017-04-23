package moises.com.petsapp.tools;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import moises.com.petsapp.ui.App;

public class Utils {

    public static int getColor(int id){
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(App.getContext(), id);
        } else {
            return App.getContext().getResources().getColor(id);
        }
    }

    public static void showToastMessage(int messageId){
        Toast.makeText(App.getContext(), App.getContext().getString(messageId), Toast.LENGTH_SHORT).show();
    }

    public static void showToastMessage(String message){
        Toast.makeText(App.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
