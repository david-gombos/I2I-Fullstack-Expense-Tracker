import React, { useState, useRef, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import AdminService from '../../services/adminService';
import { toast, Toaster } from 'react-hot-toast';

function AddCategory() {
    const { register, handleSubmit, watch, reset, formState } = useForm();
    const [isSaving, setIsSaving] = useState(false);
    const navigate = useNavigate();

    const onSubmit = async (data) => {
        setIsSaving(true);
        await AdminService.addCategory(data.categoryName).then(
            (response) => {
                if (response.data.status === 'SUCCESS') {
                    toast.success("Category added successfully!");
                    navigate("/admin/categories");
                } else {
                    toast.error("Failed to add category: Try again later!");
                }
            },
            (error) => {
                if (error.response) {
                    toast.error(error.response.data.response);
                } else {
                    toast.error("Failed to add category: Try again later!");
                }
            }
        );
        setIsSaving(false);
    }

    const cancelProcess = (e) => {
        e.preventDefault();
        navigate("/admin/categories");
    }

    return (
        <div className='container'>
            <Toaster />
            <form className="auth-form t-form" onSubmit={handleSubmit(onSubmit)}>
                <div className='input-box'>
                    <label>Category Name</label><br />
                    <input
                        type='text'
                        {...register('categoryName', {
                            required: "Category name is required!"
                        })}
                    />
                    {formState.errors.categoryName && <small>{formState.errors.categoryName.message}</small>}
                </div>

                <div className='t-btn input-box'>
                    <input type='submit' value={isSaving ? "Saving..." : 'Add Category'}
                        className={isSaving ? "button button-fill loading" : "button button-fill"} />
                    <input type='button' className='button outline' value='Cancel' onClick={(e) => cancelProcess(e)} />
                </div>
            </form>
        </div>
    )
}

export default AddCategory;
