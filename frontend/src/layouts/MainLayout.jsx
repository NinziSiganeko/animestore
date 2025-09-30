import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import { NavLink, useNavigate, Outlet } from "react-router-dom";
import { useCart } from "../context/CartContext";
import { useState, useEffect } from "react";

function MainLayout() {
    const { cart } = useCart();
    const cartCount = cart.reduce((sum, item) => sum + item.qty, 0);
    const navigate = useNavigate();

    const [userName, setUserName] = useState(null);

    // Check if user is logged in on mount
    useEffect(() => {
        const name = localStorage.getItem("userName");
        setUserName(name); // this will now show the actual name
    }, []);


    const isUser = !!localStorage.getItem("userToken");

    const handleLogout = () => {
        localStorage.removeItem("userToken");
        localStorage.removeItem("userName");
        setUserName(null);
        navigate("/signin");
    };

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
                                        `nav-link position-relative ${isActive ? "text-primary fw-bold" : ""}`
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

                            {/* User not logged in */}
                            {!isUser && (
                                <>
                                    <li className="nav-item mx-2">
                                        <NavLink to="/signin" className="btn btn-outline-primary btn-sm">
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
                    <span className="nav-link fw-bold text-primary">
                      <i className="bi bi-person-circle me-1"></i> Hi, {userName}
                    </span>
                                    </li>
                                    <li className="nav-item mx-2">
                                        <button className="btn btn-danger btn-sm" onClick={handleLogout}>
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


