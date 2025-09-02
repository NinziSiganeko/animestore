import { useCart } from "../context/CartContext";

function AdminDashboard() {
    const { orders } = useCart();

    // Calculate stats
    const totalOrders = orders.length;
    const totalItems = orders.reduce(
        (sum, order) => sum + order.items.reduce((s, i) => s + i.qty, 0),
        0
    );
    const totalRevenue = orders.reduce((sum, order) => sum + order.total, 0);

    return (
        <div className="container py-5">
            <h2 className="fw-bold mb-4">📊 Admin Dashboard</h2>

            <div className="row g-4 mb-5">
                {/* Total Orders */}
                <div className="col-md-4">
                    <div className="card shadow-sm border-0">
                        <div className="card-body text-center">
                            <i className="bi bi-receipt-cutoff display-5 text-primary"></i>
                            <h5 className="mt-3">Total Orders</h5>
                            <h3 className="fw-bold">{totalOrders}</h3>
                        </div>
                    </div>
                </div>

                {/* Total Items */}
                <div className="col-md-4">
                    <div className="card shadow-sm border-0">
                        <div className="card-body text-center">
                            <i className="bi bi-box-seam display-5 text-warning"></i>
                            <h5 className="mt-3">Items Sold</h5>
                            <h3 className="fw-bold">{totalItems}</h3>
                        </div>
                    </div>
                </div>

                {/* Total Revenue */}
                <div className="col-md-4">
                    <div className="card shadow-sm border-0">
                        <div className="card-body text-center">
                            <i className="bi bi-cash-stack display-5 text-success"></i>
                            <h5 className="mt-3">Revenue</h5>
                            <h3 className="fw-bold">R {totalRevenue.toFixed(2)}</h3>
                        </div>
                    </div>
                </div>
            </div>

            {/* Recent Orders */}
            <h4 className="fw-bold mb-3">📦 Recent Orders</h4>
            {orders.length === 0 ? (
                <p>No orders yet.</p>
            ) : (
                <div className="list-group">
                    {orders.slice(-5).reverse().map((order) => (
                        <div key={order.id} className="list-group-item shadow-sm mb-3">
                            <h6>Order #{order.id}</h6>
                            <p className="mb-1 text-muted">{order.date}</p>
                            <strong>Total: R {order.total.toFixed(2)}</strong>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default AdminDashboard;
