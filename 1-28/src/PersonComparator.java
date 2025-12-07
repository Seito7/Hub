import java.util.*;

class Person {
    String name;
    int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return name + "(" + age + "岁)";
    }
}
public class PersonComparator {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("小张", 14));
        people.add(new Person("小王", 15));
        people.add(new Person("小明", 12));
        people.add(new Person("小红", 14));
        
        System.out.println("排序前:");
        for (Person p : people) {
            System.out.println(p);
        }
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p1.age, p2.age);
            }
        });
        System.out.println("\n按年龄升序排序:");
        for (Person p : people) {
            System.out.println(p);
        }
    }
}