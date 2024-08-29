import java.util.Scanner;

public class GradeEvaluator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many subjects are there? ");
        int numberOfSubjects = scanner.nextInt();
        System.out.println("Please enter the marks (0-100) for each subject:");

        int[] subjectMarks = new int[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Marks for subject " + (i + 1) + ": ");
            subjectMarks[i] = scanner.nextInt();
        }

        int totalMarks = computeTotalMarks(subjectMarks);
        double averagePercentage = calculateAveragePercentage(totalMarks, numberOfSubjects);
        char grade = determineGrade(averagePercentage);

        System.out.println("\nResults Summary:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    private static int computeTotalMarks(int[] marks) {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum;
    }

    private static double calculateAveragePercentage(int total, int numberOfSubjects) {
        return (double) total / numberOfSubjects;
    }

    private static char determineGrade(double average) {
        if (average >= 90) {
            return 'A';
        } else if (average >= 80) {
            return 'B';
        } else if (average >= 70) {
            return 'C';
        } else if (average >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
