import React, { useState, useEffect } from "react";
import { Table, Button, Modal, Form } from "react-bootstrap";

function adminDashboard() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const [newProduct, setNewProduct] = useState({
    name: "",
    price: "",
    stock: "",
    categoryId: "",
    status: "IN_STOCK",
  });

  const API_BASE = "http://localhost:8080/api/products";
  const CATEGORY_API = "http://localhost:8080/api/categories";

  // Fetch products and categories on load
  useEffect(() => {
    fetchProducts();
    fetchCategories();
  }, []);

  const fetchProducts = async () => {
    try {
      setLoading(true);
      const response = await fetch(API_BASE);
      if (!response.ok) throw new Error("Failed to fetch products");
      const data = await response.json();
      setProducts(data);
    } catch (err) {
      console.error(err);
      setError("Error fetching products.");
    } finally {
      setLoading(false);
    }
  };

  const fetchCategories = async () => {
    try {
      const response = await fetch(CATEGORY_API);
      if (!response.ok) throw new Error("Failed to fetch categories");
      const data = await response.json();
      setCategories(data);
    } catch (err) {
      console.error(err);
      setError("Error fetching categories.");
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewProduct((prev) => ({ ...prev, [name]: value }));
  };

  const handleAddProduct = async (e) => {
    e.preventDefault();
    setError("");

    const { name, price, stock, categoryId, status } = newProduct;
    if (!name || !price || !stock || !categoryId || !status) {
      setError("Please fill in all fields.");
      return;
    }

    try {
      setLoading(true);
      const response = await fetch(`${API_BASE}/add`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          name,
          price: parseFloat(price),
          stock: parseInt(stock, 10),
          categoryId,
          status,
        }),
      });

      if (!response.ok) {
        const errorData = await response.json().catch(() => ({}));
        throw new Error(errorData.message || "Failed to save product");
      }

      // Reload products after adding
      await fetchProducts();
      setShowModal(false);
      setNewProduct({
        name: "",
        price: "",
        stock: "",
        categoryId: "",
        status: "IN_STOCK",
      });
    } catch (err) {
      console.error(err);
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this product?")) return;
    setError("");
    try {
      setLoading(true);
      const response = await fetch(`${API_BASE}/${id}`, {
        method: "DELETE",
      });
      if (!response.ok) throw new Error("Failed to delete product");
      await fetchProducts();
    } catch (err) {
      console.error(err);
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const formatCurrencyR = (num) => `R ${Number(num).toLocaleString("en-ZA")}`;

  return (
      <div className="container mt-5">
        <h2 className="mb-4">👑 Admin Dashboard</h2>

        {error && <div className="alert alert-danger">{error}</div>}

        <Button
            variant="primary"
            className="mb-3"
            onClick={() => setShowModal(true)}
            disabled={loading}
        >
          + Add Product
        </Button>

        {loading && <p>Loading...</p>}

        <Table striped bordered hover responsive>
          <thead>
          <tr>
            <th>Product_ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price (R)</th>
            <th>Stock</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          {products.length === 0 ? (
              <tr>
                <td colSpan="7" className="text-center">
                  No products found.
                </td>
              </tr>
          ) : (
              products.map((p) => (
                  <tr key={p.productId || p.id}>
                    <td>{p.productId || p.id}</td>
                    <td>{p.name}</td>
                    <td>{p.category?.name || "N/A"}</td>
                    <td>{formatCurrencyR(p.price)}</td>
                    <td>{p.stock}</td>
                    <td>{p.status === "IN_STOCK" ? "In Stock" : "Out of Stock"}</td>
                    <td>
                      <Button
                          variant="danger"
                          size="sm"
                          onClick={() => handleDelete(p.productId || p.id)}
                          disabled={loading}
                      >
                        Delete
                      </Button>
                    </td>
                  </tr>
              ))
          )}
          </tbody>
        </Table>

        {/* Modal to add product */}
        <Modal show={showModal} onHide={() => setShowModal(false)}>
          <Modal.Header closeButton>
            <Modal.Title>Add a New Product</Modal.Title>
          </Modal.Header>
          <Form onSubmit={handleAddProduct}>
            <Modal.Body>
              {error && <div className="alert alert-danger">{error}</div>}

              <Form.Group className="mb-3">
                <Form.Label>Product Name</Form.Label>
                <Form.Control
                    name="name"
                    value={newProduct.name}
                    onChange={handleInputChange}
                    required
                    disabled={loading}
                />
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Price (Rands)</Form.Label>
                <Form.Control
                    name="price"
                    type="number"
                    step="0.01"
                    value={newProduct.price}
                    onChange={handleInputChange}
                    required
                    disabled={loading}
                />
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Stock</Form.Label>
                <Form.Control
                    name="stock"
                    type="number"
                    min="0"
                    value={newProduct.stock}
                    onChange={handleInputChange}
                    required
                    disabled={loading}
                />
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Category</Form.Label>
                <Form.Select
                    name="categoryId"
                    value={newProduct.categoryId}
                    onChange={handleInputChange}
                    required
                    disabled={loading}
                >
                  <option value="">-- Select Category --</option>
                  {categories.map((cat) => (
                      <option key={cat.categoryId} value={cat.categoryId}>
                        {cat.name}
                      </option>
                  ))}
                </Form.Select>
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Status</Form.Label>
                <Form.Select
                    name="status"
                    value={newProduct.status}
                    onChange={handleInputChange}
                    required
                    disabled={loading}
                >
                  <option value="IN_STOCK">In Stock</option>
                  <option value="OUT_OF_STOCK">Out of Stock</option>
                </Form.Select>
              </Form.Group>
            </Modal.Body>

            <Modal.Footer>
              <Button
                  variant="secondary"
                  onClick={() => setShowModal(false)}
                  disabled={loading}
              >
                Cancel
              </Button>
              <Button variant="primary" type="submit" disabled={loading}>
                Save Product
              </Button>
            </Modal.Footer>
          </Form>
        </Modal>
      </div>
  );
}

export default adminDashboard;
