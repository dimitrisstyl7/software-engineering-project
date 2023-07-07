// Nav is to keep track of the Month we're looking at
let nav = 0;

// ---------------------------------------------------------------------------------------------
// When the program is loaded again, the clicked should not be on the previous day
let clicked = null;
// ---------------------------------------------------------------------------------------------

let events = localStorage.getItem('events') ? JSON.parse(localStorage.getItem('events')) : [];

const calendar = document.getElementById('calendar');
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
// This function get the data of the api site that has the data for this date and showcase it in a,
// Array table
// ---------------------------------------------------------------------------------------------
function fetchData(date) {
    // The clicked has the date of the user
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
            // This is to disable the time that are picked in the system
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
    // Delete all data that are im the mytable
    // This feature correct any mistake that will happen,
    // for the data of the previous dates
    while (tbl.rows.length > 0) {
        tbl.deleteRow(0);
    }

    // Function to create a delete button for each row
    function createDeleteButton() {
        const deleteButton = document.createElement('button');
        deleteButton.textContent ="Delete";
        deleteButton.style.color = "black";
        // Create event for the deleteButton
        deleteButton.addEventListener('click', deleteRow);
        return deleteButton;
    }

    function createEditButton() {
        const editButton = document.createElement('button');
        editButton.textContent = 'Update';
        editButton.style.color = "black";
        // Create event for the editButton
        editButton.addEventListener('click', editRow);
        return editButton;
    }

    // Create Header Row
    let headerRow = tbl.insertRow();
    headerRow.style.paddingTop = "12px";
    headerRow.style.paddingBottom = "12px";
    headerRow.style.backgroundColor = "#3F4079";
    headerRow.style.color = "white";
    let headerCell1 = headerRow.insertCell();
    let headerCell2 = headerRow.insertCell();
    let headerCell3 = headerRow.insertCell();
    let headerCell4 = headerRow.insertCell();
    let headerCell5 = headerRow.insertCell();
    let headerCell6 = headerRow.insertCell();
    let headerCell7 = headerRow.insertCell();

    // First of all we put the low elements to help the User know the columns of the table
    headerCell1.innerHTML = "ID";
    headerCell2.innerHTML = "Date and Time";
    headerCell3.innerHTML = "Patient Id";
    headerCell4.innerHTML = "Name";
    headerCell5.innerHTML = "Surname";
    headerCell6.innerHTML = "Update time";
    headerCell7.innerHTML = "Delete";

    // Here we say went we have no data in the api site insert in the table null data
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
    // Here we put all the data in the necessary columns of the table
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
        // Here we put the clicked=Date with the Time to make the User understand what he put in the table
        cell2.innerText= clicked +" " + linklist[i]['timeSlot'];
        cell3.innerText=linklist[i]['amka'];
        cell4.innerText=linklist[i]['name'];
        cell5.innerText=linklist[i]['surname'];
        cell6.appendChild(createEditButton()); // Add edit button
        cell7.appendChild(createDeleteButton()); // Add delete button


    }
}

// ----------------------------------------------------------------------------------------------
// This function load the days of the Month into the site and add eventListeners in the day<divs>
// ----------------------------------------------------------------------------------------------
function load() {

    const dt = new Date(); // Gets the Date Month Year and Time of the Session

    // Check the nav if it changes
    if (nav !== 0) {
        // Update the Month with the element nav ( example 7(July) -1 (nav) = 6(June) )
        dt.setMonth(new Date().getMonth() + nav);
    }

    const day = dt.getDate();// Gets the Date
    const month = dt.getMonth();// Gets the Month
    const year = dt.getFullYear();// Gets the Year

    const firstDayOfMonth = new Date(year, month, 1); // This is the first day of the Current Month
    const daysInMonth = new Date(year, month + 1, 0).getDate(); // This is the last day of the previous Month

    // Gets the Name of the Day, DD/MM/YYYY of the Current Month ( example Friday, 1/1/2023)
    const dateString = firstDayOfMonth.toLocaleDateString('en-us', {
        weekday: 'long',
        year: 'numeric',
        month: 'numeric',
        day: 'numeric',
    });

    // Gets the name of the Day and check what is the number in the list of Weekdays
    // Example Friday = 5 in the Weekdays array list
    const paddingDays = weekdays.indexOf(dateString.split(', ')[0]);

    // Put in the monthDisplay the Month and the Year
    document.getElementById('monthDisplay').innerText =
        `${dt.toLocaleDateString('en-us', { month: 'long' })} ${year}`;

    // Wiping out all the daySquare, PaddingSquare and Everything inside the calendar div
    calendar.innerHTML = '';

    for(let i = 1; i <= paddingDays + daysInMonth; i++) { // Help render the empty squares on the screen for the paddingDays
        const daySquare = document.createElement('div');
        daySquare.classList.add('day');

        // We check if the month is < 10 or > 10
        // If is < 10 we put the 0 in front of the number
        // Example ( 9<10 yes then monthString= '09')
        const monthString = (month < 9 ? '0' + (month + 1) : month + 1);

        // We check if the paddingDays is < 10 or > 10
        // If is < 10 we put the 0 in front of the number
        // Example ( 9<10 yes then dayString= '09')
        const dayString = (i-paddingDays < 10 ? '0' + (i - paddingDays): i-paddingDays);

        // We put the YY-MM-DD into dateString
        const dateString = `${year}-${monthString}-${dayString}`;

        if (i > paddingDays) {
            daySquare.innerText = i - paddingDays;// Put into the InnerText the Current Number for the day we are in

            if (i - paddingDays === day && nav === 0) { // Create a class name for the Current Day we are in right now
                daySquare.id = 'currentDay';
            }

            daySquare.addEventListener('click', () => fetchData(dateString)); // Add a eventListener to fetch-show the data
        } else {
            daySquare.classList.add('padding'); // Make sure that the CSS is different from the daySquare pretty much the paddingDay is invisible
        }

        calendar.appendChild(daySquare); // Take daySquare and put it into the Calendar Container
    }
}

// -------------------------------------------------------------------------------------
// This Function here can close the Modal block for the input of the data
// -------------------------------------------------------------------------------------
function closeModal2() {
    // Before the close of the Modal block we remove the errors that maybe was on them
    newTime.classList.remove('error');
    newClientAFM.classList.remove('error');
    newClientName.classList.remove('error');
    newClientSurname.classList.remove('error');

    // Then I remove the display out of the screen
    newEventModal2.style.display = 'none';
    backDrop2.style.display = 'none';

    // Then I remove all the data out of the inputs
    newClientAFM.value = '';
    newClientName.value = '';
    newClientSurname.value = '';
    eventTime.value = '';

    // With the lower element I remove the date print(imprint)
    clicked = null;
    // Then I reload the data of the site to be upToDate
    load();
}


// -------------------------------------------------------------------------------------
// In the next function we save the User(Doctor) update input by the appointmentID
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
                fetchData(clicked);
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

// --------------------------------------------------------------------------------------------------------
//  This Function create the operations back - next month of the site and save - cancel of the Modal block
// --------------------------------------------------------------------------------------------------------
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
load();// We load the site to be upToDate of the Date/Months