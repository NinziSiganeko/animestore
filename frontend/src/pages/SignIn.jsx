function SignIn() {
    return (
        <div className="container py-5" style={{ maxWidth: "500px" }}>
            <h2 className="fw-bold mb-4 text-center">🔑 Sign In</h2>
            <form>
                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input type="email" className="form-control" placeholder="Enter your email" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input type="password" className="form-control" placeholder="Enter your password" />
                </div>
                <button className="btn btn-dark w-100 mt-3">Sign In</button>
                <p className="text-center mt-3">
                    Don’t have an account? <a href="/signup" className="text-warning">Sign Up</a>
                </p>
            </form>
        </div>
    );
}

export default SignIn;
