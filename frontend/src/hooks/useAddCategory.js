import { useState, useRef, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import AdminService from '../services/AdminService';
import { toast } from 'react-toastify';

/**
 * Custom hook to manage form state and submission for adding a new category.
 * It handles input validation, submission via AdminService.addCategory(),
 * and maintains error and success states.
 */
function useAddCategory() {
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [error, setError] = useState(null);
    const { register, handleSubmit, formState, reset } = useForm();

    const onSubmit = async (data) => {
        setIsSubmitting(true);
        setError(null);
        
        try {
            const response = await AdminService.addCategory(data);
            if (response.data.status === 'SUCCESS') {
                toast.success('Category added successfully!');
                reset();
            } else {
                setError(response.data.message || 'Failed to add category');
            }
        } catch (error) {
            setError(error.response?.data?.message || 'Failed to add category');
        }

        setIsSubmitting(false);
    };

    return {
        register,
        handleSubmit: handleSubmit(onSubmit),
        formState,
        isSubmitting,
        error,
    };
}

export default useAddCategory;
