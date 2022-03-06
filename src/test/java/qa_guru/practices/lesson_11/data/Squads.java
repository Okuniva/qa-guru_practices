package qa_guru.practices.lesson_11.data;

import java.util.List;

public class Squads {
    private String squadName;
    private Integer formed;
    private Boolean active;
    private List<Member> members = null;

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public Integer getFormed() {
        return formed;
    }

    public void setFormed(Integer formed) {
        this.formed = formed;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}