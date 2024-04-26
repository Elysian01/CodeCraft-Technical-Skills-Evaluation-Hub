import React from 'react';

const JobListings = () => {
  // Static data
  const jobs = [
    { name: 'Software Developer', status: 'Active', enrollments: 25 },
    { name: 'Graphic Designer', status: 'Inactive', enrollments: 15 },
    { name: 'Data Analyst', status: 'Active', enrollments: 30 },
  ];


  const getStatusButton = (status) => {
    return status === 'Active' ? (
      <button className="text-xs bg-green-200 text-green-800 rounded-full px-2 py-1">
        Active
      </button>
    ) : (
      <button className="text-xs bg-red-200 text-red-800 rounded-full px-2 py-1">
        Inactive
      </button>
    );
  };

  return (
    <div className="overflow-x-auto bg-slate-800 h-screen px-20 py-10">
      <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead className="text-xs uppercase bg-gray-100 dark:bg-gray-700 dark:text-gray-300">
          <tr>
            <th scope="col" className="px-6 py-3">
              Job Name
            </th>
            <th scope="col" className="px-6 py-3">
              View Status
            </th>
            <th scope="col" className="px-6 py-3">
              Enrollments
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
                {job.name}
              </td>
              <td className="px-6 py-4">
                {getStatusButton(job.status)}
              </td>
              <td className="px-6 py-4">{job.enrollments}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default JobListings;
