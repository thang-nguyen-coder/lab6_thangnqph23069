package com.example.lab6.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab6.R;

public class Bai2Activity extends AppCompatActivity implements View.OnClickListener{
    EditText edtBai2;
    Button btnBai2;
    TextView tvBai2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        edtBai2 = findViewById(R.id.edtBai2);
        btnBai2 = findViewById(R.id.btnBai2);
        tvBai2 = findViewById(R.id.tvBai2);
    }

    @Override
    public void onClick(View view) {
        String fromCurrency = edtBai2.getText().toString().trim();

        // Kiểm tra xem EditText có giá trị không
        if (!fromCurrency.isEmpty()) {
            // Gọi AsyncTask để thực hiện chuyển đổi tiền tệ
            new CurrencyConverterTask(this, fromCurrency, "EUR").execute();
        } else {
            // Hiển thị thông báo nếu EditText trống
            Toast.makeText(this, "Vui lòng nhập giá trị tiền tệ.", Toast.LENGTH_SHORT).show();
        }
    }
}