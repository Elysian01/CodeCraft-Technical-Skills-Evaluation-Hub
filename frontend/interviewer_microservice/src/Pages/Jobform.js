import React, { useState } from 'react';
import Navbar from '../Components/Navbar';
import { SendJob } from '../API/APIs';

function Jobform() {
  const [jobRole, setJobRole] = useState('');
  const [jobDescription, setJobDescription] = useState('');
  const [techStack, setTechStack] = useState([]);
  const [experience, setExperience] = useState(1); // Default value of experience
  const [company, setCompany] = useState('');
  const [companyId, setCompanyId] = useState('');

  const handleTechStackChange = (e) => {
    const tech = e.target.value;
    if (techStack.includes(tech)) {
      setTechStack(techStack.filter(item => item !== tech));
    } else {
      setTechStack([...techStack, tech]);
    }
  };

  
  // Function to create DTO object from form data
  const createJobDTO = (jobRole, jobDescription, techStack, experience, company, companyId) => {
    return {
      jobRole: jobRole,
      jobDescription: jobDescription,
      techStack: techStack,
      experience: experience,
      company: company,
      companyId: companyId
    };
  };
  
  // Use the createJobDTO function inside handleSubmit
  const handleSubmit = (e) => {
    e.preventDefault();
    // Create DTO object
    const uploadData = createJobDTO(jobRole, jobDescription, techStack, experience, company, companyId);
    // Send DTO object
    SendJob(uploadData);
  };
  return (
    <div>
      <Navbar/>
    <div className="flex justify-center min-h-screen bg-gray-800 py-1">
      <div className="w-2/3 p-2">
        <form onSubmit={handleSubmit} className="bg-gray-900 rounded shadow-lg border border-gray-600 p-4 mb-6">
          <div className="mb-4">
            <label htmlFor="jobRole" className="block text-sm text-white">Job Role:</label>
            <select
              id="jobRole"
              value={jobRole}
              onChange={(e) => setJobRole(e.target.value)}
              className="block py-2.5 px-4 w-full text-sm text-white bg-transparent border-0 border-b-2 border-white focus:outline-none"
              required
            >
              <option value="">Select Job Role</option>
              <option value="Frontend Developer">Frontend Developer</option>
              <option value="Backend Developer">Backend Developer</option>
              <option value="Full-stack Developer">Full-stack Developer</option>
              <option value="UI/UX Designer">UI/UX Designer</option>
              {/* Add more options as needed */}
            </select>
          </div>
          <div className="mb-4">
            <label htmlFor="jobDescription" className="block text-sm text-white">Job Description:</label>
            <textarea id="jobDescription" value={jobDescription} onChange={(e) => setJobDescription(e.target.value)} className="block py-2.5 px-4 w-full h-20 text-sm text-white bg-transparent border-0 border-b-2 border-white focus:outline-none resize-none" placeholder="Job description" required />

          </div>
          <div className="mb-4">
            <label htmlFor="techStack" className="block text-sm text-white">Tech Stack Requirements:</label>
            <div className="flex flex-wrap">
              {["HTML", "CSS", "JavaScript", "React", "Node.js", "MongoDB"].map(tech => (
                <div key={tech} className="mr-2 mb-2">
                  <input
                    type="checkbox"
                    id={tech}
                    value={tech}
                    checked={techStack.includes(tech)}
                    onChange={handleTechStackChange}
                    className="hidden"
                  />
                  <label
                    htmlFor={tech}
                    className={`cursor-pointer inline-flex items-center px-3 py-1 rounded-full text-sm font-medium ${
                      techStack.includes(tech) ? 'bg-blue-500 text-white' : 'bg-gray-600 text-gray-200'
                    }`}
                  >
                    {tech}
                  </label>
                </div>
              ))}
            </div>
          </div>
          <div className="mb-4">
            <label htmlFor="experience" className="block text-sm text-white">Experience: {experience} years</label>
            <input
              type="range"
              id="experience"
              min={1}
              max={10}
              value={experience}
              onChange={(e) => setExperience(e.target.value)}
              className="w-full"
            />
          </div>
          <div className="mb-4">
            <label htmlFor="company" className="block text-sm text-white">Company:</label>
            <input
              type="text"
              id="company"
              value={company}
              onChange={(e) => setCompany(e.target.value)}
              className="block py-2.5 px-4 w-full text-sm text-white bg-transparent border-0 border-b-2 border-white focus:outline-none"
              placeholder="Company"
              required
            />
          </div>
          <div className="mb-4">
            <label htmlFor="companyId" className="block text-sm text-white">Company ID:</label>
            <input
              type="text"
              id="companyId"
              value={companyId}
              onChange={(e) => setCompanyId(e.target.value)}
              className="block py-2.5 px-4 w-full text-sm text-white bg-transparent border-0 border-b-2 border-white focus:outline-none"
              placeholder="Company ID"
              required
            />
          </div>
          <button
            type="submit"
            className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm  px-4 py-2.5"
          >
            Proceed to Select Questions
          </button>
        </form>
      </div>
    </div>
    </div>
  );
}

export default Jobform;
