package ru.danegor.notes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.note_title).setVisibility(View.GONE);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
//            mToolbar.setNavigationIcon(R.drawable.ic_drawer);
//            mToolbar.setNavigationContentDescription("description");
//            mToolbar.setLogo(R.drawable.ic_launcher);
//            mToolbar.setLogoDescription("logo description");
//            getSupportActionBar().setTitle("title!");
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.container, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        switch (position) {
            case 0:
                setFragment(new AllNotesFragment());
                break;
            case 1:
                setFragment(new EditorFragment());
                break;
            case 2:
                break;
        }

    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment)
                .commit();
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
