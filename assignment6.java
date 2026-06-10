import java.util.*;
import java.util.stream.Collectors;

class Student {

    private int id;
    private String name;
    private List<String> courses;
    private Map<String, Integer> scores;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        courses = new ArrayList<>();
        scores = new HashMap<>();
    }

    public void addCourseScore(String course, int score) {
        courses.add(course);
        scores.put(course, score);
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public List<String> getCourses() { return courses; }

    public Map<String, Integer> getScores() { return scores; }

    public double getAverageScore() {
        return scores.values()
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }
}

public class StudentAnalyzer {

    public static List<Student> getTopNStudents(List<Student> students, int n) {

        return students.stream()
                .sorted(Comparator
                        .comparingDouble(Student::getAverageScore)
                        .reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public static Map<String, Double> getAverageScorePerCourse(List<Student> students) {

        Map<String, List<Integer>> temp = new HashMap<>();

        for (Student s : students) {

            for (String course : s.getCourses()) {

                int score = s.getScores().getOrDefault(course, 0);

                temp.computeIfAbsent(course, k -> new ArrayList<>()).add(score);
            }
        }

        Map<String, Double> result = new HashMap<>();

        temp.forEach((course, list) -> {

            double avg = list.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);

            result.put(course, avg);
        });

        return result;
    }

    public static Set<String> getAllUniqueCourses(List<Student> students) {

        return students.stream()
                .flatMap(s -> s.getCourses().stream())
                .collect(Collectors.toSet());
    }

    public static void displayAllStudents(List<Student> students) {

        for (Student s : students) {

            System.out.println("ID: " + s.getId());
            System.out.println("Name: " + s.getName());
            System.out.println("Courses: " + s.getCourses());
            System.out.println("Scores: " + s.getScores());
            System.out.println("Average: " + s.getAverageScore());
            System.out.println("---------------------------");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Student> students = new ArrayList<>();

        Student s1 = new Student(101, "Rahul");
        s1.addCourseScore("Math", 85);
        s1.addCourseScore("Physics", 90);

        Student s2 = new Student(102, "Anita");
        s2.addCourseScore("Math", 95);
        s2.addCourseScore("Physics", 80);
        s2.addCourseScore("Chemistry", 88);

        Student s3 = new Student(103, "Karan");
        s3.addCourseScore("Math", 75);
        s3.addCourseScore("Chemistry", 82);

        Student s4 = new Student(104, "Priya");
        s4.addCourseScore("Math", 88);
        s4.addCourseScore("Physics", 84);

        Student s5 = new Student(105, "Arjun");
        s5.addCourseScore("Math", 92);
        s5.addCourseScore("Chemistry", 86);

        Student s6 = new Student(106, "Neha");
        s6.addCourseScore("Physics", 91);
        s6.addCourseScore("Chemistry", 89);

        Student s7 = new Student(107, "Vikram");
        s7.addCourseScore("Math", 70);
        s7.addCourseScore("Physics", 75);

        Student s8 = new Student(108, "Sneha");
        s8.addCourseScore("Math", 83);
        s8.addCourseScore("Chemistry", 90);

        Student s9 = new Student(109, "Rohit");
        s9.addCourseScore("Physics", 87);
        s9.addCourseScore("Chemistry", 81);

        Student s10 = new Student(110, "Pooja");
        s10.addCourseScore("Math", 89);
        s10.addCourseScore("Physics", 92);

        students.addAll(Arrays.asList(
                s1,s2,s3,s4,s5,s6,s7,s8,s9,s10
        ));

        while (true) {

            System.out.println("\n===== Student Analyzer Menu =====");
            System.out.println("1. View All Students");
            System.out.println("2. View Top N Students");
            System.out.println("3. Average Score Per Course");
            System.out.println("4. View All Unique Courses");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    displayAllStudents(students);

                    System.out.println("\nTime Complexity:");
                    System.out.println("Displaying all students requires iterating through the list.");
                    System.out.println("Complexity: O(n)");
                    break;

                case 2:

                    System.out.print("Enter N: ");
                    int n = sc.nextInt();

                    getTopNStudents(students, n)
                            .forEach(s ->
                                    System.out.println(
                                            s.getName() +
                                            " Avg Score: " +
                                            s.getAverageScore()
                                    ));

                    System.out.println("\nTime Complexity:");
                    System.out.println("Sorting students by average score.");
                    System.out.println("Complexity: O(n log n)");
                    break;

                case 3:

                    getAverageScorePerCourse(students)
                            .forEach((course, avg) ->
                                    System.out.println(course + " : " + avg));

                    System.out.println("\nTime Complexity:");
                    System.out.println("We iterate through every student and their courses.");
                    System.out.println("Complexity: O(n × c)");
                    break;

                case 4:

                    getAllUniqueCourses(students)
                            .forEach(System.out::println);

                    System.out.println("\nTime Complexity:");
                    System.out.println("Flattening course lists from all students.");
                    System.out.println("Complexity: O(n × c)");
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
