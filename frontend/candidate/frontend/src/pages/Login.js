import React from "react";
import "../static/css/Login.css";
import Navbar from "../components/header/Navbar";
import PageHeading from "../components/header/PageHeading";

import { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputField from "../components/inputs/InputField";
import LoginBG from "../components/misc/LoginBG";
import axios from "axios";

function Login() {
	const navigate = useNavigate();
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [error, setError] = useState("");

	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
<<<<<<<< HEAD:CandidateFrontend/src/pages/Login.js
			await console.log("api call to localhost:8000");
			const response = await axios.post(
				"http://localhost:8000/candidate/login",
				{ email, password }
			);
========
			const response = await axios.post("http://localhost:8082/candidate/login", { email, password });
>>>>>>>> 4b974221d4dcc15b66d6d09a3f2088e8ebfae046:frontend/candidate/frontend/src/pages/Login.js
			console.log(response.data);
			// If the login is successful, navigate to the dashboard
			if (response.status === 200) {
				const id = parseInt(response.data); // Adjust this according to your API response structure
				localStorage.setItem("candidateId", id);
<<<<<<<< HEAD:CandidateFrontend/src/pages/Login.js
				navigate("/candidate-dashboard");
				console.log("Received id: " + id);
				console.log(
					"Local Storage id: " +
						localStorage.getItem("candidateId")
				);
			} else {
				setError("Invalid login credentials");
========
				navigate('/candidate-dashboard');
				console.log("Received id: " + id);
				console.log("Local Storage id: " + localStorage.getItem("candidateId"));
			} else {
				setError('Invalid login credentials');
>>>>>>>> 4b974221d4dcc15b66d6d09a3f2088e8ebfae046:frontend/candidate/frontend/src/pages/Login.js
			}
		} catch (err) {
			setError("Invalid email or password or exception"); // Set error message for invalid credentials
		}
	};

	return (
<<<<<<<< HEAD:CandidateFrontend/src/pages/Login.js
		<div className="mainContainer">
			{/* <div className="nav-heading">
				<span>CodeCraft Candidate App</span>
			</div> */}
			{/* <PageHeading title="Login" /> */}
========
		<div>
			<div className="nav-heading">
						<span>
							CodeCraft Candidate App
						</span>
			</div>
			<PageHeading title="Login" />
>>>>>>>> 4b974221d4dcc15b66d6d09a3f2088e8ebfae046:frontend/candidate/frontend/src/pages/Login.js
			<div className="login">
				{/* <LoginBG /> */}
				<div className="content">
					<div className="greetings">
						<h1 className="greetings-1">Welcome to</h1>
						<h1 className="greetings-2">CodeCraft</h1>
					</div>
					<div className="subtext">
						<h3>Please Enter Below Details</h3>
					</div>
					<form onSubmit={handleSubmit}>
						<InputField
							type="text"
							placeholder="Enter Username"
							value={email}
							onChange={(e) => setEmail(e.target.value)}
							required
						/>
						<InputField
							type="password"
							placeholder="Enter Password"
							value={password}
							onChange={(e) => setPassword(e.target.value)}
							required
						/>
						<br />
<<<<<<<< HEAD:CandidateFrontend/src/pages/Login.js
						{error && (
							<div className="error-message">{error}</div>
						)}
========
						{error && <div className="error-message">{error}</div>}
>>>>>>>> 4b974221d4dcc15b66d6d09a3f2088e8ebfae046:frontend/candidate/frontend/src/pages/Login.js
						<div className="login-submit">
							<button type="submit" className="login-btn">
								Login
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	);
}

export default Login;
