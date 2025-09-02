function SignUp() {
    return (
        <div className="container py-5" style={{ maxWidth: "500px" }}>
            <h2 className="fw-bold mb-4 text-center">📝 Create an Account</h2>
            <form>
                <div className="mb-3">
                    <label className="form-label">Full Name</label>
                    <input type="text" className="form-control" placeholder="Enter your name" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input type="email" className="form-control" placeholder="Enter your email" />
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input type="password" className="form-control" placeholder="Create a password" />
                </div>
                <button className="btn btn-warning w-100 mt-3">Sign Up</button>
                <p className="text-center mt-3">
                    Already have an account? <a href="/signin" className="text-primary">Sign In</a>
                </p>
            </form>
        </div>
    );
}

export default SignUp;
