package com.example.smartpark;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Toolbar nToolbar;
    private View homeFragmentView;

    private Button parkingHistoryButton, aboutUsButton, bookedSlotsButton, mapButton, BookingInfo, parkingVideoButton;

    public HomeFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2)
    {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        homeFragmentView =  inflater.inflate(R.layout.fragment_home, container, false);

        final TextView animateText = homeFragmentView.findViewById(R.id.welcome);

        // Create a fade-in animation
        final Animation fadeInAnimation = new AlphaAnimation(0, 1);
        fadeInAnimation.setDuration(1000); // Adjust the duration as needed

        // Create a fade-out animation
        final Animation fadeOutAnimation = new AlphaAnimation(1, 0);
        fadeOutAnimation.setDuration(1000); // Adjust the duration as needed

        // Combine both animations to create a continuous fade in and out effect
        final AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(fadeInAnimation);
        animationSet.addAnimation(fadeOutAnimation);

        animateText.startAnimation(animationSet);

        InitializeFields();

        return homeFragmentView;
    }

    private void InitializeFields()
    {
        BookingInfo = (Button) homeFragmentView.findViewById(R.id.parking);
        BookingInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                bookingInfo();
            }
        });

        bookedSlotsButton = (Button) homeFragmentView.findViewById(R.id.booked_slots);
        bookedSlotsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                bookedSlot();
            }
        });

        mapButton = (Button) homeFragmentView.findViewById(R.id.map);
        mapButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                map();
            }
        });

        parkingHistoryButton = (Button) homeFragmentView.findViewById(R.id.packing_history);
        parkingHistoryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                parkingHistory();
            }
        });

        aboutUsButton = (Button) homeFragmentView.findViewById(R.id.about_us);
        aboutUsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                aboutUsActivity();
            }
        });

        parkingVideoButton = (Button) homeFragmentView.findViewById(R.id.payment_methods);
        parkingVideoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                parkingVidActivity();
            }
        });
    }

    private void parkingVidActivity()
    {
        Intent ParkingVidIntent = new Intent(getActivity(), ParkingVideoActivity.class);
        startActivity(ParkingVidIntent);
    }

    private void bookingInfo()
    {
        Intent BookingInfoIntent = new Intent(getActivity(), BookingInforActivity.class);
        startActivity(BookingInfoIntent);
    }

    private void map()
    {
        Intent mapIntent = new Intent(getActivity(), MapActivity.class);
        startActivity(mapIntent);
    }

    private void parkingHistory()
    {
        Intent historyIntent = new Intent(getActivity(), HistoryActivity.class);
        startActivity(historyIntent);
    }

    private void aboutUsActivity()
    {
        Intent aboutUsIntent = new Intent(getActivity(), AboutUsActivity.class);
        startActivity(aboutUsIntent);
    }

    private void bookedSlot()
    {
        Intent aboutUsIntent = new Intent(getActivity(), BookedSlotsActivity.class);
        startActivity(aboutUsIntent);
    }

}