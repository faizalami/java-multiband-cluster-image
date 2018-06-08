/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.pens.multibandClusterImage;

import id.ac.pens.multibandClusterImage.model.Average;
import id.ac.pens.multibandClusterImage.model.DataSet;
import id.ac.pens.multibandClusterImage.model.Distance;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author faizalami
 */
public class MultibandCluster {

    /**
     * 
     * {
     *  {{a, b, c},
     *   {d, e, f},
     *   {g, h, i}},
     * 
     *  {{j, k, l},
     *   {m, n, o},
     *   {p, q, r}}
     * }
     * 
     */
    private ArrayList<ArrayList<ArrayList<Float>>> images = new ArrayList<>();
    
    /**
     * 
     * {
     *  {a, b, c, d, e, f, g, h, i},
     * 
     *  {j, k, l, m, n, o, p, q, r}
     * }
     * 
     */
    private ArrayList<ArrayList<Float>> inlineImages = new ArrayList<ArrayList<Float>>();
    
    /**
     * 
     * {
     *  {a, j},
     * 
     *  {b, k},
     * 
     *  ...
     * 
     *  {i, r}
     * }
     * 
     */
    private ArrayList<DataSet> dataSet = new ArrayList<DataSet>();

    private Integer width = 0;
    
    private Integer height = 0;
    
    private Integer c;
    
    private ArrayList<Integer> columns = new ArrayList<Integer>();
    
    private int clusterCount = 0;
    
    private int maxCluster = 0;
    
    private ArrayList<Distance> distance = new ArrayList<Distance>();
    
    private ArrayList<Integer> clusters = new ArrayList<Integer>();

    public ArrayList<Integer> getClusters() {
        return clusters;
    }

    public void setClusters(ArrayList<Integer> clusters) {
        this.clusters = clusters;
    }

    public MultibandCluster(Integer c) {
        this.c = c;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }
    
    /**
     * Get the value of dataSet
     *
     * @return the value of dataSet
     */
    public ArrayList getDataSet() {
        return dataSet;
    }

