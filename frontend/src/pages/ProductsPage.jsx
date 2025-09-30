import React, { useEffect, useState } from "react";
import axios from "axios";

function ProductsPage() {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        let isMounted = true;

        axios
            .get("http://localhost:8080/products")
            .then((response) => {
                if (isMounted) {
                    console.log("Products data:", response.data);
                    setProducts(response.data);
                }
            })
            .catch((error) => {
                console.error("Error fetching products:", error);
            })
            .finally(() => {
                if (isMounted) {
                    setLoading(false);
                }
            });

        return () => {
            isMounted = false;
        };
    }, []);

    if (loading) {
        return (
            <div className="container py-4">
                <div className="d-flex justify-content-center">
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                </div>
            </div>
        );
    }

    return (
        <div className="container py-4">
            <h2 className="fw-bold mb-3">Products</h2>
            <table className="table table-bordered text-center">
                <thead className="table-light">
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {products.length > 0 ? (
                    products.map((product) => (
                        <tr key={product.productId}>
                            <td>
                                {product.productImage ? (
                                    <img
                                        src={`data:image/jpeg;base64,${product.productImage}`}
                                        alt={product.name}
                                        style={{
                                            width: "60px",
                                            height: "60px",
                                            objectFit: "cover",
                                            borderRadius: "8px",
                                        }}
                                        onError={(e) => {
                                            e.target.src = "/placeholder.png";
                                        }}
                                    />
                                ) : (
                                    "No image"
                                )}
                            </td>
                            <td>{product.name}</td>
                            <td>R {product.price}</td>
                            <td>{product.category?.categoryName || 'No category'}</td>
                            <td>
                                <button className="btn btn-warning btn-sm me-2">Edit</button>
                                <button className="btn btn-danger btn-sm">Delete</button>
                            </td>
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan="5">No products available</td>
                    </tr>
                )}
                </tbody>
            </table>
        </div>
    );
}

export default ProductsPage;