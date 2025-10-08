import { useState, useEffect } from "react";

function UserDashboard() {
    const [tab, setTab] = useState("orders");
    const [orders, setOrders] = useState([]);
    const [profile, setProfile] = useState({
        firstName: "",
        lastName: "",
        email: ""
    });

    const userId = localStorage.getItem("userId");

    // Fetch profile
    useEffect(() => {
        fetch(`http://localhost:8080/customers/${userId}`)
            .then(res => res.json())
            .then(data => setProfile(data))
            .catch(err => console.error("Error loading profile:", err));
    }, [userId]);

    // Fetch orders
    useEffect(() => {
        fetch(`http://localhost:8080/orders/customer/${userId}`)
            .then(res => res.json())
            .then(data => setOrders(data))
            .catch(err => console.error("Error loading orders:", err));
    }, [userId]);

    const handleProfileUpdate = async (e) => {
        e.preventDefault();
        try {
            const res = await fetch(`http://localhost:8080/customers/${userId}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(profile),
            });
            if (!res.ok) throw new Error("Update failed");
            alert("Profile updated successfully!");
        } catch (err) {
            alert("Failed to update profile");
        }
    };

    return (
        <div className="container py-5">
            <h2 className="fw-bold mb-4">👤 My Dashboard</h2>
            <div className="row">
                {/* Sidebar */}
                <div className="col-md-3">
                    <div className="list-group shadow-sm">
                        <button
                            className={`list-group-item list-group-item-action ${tab === "orders" ? "active" : ""}`}
                            onClick={() => setTab("orders")}
                        >
                            📦 My Orders
                        </button>
                        <button
                            className={`list-group-item list-group-item-action ${tab === "profile" ? "active" : ""}`}
                            onClick={() => setTab("profile")}
                        >
                            ⚙️ Profile Settings
                        </button>
                    </div>
                </div>

                {/* Content */}
                <div className="col-md-9">
                    <div className="card shadow-sm">
                        <div className="card-body">
                            {tab === "orders" && (
                                <>
                                    <h5 className="fw-bold mb-3">📦 My Orders</h5>
                                    {orders.length > 0 ? (
                                        <table className="table table-striped">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Date</th>
                                                <th>Status</th>
                                                <th>Total</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            {orders.map((o) => (
                                                <tr key={o.orderId}>
                                                    <td>{o.orderId}</td>
                                                    <td>{new Date(o.createdAt).toLocaleDateString()}</td>
                                                    <td>{o.status}</td>
                                                    <td>R {o.totalAmount.toFixed(2)}</td>
                                                </tr>
                                            ))}
                                            </tbody>
                                        </table>
                                    ) : (
                                        <p className="text-muted">No orders found.</p>
                                    )}
                                </>
                            )}

                            {tab === "profile" && (
                                <>
                                    <h5 className="fw-bold mb-3">⚙️ Profile Settings</h5>
                                    <form onSubmit={handleProfileUpdate}>
                                        <div className="mb-3">
                                            <label className="form-label">First Name</label>
                                            <input
                                                type="text"
                                                className="form-control"
                                                value={profile.firstName}
                                                onChange={(e) => setProfile({ ...profile, firstName: e.target.value })}
                                                required
                                            />
                                        </div>
                                        <div className="mb-3">
                                            <label className="form-label">Last Name</label>
                                            <input
                                                type="text"
                                                className="form-control"
                                                value={profile.lastName}
                                                onChange={(e) => setProfile({ ...profile, lastName: e.target.value })}
                                                required
                                            />
                                        </div>
                                        <div className="mb-3">
                                            <label className="form-label">Email</label>
                                            <input
                                                type="email"
                                                className="form-control"
                                                value={profile.email}
                                                onChange={(e) => setProfile({ ...profile, email: e.target.value })}
                                                required
                                            />
                                        </div>
                                        <button className="btn btn-primary">Save Changes</button>
                                    </form>
                                </>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default UserDashboard;
