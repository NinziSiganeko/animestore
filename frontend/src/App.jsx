import { Routes, Route, Navigate } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import Home from "./pages/Home";
import Catalog from "./pages/Catalog";
import Cart from "./pages/Cart";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import Checkout from "./pages/Checkout";
import Success from "./pages/Success";
import Designs from "./pages/Designs";
import ProductDetail from "./pages/ProductDetail";
import UserDashboard from "./pages/UserDashboard";
import AdminDashboard from "./pages/adminDashboard";

// PrivateRoute for logged-in customers
function PrivateRoute({ children }) {
    const token = localStorage.getItem("userToken");
    return token ? children : <Navigate to="/signin" />;
}

// Optional: AdminRoute if you implement roles later
function AdminRoute({ children }) {
    const token = localStorage.getItem("userToken");
    const role = localStorage.getItem("userRole"); // you can save role on signin
    return token && role === "Admin" ? children : <Navigate to="/signin" />;
}

function App() {
    return (
        <Routes>
            <Route path="/" element={<MainLayout />}>
                <Route index element={<Home />} />
                <Route path="catalog" element={<Catalog />} />
                <Route path="cart" element={<Cart />} />
                <Route path="signin" element={<SignIn />} />
                <Route path="signup" element={<SignUp />} />
                <Route path="checkout" element={<Checkout />} />
                <Route path="success" element={<Success />} />
                <Route path="designs" element={<Designs />} />
                <Route path="product/:id" element={<ProductDetail />} />

                {/* Protected route for logged-in customers */}
                <Route
                    path="dashboard"
                    element={
                        <PrivateRoute>
                            <UserDashboard />
                        </PrivateRoute>
                    }
                />

                {/* Protected route for Admin only */}
                <Route
                    path="admin/dashboard"
                    element={
                        <AdminRoute>
                            <AdminDashboard />
                        </AdminRoute>
                    }
                />
            </Route>

            {/* Redirect unknown routes to Home */}
            <Route path="*" element={<Navigate to="/" />} />
        </Routes>
    );
}

export default App;



