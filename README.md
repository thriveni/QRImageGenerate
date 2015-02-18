### QR code Generation for URL

Quick Response Code (QR Code) is a two-dimensional matrix like barcode. Generating QR Code for given URLs <br/>
Store the generated n number of QR codes in separate directories in the given directory path.

####Examples :

Sample url is http://www.website.com/xyz/hfakjd/123456789/1.do <br/>
Generared QR code is renamed with the tag 123456789.png by splitting the url.

## Usage

Pass the Directory path where the text file resides. The text file should contain one url per line. <br/>

######qrcodegen qri = new qrcodegen(); <br/>
######qri.createImage("Directorypath"); <br/>


## Motivation 
Had to generate QR codes for given urls. This is done using available qrcode generation websites.
Then had to rename the downloaded image with the certain tag selected from the same url. <br/>The whole process took 3 minutes for a single qr code. There were more than 5000 urls to convert.<br/> To reduce the burden of doing this manually wrote this application. 
 
## Library used

Using ZXing ("Zebra Crossing") API for QR code processing in Java. 


## Result

Qr codes for each url saved in separate folders in the given directory path



