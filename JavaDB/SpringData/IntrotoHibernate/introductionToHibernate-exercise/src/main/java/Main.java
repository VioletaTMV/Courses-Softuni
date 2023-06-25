import exercises.*;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    final private static int AVAILABLE_NUMBER_OF_EXERCISES = 13;
    final private static int FIRST_EXERCISE_NUMBER = 2;
    final private static String WELCOME_MESSAGE = "Здравейте, моля напишете номер на задача от %d до %d, която искате да пуснете: ";
    final private static String WRONG_NUMBER_MESSAGE = "Не съществува задача с този номер. Моля подайте число между %d и %d ако искате да стартирате някоя задача.%nАко желаете да приключите, моля напишете END. ";
    final private static String GOOD_BUY_MESSAGE = "Благодаря! Приятен ден!";
    final private static String CHOSEN_EXERCISE_MESSAGE = "Избрахте упражнение %d:%s%n";
    final private static String CHOOSE_ANOTHER_EXERCISE_TO_RUN_MESSAGE_OR_END_TO_EXIT = "%nМоля подайте номер на друга задача, която искате да стартирате (м/у %d и %d), или напишете END ако желаете да приключите: ";
    final private static String EXCEPTION_MESSAGE = "Нещо се обърка, пробвайте пак да стартирате програмата и пробвайте с различни входни данни.";

    private static Map<Integer, String> EXERCISE_NUMBER_AND_NAME = new HashMap<>();

    public static void main(String[] args) {

        fillExerciseNumbersAndDescription();

        Scanner scanner = new Scanner(System.in);

        System.out.printf(WELCOME_MESSAGE, FIRST_EXERCISE_NUMBER, AVAILABLE_NUMBER_OF_EXERCISES);
        String userInput = scanner.nextLine();

        EntityManager em = Utils.getEntityManager();

        while (!"END".equals(userInput)) {

            try {

                int requestedNumExercise = Integer.parseInt(userInput);

                System.out.printf(CHOSEN_EXERCISE_MESSAGE, requestedNumExercise, EXERCISE_NUMBER_AND_NAME.get(requestedNumExercise));

                switch (requestedNumExercise) {
                    case 2:
                        _02_ChangeCasing.runExercise(em);
                        break;
                    case 3:
                        _03_ContainsEmployee.runExercise(em, scanner);
                        break;
                    case 4:
                        _04_EmployeeswithaSalaryOver50K.runExercise(em);
                        break;
                    case 5:
                        _05_EmployeesFromDepartment.runExercise(em);
                        break;
                    case 6:
                        _06_AddingaNewAddressAndUpdatingTheEmployee.runExercise(em, scanner);
                        break;
                    case 7:
                        _07_AddressesWithEmployeeCount.runExercise(em);
                        break;
                    case 8:
                        _08_GetEmployeesWithProject.runExercise(em, scanner);
                        break;
                    case 9:
                        _09_FindTheLatest10Projects.runExercise(em);
                        break;
                    case 10:
                        _10_IncreaseSalaries.runExercise(em);
                        break;
                    case 11:
                        _11_FindEmployeesByFirstName.runExercise(em, scanner);
                        break;
                    case 12:
                        _12_EmployeesMaximumSalaries.runExercise(em);
                        break;
                    case 13:
                        _13_RemoveTowns.runExercise(em, scanner);
                        break;
                    default:
                        System.out.printf(WRONG_NUMBER_MESSAGE, FIRST_EXERCISE_NUMBER, AVAILABLE_NUMBER_OF_EXERCISES);
                        break;
                }

                System.out.printf(CHOOSE_ANOTHER_EXERCISE_TO_RUN_MESSAGE_OR_END_TO_EXIT, FIRST_EXERCISE_NUMBER, AVAILABLE_NUMBER_OF_EXERCISES);
                userInput = scanner.nextLine();

            } catch (Exception e) {
                System.out.println(EXCEPTION_MESSAGE);
                return;
            }
        }

        System.out.println(GOOD_BUY_MESSAGE);
        em.close();
        scanner.close();

    }

    private static Map<Integer, String> fillExerciseNumbersAndDescription() {
        EXERCISE_NUMBER_AND_NAME.put(2, "Change casing");
        EXERCISE_NUMBER_AND_NAME.put(3, "Contains Employee");
        EXERCISE_NUMBER_AND_NAME.put(4, "Employees with a Salary Over 50 000");
        EXERCISE_NUMBER_AND_NAME.put(5, "Employees from Department");
        EXERCISE_NUMBER_AND_NAME.put(6, "Adding a New Address and Updating the Employee");
        EXERCISE_NUMBER_AND_NAME.put(7, "Addresses with Employee Count");
        EXERCISE_NUMBER_AND_NAME.put(8, "Get Employees with Project");
        EXERCISE_NUMBER_AND_NAME.put(9, "Find the Latest 10 Projects");
        EXERCISE_NUMBER_AND_NAME.put(10, "Increase Salaries");
        EXERCISE_NUMBER_AND_NAME.put(11, "Find Employees by First Name");
        EXERCISE_NUMBER_AND_NAME.put(12, "Employees Maximum Salaries");
        EXERCISE_NUMBER_AND_NAME.put(13, "Remove Towns");

        return EXERCISE_NUMBER_AND_NAME;
    }

}
