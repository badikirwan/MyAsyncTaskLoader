package com.badikirwan.dicoding.myasynctaskloader;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<WeatherItems>> {

    private ListView listView;
    private WeatherAdapter adapter;
    private EditText editKota;
    private Button btnCari;
    private static final String EXTRAS_CITY = "EXTRAS_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new WeatherAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        editKota = (EditText) findViewById(R.id.edt_kota);
        btnCari = (Button) findViewById(R.id.btn_kota);
        btnCari.setOnClickListener(myListener);

        String kota = editKota.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_CITY, kota);

        getLoaderManager().initLoader(0, bundle, this);
    }

    //Fungsi untuk menjalankan proses myasynctaskloader
    @Override
    public Loader<ArrayList<WeatherItems>> onCreateLoader(int id, Bundle args) {

        String kumpulanKota = "";
        if (args != null) {
            kumpulanKota = args.getString(EXTRAS_CITY);
        }
        return new MyAsyncTaskLoader(this, kumpulanKota);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<WeatherItems>> loader, ArrayList<WeatherItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<WeatherItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String kota = editKota.getText().toString();

            if (TextUtils.isEmpty(kota)) {
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_CITY, kota);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
