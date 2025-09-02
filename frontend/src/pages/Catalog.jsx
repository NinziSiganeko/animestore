import { useState } from "react";
import { Link } from "react-router-dom";
import { useCart } from "../context/CartContext";

function Catalog() {
    const { addToCart } = useCart();

    // Expanded product data (10 per category)
    const products = [
        // 🔥 Hoodies
        { id: 1, name: "Naruto Hoodie", price: 49.99, img: "/img/naruto-hoodie.png", category: "hoodie", gender: "women" },
        { id: 2, name: "One Piece Tee", price: 24.99, img: "/img/onepiece-tee.webp", category: "hoodie", gender: "men" },
        { id: 3, name: "Attack on Titan Beanie", price: 19.99, img: "/img/aot-beanie.jpg", category: "hoodie", gender: "men" },
        { id: 4, name: "Naruto Hoodie", price: 49.99, img: "/img/naruto-hoodie.webp", category: "hoodie", gender: "women" },
        { id: 5, name: "One Piece Tee", price: 24.99, img: "/img/-tee.webp", category: "hoodie", gender: "men" },
        { id: 6, name: "Attack on Titan Beanie", price: 19.99, img: "/img/t-beanie.webp", category: "hoodie", gender: "men" },
        { id: 7, name: "Naruto Hoodie", price: 49.99, img: "/img/to-hoodie.webp", category: "hoodie", gender: "women" },
        { id: 8, name: "One Piece Tee", price: 24.99, img: "/img/nepiece-tee.jpeg", category: "hoodie", gender: "men" },
        { id: 9, name: "Attack on Titan Beanie", price: 19.99, img: "/img/ot-beanie.jpeg", category: "hoodie", gender: "men" },
        { id: 10, name: "Naruto Hoodie", price: 49.99, img: "/img/o-hoodie.jpeg", category: "hoodie", gender: "women" },

        // 👕 Tees
        { id: 11, name: "Naruto Hoodie", price: 49.99, img: "/img/naruto-hoodie.png",  category: "tee", gender: "women" },
        { id: 12, name: "One Piece Tee", price: 24.99, img: "/img/onepiece-tee.webp" },
        { id: 13, name: "Attack on Titan Beanie", price: 19.99, img: "/img/aot-beanie.jpg",  category: "tee", gender: "men" },
        { id: 14, name: "Naruto Hoodie", price: 49.99, img: "/img/naruto-hoodie.webp",  category: "tee", gender: "women" },
        { id: 15, name: "One Piece Tee", price: 24.99, img: "/img/-tee.webp" },
        { id: 16, name: "Attack on Titan Beanie", price: 19.99, img: "/img/t-beanie.webp",  category: "tee", gender: "men" },
        { id: 17, name: "Naruto Hoodie", price: 49.99, img: "/img/to-hoodie.webp",  category: "tee", gender: "women" },
        { id: 18, name: "One Piece Tee", price: 24.99, img: "/img/nepiece-tee.jpeg",  category: "tee", gender: "men" },
        { id: 19, name: "Attack on Titan Beanie", price: 19.99, img: "/img/ot-beanie.jpeg",  category: "tee", gender: "women" },
        { id: 20, name: "Naruto Hoodie", price: 49.99, img: "/img/o-hoodie.jpeg",  category: "tee", gender: "men" },

        // ❄️ Beanies
        { id: 21, name: "Naruto Hoodie", price: 49.99, img: "/img/naruto-hoodie.png", category: "beanie", gender: "women" },
        { id: 22, name: "One Piece Tee", price: 24.99, img: "/img/onepiece-tee.webp", category: "beanie", gender: "men" },
        { id: 23, name: "Attack on Titan Beanie", price: 19.99, img: "/img/aot-beanie.jpg", category: "beanie", gender: "men" },
        { id: 24, name: "Naruto Hoodie", price: 49.99, img: "/img/to-hoodie.webp", category: "beanie", gender: "women" },
        { id: 25, name: "One Piece Tee", price: 24.99, img: "/img/-tee.webp", category: "beanie", gender: "men" },
        { id: 26, name: "Attack on Titan Beanie", price: 19.99, img: "/img/t-beanie.webp", category: "beanie", gender: "men" },
        { id: 27, name: "Naruto Hoodie", price: 49.99, img: "/img/to-hoodie.webp", category: "beanie", gender: "women" },
        { id: 28, name: "One Piece Tee", price: 24.99, img: "/img/nepiece-tee.jpeg", category: "beanie", gender: "women" },
        { id: 29, name: "Attack on Titan Beanie", price: 19.99, img: "/img/ot-beanie.jpeg", category: "beanie", gender: "men" },
        { id: 30, name: "Naruto Hoodie", price: 49.99, img: "/img/o-hoodie.jpeg", category: "beanie", gender: "men" },

        // 🧢 Caps
        { id: 31, name: "Naruto Hoodie", price: 49.99, img: "/img/naruto-hoodie.png", category: "cap", gender: "women" },
        { id: 32, name: "One Piece Tee", price: 24.99, img: "/img/onepiece-tee.webp", category: "cap", gender: "women" },
        { id: 33, name: "Attack on Titan Beanie", price: 19.99, img: "/img/aot-beanie.jpg", category: "cap", gender: "men" },
        { id: 34, name: "Naruto Hoodie", price: 49.99, img: "/img/naruto-hoodie.webp", category: "cap", gender: "men" },
        { id: 35, name: "One Piece Tee", price: 24.99, img: "/img/-tee.webp", category: "cap", gender: "women"},
        { id: 36, name: "Attack on Titan Beanie", price: 19.99, img: "/img/t-beanie.webp", category: "cap", gender: "women" },
        { id: 37, name: "Naruto Hoodie", price: 49.99, img: "/img/to-hoodie.webp", category: "cap", gender: "men" },
        { id: 38, name: "One Piece Tee", price: 24.99, img: "/img/nepiece-tee.jpeg", category: "cap", gender: "women" },
        { id: 39, name: "Attack on Titan Beanie", price: 19.99, img: "/img/ot-beanie.jpeg", category: "cap", gender: "women" },
        { id: 40, name: "Naruto Hoodie", price: 49.99, img: "/img/o-hoodie.jpeg", category: "cap", gender: "men" },

    ];

    // State for filters
    const [category, setCategory] = useState("all");
    const [gender, setGender] = useState("all");

    // Filter products
    const filteredProducts = products.filter((p) => {
        return (
            (category === "all" || p.category === category) &&
            (gender === "all" || p.gender === gender)
        );
    });

    return (
        <div className="container py-5">
            <h1 className="text-center fw-bold mb-4">🛍️ Our Catalog</h1>

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

                <select
                    className="form-select w-auto"
                    value={gender}
                    onChange={(e) => setGender(e.target.value)}
                >
                    <option value="all">All Genders</option>
                    <option value="men">Men</option>
                    <option value="women">Women</option>
                </select>
            </div>

            {/* Product Grid */}
            <div className="row g-4">
                {filteredProducts.length > 0 ? (
                    filteredProducts.map((p) => (
                        <div className="col-sm-6 col-md-4 col-lg-3" key={p.id}>
                            <div className="card shadow-lg border-0 h-100 hover-shadow">
                                <Link to={`/product/${p.id}`}>
                                    <img src={p.img} className="card-img-top" alt={p.name} />
                                </Link>
                                <div className="card-body text-center">
                                    <h5 className="card-title">{p.name}</h5>
                                    <p className="text-muted">R {p.price.toFixed(2)}</p>
                                    <div className="d-flex justify-content-center gap-2">
                                        <Link to={`/product/${p.id}`} className="btn btn-outline-dark btn-sm">
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
                    <p className="text-center text-muted">No products found 🥲</p>
                )}
            </div>
        </div>
    );
}

export default Catalog;
