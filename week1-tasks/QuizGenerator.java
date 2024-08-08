import java.util.ArrayList;
import java.util.Scanner;

class Question {
    String questionText;
    ArrayList<String> options;
    int correctAnswerIndex;

    public Question(String questionText, ArrayList<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public boolean isCorrect(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }
}

class Quiz {
    String title;
    ArrayList<Question> questions;

    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void takeQuiz() {
        Scanner in = new Scanner(System.in);
        int score = 0;
        
        for (Question question : questions) {
            System.out.println(question.questionText);
            for (int i = 0; i < question.options.size(); i++) {
                System.out.println((i + 1) + ". " + question.options.get(i));
            }
            System.out.print("Your answer: ");
            int answer = scanner.nextInt();
            if (question.isCorrect(answer - 1)) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong! The correct answer is: " + (question.correctAnswerIndex + 1));
            }
            System.out.println();
        }
        
        System.out.println("You scored " + score + " out of " + questions.size());
    }
}

public class QuizGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Quiz> quizzes = new ArrayList<>();

        while (true) {
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add questions to an existing quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter quiz title: ");
                String title = scanner.nextLine();
                quizzes.add(new Quiz(title));
                System.out.println("Quiz created successfully!\n");

            } else if (choice == 2) {
                if (quizzes.isEmpty()) {
                    System.out.println("No quizzes available. Please create a quiz first.\n");
                    continue;
                }

                System.out.println("Select a quiz to add questions:");
                for (int i = 0; i < quizzes.size(); i++) {
                    System.out.println((i + 1) + ". " + quizzes.get(i).title);
                }
                System.out.print("Choose a quiz: ");
                int quizIndex = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline

                if (quizIndex < 0 || quizIndex >= quizzes.size()) {
                    System.out.println("Invalid choice.\n");
                    continue;
                }

                System.out.print("Enter question: ");
                String questionText = scanner.nextLine();

                ArrayList<String> options = new ArrayList<>();
                System.out.print("Enter number of options: ");
                int numberOfOptions = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                for (int i = 0; i < numberOfOptions; i++) {
                    System.out.print("Enter option " + (i + 1) + ": ");
                    options.add(scanner.nextLine());
                }

                System.out.print("Enter the number of the correct answer: ");
                int correctAnswerIndex = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline

                quizzes.get(quizIndex).addQuestion(new Question(questionText, options, correctAnswerIndex));
                System.out.println("Question added successfully!\n");

            } else if (choice == 3) {
                if (quizzes.isEmpty()) {
                    System.out.println("No quizzes available. Please create a quiz first.\n");
                    continue;
                }

                System.out.println("Select a quiz to take:");
                for (int i = 0; i < quizzes.size(); i++) {
                    System.out.println((i + 1) + ". " + quizzes.get(i).title);
                }
                System.out.print("Choose a quiz: ");
                int quizIndex = scanner.nextInt() - 1;

                if (quizIndex < 0 || quizIndex >= quizzes.size()) {
                    System.out.println("Invalid choice.\n");
                    continue;
                }

                quizzes.get(quizIndex).takeQuiz();
                System.out.println();

            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;

            } else {
                System.out.println("Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }
}