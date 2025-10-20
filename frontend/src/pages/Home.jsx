import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useCart } from "../context/CartContext";

function Home() {
    const { addToCart } = useCart();
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);

    // Fetch only available products (stock > 0) from backend
    useEffect(() => {
        fetch("http://localhost:8080/products/available")
            .then((res) => {
                if (!res.ok) throw new Error("Failed to fetch products");
                return res.json();
            })
            .then((data) => {
                // Take only first 12 available products
                setProducts(data.slice(0, 12));
                setLoading(false);
            })
            .catch((err) => {
                console.error("Error fetching products:", err);
                setLoading(false);
            });
    }, []);

    return (
        <div>
            {/* Hero Section with Carousel - UNCHANGED */}
            <header>
                <div
                    id="heroCarousel"
                    className="carousel slide carousel-fade"
                    data-bs-ride="carousel"
                    data-bs-interval="5000"
                >
                    <div className="carousel-inner">
                        {/* Slide 1 */}
                        <div
                            className="carousel-item active"
                            style={{
                                height: "70vh",
                                backgroundImage: "url('/img/hero1.webp')",
                                backgroundSize: "cover",
                                backgroundPosition: "center",
                            }}
                        >
                            <div
                                className="d-flex h-100 align-items-center justify-content-center text-center text-white"
                                style={{ backgroundColor: "rgba(0, 0, 50, 0.5)" }}
                            >
                                <div>
                                    <h1 className="display-3 fw-bold">Unleash Your Anime Style</h1>
                                    <p className="lead mt-3">
                                        Premium hoodies, tees & beanies inspired by your favorite anime.
                                    </p>
                                    <Link
                                        to="/catalog"
                                        className="btn btn-primary btn-lg mt-4 shadow"
                                    >
                                        Shop Now <i className="bi bi-arrow-right-circle ms-2"></i>
                                    </Link>
                                </div>
                            </div>
                        </div>

                        {/* Slide 2 */}
                        <div
                            className="carousel-item"
                            style={{
                                height: "70vh",
                                backgroundImage: "url('/img/hero2.webp')",
                                backgroundSize: "cover",
                                backgroundPosition: "center",
                            }}
                        >
                            <div
                                className="d-flex h-100 align-items-center justify-content-center text-center text-white"
                                style={{ backgroundColor: "rgba(0, 0, 50, 0.5)" }}
                            >
                                <div>
                                    <h1 className="display-3 fw-bold">Wear the Legend</h1>
                                    <p className="lead mt-3">
                                        From Naruto to One Piece, bring your favorite heroes to life.
                                    </p>
                                    <Link
                                        to="/catalog"
                                        className="btn btn-primary btn-lg mt-4 shadow"
                                    >
                                        Explore Collection <i className="bi bi-arrow-right-circle ms-2"></i>
                                    </Link>
                                </div>
                            </div>
                        </div>

                        {/* Slide 3 */}
                        <div
                            className="carousel-item"
                            style={{
                                height: "70vh",
                                backgroundImage: "url('/img/hero3.jpeg')",
                                backgroundSize: "cover",
                                backgroundPosition: "center",
                            }}
                        >
                            <div
                                className="d-flex h-100 align-items-center justify-content-center text-center text-white"
                                style={{ backgroundColor: "rgba(18,79,143,0.67)" }}
                            >
                                <div>
                                    <h1 className="display-3 fw-bold">Worldwide Shipping</h1>
                                    <p className="lead mt-3">
                                        Get your anime drip delivered to your doorstep.
                                    </p>
                                    <Link
                                        to="/catalog"
                                        className="btn btn-primary btn-lg mt-4 shadow"
                                    >
                                        Start Shopping <i className="bi bi-arrow-right-circle ms-2"></i>
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Carousel dots */}
                    <div className="carousel-indicators">
                        <button type="button" data-bs-target="#heroCarousel" data-bs-slide-to="0" className="active"></button>
                        <button type="button" data-bs-target="#heroCarousel" data-bs-slide-to="1"></button>
                        <button type="button" data-bs-target="#heroCarousel" data-bs-slide-to="2"></button>
                    </div>
                </div>
            </header>

            {/* Best Sellers Section */}
            <main className="container py-5">
                <h2 className="text-center mb-5 fw-bold text-primary">Best Sellers</h2>

                {loading ? (
                    <div className="text-center">
                        <div className="spinner-border text-primary" role="status">
                            <span className="visually-hidden">Loading...</span>
                        </div>
                        <p className="mt-2 text-muted">Loading products...</p>
                    </div>
                ) : (
                    <div className="row g-4">
                        {products.length > 0 ? (
                            products.map((p) => (
                                <div className="col-md-6 col-lg-4" key={p.productId}>
                                    <div className="card shadow-lg border-0 h-100 hover-shadow">
                                        <Link to={`/product/${p.productId}`}>
                                            <img
                                                src={
                                                    p.productImage
                                                        ? `data:image/jpeg;base64,${p.productImage}`
                                                        : "/img/placeholder.png"
                                                }
                                                alt={p.name}
                                                className="card-img-top"
                                                style={{
                                                    height: "250px",   // uniform image height
                                                    objectFit: "cover", // keeps images same size
                                                }}
                                                onError={(e) => (e.target.src = "/img/placeholder.png")}
                                            />
                                        </Link>
                                        <div className="card-body text-center">
                                            <h5 className="card-title text-secondary">{p.name}</h5>
                                            <p className="text-muted">R {p.price.toFixed(2)}</p>
                                            <p className="text-success">
                                                In Stock: {p.stock}
                                            </p>
                                            <button
                                                className="btn btn-primary btn-sm"
                                                onClick={() => addToCart(p)}
                                            >
                                                <i className="bi bi-cart me-1"></i> Add to Cart
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            ))
                        ) : (
                            <div className="col-12 text-center">
                                <div className="py-5">
                                    <i className="fas fa-box-open fa-3x text-muted mb-3"></i>
                                    <h4 className="text-muted">No Products Available</h4>
                                    <p className="text-muted">Check back later for new arrivals!</p>
                                    <Link to="/catalog" className="btn btn-outline-primary mt-2">
                                        Browse Catalog
                                    </Link>
                                </div>
                            </div>
                        )}
                    </div>
                )}
            </main>

            {/* About Section - UNCHANGED */}
            <section
                className="py-5 text-white"
                style={{
                    backgroundImage: "url('https://wallpaperaccess.com/full/9824285.jpg')",
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                }}
            >
                <div
                    className="container text-center"
                    style={{
                        backgroundColor: "rgba(0, 0, 50, 0.6)",
                        borderRadius: "12px",
                        padding: "40px",
                    }}
                >
                    <h2 className="display-5 fw-bold mb-4 text-primary">Why Choose AnimeStore?</h2>
                    <p className="lead mb-5">
                        AnimeStore isn't just about fashion it's about passion. We blend premium fabrics with
                        authentic anime-inspired designs, crafted locally for fans who live and breathe anime culture.
                    </p>

                    {/* Features */}
                    <div className="row g-4 mb-5">
                        <div className="col-md-4">
                            <div className="p-4 bg-secondary rounded h-100 shadow">
                                <i className="bi  text-primary display-6 mb-3"></i>
                                <h5 className="fw-bold">Premium Quality</h5>
                                <p>Soft, durable fabrics designed for comfort hoodies, tees, and more.</p>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="p-4 bg-secondary rounded h-100 shadow">
                                <i className="bi  text-primary display-6 mb-3"></i>
                                <h5 className="fw-bold">Nationwide Delivery</h5>
                                <p>We proudly deliver across South Africa from Cape Town to Johannesburg and beyond.</p>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="p-4 bg-secondary rounded h-100 shadow">
                                <i className="bi  text-primary display-6 mb-3"></i>
                                <h5 className="fw-bold">For True Fans</h5>
                                <p>Every design is inspired by legendary anime made by fans, for fans.</p>
                            </div>
                        </div>
                    </div>

                    {/* Social Media Links */}
                    <div className="d-flex justify-content-center gap-4">
                        <a
                            href="https://www.facebook.com/AnimeStoreSA"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-white fs-3"
                        >
                            <i className="bi bi-facebook"></i>
                        </a>
                        <a
                            href="https://www.instagram.com/AnimeStoreSA"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-white fs-3"
                        >
                            <i className="bi bi-instagram"></i>
                        </a>
                        <a
                            href="https://www.tiktok.com/@AnimeStoreSA"
                            target="_blank"
                            rel="noopener noreferrer"
                            className="text-white fs-3"
                        >
                            <i className="bi bi-tiktok"></i>
                        </a>
                    </div>
                    <p className="mt-3 text-white-50">Follow us for new drops and exclusive deals</p>
                </div>
            </section>
        </div>
    );
}

export default Home;