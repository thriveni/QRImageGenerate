package com.qrimage.thr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class qrcodegen {

	private static final String FILE_PATH = "directory-path";
	private static final int IMG_SIZE = 1000;
	private static final String FILE_TYPE = "png";
	private static final int IMG_COUNT = 5;
	private static int dirname_start = 1;
	private static int dirname_end = IMG_COUNT;
	private static String start_name = null;
	private static String end_name = null;
	private static String filePath = null;
	private static String[] splitstr = null;
	private static String rename = null;

	public void createImage(String filename) throws FileNotFoundException {

		FileInputStream fis = new FileInputStream(FILE_PATH + filename);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
			int count = 0;
			String line;
			String dir = create_directory();

			while ((line = br.readLine()) != null) {
				count++;
				System.out.println("\n" + line);
				splitstr = line.split("/");
				rename = splitstr[5] + ".png";
				// System.out.println(split[5]+"rename:"+rename);

				filePath = FILE_PATH + dir + "\\" + rename;
				System.out.println(filePath);
				File qrFile = new File(filePath);
				// creating QR image by sending url, file type and file path and the size of the image
				createQRImage(qrFile, line, IMG_SIZE, FILE_TYPE);

				if (count == IMG_COUNT) {
					// create new directory after certain number images stored
					// in that respective directory
					dir = create_directory();
					count = 0;
				}
			}
			System.out.println("DONE");
		} catch (IOException | WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String create_directory() {

		start_name = String.format(new DecimalFormat("0000").format(dirname_start));
		end_name = String.format(new DecimalFormat("0000").format(dirname_end));
		// creating the directory name (0001-0005)
		String dir = start_name + "-" + end_name;
		System.out.println("dir name:" + dir);
		new File(FILE_PATH + dir).mkdir();
		dirname_start = dirname_start + IMG_COUNT;
		dirname_end = dirname_end + IMG_COUNT;
		return dir;
	}

	private static void createQRImage(File qrFile, String qrCodeText, int size,
			String fileType)

	throws WriterException, IOException {

		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable hintMap = new Hashtable();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// hintMap.put(EncodeHintType.CHARACTER_SET, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
				BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();

		// BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
		// BufferedImage.TYPE_INT_RGB);

		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
				BufferedImage.TYPE_4BYTE_ABGR);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		// graphics.setColor(Color.WHITE);
		// to get transparent background of the image
		graphics.setColor(new Color(1f, 0f, 0f, 0f));
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}

	public static void main(String... args) throws InterruptedException , WriterException ,FileNotFoundException {

		qrcodegen qri = new qrcodegen();
		
		if (args[0]==null){
			
			System.out.println("Proper Usage is: URL text filename (qrcodelink.txt)");
            		System.exit(0);
		}else{
			//qri.createImage("qrcodelink.txt");
			qri.createImage(args[0]);
		}
		
		
		
		
	}
}
