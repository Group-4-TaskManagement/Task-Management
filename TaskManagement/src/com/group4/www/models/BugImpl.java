package com.group4.www.models;

import com.group4.www.models.contracts.Bug;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.PriorityBug;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.StatusBug;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BugImpl extends TaskBase implements Bug {
    public static final String ADVANCED_STATUS = "The status of bug changed from Active to Fixed.";
    public static final String ADVANCE_STATUS_ERROR = "Can not advance status! Already at Fixed.";
    public static final String STATUS_REVERTED = "The status of the bug changed from Fixed to Active";
    public static final String REVERT_STATUS_ERROR = "Can not revert status! Already at Active";
    public static final String REPRODUCE_STEPS = "Please enter 3 steps for the procurement of the bug: \n";
    public static final String PRIORITY_ADVANCE_LOW_MED =
            "The priority of the bug was changed from Low to Medium.";
    public static final String PRIORITY_ADVANCE_MED_HIGH =
            "The priority of the bug was changed from Medium to High.";
    public static final String PRIORITY_ADVANCE_ERROR =
            "The priority of the bug can not be changed, it is already at High!";
    public static final String PRIORITY_REV_HIGH_MED =
            "The priority of the bug was changed from High to Medium.";
    public static final String PRIORITY_REV_MED_LOW =
            "The priority of the bug was changed from Medium to Low.";
    public static final String PRIORITY_REV_ERROR =
            "The priority of the bug can not be changed, it is already at Low!";
    public static final String ADVANCE_SEV_MINOR_TO_MAJOR =
            "The severity of the bug was changed from Minor to Major.";
    public static final String ADVANCE_SEV_MAJOR_TO_CRITICAL =
            "The severity of the bug was changed from Major to Critical.";
    public static final String SEVERITY_ADVANCE_ERROR =
            "The severity of the bug can not be changed, it is already at Critical!";
    public static final String REV_SEV_CRITICAL_TO_MAJOR =
            "The severity of the bug was changed from Critical to Major.";
    public static final String REV_SEV_MAJOR_TO_MINOR =
            "The severity of the bug was changed from Major to Minor.";
    public static final String SEV_REV_ERROR =
            "The severity of the bug can not be changed, it is already at Minor!";

    private List<String> steps;
    private PriorityBug priority;
    private SeverityBug severity;
    private StatusBug status;


    public BugImpl(String title, String description, PriorityBug priority, SeverityBug severity, Member assignee) {
        super(title, description, assignee);
        this.priority = priority;
        this.severity = severity;
        addStepsToReproduce();
        this.status = StatusBug.ACTIVE;
    }


    @Override
    public PriorityBug getPriority() {
        return priority;
    }

    @Override
    public SeverityBug getSeverity() {
        return severity;
    }

    @Override
    public StatusBug getStatus() {
        return status;
    }

    @Override
    public List<String> getSteps() {
        return new ArrayList<>(steps);
    }

    private void addStepsToReproduce() {
        steps = new ArrayList<>();
        System.out.print(REPRODUCE_STEPS);
        for (int i = 1; i <= 3; i++) {
            System.out.print(String.format("STEP %d: ", i));
            Scanner scn = new Scanner(System.in);
            steps.add(scn.nextLine());
        }
    }

    @Override
    public void advanceStatus() {
        if (status == StatusBug.ACTIVE) {
            this.status = StatusBug.FIXED;
            System.out.println(ADVANCED_STATUS);
        } else
            System.out.println(ADVANCE_STATUS_ERROR);
    }

    @Override
    public void revertStatus() {
        if (status == StatusBug.FIXED) {
            this.status = StatusBug.ACTIVE;
            System.out.println(STATUS_REVERTED);
        } else
            System.out.println(REVERT_STATUS_ERROR);
    }

    @Override
    public void advancePriority() {
        if(getPriority()==PriorityBug.LOW){
            this.priority=PriorityBug.MEDIUM;
            System.out.println(PRIORITY_ADVANCE_LOW_MED);
        }
        if(getPriority()==PriorityBug.MEDIUM){
            this.priority=PriorityBug.HIGH;
            System.out.println(PRIORITY_ADVANCE_MED_HIGH);
        }else System.out.println(PRIORITY_ADVANCE_ERROR);
    }

    @Override
    public void revertPriority() {
        if(getPriority()==PriorityBug.HIGH){
            this.priority=PriorityBug.MEDIUM;
            System.out.println(PRIORITY_REV_HIGH_MED);
        }
        if(getPriority()==PriorityBug.MEDIUM){
            this.priority=PriorityBug.LOW;
            System.out.println(PRIORITY_REV_MED_LOW);
        }else System.out.println(PRIORITY_REV_ERROR);
    }

    @Override
    public void advanceSeverity() {
        if(getSeverity()==SeverityBug.MINOR){
            this.severity=SeverityBug.MAJOR;
            System.out.println(ADVANCE_SEV_MINOR_TO_MAJOR);
        }
        if(getSeverity()==SeverityBug.MAJOR){
            this.severity=SeverityBug.CRITICAL;
            System.out.println(ADVANCE_SEV_MAJOR_TO_CRITICAL);
        }else System.out.println(SEVERITY_ADVANCE_ERROR);
    }

    @Override
    public void revertSeverity() {
        if(getSeverity()==SeverityBug.CRITICAL){
            this.severity=SeverityBug.MAJOR;
            System.out.println(REV_SEV_CRITICAL_TO_MAJOR);
        }
        if(getSeverity()==SeverityBug.MAJOR){
            this.severity=SeverityBug.MINOR;
            System.out.println(REV_SEV_MAJOR_TO_MINOR);
        }else System.out.println(SEV_REV_ERROR);
    }

}
