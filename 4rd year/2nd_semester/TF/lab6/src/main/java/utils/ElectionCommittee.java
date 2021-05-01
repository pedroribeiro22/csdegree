package utils;

import spread.SpreadGroup;

import java.util.ArrayList;
import java.util.List;

public class ElectionCommittee {

    private String current_leader;
    private int max_sequence_number;
    private List<String> group_members;
    private int completed_elements;

    public ElectionCommittee() {}

    public void startElection(SpreadGroup[] members) {
       this.current_leader = null;
       this.max_sequence_number = -1;
       this.group_members = new ArrayList<>();
       for(SpreadGroup member : members)
           this.group_members.add(member.toString());
       this.completed_elements = 0;
    }

    public void processElement(SpreadGroup member, int sequence_number) {
        if(sequence_number > max_sequence_number || (sequence_number == max_sequence_number && member.toString().compareTo(this.current_leader) > 0)) {
            this.max_sequence_number = sequence_number;
            this.current_leader = member.toString();
            this.completed_elements++;
        }
    }

    public Boolean isFinished() {
        return this.completed_elements == this.group_members.size();
    }

    public String getLeader() {
        return this.current_leader;
    }
}
