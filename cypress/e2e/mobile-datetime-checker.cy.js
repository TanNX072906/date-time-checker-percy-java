import '@percy/cypress';

describe('Date Time Checker - Mobile View', () => {
    beforeEach(() => {
        // Set viewport to iPhone X size for mobile testing
        cy.viewport('iphone-x');
        // Visit the Spring Boot server root
        cy.visit('/');
    });

    it('should have the correct initial layout on mobile', () => {
        cy.get('.title').should('contain', 'Date Time Checker');
        cy.get('#day').should('be.visible');
        cy.get('#month').should('be.visible');
        cy.get('#year').should('be.visible');
        
        // Take a Percy snapshot specifically for mobile viewport
        cy.percySnapshot('Mobile Layout - Check Date Mode', { widths: [375] });
    });

    it('should show error for invalid format on mobile', () => {
        cy.get('#day').type('abc');
        cy.get('#month').type('12');
        cy.get('#year').type('2024');
        cy.get('#checkBtn').click();
        
        cy.get('#messageModal').should('be.visible');
        cy.get('#modalMessage').should('contain', 'Input data for Day is incorrect format!');
        cy.get('#modalOkBtn').click();
    });

    it('should switch to Day In Month mode and display correctly on mobile', () => {
        cy.get('#switchModeBtn').click();
        cy.get('.title').should('contain', 'Day In Month Checker');
        cy.get('#day').should('not.be.visible');
        
        cy.percySnapshot('Mobile Layout - Day In Month Mode', { widths: [375] });
    });
});
