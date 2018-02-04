package com.kawal.latitudelongitude;

import android.location.Geocoder;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeocodingActivity extends AppCompatActivity {
    Button addressButton;
    TextView addressTV;
    TextView latLongTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocoding);

        addressTV = (TextView) findViewById(R.id.addressTV);
        latLongTV = (TextView) findViewById(R.id.latLongTV);


        addressButton = (Button) findViewById(R.id.addressButton);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.addressET);
                String address = editText.getText().toString();

                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(address,
                        getApplicationContext(), new GeocoderHandler());
            }
        });
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String locationAddress;
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            latLongTV.setText(locationAddress);
        }
    }

}