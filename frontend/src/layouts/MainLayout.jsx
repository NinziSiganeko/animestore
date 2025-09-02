import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import { NavLink, Outlet } from "react-router-dom";
import { useCart } from "../context/CartContext"; //
// 👈 import cart hook

function MainLayout() {
    const { cart } = useCart();
    const cartCount = cart.reduce((sum, item) => sum + item.qty, 0);

    return (
        <div>
            {/* Navbar */}
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm sticky-top">
                <div className="container">
                    <NavLink className="navbar-brand fw-bold text-warning" to="/">
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
                                        `nav-link ${isActive ? "text-warning fw-bold" : ""}`
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
                                        `nav-link ${isActive ? "text-warning fw-bold" : ""}`
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
                                        `nav-link position-relative ${isActive ? "text-warning fw-bold" : ""}`
                                    }
                                >
                                    <i className="bi bi-cart me-1"></i> Cart
                                    {cartCount > 0 && (
                                        <span
                                            className="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-warning text-dark"
                                            style={{ fontSize: "0.75rem" }}
                                        >
                                            {cartCount}
                                        </span>
                                    )}
                                </NavLink>
                            </li>

                            {/* Sign In */}
                            <li className="nav-item mx-2">
                                <NavLink
                                    to="/signin"
                                    className={({ isActive }) =>
                                        `btn btn-outline-light btn-sm ${isActive ? "border-warning text-warning" : ""}`
                                    }
                                >
                                    <i className="bi bi-box-arrow-in-right me-1"></i> Sign In
                                </NavLink>
                            </li>

                            {/* Sign Up */}
                            <li className="nav-item mx-2">
                                <NavLink
                                    to="/signup"
                                    className={({ isActive }) =>
                                        `btn btn-warning btn-sm ${isActive ? "fw-bold shadow" : ""}`
                                    }
                                >
                                    <i className="bi bi-person-plus me-1"></i> Sign Up
                                </NavLink>
                            </li>
                            <li className="nav-item mx-2">
                                <NavLink
                                    to="admin/dashboard"
                                    className={({ isActive }) => `nav-link ${isActive ? "text-warning fw-bold" : ""}`}
                                >
                                    <i className="bi bi-person-circle me-1"></i> Dashboard
                                </NavLink>
                            </li>


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
