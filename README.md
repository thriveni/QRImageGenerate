## QRImageGenerate

Quick Response Code (QR Code) is a two-dimensional matrix like barcode. Generating QR Code for given URLs <br/>
of fixed size and transparent background, and renaming the generated image with the particular tag from the url itself.

####Examples :

Sample url is http://www.website.com/xyz/hfakjd/123456789/1.do <br/>
Generared QR code is renamed with the tag 123456789.png by splitting the url.


### Construction


## Usage

Pass the Directory path where the text file resides. The text file should contain one url per line. <br/>

######qrcodegen qri = new qrcodegen(); <br/>
######qri.createImage("Directorypath"); <br/>


## Motivation 
Had to generate QR codes for particular url. For that used available Qrcode generation websites which gives generated image ina rar file.

Generating QR code from URL using existing Qrcode generating websites usually took 3 minutes to generate and download 
and rename the image with the respective tag from the given url. This would take hours and more man power to generate 
if the url numbers are in 4k-5k. 


## Library used

Using ZXing ("Zebra Crossing") API for QR code processing in Java. 


## Result

Qr codes for each url saved in separate folders in the given directory path



