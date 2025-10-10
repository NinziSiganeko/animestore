import { Link, useLocation } from "react-router-dom";

function OrderSuccess() {
    const { state } = useLocation();

    if (!state) {
        return (
            <div className="container py-5 text-center">
                <h2 className="text-danger fw-bold mb-3">No Order Found</h2>
                <p>Please return to the catalog and place your order again.</p>
                <Link to="/catalog" className="btn btn-outline-primary mt-3">
                    Back to Catalog
                </Link>
            </div>
        );
    }

    return (
        <div className="container py-5 text-center">
            <h2 className="text-success fw-bold mb-3">Order Placed Successfully!</h2>
            <p>Thank you for shopping with AnimeWear.</p>

            <div className="card mx-auto mt-4 shadow-sm" style={{ maxWidth: "600px" }}>
                <div className="card-body text-start">
                    <h5 className="card-title text-center mb-3">Order Summary</h5>
                    <p><strong>Order ID:</strong> {state.orderId}</p>
                    <p><strong>Payment ID:</strong> {state.paymentId}</p>
                    <p><strong>Customer:</strong> {state.customerName}</p>
                    <p><strong>Email:</strong> {state.customerEmail}</p>
                    <p><strong>Transaction Reference:</strong> {state.transactionReference}</p>
                    <p><strong>Order Date:</strong> {new Date(state.orderDate).toLocaleString()}</p>
                    <p><strong>Status:</strong> {state.status}</p>
                    <p className="fw-bold mt-3 fs-5">
                        Total Paid: R {state.amount.toFixed(2)}
                    </p>
                </div>
            </div>

            <Link to="/catalog" className="btn btn-outline-primary mt-4">
                Continue Shopping
            </Link>
        </div>
    );
}

export default OrderSuccess;
