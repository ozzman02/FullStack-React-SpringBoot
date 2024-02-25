import { useState } from "react"
import { createEmployee } from "../services/EmployeeService"
import { useNavigate } from "react-router-dom"

const EmployeeComponent = () => {

    const [firstName, setFirstName] = useState("")

    const [lastName, setLastName] = useState("")
    
    const [email, setEmail] = useState("")

    const navigator = useNavigate();

    /* Initialize state variables that will hold validation errors */
    const [errors, setErrors] = useState({
        firstName: "",
        lastName: "",
        email: ""
    })

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

        setErrors(errorsCopy);

        return valid;
    }

    /*const handleFirstName = (e) => setFirstName(e.target.value)

    const handleLastName = (e) => setLastName(e.target.value) 

    const handleEmail = (e) => setEmail(e.target.value)*/ 

    const saveEmployee = (e) => {
        e.preventDefault();
        if (validateForm()) {
            const employee = { firstName, lastName, email }
            console.log(employee)
            createEmployee(employee).then((response) => {
                console.log(response.data);
                navigator("/employees");
            })
        }
    }

    return (
        <div className="container">
            <br></br>
            <br></br>
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    <h2 className="text-center">Add Employee</h2>
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
                            <button className="btn btn-success" onClick={saveEmployee}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
} 

export default EmployeeComponent