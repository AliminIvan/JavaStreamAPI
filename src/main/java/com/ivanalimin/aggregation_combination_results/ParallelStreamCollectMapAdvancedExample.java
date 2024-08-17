package com.ivanalimin.aggregation_combination_results;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/*
Создайте коллекцию студентов, где каждый студент содержит информацию о предметах, которые он изучает, и его оценках по этим предметам.
Используйте Parallel Stream для обработки данных и создания Map, где ключ - предмет, а значение - средняя оценка по всем студентам.
Выведите результат: общую Map с средними оценками по всем предметам.
 */
public class ParallelStreamCollectMapAdvancedExample {
    public static void main(String[] args) {
        //Создайте коллекцию студентов, где каждый студент содержит информацию о предметах, которые он изучает,
        // и его оценках по этим предметам.
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        //Используйте Parallel Stream для обработки данных и создания Map, где ключ - предмет, а значение -
        // средняя оценка по всем студентам.
        Map<String, Double> avgGradesMap = students.parallelStream()
                .flatMap(student -> student.grades().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingDouble(Map.Entry::getValue)
                ));
        //Выведите результат: общую Map с средними оценками по всем предметам.
        avgGradesMap.forEach((subject, avgGrade) ->
                System.out.println("Subject: " + subject + ", Average grade: " + avgGrade));
    }
}
