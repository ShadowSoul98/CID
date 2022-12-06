 package mLinearMatrix;
 
 import java.util.stream.*;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.Math;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dataset {
    
 //   private final static double[][] x_data = new double[2][50];
 //   private final static double[] y_data = new double[50];
    private final static List<Double> list =new ArrayList<Double>();
    private static int n = 0;
    private static int l = 0;
    private static double data1[];
    private static double dataset[][]; 



   Dataset(String csvFile) {
        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br = new BufferedReader(new FileReader("C:/Users/jehu/Desktop/cid/java/dataset/" + csvFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);//array each tuple
               // System.out.print(data.length);
                data1 = new double[data.length];
                for (int i = 0;i < data.length;i++){
                     data1[i]= Double.parseDouble(data[i]);
                     list.add(data1[i]);
                }
                
                n++;//number of tuples\
                l = data.length;//number of columns
            }
            
  
            dataset = new double[l][n];
            for (int i = 0,j=0;i < n;i++){
                for (int k = 0;k < l;k++){
                    dataset[k][i] = list.get(j);
                   // System.out.print(dataset[k][i]+"\t\t");
                    j++;//traverse list retriving each index
                }
               //
            }
                
        
            //System.out.println(Arrays.deepToString(dataset));
            
            
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

    public double[] getYData() {
        return dataset[0];
    }

    public double[][] getXData() {
        return dataset;
    }

    public int getSize() {
        return n;
    }

    public int getTuples() {
        return l;
    }





  
}