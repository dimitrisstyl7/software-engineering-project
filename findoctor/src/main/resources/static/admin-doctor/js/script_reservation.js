let nav = 0;

// ---------------------------------------------------------------------------------------------
// Otan gini load xana to programa to clicked na min einai stin proigoumeni imera
let clicked = null;
// ---------------------------------------------------------------------------------------------

let events = localStorage.getItem('events') ? JSON.parse(localStorage.getItem('events')) : [];

const calendar = document.getElementById('calendar');
const newEventModal = document.getElementById('newEventModal');
const deleteEventModal = document.getElementById('deleteEventModal');
const backDrop = document.getElementById('modalBackDrop');
const eventTitleInput = document.getElementById('eventTitleInput');
const clientAFM = document.getElementById('clientAFM');
const clientName = document.getElementById('clientName');
const clientSurname = document.getElementById('clientSurname');
const clientEmail = document.getElementById('clientEmail');
const eventTime = document.getElementById('eventTimeOfDate');

const newEventModal2 = document.getElementById('newEventModal2');
const backDrop2 = document.getElementById('modalBackDrop2');
const newTime = document.getElementById('newTimeOfDate');
const newTitleInput = document.getElementById('newTitleInput');
const newClientAFM = document.getElementById('newClientAFM');
const newClientName = document.getElementById('newClientName');
const newClientSurname = document.getElementById('newClientSurname');

const tbl = document.getElementById("myTable");
const doctorUsername = document.getElementById('doctorUsername');



const weekdays = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];


