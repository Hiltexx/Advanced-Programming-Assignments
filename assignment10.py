class Address:
    def __init__(self, street, city, zipCode):
        self.street = street
        self.city = city
        self.zipCode = zipCode

    def __str__(self):
        return f"{self.street}, {self.city} - {self.zipCode}"


class Student:
    def __init__(self, name, age, address):
        self.name = name
        self._age = None
        self.age = age
        self.address = address
        self.courses = []

    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, value):
        if value < 18 or value > 30:
            raise ValueError("Invalid age")
        self._age = value

    def add_course(self, course):
        self.courses.append(course)

    def display(self):
        print(f"\nName: {self.name}")
        print(f"Age: {self.age}")
        print(f"Address: {self.address}")
        print(f"Courses: {self.courses}")


class ScholarshipStudent(Student):
    def __init__(self, name, age, address, scholarshipAmount):
        super().__init__(name, age, address)
        self.scholarshipAmount = scholarshipAmount

    def display(self):
        super().display()
        print(f"Scholarship Amount: {self.scholarshipAmount}")


# ---------------- SYSTEM ----------------

students = []

# 🔹 Predefined students
addr1 = Address("GS Road", "Guwahati", "781005")
s1 = Student("Rahul", 20, addr1)
s1.add_course("Math")
s1.add_course("Physics")

addr2 = Address("Zoo Road", "Guwahati", "781024")
s2 = ScholarshipStudent("Anita", 22, addr2, 50000)
s2.add_course("CS")
s2.add_course("AI")

students.extend([s1, s2])


# ---------------- MENU ----------------

def add_student():
    try:
        name = input("Enter name: ")
        age = int(input("Enter age: "))
        street = input("Enter street: ")
        city = input("Enter city: ")
        zipCode = input("Enter zip code: ")

        address = Address(street, city, zipCode)

        choice = input("Scholarship student? (y/n): ").lower()

        if choice == 'y':
            amount = float(input("Enter scholarship amount: "))
            student = ScholarshipStudent(name, age, address, amount)
        else:
            student = Student(name, age, address)

        # Add courses
        while True:
            course = input("Add course (or press enter to stop): ")
            if course == "":
                break
            student.add_course(course)

        students.append(student)
        print("✅ Student added successfully!")

    except Exception as e:
        print(" Error:", e)


def view_students():
    if not students:
        print("No students available.")
        return

    for i, student in enumerate(students, start=1):
        print(f"\n===== Student {i} =====")
        student.display()


# ---------------- MAIN LOOP ----------------

while True:
    print("\n===== STUDENT SYSTEM =====")
    print("1. View Students")
    print("2. Add Student")
    print("3. Exit")

    choice = input("Enter choice: ")

    if choice == '1':
        view_students()
    elif choice == '2':
        add_student()
    elif choice == '3':
        print("Exiting...")
        break
    else:
        print("Invalid choice!")
