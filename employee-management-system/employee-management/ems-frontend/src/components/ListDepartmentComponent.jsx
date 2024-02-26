import { useEffect, useState } from "react"
import { deleteDepartment, listDepartments } from "../services/DepartmentService";
import { Link, useNavigate } from "react-router-dom";

const ListDepartmentComponent = () => {
    
    const navigator = useNavigate();

    const [departments, setDepartments] = useState([]);
    
    const updateDepartment = (id) => navigator(`/edit-department/${id}`);

    const removeDepartment = (id) => {
        deleteDepartment(id).then((response) => {
            console.log("Was the department with id: " + id + " deleted? " + response.data);
            getAllDepartments();
        }).catch(error => {
            console.error(error);
        })
    }

    const getAllDepartments = () => {
        listDepartments()
            .then((response) => { setDepartments(response.data);
        }).catch((error) => {
            console.error(error);
        });
    }

    useEffect(() => {
        getAllDepartments();    
    }, []);

    return (
        <div className="container">
            <h2 className="text-center">List of Departments</h2>
            <Link to="/add-department" className="btn btn-primary mb-2">Add Department</Link>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr className="text-center">
                        <th>Department Id</th>
                        <th>Department Name</th>
                        <th>Department Description</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {departments.map((department) => (
                        <tr key={department.id}>
                            <td>{department.id}</td>
                            <td>{department.departmentName}</td>
                            <td>{department.departmentDescription}</td>
                            <td>
                                <button 
                                    className="btn btn-info" 
                                    onClick={ () => updateDepartment(department.id) }
                                >
                                    Update
                                </button>
                                <button 
                                    className="btn btn-danger" 
                                    style={{marginLeft:'10px'}} 
                                    onClick={ () => removeDepartment(department.id) }
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default ListDepartmentComponent