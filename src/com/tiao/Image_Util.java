package com.tiao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image_Util {

	public int getRgb(String image, String image2) {
		int distance = 0;
		int[] rgb = new int[3];
		int[] last_rgb = new int[3];
		File file = new File(image);
		File file2 = new File(image2);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		// System.out.println("width=" + width + ",height=" + height + ".");
		// System.out.println("minx=" + minx + ",miniy=" + miny + ".");

		// -----------------------------
		int scan_start_y = 0;
		for (int i = height / 4; i < height * 3 / 4; i += 50) {
			int last_pixel = bi.getRGB(0, i);
			last_rgb[0] = (last_pixel & 0xff0000) >> 16;
			last_rgb[1] = (last_pixel & 0xff00) >> 8;
			last_rgb[2] = (last_pixel & 0xff);

			for (int j = 1; j < width; j++) {
				int pixel = bi.getRGB(j, i);
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				if (rgb[0] != last_rgb[0] || rgb[1] != last_rgb[1]
						|| rgb[2] != last_rgb[2]) {
					scan_start_y = i - 50;
					break;
				}

			}
			if (scan_start_y > 0) {
				break;
			}
		}
		// System.out.println("scan_start_y:" + scan_start_y);
		// ----------------------------------------------------
		int scan_x_border = 5;
		int piece_x_sum = 0;
		int piece_x_c = 0;
		int piece_y_max = 0;
		for (int i = scan_start_y; i < height * 3 / 4; i++) {

			for (int j = scan_x_border; j < width - scan_x_border; j++) {
				int pixel = bi.getRGB(j, i);
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				if ((50 < rgb[0] && rgb[0] < 60)
						&& (53 < rgb[1] && rgb[1] < 60)
						&& (95 < rgb[2] && rgb[2] < 110)) {
					piece_x_sum += j;
					piece_x_c += 1;
					if (i > piece_y_max) {
						piece_y_max = i;
					} else {
						piece_y_max = piece_y_max;
					}
				}
			}
		}
		int piece_x = piece_x_sum / piece_x_c;
		int piece_y = piece_y_max;
		// System.out.println("piece_x==" + piece_x + "    piece_y===" +
		// piece_y);
		// --------------------------------------------------------------------
		for (int i = piece_x - 10; i < piece_x + 10; i++) {
			for (int j = piece_y - 10; j < piece_y + 10; j++) {
				bi.setRGB(i, j, 0xFF0000);
			}
		}
		// ------------------------------------------------------------------
		//
		int board_x_start = 0;
		int board_x_end = 0;
		if (piece_x < width / 2) {
			board_x_start = piece_x;
			board_x_end = width;
		} else {
			board_x_start = 0;
			board_x_end = piece_x;
		}

		int board_x = 0;
		int board_y = 0;
		int board_x_sum = 0;
		int board_x_c = 0;
		for (int i = height / 4; i < height * 3 / 4; i++) {
			int last_pixel = bi.getRGB(0, i);
			last_rgb[0] = (last_pixel & 0xff0000) >> 16;
			last_rgb[1] = (last_pixel & 0xff00) >> 8;
			last_rgb[2] = (last_pixel & 0xff);
			if (board_x > 0 && board_y > 0) {
				break;
			}

			for (int j = board_x_start; j < board_x_end; j++) {
				int pixel = bi.getRGB(j, i);
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				// if (Math.abs(j - piece_x) < width)
				// continue;

				if (Math.abs(rgb[0] - last_rgb[0])
						+ Math.abs(rgb[1] - last_rgb[1])
						+ Math.abs(rgb[2] - last_rgb[2]) > 10) {
					board_x_sum += j;
					board_x_c += 1;
					if (board_y < i) {
						board_y = i + 60;
					} else {
						board_y = board_y;
					}
				}
				if (board_x_sum > 0) {
					board_x = board_x_sum / board_x_c;
				}
				last_pixel = bi.getRGB(board_x, i);
			}

		}
		// System.out.println("border_x_sum:" + board_x_sum);
		// System.out.println("border_x:" + board_x);
		// System.out.println("border_y:" + board_y);

		for (int i = board_x - 10; i < board_x + 10; i++) {
			for (int j = board_y - 10; j < board_y + 10; j++) {
				bi.setRGB(i, j, 0xFF0000);
			}
		}
		// ------------------------------------------

		try {
			ImageIO.write(bi, "png", file2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
		distance = (int) Math.sqrt((board_x - piece_x) * (board_x - piece_x)
				+ (board_y - piece_y) * (board_y - piece_y));

		// System.out.println("distance:" + distance);

		return distance;

	}

	public static void main(String[] args) {
		new Image_Util().getRgb("G:\\MyWorkSpace\\ÌøÒ»Ìø¸¨Öú\\pic\\tiao.png",
				"G:\\MyWorkSpace\\ÌøÒ»Ìø¸¨Öú\\pic\\tiao2.png");
	}

}
