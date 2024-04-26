import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const StaticTable = () => {
  // Array containing job data
  const [jobs, setJobs] = useState([
    {
      jobName: "Software Engineer",
      requirements: "5+ years experience, strong knowledge of React and Node.js",
      enrollments: 10
    },
    {
      jobName: "Data Scientist",
      requirements: "Masters in Data Science, Python expertise, experience with ML algorithms",
      enrollments: 7
    },
    {
      jobName: "UX/UI Designer",
      requirements: "2+ years experience in UX/UI design, proficiency in Figma",
      enrollments: 12
    }
  ]);



  const visibleJobs = jobs.slice(0, 3);

  return (
    <div className="relative overflow-x-auto dark:bg-gray-900 rounded-lg">
      <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400 rounded-lg">
        <caption className="text-lg font-semibold text-gray-800 dark:text-gray-200 mb-4">Job Openings</caption>
        <thead className="text-xs text-gray-700 uppercase bg-gray-100 dark:bg-gray-900 dark:text-gray-400 rounded-lg">
          <tr>
            <th scope="col" className="px-6 py-3 rounded-l-lg">
              Job name
            </th>
            <th scope="col" className="px-6 py-3">
              Requirements
            </th>
            <th scope="col" className="px-6 py-3">
              Enrollment
            </th>
            <th scope="col" className="px-6 py-3 rounded-r-lg">
              View
            </th>
          </tr>
        </thead>
        <tbody>
          {visibleJobs.map((job, index) => (
            <React.Fragment key={index}>
              <tr className="bg-white dark:bg-gray-900 rounded-lg">
                <td className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                  {job.jobName}
                </td>
                <td className="px-6 py-4">{job.requirements}</td>
                <td className="px-6 py-4">{job.enrollments}</td>
                <td className="px-6 py-4">
                  <Link to ="enrollment">
                  <button className="text-blue-600 dark:text-blue-400 hover:underline focus:outline-none">View</button>
                  </Link>
                </td>
              </tr>
              <tr className="bg-gray-900">
                <td colSpan="4" className="h-px"></td>
              </tr>
            </React.Fragment>
          ))}
        </tbody>
        <tfoot>
            <tr>
              <td colSpan="4" className="px-6 py-3 text-center">
                <Link to= "/view-more-open" ><button className="text-blue-600 dark:text-blue-400 hover:underline focus:outline-none">View more</button></Link>
              </td>
            </tr>

        </tfoot>
      </table>
    </div>
  );
};

export default StaticTable;
