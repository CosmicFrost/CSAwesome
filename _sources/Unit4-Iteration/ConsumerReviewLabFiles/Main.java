


class Main {
  public static void main(String[] args) {



    
    String fileName = "simpleReviewFake.txt";//"customReview.txt";


    //Activity 1
    System.out.println("--Activity 1--");
    String theReview = Review.textToString(fileName);

    System.out.println(theReview);
    
    //Activity 2
    System.out.println("--Activity 2--");
    double totSentimentValue = Review.totalSentiment(theReview);
    double starRating = Review.starRating(theReview);

    System.out.println("The sentiment value is: "+ totSentimentValue);
    System.out.println("The star rating is: "+starRating);

    //Activity 3
    System.out.println("--Activity 3--");
    System.out.println("This is a fake review that has words replaced from a review.");
    System.out.println(Review.fakeReview(fileName));




  }
}