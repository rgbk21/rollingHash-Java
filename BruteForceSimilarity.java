package com.company;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSimilarity {

    private ArrayList<String> s1_Array;//MultiSet S in the problem
    private ArrayList<String> s2_Array;//MultiSet T in the problem
    private ArrayList<String> markedS1 = new ArrayList<String>();//Unique elements of S
    private ArrayList<String> markedS2 = new ArrayList<String>();//Unique elements of T

    //Create a new array list of tuples with the unique element from sArray and the final count of
    //the number of times it occurs in the array.
    private ArrayList<CountingTuples> myCount = new ArrayList<>();

    //marked Array to store which strings have been counted
    //Multi Set U in the problem
    ArrayList<String> arrayU = new ArrayList<>();

    public void BruteForceSimilarity(String s1, String s2, int sLength) {

        //Split s1,s2 into array

        s1_Array = splitStringIntoArray(s1, sLength);
        s2_Array = splitStringIntoArray(s2, sLength);

//        for (int i = 0; i < s1_Array.size(); i++) {
////            System.out.println(s1_Array.get(i));
//        }
//        System.out.println("**************************");
//
//        for (int i = 0; i < s2_Array.size(); i++) {
//            System.out.println(s2_Array.get(i));
//        }

    }

    public float lengthOfS1() {
        int count = 0;
        float vectorSum = 0;
        float sum = 0;
        boolean flag = false;
//        System.out.println("******* LENGTH OF S1 STARTS************");

        for (int i = 0; i < s1_Array.size(); i++) {
            count = 1;
            //If marked array does not contain the element from s1_Array
            //add it to marked
            if (!markedS1.contains(s1_Array.get(i))) {
                markedS1.add(s1_Array.get(i));
                //System.out.println("size of myCount array is: " + myCount.size());
                //add the element and count to the myCount arraylist

                for (int j = i + 1; j < s1_Array.size(); j++) {
                    if (s1_Array.get(i).equals(s1_Array.get(j))) {
                        count++;
                    }
                }

                if (myCount.isEmpty()) {
//                    System.out.println("myCount was empty for " + s1_Array.get(i));
                    CountingTuples myTuple = new CountingTuples(s1_Array.get(i));
                    myTuple.setCount_In_s1(count);
                    myCount.add(myTuple);
                } else
                    {
                    for (int k = 0; k < myCount.size(); k++) {
                        if (myCount.get(k).getValue().equals(s1_Array.get(i))) {
                            flag = true;
//                            System.out.println("***Value already exists in myCount***");
//                            System.out.println("Value of myCount " + myCount.get(k).getValue());
//                            System.out.println("Value of s1_Array: " + s1_Array.get(i));
                            break;
                        }
                    }
                    if (!flag) {
                        CountingTuples myTuple = new CountingTuples(s1_Array.get(i));
                        myTuple.setCount_In_s1(count);
                        myCount.add(myTuple);

                    }
                }

//                System.out.println("count for " + s1_Array.get(i) + " " + count);
                sum += count * count;
//                System.out.println("Sum is " + sum);

            }

        }
        vectorSum = (float) Math.sqrt(sum);
//        System.out.println("Vector Sum is " + vectorSum);

//        System.out.println("******* LENGTH OF S1 ENDS************");

        return vectorSum;

    }

    public float lengthOfS2() {
        int count = 0;
        float vectorSum = 0;
        float sum = 0;

//        System.out.println("******* LENGTH OF S2 STARTS************");


        for (int i = 0; i < s2_Array.size(); i++) {
            count = 1;
            boolean flag = false;
            //If marked array does not contain the element from s1_Array
            //add it to marked
            if (!markedS2.contains(s2_Array.get(i))) {
                markedS2.add(s2_Array.get(i));

                //find the count of the number of times the element occurs in the s1_array

                for (int j = i + 1; j < s2_Array.size(); j++) {
                    if (s2_Array.get(i).equals(s2_Array.get(j))) {
                        count++;
                    }
                }

                //add the element and count to the myCount arraylist

                if (myCount.isEmpty()) {
//                    System.out.println("myCount was empty for " + s2_Array.get(i));
                    CountingTuples myTuple = new CountingTuples(s2_Array.get(i));
                    myTuple.setCount_In_s2(count);
                    myCount.add(myTuple);
                } else {
                    for (int k = 0; k < myCount.size(); k++) {
                        if (myCount.get(k).getValue().equals(s2_Array.get(i))) {
                            flag = true;
                            myCount.get(k).setCount_In_s2(count);
//                            System.out.println("***Value already exists in myCount***");
//                            System.out.println("Value of myCount " + myCount.get(k).getValue());
//                            System.out.println("Value of s2_Array: " + s2_Array.get(i));
                            break;
                        }
                    }
                    if (!flag) {
                        CountingTuples myTuple = new CountingTuples(s2_Array.get(i));
                        myTuple.setCount_In_s2(count);
                        myCount.add(myTuple);

                    }
                }


//                System.out.println("count for " + s2_Array.get(i) + " " + count);
                sum += count * count;
//                System.out.println("Sum is " + sum);

            }

        }
        vectorSum = (float) Math.sqrt(sum);
//        System.out.println("Vector Sum is " + vectorSum);
//        System.out.println("******* LENGTH OF S2 ENDS************");
        return vectorSum;

    }


    public void printMyCount() {
        System.out.println("***************Printing MY COUNT ARRAY LIST**********************");

        for (int i = 0; i < myCount.size(); i++) {
            System.out.println("String Value " + myCount.get(i).getValue());
            System.out.println("Count 1 " + myCount.get(i).getCount_In_s1());
            System.out.println("Count 2 " + myCount.get(i).getCount_In_s2());
        }
    }

    public float similarity() {

        float sum = 0;
        float similarity = 0;
        float vectorLength_S1;
        float vectorLength_S2;

        vectorLength_S1 = lengthOfS1();
        vectorLength_S2 = lengthOfS2();
//        System.out.println("Merging arrays in brute force..");
//        mergeArrayList();
//        System.out.println("Calculating sum in brute force");

        for (int j = 0; j < myCount.size(); j++)
        {
            sum += myCount.get(j).getCount_In_s1() * myCount.get(j).getCount_In_s2();

        }

        similarity = (float) sum / (vectorLength_S1 * vectorLength_S2);
        return similarity;
    }



    private ArrayList splitStringIntoArray(String str, int slength) {

        ArrayList<String> ret = new ArrayList<String>((str.length() + slength - 1) / slength);

        for (int start = 0; start < str.length() - slength + 1; start++) {
            ret.add(str.substring(start, Math.min(str.length(), start + slength)));
        }
        return ret;
    }


}
