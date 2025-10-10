import { useCart } from "../context/CartContext";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../utils/api";

function Checkout() {
    const { cart, clearCart } = useCart();
    const [loading, setLoading] = useState(false);
    const [paymentMethod, setPaymentMethod] = useState("");
    const [paymentDetails, setPaymentDetails] = useState({});
    const [customer, setCustomer] = useState(null);
    const navigate = useNavigate();
    const total = cart.reduce((sum, item) => sum + item.price * item.qty, 0);

    useEffect(() => {
        const token = localStorage.getItem("userToken");
        const userEmail = localStorage.getItem("userEmail"); // Store email during signin
        const userId = localStorage.getItem("userId");

        if (!token) return;
        // Try to fetch by userId first, then by email
        const fetchCustomer = async () => {
            try {
                let response;
                if (userId) {
                    response = await api.get(`/customer/read/${userId}`);
                } else if (userEmail) {
                    response = await api.get(`/customer/read/email/${userEmail}`);
                } else {
                    throw new Error("No user identifier found");
                }

                console.log("Fetched customer:", response.data);
                setCustomer(response.data);
            } catch (err) {
                console.error("Error fetching customer:", err);
                if (err.response?.status === 403 || err.response?.status === 401) {
                    localStorage.clear();
                    navigate("/signin");
                }
            }
        };
        fetchCustomer();
    }, [navigate]);
    // Handle payment method selection (dropdown)
    const handlePaymentChange = (e) => {
        setPaymentMethod(e.target.value);
        setPaymentDetails({}); // reset previous payment fields
    };
// Handle dynamic input fields
    const handleDetailChange = (e) => {
        const { name, value } = e.target;
        setPaymentDetails((prev) => ({
            ...prev,
            [name]: value,
        }));
    };
// Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!customer) {
            alert("Customer details not loaded yet. Please wait a moment.");
            return;
        }

        setLoading(true);
        try {
            // Map the frontend method to backend enum
            const methodMap = {
                card: "CREDIT_CARD",
                eft: "EFT",
                paypal: "PAYPAL",
            };

            // Create a mock transaction reference
            const transactionReference = `TXN-${Date.now()}`;

            // Build payment request
            const paymentRequest = {
                amount: total,
                method: methodMap[paymentMethod],
                status: "PENDING",
                transactionReference,
                customer: {
                    userId: customer.userId,
                    email: customer.email,
                },
            };

            console.log("Submitting payment:", paymentRequest);

            // Send to backend
            const response = await api.post("/payment/create", paymentRequest);
            console.log("Payment success:", response.data);

            const paymentData = response.data; // save for clarity

            clearCart();


            navigate("/success", {
                state: {
                    orderId: paymentData.orderId,
                    paymentId: paymentData.paymentId,
                    amount: paymentData.amount,
                    status: paymentData.status,
                    transactionReference: paymentData.transactionReference,
                    customerEmail: paymentData.customerEmail,
                    customerName: paymentData.customerName,
                    orderDate: paymentData.orderDate,
                    cartItems: cart,
                },
            });
        } catch (error) {
            console.error("Payment failed:", error);
            alert("Payment could not be processed. Please try again.");
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
                                    <li
                                        key={item.id}
                                        className="list-group-item d-flex justify-content-between"
                                    >
                    <span>
                      {item.name} (x{item.qty})
                    </span>
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
                        <div className="card-header bg-dark text-white fw-bold">
                            Shipping & Payment
                        </div>
                        <div className="card-body">
                            <form onSubmit={handleSubmit}>
                                {/* Customer Info */}
                                <div className="mb-3">
                                    <label className="form-label fw-bold">Full Name</label>
                                    <p className="form-control-plaintext">
                                        {customer
                                            ? `${customer.firstName} ${customer.lastName}`
                                            : "Loading..."}
                                    </p>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label fw-bold">Delivery Address</label>
                                    <p className="form-control-plaintext">
                                        {customer ? customer.address : "Loading..."}
                                    </p>
                                </div>
                                <div className="mb-3 text-muted">
                                    <small>
                                        If you need to update your info, go to{" "}
                                        <a href="/dashboard">My Profile</a>.
                                    </small>
                                </div>
                                {/* Payment Method */}
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
                                {/* Dynamic Fields */}
                                {paymentMethod === "card" && (
                                    <div className="mb-3">
                                        <label className="form-label">Card Number</label>
                                        <input
                                            type="text"
                                            name="cardNumber"
                                            className="form-control"
                                            onChange={handleDetailChange}
                                            required
                                        />
                                        <label className="form-label mt-2">Expiry Date</label>
                                        <input
                                            type="text"
                                            name="expiryDate"
                                            className="form-control"
                                            placeholder="MM/YY"
                                            onChange={handleDetailChange}
                                            required
                                        />
                                        <label className="form-label mt-2">CVV</label>
                                        <input
                                            type="text"
                                            name="cvv"
                                            className="form-control"
                                            onChange={handleDetailChange}
                                            required
                                        />
                                    </div>
                                )}
                                {paymentMethod === "eft" && (
                                    <div className="mb-3">
                                        <label className="form-label">Bank Name</label>
                                        <input
                                            type="text"
                                            name="bankName"
                                            className="form-control"
                                            onChange={handleDetailChange}
                                            required
                                        />
                                        <label className="form-label mt-2">Account Number</label>
                                        <input
                                            type="text"
                                            name="accountNumber"
                                            className="form-control"
                                            onChange={handleDetailChange}
                                            required
                                        />
                                    </div>
                                )}
                                {paymentMethod === "paypal" && (
                                    <div className="mb-3">
                                        <label className="form-label">PayPal Email</label>
                                        <input
                                            type="email"
                                            name="paypalEmail"
                                            className="form-control"
                                            onChange={handleDetailChange}
                                            required
                                        />
                                    </div>
                                )}
                                <button
                                    type="submit"
                                    className="btn btn-dark w-100"
                                    disabled={loading}
                                >
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
