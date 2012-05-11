<!--
 function OpenCertDetails()
 {
thewindow =window.open('https://www.thawte.com/cgi/server/certdetails.exe?code=AUEXPAX1-1X', 'anew',config='height=490,width=516,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,directories=no,status=yes');
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

var submitcount=0;
                        function checkData (){
                                if (document.Payment.CardNumber.value == "") {
                                        alert("Please enter your 16 digit credit card number        ")
                                        document.Payment.CardNumber.focus()
                                        return false} 
                                if (document.Payment.Name.value == "") {
                                        alert("Please enter your name as it appears on your credit card        ")
                                        document.Payment.Name.focus()
                                        return false} 
                                if (document.Payment.CardType.value == "") {
                                        alert("Please enter the type of credit card        ")
                                        document.Payment.CardType.focus()
                                        return false} 
                                if (document.Payment.ExpiryMonth.value == "") {
                                        alert("Please enter the two digit Expiry Month        ")
                                        document.Payment.ExpiryMonth.focus()
                                        return false} 
                                if (document.Payment.ExpiryYear.value == "") {
                                        alert("Please enter the two digit Expiry Year        ")
                                        document.Payment.ExpiryYear.focus()
                                        return false} 
                                if (document.Payment.Authorise.checked == false ) {
					     alert("Please tick the \"YES\" box to authorise this charge on this credit card     \n ");
                                        return false;                   
}                                                                                                                                                                                                                                                                                                     
                          else 
   {
   if (submitcount == 0)
      {
      submitcount++;
      return true;
      }
   else 
      {
      alert("Please wait . . . . already sending.");
      return false;
      }
   }
}
// -->