package moises.com.petsapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import moises.com.petsapp.R;
import moises.com.petsapp.model.Pet;
import moises.com.petsapp.ui.fragment.ContainerFragment;
import moises.com.petsapp.ui.fragment.PetListFragment;
import moises.com.petsapp.ui.test.MainMenuActivity;

public class MainActivity extends AppCompatActivity implements PetListFragment.OnPetListFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showFragment(PetListFragment.getInstance(), false);
    }

    public void showFragment(Fragment fragment, boolean stack){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(stack)
            ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.replace(R.id.content_main, fragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            MainMenuActivity.startActivity(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPetClick(Pet pet) {
        showFragment(ContainerFragment.newInstance(pet.getId()), true);
    }
}
