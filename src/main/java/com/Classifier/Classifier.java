package com.Classifier;


import java.util.ArrayList;

/**
 * Created by von on 12/16/14.
 */

/**
 * Interface for any classifier
 */
public interface Classifier {

    /**
     * takes the data in csv format and returns the list of labeled data
     * @param datasetPath data in csv format
     * @return a list of labeled data
     */
    public ArrayList classify(String datasetPath);


}
