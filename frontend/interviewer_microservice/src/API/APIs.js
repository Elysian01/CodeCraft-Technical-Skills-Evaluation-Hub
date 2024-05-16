import ApiManager from "./ApiManager";

export const GetJobOpenings = async () => {
		const result = await ApiManager("/get_jobs", {
			method: "GET",
		});
		console.log(result[0]);
		return result.data;
};

export const SendJob = async (uploadData) => {
	const result = await ApiManager("/create_job", {
		method: "POST",
		data:uploadData
	});
	console.log(result[0]);
	return result.data;
};
