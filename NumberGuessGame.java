import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int totalScore = 0;
        int gamesPlayed = 0;
        String continuePlaying = "y";

        System.out.println("Guess a number between 1 and 100");

        while (continuePlaying.equalsIgnoreCase("y")) {
            gamesPlayed++;
            System.out.println("Game " + gamesPlayed);

            int attemptsUsed = playGame(inputScanner);
            int roundScore = calculateScore(attemptsUsed);

            totalScore += roundScore;
            System.out.println("Current score: " + totalScore);

            System.out.print("Play again? (y/n): ");
            continuePlaying = inputScanner.next();
        }

        System.out.println("Game over! You played " + gamesPlayed + " games with a total score of " + totalScore + ".");
        inputScanner.close();
    }

    private static int playGame(Scanner inputScanner) {
        int targetNumber = getRandomNumber(LOWER_BOUND, UPPER_BOUND);
        int attemptsCount = 0;

        while (attemptsCount < MAX_ATTEMPTS) {
            int userGuess = getUserGuess(inputScanner);
            attemptsCount++;
            String result = evaluateGuess(userGuess, targetNumber);
            System.out.println(result);

            if (result.equals("Correct!")) {
                return attemptsCount;
            }
        }

        System.out.println("You've exhausted all attempts. The correct number was " + targetNumber + ".");
        return MAX_ATTEMPTS + 1;
    }

    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private static int getUserGuess(Scanner inputScanner) {
        while (true) {
            try {
                System.out.print("Enter your guess: ");
                return Integer.parseInt(inputScanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static String evaluateGuess(int guess, int target) {
        if (guess < target) {
            return "Too low!";
        } else if (guess > target) {
            return "Too high!";
        } else {
            return "Correct!";
        }
    }

    private static int calculateScore(int attempts) {
        return attempts <= MAX_ATTEMPTS ? (MAX_ATTEMPTS - attempts + 1) : 0;
    }
  }
