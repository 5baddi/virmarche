package info.baddi.virmarche.Helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import info.baddi.virmarche.Fragments.LoginFragment;
import info.baddi.virmarche.Fragments.RegisterFragment;

/**
 * Created by 5Baddi on 04-Mar-18.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        if(position == 0) return new LoginFragment();
        else return new RegisterFragment();
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}