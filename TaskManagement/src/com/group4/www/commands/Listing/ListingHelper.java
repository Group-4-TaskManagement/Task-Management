package com.group4.www.commands.Listing;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.tasks.contracts.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListingHelper {

    public static List<Task> mergeToTasks(List<Bug> bugs, List<Story> stories, List<Feedback> feedbacks){
        List<Task> task = Stream.of(bugs,stories).flatMap(Collection::stream).collect(Collectors.toList());
        return Stream.of(task,feedbacks).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static List<Task> filterByTitle(List<Bug> bugs, List<Story> stories, List<Feedback> feedbacks, String title ){
        List<Task> tasks = mergeToTasks(bugs,stories,feedbacks);
        return tasks.stream().filter(task -> task.getTitle().equals(title)).collect(Collectors.toList());
    }

    public static List<Task> sortByTitle(List<Bug> bugs, List<Story> stories, List<Feedback> feedbacks){
        List<Task> tasks = mergeToTasks(bugs,stories,feedbacks);
        return tasks.stream().sorted(Comparator.comparing(Task::getTitle)).collect(Collectors.toList());
    }

    public static List<Task> listOfTasksWithAssignee(List<Bug> bugs, List<Story> stories, List<Feedback> feedbacks) {
        List<Task> tasks = mergeToTasks(bugs, stories, feedbacks);
        List<Task> result = new ArrayList<>();
        int i = 0;
        try {
            for(; i<tasks.size();i++){
                if(tasks.get(i).getAssignee().getName()!=null){
                    result.add(tasks.get(i));
                }
            }
            //result = tasks.stream().filter(task -> task.getAssignee().getName() != null).collect(Collectors.toList());
        }catch (NullPointerException ig){
            StringBuilder builder = new StringBuilder();
            builder.append(System.lineSeparator());
            builder.toString().trim();
        }
        return result;
    }

    public static List<Task> filterByStatus(List<Bug> bugs, List<Story> stories, List<Feedback> feedbacks, String status){
        List<Task> tasks = listOfTasksWithAssignee(bugs,stories,feedbacks);
        return tasks.stream().filter(task -> task.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
    }
    public static List<Task> filterByAssignee(List<Bug> bugs, List<Story> stories, List<Feedback> feedbacks, String assignee){
        List<Task> tasks = listOfTasksWithAssignee(bugs,stories,feedbacks);
        return tasks.stream().filter(task -> task.getAssignee().getName().equals(assignee)).collect(Collectors.toList());
    }
    public static List<Task> filterByStatusAndAssignee(List<Bug> bugs, List<Story> stories, List<Feedback> feedbacks,String status, String assignee){
        List<Task> tasks = listOfTasksWithAssignee(bugs,stories,feedbacks);
        return tasks.stream().filter(task -> task.getStatus().equalsIgnoreCase(status)&&task.getAssignee().getName().equals(assignee)).collect(Collectors.toList());
    }


}
