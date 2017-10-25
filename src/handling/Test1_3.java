package handling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Luis on 2017/10/25.
 */
public class Test1_3 {
    private static final String filePath1 = "res/exp_data/";
    private static final String filePath2 = "User_train_ratings.dat";
    private static final int TITLES = 3952;
    private static final int movie1 = 5;
    private static final int movie2 = 10;
    private static final int user1 = 1;
    private static final int user2 = 5;
    private static final boolean printTitleTable = false;
    private static final int USERNUM = 100;
    private static final boolean movie2UserFlag = false;
    private static final int COUNT = 1000;
    private static long sumCalcTime = 0;
    private static String user2List1 = "人の時、任意の二つのUserIDを入力に取り、";
    private static String user2List2 = "両方のユーザが見た映画のMovieIdとその映画のRatingのリストを出力する平均時間 : ";
    private static String movie2List1 = "人の時、任意の二つのMovieIDを入力に取り、";
    private static String movie2List2 = "両方の映画を見たユーザのUserIdとそのユーザが与えたRatingのリストを出力する平均時間 : ";

    public static void main(String[] args){
        List<IdAndRating> list = new ArrayList<>();
        RatingMatrix ratingMatrix = new RatingMatrix();
        ratingMatrix.loadData(decideFilePath(USERNUM));
        //ratingMatrix.printUserRatingList(movie1);
        //ratingMatrix.printMovieRatingList(user1);

        sumCalcTime = 0;
        measureCalculateTimeFrom2Ids(list,ratingMatrix);
        if (movie2UserFlag) {
            System.out.println(USERNUM + user2List1);
            System.out.println(user2List2 + sumCalcTime/COUNT + "(nanosec)");
        }
        else {
            System.out.println(USERNUM + movie2List1);
            System.out.println(movie2List2 + sumCalcTime/COUNT + "(nanosec)");
        }


        //ratingMatrix.printMovieRatingListFrom2Users(list,printTitleTable);
        //ratingMatrix.printUserRatingListFrom2Movies(movie1,movie2,printTitleTable);

    }

    public static void measureCalculateTimeFrom2Ids(List<IdAndRating> list, RatingMatrix ratingMatrix){
        Random rand = new Random();
        rand.setSeed(100);


        for (int i = 0;i < COUNT; i++){
            int id1,id2;
            if (movie2UserFlag) {
                id1 = rand.nextInt(TITLES) + 1;
                id2 = rand.nextInt(TITLES) + 1;
            } else {
                id1 = rand.nextInt(USERNUM) + 1;
                id2 = rand.nextInt(USERNUM) + 1;
            }

            long start = System.nanoTime();
            list = ratingMatrix.makeListFrom2Ids(id1,id2,list,movie2UserFlag);
            long end = System.nanoTime();
            sumCalcTime += end - start;
        }
    }

    public static String decideFilePath(int userNum){
        return filePath1 + String.valueOf(userNum) + filePath2;
    }
}