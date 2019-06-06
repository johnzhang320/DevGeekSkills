
<strong>* Agent Login Help</strong>
 
<br>*  Differing from using Base64 encode password in Spring Basic Authentication or HTTP header Authorization: basic UNLKL@#d=R12
<br>*  We are using dynamic public key to encrypt password in Browser side
<br>*  If people simply do not use SSL communication or Certificate expired cause HTTPS failed or hacker make web victims introduce 
<br>*  Troy Virus to intercept user's typed password locally even HTTPS is available, this front end encryption technology may be valuable!     
<br>*  Password will be encrypted as soon as mouse cursor left password field 		
<br>*  Password is encrypted by public key which server dynamically generated
<br>*  Each time the server creates a new pair of public key and private key as mouse blur event sends request the server
<br>*  Javascript encrypts password using its received public
<br>*  The sever thread decrypts the password using the private key within same pair as public key
 
<<br><br>*  Take a look at Public key Modulus N and Exponent E , we use 1024 digits of Modulus<br>

<img src="http://localhost:8080/SpringbootAdvancedDemo/images/agents/RSA_Cryptography.png" >