package omanix.instantbaseconverter;

import android.content.Context;
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
            String regex = "[0-1]+";

            if (input.getText().toString().matches(regex)) {
                output.setText(binaryToDecimal(input.getText().toString()));
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Invalid characters for a binary number!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        else if(spinnerinput.equals("Base 10") && spinnerinput2.equals("Base 2")) {
            String regex = "[0-9]+";

            if(input.getText().toString().matches(regex)) {
                output.setText(decimalToBinary(input.getText().toString()));
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Invalid characters for a decimal number!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }








    }



    public static String binaryToDecimal(String input) {
        int decimalValue = Integer.parseInt(input, 2);
        return Integer.toString(decimalValue);
    }



    public static String decimalToBinary(String input) {
        int decimalValue = Integer.parseInt(input);
        return Integer.toBinaryString(decimalValue);
    }


}
