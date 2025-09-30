import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function SignIn() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    // 🔹 Auto-redirect if already signed in
    useEffect(() => {
        const token = localStorage.getItem("userToken");
        const role = localStorage.getItem("userRole");

        if (token && role === "Admin") {
            navigate("/admin/dashboard");
        } else if (token && role === "Customer") {
            navigate("/dashboard");
        }
    }, [navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const response = await fetch("http://localhost:8080/customer/signin", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password }),
            });

            const data = await response.json();
            console.log("Backend response:", data, "status:", response.status);

            if (response.ok) {
                const fullName = data.firstName + " " + data.lastName;
                localStorage.setItem("userToken", data.token);
                localStorage.setItem("userName", fullName);

                // 🔹 Check if email belongs to Admin
                if (email.endsWith("@animestore.co.za")) {
                    localStorage.setItem("userRole", "Admin");
                    navigate("/admin/dashboard");
                } else {
                    localStorage.setItem("userRole", "Customer");
                    navigate("/dashboard");
                }
            } else {
                setError(data.message || "Invalid credentials");
            }
        } catch (err) {
            setError("Network error. Please try again later.");
        }
    };

    return (
        <div className="container py-5" style={{ maxWidth: "400px" }}>
            <h2 className="fw-bold mb-4 text-center">Sign In</h2>

            {error && <div className="alert alert-danger">{error}</div>}

            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input
                        type="email"
                        className="form-control"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input
                        type="password"
                        className="form-control"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                <button type="submit" className="btn btn-warning w-100">
                    Sign In
                </button>

                <p className="text-center mt-3">
                    Don't have an account?{" "}
                    <a href="/signup" className="text-primary">Sign Up</a>
                </p>
            </form>
        </div>
    );
}

export default SignIn;
