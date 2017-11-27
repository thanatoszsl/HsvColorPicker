package model;

import java.util.Observable;


/**
 * *Created by thanatoszsl on 2017-11-27.
 *
 * Purpose/Description
 *
 * hold the data.
 *
 * @author Shenglin ZHou (zhou0121@algonquinlive.com)
 */

public class HSVModel extends Observable {

    //Class Variables
    public static final Integer MAX_HUE = 360;
    public static final Integer MIN_HSV = 0;
    public static final float MAX_SATURATION = 1;
    public static final float MAX_VALUE = 1;

    //Instance Variables
    private Integer hue;
    private float saturation;
    private float value;



    public HSVModel() {

        this(MAX_HUE, MAX_SATURATION, MAX_VALUE);
    }

    public HSVModel(Integer hue, float saturation, float value) {

        super();

        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    //Set values for each button
    public void asBlack() {
        this.setHSV(0, 0, 0);
    }

    public void asRed() {
        this.setHSV(0, 1, 1);
    }

    public void asLime() {
        this.setHSV(120, 1, 1);
    }

    public void asBlue() {
        this.setHSV(240, 1, 1);
    }

    public void asYellow() {
        this.setHSV(60, 1, 1);
    }

    public void asCyan() {
        this.setHSV(180, 1, 1);
    }

    public void asMagenta() {
        this.setHSV(300, 1, 1);
    }

    public void asSilver() {
        this.setHSV(0, 0, 0.75f);
    }

    public void asGray() {
        this.setHSV(0, 0, 0.5f);
    }

    public void asMaroon() {
        this.setHSV(0, 100, 0.5f);
    }

    public void asOlive() {
        this.setHSV(60, 1, 0.5f);
    }

    public void asGreen() {
        this.setHSV(120, 1, 0.5f);
    }

    public void asPurple() {
        this.setHSV(300, 1, 0.5f);
    }

    public void asTeal() {
        this.setHSV(180, 1, 0.5f);
    }

    public void asNavy() {
        this.setHSV(240, 1, 0.5f);
    }

    //Getters
    public Integer getHue() {

        return hue;
    }

    public float getSaturation() {

        return saturation;
    }

    public float getValue() {

        return value;
    }

    //Setters
    public void setHue(Integer hue) {

        this.hue = hue;
        this.updateObservers();
    }

    public void setSaturation(float saturation) {

        this.saturation = saturation;
        this.updateObservers();
    }

    public void setValue(float value) {

        this.value = value;
        this.updateObservers();
    }

    //Convenient setter: set H, S, V
    public void setHSV(Integer hue, float saturation, float value) {

        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
        this.updateObservers();
    }

    //Broadcast the update method to all registered observers
    private void updateObservers() {

        this.setChanged();
        this.notifyObservers();
    }
}
