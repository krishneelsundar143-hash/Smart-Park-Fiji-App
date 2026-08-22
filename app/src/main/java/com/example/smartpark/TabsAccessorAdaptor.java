package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAccessorAdaptor extends FragmentPagerAdapter
{

    public TabsAccessorAdaptor(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;

            case 1:
                ParkingFragment parkingFragment = new ParkingFragment();
                return parkingFragment;

            case 2:
                MapFragment mapFragment = new MapFragment();
                return mapFragment;

            /**case 2:
                ParkingFragment parkingFragment = new ParkingFragment();
                return parkingFragment;**/

            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "";//Home

            case 1:
                return "";//Parking

            case 2:
                return "";//Map

            default:
                return null;
        }
    }

    public int getIcon(int position)
    {
        switch (position)
        {
            case 0:
                return R.drawable.home_icon; // Replace with your home icon resource
            case 1:
                return R.drawable.carpar_icon; // Replace with your parking icon resource
            case 2:
                return R.drawable.map_icon; // Replace with your map icon resource
            default:
                return 0;
        }
    }

}
