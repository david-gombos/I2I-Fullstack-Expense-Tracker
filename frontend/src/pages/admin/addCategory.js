import React, { useState, useRef, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { toast, Toaster } from 'react-hot-toast';
import AuthService from '../../services/authService';
import AdminService from '../../services/adminService';
import Container from '../../components/Container';
import Header from '../../components/Header';
import { useNavigate } from 'react-router-dom';
import Loading from '../../components/Loading';
import Info from '../../components/Info';

const AddCategory = () => {
  const { register, handleSubmit, formState, reset } = useForm();
  const [isSubmitting, setIsSubmitting] = useState(false);
  const navigate = useNavigate();

  const validateCategoryDetails = (data) => {
    const errors = [];
    if (!data.categoryName || data.categoryName.trim() === '') {
      errors.push('Category name is required.');
    }
    if (!data.transactionType || data.transactionType.trim() === '0') {
      errors.push('Transaction type is required.');
    }
    if (data.categoryName && data.categoryName.length > 50) {
      errors.push('Category name cannot exceed 50 characters.');
    }
    return errors;
  };

  const showSuccessMessage = (message) => {
    toast.success(message);
  };

  const showErrorMessage = (message) => {
    toast.error(message);
  };

  const handleCategorySubmission = async (data) => {
    setIsSubmitting(true);

    const errors = validateCategoryDetails(data);
    if (errors.length > 0) {
      errors.forEach(showErrorMessage);
      setIsSubmitting(false);
      return;
    }

    try {
      const response = await AdminService.addCategory(data);
      if (response.data.status === 'SUCCESS') {
        showSuccessMessage('Category added successfully');
        reset();
        navigate('/admin/categories');
      } else {
        showErrorMessage(response.data.response);
      }
    } catch (error) {
      if (error.response) {
        showErrorMessage(error.response.data.response);
      } else {
        showErrorMessage('An unexpected error occurred. Please try again later.');
      }
    } finally {
      setIsSubmitting(false);
    }
  };

  const renderAddCategoryForm = () => (
    <form className="auth-form" onSubmit={handleSubmit(handleCategorySubmission)}>
      <div className="input-box">
        <label>Category Name</label><br />
        <input
          type='text'
          {...register('categoryName', { required: 'Category name is required' })}
        />
        {formState.errors.categoryName && <small>{formState.errors.categoryName.message}</small>}
      </div>

      <div className="input-box">
        <label>Transaction Type</label><br />
        <select {...register('transactionType', { required: 'Transaction type is required' })}>
          <option value="0">Select Transaction Type</option>
          <option value="1">Expense</option>
          <option value="2">Income</option>
        </select>
        {formState.errors.transactionType && <small>{formState.errors.transactionType.message}</small>}
      </div>

      <div className='t-btn input-box'>
        <input type='submit' value={isSubmitting ? "Adding..." : 'Add Category'}
          className={isSubmitting ? "button button-fill loading" : "button button-fill"} />
      </div>
    </form>
  );

  return (
    <Container activeNavId={7}>
      <Header title="Add Category" />
      <Toaster />
      {renderAddCategoryForm()}
    </Container>
  );
};

export default AddCategory;
