import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useCart } from "../context/CartContext";

function Catalog() {
    const { addToCart } = useCart();
    const [products, setProducts] = useState([]);
    const [category, setCategory] = useState("all");
    const [search, setSearch] = useState("");

    //Fetch only available products (stock > 0)
    useEffect(() => {
        fetch("http://localhost:8080/products/available") // CHANGED ENDPOINT
            .then((res) => {
                if (!res.ok) throw new Error("Failed to fetch products");
                return res.json();
            })
            .then((data) => {
                setProducts(data);
            })
            .catch((err) => console.error("Error fetching products:", err));
    }, []);

    // Filter products (only available products will be shown)
    const filteredProducts = products.filter((p) => {
        const matchesCategory =
            category === "all" ||
            (p.category?.categoryName &&
                p.category.categoryName.toLowerCase() === category.toLowerCase());

        const matchesSearch =
            search === "" || p.name.toLowerCase().includes(search.toLowerCase());

        return matchesCategory && matchesSearch;
    });

    return (
        <div className="container py-5">
            <h1 className="text-center fw-bold mb-4">Our Catalog</h1>

            {/* Filters */}
            <div className="d-flex flex-wrap justify-content-center gap-3 mb-4">
                <select
                    className="form-select w-auto"
                    value={category}
                    onChange={(e) => setCategory(e.target.value)}
                >
                    <option value="all">All Categories</option>
                    <option value="hoodie">Hoodies</option>
                    <option value="tee">Tees</option>
                    <option value="beanie">Beanies</option>
                    <option value="cap">Caps</option>
                </select>

                <input
                    type="text"
                    className="form-control w-auto"
                    placeholder="Search product..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            {/* Product Grid */}
            <div className="row g-4">
                {filteredProducts.length > 0 ? (
                    filteredProducts.map((p) => (
                        <div className="col-sm-6 col-md-4 col-lg-3" key={p.productId}>
                            <div className="card shadow-lg border-0 h-100 hover-shadow">
                                <Link to={`/product/${p.productId}`}>
                                    {p.productImage ? (
                                        <img
                                            src={`data:image/jpeg;base64,${p.productImage}`}
                                            className="card-img-top"
                                            alt={p.name}
                                            style={{ height: "200px", objectFit: "cover" }}
                                        />
                                    ) : (
                                        <img
                                            src="/img/placeholder.png"
                                            className="card-img-top"
                                            alt="placeholder"
                                            style={{ height: "200px", objectFit: "cover" }}
                                        />
                                    )}
                                </Link>

                                <div className="card-body text-center">
                                    <h5 className="card-title">{p.name}</h5>
                                    <p className="text-muted">R {p.price.toFixed(2)}</p>
                                    <p className="text-success">
                                        In Stock: {p.stock}
                                    </p>
                                    <div className="d-flex justify-content-center gap-2">
                                        <Link
                                            to={`/product/${p.productId}`}
                                            className="btn btn-outline-dark btn-sm"
                                        >
                                            View
                                        </Link>
                                        <button
                                            className="btn btn-dark btn-sm"
                                            onClick={() => addToCart(p)}
                                        >
                                            <i className="bi bi-cart me-1"></i> Add
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))
                ) : (
                    <div className="col-12 text-center">
                        <p className="text-muted">No products found.</p>
                        <p className="text-muted small">
                            {search || category !== "all"
                                ? "Try adjusting your search or filter."
                                : "All products are currently out of stock. Check back soon!"}
                        </p>
                    </div>
                )}
            </div>
        </div>
    );
}
export default Catalog;