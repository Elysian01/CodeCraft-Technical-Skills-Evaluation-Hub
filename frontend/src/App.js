import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "./Pages/Dashboard";
import Login from "./Pages/Login";
import Navbar from "./Components/Navbar";
import Jobform from "./Pages/Jobform.js";
import JobListings from "./Pages/JobListings";
import Enrollmentdetails from "./Pages/EnrollmentDetails.js";
import ViewMorePreviousJob from "./Pages/ViewMorePreviousJob.js";
import ViewMoreOpenJob from "./Pages/ViewMoreOpenJob.js";
import Layout from "./Layout.js";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />} >
          <Route index element={<Login />}/>
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/create-job" element={<Jobform />} />
                <Route path="/create-job" element={<Jobform />} />
                <Route path="/job-listing" element={<JobListings />} />
                <Route path="/dashboard/enrollment" element={<Enrollmentdetails />} />
                <Route path="/view-more-prev" element={<ViewMorePreviousJob/>} />
                <Route path="/view-more-open" element={<ViewMoreOpenJob/>} />
        </Route>

        
      </Routes>
    </BrowserRouter>
  );
}
