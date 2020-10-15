import React from 'react';
import { Button, Nav, Navbar, Form, FormControl } from 'react-bootstrap';


export default function Header() {
    return <span>
        <Navbar data-testid="header-component" bg="light" expand="lg">
            <Navbar.Brand href="#home">Service Manager</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
                <Nav.Link href="#home">Home</Nav.Link>
                <Nav.Link href="#link"><a href="http://localhost:8080/history/">View Service Status History</a></Nav.Link>
            </Nav>
            </Navbar.Collapse>
        </Navbar>
    </span>
}