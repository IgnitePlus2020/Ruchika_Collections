import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 4/9/2020.
 */
public class IgniteMembers {
    String name;
    private String college;
    private int age;
    Set<String> skills = new HashSet<>();

    public IgniteMembers(String name, String college, int age, Set<String> skills) {
        this.name = name;
        this.college = college;
        this.age = age;
        this.skills = skills;
    }
    @Override
    public String toString() {
        return "\nIgniteMember:\n" +
                "Name:'" + name + '\'' +
                ", College:'" + college + '\'' +
                ", Age:" + age +
                ", Skills:" + skills +
                '}';
    }
}
