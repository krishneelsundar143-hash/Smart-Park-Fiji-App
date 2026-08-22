package com.example.smartpark;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import android.widget.SearchView;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1; // You can choose any integer value you prefer


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GoogleMap googleMap;

    // Declare the SearchView and GoogleMap variables
    private SearchView searchView;


    public MapFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
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
        View view =  inflater.inflate(R.layout.fragment_map, container, false);

        // Obtain a reference to the SupportMapFragment
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);

        // Initialize the map
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        googleMap = map;

        // Customize your map here
        // For example, add a marker at a specific location
        //LatLng location = new LatLng(37.7749, -122.4194); // San Francisco coordinates
        //googleMap.addMarker(new MarkerOptions().position(location).title("Marker Title"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
        // Add a marker at a specific location (e.g., Fiji)
        LatLng fijiLocation = new LatLng(-17.7134, 178.0650); // Fiji coordinates
        googleMap.addMarker(new MarkerOptions().position(fijiLocation).title("Fiji"));
        // Move the camera to the marker location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fijiLocation, 8));

        // Check for location permission
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Enable user's current location
            googleMap.setMyLocationEnabled(true);
        }
        else
        {
            // Request location permission if not granted
            requestLocationPermission();
            //ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // Default
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }

    private void requestLocationPermission()
    {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))
        {
            // Explain to the user why you need the permission and then request it
            // You can show a dialog or provide an explanation here
        }
        else
        {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                // Permission granted, enable user's current location
                //googleMap.setMyLocationEnabled(true);

                // Permission granted, enable user's current location
                try
                {
                    googleMap.setMyLocationEnabled(true);
                }
                catch (SecurityException e)
                {
                    // Handle any exception related to setting MyLocationEnabled
                    e.printStackTrace();
                }
            }
            else
            {
                // Permission denied, handle accordingly (e.g., show a message)
                // You can inform the user that certain features won't work without location permission.
                // Permission denied, show a message to the user
                Snackbar.make(getView(), "Location permission is required to use this feature.", Snackbar.LENGTH_LONG)
                        .setAction("Settings", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                // Open app settings where the user can manually grant the permission
                                openAppSettings();
                            }
                        })
                        .show();
            }
        }
    }

    // Method to open the app settings
    private void openAppSettings()
    {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

}