// ---------------------------------------------------------------------------------------------
// Auto to function exei tin leitourgia otan patisi o xristis mia fora na mpori na tou emfanisti
// enas pinakas me ola ta stoixia tou xristi
// ---------------------------------------------------------------------------------------------
function fetchData(date) {
    // To clicked periexei tin imera pou exi patisi o xristis
    clicked = date;

    console.log(doctorUsername.value);

    /*
    fetch('https://api.example.com/data')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text(); // Parse the response as plain text
        })
        .then(data => {
            // Process the retrieved data
            console.log(data);
        })
        .catch(error => {
            // Handle any errors that occurred during the request
            console.error('Error:', error);
        });

     */

    const filter = events.filter( e => e.date === clicked);

    // Diagrafi oti dara iparxoun sto mytable
    // Auto to kani oste na min iparxoun proigoumen data apo alles imeres
    let linklist = filter.length;
    while (tbl.rows.length > 0) {
        tbl.deleteRow(0);
    }

    // Function to delete a specific row
    function deleteRow(event) {
        const row = event.target.parentNode.parentNode; // Get the parent row element
        tbl.deleteRow(row.rowIndex);
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

    // Prota Prota vazoume stin proti stili ta pio kato stoixeia oste
    // o xristis na mpori na xeri ti einai to kathe ena
    headerCell1.innerHTML = "Date and Time";
    headerCell2.innerHTML = "Patient Id";
    headerCell3.innerHTML = "Name";
    headerCell4.innerHTML = "Surname";
    headerCell5.innerHTML = "Delete";
    headerCell6.innerHTML = "Edit";

    // Function to handle edit button click
    function editRow(event) {
        const row = event.target.parentNode.parentNode; // Get the parent row element

        // Perni ta stoixia tis grammis
        const eventTimeCell = row.cells[0];
        const clientAFMCell = row.cells[1];
        const clientNameCell = row.cells[2];
        const clientSurnameCell = row.cells[3];

        // Efanizontas ena prompt dini tin epilogi sto xristi na mpori
        // na allaxi ta dedomena pou einai kataxorimena
        const newEventTime = prompt('Enter the new event time:', eventTimeCell.innerHTML);
        const newClientAFM = prompt('Enter the new client AFM:', clientAFMCell.innerText);
        const newClientName = prompt('Enter the new client name:', clientNameCell.innerText);
        const newClientSurname = prompt('Enter the new client surname:', clientSurnameCell.innerText);

        // Allazi ola ta proigoumena stoixia me ta kainourgia stoixia pou exei dosi
        eventTimeCell.innerHTML = newEventTime;
        clientAFMCell.innerText = newClientAFM;
        clientNameCell.innerText = newClientName;
        clientSurnameCell.innerText = newClientSurname;
    }


    // Function to create an edit button for each row
    function createEditButton() {
        const editButton = document.createElement('button');
        editButton.textContent = 'Edit';
        editButton.style.color = "black";
        // Dimiourgo event gia to edit
        editButton.addEventListener('click', () => {
            const eventForDay = events.find(e => e.date === clicked);
            newEventModal2.style.display = 'block';
            backDrop2.style.display = 'block';
        });
        return editButton;
    }

    // Otan den iparxoun data mesa stin imera tote topo8eta null sto row
    if (linklist == 0 ){
        let r = tbl.insertRow();
        let cell1 = r.insertCell();
        let cell2 = r.insertCell();
        let cell3 = r.insertCell();
        let cell4 = r.insertCell();
        let cell5 = r.insertCell();
        let cell6 = r.insertCell();

        cell1.innerHTML=null;
        cell2.innerText=null;
        cell3.innerText=null;
        cell4.innerText=null;
        cell5.innerText=null;
        cell6.innerText=null;
    }

    // Create Rows of Information
    // Me ena for vazoume ola ta data mesa ston pinaka tis imeras
    for (let i=0; i<linklist; i++){
        let r = tbl.insertRow();
        let cell1 = r.insertCell();
        let cell2 = r.insertCell();
        let cell3 = r.insertCell();
        let cell4 = r.insertCell();
        let cell5 = r.insertCell();
        let cell6 = r.insertCell();

        cell1.innerHTML=filter[i].eventTime;
        cell2.innerText=filter[i].clientAFM;
        cell3.innerText=filter[i].clientName;
        cell4.innerText=filter[i].clientSurname;
        cell5.appendChild(createDeleteButton()); // Add delete button
        cell6.appendChild(createEditButton()); // Add edit button

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

        const dayString = `${month + 1}/${i - paddingDays}/${year}`;

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

            daySquare.addEventListener('click', () => fetchData(dayString));
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
function closeModal() {
    // Prin na to kliso kano remove ta errors ean iparxoun
    eventTitleInput.classList.remove('error');
    eventTime.classList.remove('error');
    clientAFM.classList.remove('error');
    clientName.classList.remove('error');
    clientSurname.classList.remove('error');
    clientEmail.classList.remove('error');

    // Kano se ola ta display na feugoun apo to screen
    newEventModal.style.display = 'none';
    deleteEventModal.style.display = 'none';
    backDrop.style.display = 'none';

    // Pio kato feugo ola ta stoixia pou egra4e prin sta inputs
    eventTitleInput.value = '';
    clientAFM.value = '';
    clientName.value = '';
    clientSurname.value = '';
    clientEmail.value = '';
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
function saveEvent(){
    // To c=0 einai gia na kratisoume ena value oste ean se kapio apo ta input eixe lathos
    // na mporousame na stamatisoume na kani save
    let c=0;
    // Pattern gia to email px a + @gmail + .com
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    // Elenxi ( trexei to test ) ean isxi me to emailPattern
    if ( emailPattern.test(clientEmail.value) ){
        clientEmail.classList.remove('error');
        c=c+1;
    }else{
        clientEmail.classList.add('error');
    }

    // Elenxi ean einai keno to time
    if ( eventTime.value == "" ){
        eventTime.classList.add('error');
    }else{
        eventTime.classList.remove('error');
        c=c+1;
    }

    // Elenxi ean exei numbers mesa sto input me tin xrisi tou NaN
    // Akomi elenxoume ean einai keno to input
    if ( ( !isNaN(clientName.value) ) || (clientName.value =="")){
        clientName.classList.add('error');
    }else{
        clientName.classList.remove('error');
        c=c+1;
    }

    // Elenxi ean exei numbers mesa sto input me tin xrisi tou NaN
    // Akomi elenxoume ean einai keno to input
    if ( ( !isNaN(clientSurname.value) ) || (clientSurname.value =="")){
        clientSurname.classList.add('error');
    }else{
        clientSurname.classList.remove('error');
        c=c+1;
    }

    // Elenxi ean exei characters mesa sto input me tin xrisi tou NaN
    // Akomi elenxoume ean einai keno to input
    if ( ( isNaN(clientAFM.value) ) || ( clientAFM.value =="") ){
        clientAFM.classList.add('error');
    }else{
        clientAFM.classList.remove('error');
        c=c+1;
    }

    // Elenxoume ean einai keno to input
    if (eventTitleInput.value ==""){
        eventTitleInput.classList.add('error');
    }else{
        eventTitleInput.classList.remove('error');
        c=c+1;
    }

    // Elenxoume ean ola ta value (c) einai 6 oste na doume oti einai ola sosta
    // Epeita apothikevoume sto localstorage me tin xrisi tou push
    if (c == 6) {

        events.push({
            date: clicked , // to clicked einai to date pou patise o xristis
            eventTime: clicked + " " + eventTime.value,
            title: eventTitleInput.value,
            clientAFM: clientAFM.value,
            clientName: clientName.value,
            clientSurname: clientSurname.value,
        });

        localStorage.setItem('events', JSON.stringify(events));
        closeModal(); // Klini to Modal block pou fenete
    }
}

// -------------------------------------------------------------------------------------
// Auti einai i Function pou klini to Modal block pou einai gia tin eisagogi ton stixion
// -------------------------------------------------------------------------------------
function closeModal2() {
    // Prin na to kliso kano remove ta errors ean iparxoun
    newTitleInput.classList.remove('error');
    newTime.classList.remove('error');
    newClientAFM.classList.remove('error');
    newClientName.classList.remove('error');
    newClientSurname.classList.remove('error');

    // Kano se ola ta display na feugoun apo to screen
    newEventModal2.style.display = 'none';
    backDrop2.style.display = 'none';

    // Pio kato feugo ola ta stoixia pou egra4e prin sta inputs
    newTitleInput.value = '';
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
function saveEvent2(){
    // To c=0 einai gia na kratisoume ena value oste ean se kapio apo ta input eixe lathos
    // na mporousame na stamatisoume na kani save
    let c=0;

    // Elenxi ean einai keno to time
    if ( newTime.value == "" ){
        newTime.classList.add('error');
    }else{
        newTime.classList.remove('error');
        c=c+1;
    }

    // Elenxi ean exei numbers mesa sto input me tin xrisi tou NaN
    // Akomi elenxoume ean einai keno to input
    if ( ( !isNaN(newClientName.value) ) || (newClientName.value =="")){
        newClientName.classList.add('error');
    }else{
        newClientName.classList.remove('error');
        c=c+1;
    }

    // Elenxi ean exei numbers mesa sto input me tin xrisi tou NaN
    // Akomi elenxoume ean einai keno to input
    if ( ( !isNaN(newClientSurname.value) ) || (newClientSurname.value =="")){
        newClientSurname.classList.add('error');
    }else{
        newClientSurname.classList.remove('error');
        c=c+1;
    }

    // Elenxi ean exei characters mesa sto input me tin xrisi tou NaN
    // Akomi elenxoume ean einai keno to input
    if ( ( isNaN(newClientAFM.value) ) || ( newClientAFM.value =="") ){
        newClientAFM.classList.add('error');
    }else{
        newClientAFM.classList.remove('error');
        c=c+1;
    }

    // Elenxoume ean einai keno to input
    if (newTitleInput.value ==""){
        newTitleInput.classList.add('error');
    }else{
        newTitleInput.classList.remove('error');
        c=c+1;
    }

    // Elenxoume ean ola ta value (c) einai 6 oste na doume oti einai ola sosta
    // Epeita apothikevoume sto localstorage me tin xrisi tou push
    if (c == 5) {

        events.push({
            date: clicked , // to clicked einai to date pou patise o xristis
            eventTime: clicked + " " + eventTime.value,
            title: eventTitleInput.value,
            clientAFM: clientAFM.value,
            clientName: clientName.value,
            clientSurname: clientSurname.value,
        });

        localStorage.setItem('events', JSON.stringify(events));
        closeModal2(); // Klini to Modal block pou fenete
    }
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

    document.getElementById('saveButton').addEventListener('click', saveEvent);
    document.getElementById('cancelButton').addEventListener('click', closeModal)
    document.getElementById('saveButton2').addEventListener('click', saveEvent2);
    document.getElementById('cancelButton2').addEventListener('click', closeModal2)
}

initButtons();
load();