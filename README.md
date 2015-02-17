## QRImageGenerate

Quick Response Code (QR Code) is a two-dimensional matrix like barcode. Generating QR Code for given URLs <br/>
of fixed size and transparent background, and renaming the generated image with the particular tag from the url itself.

####Examples :

Sample url is http://www.especiallyu.com/check/HL59HulI/50000000634/1.do <br/>
Generared QR code is renamed with the tag 50000000634.png by splitting the url.


### Construction

To generate the QR codes for the respective URLs,just pass the text file which contains the url. <br/>
The File should contain one url per line <br/>

######qrcodegen qri = new qrcodegen(); <br/>
######qri.createImage("filename.txt"); <br/>

## Motivation 
Generating QR code from URL using existing Qrcode generating websites usually took 3 minutes to generate and download 
and rename the image with the respective tag from the given url. This would take hours and more man power to generate 
if the url numbers are in 4k-5k. 

## Tests

Directory path should be mentioned in <br/> 
######FILE_PATH = "directory-path"; 
which should exclude the filename 

######IMG_COUNT = 5; 
This will force to store the 5 QR images in one directory in the given file-path.
Directory name is created by the method create_directory()

## API Reference

Using ZXing ("Zebra Crossing") API for QR code processing in Java. 
Original credit for QR code gereration goes to
http://www.javacodegeeks.com/2012/10/generate-qr-code-image-from-java-program.html

## Result
QR image of size 16-18KB of pixel size 1000x1000


