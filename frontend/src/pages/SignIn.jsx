import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
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

        if (token && role === "ADMIN") navigate("/admin/dashboard");
        else if (token && role === "CUSTOMER") navigate("/");
    }, [navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setLoading(true);

        try {
            const isAdmin = email.toLowerCase().endsWith("@animestore.co.za");
            const endpoint = isAdmin ? "/admin/signin" : "/customer/signin";

            const response = await api.post(endpoint, {
                email: email.trim(),
                password: password.trim()
            });

            if (response.data && response.data.token) {
                localStorage.setItem("userToken", response.data.token);
                localStorage.setItem("userId", response.data.userId || response.data.id || "");
                localStorage.setItem("userEmail", response.data.email || email);
                localStorage.setItem("userRole", response.data.role || (isAdmin ? "ADMIN" : "CUSTOMER"));

                if (isAdmin) {
                    localStorage.setItem("userName", response.data.name || response.data.username || email.split("@")[0]);
                    localStorage.setItem("firstName", response.data.name || response.data.username || "Admin");
                    localStorage.setItem("lastName", "");
                    navigate("/admin/dashboard"); // Admin goes to dashboard
                } else {
                    localStorage.setItem("firstName", response.data.firstName || "");
                    localStorage.setItem("lastName", response.data.lastName || "");
                    localStorage.setItem("userName", `${response.data.firstName || ""} ${response.data.lastName || ""}`.trim() || email.split("@")[0]);
                    navigate("/"); // Customer goes to Home page
                }
            } else {
                setError("Invalid response from server. Please try again.");
            }
        } catch (err) {
            console.error("Sign in error:", err);
            if (err.response) {
                if (err.response.status === 401) setError("Invalid email or password");
                else if (err.response.status === 403) setError("Access denied");
                else setError(err.response.data?.message || "Sign in failed");
            } else if (err.request) {
                setError("Cannot connect to server. Please check your connection.");
            } else {
                setError("An unexpected error occurred. Please try again.");
            }
        } finally {
            setLoading(false);
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
                    maxWidth: "400px",
                    backgroundColor: "rgba(18,79,143,0.8)",
                    borderRadius: "15px",
                    boxShadow: "0 8px 25px rgba(0,0,0,0.3)",
                    padding: "40px",
                    backdropFilter: "blur(5px)",
                }}
            >
                <h2 className="fw-bold mb-4 text-center text-white">Sign In</h2>

                {error && <div className="alert alert-danger">{error}</div>}

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label text-light">Email</label>
                        <input
                            type="email"
                            className="form-control"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            disabled={loading}
                            placeholder="Enter your email"
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label text-light">Password</label>
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
                        className="btn w-100 mt-3"
                        style={{
                            backgroundColor: "#0D6EFD",
                            color: "#fff",
                            fontWeight: "bold",
                            border: "none",
                        }}
                        disabled={loading}
                    >
                        {loading ? "Signing In..." : "Sign In"}
                    </button>

                    <p className="text-center mt-3 text-white">
                        Don't have an account?{" "}
                        <Link to="/signup" className="text-warning fw-bold">
                            Sign Up
                        </Link>
                    </p>
                </form>
            </div>
        </div>
    );
}

export default SignIn;