
package com.toune.platenum.platenumber;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.toune.eleven.platenumber.PlateNumDialog;

public class MainActivity extends AppCompatActivity {

    private String plateNum;
    private Button plateNumTv;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPlate(View view) {
        plateNumTv = (Button) view;
        PlateNumDialog plateNumDialog = new PlateNumDialog(this, plateNum) {
            @Override
            public void onDone(StringBuilder str) {
                plateNum = str.toString();
                plateNumTv.setText(plateNum);
            }

            @Override
            public void onCancle() {
            }
        }.builder().show();
    }
}
