import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class QuizApp {
    private static final int TIME_LIMIT_SECONDS = 10;
    private static final QuizQuestion[] QUESTIONS = {
        new QuizQuestion("What does CPU stand for?", new String[] {
            "A. Central Processing Unit", 
            "B. Central Processing Unit", 
            "C. Central Processor Unit", 
            "D. Central Processing Unit"
        }, 'B'),
        new QuizQuestion("What is the primary purpose of RAM in a computer?", new String[] {
            "A. To store data permanently", 
            "B. To run the operating system", 
            "C. To store data temporarily", 
            "D. To connect to the internet"
        }, 'C'),
        new QuizQuestion("Which company developed the Java programming language?", new String[] {
            "A. Microsoft", 
            "B. Apple", 
            "C. Sun Microsystems", 
            "D. Google"
        }, 'C')
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("\n<---------You have only 10 seconds to answer each question.---------->\n");

        for (QuizQuestion question : QUESTIONS) {
            if (askQuestion(scanner, question)) {
                score++;
            }
        }

        System.out.println("Game Over..........");
        displayResults(score);
        scanner.close();
    }

    private static boolean askQuestion(Scanner scanner, QuizQuestion question) {
        System.out.println(question.getQuestion());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> timeoutTask = executor.schedule(() -> System.out.println("Your Time's up!"), TIME_LIMIT_SECONDS, TimeUnit.SECONDS);

        String userInput = scanner.nextLine().toUpperCase();
        timeoutTask.cancel(true);
        executor.shutdown();

        return question.isCorrectAnswer(userInput.length() > 0 ? userInput.charAt(0) : ' ');
    }

    private static void displayResults(int score) {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Score: " + score + "/" + QUESTIONS.length);
        for (QuizQuestion question : QUESTIONS) {
            System.out.println(question.getQuestion() + " - Correct Answer: " + question.getCorrectAnswer());
        }
    }
}

class QuizQuestion {
    private final String question;
    private final String[] options;
    private final char correctAnswer;

    public QuizQuestion(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrectAnswer(char answer) {
        return answer == correctAnswer;
    }
}
