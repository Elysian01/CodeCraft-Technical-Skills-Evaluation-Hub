import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const ViewEnrollments = () => {
  const location = useLocation();
  const { id } = location.state || {};
  const [jobs, setJobs] = useState([]); // Corrected useState initialization

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8081/interviewer/jobEnrollments/${id}`);
        setJobs(response.data);
      } catch (error) {
        console.error('Error getting data:', error);
      }
    };

    fetchData(); // Call fetchData when component mounts
  }, [id]); // Corrected dependency array to run when `id` changes

  return (
    <div className="overflow-x-auto bg-slate-800 h-screen px-20 py-10">
      <h1>{id}</h1>
      <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead className="text-xs uppercase bg-gray-100 dark:bg-gray-700 dark:text-gray-300">
          <tr>
            <th scope="col" className="px-6 py-3">
              Candidate Name
            </th>
            <th scope="col" className="px-6 py-3">
              OA Score
            </th>
            <th scope="col" className="px-6 py-3">
              Job ID
            </th>
            <th scope="col" className="px-6 py-3">
              Action
            </th>
          </tr>
        </thead>
        <tbody>
          {jobs.map((job, index) => (
            <tr
              key={index}
              className={`${index % 2 === 0 ? 'bg-gray-50' : 'bg-gray-100'} dark:bg-gray-800 dark:border-gray-700`}
            >
              <td className="px-6 py-4 font-medium whitespace-nowrap dark:text-white">
                {job.candidateName} {/* Correct property names */}
              </td>
              <td className="px-6 py-4">
                {job.testScore} {/* Correct property names */}
              </td>
              <td className="px-6 py-4">
                {job.jobId} {/* Correct property names */}
              </td>
              <td className="px-6 py-4">
                <button className="text-xs bg-blue-200 text-blue-800 rounded-full px-2 py-1">
                  Schedule Interview
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewEnrollments;
