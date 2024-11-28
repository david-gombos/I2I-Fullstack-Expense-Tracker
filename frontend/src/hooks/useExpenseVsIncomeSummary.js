import { useEffect, useState } from "react";
import UserService from "../services/userService";
import AuthService from "../services/auth.service";

function useExpenseVsIncomeSummary(startDate, endDate) {
    const [data, setData] = useState([]);
    const [isError, setIsError] = useState(false);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const getData = async () => {
            const response = await UserService.getSummaryByDateRange(AuthService.getCurrentUser().id, startDate, endDate).then(
                (response) => {
                    if (response.data.status === "SUCCESS") {
                        setData(response.data.response);
                    }
                },
                (error) => {
                    setIsError(true);
                }
            );
            setIsLoading(false);
        };

        getData();
    }, [startDate, endDate]);

    return [data, isLoading, isError];
}

export default useExpenseVsIncomeSummary;