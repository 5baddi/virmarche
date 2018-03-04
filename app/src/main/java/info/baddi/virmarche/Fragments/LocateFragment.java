package info.baddi.virmarche.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import info.baddi.virmarche.Helpers.Command;
import info.baddi.virmarche.R;

public class LocateFragment extends Fragment implements OnMapReadyCallback{

    private final int REQUEST_CODE_ASK_PERMISSION = 123;
    private GoogleMap mMap;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locate, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*Command command = new Command(getActivity());
        command.requestCurrentPosition("+212612836207", "116417");*/

        bundle = this.getArguments();

        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when tnot installed on the device, the user will be prompted to install
     * it inside the SupportMapFragmenthe map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is . This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(bundle != null)
        {
            String title = getString(R.string.fragment_locate);
            // String snippet = "Latitude: " + bundle.getDouble("lat") + " Longitude: " +  bundle.getDouble("long");
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(bundle.getLong("timeStamp"));
//            Date date = calendar.getTime();
//            String snippet = "\nAt: " + DateFormat.getDateTimeInstance().format(date);
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            try {
                List<Address> addressList = geocoder.getFromLocation(bundle.getDouble("lat"), bundle.getDouble("long"), 1);
                if(addressList.size() > 0) title = addressList.get(0).getLocality();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.map_marker);
            Bitmap bitmap = bitmapDrawable.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(bitmap, 120, 120, false);

            LatLng currentPos = new LatLng(bundle.getDouble("lat"), bundle.getDouble("long"));
            mMap.addMarker(new MarkerOptions().position(currentPos).title(title).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).draggable(false));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPos, 10));
            mMap.setBuildingsEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }

        /*if((int)Build.VERSION.SDK_INT >= 23)
        {
            if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                if(!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))
                {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ASK_PERMISSION);
                }
            }
        }*/
    }

    /*private void enableMyLocationButton()
    {
        if(mMap == null) return;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case REQUEST_CODE_ASK_PERMISSION:
                if((grantResults[0] == PackageManager.PERMISSION_GRANTED) || (grantResults[1] == PackageManager.PERMISSION_GRANTED)) enableMyLocationButton();
                else Toast.makeText(getActivity(), "Access Location Denied !", Toast.LENGTH_SHORT).show();
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            break;
        }
    }*/
}
