package com.niklasarndt.aoc.twentytwenty;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bootstrap {

    private static final String STARS = "**************";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final List<Class<?>> exercises = new ArrayList<>();

    private Bootstrap() {
        loadExercises();
        System.out.printf("%02d exercises have been found.\n", exercises.size());
        runExecutionLoop();
    }

    public static void main(String[] args) {
        new Bootstrap();
    }

    /**
     * Loads all {@link Exercise}s using the reflections library.
     */
    private void loadExercises() {
        exercises.addAll(new Reflections("com.niklasarndt.aoc.twentytwenty")
                .getTypesAnnotatedWith(Exercise.class));
        exercises.sort((o1, o2) -> {
            Exercise first = (Exercise) o1.getDeclaredAnnotation(Exercise.class);
            Exercise second = (Exercise) o2.getDeclaredAnnotation(Exercise.class);
            return Integer.compare(first.value(), second.value());
        });
    }

    private void runExecutionLoop() {
        //Decide whether to run in endless mode
        /*String endless = Utils.readLine("Run in endless mode? [y/n]");

        boolean exit = !(endless.startsWith("j") || endless.startsWith("y"));
*/
        boolean exit = true;
        do {
            //Print existing exercises
            System.out.println(STARS);

            for (Class clazz : exercises) {
                Exercise e = (Exercise) clazz.getAnnotation(Exercise.class);
                System.out.printf("[%02d] Solution for day %02d\n", e.value(), e.value());
            }

            System.out.println(STARS);

            //Get user input
            String in = readExerciseIndex();

            //Check the edge cases
            if (in == null) {
                System.out.println("No input provided, exiting");
                exit = true;
                continue;
            }

            if (in.equalsIgnoreCase("e") || in.equalsIgnoreCase("exit")) {
                System.out.println("\nGoodbye!");
                exit = true;
                continue;
            }

            int number = Utils.parseInt(in, -1);
            //Parse input + execute if valid number
            Optional<Class<?>> exercise = exercises.stream().filter(item -> ((Exercise) item
                    .getAnnotation(Exercise.class)).value() == number).findFirst();

            exercise.ifPresentOrElse(item -> {
                System.out.println("\n[OUTPUT]\n");
                Timer.instance().start();
                runExercise(item);
                System.out.printf("\n[END OF OUTPUT, %dms]\n",
                        Timer.instance().stop());
            }, () -> System.out.println("The input you provided is invalid. Please try again!"));
        } while (!exit);
    }

    private String readExerciseIndex() {
        return Utils.readLine("Please type in the [number] of the " +
                "exercise you want to execute, or [exit]:");
    }

    /**
     * Runs an exercise with the given {@code index}.
     *
     * @param exercise The class to be executed.
     */
    private void runExercise(Class exercise) {

        try {
            //Obtain main method
            Method mainMethod = exercise.getMethod("main", String[].class);

            mainMethod.invoke(null, (Object) null); //This is the fake args string array
        } catch (NoSuchMethodException e) {
            //Exception is thrown -> No main method -> Use class-based approach
            try {
                //Invoke constructor (no parameters!)
                exercise.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                logger.error("Failed to execute exercise {} via Constructor", exercise.getSimpleName(), ex);
            }
        } catch (Exception ex) {
            logger.error("Failed to execute exercise {} via main method", exercise.getSimpleName(), ex);
        }
    }
}
