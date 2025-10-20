import { useState, useEffect } from "react";
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
    const [userName, setUserName] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        const storedName = localStorage.getItem("userName");
        if (storedName) setUserName(storedName);
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
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
        <div
            style={{
                backgroundImage: "url('https://wallpaperaccess.com/full/9824285.jpg')",
                backgroundSize: "cover",
                backgroundPosition: "center",
                minHeight: "100vh",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                color: "#fff",
            }}
        >
            <div
                className="container py-5"
                style={{
                    maxWidth: "500px",
                    backgroundColor: "rgba(18,79,143,0.8)",
                    borderRadius: "15px",
                    boxShadow: "0 8px 25px rgba(0,0,0,0.3)",
                    padding: "40px",
                    backdropFilter: "blur(5px)",
                }}
            >
                {userName && (
                    <h4 className="text-center mb-3 text-light">Hi, {userName} 👋</h4>
                )}
                <h2 className="fw-bold mb-4 text-center text-white">Sign Up</h2>

                {error && <div className="alert alert-danger">{error}</div>}
                {success && <div className="alert alert-success">{success}</div>}

                <form onSubmit={handleSubmit}>
                    <div className="row">
                        <div className="col-md-6 mb-3">
                            <label className="form-label text-light">First Name</label>
                            <input
                                type="text"
                                className="form-control"
                                name="firstName"
                                value={formData.firstName}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="col-md-6 mb-3">
                            <label className="form-label text-light">Last Name</label>
                            <input
                                type="text"
                                className="form-control"
                                name="lastName"
                                value={formData.lastName}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    <div className="mb-3">
                        <label className="form-label text-light">Email</label>
                        <input
                            type="email"
                            className="form-control"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label text-light">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label text-light">Address</label>
                        <input
                            type="text"
                            className="form-control"
                            name="address"
                            value={formData.address}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label text-light">Phone Number</label>
                        <input
                            type="tel"
                            className="form-control"
                            name="phoneNumber"
                            value={formData.phoneNumber}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <button
                        type="submit"
                        className="btn w-100 mt-3"
                        style={{
                            backgroundColor: "#0D6EFD",
                            color: "#fff",
                            fontWeight: "bold",
                            border: "none",
                        }}
                    >
                        Sign Up
                    </button>

                    <p className="text-center mt-3 text-white">
                        Already have an account?{" "}
                        <Link to="/signin" className="text-warning fw-bold">
                            Sign In
                        </Link>
                    </p>
                </form>
            </div>
        </div>
    );
}

export default SignUp;

