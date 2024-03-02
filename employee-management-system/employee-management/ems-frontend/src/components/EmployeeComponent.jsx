import { useEffect, useState } from "react"
import { createEmployee, getEmployee, updateEmployee } from "../services/EmployeeService"
import { useNavigate, useParams } from "react-router-dom"
import { listDepartments } from "../services/DepartmentService";

const EmployeeComponent = () => {

    const [firstName, setFirstName] = useState("");

    const [lastName, setLastName] = useState("");
    
    const [email, setEmail] = useState("");

    const [departmentId, setDepartmentId] = useState("")

    const [departments, setDepartments] = useState([])

    const navigator = useNavigate();

    /* get the employee id from the url using useParams hook */
    const { id } = useParams();

    /* Initialize state variables that will hold validation errors */
    const [errors, setErrors] = useState({
        firstName: "",
        lastName: "",
        email: "",
        department: ""
    })

    const pageTitle = () => {
        if (id) {
            return <h2 className="text-center">Update Employee</h2>
        } else {
            return <h2 className="text-center">Add Employee</h2>
        }
    }

    const validateForm = () => {
        
        let valid = true;
        
        /* spread operator -> we are copying the errors object into the errorsCopy variable */
        const errorsCopy = {... errors}

        if (firstName.trim()) {
            errorsCopy.firstName = "";
        } else {
            errorsCopy.firstName = "First name is required";
            valid = false;
        }

        if (lastName.trim()) {
            errorsCopy.lastName = "";
        } else {
            errorsCopy.lastName = "Last name is required";
            valid = false;
        }

        if (email.trim()) {
            errorsCopy.email = "";
        } else {
            errorsCopy.email = "Email is required";
            valid = false;
        }

        if (departmentId){
            errorsCopy.department = ""
        } else {
            errorsCopy.department = 'Select Department'
            valid = false
        }

        setErrors(errorsCopy);

        return valid;
    }

    /*const handleFirstName = (e) => setFirstName(e.target.value)

    const handleLastName = (e) => setLastName(e.target.value) 

    const handleEmail = (e) => setEmail(e.target.value)*/ 

    const saveOrUpdateEmployee = (e) => {
        e.preventDefault();
        if (validateForm()) {
            const employee = { firstName, lastName, email, departmentId }
            console.log(employee)
            if (id) {
                updateEmployee(id, employee).then((response) => {
                    console.log(response.data);
                    navigator('/employees');
                }).catch(error => {
                    console.error(error);
                })
            } else {
                createEmployee(employee).then((response) => {
                    console.log(response.data);
                    navigator("/employees");
                }).catch(error => {
                    console.error(error);
                })
            }
        }
    }

    useEffect(() => {
        if (id) {
            getEmployee(id).then((response) => {
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);
                setDepartmentId(response.data.departmentId)
            }).catch(error => {
                console.error(error);
            })
        }
    }, [id])

    useEffect(() => {
        listDepartments().then((response) => {
            setDepartments(response.data);
        }).catch(error => {
            console.log(error);
        })
    }, [])

    return (
        <div className="container">
            <br></br>
            <br></br>
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    {
                        pageTitle()
                    }
                    <div className="card-body">
                        <form>
                            <div className="form-group mb-2">
                                <label className="form-label">First Name:</label>
                                <input 
                                    type="text"
                                    placeholder="Enter employee first name"
                                    name="firstName"
                                    value={firstName}
                                    className={`form-control ${ errors.firstName ? "is-invalid" : ""}`}
                                    //onChange={handleFirstName}
                                    onChange={(e) => setFirstName(e.target.value)}
                                >
                                </input>
                                { errors.firstName && <div className="invalid-feedback">{errors.firstName}</div> }
                            </div>
                            <div className="form-group mb-2">
                                <label className="form-label">Last Name:</label>
                                <input 
                                    type="text"
                                    placeholder="Enter employee last name"
                                    name="lastName"
                                    value={lastName}
                                    className={`form-control ${ errors.lastName ? "is-invalid" : ""}`}
                                    //onChange={handleLastName}
                                    onChange={(e) => setLastName(e.target.value)}
                                >
                                </input>
                                { errors.lastName && <div className="invalid-feedback">{errors.lastName}</div> }
                            </div>
                            <div className="form-group mb-2">
                                <label className="form-label">Email:</label>
                                <input 
                                    type="text"
                                    placeholder="Enter employee email address"
                                    name="email"
                                    value={email}
                                    className={`form-control ${ errors.email ? "is-invalid" : ""}`}
                                    //onChange={handleEmail}
                                    onChange={(e) => setEmail(e.target.value)}
                                >
                                </input>
                                { errors.email && <div className="invalid-feedback">{errors.email}</div> }
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Select Department:</label>
                                <select
                                    className={`form-control ${ errors.department ? 'is-invalid': '' }`}
                                    value={departmentId}
                                    onChange={(e) => setDepartmentId(e.target.value)}
                                >
                                    <option value="Select Department">Select Department</option>
                                    {
                                        departments.map( department => 
                                            <option key={department.id} value={department.id} > {department.departmentName}</option>
                                        )
                                    }   
                                </select>
                                { errors.department && <div className='invalid-feedback'> { errors.department} </div> }
                            </div>
                            <button className="btn btn-success" onClick={saveOrUpdateEmployee}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
} 

export default EmployeeComponent