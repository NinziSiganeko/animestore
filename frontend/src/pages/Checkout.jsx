import { useCart } from "../context/CartContext";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Checkout() {
    const { cart, clearCart } = useCart();
    const [loading, setLoading] = useState(false);
    const [paymentMethod, setPaymentMethod] = useState("");
    const [paymentDetails, setPaymentDetails] = useState({});
    const [customer, setCustomer] = useState(null);
    const navigate = useNavigate();

    const total = cart.reduce((sum, item) => sum + item.price * item.qty, 0);

    // 🔹 Fetch customer info on mount
    useEffect(() => {
        const token = localStorage.getItem("userToken");
        const userId = localStorage.getItem("userId");

        if (!token || !userId) {
            navigate("/signin");
            return;
        }

        fetch(`http://localhost:8080/customer/read/${userId}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        })
            .then(res => {
                if (!res.ok) throw new Error("Failed to fetch customer");
                return res.json();
            })
            .then(data => {
                console.log("Fetched customer:", data);
                setCustomer(data);
            })
            .catch(err => {
                console.error("Error fetching customer:", err);
                navigate("/signin");
            });
    }, [navigate]);

    const handlePaymentChange = (e) => {
        setPaymentMethod(e.target.value);
        setPaymentDetails({});
    };

    const handleDetailChange = (e) => {
        setPaymentDetails({
            ...paymentDetails,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);

        try {
            const userId = localStorage.getItem("userId");

            const response = await fetch("http://localhost:8080/payment/create", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    amount: total,
                    method:
                        paymentMethod === "card"
                            ? "CREDIT_CARD"
                            : paymentMethod === "eft"
                                ? "EFT"
                                : "PAYPAL",
                    paymentDate: new Date().toISOString(),
                    status: "PENDING",
                    transactionReference:
                        paymentDetails.transactionReference ||
                        paymentDetails.cardNumber ||
                        paymentDetails.accountNumber ||
                        paymentDetails.paypalEmail ||
                        "TEMP_TXN",
                    userId: userId // 🔹 Link payment to UserId from backend User.java
                }),
            });

            if (response.ok) {
                const data = await response.json();
                navigate("/OrderSuccess", {
                    state: {
                        items: cart,
                        total,
                        payment: data,
                        customer: customer
                    },
                });
                clearCart();
            } else {
                alert("Payment failed ");
            }
        } catch (err) {
            console.error("Error creating payment:", err);
            alert("Error processing payment");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container py-5">
            <h2 className="fw-bold mb-4">Checkout</h2>

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
                        <div className="card-header bg-dark text-white fw-bold">Shipping & Payment</div>
                        <div className="card-body">
                            <form onSubmit={handleSubmit}>
                                {/* Autofilled Customer Details */}
                                <div className="mb-3">
                                    <label className="form-label fw-bold">Full Name</label>
                                    <p className="form-control-plaintext">
                                        {customer ? customer.firstName + " " + customer.lastName : "Loading..."}
                                    </p>
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-bold">Delivery Address</label>
                                    <p className="form-control-plaintext">
                                        {customer ? customer.address : "Loading..."}
                                    </p>
                                </div>


                                <div className="mb-3 text-muted">
                                    <small>If you need to update your info, go to <a href="/dashboard">My Profile</a>.</small>
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Payment Method</label>
                                    <select
                                        className="form-select"
                                        value={paymentMethod}
                                        onChange={handlePaymentChange}
                                        required
                                    >
                                        <option value="">Select...</option>
                                        <option value="card">Credit / Debit Card</option>
                                        <option value="eft">EFT / Bank Transfer</option>
                                        <option value="paypal">PayPal</option>
                                    </select>
                                </div>

                                {/* Dynamic Payment Fields */}
                                {paymentMethod === "card" && (
                                    <div className="mb-3">
                                        <label className="form-label">Card Number</label>
                                        <input type="text" name="cardNumber" className="form-control" onChange={handleDetailChange} required />
                                        <label className="form-label mt-2">Expiry Date</label>
                                        <input type="text" name="expiryDate" className="form-control" placeholder="MM/YY" onChange={handleDetailChange} required />
                                        <label className="form-label mt-2">CVV</label>
                                        <input type="text" name="cvv" className="form-control" onChange={handleDetailChange} required />
                                    </div>
                                )}

                                {paymentMethod === "eft" && (
                                    <div className="mb-3">
                                        <label className="form-label">Bank Name</label>
                                        <input type="text" name="bankName" className="form-control" onChange={handleDetailChange} required />
                                        <label className="form-label mt-2">Account Number</label>
                                        <input type="text" name="accountNumber" className="form-control" onChange={handleDetailChange} required />
                                    </div>
                                )}

                                {paymentMethod === "paypal" && (
                                    <div className="mb-3">
                                        <label className="form-label">PayPal Email</label>
                                        <input type="email" name="paypalEmail" className="form-control" onChange={handleDetailChange} required />
                                    </div>
                                )}

                                <button type="submit" className="btn btn-dark w-100" disabled={loading}>
                                    {loading ? "Processing..." : "Place Order"}
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
