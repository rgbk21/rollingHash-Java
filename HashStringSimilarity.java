package com.company;

import java.util.ArrayList;
import java.util.List;

public class HashStringSimilarity {

    //    private int sLength2;
    HashTable HSS_Table_1 = new HashTable();
    HashTable HSS_Table_2 = new HashTable();
    HashTable HSS_Table_U = new HashTable();
    private ArrayList<String> s1_Array;//MultiSet S in the problem
    private ArrayList<String> s2_Array;//MultiSet T in the problem
    private ArrayList<String> ArrayU = new ArrayList<>(); //Unique elements from set S and T

    //COmment this out

//    private Tuple t = new Tuple(3526396, "seis");
//    private int count_t = 0;
    private List<Tuple>[] hashTable_s1; //Hash table containing elements from s1_Array
    private List<Tuple>[] hashTable_s2; //Hash table containing elements from s2_Array
    private List<Tuple>[] hashTable_U; //Hash table containing unique elements from s1 and s2

    public void HashStringSimilarity(String s1, String s2, int sLength) {

        s1_Array = splitStringIntoArray(s1, sLength);
        s2_Array = splitStringIntoArray(s2, sLength);
//        sLength2 = sLength;

        //Rollover hashing method

        //System.out.println("***** CREATING HASH TABLE S1 *********");
        hashTable_s1 = RolloverHashing(sLength, s1, s1_Array, HSS_Table_1);

        //System.out.println("***** CREATING HASH TABLE S2 *********");
        hashTable_s2 = RolloverHashing(sLength, s2, s2_Array, HSS_Table_2);

        //System.out.println("***** CREATING HASH TABLE U *********");
        hashTable_U = createHashTableU();

        //createArrayU();
        //printoutArrayU();
//        System.out.println("**** PRINTING HASH TABLE U ****");
//        printHashTable(hashTable_U);


    }

    public float lengthOfS1() {

        int count = 0;
        int squaredCount = 0;
        double sum = 0;
        int rolloverValue = 0;
        float vectorLength = 0;

        //System.out.println("*********** CALCULATING LENGTH OF S1 ****************");

        for (int i = 0; i < hashTable_U.length; i++) {
            for (int j = 0; j < hashTable_U[i].size(); j++) {
//                System.out.println("Searching for: " + hashTable_U[i].get(j).getValue());
                count = HSS_Table_1.search(hashTable_U[i].get(j));
//                System.out.println("count = " + count);
                squaredCount = count * count;
                sum += squaredCount;
//                System.out.println("Partial Sum = " + sum);
            }
        }

//        System.out.println("Final sum of vector length 1 is: " + sum);
        vectorLength = (float) Math.sqrt(sum);
//        System.out.println("Vector Length 1 is: " + vectorLength);
//        System.out.println("**** PRINTING HASH TABLE S1 ****");
//        printHashTable(hashTable_s1);

        return vectorLength;

    }

    public float lengthOfS2() {

        int count = 0;
        int squaredCount = 0;
        double sum = 0;
        int rolloverValue = 0;
        float vectorLength = 0;
//        count_t = HSS_Table_1.search(t);
//        System.out.println("7) Count_t is: " + count_t);
//        System.out.println("*********** CALCULATING LENGTH OF S2 ****************");
        for (int i = 0; i < hashTable_U.length; i++) {
            for (int j = 0; j < hashTable_U[i].size(); j++) {
//                System.out.println("Searching for: " + hashTable_U[i].get(j).getValue());
                count = HSS_Table_2.search(hashTable_U[i].get(j));
//                System.out.println("count = " + count);
                squaredCount = count * count;
                sum += squaredCount;
//                System.out.println("Partial Sum = " + sum);
            }
        }

//        System.out.println("Final sum of Vector Length 2 is: " + sum);
        vectorLength = (float) Math.sqrt(sum);
//        System.out.println("Vector Length 2 is: " + vectorLength);
//        System.out.println("**** PRINTING HASH TABLE S2 *****");
//        printHashTable(hashTable_s2);
//        count_t = HSS_Table_1.search(t);
//        System.out.println("9) Count_t is: " + count_t);
        return vectorLength;

    }

    public float similarity() {

        int count_1 = 0;
        int count_2 = 0;
        float sum = 0;
        int rolloverValue = 0;
        float similarity = 0;
        ArrayList<Tuple> t_1;
        ArrayList<Tuple> t_2;
//        System.out.println("****************************************************");
//        System.out.println("*********** CALCULATING SIMILARITY ****************");
//        System.out.println("****************************************************");

        for (int i = 0; i < hashTable_U.length; i++) {
            count_1 = 0;
            count_2 = 0;

            for (int j = 0; j < hashTable_U[i].size(); j++) {
//                System.out.println("Searching for: " + hashTable_U[i].get(j).getValue());
                count_1 = HSS_Table_1.search(hashTable_U[i].get(j));
                count_2 = HSS_Table_2.search(hashTable_U[i].get(j));
//                System.out.println("count_1 = " + count_1);
//                System.out.println("count_2 = " + count_2);

//                System.out.println("Earlier Value of sum: " + sum);
                sum = sum + (count_1 * count_2);
//                System.out.println("count_1 * count_2 = " + count_1 + " * " + count_2 + " = " + count_1 * count_2);
//                System.out.println("After Value of sum: " + sum);
            }
        }
//        System.out.println("Final value of similarity sum is: " + sum);
        similarity = sum / (lengthOfS1() * lengthOfS2());
        //System.out.println("Value of similarity is: " + similarity);
        return similarity;
    }


