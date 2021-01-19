package com.example.theseus.qrcode;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    Button generate_QRCode;
    ImageView qrCode;
    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextId;
    Button scanQr;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews();
        generateQrCode();
        return view;
    }

    private void generateQrCode() {
        generate_QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qrCodeString = createJson();
                Intent showQrCode = new Intent(getActivity(), GenerateQrCode.class);
                showQrCode.putExtra("qrCodedata", qrCodeString);
                startActivity(showQrCode);
            }
        });

        scanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scanqrCode = new Intent(getActivity(), ScanQrCode.class);
                startActivityForResult(scanqrCode, 100);
            }
        });
    }

    private String createJson() {
        JSONObject qrJsonObject = new JSONObject();
        try {
            qrJsonObject.put("first_name", editTextFirstName.getText().toString());
            qrJsonObject.put("last_name", editTextLastName.getText().toString());
            qrJsonObject.put("id", editTextId.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrJsonObject.toString();
    }

    private void initViews() {
        generate_QRCode = (Button) view.findViewById(R.id.generate_qr);
        editTextFirstName = (EditText) view.findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) view.findViewById(R.id.editTextLastName);
        editTextId = (EditText) view.findViewById(R.id.editTextId);
        scanQr = (Button) view.findViewById(R.id.scan_qr);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                String result = data.getStringExtra("Result");
                Toast.makeText(getActivity(), " " + result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
