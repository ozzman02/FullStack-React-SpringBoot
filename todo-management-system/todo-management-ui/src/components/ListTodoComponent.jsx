/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react"
import { deleteTodo, getAllTodos, completeTodo, incompleteTodo } from "../services/TodoService"
import { useNavigate } from "react-router-dom"

const ListTodoComponent = () => {
    
    const [todos, setTodos] = useState([])

    const navigate = useNavigate()

    const addNewTodo = () => navigate('/add-todo')

    const updateTodo = (id) => {
        navigate(`/update-todo/${id}`)
    }

    const removeTodo = (id) => {
        deleteTodo(id).then((response) => {
            listAllTodos();
        }).catch(error => {
            console.error(error);
        })
    }

    const listAllTodos = () => {
        getAllTodos().then((response) => {
            setTodos(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    const markTodoAsComplete = (id) => {
        completeTodo(id).then((response) => {
            listAllTodos();
        }).catch(error => {
            console.log(error);
        })
    }

    const markTodoAsInComplete = (id) => {
        incompleteTodo(id).then((response) => {
            listAllTodos();
        }).catch(error => {
            console.log(error);
        })
    }
    
    useEffect(() => {
        listAllTodos();
    }, [])

    return (
        <div className="container">
            <h2 className="text-center">List of Todos</h2>
            <button className="btn btn-primary mb-2" onClick={addNewTodo}>Add Todo</button>
            <div>
                <table className="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Todo Title</th>
                            <th>Todo Description</th>
                            <th>Todo Completed</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            todos.map(todo => 
                                <tr key={todo.id}>
                                    <td>{todo.title}</td>
                                    <td>{todo.description}</td>
                                    <td>{todo.completed ? 'YES' : 'NO'}</td>
                                    <td>
                                        <button 
                                            className="btn btn-info" 
                                            onClick={() => updateTodo(todo.id)}
                                        >Update
                                        </button>
                                        <button 
                                            className="btn btn-danger" 
                                            onClick={() => removeTodo(todo.id)} 
                                            style={{marginLeft: "10px"}}
                                        >Delete
                                        </button>
                                        <button 
                                            className="btn btn-success" 
                                            onClick={() => markTodoAsComplete(todo.id)} 
                                            style={{marginLeft: "10px"}}
                                        >Complete
                                        </button>
                                        <button 
                                            className="btn btn-info" 
                                            onClick={() => markTodoAsInComplete(todo.id)} 
                                            style={{marginLeft: "10px"}}
                                        >Incomplete
                                        </button>
                                    </td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default ListTodoComponent