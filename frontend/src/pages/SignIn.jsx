import { useState } from "react";
import { useNavigate } from "react-router-dom";

function SignIn() {
    const navigate = useNavigate();
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        const email = e.target.email.value.toLowerCase();
        const password = e.target.password.value;

        try {
            const res = await fetch("http://localhost:8080/api/auth/signin", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password }),
            });

            const data = await res.json();
            if (!res.ok) {
                setError(data.error || "Signin failed");
                return;
            }

            // Redirect based on role
            if (data.role.toLowerCase() === "admin") {
                navigate("/adminDashboard");
            } else {
                navigate("/"); // Home.jsx
            }

        } catch (err) {
            console.error(err);
            setError("Something went wrong. Try again.");
        }
    };

    return (
        <div className="container py-5" style={{ maxWidth: "500px" }}>
            <h2 className="fw-bold mb-4 text-center"> Sign In</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input name="email" type="email" className="form-control" placeholder="Enter your email" required />
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input name="password" type="password" className="form-control" placeholder="Enter your password" required />
                </div>

                {error && <p className="text-danger">{error}</p>}

                <button type="submit" className="btn btn-dark w-100 mt-3">Sign In</button>
                <p className="text-center mt-3">
                    Don’t have an account? <a href="/signup" className="text-primary">Sign Up</a>
                </p>
            </form>
        </div>
    );
}

export default SignIn;