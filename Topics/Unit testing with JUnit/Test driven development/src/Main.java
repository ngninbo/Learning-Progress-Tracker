
class Person {
    // Do not change these fields
    public static final String DEFAULT_NAME = "Unknown";
    public static final int MAX_AGE = 130;
    public static final int MIN_AGE = 0;
    private final String name;
    private final int age;

    // Fix the constructor to make its code pass the unit tests
    Person(String name, int age) {

        this.name = name == null || name.trim().isEmpty() ? DEFAULT_NAME : name;

        this.age = age < 0 ? MIN_AGE : Math.min(age, MAX_AGE);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}