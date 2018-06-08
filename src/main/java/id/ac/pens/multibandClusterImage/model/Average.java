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
public class Average {
    
    private int cluster;
    private Float avg;

    public Average(int cluster, Float avg) {
        this.cluster = cluster;
        this.avg = avg;
    }

    /**
     * Get the value of avg
     *
     * @return the value of avg
     */
    public Float getAvg() {
        return avg;
    }

    /**
     * Set the value of avg
     *
     * @param avg new value of avg
     */
    public void setAvg(Float avg) {
        this.avg = avg;
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
