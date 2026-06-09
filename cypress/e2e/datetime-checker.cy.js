import '@percy/cypress';

describe('Date Time Checker', () => {
    beforeEach(() => {
        // Visit the Spring Boot server root
        cy.visit('/');
    });

    it('should have the correct initial layout', () => {
        cy.get('.title').should('contain', 'Date Time Checker');
        cy.get('#day').should('be.visible');
        cy.get('#month').should('be.visible');
        cy.get('#year').should('be.visible');
        cy.get('#checkBtn').should('be.visible');
        cy.get('#clearBtn').should('be.visible');
        cy.percySnapshot('Initial Layout');
    });

    it('should show error for invalid format', () => {
        cy.get('#day').type('abc');
        cy.get('#month').type('12');
        cy.get('#year').type('2024');
        cy.get('#checkBtn').click();
        
        cy.get('#messageModal').should('be.visible');
        cy.get('#modalMessage').should('contain', 'Input data for Day is incorrect format!');
        cy.percySnapshot('Invalid Format Error');
        cy.get('#modalOkBtn').click();
    });

    it('should show error for out of range input', () => {
        cy.get('#day').type('32');
        cy.get('#month').type('1');
        cy.get('#year').type('2024');
        cy.get('#checkBtn').click();
        
        cy.get('#messageModal').should('be.visible');
        cy.get('#modalMessage').should('contain', 'Input data for Day is out of range!');
        cy.percySnapshot('Out of Range Error');
        cy.get('#modalOkBtn').click();
    });

    it('should validate logical incorrect date (e.g. 29/2/2023)', () => {
        cy.get('#day').type('29');
        cy.get('#month').type('2');
        cy.get('#year').type('2023');
        cy.get('#checkBtn').click();
        
        cy.get('#messageModal').should('be.visible');
        cy.get('#modalMessage').should('contain', '29/2/2023 is invalid date time!');
        cy.percySnapshot('Invalid Logical Date');
        cy.get('#modalOkBtn').click();
    });

    it('should validate correct date (e.g. 29/2/2024)', () => {
        cy.get('#day').type('29');
        cy.get('#month').type('2');
        cy.get('#year').type('2024');
        cy.get('#checkBtn').click();
        
        cy.get('#messageModal').should('be.visible');
        cy.get('#modalMessage').should('contain', '29/2/2024 is correct date time!');
        cy.percySnapshot('Valid Date');
        cy.get('#modalOkBtn').click();
    });

    it('should clear fields when Clear is clicked', () => {
        cy.get('#day').type('12');
        cy.get('#clearBtn').click();
        cy.get('#day').should('have.value', '');
    });

    it('should show confirm dialog when X is clicked', () => {
        cy.get('#closeBtn').click();
        cy.get('#messageModal').should('be.visible');
        cy.get('#modalMessage').should('contain', 'Are you sure to exit?');
        cy.percySnapshot('Close Confirm Dialog');
        cy.get('#confirmNo').click();
        cy.get('#messageModal').should('not.be.visible');
    });
});
