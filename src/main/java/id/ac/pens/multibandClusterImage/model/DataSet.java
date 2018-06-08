/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.pens.multibandClusterImage.model;

import java.util.ArrayList;

/**
 *
 * @author faizalami
 */
public class DataSet {
    
    private ArrayList<Float> pixel = new ArrayList<Float>();
    private int index;
    private int cluster;

    public DataSet(int index, int cluster) {
        this.index = index;
        this.cluster = cluster;
    }

    /**
     * Get the value of cluster
     *
     * @return the value of cluster
     */
    public int getCluster() {
        return cluster;
    }
    
    public void addPixel(Float pixel) {
        this.pixel.add(pixel);
    }

    /**
     * Set the value of cluster
     *
     * @param cluster new value of cluster
     */
    public void setCluster(int cluster) {
        this.cluster = cluster;
    }


    /**
     * Get the value of index
     *
     * @return the value of index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set the value of index
     *
     * @param index new value of index
     */
    public void setIndex(int index) {
        this.index = index;
    }


    /**
     * Get the value of pixel
     *
     * @return the value of pixel
     */
    public ArrayList<Float> getPixel() {
        return pixel;
    }

    /**
     * Set the value of pixel
     *
     * @param pixel new value of pixel
     */
    public void setPixel(ArrayList<Float> pixel) {
        this.pixel = pixel;
    }

}
