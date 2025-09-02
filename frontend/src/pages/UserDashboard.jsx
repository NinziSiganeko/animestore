function UserDashboard() {
    return (
        <div className="container py-5">
            <h2 className="fw-bold mb-4">👤 My Dashboard</h2>

            <div className="row">
                {/* Sidebar */}
                <div className="col-md-4">
                    <div className="list-group shadow-sm">
                        <button className="list-group-item list-group-item-action">📦 My Orders</button>
                        <button className="list-group-item list-group-item-action">⚙️ Profile Settings</button>
                        <button className="list-group-item list-group-item-action">❤️ Wishlist</button>
                    </div>
                </div>

                {/* Content */}
                <div className="col-md-8">
                    <div className="card shadow-sm">
                        <div className="card-body">
                            <h5>Welcome back, AnimeFan!</h5>
                            <p>You can view your orders, update your profile, and manage your wishlist here.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default UserDashboard;
