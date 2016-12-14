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

        if (checkOK (spinnerinput, input.getText().toString())) {

            String intermediateValue = toBase10(spinnerinput, input.getText().toString());
            int intValue = Integer.parseInt(intermediateValue);

            switch (spinnerinput2) {

                case "Base 10": output.setText(toBase10(spinnerinput2, intermediateValue)); break;
                case "Base 2": output.setText(Integer.toBinaryString(intValue)); break;
                case "Base 8": output.setText(Integer.toOctalString(intValue)); break;
                case "Base 16": output.setText(Integer.toHexString(intValue).toUpperCase()); break;
            }
        } else {
            Context context = getApplicationContext();
            showToast(context, "Invalid input!");
        }

    } // end convert()

    // 'base' is the base being used;
// 'digits' is the number written in that base.
    private boolean checkOK(String base, String digits) {

        String regex;

        switch (base) {

            case "Base 2": regex = "[0-1]+"; break;
            case "Base 8": regex = "[0-7]+"; break;
            case "Base 10": regex = "[0-9]+"; break;
            case "Base 16": regex = "[0-9A-Fa-f]+"; break;

            default: return false;
        } // end switch

        return digits.matches(regex);

    } // end checkOK()

    // 'base' is the base being used;
// 'digits' is the number written in that base.
    private String toBase10(String base, String digits) {

        int base10;

        switch (base) {

            case "Base 2": base10 = Integer.parseInt(digits, 2); break;
            case "Base 8": base10 = Integer.parseInt(digits, 8); break;
            case "Base 10": base10 = Integer.parseInt(digits, 10); break;
            case "Base 16": base10 = Integer.parseInt(digits, 16); break;

            default:
                Context context = getApplicationContext();
                showToast(context, "Invalid base input to toBase10.");
                return "-1";

        } // end switch

        return Integer.toString(base10);

    } // end toBase10()

    public static void showToast(Context context, String text) { // Displays message to the user
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
