package com.example.a.t13_location;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        final Geocoder geocoder = new Geocoder(this);



        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String str = "";
        // provider : 위치 정보 가져오는 역할?
        List<String> providers = manager.getAllProviders();
        for(int i=0; i<providers.size(); i++){
            str += "providers: "+providers.get(i)+" state: "+manager.isProviderEnabled(providers.get(i))+"\n";
        }
        textView.setText(str);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String s = "lat : "+location.getLatitude()+" log : "+location.getLongitude();
                textView.append(s);
                try {
                    List<Address> list
                    = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                    Address address = list.get(0);
                    textView.append(address.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
        textView.append("hello world");
    }
}
