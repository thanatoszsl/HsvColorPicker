package com.algonquincollege.zhou0121.hsvcolorpicker;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;


/**
 * Purpose/Description
 *
 * Build buttons and dialog.
 *
 * Allows users to pick up colors based on Hue, Saturation and Brightness.
 *
 * @author Shenglin Zhou (zhou0121@algonquinlive.com)
 */


public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {

    private static final String ABOUT_DIALOG_TAG = "About Dialog";

    //Variables
    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueSB;

    private TextView mHueTV;
    private TextView mSaturationTV;
    private TextView mValueTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate a new HSV model

        mModel = new HSVModel();
        mModel.setHSV(HSVModel.MIN_HSV, HSVModel.MIN_HSV, HSVModel.MIN_HSV);

        mModel.addObserver(this);

        //Reference each View
        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);

        mHueTV = (TextView) findViewById(R.id.hue);
        mSaturationTV = (TextView) findViewById(R.id.saturation);
        mValueTV = (TextView) findViewById(R.id.value);

        //Set the domain (i.e. max)
        mHueSB.setMax(HSVModel.MAX_HUE);
        mSaturationSB.setMax(100);
        mValueSB.setMax(100);

        //Register event handles
        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);

        //Add a toast message when users clicked the button.
        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick( final View view) {

                float saturation = mModel.getSaturation();
                float value = mModel.getValue();

                int hue = mModel.getHue();

                saturation = saturation * 100;
                value = value * 100;

                String HSV = "H: " + hue + "\u00B0 S: " + (int) saturation + "% V: " + (int) value + "%";
                Toast.makeText(getApplicationContext(), HSV, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        this.updateView();
    }


    public void changeColourPreset(View v) {
        switch (v.getId()) {
            case R.id.blackButton:
                mModel.asBlack();
                break;
            case R.id.redButton:
                mModel.asRed();
                break;
            case R.id.limeButton:
                mModel.asLime();
                break;
            case R.id.blueButton:
                mModel.asBlue();
                break;
            case R.id.yellowButton:
                mModel.asYellow();
                break;
            case R.id.cyanButton:
                mModel.asCyan();
                break;
            case R.id.magentaButton:
                mModel.asMagenta();
                break;
            case R.id.silverButton:
                mModel.asSilver();
                break;
            case R.id.grayButton:
                mModel.asGray();
                break;
            case R.id.maroonButton:
                mModel.asMaroon();
                break;
            case R.id.oliveButton:
                mModel.asOlive();
                break;
            case R.id.greenButton:
                mModel.asGreen();
                break;
            case R.id.purpleButton:
                mModel.asPurple();
                break;
            case R.id.tealButton:
                mModel.asTeal();
                break;
            case R.id.navyButton:
                mModel.asNavy();
                break;
        }

        float saturation = mModel.getSaturation();
        float value = mModel.getValue();

        int hue = mModel.getHue();

        saturation = saturation * 100;
        value = value * 100;

        String HSV = "H: " + hue + "\u00B0 S: " + (int) saturation + "% V: " + (int) value + "%";
        Toast.makeText(getApplicationContext(), HSV, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser == false) {
            return;
        }

        //Determine which seekBar cause the event
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue(mHueSB.getProgress());
                String hue = getResources().getString(R.string.hueProgress, progress).toUpperCase() + "\u00B0";
                mHueTV.setText(hue);
                break;

            case R.id.saturationSB:
                float saturation = mSaturationSB.getProgress();
                saturation = saturation / 100;
                mModel.setSaturation(saturation);
                String sat = getResources().getString(R.string.saturationProgress, progress).toUpperCase() + "%";
                mSaturationTV.setText(sat);
                break;

            case R.id.valueSB:
                float value = mValueSB.getProgress();
                value = value / 100;
                mModel.setValue(value);
                String val = getResources().getString(R.string.valueProgress, progress).toUpperCase() + "%";
                mValueTV.setText(val);
                break;
        }
    }





    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText(getResources().getString(R.string.hue));
                break;
            case R.id.saturationSB:
                mSaturationTV.setText(getResources().getString(R.string.saturation));
                break;
            case R.id.valueSB:
                mValueTV.setText(getResources().getString(R.string.value));
                break;
        }
    }

    //Display the current values after refresh the view
    @Override
    public void update(Observable observable, Object data) {

        this.updateView();
    }


    //Get the hue value, saturation value, brightness value, and set seekbars
    private void updateHueSB() {

        mHueSB.setProgress(mModel.getHue());
    }

    private void updateSaturationSB() {
        float sat = mModel.getSaturation();
        sat = sat * 100;
        mSaturationSB.setProgress((int) sat);
    }

    private void updateValueSB() {
        float value = mModel.getValue();
        value = value * 100;
        mValueSB.setProgress((int) value);
    }

    //Set the background colour
    private void updateColorSwatch() {
        float[] hsv = {mModel.getHue(), mModel.getSaturation(), mModel.getValue()};
        mColorSwatch.setBackgroundColor(Color.HSVToColor(hsv));
    }

    //Synchronize each view component with the model
    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }

    //AboutDialog
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}