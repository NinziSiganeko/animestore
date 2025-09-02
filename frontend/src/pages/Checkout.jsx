import { useCart } from "../context/CartContext";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Checkout() {
    const { cart, clearCart } = useCart();
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const total = cart.reduce((sum, item) => sum + item.price * item.qty, 0);

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);

        // simulate order placement (later you’ll call backend API here)
        setTimeout(() => {
            setLoading(false);

            // ✅ redirect to success page with order details
            navigate("/order-success", {
                state: {
                    items: cart,
                    total,
                },
            });

            // ✅ clear cart ONLY after navigating
            clearCart();
        }, 1500);
    };

    return (
        <div className="container py-5">
            <h2 className="fw-bold mb-4">🛒 Checkout</h2>

            <div className="row">
                {/* Order Summary */}
                <div className="col-lg-6 mb-4">
                    <div className="card shadow border-0">
                        <div className="card-header bg-dark text-white fw-bold">
                            Order Summary
                        </div>
                        <ul className="list-group list-group-flush">
                            {cart.length === 0 ? (
                                <li className="list-group-item text-muted">Your cart is empty.</li>
                            ) : (
                                cart.map((item) => (
                                    <li key={item.id} className="list-group-item d-flex justify-content-between">
                                        <span>{item.name} (x{item.qty})</span>
                                        <span>R {(item.price * item.qty).toFixed(2)}</span>
                                    </li>
                                ))
                            )}
                            {cart.length > 0 && (
                                <li className="list-group-item d-flex justify-content-between fw-bold">
                                    <span>Total</span>
                                    <span>R {total.toFixed(2)}</span>
                                </li>
                            )}
                        </ul>
                    </div>
                </div>

                {/* Checkout Form */}
                <div className="col-lg-6">
                    <div className="card shadow border-0">
                        <div className="card-header bg-warning fw-bold">Shipping & Payment</div>
                        <div className="card-body">
                            <form onSubmit={handleSubmit}>
                                <div className="mb-3">
                                    <label className="form-label">Full Name</label>
                                    <input type="text" className="form-control" required />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Delivery Address</label>
                                    <textarea className="form-control" rows="3" required></textarea>
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Payment Method</label>
                                    <select className="form-select" required>
                                        <option value="">Select...</option>
                                        <option value="card">Credit / Debit Card</option>
                                        <option value="eft">EFT / Bank Transfer</option>
                                        <option value="cod">Cash on Delivery</option>
                                    </select>
                                </div>

                                <button type="submit" className="btn btn-dark w-100" disabled={loading}>
                                    {loading ? "Placing Order..." : "Place Order"}
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Checkout;
