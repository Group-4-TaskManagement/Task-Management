package com.group4.www.models.utils;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListingHelper {

    public static <T extends AssignableTask> List<T> listOfTasksWithAssignee(List<T> assignableTasks) {
        return assignableTasks.stream().filter(t -> t.getAssignee()!=null).collect(Collectors.toList());
    }

    public static <T> List<T> filterByCondition(List<T> list, Predicate<T> condition){
        return list.stream().filter(condition).collect(Collectors.toList());
    }

    public static <T> List<T> sortByCondition(List<T> list, Comparator<T> comparator){
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }
}
