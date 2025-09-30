import React, { useState, useEffect } from "react";
import { Button, Form, Row, Col, Card } from "react-bootstrap";
import axios from "axios";
import ProductsPage from "./ProductsPage";
import CustomersPage from "./CustomersPage";
import InventoryPage from "./InventoryPage"; // 👈 import inventory page

function AdminDashboard() {
  const [activePage, setActivePage] = useState("dashboard");
  const [categories, setCategories] = useState([]);
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

  // Fetch categories
  const fetchCategories = async () => {
    try {
      const response = await axios.get("http://localhost:8080/categories");
      setCategories(response.data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

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

      await axios.post("http://localhost:8080/products", productFormData, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      // Reset form
      setFormData({
        name: "",
        price: "",
        stock: "",
        categoryName: "",
        status: "",
        productImage: null,
      });
    } catch (err) {
      console.error(err);
      setError("Failed to save product. " + (err.response?.data?.message || err.message));
    } finally {
      setLoading(false);
    }
  };

  return (
      <div className="d-flex">
        {/* Sidebar */}
        <div className="bg-light p-3" style={{ minWidth: "220px", height: "100vh" }}>
          <h5 className="mb-4">ANIME CLOTHING</h5>
          <ul className="list-unstyled">
            <li
                className={`mb-2 fw-bold ${activePage === "dashboard" ? "text-primary" : ""}`}
                onClick={() => setActivePage("dashboard")}
                style={{ cursor: "pointer" }}
            >
              Dashboard
            </li>
            <li
                className={`mb-2 ${activePage === "products" ? "text-primary fw-bold" : ""}`}
                onClick={() => setActivePage("products")}
                style={{ cursor: "pointer" }}
            >
              Products
            </li>
            <li
                className={`mb-2 ${activePage === "customers" ? "text-primary fw-bold" : ""}`}
                onClick={() => setActivePage("customers")}
                style={{ cursor: "pointer" }}
            >
              Customers
            </li>
            <li className="mb-2">Orders</li>
            <li
                className={`mb-2 ${activePage === "inventory" ? "text-primary fw-bold" : ""}`}
                onClick={() => setActivePage("inventory")}
                style={{ cursor: "pointer" }}
            >
              Inventory
            </li>
            <li className="mb-2">Reviews</li>
            <li className="mb-2">Settings</li>
          </ul>
        </div>

        {/* Content Area */}
        <div className="flex-grow-1 p-4">
          {activePage === "dashboard" && (
              <Card className="p-4">
                <h4 className="mb-3">Add New Product</h4>
                {error && <div className="alert alert-danger">{error}</div>}
                <Form onSubmit={handleSubmit} encType="multipart/form-data">
                  {/* Product Name + Price */}
                  <Row>
                    <Col md={6}>
                      <Form.Group className="mb-3">
                        <Form.Label>Product Name</Form.Label>
                        <Form.Control
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />
                      </Form.Group>
                    </Col>
                    <Col md={6}>
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
                    </Col>
                  </Row>

                  {/* Stock + Category */}
                  <Row>
                    <Col md={6}>
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
                    </Col>
                    <Col md={6}>
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
                    </Col>
                  </Row>

                  {/* Status + Image */}
                  <Row>
                    <Col md={6}>
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
                    </Col>
                    <Col md={6}>
                      <Form.Group className="mb-3">
                        <Form.Label>Product Image</Form.Label>
                        <Form.Control
                            type="file"
                            name="productImage"
                            accept="image/*"
                            onChange={handleChange}
                        />
                      </Form.Group>
                    </Col>
                  </Row>

                  <Button variant="primary" type="submit" disabled={loading}>
                    {loading ? "Saving..." : "Save Product"}
                  </Button>
                </Form>
              </Card>
          )}

          {activePage === "products" && <ProductsPage />}
          {activePage === "customers" && <CustomersPage />}
          {activePage === "inventory" && <InventoryPage />}
        </div>
      </div>
  );
}

export default AdminDashboard;
