package com.algonquincollege.pada0008.hsvcollerpicker.Model;

import java.util.Observable;


/**
 * HSV Model
 * Created by janki pada0005
 */

public class HSVColorPicker extends Observable {


    public int hue;
    public int saturation;
    public int value;
    public int MAXHUE = 359;
    public int MINHUE = 0;
    public int MAXSATURATION = 100;
    public int MINSATURATION = 0;
    public int MAXVALUE = 100;
    public int MINVALUE = 0;

    private void updateObservers(){
        this.setChanged();
        this.notifyObservers();
    }

    public HSVColorPicker() {
        setHue(MINHUE);
        setSaturation(MINSATURATION);
        setValue(MINVALUE);
    }

    public HSVColorPicker( int hue, int saturation, int value ) {
        super();
        setHSV(hue,saturation,value);
    }

    public int getHue() {
        return this.hue;
    }

    public void setHue(int hue) {
        if (hue <= this.MAXHUE && hue >= this.MINHUE) {
            this.hue = hue;
        }
        this.updateObservers();
    }

    public int getSaturation() {
        return this.saturation;
    }

    public void setSaturation(int saturation) {
        if (saturation <= this.MAXSATURATION && saturation >= this.MINSATURATION) {
            this.saturation = saturation;
        }
        this.updateObservers();
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        if (value <= this.MAXVALUE && value >= this.MINVALUE) {
            this.value = value;
        }
        this.updateObservers();
    }

    public void setHSV( int hue, int saturation, int value ) {
        setHue(hue);
        setSaturation(saturation);
        setValue(value);
    }

}
