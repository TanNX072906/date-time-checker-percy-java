// UI Interaction
document.addEventListener('DOMContentLoaded', () => {
    const dayInput = document.getElementById('day');
    const monthInput = document.getElementById('month');
    const yearInput = document.getElementById('year');
    
    const dayGroup = dayInput.parentElement;
    const appTitle = document.getElementById('appTitle');
    const switchModeBtn = document.getElementById('switchModeBtn');

    const clearBtn = document.getElementById('clearBtn');
    const checkBtn = document.getElementById('checkBtn');
    const closeAppBtn = document.getElementById('closeBtn');

    const modal = document.getElementById('messageModal');
    const modalMessage = document.getElementById('modalMessage');
    const modalFooter = document.getElementById('modalFooter');
    const closeModalSpans = document.getElementsByClassName('close-modal');

    let isCheckDateMode = true;

    // Switch mode logic
    switchModeBtn.addEventListener('click', () => {
        isCheckDateMode = !isCheckDateMode;
        if (isCheckDateMode) {
            dayGroup.style.display = 'flex';
            appTitle.innerText = 'Date Time Checker';
            switchModeBtn.innerText = 'Switch to Day In Month';
        } else {
            dayGroup.style.display = 'none';
            appTitle.innerText = 'Day In Month Checker';
            switchModeBtn.innerText = 'Switch to Check Date';
        }
        // clear inputs on switch
        dayInput.value = '';
        monthInput.value = '';
        yearInput.value = '';
    });

    function showModal(message, type = 'info') {
        modalMessage.innerText = message;
        modalFooter.innerHTML = ''; // Clear buttons

        if (type === 'confirm') {
            const yesBtn = document.createElement('button');
            yesBtn.innerText = 'Yes';
            yesBtn.id = 'confirmYes';
            yesBtn.onclick = () => {
                modal.style.display = 'none';
                // Simulate exit
                document.body.innerHTML = '<h1 style="text-align: center; margin-top: 20%;">Application Closed</h1>';
            };

            const noBtn = document.createElement('button');
            noBtn.innerText = 'No';
            noBtn.id = 'confirmNo';
            noBtn.onclick = () => {
                modal.style.display = 'none';
            };

            modalFooter.appendChild(yesBtn);
            modalFooter.appendChild(noBtn);
        } else {
            const okBtn = document.createElement('button');
            okBtn.innerText = 'OK';
            okBtn.id = 'modalOkBtn';
            okBtn.onclick = () => {
                modal.style.display = 'none';
            };
            modalFooter.appendChild(okBtn);
        }

        modal.style.display = 'block';
    }

    for (let span of closeModalSpans) {
        span.onclick = function() {
            modal.style.display = "none";
        }
    }

    clearBtn.addEventListener('click', () => {
        dayInput.value = '';
        monthInput.value = '';
        yearInput.value = '';
    });

    checkBtn.addEventListener('click', async () => {
        try {
            if (isCheckDateMode) {
                const queryParams = new URLSearchParams({
                    day: dayInput.value,
                    month: monthInput.value,
                    year: yearInput.value
                }).toString();

                const response = await fetch(`/api/checkDate?${queryParams}`);
                const data = await response.json();
                
                showModal(data.message, 'info');
            } else {
                const queryParams = new URLSearchParams({
                    month: monthInput.value,
                    year: yearInput.value
                }).toString();

                const response = await fetch(`/api/dayInMonth?${queryParams}`);
                const data = await response.json();
                
                if (data.error) {
                    showModal(data.error, 'info');
                } else {
                    showModal(`Month ${monthInput.value}/${yearInput.value} has ${data.days} days.`, 'info');
                }
            }
        } catch (error) {
            showModal("Error connecting to server!", 'info');
        }
    });

    closeAppBtn.addEventListener('click', () => {
        showModal('Are you sure to exit?', 'confirm');
    });
});
