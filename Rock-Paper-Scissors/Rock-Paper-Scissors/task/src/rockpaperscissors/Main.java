package rockpaperscissors;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static String computer;
    public static String user;
    public static String userName;
    public static Integer userScore;

    public static Integer halfArray;
    public static String allChoices;
    public static int y;

    public static ArrayList<String> choices=new ArrayList<String>();

    public static Integer computerRandom;
    public static void main(String[] args) {
        System.out.println("Enter your name:");
        Scanner sc =new Scanner(System.in);
        //read the user name and welcomes him
        userName=sc.next();
        userScore=0;

        System.out.println("Hello, "+userName);
        //read from a givven txt file named rating.txt
        File file=new File("rating.txt");
        //read all the file with .hasNext() and check if the userName is listed
        //if yes the assign the new userScore else userScore remains 0
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String name= scanner.next();
                int score=scanner.nextInt();
                if (name.equals(userName)){ userScore=score;
                break;}
            }
            scanner.close();
        } catch (FileNotFoundException e) {System.out.println("File not found");}


        //input all the choices and save them in an array
        sc.nextLine();
        allChoices=sc.nextLine();
        if(allChoices.equals("")){
             choices.add("rock");
             choices.add("paper");
             choices.add("scissors");

        }else{
            String str[] =allChoices.split(",");
            Collections.addAll(choices, str);}



        //start with the first user command
        System.out.println("Okay, let's start");
        user=sc.next();
        halfArray= (choices.size())/2;
        //test if the user input is an available move choise
            //if the user does not use exit the game runs in the while loop
            //it breaks the while loop only when the user wants to exit: "!exit" input
            while(!"!exit".equals(user)) {
                //checks if there is a valid input to run the game
                if(choices.contains(user)|| user.equals("!rating")){
                    //Create a random number (within ths size of the array length of available -> choices.length)
                    //Computer choses randomly from the choices array
                    Random random = new Random();

                    computerRandom = random.nextInt(choices.size());
                    computer = choices.get(computerRandom);
                    //we check if the computer wins by seeing if the previous half choices are chosen by the user
                    boolean checkComputerWin=false;
                    for(int i=computerRandom-halfArray;i<computerRandom;i++){
                        if(i<0){
                             y=choices.size()+i;
                        }else{y=i;}
                        if(choices.get(y).equals(user)){
                            checkComputerWin=true;
                            break;
                        }
                    }

                    //checks if it is a draw or if a user loses else the user won
                    if(user.equals("!rating")){
                        System.out.println("Your rating: "+userScore);
                    }
                    else if (user.equals(computer)) {
                        System.out.println("There is a draw (" + computer + ")");
                        userScore+=50;


                    } else if (checkComputerWin){
                        System.out.println("Sorry, but the computer chose " + computer);
                    } else {
                        System.out.println("Well done. The computer chose " + computer + "  and failed");
                        userScore+=100;

                    }
                    user=sc.next();
                }
                //else if the is is invalid input reads new input and returns to while loop
                else{System.out.println("Invalid input");
                user=sc.next();
                return;}

            }
            //if the user enters exit and breaks the while loop -> outputs Bye!
            System.out.println("Bye!");
            sc.close();
        }
}
