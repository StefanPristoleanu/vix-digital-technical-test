import React from 'react';
import { render } from '@testing-library/react';
import Header from './Header';

describe('Renders the Header component', () => {

  it('Should renders the Header component', () => {
    const { getByTestId } = render(<Header />);
    expect(getByTestId("header-component")).toBeTruthy();
  })

  it('Should render the link and go to the correct url when it is clicked', () => {
    
  }) 

});
