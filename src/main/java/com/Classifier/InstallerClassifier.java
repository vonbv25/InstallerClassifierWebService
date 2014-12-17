package com.Classifier;

import com.rapidminer.Process;
import com.rapidminer.RapidMiner;
import com.rapidminer.example.Attribute;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.example.set.SimpleExampleSet;
import com.rapidminer.example.table.ExampleTable;
import com.rapidminer.operator.IOContainer;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorException;
import com.rapidminer.tools.XMLException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by von on 9/29/14.
 */
public class InstallerClassifier implements Classifier {


    private String getFile(String fileName) {

        StringBuilder result;

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        result = new StringBuilder(file.toString());

        return result.toString();

    }

    /**
     * Classify the dataset using logRes.
     * this method will produce 3 outputs, dataset classified by logRes and dataset classified by wj48
     * @param datasetPath dataset to be classified, must be in .csv
     */
    public ArrayList classify(String datasetPath) {

        String processPath = getFile("prediction.rmp");
        try {
            RapidMiner.setExecutionMode(RapidMiner.ExecutionMode.COMMAND_LINE);
            RapidMiner.init();
            Process process = new Process(new File(processPath));
            Operator read_csv= process.getOperator("Read CSV");
            read_csv.setParameter("csv_file",datasetPath);
            read_csv.freeMemory();

            IOContainer container = process.run();


            SimpleExampleSet  result=  (SimpleExampleSet) container.getElementAt(0);
            SimpleExampleSet  fileNames=  (SimpleExampleSet) container.getElementAt(1);
            Iterator<Example> it = result.iterator();
            Iterator <Example> fileNamesIter = fileNames.iterator();
            ArrayList<Installer> installers = new ArrayList<>();
            while (it.hasNext() && fileNamesIter.hasNext() ) {
                installers.add(
                        new Installer(fileNamesIter.next().toString(),
                                it.next().getPredictedLabel()==1.0 ? true:false)

                );

            }
            return installers;

        } catch (IOException | XMLException | OperatorException ex) {
            ex.printStackTrace();
        }

      return null;

    }

    public static void main(String[] args) {
        String data = "/home/von/IdeaProjects/InstallerClassification/Report/Dataset/research-installers.csv";
        InstallerClassifier in = new InstallerClassifier();
        System.out.println(in.classify(data).toString());




    }
}

