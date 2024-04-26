import React from 'react';

const ViewEnrollments = () => {
  // Static data
  const jobs = [
    { name: 'Aakash', oaScore: 120, interviewScore: 90 },
    { name: 'Abhishek', oaScore: 100, interviewScore: 85 },
    { name: 'Raghuram', oaScore: 150, interviewScore: 95 },
  ];

  return (
    <div className="overflow-x-auto bg-slate-800 h-screen px-20 py-10">
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
              Average OA Score
            </th>
            <th scope="col" className="px-6 py-3">
              Interview
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
                {job.oaScore}
              </td>
              <td className="px-6 py-4">
                {job.interviewScore}
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
