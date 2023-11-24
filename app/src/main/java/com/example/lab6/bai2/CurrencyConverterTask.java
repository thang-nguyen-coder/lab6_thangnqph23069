package com.example.lab6.bai2;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab6.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
public class CurrencyConverterTask extends AsyncTask<Void, Void, String> {
    private static final String NAMESPACE = "http://www.webserviceX.NET/";
    private static final String URL = "http://www.webservicex.net/CurrencyConvertor.asmx";
    private static final String SOAP_ACTION = "http://www.webserviceX.NET/ConversionRate";
    private static final String METHOD_NAME = "ConversionRate";

    private Bai2Activity activity2;
    private String fromCurrency;
    private String toCurrency;
    public CurrencyConverterTask(Bai2Activity activity2, String fromCurrency, String toCurrency) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.activity2 = activity2;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            PropertyInfo fromProp = new PropertyInfo();
            fromProp.setName("FromCurrency");
            fromProp.setValue(fromCurrency);
            fromProp.setType(String.class);
            request.addProperty(fromProp);

            PropertyInfo toProp = new PropertyInfo();
            toProp.setName("ToCurrency");
            toProp.setValue(toCurrency);
            toProp.setType(String.class);
            request.addProperty(toProp);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            // Cập nhật giao diện người dùng với kết quả chuyển đổi tiền tệ
            TextView tvBai2 = activity2.tvBai2;
            tvBai2.setText("Kết quả chuyển đổi: " + result);
        } else {
            Toast.makeText(activity2, "Đã xảy ra lỗi khi chuyển đổi", Toast.LENGTH_SHORT).show();
        }
    }
}
