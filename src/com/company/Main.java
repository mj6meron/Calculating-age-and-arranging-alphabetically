package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int legalAge = 18;
        List<Person> people = creater();
        Map<String, Integer> mappedPeople = listToMapWithConcatAndAge(people);
        System.out.println("People who are above 18-years old:");
        displayer(mappedPeople, (x -> x > legalAge));
    }


    // display all above legal limit
    private static void displayer(Map<String, Integer> people, filterAge filterAge) {
        people.entrySet().stream()                              // Stream maps entry pairs
                .filter(x-> filterAge.testAge(x.getValue()))    //..filter using passed lambda
                .map(Map.Entry::getKey)                         //..map to only show names
                .sorted()                                       //..sort to alphabetical order
                .forEach(System.out::println);                  //..print all names
    }


    private static List<Person> creater() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Mike","Jenner", 1960));
        people.add(new Person("John", "Peter", 1996));
        people.add(new Person("Chris", "Patrik", 2002));
        people.add(new Person("Jennifer", "Lopez",2006));
        people.add(new Person("Jessica", "Henry", 2010));
        return people;
    }

    private static Map<String, Integer> listToMapWithConcatAndAge(List<Person> people) {
        return people.stream()                  // Stream list of persons
                .collect(Collectors.toMap(      //..using collector to map pairs
                        Main::concatName,       //..using method in main to concat names
                        Main::calculateAge));   //..using method in main to calculate age
    }


    private static int calculateAge(Person person) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year - person.birthYear();
    }

    private static String concatName(Person person) {
        return (person.firstName() + ", " + person.lastName());
    }
}

@FunctionalInterface
interface filterAge{
    boolean testAge(int age);
}


record Person(String firstName, String lastName, int birthYear){ }
// check the capitalization of the names
// make own int method(int i)