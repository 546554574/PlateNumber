
package com.toune.platenum.platenumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String plateNum;
    private Button plateNumTv;

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
