import { useState } from 'react';
import AdminService from '../../services/adminService';
import Header from '../../components/utils/header';
import Container from '../../components/utils/Container';
import toast, { Toaster } from 'react-hot-toast';

function AddCategory() {
    const [categoryName, setCategoryName] = useState('');
    const [transactionTypeId, setTransactionTypeId] = useState(1); // Default to Expense

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await AdminService.addCategory(categoryName, transactionTypeId);
            if (response.data.status === 'SUCCESS') {
                toast.success(response.data.response);
                setCategoryName('');
                setTransactionTypeId(1);
            }
        } catch (error) {
            toast.error(error.response ? error.response.data.response : "Failed to add category: Try again later!");
        }
    }

    return (
        <Container activeNavId={7}>
            <Header title="Add Category" />
            <Toaster />
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Category Name:</label>
                    <input
                        type="text"
                        value={categoryName}
                        onChange={(e) => setCategoryName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Transaction Type:</label>
                    <select
                        value={transactionTypeId}
                        onChange={(e) => setTransactionTypeId(parseInt(e.target.value))}
                    >
                        <option value={1}>Expense</option>
                        <option value={2}>Income</option>
                    </select>
                </div>
                <button type="submit">Add Category</button>
            </form>
        </Container>
    );
}

export default AddCategory;