import React, { useState } from "react"; 
function Assignment4() { 
const [todos, setTodos] = useState([]); 
const [input, setInput] = useState(""); 
const [priority, setPriority] = useState("Medium"); 
const [section, setSection] = useState("General"); 
const [sections, setSections] = useState(["General"]); 
const addTodo = () => { 
const trimmed = input.trim(); 
if (!trimmed) return; 
setTodos([ 
...todos, 
{ 
text: trimmed, 
completed: false, 
editing: false, 
priority, 
section, 
}, 
]); 
setInput(""); 
}; 
const handleKeyPressAdd = (e) => { 
if (e.key === "Enter") addTodo(); 
}; 
const toggleComplete = (index) => { 
const newTodos = [...todos]; 
newTodos[index].completed = !newTodos[index].completed; 
setTodos(newTodos); 
}; 
const deleteTodo = (index) => { 
setTodos(todos.filter((_, i) => i !== index)); 
}; 
const startEditing = (index) => { 
const newTodos = [...todos]; 
newTodos[index].editing = true; 
setTodos(newTodos); 
}; 
const saveEdit = (index, newText) => { 
const newTodos = [...todos]; 
newTodos[index].text = newText; 
newTodos[index].editing = false; 
setTodos(newTodos); 
}; 
const cancelEdit = (index) => { 
const newTodos = [...todos]; 
newTodos[index].editing = false; 
setTodos(newTodos); 
}; 
const addSection = () => { 
const name = prompt("Enter new section name:"); 
if (name && !sections.includes(name)) setSections([...sections, 
name]); 
}; 
const deleteAll = () => { 
if (todos.length === 0) return; 
if (window.confirm("Are you sure you want to delete ALL todos?")) 
setTodos([]); 
}; 
const groupedTodos = sections.map((sec) => ({ 
name: sec, 
items: todos.filter((t) => t.section === sec), 
})); 
return ( 
<div style={styles.container}> 
<h1 style={styles.header}>TODO LIST</h1> 
<div style={styles.inputContainer}> 
<input 
type="text" 
value={input} 
onChange={(e) => setInput(e.target.value)} 
onKeyDown={handleKeyPressAdd} 
placeholder="Type your todo here..." 
style={styles.input} 
/> 
<div style={styles.dropdownContainer}> 
<label style={styles.label}>Priority</label> 
<select 
value={priority} 
onChange={(e) => setPriority(e.target.value)} 
style={styles.select} 
> 
<option>High</option> 
<option>Medium</option> 
<option>Low</option> 
</select> 
</div> 
<div style={styles.dropdownContainer}> 
<label style={styles.label}>Section</label> 
<select 
value={section} 
onChange={(e) => setSection(e.target.value)} 
style={styles.select} 
> 
{sections.map((sec, i) => ( 
<option key={i}>{sec}</option> 
))} 
</select> 
</div> 
<button onClick={addTodo} style={styles.button}> 
Add 
</button> 
</div> 
<button onClick={addSection} style={styles.addSectionBtn}> 
+ Add Section 
</button> 
{groupedTodos.map((group, i) => ( 
<div key={i} style={styles.section}> 
<h2>{group.name}</h2> 
{group.items.length === 0 ? ( 
<p style={styles.empty}>No todos here</p> 
) : ( 
            <ul style={styles.list}> 
              {group.items.map((todo) => { 
                const globalIndex = todos.findIndex((t) => t === todo); 
 
                const priorityColor = 
                  todo.priority === "High" 
                    ? "#f44336" 
                    : todo.priority === "Medium" 
                    ? "#ff9800" 
                    : "#2196f3"; 
 
                return ( 
                  <li 
                    key={globalIndex} 
                    style={{ 
                      ...styles.listItem, 
                      borderLeft: `4px solid ${ 
                        todo.completed ? "#4CAF50" : priorityColor 
                      }`, 
                    }} 
                  > 
                    <input 
                      type="checkbox" 
                      checked={todo.completed} 
                      onChange={() => toggleComplete(globalIndex)} 
                      style={{ marginRight: "12px" }} 
                    /> 
 
                    {todo.editing ? ( 
                      <input 
                        type="text" 
                        defaultValue={todo.text} 
                        onKeyDown={(e) => 
                          e.key === "Enter" 
                            ? saveEdit(globalIndex, e.target.value) 
                            : e.key === "Escape" 
                            ? cancelEdit(globalIndex) 
                            : null 
                        } 
                        onBlur={(e) => saveEdit(globalIndex, 
e.target.value)} 
                        autoFocus 
                        style={styles.editInput} 
                      /> 
                    ) : ( 
                      <span 
                        style={{ 
                          ...styles.todoText, 
                          textDecoration: todo.completed 
                            ? "line-through" 
                            : "none", 
                          color: todo.completed ? "#999" : "#333", 
                        }} 
                      > 
                        {todo.text} 
                      </span> 
                    )} 
 
                    <div style={styles.actions}> 
                      {!todo.editing && ( 
                        <button 
                          onClick={() => startEditing(globalIndex)} 
                          style={{ 
                            ...styles.actionBtn, 
                            backgroundColor: "#2196F3", 
                          }} 
                        > 
                          Edit 
                        </button> 
                      )} 
                      <button 
                        onClick={() => deleteTodo(globalIndex)} 
                        style={{ 
                          ...styles.actionBtn, 
                          backgroundColor: "#f44336", 
                        }} 
                      > 
                        Delete 
                      </button> 
                    </div> 
                  </li> 
                ); 
              })} 
            </ul> 
          )} 
        </div> 
      ))} 
 
      {todos.length > 0 && ( 
        <button onClick={deleteAll} style={styles.deleteAllBtn}> 
          Delete All 
        </button> 
      )} 
    </div> 
  ); 
} 
 
