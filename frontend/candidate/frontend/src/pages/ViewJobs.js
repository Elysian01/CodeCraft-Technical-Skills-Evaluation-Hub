import React, { useEffect, useState } from "react";
import Navbar from "../components/header/Navbar";
import PageHeading from "../components/header/PageHeading";
import Table from "../components/tables/Table";
import axios from "axios";

function ViewJobs() {
	const columns = ["Company Name", "Job Role", "Job Description", "Requirements", "Apply"];
	
	function JobApplyDTO(id, company, candidateId) {
		return {
			cid: parseInt(candidateId),
			jid: id,
			company_name: company
		};
	}

	const handleViewClick = async (id, company, candidateId) => {
		try {
			const dto = JobApplyDTO(id, company, candidateId);
			await axios.post("http://localhost:8082/candidate/apply", dto); // Directly sending dto without wrapping in {dto}
			console.log('Job application submitted:', dto);
		} catch (error) {
			console.error('Error applying for job:', error);
		}
	};

	const [tableData, setTableData] = useState([]);

	const renderViewButton = (id, company) => (
		<button onClick={() => handleViewClick(id, company, localStorage.getItem("candidateId"))} className="view-button">
			Apply
		</button>
	);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const response = await axios.get("http://localhost:8081/interviewer/all-active-jobs");
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
			<PageHeading title="Available Jobs" />
			<Table 
				columns={columns}
				data={tableData.map(row => ({
					"Company Name": row.company,
					"Job Role": row.roleType,
					"Job Description": row.jobDescription,
					"Requirements": row.allRequirements.map(req => req.requirementName).join(', '),
					"Apply": renderViewButton(row.id, row.company),
				}))}
			/> 
		</div>
	);
}

export default ViewJobs;
