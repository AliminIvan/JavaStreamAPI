package com.ivanalimin.generating_numbers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Предположим, у нас есть список заказов, и каждый заказ представляет собой продукт и его стоимость.
Задача состоит в использовании Stream API и коллекторов для решения следующих задач:
Создайте список заказов с разными продуктами и их стоимостями.
Группируйте заказы по продуктам.
Для каждого продукта найдите общую стоимость всех заказов.
Отсортируйте продукты по убыванию общей стоимости.
Выберите три самых дорогих продукта.
Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
 */
public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("TV", 2000.0)
        );
        //Создайте список заказов с разными продуктами и их стоимостями
        Map<String, Double> uniqueProducts = orders.stream()
                .collect(Collectors.toMap(
                        Order::product,
                        Order::cost,
                        (existing, replacement) -> existing
                ));
        System.out.println(uniqueProducts);
        System.out.println("*".repeat(100));

        //Группируйте заказы по продуктам
        Map<String, List<Order>> groupedByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::product));
        System.out.println(groupedByProduct);
        System.out.println("*".repeat(100));

        //Для каждого продукта найдите общую стоимость всех заказов.
        Map<String, Double> totalCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::product,
                        Collectors.summingDouble(Order::cost)
                ));
        System.out.println(totalCostByProduct);
        System.out.println("*".repeat(100));

        //Отсортируйте продукты по убыванию общей стоимости.
        List<Map.Entry<String, Double>> sortedByTotalCost = totalCostByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .toList();
        System.out.println(sortedByTotalCost);
        System.out.println("*".repeat(100));

        //Выберите три самых дорогих продукта.
        List<Map.Entry<String, Double>> threeMostExpensiveTotalCostProducts = sortedByTotalCost.stream()
                .limit(3)
                .toList();
        System.out.println(threeMostExpensiveTotalCostProducts);
        System.out.println("*".repeat(100));

        //Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
        threeMostExpensiveTotalCostProducts.forEach(
                entry -> System.out.println("Product: " + entry.getKey() + ", Total cost: " + entry.getValue())
        );
    }
}
