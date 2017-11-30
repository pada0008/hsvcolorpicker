package com.algonquincollege.pada0008.hsvcollerpicker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.algonquincollege.pada0008.hsvcollerpicker.Model.HSVColorPicker;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {


    public AboutDialog aboutDialog;
    public HSVColorPicker HSVObject;

    public SeekBar sHue;
    public SeekBar sSaturation;
    public SeekBar sValue;
    public TextView tColorSwatch;
    public TextView tHue;
    public TextView tSaturation;
    public TextView tValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ini elements
        aboutDialog = new AboutDialog();
        HSVObject = new HSVColorPicker();
        HSVObject.addObserver(this);
        tColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        sHue = (SeekBar) findViewById(R.id.sHue);
        tHue = (TextView) findViewById(R.id.hue);
        sSaturation = (SeekBar) findViewById(R.id.sSaturation);
        tSaturation = (TextView) findViewById(R.id.saturation);
        sValue = (SeekBar) findViewById(R.id.sValue);
        tValue = (TextView) findViewById(R.id.value);


        //
        sHue.setOnSeekBarChangeListener(this);
        sSaturation.setOnSeekBarChangeListener(this);
        sValue.setOnSeekBarChangeListener(this);
        tColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText( getApplicationContext(), getHSVMessage(), Toast.LENGTH_SHORT ).show();
                return true;
            }
        });

        this.updateView();

    }

    @Override
    public void update(Observable observable, Object o) {
        this.updateView();
    }


    // menu about
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.aboutmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuAbout){
            aboutDialog.show( getFragmentManager(), "About Me" );
            return true;
        }

        return false;
    }

    private String getHSVMessage(){
        return getResources().getString(
                R.string.hsv_values,
                HSVObject.getHue(),
                HSVObject.getSaturation(),
                HSVObject.getValue());
    }


    private void updateColorSwatch() {
        float[] hsvColor = {HSVObject.getHue(), HSVObject.getSaturation()/100.f, HSVObject.getValue()/100.f};
        tColorSwatch.setBackgroundColor(Color.HSVToColor(hsvColor));
    }

    private void updatesHue(){
        sHue.setProgress( HSVObject.getHue() );
    }
    private void updatesSaturation(){
        sSaturation.setProgress( HSVObject.getSaturation() );
    }
    private void updatesValue(){
        sValue.setProgress( HSVObject.getValue() );
    }

    public void updateView() {
        this.updateColorSwatch();
        this.updatesHue();
        this.updatesSaturation();
        this.updatesValue();
    }





    //SEEK BAR METHODS IMPLEMENTATION

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        //If came from user return
        if ( !b ) {
            return;
        }

        // - Change model with input value.
        // - Update Label with current value.
        switch ( seekBar.getId() ) {
            case R.id.sHue:
                HSVObject.setHue(sHue.getProgress());
                tHue.setText(getResources().getString(R.string.hue_progress, i).toUpperCase());
                break;

            case R.id.sSaturation:
                HSVObject.setSaturation(sSaturation.getProgress());
                tSaturation.setText(getResources().getString(R.string.saturation_progress, i).toUpperCase());
                break;

            case R.id.sValue:
                HSVObject.setValue(sValue.getProgress());
                tValue.setText(getResources().getString(R.string.value_progress, i).toUpperCase());
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        //Change text back to original value
        switch (seekBar.getId()) {
            case R.id.sHue:
                tHue.setText(getResources().getString(R.string.hue));
                break;
            case R.id.sSaturation:
                tSaturation.setText(getResources().getString(R.string.saturation));
                break;
            case R.id.sValue:
                tValue.setText(getResources().getString(R.string.value));
                break;
        }
    }


    //BUTTON METHODS IMPLEMENTATION

    public void onColorButtonClick(View view) {
        switch (view.getId()){
            case R.id.blackButton:
                HSVObject.setHSV(HSVObject.MINHUE, HSVObject.MINSATURATION, HSVObject.MINVALUE );
                break;
            case R.id.redButton:
                HSVObject.setHSV(HSVObject.MINHUE, HSVObject.MAXSATURATION, HSVObject.MAXVALUE);
                break;
            case R.id.limeButton:
                HSVObject.setHSV(120, 76, 80 );
                break;
            case R.id.blueButton:
                HSVObject.setHSV(240, HSVObject.MAXSATURATION, HSVObject.MAXVALUE);
                break;
            case R.id.yellowButton:
                HSVObject.setHSV(60, HSVObject.MAXSATURATION, HSVObject.MAXVALUE );
                break;
            case R.id.cyanButton:
                HSVObject.setHSV(180, HSVObject.MAXSATURATION, HSVObject.MAXVALUE );
                break;
            case R.id.magentaButton:
                HSVObject.setHSV(300, HSVObject.MAXSATURATION, HSVObject.MAXVALUE );
                break;
            case R.id.silverButton:
                HSVObject.setHSV(HSVObject.MINHUE, HSVObject.MINSATURATION, 75);
                break;
            case R.id.grayButton:
                HSVObject.setHSV(HSVObject.MINHUE, HSVObject.MINSATURATION, 50 );
                break;
            case R.id.maroonButton:
                HSVObject.setHSV(HSVObject.MINHUE, HSVObject.MAXSATURATION, 50);
                break;
            case R.id.oliveButton:
                HSVObject.setHSV(60, HSVObject.MAXSATURATION, 50 );
                break;
            case R.id.greenButton:
                HSVObject.setHSV(120, HSVObject.MAXSATURATION, 50);
                break;
            case R.id.purpleButton:
                HSVObject.setHSV(300, HSVObject.MAXSATURATION, 50);
                break;
            case R.id.tealButton:
                HSVObject.setHSV(180, HSVObject.MAXSATURATION, 50 );
                break;
            case  R.id.navyButton:
                HSVObject.setHSV(240, HSVObject.MAXSATURATION, 50 );
                break;
        }

        Toast.makeText( getApplicationContext(), getHSVMessage(), Toast.LENGTH_SHORT ).show();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        int[] hsvColor = { HSVObject.getHue(), HSVObject.getSaturation(), HSVObject.getValue() };
        savedInstanceState.putIntArray("HSV", hsvColor);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        if (savedInstanceState != null && savedInstanceState.containsKey("HSV")){
            int[] hsvColor = savedInstanceState.getIntArray("HSV");

            if (hsvColor != null && hsvColor.length == 3) {
                HSVObject.setHSV(hsvColor[0],hsvColor[1],hsvColor[2]);
            }
        }
    }



}
