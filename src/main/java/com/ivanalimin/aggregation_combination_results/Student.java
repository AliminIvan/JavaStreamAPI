package com.ivanalimin.aggregation_combination_results;

import java.util.Map;

public record Student(String name, Map<String, Integer> grades) {

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grades=" + grades +
                '}';
    }
}
