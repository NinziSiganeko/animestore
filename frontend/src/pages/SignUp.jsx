import { useState } from "react";
import { useNavigate } from "react-router-dom";

function SignUp() {
    const navigate = useNavigate();
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        const username = e.target.name.value;
        const email = e.target.email.value.toLowerCase();
        const password = e.target.password.value;

        try {
            const res = await fetch("http://localhost:8080/api/auth/signup", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, email, password }),
            });

            if (!res.ok) {
                const data = await res.json();
                setError(data.error || "Signup failed");
                return;
            }

            navigate("/signin"); // redirect to signin after signup
        } catch (err) {
            setError("Something went wrong. Try again.");
        }
    };

    return (
        <div className="container py-5" style={{ maxWidth: "500px" }}>
            <h2 className="fw-bold mb-4 text-center"> Create an Account</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Full Name</label>
                    <input name="name" type="text" className="form-control" placeholder="Enter your name" required />
                </div>
                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input name="email" type="email" className="form-control" placeholder="Enter your email" required />
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input name="password" type="password" className="form-control" placeholder="Create a password" required />
                </div>

                {error && <p className="text-danger">{error}</p>}

                <button type="submit" className="btn btn-dark w-100 mt-3">Sign Up</button>
                <p className="text-center mt-3">
                    Already have an account? <a href="/signin" className="text-primary">Sign In</a>
                </p>
            </form>
        </div>
    );
}

export default SignUp;