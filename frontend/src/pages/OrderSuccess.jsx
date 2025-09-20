import { useLocation, Link } from "react-router-dom";

function OrderSuccess() {
    const { state } = useLocation();

    return (
        <div className="container py-5 text-center">
            <h2 className="text-success fw-bold mb-3">🎉 Order Placed Successfully!</h2>
            <p>Thank you for shopping with AnimeWear.</p>

            {state && (
                <>
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
                </>
            )}

            <Link to="/catalog" className="btn btn-warning">Continue Shopping</Link>
        </div>
    );
}

export default OrderSuccess;
