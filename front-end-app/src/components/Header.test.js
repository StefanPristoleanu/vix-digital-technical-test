import React from 'react';
import { render } from '@testing-library/react';
import Header from './Header';

test('renders the Header component', () => {
  const { getByTestId } = render(<Header />);
	expect(getByTestId("header-component")).toBeTruthy();
});
