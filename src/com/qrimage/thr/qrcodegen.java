package com.qrimage.thr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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

	private static final String FILE_PATH = "I:\\QRImage\\";
	private static final int IMG_SIZE = 1000;
	private static final String FILE_TYPE = "png";
	private static final int IMG_COUNT = 21;

	public void createImage() {

		// public void createImage(){

		int dircount1 = 1;
		int dircount2 = IMG_COUNT;

		try {

			FileInputStream fis = new FileInputStream(FILE_PATH
					+ "qrcodelink.txt");
			// Construct BufferedReader from InputStreamReader
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			int count = 0;
			int j = 1;
			String line = null;

			dircount1 = 1;
			dircount2 = IMG_COUNT;

			String temp1 = String.format(new DecimalFormat("0000")
					.format(dircount1));
			String temp2 = String.format(new DecimalFormat("0000")
					.format(dircount2));

			String dir = temp1 + "-" + temp2;
			new File(FILE_PATH + dir).mkdir();

			while ((line = br.readLine()) != null) {

				System.out.println("\n" + line);
				count++;

				String[] split = line.split("/");
				String rename = split[5] + ".png";
				// System.out.println(split[5]+"rename:"+rename);

				String filePath = FILE_PATH + dir + "\\" + rename;
				System.out.println(filePath);

				File qrFile = new File(filePath);
				createQRImage(qrFile, line, IMG_SIZE, FILE_TYPE);
				
				// create new directory after certain number images stored in that respective directory
				
				if (count == IMG_COUNT) {

					dircount1 = dircount1 + IMG_COUNT;
					dircount2 = dircount2 + IMG_COUNT;
					temp1 = String.format(new DecimalFormat("0000").format(dircount1));
					temp2 = String.format(new DecimalFormat("0000").format(dircount2));
					dir = temp1 + "-" + temp2;
					new File(FILE_PATH + dir).mkdir();
					System.out.println("\ndir name: " + dir);
					count = 0;

				}
			}

			br.close();
			System.out.println("DONE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public static void main(String[] args) throws WriterException, IOException {

		qrcodegen qri = new qrcodegen();
		qri.createImage();
	}
}