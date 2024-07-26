package uz.pdp.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor

public class Todo {

    private String fullName;
    private int age;

    @Override
    public String toString() {
        return "FullName: " + fullName + ", Age: " + age;
    }
}
