import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import { NavLink, useNavigate, Outlet, useLocation } from "react-router-dom";
import { useCart } from "../context/CartContext";
import { useState, useEffect } from "react";

function MainLayout() {
    const { cart } = useCart();
    const cartCount = cart.reduce((sum, item) => sum + item.qty, 0);
    const navigate = useNavigate();
    const location = useLocation(); // <-- used to refresh header state on route change

    // keep userName / userRole in state so we can force re-render on logout + initial load
    const [userName, setUserName] = useState(localStorage.getItem("userName"));
    const [userRole, setUserRole] = useState(localStorage.getItem("userRole"));
    const isUser = !!localStorage.getItem("userToken");

    const handleLogout = () => {
        localStorage.removeItem("userToken");
        localStorage.removeItem("userName");
        localStorage.removeItem("userRole");
        setUserName(null);
        setUserRole(null);
        navigate("/signin");
    };

    // 🔧 Keep navbar state in sync after navigation (e.g. after signin)
    useEffect(() => {
        setUserName(localStorage.getItem("userName"));
        setUserRole(localStorage.getItem("userRole"));
    }, [location]);

    // 🔧 Use case-insensitive role check so "ADMIN", "Admin" or "admin" all work
    const isAdmin = !!userRole && userRole.toString().toLowerCase() === "admin";

    // Decide where to redirect when clicking "Hi, user"
    const dashboardPath = isAdmin ? "/admin/dashboard" : "/dashboard";

    return (
        <div>
            {/* Navbar */}
            <nav className="navbar navbar-expand-lg navbar-white bg-white shadow-sm sticky-top">
                <div className="container">
                    <NavLink className="navbar-brand fw-bold text-primary" to="/">
                        <i className="bi bi-fire me-1"></i> AnimeWear
                    </NavLink>

                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarNav"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav ms-auto align-items-lg-center">
                            {/* Home */}
                            <li className="nav-item mx-2">
                                <NavLink
                                    to="/"
                                    end
                                    className={({ isActive }) =>
                                        `nav-link ${isActive ? "text-primary fw-bold" : ""}`
                                    }
                                >
                                    <i className="bi bi-house-door me-1"></i> Home
                                </NavLink>
                            </li>

                            {/* Catalog */}
                            <li className="nav-item mx-2">
                                <NavLink
                                    to="/catalog"
                                    className={({ isActive }) =>
                                        `nav-link ${isActive ? "text-primary fw-bold" : ""}`
                                    }
                                >
                                    <i className="bi bi-bag me-1"></i> Catalog
                                </NavLink>
                            </li>

                            {/* Cart */}
                            <li className="nav-item mx-2">
                                <NavLink
                                    to="/cart"
                                    className={({ isActive }) =>
                                        `nav-link position-relative ${
                                            isActive ? "text-primary fw-bold" : ""
                                        }`
                                    }
                                >
                                    <i className="bi bi-cart me-1"></i> Cart
                                    {cartCount > 0 && (
                                        <span
                                            className="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary text-white"
                                            style={{ fontSize: "0.75rem" }}
                                        >
                                            {cartCount}
                                        </span>
                                    )}
                                </NavLink>
                            </li>

                            {/* Admin-only Dashboard button (case-insensitive check) */}
                            {isUser && isAdmin && (
                                <li className="nav-item mx-2">
                                    <NavLink
                                        to="/admin/dashboard"
                                        className={({ isActive }) =>
                                            `nav-link ${isActive ? "text-primary fw-bold" : ""}`
                                        }
                                    >
                                        <i className="bi bi-speedometer2 me-1"></i> Dashboard
                                    </NavLink>
                                </li>
                            )}

                            {/* User not logged in */}
                            {!isUser && (
                                <>
                                    <li className="nav-item mx-2">
                                        <NavLink
                                            to="/signin"
                                            className="btn btn-outline-primary btn-sm"
                                        >
                                            <i className="bi bi-box-arrow-in-right me-1"></i> Sign In
                                        </NavLink>
                                    </li>

                                    <li className="nav-item mx-2">
                                        <NavLink to="/signup" className="btn btn-primary btn-sm">
                                            <i className="bi bi-person-plus me-1"></i> Sign Up
                                        </NavLink>
                                    </li>
                                </>
                            )}

                            {/* User logged in */}
                            {isUser && (
                                <>
                                    <li className="nav-item mx-2">
                                        {/* "Hi, user" is clickable and routes to the correct dashboard */}
                                        <NavLink
                                            to={dashboardPath}
                                            className="btn btn-outline-primary"
                                        >
                                            <i className="bi bi-person-circle me-1"></i> Hi, {userName}
                                        </NavLink>
                                    </li>
                                    <li className="nav-item mx-2">
                                        <button
                                            className="btn btn-danger btn-sm"
                                            onClick={handleLogout}
                                        >
                                            <i className="bi bi-box-arrow-right me-1"></i> Logout
                                        </button>
                                    </li>
                                </>
                            )}
                        </ul>
                    </div>
                </div>
            </nav>

            {/* Page Content */}
            <Outlet />
        </div>
    );
}

export default MainLayout;
