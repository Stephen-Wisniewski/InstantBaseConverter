package omanix.instantbaseconverter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

//import com.google.android.gms.ads.InterstitialAd; // Will possibly be used later

public class MainActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText output = (EditText) findViewById(R.id.output);

        output.setFocusable(false);

        AdView adView = (AdView) findViewById(R.id.adView);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        AdRequest adRequest = new AdRequest.Builder() .setRequestAgent("android_studio:ad_template").build();
        
        adView.loadAd(adRequest);

    } // end onCreate()

    public void convert(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        EditText input = (EditText) findViewById(R.id.input);
        EditText output = (EditText) findViewById(R.id.output);


        String spinnerinput =  spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
        String spinnerinput2 = spinner2.getItemAtPosition(spinner2.getSelectedItemPosition()).toString();

        if (checkOK (spinnerinput, input.getText().toString())) {

            String tempIntermediateValue = toBase10(spinnerinput, input.getText().toString());
            long intermediateValue = Long.parseLong(tempIntermediateValue);

            if(intermediateValue > Integer.MAX_VALUE) {
                Context context = getApplicationContext();
                showToast(context, "Number too large!");
            } else {


                switch (spinnerinput2) {

                    case "Base 10":
                        output.setText(toBase10(spinnerinput2, tempIntermediateValue));
                        break;
                    case "Base 2":
                        output.setText(Long.toBinaryString(intermediateValue));
                        break;
                    case "Base 8":
                        output.setText(Long.toOctalString(intermediateValue));
                        break;
                    case "Base 16":
                        output.setText(Long.toHexString(intermediateValue).toUpperCase());
                        break;
                }
            }

            // Show message to the user
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

        long base10;

        switch (base) {

            case "Base 2": base10 = Long.parseLong(digits, 2); break;
            case "Base 8": base10 = Long.parseLong(digits, 8); break;
            case "Base 10": base10 = Long.parseLong(digits, 10); break;
            case "Base 16": base10 = Long.parseLong(digits, 16); break;

            default:
                return "-1";

        } // end switch

        return Long.toString(base10);

    } // end toBase10()

    // Displays message to the user
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
} // end class
