package com.example.tugasmenuoption;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AbsenActivity extends AppCompatActivity {


    TextView tvTanggal, tvWaktu;
    EditText etKet;
    Spinner sp;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

        tvTanggal = findViewById(R.id.tv_tanggal);
        tvWaktu = findViewById(R.id.tv_waktu);
        etKet = findViewById(R.id.et_ket);
        btnSubmit = findViewById(R.id.btn_submit);


        Spinner spinner = findViewById(R.id.sp_ket);

        //Inisialiasi Array Adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ket_masuk, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show();

                if (adapter.getItem(i).equals("Hadir tepat waktu")){
                    etKet.setVisibility(View.INVISIBLE);
                }
                else{
                    etKet.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        tvTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        tvWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

    }
    public void showAlertDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AbsenActivity.this);
        alertBuilder.setTitle("Konfirmasi");
        alertBuilder.setMessage("Apakah kamu yakin data yang akan kamu kirim sudah sesuai?");

        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast toast = Toast.makeText(getApplicationContext(), "Anda Berhasil", Toast.LENGTH_SHORT);
                toast.show();
                tvTanggal.setText("");
                tvWaktu.setText("");
                etKet.setText("");
            }
        });
        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvTanggal.setText("");
                tvWaktu.setText("");
                etKet.setText("");

            }
        });

        alertBuilder.show();
    }

    public void showDatePicker(){
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(),"date-picker");
    }

    public void showTimePicker(){
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "time-picker");
    }

    public void processDatePickerResult(int day, int month, int year){
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month+1);
        String year_string = Integer.toString(year);

        String dateMessage = day_string + "-" + month_string + "-" + year_string;
        tvTanggal.setText(dateMessage);
    }

    public void processTimePickerResult(int minute, int hour){
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);

        String timeMessage = hour_string + " : " + minute_string;
        tvWaktu.setText(timeMessage);
    }
}