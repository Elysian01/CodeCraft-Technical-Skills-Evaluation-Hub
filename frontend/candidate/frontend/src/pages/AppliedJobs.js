import React, { useEffect, useState } from "react";
import Navbar from "../components/header/Navbar";
import PageHeading from "../components/header/PageHeading";
import Table from "../components/tables/Table";
import axios from "axios";

function ViewJobs() {
	const columns = ["Company Name", "Job Role", "Job Description", "Requirements", "Status"];
	
	const [tableData, setTableData] = useState([]);
	useEffect(() => {
		const fetchData = async () => {
			try {
				const id = parseInt(localStorage.getItem("candidateId"))
				const response = await axios.get(`http://localhost:8082/candidate/appliedJobs/${id}`);
				setTableData(response.data); // Assuming response.data is an array of data for the table
			} catch (error) {
				console.error('Error fetching data:', error);
			}
		};

		fetchData();
	}, []);

	return (
		<div>
			<Navbar />
			<PageHeading title="Applied Jobs" />
			<Table 
				columns={columns}
				data={tableData.map(row => ({
					"Company Name": row.jobName,
					"Job Role": row.roleType,
					"Job Description": row.jobDescription,
					"Status": row.status
				}))}
			/> 
		</div>
	);
}

export default ViewJobs;
