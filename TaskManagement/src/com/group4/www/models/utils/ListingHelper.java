package com.group4.www.models.utils;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListingHelper {
    //BELOW TO BE DELETED
    public static List<Task> filterByTitle(List<Task> tasks, String title) {
        return tasks.stream().filter(task -> task.getTitle().equals(title)).collect(Collectors.toList());
    }

    public static <T extends Task> List<T> sortByTitle(List<T> tasks) {
        return tasks.stream().sorted(Comparator.comparing(Task::getTitle)).collect(Collectors.toList());
    }


    public static <T extends Task> List<T> filterByStatus(List<T> tasks, String status) {
        return tasks.stream().filter(e -> e.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
    }

    public static <T extends AssignableTask> List<T> filterByAssignee(List<T> tasks, String assignee) {
        List<T> result = listOfTasksWithAssignee(tasks);
        List<T> displayedResult = result.stream().filter(task -> task.getAssignee().getName().equals(assignee)).collect(Collectors.toList());
        return displayedResult;
    }

    public static <T extends AssignableTask> List<T> filterByStatusAndAssignee(List<T> tasks, String status, String assignee) {
        List<T> result = listOfTasksWithAssignee(tasks);
        List<T> displayedResult = result.stream().filter(t -> t.getStatus().equalsIgnoreCase(status) && t.getAssignee().getName().equals(assignee)).collect(Collectors.toList());
        return displayedResult;
    }



    public static List<Bug> sortBugsByPriority(List<Bug> bugs){
        return bugs.stream().sorted(Comparator.comparing(Bug::getPriority)).collect(Collectors.toList());
    }

    public static List<Bug> sortBugsBySeverity(List<Bug> bugs){
        return bugs.stream().sorted(Comparator.comparing(Bug::getSeverity)).collect(Collectors.toList());
    }

    public static List<Story> sortStoriesByPriority(List<Story> stories){
        return stories.stream().sorted(Comparator.comparing(Story::getPriority)).collect(Collectors.toList());
    }

    public static List<Story> sortStoriesBySize(List<Story> stories){
        return stories.stream().sorted(Comparator.comparing(Story::getSize)).collect(Collectors.toList());
    }
    public static List<Feedback>sortFeedbackByRating(List<Feedback> feedbacks){
        return feedbacks.stream().sorted(Comparator.comparing(Feedback::getRating)).collect(Collectors.toList());
    }
    //ABOVE TO BE DELETED


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