    private List<Tuple>[] createHashTableU() {

        //Adding elements from hashTable_s1 into ArrayU
        //Elements are copied from the hash table created
        //Elements contained in hash table are already in a way unique

        HSS_Table_U.HashTable(8);

//        count_t = HSS_Table_1.search(t);
//        System.out.println("4) Count_t is: " + count_t);

        for (int i = 0; i < hashTable_s1.length; i++) {
            for (int j = 0; j < hashTable_s1[i].size(); j++) {
//                System.out.println("hashTable_S1 element is: " + hashTable_s1[i].get(j).getValue());
                HSS_Table_U.add(hashTable_s1[i].get(j));
//                count_t = HSS_Table_1.search(t);
//                System.out.println("Inside loop: Count_t is: " + count_t);
            }
        }
//        System.out.println("Completing adding contents of S1");

//        count_t = HSS_Table_1.search(t);
//        System.out.println("5) Count_t is: " + count_t);

//        System.out.println("Adding contents of S2 to U");

        for (int i = 0; i < hashTable_s2.length; i++) {
            for (int j = 0; j < hashTable_s2[i].size(); j++) {
                if (HSS_Table_U.search(hashTable_s2[i].get(j)) == 0) {
                    HSS_Table_U.add(hashTable_s2[i].get(j));

//                    System.out.println("Element added to U in this loop was: " + hashTable_s2[i].get(j).getValue());
//                    count_t = HSS_Table_1.search(t);
//                    System.out.println("Inside second for loop Count_t is: " + count_t);
                }
            }
        }
//        count_t = HSS_Table_1.search(t);
//        System.out.println("6) Count_t is: " + count_t);

        return HSS_Table_U.returnHashTable();
    }

    private List<Tuple>[] RolloverHashing(int slength, String s, ArrayList<String> myArray, HashTable HSS_Table) {

        //What is a good initial size for a hash table?
        //Hash table will store the elements of array S: s1_array
        //Setting it to 4 right now without any reason
        HSS_Table.HashTable(8);

        String[] array1 = new String[s.length()];
//        System.out.println("slength value is: " + slength);
        int[] exponent = new int[slength];
        int alpha = 31;
        int asciiValue;

        //Calculating exponent array
        exponent[exponent.length - 1] = 1;
        for (int i = exponent.length - 1; i > 0; i--) {
            exponent[i - 1] = exponent[i] * alpha;
        }
//        for (int i=0; i < exponent.length; i++)
//        {
//            System.out.println(i + " th element is " + exponent[i]);
//        }

        //Doing all the work here. Split s1 into individual characters to
        //calculate the hashValue later
//        System.out.println("********** SPLITTING each element of myArray (s1Array and s2Array)*********");
        array1 = s.split("");
//        for (int i =0; i < array1.length; i++)
//        {
//            System.out.println(i + "th element in array1 is " + array1[i]);
//        }
        //Compute h1 -- the first hash value

        int h1 = 0;

//        System.out.println("********** Computing first Hash Value *********");

        for (int i = 0; i < slength; i++) {
            //If your string contains exactly one character the simplest way to convert
            // it to a character is probably to call the charAt method:
            char c = array1[i].charAt(0);
//            System.out.println("character c is : " + c);
            asciiValue = (int) c;
//            System.out.println("ascii value of c is " + asciiValue);
//            System.out.println("exponent[i] = " + exponent[i]);
//            System.out.println("Pre Value of h1 is: " + h1);
//            System.out.println("asciiValue * exponent[i] = " + asciiValue * exponent[i]);
            h1 = h1 + (asciiValue * exponent[i]);
//            System.out.println("Post Value of h1 is " + h1);
        }


        //Create a new tuple for h1
        Tuple newTuple = new Tuple(h1, myArray.get(0));
        HSS_Table.add(newTuple);

        //Calculate the remaining hash values
        char subtract;
        int asciiValue_subtract;
        int finally_subtract;
        char add;
        int asciiValue_add;
        int h_next;
        for (int i = 1; i < array1.length - slength + 1; i++) {
            subtract = array1[i - 1].charAt(0);
//            System.out.println("subtract char is: " + subtract);
            asciiValue_subtract = (int) subtract;
            finally_subtract = asciiValue_subtract * exponent[0];
//            System.out.println("Ascii value of subtract is " + asciiValue_subtract);
            add = array1[i + slength - 1].charAt(0);
//            System.out.println("add char is: " + add);
            asciiValue_add = (int) add;
//            System.out.println("Ascii value of add is " + asciiValue_add);
            h_next = (h1 - finally_subtract) * alpha + asciiValue_add;
//            System.out.println("value of i " + i);

            //And create new tuples for them as well

            Tuple newTuple_T = new Tuple(h_next, myArray.get(i));
            HSS_Table.add(newTuple_T);
            h1 = h_next;
        }

//        System.out.println("****** PRINTING HASH TABLE *****************");
//        HSS_Table.printHashTable();

        List<Tuple>[] hashTable = HSS_Table.returnHashTable();
        return hashTable;
    }

    private ArrayList splitStringIntoArray(String str, int slength) {

        ArrayList<String> ret = new ArrayList<String>((str.length() + slength - 1) / slength);//fixed its length

        for (int start = 0; start < str.length() - slength + 1; start++) {
            ret.add(str.substring(start, Math.min(str.length(), start + slength)));
        }
        return ret;
    }

    private void printHashTable(List<Tuple>[] myHashTable) {

        for (int i = 0; i < myHashTable.length; i++) {
            {
                for (int j = 0; j < myHashTable[i].size(); j++) {
                    System.out.println("In bucket number " + i);
                    System.out.println("Key is:" + myHashTable[i].get(j).getKey());
                    System.out.println("Value is:" + myHashTable[i].get(j).getValue());
                    System.out.println("Count is:" + myHashTable[i].get(j).getCount());

                }

            }
        }

    }

}
