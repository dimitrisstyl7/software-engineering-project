let nav = 0;

// ---------------------------------------------------------------------------------------------
// Otan gini load xana to programa to clicked na min einai stin proigoumeni imera
let clicked = null;
// ---------------------------------------------------------------------------------------------

let events = localStorage.getItem('events') ? JSON.parse(localStorage.getItem('events')) : [];

const calendar = document.getElementById('calendar');
const newEventModal = document.getElementById('newEventModal');
const backDrop = document.getElementById('modalBackDrop');
const eventTime = document.getElementById('eventTimeOfDate');

const newEventModal2 = document.getElementById('newEventModal2');
const backDrop2 = document.getElementById('modalBackDrop2');
const newAppointmentID = document.getElementById('newAppointmentID');
const newTime = document.getElementById('newTimeOfDate');
const newClientAFM = document.getElementById('newClientAFM');
const newClientName = document.getElementById('newClientName');
const newClientSurname = document.getElementById('newClientSurname');

const tbl = document.getElementById("myTable");
const doctorUsername = document.getElementById('doctorUsername');


const weekdays = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

let linklist=[];

// ---------------------------------------------------------------------------------------------
// Auto to function exei tin leitourgia otan patisi o xristis mia fora na mpori na tou emfanisti
// enas pinakas me ola ta stoixia tou xristi
// ---------------------------------------------------------------------------------------------
function fetchData(date) {
    // To clicked periexei tin imera pou exi patisi o xristis
    clicked = date;

    console.log(date);

    fetch('http://localhost:8080/api/v1/appointments?date=' + clicked)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // Parse the response as plain text
        })
        .then(data => {
            // Process the retrieved data
            console.log(typeof data);
            if (data.length !== 0){
                linklist = JSON.parse(data);
            }else{
                linklist = "";
            }
            createTable(linklist);
        })
        .catch(error => {
            // Handle any errors that occurred during the request
            console.error('Error:', error);
        });

}

function deleteRow(event) {
    const row = event.target.parentNode.parentNode; // Get the parent row element
    const appointmentID = row.cells[0].innerText;

    let url = 'http://localhost:8080/api/v1/appointments/delete/' + appointmentID;

    fetch(url,{method:'DELETE'})
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            row.remove();
        })
        .catch(error => {
            // Handle any errors that occurred during the request
            console.error('Error:', error);
        });
}

function editRow(event){

    const row = event.target.parentNode.parentNode; // Get the parent row element
    const ClientAppointment = row.cells[0].innerText;
    const DayAndTime = row.cells[1].innerText;
    const Day = DayAndTime.split(' ')[0]
    const ClientAFM = row.cells[2].innerText;
    const ClientName = row.cells[3].innerText;
    const ClientSurname= row.cells[4].innerText;

    let selectElement = document.getElementById("newTimeOfDate");

    let url = 'http://localhost:8080/api/v1/available-time-slots?&date='+ Day +'&doctorUsername='+ doctorUsername.value;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // Parse the response as plain text
        })
        .then(data => {

            let TimeSlots = JSON.parse(data);

            // Iterate over the options and disable if the value is false
            for (let i = 0; i < selectElement.options.length; i++) {
                let optionValue = selectElement.options[i].value;
                let jsonValue = TimeSlots[optionValue];

                if (jsonValue === false) {
                    selectElement.options[i].disabled = true;
                }
            }

        })



    newAppointmentID.value=ClientAppointment;
    newClientAFM.value=ClientAFM;
    newClientName.value=ClientName;
    newClientSurname.value=ClientSurname;

    newEventModal2.style.display = 'block';
    backDrop2.style.display = 'block';
}


function createTable(linklist){
    // Diagrafi oti dara iparxoun sto mytable
    // Auto to kani oste na min iparxoun proigoumen data apo alles imeres
    while (tbl.rows.length > 0) {
        tbl.deleteRow(0);
    }

    // Function to create a delete button for each row
    function createDeleteButton() {
        const deleteButton = document.createElement('button');
        deleteButton.textContent ="delete";
        deleteButton.style.color = "black";
        // Dimiourgo event gia to delete
        deleteButton.addEventListener('click', deleteRow);
        return deleteButton;
    }

    function createEditButton() {
        const editButton = document.createElement('button');
        editButton.textContent = 'Edit';
        editButton.style.color = "black";
        // Dimiourgo event gia to edit
        editButton.addEventListener('click', editRow);
        return editButton;
    }

    // Create Header Row
    let headerRow = tbl.insertRow();
    headerRow.style.paddingTop = "12px";
    headerRow.style.paddingBottom = "12px";
    headerRow.style.backgroundColor = "Green";
    headerRow.style.color = "white";
    let headerCell1 = headerRow.insertCell();
    let headerCell2 = headerRow.insertCell();
    let headerCell3 = headerRow.insertCell();
    let headerCell4 = headerRow.insertCell();
    let headerCell5 = headerRow.insertCell();
    let headerCell6 = headerRow.insertCell();
    let headerCell7 = headerRow.insertCell();

    // Prota Prota vazoume stin proti stili ta pio kato stoixeia oste
    // o xristis na mpori na xeri ti einai to kathe ena
    headerCell1.innerHTML = "Appointment ID";
    headerCell2.innerHTML = "Date and Time";
    headerCell3.innerHTML = "Patient Id";
    headerCell4.innerHTML = "Name";
    headerCell5.innerHTML = "Surname";
    headerCell6.innerHTML = "Delete";
    headerCell7.innerHTML = "Edit";

    if (linklist.length === 0 ){
        let r = tbl.insertRow();
        let cell1 = r.insertCell();
        let cell2 = r.insertCell();
        let cell3 = r.insertCell();
        let cell4 = r.insertCell();
        let cell5 = r.insertCell();
        let cell6 = r.insertCell();
        let cell7 = r.insertCell();

        cell1.innerText=null;
        cell2.innerText=null;
        cell3.innerText=null;
        cell4.innerText=null;
        cell5.innerText=null;
        cell6.innerText=null;
        cell7.innerText=null;
    }

    // Create Rows of Information
    // Me ena for vazoume ola ta data mesa ston pinaka tis imeras
    for (let i=0; i<linklist.length; i++){
        let r = tbl.insertRow();
        let cell1 = r.insertCell();
        let cell2 = r.insertCell();
        let cell3 = r.insertCell();
        let cell4 = r.insertCell();
        let cell5 = r.insertCell();
        let cell6 = r.insertCell();
        let cell7 = r.insertCell();

        cell1.innerText=linklist[i]['id'];
        cell2.innerText= clicked +" " + linklist[i]['timeSlot'];
        cell3.innerText=linklist[i]['amka'];
        cell4.innerText=linklist[i]['name'];
        cell5.innerText=linklist[i]['surname'];
        cell6.appendChild(createDeleteButton()); // Add delete button
        cell7.appendChild(createEditButton()); // Add edit button

    }
}

