import { useState } from "react";
import IncomeVsExpenseChart from "../../components/userDashboard/incomeVsExpenseChart";
import Header from "../../components/utils/header";
import Loading from '../../components/utils/loading';
import useExpenseVsIncomeSummary from '../../hooks/useExpenseVsIncomeSummary';
import Info from "../../components/utils/Info";
import Container from "../../components/utils/Container";
import toast, { Toaster } from "react-hot-toast";

function UserStatistics() {
    const [startDate, setStartDate] = useState(new Date().toISOString().split('T')[0]);
    const [endDate, setEndDate] = useState(new Date().toISOString().split('T')[0]);
    const [data, isLoading, isError] = useExpenseVsIncomeSummary(startDate, endDate);

    return (
        <Container activeNavId={9}>
            <Header title="Statistics" />
            <Toaster />
            <div>
                <label>Start Date: </label>
                <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
                <label>End Date: </label>
                <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
            </div>
            {(isLoading) && <Loading />}
            {(isError) && toast.error("Failed to fetch information. Try again later!")}
            {(isError) && <Info text="No data found!" />}
            {(!isError) && <IncomeVsExpenseChart data={data} />}
        </Container>
    );
}

export default UserStatistics;