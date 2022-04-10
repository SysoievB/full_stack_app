import './App.css';
import {getAllStudents} from "./client";

function App() {
    getAllStudents()
        .then(response => response.json())
        .then(console.log)
    return "hello react";
}

export default App;
