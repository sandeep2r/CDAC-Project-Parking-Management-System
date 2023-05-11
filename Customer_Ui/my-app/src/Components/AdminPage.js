import React from 'react'
import { Link } from 'react-router-dom';


function AdminPage() {

    return (
        <><div className="p-3" style={{
            width: "200px",
            height: "750px",
            display: "flex",
            flexDirection: "column",
            flexShrink: 0,
            padding: "1rem",
            color: "#fff",
            backgroundColor: "#212529"
        }}>
            <ul className="list-unstyled" style={{ margin: 0 }}>
                <li style={{ marginBottom: "5rem" }}>
                    <Link to="/list" style={{ textDecoration: "none", color: "inherit" }}>
                        <b>Profile</b>
                    </Link>
                </li>
                <li style={{ marginBottom: "5rem" }}>
                    <Link to="/dashboard" style={{ textDecoration: "none", color: "inherit" }}>
                        <b>Dashboard</b>
                    </Link>
                </li>
                <li style={{ marginBottom: "5rem" }}>
                    <Link to="/reports" style={{ textDecoration: "none", color: "inherit" }}>
                        <b>Reports</b>
                    </Link>
                </li>
                <li style={{ marginBottom: "5rem" }}>
                    <Link to="/agent/list" style={{ textDecoration: "none", color: "inherit" }}>
                        <b>Manage Agent</b>
                    </Link>
                </li>
                <li style={{ marginBottom: "5rem" }}>
                    <Link to="/slots" style={{ textDecoration: "none", color: "inherit" }}>
                        <b>Manage Slots</b>
                    </Link>
                </li>
                <li style={{ marginBottom: "5rem" }}>
                    <Link to="/vehicletype/list" style={{ textDecoration: "none", color: "inherit" }}>
                        <b>Manage Rates</b>
                    </Link>
                </li>
                <li style={{ marginBottom: "5rem" }}>
                    <Link to="/availableslot" style={{ textDecoration: "none", color: "inherit" }}>
                        <b>Available Slots</b>
                    </Link>
                </li>
                <li>
                    <button className="btn btn-success w-100" style={{ borderRadius: "0.25rem" }}>
                        <b>Logout</b>
                    </button>
                </li>
            </ul>
        </div>


        </>
    );
}

export default AdminPage