import java.util.ArrayList;
import java.util.Scanner;
public class assignment_3 {
public static void main(String[] args) {
ArrayList<String> books = new ArrayList<>();
books.add("The Great Gatsby");
books.add("To Kill a Mockingbird");
books.add("The Lord of the Rings");
books.add("The Catcher in the Rye");
books.add("Great Expectations");
Scanner scanner = new Scanner(System.in);
System.out.print("Enter a word to search in book titles: ");
String searchWord = scanner.nextLine().toLowerCase();
System.out.println("\nBooks containing the word \"" + searchWord +
"\":");
boolean found = false;

for (String book : books) {
String[] words = book.toLowerCase().split(" ");
for (String word : words) {
if (word.equals(searchWord)) {
System.out.println(book);
found = true;
break;
}
}
}
if (!found) {
System.out.println("No books found with that word.");
}
scanner.close();
}
}