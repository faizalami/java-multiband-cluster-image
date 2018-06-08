/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.pens.multibandClusterImage.model;

import java.awt.Color;

/**
 *
 * @author faizalami
 */
public class ClusterColor {
    
    private int cluster;
    private Color color;

    public ClusterColor(int cluster, Color color) {
        this.cluster = cluster;
        this.color = color;
    }

    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the value of color
     *
     * @param color new value of color
     */
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * Get the value of cluster
     *
     * @return the value of cluster
     */
    public int getCluster() {
        return cluster;
    }

    /**
     * Set the value of cluster
     *
     * @param cluster new value of cluster
     */
    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

}
