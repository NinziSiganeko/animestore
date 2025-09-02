function Success() {
    return (
        <div className="container py-5 text-center">
            <i className="bi bi-check-circle-fill text-success display-1 mb-3"></i>
            <h2 className="fw-bold">Order Placed Successfully 🎉</h2>
            <p className="lead text-muted mt-3">
                Thank you for shopping with <span className="fw-bold text-warning">AnimeWear</span>!
                Your order is being processed and will be shipped soon.
            </p>
            <a href="/catalog" className="btn btn-dark mt-4">
                Continue Shopping
            </a>
        </div>
    );
}

export default Success;
