import React from "react";
import "../static/css/Login.css";
import Navbar from "../components/header/Navbar";
import PageHeading from "../components/header/PageHeading";

import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import InputField from "../components/inputs/InputField";
import LoginBG from "../components/misc/LoginBG";

function Login() {
	const navigate = useNavigate();
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [error, setError] = useState("");

	const handleSubmit = async (e) => {
		e.preventDefault();
		// try {
		// 	const userData = await authApi.login({
		// 		username: email,
		// 		password
		// 	});
		// 	console.log(userData);
		// 	navigate(
		// 		"/candidate-dashboard"
		// 	);
		// } catch (err) {
		// 	setError("Invalid email or password"); // Set error message for invalid credentials
		// }
	};

	return (
		<div>
			<Navbar />
			<PageHeading title="Login" />
			<div className="login">
				<LoginBG />
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
