import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";

function SignUp() {
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        address: "",
        phoneNumber: ""
    });
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSuccess("");

        try {
            const response = await fetch("http://localhost:8080/customer/create", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData)
            });

            if (response.ok) {
                setSuccess("Account created successfully!");
                setFormData({
                    firstName: "",
                    lastName: "",
                    email: "",
                    password: "",
                    address: "",
                    phoneNumber: ""
                });

                // Redirect to SignIn page after successful signup
                navigate("/signin");
            } else {
                let data;
                try { data = await response.json(); } catch { data = {}; }
                setError(data.message || "Registration failed");
            }
        } catch (err) {
            console.error(err);
            setError("Network error. Make sure backend is running on port 8080.");
        }
    };

    return (
        <div className="container py-5" style={{ maxWidth: "500px" }}>
            <h2 className="fw-bold mb-4 text-center">Sign Up</h2>

            {error && <div className="alert alert-danger">{error}</div>}
            {success && <div className="alert alert-success">{success}</div>}

            <form onSubmit={handleSubmit}>
                <div className="row">
                    <div className="col-md-6 mb-3">
                        <label className="form-label">First Name</label>
                        <input type="text" className="form-control" name="firstName" value={formData.firstName} onChange={handleChange} required />
                    </div>
                    <div className="col-md-6 mb-3">
                        <label className="form-label">Last Name</label>
                        <input type="text" className="form-control" name="lastName" value={formData.lastName} onChange={handleChange} required />
                    </div>
                </div>

                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input type="email" className="form-control" name="email" value={formData.email} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input type="password" className="form-control" name="password" value={formData.password} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label className="form-label">Address</label>
                    <input type="text" className="form-control" name="address" value={formData.address} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label className="form-label">Phone Number</label>
                    <input type="tel" className="form-control" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} required />
                </div>

                <button type="submit" className="btn btn-primary w-100 mt-3">Sign Up</button>

                <p className="text-center mt-3">
                    Already have an account? <Link to="/signin" className="text-primary">Sign In</Link>
                </p>

            </form>
        </div>
    );
}
export default SignUp;

