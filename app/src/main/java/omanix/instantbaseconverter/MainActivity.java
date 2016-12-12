package omanix.instantbaseconverter;

import android.content.Context;
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
                showToast(context, "Invalid characters for a base 2 number!");

            }
        }

        else if(spinnerinput.equals("Base 10") && spinnerinput2.equals("Base 2")) {
            String regex = "[0-9]+";

            if(input.getText().toString().matches(regex)) {
                output.setText(decimalToBinary(input.getText().toString()));
            } else {
                Context context = getApplicationContext();
                showToast(context, "Invalid characters for a base 10 number!");
            }
        }

        else if(spinnerinput.equals("Base 10") && spinnerinput2.equals("Base 16")) {
            String regex = "[0-9]+";

            if(input.getText().toString().matches(regex)) {
                output.setText(decimalToHex(input.getText().toString()));
            } else {
                Context context = getApplicationContext();
                showToast(context, "Invalid characters for a base 10 number!");
            }
        }

        else if(spinnerinput.equals("Base 10") && spinnerinput2.equals("Base 8")) {
            String regex = "[0-9]+";

            if(input.getText().toString().matches(regex)) {
                output.setText(decimalToOctal(input.getText().toString()));
            } else {
                Context context = getApplicationContext();
                showToast(context, "Invalid characters for a base 10 number!");
            }
        }


    }

    public static String binaryToDecimal(String input) {
        int decimalValue = Integer.parseInt(input, 2);
        return Integer.toString(decimalValue);
    }

    public static String binaryToHex(String input) {
        return "x";

    }

    public static String decimalToBinary(String input) {
        int decimalValue = Integer.parseInt(input);
        return Integer.toBinaryString(decimalValue);
    }

    public static String decimalToHex(String input) {
        int decimalValue = Integer.parseInt(input);
        return Integer.toHexString(decimalValue).toUpperCase();
    }

    public static String decimalToOctal(String input) {
        int decimalValue = Integer.parseInt(input);
        return Integer.toOctalString(decimalValue);
    }



    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }


}
