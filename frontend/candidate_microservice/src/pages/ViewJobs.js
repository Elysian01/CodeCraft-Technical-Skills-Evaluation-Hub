import React from "react";
import Navbar from "../components/header/Navbar";
import PageHeading from "../components/header/PageHeading";
import Table from "../components/tables/Table";

function ViewJobs() {
	const columns = ["Job Title", "Job Description", "Requirements", "Apply"];
	const tableData = [
		{
			"Job Title": "Software Developer",
			"Job Description": "Develop software applications",
			Requirements: ["python", "java", "scala", "css"].join(", "),
			Apply: "Apply",
		},
		{
			"Job Title": "Software Developer",
			"Job Description": "Develop software applications",
			Requirements: ["python", "java", "scala", "css"].join(", "),
			Apply: "Apply",
		},
	];
	return (
		<div>
			<Navbar />
			<PageHeading title="Available Jobs" />
			{/* <Table columns={columns} data={tableData} /> */}
		</div>
	);
}

export default ViewJobs;
