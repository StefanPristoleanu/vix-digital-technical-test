import React from 'react';
import { render } from '@testing-library/react';
import ServiceManager from './ServiceManager';

describe('ServiceManager page', () => {

    //TODO nock the backend APIs setup

    it('Should render the main components of the ServiceManager', () => {
        const { getByTestId } = render(<ServiceManager />);
        //assert against the data-testid of the main components: header, add service panel, and specific service card
        //assert that getAllServices is called on render
    })

    describe('Popup modal', () => {

        it('Should render the modal buttons and fields', () => {

        })
        it('Should open the modal when clicking the add button with the appropriate props', () => {

        })


        it('Should call addNewService when the save service button is pressed', () => {
        
        })
        
        it('Should open the modal when clicking the update button with the appropriate props', () => {
            
        })
        

        it('Should call updateService when the save service button is pressed, WITH PREFILLED DATA', () => {
        
        })
        
    });

    
    it('Should render a service card with update and delete buttons', () => {
        
    })

    it('Should call deleteService when the delete button is pressed', () => {
        
    })
});