// -------------------------------------------------------------------------------------
//
// -------------------------------------------------------------------------------------
function load() {


    const dt = new Date();

    if (nav !== 0) {
        dt.setMonth(new Date().getMonth() + nav);
    }

    const day = dt.getDate();
    const month = dt.getMonth();
    const year = dt.getFullYear();

    const firstDayOfMonth = new Date(year, month, 1);
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    const dateString = firstDayOfMonth.toLocaleDateString('en-us', {
        weekday: 'long',
        year: 'numeric',
        month: 'numeric',
        day: 'numeric',
    });
    const paddingDays = weekdays.indexOf(dateString.split(', ')[0]);

    document.getElementById('monthDisplay').innerText =
        `${dt.toLocaleDateString('en-us', { month: 'long' })} ${year}`;

    calendar.innerHTML = '';

    for(let i = 1; i <= paddingDays + daysInMonth; i++) {
        const daySquare = document.createElement('div');
        daySquare.classList.add('day');

        const monthString = (month < 9 ? '0' + (month + 1) : month + 1);
        const dayString = (i-paddingDays < 10 ? '0' + (i - paddingDays): i-paddingDays);
        const dateString = `${year}-${monthString}-${dayString}`;

        if (i > paddingDays) {
            daySquare.innerText = i - paddingDays;
            const eventForDay = events.find(e => e.date === dayString);

            if (i - paddingDays === day && nav === 0) {
                daySquare.id = 'currentDay';
            }

            if (eventForDay) {
                const eventDiv = document.createElement('div');
                eventDiv.classList.add('event');
                eventDiv.innerText = eventForDay.title;
                daySquare.appendChild(eventDiv);
            }

            daySquare.addEventListener('click', () => fetchData(dateString));
            daySquare.addEventListener('dblclick', () => {
                const eventForDay = events.find(e => e.date === paddingDays);
                newEventModal.style.display = 'block';
                backDrop.style.display = 'block';
            });
        } else {
            daySquare.classList.add('padding');
        }

        calendar.appendChild(daySquare);
    }
}

// -------------------------------------------------------------------------------------
// Auti einai i Function pou klini to Modal block pou einai gia tin eisagogi ton stixion
// -------------------------------------------------------------------------------------
function closeModal2() {
    // Prin na to kliso kano remove ta errors ean iparxoun
    newTime.classList.remove('error');
    newClientAFM.classList.remove('error');
    newClientName.classList.remove('error');
    newClientSurname.classList.remove('error');

    // Kano se ola ta display na feugoun apo to screen
    newEventModal2.style.display = 'none';
    backDrop2.style.display = 'none';

    // Pio kato feugo ola ta stoixia pou egra4e prin sta input
    newClientAFM.value = '';
    newClientName.value = '';
    newClientSurname.value = '';
    eventTime.value = '';

    // Me to clicked null feugo tin imera pou eixe patisi o xristis prin
    clicked = null;
    // Kano load oste na prosthesi tixon kainourgia stoixia stin selida
    // px. Events Titles
    load();
}


// -------------------------------------------------------------------------------------
// Sto pio kato Function apothikevi ta inputs pou exei kani o xristis
// -------------------------------------------------------------------------------------
function saveButton2() {

    let urlPut = 'http://localhost:8080/api/v1/appointments/update/' + newAppointmentID.value +'?newTime='+ newTime.value;
    console.log(urlPut);

    fetch(urlPut, {
        method: 'PUT',
    })
        .then(response => {
            if (response.ok) {
                console.log("JSON API updated successfully.");
                newTime.classList.remove('error');
                closeModal2();
            } else {
                console.error("Failed to update JSON API.");
                newTime.classList.add('error');
            }
        })
        .catch(error => {
            console.error("An error occurred while updating JSON API:", error);
            // Handle the error
        });

}

// ------------------------------------------------------------------------------------------
//  Sto pio kato Fuction kani tis leitourgies back - next month kai to save - cancel to Modal
// ------------------------------------------------------------------------------------------
function initButtons() {
    document.getElementById('nextButton').addEventListener('click', () => {
        nav++;
        load();
    });

    document.getElementById('backButton').addEventListener('click', () => {
        nav--;
        load();
    });

    document.getElementById('saveButton2').addEventListener('click', saveButton2);
    document.getElementById('cancelButton2').addEventListener('click', closeModal2)
}

initButtons();
load();