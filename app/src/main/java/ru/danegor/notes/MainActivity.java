package ru.danegor.notes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerFragment mNavigationDrawerFragment;

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

        mDrawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));

        mNavigationDrawerFragment.setUp(R.id.container, mDrawerLayout);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AllNotesFragment())
                .commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        mDrawerLayout.closeDrawers();
        switch (position) {
            case 0: {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                if (fragment instanceof EditorFragment) {
                    ((EditorFragment) fragment).offerSave();
                } else {
                    super.onBackPressed();
                }
                setFragment(new AllNotesFragment());
                break;
            }
            case 1:
                setFragment(new EditorFragment());
                break;
            case 2: {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                if (fragment instanceof EditorFragment) {
                    ((EditorFragment) fragment).save();
                } else {
                    super.onBackPressed();
                }
                break;
            }
        }

    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment)
                .commit();
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawers();
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
            if (fragment instanceof EditorFragment) {
                ((EditorFragment) fragment).onBackPressed();
            } else {
                super.onBackPressed();
            }
        }
    }

    public void pressBack() {
        super.onBackPressed();
    }

    public void hideSaveButton() {
        mNavigationDrawerFragment.setSaveButtonVisible(false);
    }

    public void showSaveButton() {
        mNavigationDrawerFragment.setSaveButtonVisible(true);
    }
}
