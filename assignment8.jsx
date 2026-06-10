import React, { useState } from "react";

function Assignment8() {
  const [students, setStudents] = useState(
    new Map([
      [
        1,
        {
          id: 1,
          name: "Aarav Sharma",
          enrolledCourses: new Set(["DAA", "OS", "DBMS"]),
          gpa: 8.2,
        },
      ],
      [
        2,
        {
          id: 2,
          name: "Riya Gupta",
          enrolledCourses: new Set(["AI", "ML", "DBMS"]),
          gpa: 7.8,
        },
      ],
      [
        3,
        {
          id: 3,
          name: "Rahul Verma",
          enrolledCourses: new Set(["DAA", "AI", "ML"]),
          gpa: 9.1,
        },
      ],
    ])
  );

  const [name, setName] = useState("");
  const [gpa, setGpa] = useState("");
  const [courses, setCourses] = useState("");
  const [removeId, setRemoveId] = useState("");
  const [filterCourse, setFilterCourse] = useState("");

  const addStudent = () => {
    if (!name || !gpa || !courses) {
      alert("Please fill all fields");
      return;
    }

    const newId =
      students.size > 0
        ? Math.max(...Array.from(students.keys())) + 1
        : 1;

    const newStudent = {
      id: newId,
      name,
      gpa: parseFloat(gpa),
      enrolledCourses: new Set(
        courses.split(",").map((course) => course.trim())
      ),
    };

    const updatedStudents = new Map(students);
    updatedStudents.set(newId, newStudent);

    setStudents(updatedStudents);

    setName("");
    setGpa("");
    setCourses("");
  };

  const removeStudent = () => {
    const updatedStudents = new Map(students);

    if (updatedStudents.has(Number(removeId))) {
      updatedStudents.delete(Number(removeId));
      setStudents(updatedStudents);
    } else {
      alert("Student ID not found");
    }

    setRemoveId("");
  };

  const studentArray = Array.from(students.values());

  const sortedStudents = [...studentArray].sort(
    (a, b) => b.gpa - a.gpa
  );

  const filteredStudents = filterCourse
    ? sortedStudents.filter((student) =>
        student.enrolledCourses.has(filterCourse)
      )
    : sortedStudents;

  const uniqueCourses = Array.from(
    studentArray.reduce((courseSet, student) => {
      student.enrolledCourses.forEach((course) =>
        courseSet.add(course)
      );
      return courseSet;
    }, new Set())
  );

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>
      <h1>🎓 Course Enrollment Dashboard</h1>

      <hr />

      <h2>Add New Student</h2>

      <input
        type="text"
        placeholder="Student Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <input
        type="number"
        step="0.01"
        placeholder="GPA"
        value={gpa}
        onChange={(e) => setGpa(e.target.value)}
      />

      <input
        type="text"
        placeholder="Courses (comma separated)"
        value={courses}
        onChange={(e) => setCourses(e.target.value)}
      />

      <button onClick={addStudent}>Add Student</button>

      <hr />

      <h2>Remove Student By ID</h2>

      <input
        type="number"
        placeholder="Student ID"
        value={removeId}
        onChange={(e) => setRemoveId(e.target.value)}
      />

      <button onClick={removeStudent}>Remove Student</button>

      <hr />

      <h2>All Unique Courses</h2>

      <ul>
        {uniqueCourses.map((course) => (
          <li key={course}>{course}</li>
        ))}
      </ul>

      <hr />

      <h2>Filter Students By Course</h2>

      <input
        type="text"
        placeholder="Enter Course Name"
        value={filterCourse}
        onChange={(e) => setFilterCourse(e.target.value)}
      />

      <hr />

      <h2>Students Sorted By GPA (Descending)</h2>

      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>GPA</th>
            <th>Courses</th>
          </tr>
        </thead>

        <tbody>
          {filteredStudents.map((student) => (
            <tr key={student.id}>
              <td>{student.id}</td>
              <td>{student.name}</td>
              <td>{student.gpa}</td>
              <td>
                {[...student.enrolledCourses].join(", ")}
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <hr />

      <h3>Time Complexity</h3>

      <p>
        Filtering students by course takes <b>O(n × k)</b>,
        where n is the number of students and k is the
        average number of courses per student.
      </p>
    </div>
  );
}

export default Assignment8;