    /**
     * Set the value of dataSet
     *
     * @param dataSet new value of dataSet
     */
    public void setDataSet(ArrayList dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * Get the value of images
     *
     * @return the value of images
     */
    public ArrayList getImages() {
        return images;
    }

    /**
     * Set the value of images
     *
     * @param images new value of images
     */
    public void setImages(ArrayList images) {
        this.images = images;
    }

    /**
     * Add the value of images
     *
     * @param image new value of image
     */
    public void addImage(int[][] image) {
        ArrayList<ArrayList<Float>> imageList = new ArrayList<ArrayList<Float>>();
        for (int[] row : image) {
            ArrayList<Float> imageRow = new ArrayList<Float>();
            for (int pixel : row) {
                imageRow.add(new Float(pixel));
            }
            imageList.add(imageRow);
        }
        
        if (imageList.size() > this.width) { 
            this.width = imageList.size();
        }
        
        if (imageList.get(0).size() > this.height) {
            this.height = imageList.get(0).size();
        }
        
        this.images.add(imageList);
        
        try {
            this.columns.set(this.images.size() - 1, this.images.size() - 1);
        } catch ( IndexOutOfBoundsException e ) {
            this.columns.add(this.images.size() - 1);
        }
        
        this.printImage(imageList);
    }
    
    public void convertImgToDataSet() {
        this.images.forEach((ArrayList<ArrayList<Float>> image) -> {
            ArrayList<Float> inlineImage = new ArrayList<Float>();
            image.forEach((ArrayList<Float> row) -> {
                row.forEach((Float pixel) -> {
                    inlineImage.add(pixel);
                });
            });
            this.inlineImages.add(inlineImage);
            this.printRow(inlineImage);
        });
        
        System.out.println();
        
        for (ArrayList<Float> image : inlineImages) {
            this.clusters = new ArrayList<Integer>();
            for (int i = 0; i< image.size(); i++) {
                
                try {
                    this.dataSet.get(i).addPixel(image.get(i));
                } catch ( IndexOutOfBoundsException e ) {
                    this.dataSet.add(new DataSet(i, i));
                    this.dataSet.get(i).addPixel(image.get(i));
                }
                
                try {
                    this.clusters.set(i, i);
                } catch ( IndexOutOfBoundsException e ) {
                    this.clusters.add(i);
                }
            }
        }
    }
    
    public void printImage(ArrayList<ArrayList<Float>> image) {
        image.forEach((ArrayList<Float> row) -> {
            String rowString = "";
            for (Float pixel : row) {
                rowString += pixel.toString().concat("\t");
            }
            System.out.println(rowString);
        });
        System.out.println();
    }
    
    public void printRow(ArrayList<Float> data) {
        String rowString = "";
        for (Float item : data) {
            rowString += item.toString().concat("\t");
        }
        System.out.println(rowString);
    }


    /**
     * Get the value of columns
     *
     * @return the value of columns
     */
    public ArrayList<Integer> getColumns() {
        return columns;
    }

    /**
     * Set the value of columns
     *
     * @param columns new value of columns
     */
    public void setColumns(ArrayList<Integer> columns) {
        this.columns = columns;
    }

    
    public Float calculateEuclidian(DataSet a, DataSet b) {
        double total = 0;
        for (int index : this.columns) {
            total += Math.pow((a.getPixel().get(index) - b.getPixel().get(index)), 2);
        }
        
        float euclidian = (float)Math.sqrt(total);
        
        return new Float(euclidian);
    }
    
    public void initDistance() {
        System.out.print("Init Distance");
        int in1 = 0;
        for (DataSet el1 : this.dataSet) {
            int in2 = 0;
            for (DataSet el2 : this.dataSet) {
                if(in1 >= in2) {
                    distance.add(new Distance(in1, in2, calculateEuclidian(el1, el2)));
                    System.out.println(".");
                }
                in2++;
            }
            in1++;
        }
        
        System.out.println();
        this.clusterCount = this.dataSet.size();
    }
    
    public void clusterAverage() {
        System.out.println("Cluster Process");
        int a = 0;
        while (clusterCount > c) {
                  
            ArrayList<DataSet> clusterA = this.filterDataSet(a);

            if (!clusterA.isEmpty()) {
                ArrayList<Integer> clusterIdB = this.filterClusterList(a);

                ArrayList<Average> compareDistanceCluster = new ArrayList();

                for (DataSet el1 : clusterA) {
                    for (int id : clusterIdB) {
                        ArrayList<DataSet> clusterB = this.filterDataSet(id);

                        ArrayList<Float> compareDistanceNode = new ArrayList<Float>();

                        int chooseCluster;
                        if(a > id) {
                            chooseCluster = a;
                        } else {
                            chooseCluster = id;
                        }

                        for (DataSet el2 : clusterB) {
                            if (el1.getIndex() < el2.getIndex()) {
                                Float findDistance = getDistanceByIndex(el2.getIndex(), el1.getIndex());
                                compareDistanceNode.add(findDistance);
                            }
                        }

                        if (!compareDistanceNode.isEmpty()) {
                            Average dataAvg = new Average(chooseCluster, this.countAverage(compareDistanceNode));

                            compareDistanceCluster.add(dataAvg);
                        }
                    }
                }

                int getMin = this.findMinAvg(compareDistanceCluster);

                if (!Objects.equals(getMin, a)) {
                    for (DataSet data : dataSet) {
                        if (Objects.equals(data.getCluster(), getMin)) {
                            dataSet.get(data.getIndex()).setCluster(a);
                        }
                    }

                    HashSet<Integer> clustersSet = new HashSet<Integer>();
                    ArrayList<Integer> clustersTemp = new ArrayList<Integer>();
                    for (int i = 0; i < dataSet.size(); i++) {
                        Integer cls = dataSet.get(i).getCluster();
                        if (!clustersSet.contains(cls)) {
                            clustersSet.add(cls);
                            clustersTemp.add(cls);
                        }
                    }
                    this.clusters = new ArrayList<Integer>();
                    this.clusters = clustersTemp;
                    this.clusterCount = clusters.size();
                    this.maxCluster = clusters.get(this.clusterCount - 1);
                    System.out.println("cluster count = " + clusterCount);
                }
            }
        
            if (a+1 < maxCluster) {
                a+=1;
            } else {
                a=0;
            }
        }
    }
    
    public Float getDistanceByIndex(int x, int y) {
        Float foundDistance = 0f;
        for (Distance d : distance) {
            if (d.getX() == x && d.getY() == y) {
                foundDistance = d.getDistance();
            }
        }
        
        return foundDistance;
    }
    
    public Float countAverage(ArrayList<Float> data) {
        Float total = 0f;
        for (Float item : data) {
            total += item;
        }
        
        return total / data.size();
    }
    
    public int findMinAvg(ArrayList<Average> avg) {
        Float minAvg = Float.MAX_VALUE;
        int minCluster = 0;
        
        for (Average item : avg) {
            if (item.getAvg() < minAvg) {
                minAvg = item.getAvg();
                minCluster = item.getCluster();
            }
        }
        
        return minCluster;
    }
    
    public ArrayList<DataSet> filterDataSet(int findCluster) {
        ArrayList<DataSet> found = new ArrayList<DataSet>();
        
        for(DataSet data : dataSet) {
            if (Objects.equals(data.getCluster(), findCluster)) {
                found.add(data);
            }
        }
        
        return found;
    }
    
    public ArrayList<Integer> filterClusterList(Integer findCluster) {
        ArrayList<Integer> found = new ArrayList<Integer>();
        
        for (int foundCluster : this.clusters) {
            if (!Objects.equals(foundCluster, findCluster)) {
                found.add(foundCluster);
            }
        }
        
        return found;
    }
    
    public ArrayList<ArrayList<Integer>> convertClusterToImage() {
        ArrayList<ArrayList<Integer>> converted = new ArrayList<ArrayList<Integer>>();
        
        int index = 0;
        for (int i = 0; i < this.width; i++) {
            String rowString = "";
            for (int j = 0; j < this.height; j++) {
                Integer data = dataSet.get(index).getCluster();
                index++;
                try {
                    converted.get(i).set(j, data);
                } catch ( IndexOutOfBoundsException e) {
                    converted.add(new ArrayList<Integer>());
                    try {
                        converted.get(i).set(j, data);
                    } catch ( IndexOutOfBoundsException ex) {
                        converted.get(i).add(0);
                        converted.get(i).set(j, data);
                    }
                    
                }
                rowString += converted.get(i).get(j).toString().concat("\t");
            }
            System.out.println(rowString);
        }
        System.out.println();
        
        return converted;
    }
}
