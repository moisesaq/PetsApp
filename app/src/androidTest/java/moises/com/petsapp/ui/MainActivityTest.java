package moises.com.petsapp.ui;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import moises.com.petsapp.R;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRules = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;
    @Before
    public void setUp() throws Exception {
        mActivity = mActivityRules.getActivity();
    }

    @Test
    public void lunch(){
        View view = mActivity.findViewById(R.id.toolbar);
        assertNotNull("Toolbar is ok", view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}