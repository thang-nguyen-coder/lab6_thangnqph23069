package com.example.lab6.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab6.R;

public class Bai1Activity extends AppCompatActivity implements View.OnClickListener {
    EditText edtFC;
    Button btnF, btnC;
    String strFC;
    TextView tvResult;
    int convertStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        edtFC = findViewById(R.id.edtF_C);
        btnF = findViewById(R.id.btnF);
        btnC = findViewById(R.id.btnC);
        tvResult = findViewById(R.id.tvResult);
        btnF.setOnClickListener(this);
        btnC.setOnClickListener(this);

    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnF) {
            invokeAsyncTask("Fahrenheit", Constants.F_TO_C_SOAP_ACTION, Constants.F_TO_C_METHOD_NAME);
            convertStyle = 1;
        } else if (view.getId() == R.id.btnC) {
            invokeAsyncTask("Celsius", Constants.C_TO_F_SOAP_ACTION, Constants.C_TO_F_METHOD_NAME);
            convertStyle = 0;
        }
    }

    //create and execute asynctask to get response from W3school server
    private void invokeAsyncTask(String convertParams, String soapAction,
                                 String methodName) {
        new ConvertTemperatureTask(this, soapAction, methodName,
                convertParams).execute(edtFC.getText()
                .toString().trim());
    }

    //call back data from background thread and set to views
    public void callBackDataFromAsyncTask(String result) {
        double resultTemperature = Double.parseDouble(result); //parse String to double
        if (convertStyle == 0) {// C degree to F degree
            tvResult.setText(edtFC.getText().toString().trim() + " degree Celsius = "
                    + String.format("%.2f", resultTemperature) + " degree Fahrenheit");
        } else {// F degree to C degree
            tvResult.setText(edtFC.getText().toString().trim() + " degree Fahrenheit = "
                    + String.format("%.2f", resultTemperature) + " degree Celsius");
        }
    }
}