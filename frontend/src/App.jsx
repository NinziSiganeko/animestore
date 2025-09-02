import { Routes, Route } from "react-router-dom";
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
import AdminDashboard from "./pages/AdminDashboard";
import UserDashboard from "./pages/UserDashboard";
import OrderSuccess from "./pages/OrderSuccess";



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
                <Route path="/designs" element={<Designs />} />
                <Route path="/product/:id" element={<ProductDetail />} />
                <Route path="/checkout" element={<Checkout />} />
                <Route path="admin/dashboard" element={<AdminDashboard />} />
                <Route path="/dashboard" element={<UserDashboard />} />
                <Route path="/order-success" element={<OrderSuccess />} />

            </Route>
        </Routes>
    );
}

export default App;
