package com.example.nick.orgsmobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventAttendeesFragment extends Fragment {

    View eventAttendeesView;

    public EventAttendeesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        eventAttendeesView = inflater.inflate(R.layout.event_attendees_fragment, container, false);

        final Button scanQRCode = eventAttendeesView.findViewById(R.id.scanQRCode);
        scanQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQRCode();
            }
        });

        return  eventAttendeesView;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(eventAttendeesView.getContext(), "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(eventAttendeesView.getContext(), result.getContents(), Toast.LENGTH_LONG).show();
                Log.d("QR Result", result.getContents());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public void scanQRCode(){
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan Student Attendee QR Code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.forSupportFragment(EventAttendeesFragment.this).initiateScan();
    }


}
