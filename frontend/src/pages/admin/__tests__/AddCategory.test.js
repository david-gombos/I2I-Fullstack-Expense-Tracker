import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import AddCategory from '../AddCategory';
import AdminService from '../../../services/AdminService';
import { BrowserRouter as Router } from 'react-router-dom';

jest.mock('../../../services/AdminService');

describe('AddCategory Component', () => {
  const setup = () => {
    render(
      <Router>
        <AddCategory />
      </Router>
    );
  };

  it('should render the AddCategory form', () => {
    setup();
    expect(screen.getByText(/Add New Category/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Category Name/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Transaction Type/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /Submit/i })).toBeInTheDocument();
  });

  it('should display validation errors when form is empty', async () => {
    setup();
    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));
    await waitFor(() => {
      expect(screen.getByText(/Category name is required/i)).toBeInTheDocument();
      expect(screen.getByText(/Transaction type is required/i)).toBeInTheDocument();
    });
  });

  it('should call AdminService.addCategory on successful form submission', async () => {
    AdminService.addCategory.mockResolvedValueOnce({
      data: {
        status: 'SUCCESS',
        response: 'Category added successfully!',
      },
    });

    setup();
    fireEvent.change(screen.getByLabelText(/Category Name/i), { target: { value: 'New Category' } });
    fireEvent.change(screen.getByLabelText(/Transaction Type/i), { target: { value: 'Expense' } });
    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    await waitFor(() => {
      expect(AdminService.addCategory).toHaveBeenCalledWith({
        categoryName: 'New Category',
        transactionType: 'Expense',
      });
      expect(screen.getByText(/Category added successfully!/i)).toBeInTheDocument();
    });
  });

  it('should display an error message on failed form submission', async () => {
    AdminService.addCategory.mockRejectedValueOnce({
      response: {
        data: {
          status: 'FAILED',
          response: 'Failed to add category. Try again later!',
        },
      },
    });

    setup();
    fireEvent.change(screen.getByLabelText(/Category Name/i), { target: { value: 'New Category' } });
    fireEvent.change(screen.getByLabelText(/Transaction Type/i), { target: { value: 'Expense' } });
    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    await waitFor(() => {
      expect(screen.getByText(/Failed to add category. Try again later!/i)).toBeInTheDocument();
    });
  });
});