const styles = { 
  container: { 
    maxWidth: "700px", 
    margin: "50px auto", 
    padding: "20px", 
    backgroundColor: "#ffffff", 
    borderRadius: "12px", 
    boxShadow: "0 6px 18px rgba(0,0,0,0.08)", 
    fontFamily: "Arial, sans-serif", 
  }, 
  header: { 
    textAlign: "center", 
    marginBottom: "20px", 
  }, 
  inputContainer: { 
    display: "flex", 
    gap: "10px", 
    marginBottom: "15px", 
  }, 
  input: { 
    flex: 2, 
    padding: "10px", 
    borderRadius: "6px", 
    border: "1px solid #ddd", 
  }, 
  dropdownContainer: { 
    display: "flex", 
    flexDirection: "column", 
    fontSize: "14px", 
  }, 
  label: { 
    fontSize: "12px", 
    marginBottom: "4px", 
    color: "#666", 
  }, 
  select: { 
    padding: "8px", 
    borderRadius: "6px", 
    border: "1px solid #ddd", 
  }, 
  button: { 
    padding: "10px 18px", 
    backgroundColor: "#4CAF50", 
    color: "#fff", 
    border: "none", 
    borderRadius: "6px", 
    cursor: "pointer", 
  }, 
  addSectionBtn: { 
    marginBottom: "20px", 
    padding: "8px 14px", 
    backgroundColor: "#FF9800", 
    color: "#fff", 
    border: "none", 
    borderRadius: "6px", 
    cursor: "pointer", 
  }, 
  list: { 
    listStyle: "none", 
    padding: 0, 
  }, 
  listItem: { 
    display: "flex", 
    alignItems: "center", 
    justifyContent: "space-between", 
    padding: "12px 15px", 
    marginBottom: "10px", 
    borderRadius: "8px", 
    backgroundColor: "#fafafa", 
    transition: "0.2s ease", 
  }, 
  todoText: { 
    flex: 1, 
    fontSize: "16px", 
  }, 
  editInput: { 
    flex: 1, 
    padding: "6px 8px", 
    borderRadius: "6px", 
    border: "1px solid #ccc", 
  }, 
  actions: { 
    display: "flex", 
    gap: "8px", 
  }, 
  actionBtn: { 
    padding: "5px 10px", 
    border: "none", 
    color: "#fff", 
    borderRadius: "5px", 
    cursor: "pointer", 
    fontSize: "13px", 
  }, 
  deleteAllBtn: { 
    marginTop: "15px", 
    padding: "10px", 
    backgroundColor: "#f44336", 
    color: "#fff", 
    border: "none", 
    borderRadius: "6px", 
    cursor: "pointer", 
    width: "100%", 
}, 
section: { 
marginBottom: "30px", 
}, 
empty: { 
fontStyle: "italic", 
color: "#888", 
}, 
}; 
export default Assignment4; 