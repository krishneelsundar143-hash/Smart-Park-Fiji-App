package com.example.smartpark;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParkingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParkingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View parkingFragmentView;

    private Button DamodarCity, RupsMallNakasi, TapoosCity, MHCC;

    public ParkingFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParkingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParkingFragment newInstance(String param1, String param2) {
        ParkingFragment fragment = new ParkingFragment();
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        parkingFragmentView =  inflater.inflate(R.layout.fragment_parking, container, false);

        InitializeFields();

        return parkingFragmentView;
    }

    private void InitializeFields()
    {
        DamodarCity = (Button) parkingFragmentView.findViewById(R.id.damo_city);
        DamodarCity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                slotActivity();
            }
        });

        RupsMallNakasi = (Button) parkingFragmentView.findViewById(R.id.rups);
        RupsMallNakasi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                slot2Activity();
            }
        });

        TapoosCity = (Button) parkingFragmentView.findViewById(R.id.tapo_city);
        TapoosCity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                slot3Activity();
            }
        });

        MHCC = (Button) parkingFragmentView.findViewById(R.id.mhcc);
        MHCC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                slot4Activity();
            }
        });
    }

    private void slot4Activity()
    {
        Intent slot4Intent = new Intent(getActivity(), Slot4Activity.class);
        slot4Intent.putExtra("location", " MHCC - "); // Pass the location as an extra
        startActivity(slot4Intent);
    }

    private void slot3Activity()
    {
        Intent slot3Intent = new Intent(getActivity(), Slot3Activity.class);
        slot3Intent.putExtra("location", " Tapoos City - "); // Pass the location as an extra
        startActivity(slot3Intent);
    }

    private void slot2Activity()
    {
        Intent slot2Intent = new Intent(getActivity(), Slot2Activity.class);
        slot2Intent.putExtra("location", " Rups Mall Nakasi - "); // Pass the location as an extra
        startActivity(slot2Intent);
    }


    private void slotActivity()
    {
        Intent slotIntent = new Intent(getActivity(), SlotActivity.class);
        slotIntent.putExtra("location", "Damodar City - "); // Pass the location as an extra
        startActivity(slotIntent);
    }

}