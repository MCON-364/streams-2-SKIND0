package edu.touro.las.mcon364.streams.exercises;

import javax.swing.*;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stream practice focused on collecting, grouping, and partitioning.
 *
 * Implement each method using streams.
 * Don't use loops.
 */
public class StreamTaskExercises {

    /**
     * Basics refresher:
     * Return the descriptions of all HIGH priority tasks in encounter order.
     */
    public List<String> highPriorityDescriptions(List<Task> tasks) {
        //throw new UnsupportedOperationException("TODO");
        return tasks.stream().filter(t -> t.priority() == Priority.HIGH).map(Task::description).toList();
    }

    /**
     * Collecting + grouping:
     * Return the number of tasks in each status.
     */
    public Map<Status, Long> countByStatus(List<Task> tasks) {
        //throw new UnsupportedOperationException("TODO");
        //map looks busy now: task im sorry. has id, description, priority and status.
        //it's just a list rn tho. list of tasks with tasks begin all of this tho.
        return tasks.stream().collect(Collectors.groupingBy(Task::status, Collectors.counting()));

    }

    /**
     * Grouping + downstream mapping:
     * Group tasks by priority, but keep only task descriptions.
     */
    public Map<Priority, List<String>> descriptionsByPriority(List<Task> tasks) {
        //throw new UnsupportedOperationException("TODO");
        return tasks.stream().collect(Collectors.groupingBy(Task::priority, Collectors.mapping(Task::description, Collectors.toList())));
    }

    /**
     * Partitioning:
     * Partition tasks into DONE and not DONE.
     * The map keys should be true and false.
     */
    public Map<Boolean, List<Task>> partitionByDone(List<Task> tasks) {
        //throw new UnsupportedOperationException("TODO");
        return tasks.stream().collect(Collectors.partitioningBy(t -> t.status() == Status.DONE));
    }

    /**
     * Partitioning + downstream counting:
     * Count how many tasks are DONE vs not DONE.
     */
    public Map<Boolean, Long> countDonePartition(List<Task> tasks) {
        //throw new UnsupportedOperationException("TODO");
        return tasks.stream().collect(Collectors.partitioningBy(t -> t.status() == Status.DONE, Collectors.counting()));
    }

    /**
     * Nested grouping:
     * First group by status, then by priority.
     */
    public Map<Status, Map<Priority, List<Task>>> groupByStatusThenPriority(List<Task> tasks) {
        //throw new UnsupportedOperationException("TODO");
        //meaning what? it should look like status 1, 2, 3. and for each their individual priority ig
        return tasks.stream().collect(Collectors.groupingBy(Task::status, Collectors.groupingBy(Task::priority)));
    }

    /**
     * Grouping + mapping + collectingAndThen:
     * Group by status and return alphabetically sorted descriptions for each status.
     */
    public Map<Status, List<String>> sortedDescriptionsByStatus(List<Task> tasks) {
        //throw new UnsupportedOperationException("TODO");
        //means gives you groups of statuses plus descriptions which are sorted for each status. problem is im not sure what this means at all.
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::status, Collectors.mapping(Task::description,
                                Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().sorted().toList()))));

    }

    /**
     * Challenge:
     * Return a comma-separated string of descriptions for DONE tasks,
     * preserving encounter order.
     *
     * Example: "Write syllabus, Grade quizzes"
     */
    public String doneTaskSummary(List<Task> tasks) {
        //throw new UnsupportedOperationException("TODO");
        return tasks.stream().filter(t -> t.status() == Status.DONE).map(Task::description).collect(Collectors.joining(", "));
    }

    /**
     * flatMap:
     * Return all tags from all work items in encounter order.
     */
    public List<String> allTags(List<WorkItem> items) {
        //throw new UnsupportedOperationException("TODO");
        return items.stream().flatMap(i -> i.tags().stream().distinct()).collect(Collectors.toList());
    }

    /**
     * flatMap + distinct:
     * Return distinct assignees for DONE items in encounter order.
     */
    public List<String> distinctDoneAssignees(List<WorkItem> items) {
        //throw new UnsupportedOperationException("TODO");
        //return items.stream().flatMap(i -> i.assignees().stream().distinct().collect(Collectors.toList()), mapping(WorkItem::status == Status.DONE)));
        //distinct goes after everything... (twice basically)
        return items.stream().filter(i -> i.status() == Status.DONE).
                flatMap(i -> i.assignees().stream()).distinct()
                .collect(Collectors.toList());
    }

    /**
     * toMap:
     * Build a map from work-item id to status.
     */
    public Map<String, Status> idToStatus(List<WorkItem> items) {
        //throw new UnsupportedOperationException("TODO");
        return items.stream().collect(Collectors.toMap(WorkItem::id, WorkItem::status));
    }

    /**
     * groupingBy + mapping:
     * Group by priority and collect only titles.
     */
    public Map<Priority, List<String>> titlesByPriorityUsingMapping(List<WorkItem> items) {
        //throw new UnsupportedOperationException("TODO");
        return items.stream().collect(Collectors.groupingBy(WorkItem::priority, Collectors.mapping(WorkItem::title, Collectors.toList())));
    }
}
