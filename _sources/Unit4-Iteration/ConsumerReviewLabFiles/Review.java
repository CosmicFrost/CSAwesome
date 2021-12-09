import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.*;

/**
 * Class that contains helper methods for the Review Lab
 * (method removePunctuation() was added from teacher code)
 **/
public class Review {
  
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();
 
  
  private static final String SPACE = " ";
  
  static{
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
        //System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }
  
  
  //read in the positive adjectives in postiveAdjectives.txt
     try {
      Scanner input = new Scanner(new File("positiveAdjectives.txt"));
      while(input.hasNextLine()){
        String temp = input.nextLine().trim();
        System.out.println(temp);
        posAdjectives.add(temp);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }   
 
  //read in the negative adjectives in negativeAdjectives.txt
     try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while(input.hasNextLine()){
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }   
  }
  
  /** 
   * returns a string containing all of the text in fileName (including punctuation), 
   * with words separated by a single space 
   */
  public static String textToString( String fileName )
  {  
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
      
    }
    catch(Exception e){
      System.out.println("Unable to locate " + fileName);
    }
    //make sure to remove any additional space that may have been added at the end of the string.
    return temp.trim();
  }
  
  /**
   * @returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal( String word )
  {
    try
    {
      return sentiment.get(word.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }
  
  /**
   * Returns the ending punctuation of a string, or the empty string if there is none 
   */
  public static String getPunctuation( String word )
  { 
    String punc = "";
    for(int i=word.length()-1; i >= 0; i--){
      if(!Character.isLetterOrDigit(word.charAt(i))){
        punc = punc + word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }
  
  /**
   * Returns the word after removing any beginning or ending punctuation
   */
  public static String removePunctuation( String word )
  {
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(0)))
    {
      word = word.substring(1);
    }
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length()-1)))
    {
      word = word.substring(0, word.length()-1);
    }
    
    return word;
  }
  
  /** 
   * Randomly picks a positive adjective from the positiveAdjectives.txt file and returns it.
   */
  public static String randomPositiveAdj()
  {
    int index = (int)(Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }
  
  /** 
   * Randomly picks a negative adjective from the negativeAdjectives.txt file and returns it.
   */
  public static String randomNegativeAdj()
  {
    int index = (int)(Math.random() * negAdjectives.size());
    return negAdjectives.get(index);
    
  }
  
  /** 
   * Randomly picks a positive or negative adjective and returns it.
   */
  public static String randomAdjective()
  {
    boolean positive = Math.random() < .5;
    if(positive){
      return randomPositiveAdj();
    } else {
      return randomNegativeAdj();
    }
  }

 public static String fakeReview(String filename)
 {
   String generateFakeReview = textToString(filename);
   int wordCount = totalWords(generateFakeReview);
   String[] words = new String[wordCount];
   String[] newWords = new String[wordCount];

// for (int i = 0; i < wordCount; i++)
// {

  // System.out.println(generateFakeReview);
  // System.out.println(wordCount);

  Scanner inputFile = new Scanner(generateFakeReview);

  while (inputFile.hasNextLine())
  {
   String currentLine = inputFile.nextLine();
   
   words = currentLine.split(" ");
   
  }
  inputFile.close();

// }
// System.out.println("This is in words");
// for (int i = 0; i < words.length; i++)
// {
//   System.out.println(words[i]);
// }


String newReview ="";
for (int i = 0; i < wordCount; i++)

 if (words[i].contains("*"))
 {
   System.out.println(words[i]);
   if (i % 2 == 0)
   {
     newWords[i] = randomPositiveAdj();

   }
   else
   {
     newWords[i] = randomNegativeAdj();
   }
 }
 else 
 {
  newWords[i] = words[i];
 }


//  System.out.println("This is in new words");
// for (int i = 0; i < newWords.length; i++)
// {
//   System.out.println(newWords[i]);
// }

newReview = String.join(" ", newWords);


return newReview;

 }


 public static int totalWords(String filename)
  {
    // read in the file contents into a string using the textToString method with the filename

    // set up a sentimentTotal variable
    int wordCount = 0;

    // loop through the file contents 

       // find each word
       // add in its sentimentVal
       // set the file contents to start after this word

       Scanner inputFile = new Scanner(filename);

       while (inputFile.hasNextLine())
       {
        String currentLine = inputFile.nextLine();
        String[] words = currentLine.split(" ");
        
        for (String a: words)
      
        {

          wordCount +=1;
        }
        
       }

       inputFile.close();
    
   return wordCount; 
  }

/** Activity 2: totalSentiment()
  * Write the code to total up the sentimentVals of each word in a review.
 */
  public static double totalSentiment(String filename)
  {
    // read in the file contents into a string using the textToString method with the filename

    // set up a sentimentTotal variable
    double sentimentTotal = 0;

    // loop through the file contents 

       // find each word
       // add in its sentimentVal
       // set the file contents to start after this word

       Scanner inputFile = new Scanner(filename);

       while (inputFile.hasNextLine())
       {
        String currentLine = inputFile.nextLine();
        String[] words = currentLine.split(" ");
        
        for (String a: words)
      
        {
        //  System.out.println(a);
         // System.out.println(sentimentVal(a));
          sentimentTotal += sentimentVal(a);
        }
        
       }

       inputFile.close();
    
   return sentimentTotal; 
  }


  /** Activity 2 starRating method
     Write the starRating method here which returns the number of stars for the review based on its totalSentiment.
  */
  public static int starRating(String filename)
  {
    // call the totalSentiment method with the fileName

    // determine number of stars between 0 and 4 based on totalSentiment value 
    int stars = 0; // change this!
    // write if statements here

    double sentimentTotal = totalSentiment(filename);

    if (sentimentTotal < 0.0 )
    {
      stars = 0;
    }
    else if ( sentimentTotal > 0.0  && sentimentTotal < 5.0)
    {
      stars = 1;
    }
    else if ( sentimentTotal > 5.0 && sentimentTotal < 10.0)
    {
      stars = 2;
    }
    else if ( sentimentTotal > 10.0  && sentimentTotal < 15.0)
    {
      stars = 3;
    }
    else
    {
      stars = 4;
    }

  
    // return number of stars
    return stars; 
  }
}
