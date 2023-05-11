<?php

if(!$_POST) exit;

// Email verification, do not edit.
function isEmail($email_register ) {
	return(preg_match("/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/",$email_register ));
}

if (!defined("PHP_EOL")) define("PHP_EOL", "\r\n");
$name_register     = $_POST['name_register'];
$lastname_register    = $_POST['lastname_register'];
$specialization    = $_POST['specialization'];
$city_register    = $_POST['city_register'];
$country_register    = $_POST['country_register'];
$address_register    = $_POST['address_register'];
$mobile_register    = $_POST['mobile_register'];
$office_phone_register    = $_POST['office_phone_register'];
$email_register    = $_POST['email_register'];
$verify_register   = $_POST['verify_register'];

if(trim($name_register) == '') {
	echo '<div class="error_message">Please enter your Name.</div>';
	exit();
} else if(trim($lastname_register ) == '') {
	echo '<div class="error_message">Please enter your Last name.</div>';
	exit();
} else if(trim($specialization ) == '') {
	echo '<div class="error_message">Please enter your Specialization.</div>';
	exit();
} else if(trim($city_register ) == '') {
	echo '<div class="error_message">Please enter your City.</div>';
	exit();
} else if(trim($country_register ) == '') {
	echo '<div class="error_message">Please enter your Country.</div>';
	exit();
} else if(trim($address_register ) == '') {
	echo '<div class="error_message">Please enter your Address.</div>';
	exit();
} else if(trim($mobile_register) == '') {
	echo '<div class="error_message">Please enter a valid mobile number.</div>';
	exit();
} else if(!is_numeric($mobile_register)) {
	echo '<div class="error_message">Phone number can only contain numbers.</div>';
	exit();
} else if(trim($office_phone_register) == '') {
	echo '<div class="error_message">Please enter a valid phone number.</div>';
	exit();
} else if(!is_numeric($office_phone_register)) {
	echo '<div class="error_message">Phone number can only contain numbers.</div>';
	exit();
} else if(trim($email_register) == '') {
	echo '<div class="error_message">Please enter a valid email address.</div>';
	exit();
} else if(!isEmail($email_register)) {
	echo '<div class="error_message">You have enter an invalid e-mail address, try again.</div>';
	exit();
} else if(!isset($verify_register) || trim($verify_register) == '') {
	echo '<div class="error_message"> Please enter the verification number.</div>';
	exit();
} else if(trim($verify_register) != '4') {
	echo '<div class="error_message">The verification number you entered is incorrect.</div>';
	exit();
}

//$address = "HERE your email address";
$address = "test@domain.com";


// Below the subject of the email
$e_subject = 'You\'ve been contacted by ' . $name_register . '.';

// You can change this if you feel that you need to.
$e_body = "You have been contacted by $name_register $lastname_register with registration info is as follows." . PHP_EOL . PHP_EOL;
$e_content = "Name: $name_register\nLastname: $lastname_register\nSpecialization: $specialization\nCity: $city_register\nCountry: $country_register\nAddress: $address_register\nMobile number: $mobile_register\nOffice number: $office_phone_register\nEmail address: $email_register" . PHP_EOL . PHP_EOL;
$e_reply = "You can contact $lastname_register via email, $email_register or via Mobile phone $mobile_register or Office phone $office_phone_register";

$msg = wordwrap( $e_body . $e_content . $e_reply, 70 );

$headers = "From: $email_register" . PHP_EOL;
$headers .= "Reply-To: $email_register" . PHP_EOL;
$headers .= "MIME-Version: 1.0" . PHP_EOL;
$headers .= "Content-type: text/plain; charset=utf-8" . PHP_EOL;
$headers .= "Content-Transfer-Encoding: quoted-printable" . PHP_EOL;

$user = "$email_register";
$usersubject = "Thank You";
$userheaders = "From: info@findoctor.com\n";
$usermessage = "Thank you for register to Findoctor. We will reply shortly!";
mail($user,$usersubject,$usermessage,$userheaders);

if(mail($address, $e_subject, $msg, $headers)) {

	// Success message
	echo "<div id='success_page' style='padding:60px 30px;text-align:center;'>";
	echo "<h4>Email Sent!</h4><br>";
	echo "Thank you <strong>$name_register $lastname_register</strong>,<br> your registration has been submitted. We will contact you shortly.";
	echo "</div>";

} else {

	echo 'ERROR!';

}
