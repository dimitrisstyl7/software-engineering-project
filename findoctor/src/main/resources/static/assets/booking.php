<?php

if(!$_POST) exit;

// Email verification, do not edit.
function isEmail($email_booking ) {
	return(preg_match("/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/",$email_booking ));
}

if (!defined("PHP_EOL")) define("PHP_EOL", "\r\n");
$doctor_name_booking     = $_POST['doctor_name_booking'];
$name_booking     = $_POST['name_booking'];
$lastname_booking   = $_POST['lastname_booking'];
$email_booking   = $_POST['email_booking'];
$booking_date    = $_POST['booking_date'];
$booking_time   = $_POST['booking_time'];
$booking_visit    = $_POST['booking_visit'];
$booking_message    = $_POST['booking_message'];
$verify_booking   = $_POST['verify_booking'];


if(trim($name_booking) == '') {
	echo '<div class="error_message">Please enter your Name.</div>';
	exit();
} else if(trim($lastname_booking ) == '') {
	echo '<div class="error_message">Please enter your Last name.</div>';
	exit();
} else if(trim($email_booking) == '') {
	echo '<div class="error_message">Please enter a valid email address.</div>';
	exit();
} else if(!isEmail($email_booking)) {
	echo '<div class="error_message">You have enter an invalid e-mail address, try again.</div>';
	exit();
} else if(trim($booking_date) == '') {
	echo '<div class="error_message">Please enter your Date.</div>';
	exit();
} else if(trim($booking_time) == '') {
	echo '<div class="error_message">Please enter your Time.</div>';
	exit();
} else if(trim($booking_visit) == '') {
	echo '<div class="error_message">Please enter which visit.</div>';
	exit();
} else if(!isset($verify_booking) || trim($verify_booking) == '') {
	echo '<div class="error_message"> Please enter the verification number.</div>';
	exit();
} else if(trim($verify_booking) != '4') {
	echo '<div class="error_message">The verification number you entered is incorrect.</div>';
	exit();
}

//$address = "HERE your email address";
$address = "test@domain.com";


// Below the subject of the email
$e_subject = 'You\'ve been contacted by ' . $name_booking . '.';

// You can change this if you feel that you need to.
$e_body = "You have been contacted by $name_booking  $lastname_booking with booking info is as follows." . PHP_EOL . PHP_EOL;
$e_content = "Name: $name_booking\nLastname: $lastname_booking\nEmail address: $email_booking\nDoctor name: $doctor_name_booking\nVisit date: $booking_date\nVisit time: $booking_time\nWhich visit: $booking_visit\nAdditional message $booking_message" . PHP_EOL . PHP_EOL;
$e_reply = "You can contact $name_booking $lastname_booking via email $email_booking";

$msg = wordwrap( $e_body . $e_content . $e_reply, 70 );

$headers = "From: $email_booking" . PHP_EOL;
$headers .= "Reply-To: $email_booking" . PHP_EOL;
$headers .= "MIME-Version: 1.0" . PHP_EOL;
$headers .= "Content-type: text/plain; charset=utf-8" . PHP_EOL;
$headers .= "Content-Transfer-Encoding: quoted-printable" . PHP_EOL;

$user = "$email_booking";
$usersubject = "Thank You";
$userheaders = "From: info@findoctor.com\n";
$usermessage = "Thank you for booking via Findoctor. We will confirm shortly!";
mail($user,$usersubject,$usermessage,$userheaders);

if(mail($address, $e_subject, $msg, $headers)) {

	// Success message
	echo "<div id='success_page' style='padding:60px 30px;text-align:center;'>";
	echo "<h4>Email Sent!</h4><br>";
	echo "Thank you <strong>$name_booking $lastname_booking</strong>,<br> your bookinq request has been submitted. We will confirm date and time you shortly.";
	echo "</div>";

} else {

	echo 'ERROR!';

}
