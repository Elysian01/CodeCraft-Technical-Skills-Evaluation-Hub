import React, { useEffect, useState } from 'react';
import Card from '../Components/Card';
import { Link } from 'react-router-dom';
import logo from "../Assets/applogo.png"
import Navbar from '../Components/Navbar';
import ActiveJobs from '../Components/ActiveJobs';
import PreviousJobs from '../Components/PreviousJobs';
import { GetJobOpenings } from '../API/APIs';

const Dashboard = () => {
  const [getJobs, setJobs] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await GetJobOpenings();
        console.log("R: after call "+ result)
        setJobs(result);
      } catch (error) {
        console.error("Error fetching job openings:", error);
        // Optionally, you can handle the error state here, e.g., setJobs([]) or display an error message.
      }
    };

    fetchData();
  }, []); // Empty dependency array to ensure useEffect only runs once when the component mounts

  return (
    <div>
      <Navbar/>
      <div className='flex flex-col gap-3 p-3 h-screen overflow-auto bg-gray-800'>
        <div className='flex flex-row flex-wrap items-center justify-between gap-4 px-8 py-2 '>
          <h2 style={{ color: 'white', fontFamily: 'Arial, sans-serif' }}>Dashboard</h2>
          <Link to ="/create-job">
            <button className="bg-blue-500 text-white text-l px-6 py-3 rounded-xl flex-grow-0">Create Job</button>
          </Link>
        </div>
        <div className="flex flex-row flex-wrap gap-4 justify-center ">
          <Card 
            className="hover:shadow-lg w-1/4" 
            heading={"Total active Jobs"} count={100} imageLink={logo}
          />
          <Card 
            className="hover:shadow-lg w-1/4" 
            heading={"Closed postings"} count={100} imageLink={logo} 
          />
          <Card 
            className="hover:shadow-lg w-1/4" 
            heading={"Total Enrollments"} count={100} imageLink={logo}
          />
          <Card 
            className="hover:shadow-lg w-1/4" 
            heading={"Total interviews"} count={100} imageLink={logo}
          />
        </div>
        <div className="flex flex-row flex-wrap gap-2 px-2">
          <ActiveJobs className="p-4  max-w-1/3" jobs={getJobs} />
          <PreviousJobs className="p-4 w-1/3" />
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
