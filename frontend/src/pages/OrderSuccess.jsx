import { useLocation, Link } from "react-router-dom";

function OrderSuccess() {
    const { state } = useLocation();

    return (
        <div className="container py-5 text-center">
            <h2 className="text-success fw-bold mb-3"> Order Purchased Successfully!</h2>
            <p>Thank you for shopping with AnimeWear.</p>

            {state && (
                <>
                    {/* Order Summary */}
                    <h5 className="mt-4">Order Summary:</h5>
                    <ul className="list-group mb-4">
                        {state.items.map((item) => (
                            <li key={item.id} className="list-group-item d-flex justify-content-between">
                                <span>{item.name} (x{item.qty})</span>
                                <span>R {(item.price * item.qty).toFixed(2)}</span>
                            </li>
                        ))}
                        <li className="list-group-item fw-bold d-flex justify-content-between">
                            <span>Total</span>
                            <span>R {state.total.toFixed(2)}</span>
                        </li>
                    </ul>

                    {/* Payment Details */}
                    {state.payment && (
                        <div className="card shadow border-0 mb-4">
                            <div className="card-header bg-dark text-white fw-bold">
                                Payment Details
                            </div>
                            <div className="card-body text-start">
                                <p><strong>Payment ID:</strong> {state.payment.paymentId}</p>
                                <p><strong>Amount Paid:</strong> R {state.payment.amount.toFixed(2)}</p>
                                <p><strong>Date:</strong> {new Date(state.payment.paymentDate).toLocaleString()}</p>
                                <p><strong>Status:</strong> {state.payment.status}</p>
                                <p><strong>Method:</strong> {state.payment.method}</p>
                            </div>
                        </div>
                    )}
                </>
            )}

            <Link to="/catalog" className="btn btn-warning">Continue Shopping</Link>
        </div>
    );
}

export default OrderSuccess;
