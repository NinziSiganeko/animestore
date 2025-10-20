import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../utils/api";

function SignIn() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    // 🔹 Auto-redirect if already signed in
    useEffect(() => {
        const token = localStorage.getItem("userToken");
        const role = localStorage.getItem("userRole");

        if (token && role === "ADMIN") {
            navigate("/admin/dashboard");
        } else if (token && role === "CUSTOMER") {
            navigate("/dashboard");
        }
    }, [navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setLoading(true);

        try {
            // Determine if it's admin based on email domain
            const isAdmin = email.toLowerCase().endsWith("@animestore.co.za");

            // Choose endpoint based on email domain
            const endpoint = isAdmin ? "/admin/signin" : "/customer/signin";

            // Make API call using the api utility (includes auth interceptors)
            const response = await api.post(endpoint, {
                email: email.trim(),
                password: password.trim()
            });

            console.log("Backend response:", response.data);

            if (response.data && response.data.token) {
                // Store JWT token and user information
                localStorage.setItem("userToken", response.data.token);
                localStorage.setItem("userId", response.data.userId || response.data.id || "");
                localStorage.setItem("userEmail", response.data.email || email);
                localStorage.setItem("userRole", response.data.role || (isAdmin ? "ADMIN" : "CUSTOMER"));

                // Store username information
                if (isAdmin) {
                    // For admin, store name and username
                    localStorage.setItem("userName", response.data.name || response.data.username || email.split("@")[0]);
                    localStorage.setItem("firstName", response.data.name || response.data.username || "Admin");
                    localStorage.setItem("lastName", "");
                } else {
                    // For customer, store firstName and lastName
                    localStorage.setItem("firstName", response.data.firstName || "");
                    localStorage.setItem("lastName", response.data.lastName || "");
                    localStorage.setItem("userName", `${response.data.firstName || ""} ${response.data.lastName || ""}`.trim() || email.split("@")[0]);
                }

                // Navigate based on role
                if (response.data.role === "ADMIN") {
                    navigate("/admin/dashboard");
                } else {
                    navigate("/dashboard");
                }
            } else {
                setError("Invalid response from server. Please try again.");
            }
        } catch (err) {
            console.error("Sign in error:", err);

            // Handle different error types
            if (err.response) {
                // Server responded with error
                if (err.response.status === 401) {
                    setError("Invalid email or password");
                } else if (err.response.status === 403) {
                    setError("Access denied. Please check your credentials.");
                } else {
                    setError(err.response.data?.message || "Sign in failed. Please try again.");
                }
            } else if (err.request) {
                // Request made but no response
                setError("Cannot connect to server. Please check your connection.");
            } else {
                // Something else went wrong
                setError("An unexpected error occurred. Please try again.");
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container py-5" style={{ maxWidth: "400px" }}>
            <h2 className="fw-bold mb-4 text-center">Sign In</h2>

            {error && (
                <div className="alert alert-danger alert-dismissible fade show" role="alert">
                    {error}
                    <button
                        type="button"
                        className="btn-close"
                        onClick={() => setError("")}
                        aria-label="Close"
                    ></button>
                </div>
            )}

            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input
                        type="email"
                        className="form-control"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        disabled={loading}
                        placeholder="Enter your email"
                    />
                    {email.toLowerCase().endsWith("@animestore.co.za") && (
                        <small className="text-muted">
                            Admin account detected
                        </small>
                    )}
                </div>

                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input
                        type="password"
                        className="form-control"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        disabled={loading}
                        placeholder="Enter your password"
                    />
                </div>

                <button
                    type="submit"
                    className="btn btn-primary w-100"
                    disabled={loading}
                >
                    {loading ? (
                        <>
                            <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                            Signing in...
                        </>
                    ) : (
                        "Sign In"
                    )}
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