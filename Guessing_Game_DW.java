// Name: Drakin Woodell
// Date: 4/5/2024
// Class: CS&145
// Assignment: Lab 1 - Guessing Game
// Citation: D/D
// Purpose: To prompt you to play a game of higher or lower until user no longer wants to

import java.util.Scanner;
import java.util.Random;

public class Guessing_Game_DW {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char playAgain;
        int bestGuess = Integer.MAX_VALUE;
        int gamesPlayed = 0;
        int totalGuesses = 0;

        Instructions();

        do {
            System.out.println("Do you want to play? (Y/N)");
            playAgain = input.next().charAt(0);
            if (playAgain == 'y' || playAgain == 'Y') {
            int tries = playGame(input);
                bestGuess = Math.min(bestGuess, tries);
                totalGuesses += tries;
                System.out.println("You got it in " + tries + " tries.");
                gamesPlayed++;
            }
            
        } while (playAgain == 'y' || playAgain == 'Y');
        System.out.println("Results:");
        System.out.println("Games played: " + gamesPlayed);
        System.out.println("Total guesses: " + totalGuesses);
        System.out.println("Best guess: " + (bestGuess == Integer.MAX_VALUE ? "N/A" : bestGuess));
    }

    public static void Instructions() {
        System.out.println("The program will think of a number between 1 and");
        System.out.println("1000 and will allow you to guess until");
        System.out.println("you get it. For each guess, It will tell you");
        System.out.println("whether the right answer is higher or lower");
    }

  public static int playGame(Scanner input) {
        Random rand = new Random();
        int r = rand.nextInt(1000) + 1;
       // System.out.println("Random number is: " + r);

        int playerGuess;
          int numGuess = 0;

        System.out.println("Pick a number between 1 and 1000");
        playerGuess = input.nextInt();

        while (playerGuess != r) {
            if (playerGuess > r) {
                System.out.println("GO LOWER");
                } 
         else {
                System.out.println("GO HIGHER");
         }
            numGuess++;
            playerGuess = input.nextInt();
        }

       return numGuess + 1;
    }
}