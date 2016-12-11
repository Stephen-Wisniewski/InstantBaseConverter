package omanix.instantbaseconverter;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void convert(View view) {

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        EditText input = (EditText) findViewById(R.id.input);
        EditText output = (EditText) findViewById(R.id.editText);

        String spinnerinput =  spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
        String spinnerinput2 = spinner2.getItemAtPosition(spinner2.getSelectedItemPosition()).toString();

        if(spinnerinput.equals(spinnerinput2)) {
            output.setText(input.getText());
        }

        else if(spinnerinput.equals("Base 2") && spinnerinput2.equals("Base 10")) {
            output.setText(binaryToDecimal(input.getText().toString()));
        }






    }



    public static String binaryToDecimal(String input) {
        int decimalValue = Integer.parseInt(input, 2);
        return Integer.toString(decimalValue);
    }


}
