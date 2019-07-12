package com.pungkyaw.uas2019;




import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ilyasayusuf.uas2019.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextNim;
    private EditText editTextAlamat;

    private Button buttonAdd;
    private Button buttonView;
    private Spinner spinJk;
    String KEELAS;

    String[] JK = {"A", "B","C","D"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText)findViewById(R.id.nama);
        editTextNim = (EditText)findViewById(R.id.nik);
        editTextAlamat = (EditText)findViewById(R.id.jam);
        spinJk = findViewById(R.id.kelas);

        buttonAdd = (Button)findViewById(R.id.btnsimpan);
        buttonView = (Button)findViewById(R.id.btnhapus);


        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, JK);
        spinJk.setAdapter(adapter);
    }

    private void addEmployee(){
        final String name = editTextName.getText().toString().trim();
        final String nik = editTextNim.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String spin = spinJk.getSelectedItem().toString().trim();

        class AddEmployee extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_NAMA, name);
                params.put(Konfigurasi.KEY_EMP_NIM, nik);
                params.put(Konfigurasi.KEY_EMP_ALAMAT, alamat);
                params.put(Konfigurasi.KEY_EMP_JENISKELAMIN, spin);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
                return res;
            }
        }
        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    private void addEmployee1(){
        final String name = editTextName.getText().toString().trim();
        final String nik = editTextNim.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        if (spinJk.getSelectedItem().toString().equals(JK[0])){
            KEELAS = "A";
        }
        else if (spinJk.getSelectedItem().toString().equals(JK[1])){
            KEELAS = "B";
        }
        else if (spinJk.getSelectedItem().toString().equals(JK[2])){
            KEELAS = "C";
        }
        else {
            KEELAS = "D";
        }

        class AddEmployee extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_NAMA, name);
                params.put(Konfigurasi.KEY_EMP_NIM, nik);
                params.put(Konfigurasi.KEY_EMP_ALAMAT, alamat);
                params.put(Konfigurasi.KEY_EMP_JENISKELAMIN, KEELAS);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
                return res;
            }
        }
        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonAdd){
           addEmployee();
        }
        else if (view == buttonView){
            Intent a = new Intent(MainActivity.this, Tampil.class);
            startActivity(a);
        }
    }
}

