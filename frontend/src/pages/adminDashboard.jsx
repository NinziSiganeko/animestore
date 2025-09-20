import React, { useState, useEffect } from "react";
import { Table, Button, Modal, Form } from "react-bootstrap";
import axios from "axios";

function AdminDashboard() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const [formData, setFormData] = useState({
    name: "",
    price: "",
    stock: "",
    categoryName: "",
    status: "",
    productImage: null,
  });

  // Fetch all products

  const fetchProducts = async () => {
    try {
      const response = await axios.get("http://localhost:8080/products");
      setProducts(response.data);
    } catch (err) {
      console.error(err);
      setError("Error fetching products.");
    }
  };

  // Fetch all categories
  const fetchCategories = async () => {
    try {
      const response = await axios.get("http://localhost:8080/categories");
      setCategories(response.data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchProducts();
    fetchCategories();
  }, []);

  // Convert product image for display
  const displayImage = (product) => {
    if (product.productImage && product.productImage.length > 0) {
      try {
        if (typeof product.productImage === "string") {
          return `data:image/jpeg;base64,${product.productImage}`;
        } else if (
            Array.isArray(product.productImage) ||
            product.productImage instanceof Uint8Array
        ) {
          const bytes = new Uint8Array(product.productImage);
          let binary = "";
          bytes.forEach((byte) => (binary += String.fromCharCode(byte)));
          return `data:image/jpeg;base64,${btoa(binary)}`;
        }
      } catch (error) {
        console.error("Error converting image:", error);
        return null;
      }
    }
    return null;
  };

  // Handle input changes
  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "productImage") {
      setFormData({ ...formData, productImage: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  // Submit product form
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!formData.name || !formData.price || !formData.stock || !formData.categoryName) {
      setError("Please fill in all required fields.");
      return;
    }

    try {
      setLoading(true);
      setError("");

      // Find or create category
      let categoryId;
      const existingCategory = categories.find(
          (cat) => cat.categoryName.toLowerCase() === formData.categoryName.toLowerCase()
      );

      if (existingCategory) {
        categoryId = existingCategory.categoryId;
      } else {
        const categoryResponse = await axios.post("http://localhost:8080/categories", {
          categoryName: formData.categoryName,
        });
        categoryId = categoryResponse.data.categoryId;
        await fetchCategories();
      }

      // Create product with image
      const productFormData = new FormData();
      productFormData.append("name", formData.name);
      productFormData.append("price", formData.price);
      productFormData.append("stock", formData.stock);
      productFormData.append("category_Id", categoryId);

      if (formData.productImage) {
        productFormData.append("productImage", formData.productImage);
      }

      const productResponse = await axios.post(
          "http://localhost:8080/products",
          productFormData,
          { headers: { "Content-Type": "multipart/form-data" } }
      );

      const newProduct = productResponse.data;

      // Create inventory entry
      await axios.post("http://localhost:8080/inventory/create", {
        product: { productId: newProduct.productId },
        status: formData.status,
      });

      // Refresh data
      await fetchProducts();
      setShowModal(false);
      setFormData({
        name: "",
        price: "",
        stock: "",
        categoryName: "",
        status: "IN_STOCK",
        productImage: null,
      });
    } catch (err) {
      console.error(err);
      setError("Failed to save product. " + (err.response?.data?.message || err.message));
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this product?")) return;
    try {
      await axios.delete(`http://localhost:8080/products/${id}`);
      await fetchProducts();
    } catch (err) {
      setError("Failed to delete product.");
    }
  };

  const formatCurrency = (num) => `R${Number(num).toFixed(2)}`;

  return (
      <div className="container mt-4">
        <div className="d-flex justify-content-between align-items-center mb-3">
          <h2>Admin</h2>
          <Button variant="outline-secondary">Logout</Button>
        </div>

        <div className="d-flex justify-content-between align-items-center mb-2">
          <h4>Products / Inventory</h4>
          <Button variant="primary" onClick={() => setShowModal(true)}>
            Add Product
          </Button>
        </div>

        {error && <div className="alert alert-danger">{error}</div>}
        {loading && <p>Loading...</p>}

        <Table bordered responsive>
          <thead>
          <tr>
            <th>Product ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Status</th>
            <th>Product Image</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          {products.length === 0 ? (
              <tr>
                <td colSpan="8" className="text-center">
                  No products found.
                </td>
              </tr>
          ) : (
              products.map((p) => {
                const imageUrl = displayImage(p);
                return (
                    <tr key={p.productId}>
                      <td>{p.productId}</td>
                      <td>{p.name}</td>
                      <td>{p.category?.categoryName || "N/A"}</td>
                      <td>{formatCurrency(p.price)}</td>
                      <td>{p.stock}</td>
                      <td>
                        {p.stock === 0
                            ? "OUT_OF_STOCK"
                            : p.stock < 10
                                ? "FEW_IN_STOCK"
                                : "IN_STOCK"}
                      </td>
                      <td>
                        {imageUrl ? (
                            <img
                                src={imageUrl}
                                alt={p.name}
                                style={{ width: "60px", height: "60px", objectFit: "cover" }}
                            />
                        ) : (
                            "No image"
                        )}
                      </td>
                      <td>
                        <Button variant="secondary" size="sm" className="me-1">
                          Edit
                        </Button>
                        <Button
                            variant="danger"
                            size="sm"
                            onClick={() => handleDelete(p.productId)}
                        >
                          Delete
                        </Button>
                      </td>
                    </tr>
                );
              })
          )}
          </tbody>
        </Table>

        {/* Add Product Modal */}
        <Modal show={showModal} onHide={() => setShowModal(false)}>
          <Modal.Header closeButton>
            <Modal.Title>Add New Product</Modal.Title>
          </Modal.Header>
          <Form onSubmit={handleSubmit} encType="multipart/form-data">
            <Modal.Body>
              {error && <div className="alert alert-danger">{error}</div>}
              <Form.Group className="mb-3">
                <Form.Label>Product Name</Form.Label>
                <Form.Control
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    required
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Price</Form.Label>
                <Form.Control
                    name="price"
                    type="number"
                    step="0.01"
                    value={formData.price}
                    onChange={handleChange}
                    required
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Stock</Form.Label>
                <Form.Control
                    name="stock"
                    type="number"
                    value={formData.stock}
                    onChange={handleChange}
                    required
                />
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Category</Form.Label>
                <Form.Select
                    name="categoryName"
                    value={formData.categoryName}
                    onChange={handleChange}
                    required
                >
                  <option value="">Select a category</option>
                  <option value="Beanie">Beanie</option>
                  <option value="Cap">Cap</option>
                  <option value="Hoodie">Hoodie</option>
                  <option value="Tee">Tee</option>
                </Form.Select>
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Status</Form.Label>
                <Form.Select
                    name="status"
                    value={formData.status}
                    onChange={handleChange}
                    required
                >
                  <option value="">Choose Status</option>
                  <option value="IN_STOCK">IN_STOCK</option>
                  <option value="OUT_OF_STOCK">OUT_OF_STOCK</option>
                  <option value="FEW_IN_STOCK">FEW_IN_STOCK</option>

                </Form.Select>
              </Form.Group>
              <Form.Group className="mb-3">
                <Form.Label>Product Image</Form.Label>
                <Form.Control
                    type="file"
                    name="productImage"
                    accept="image/*"
                    onChange={handleChange}
                />
              </Form.Group>
            </Modal.Body>
            <Modal.Footer>
              <Button
                  variant="secondary"
                  onClick={() => {
                    setShowModal(false);
                    setError("");
                  }}
              >
                Cancel
              </Button>
              <Button variant="primary" type="submit" disabled={loading}>
                {loading ? "Saving..." : "Save Product"}
              </Button>
            </Modal.Footer>
          </Form>
        </Modal>
      </div>
  );
}

export default AdminDashboard;
