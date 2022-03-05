package com.stefania.versuri;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private EditText edtArtist,edtSong;
    private Button btnGet;
    private TextView textSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtArtist = findViewById(R.id.edtArtist);
        edtSong = findViewById(R.id.edtSong);
        btnGet = findViewById(R.id.btnGet);
        textSong = findViewById(R.id.textVersuri);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.lyrics.ovh/v1/" + edtArtist.getText().toString() + "/" +edtSong.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            textSong.setText(response.getString("lyrics"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            textSong.setText(getResources().getString(R.string.eroare_versuri));
                    }
                });

              requestQueue.add(jsonObjectRequest);
            }
        });



    }
}