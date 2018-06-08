/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.pens.multibandClusterImage.model;

/**
 *
 * @author faizalami
 */
public class Distance {
    
    private int x;
    private int y;
    private Float distance;

    public Distance(int x, int y, Float distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    /**
     * Get the value of distance
     *
     * @return the value of distance
     */
    public Float getDistance() {
        return distance;
    }

    /**
     * Set the value of distance
     *
     * @param distance new value of distance
     */
    public void setDistance(Float distance) {
        this.distance = distance;
    }


    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public int getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(int x) {
        this.x = x;
    }

}
