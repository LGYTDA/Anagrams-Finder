import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Class for a program that finds the most number of anagarams
 * from an input file and prints them.
 * @author Lilita Getnet Yenew
 *         lgy2104
 * @version 1.0.1 May 6,2024
 */

public class Most{
    //Sources used:
    //https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/
    //https://www.w3schools.com/java/java_iterator.asp
    /**
     * Reads through the input file, processing each word to identify anagrams
     * and organizes them into a group/list. Each anagram grouping/list is associated
     * with a key formed by lexicographically sorting the lowercase characters of the
     * word and put into a map.
     * <p>
     * Passes the resulting map into a method that identifies the list with the most
     * number of anagrams.
     *
     * @param fileName The name of the file to be processed, containing a list of words
     * @param map The map structure to be populated with sorted keys and their corresponding list of
     *            anagrams.
     */
    private static void dictionaryProcessor(String fileName,MyMap<String,MyList<String>> map){
        BufferedReader reader;
        //tries reading the file line by line converting each word to lowercase
        //and sorting lexicographically to form the map keys
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line!=null){
                String lowerCase = line.toLowerCase();
                String key = insertionSort(lowerCase);
                MyList<String> anagrams = map.get(key);

                //no anagrams previously associated with the key
                //creates new pairing
                if(anagrams==null){
                    anagrams = new MyLinkedList<>();
                    map.put(key,anagrams);
                }
                //adds the original word to the list of anagrams
                anagrams.add(line);
                map.put(key,anagrams);

                //read nextline
                line = reader.readLine();
            }
        }
        catch(IOException ioe){
            System.err.println("Error: An I/O error occurred reading '" + fileName + "'.");
            System.exit(1);
        }

        //at this point we have a map populated with a key and a list(group) of anagrams
        //neither the strings within the list nor the groups themselves are sorted

        findMostAnagrams(map);
    }


    /**
     * Finds the group/list with the maximum number of anagrams
     * from the map passed to it. Passes the resulting list into
     * a method that displays the results.
     *
     * @param map The map containing a list of words that are anagrams
     *            of each other paired with their corresponding key.
     */
    private static void findMostAnagrams(MyMap<String,MyList<String>> map) {
        int groupCount = 0;
        int anagramCount=0;


        //finds max number of anagrams by iterating through mapping
        //and comparing size of list of each map entry
        Iterator<Entry<String, MyList<String>>> iter = map.iterator();
        while(iter.hasNext()){
            Entry<String, MyList<String>> item= iter.next();
            MyList<String> anagrams = item.value;
            int temp = anagrams.size();
            if(temp>anagramCount){
                    anagramCount = temp;
                }
            }

        //Organizes lists with the max number of anagrams into list
        if(anagramCount>1){
            MyList<MyList<String>> finalAnagramList = new MyLinkedList<>();
            Iterator<Entry<String, MyList<String>>> iter2 = map.iterator();
            while(iter2.hasNext()){
                Entry<String, MyList<String>> item= iter2.next();
                MyList<String> anagrams = item.value;
                if(anagrams.size()==anagramCount){
                    finalAnagramList.add(item.value);
                }
            }
            groupCount = finalAnagramList.size();
            //sorting strings within list
            for(int i=0; i<groupCount;i++){
                insertionSort(finalAnagramList.get(i));
            }
            //sorting anagram lists by their first entry
            insertionSort(finalAnagramList, groupCount);
            displayOutput(finalAnagramList,groupCount,anagramCount);
        }

         else{
            System.out.println("No anagrams found.");
        }
    }


    /**
     * Displays the maximum number of anagrams and the number of lists with the max
     * anagram count. Prints each list of anagrams as separate groups.
     *
     * @param anagramGroups list where each entry is a list of strings that are anagrams
     *                      of each other.
     * @param groupCount number of lists with the maximum number of anagrams
     * @param anagramCount maximum number of words that are anagrams of each other
     */
    private static void displayOutput(MyList<MyList<String>> anagramGroups, int groupCount, int anagramCount){
        System.out.println("Groups: " + groupCount + ", Anagram count: " + anagramCount);
        Iterator<MyList<String>> iter4 = anagramGroups.iterator();
        while(iter4.hasNext()){
            MyList<String> group= iter4.next();
            System.out.print("[");
            for(int i =0 ; i<group.size(); i++){
                System.out.print(group.get(i));
                if(i<group.size()-1){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    /**
     * Sorts the characters of a string using the insertion sort algorithm
     *
     * @param word the string to be sorted
     * @return the sorted word as a string
     */
    private static String insertionSort(String word){
        char[] letters = word.toCharArray(); //breaking up the word into letters
        int length = letters.length;

        for(int i = 1; i<length; ++i){
            int k;
            char current = letters[i];
            for(k = i-1; k>=0 && letters[k]>current; --k){
                letters[k+1]=letters[k];
            }
            letters[k+1] = current;
        }
        return String.valueOf(letters);
    }


    /**
     * Sorts a list of strings using the insertion sort algorithm
     *
     * @param list the list to be sorted
     */
    private static void insertionSort(MyList<String> list){
        int length = list.size();

        for(int i = 1; i<length; ++i){
            int k;
            String current = list.get(i);
            for(k = i-1; k>=0 && list.get(k).compareTo(current)>0; --k){
                list.set(k+1,list.get(k));
            }
            list.set(k+1,current);
        }
    }


    /**
     * Sorts a list of string lists using the insertion sort algorithm
     *
     * @param totalList the list of string lists to be sorted
     */
    private static void insertionSort(MyList<MyList<String>> totalList, int groupCount) {
        int length = totalList.size();

        for (int i = 1; i < length; ++i) {
            MyList<String> current = totalList.get(i);
            char currentFirstLetter = current.get(0).charAt(0);
            int k;
            for (k = i - 1; k >= 0 && totalList.get(k).get(0).charAt(0) > currentFirstLetter; --k) {
                totalList.set(k + 1, totalList.get(k));
            }
            totalList.set(k + 1, current);
        }
    }


    /**
     * The main method of the MostAnagaramsFinder program.
     *<p>
     * Takes in an input file and data structure preference from the user,validates them,
     * creates the appropriate data structure based on the input. It then passes both
     * the input file and data structure to a method that processes the file to find anagrams.
     *
     * @param args  a string array of 2 commandline arguments.
     *              args[0] = the filename/ path of the file to be processed for anagrams
     *              args[1] = name of the data structure("rbt","bst", or "hash" ) used to store
     *                        the contents of the file after it's been processed.
     */
    public static void main(String[] args){
        //Checks if number of arguments is correct
        if(args.length!=2){
            System.err.println("Usage: java MostAnagramsFinder <dictionary file> <bst|rbt|hash>");
            System.exit(1);
        }

        String inputFileName = args[0];
        String dataStructure = args[1];

        //checks if a file with the input name exists
        File inputFile = new File(inputFileName);
        if(!inputFile.exists()){
            System.err.println("Error: Cannot open file '" + args[0] + "' for input.");
            System.exit(1);
        }

        //checks if the input data structure is valid
        if(!dataStructure.equals("bst")&&!dataStructure.equals("rbt")&& !dataStructure.equals("hash")){
            System.err.println("Error: Invalid data structure '" + args[1] + "' received.");
            System.exit(1);
        }

        MyMap<String,MyList<String>> map;
        switch(dataStructure){
            case "bst":
                map = new BSTreeMap<>();
                break;
            case "rbt":
                map = new RBTreeMap<>();
                break;
            case "hash":
                map = new MyHashMap<>();
                break;
            default:
                //do nothing
                //won't be executed due to input validation above
                return; //unreachable, included to avoid compiler error
        }

        //processes the input file for anagrams
        dictionaryProcessor(inputFileName,map);
    }
}
