import { useState } from "react"

const EmployeeComponent = () => {

    const [firstName, setFirstName] = useState("")

    const [lastName, setLastName] = useState("")
    
    const [email, setEmail] = useState("")

    /*const handleFirstName = (e) => setFirstName(e.target.value)

    const handleLastName = (e) => setLastName(e.target.value) 

    const handleEmail = (e) => setEmail(e.target.value)*/ 

    const saveEmployee = (e) => {
        e.preventDefault();
        const employee = { firstName, lastName, email }
        console.log(employee)
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
                                    className="form-control"
                                    //onChange={handleFirstName}
                                    onChange={(e) => setFirstName(e.target.value)}
                                >
                                </input>
                            </div>
                            <div className="form-group mb-2">
                                <label className="form-label">Last Name:</label>
                                <input 
                                    type="text"
                                    placeholder="Enter employee last name"
                                    name="lastName"
                                    value={lastName}
                                    className="form-control"
                                    //onChange={handleLastName}
                                    onChange={(e) => setLastName(e.target.value)}
                                >
                                </input>
                            </div>
                            <div className="form-group mb-2">
                                <label className="form-label">Email:</label>
                                <input 
                                    type="text"
                                    placeholder="Enter employee email address"
                                    name="email"
                                    value={email}
                                    className="form-control"
                                    //onChange={handleEmail}
                                    onChange={(e) => setEmail(e.target.value)}
                                >
                                </input>
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