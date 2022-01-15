/**
 * IPR | Graphics - Exercise : Images
 * 
 * This is the handout and the exercise notebook for the IPR Graphics Exercise.
 * You have to solve 3 tasks in Python and Java summing to 12 points.
 * 
 * @Deadline: **23.12.2021, 10:00**
 * 
 * @Author [Todo: Student name]
 * @ID [Todo: Student id]
 * 
 * 
 * 
 * @Contact: If you have any questions, please contact the tutors or the
 *           instructor (David Schedl, email: david.schedl@fh-hagenberg.at)
 * 
 * @Notes:
 * 
 *         <ul>
 *         <li>Hand in the solution via Github Classroom on time.
 *         <li>You can delete code that is not related to the tasks.
 *         <li>Ensure that you added your name and matriculation number to the
 *         top of the files and in any additional source files.
 *         <li>For this exercise, you also need to hand in a Python notebook!
 *         </ul>
 */
public class GbImages {

	public static void main(String[] args) {

		/*
		 * Pixels of an image in Java are encoded as int values.
		 */
		{
			final int T = 0xffffff; // white
			// zero is black

			int[][] pixels_bw = {
					// col/u --->
					{ 0, 0, T, T, T, T, 0, 0 }, // row/v 0
					{ 0, T, 0, 0, 0, 0, T, 0 }, // row 1
					{ T, 0, T, 0, 0, T, 0, T }, // row 2
					{ T, 0, 0, 0, 0, 0, 0, T }, // row 3
					{ T, 0, T, 0, 0, T, 0, T }, // row 4
					{ T, 0, 0, T, T, 0, 0, T }, // row 5
					{ 0, T, 0, 0, 0, 0, T, 0 }, // row 6
					{ 0, 0, T, T, T, T, 0, 0 } // row 7
			};
			// Make sure that this file (`.java`) is in the same directory as the `IPR.java`
			// file, to get access to the `IPR.imageShow` function for displaying images.
			IPR.imageShow("Smiley image", pixels_bw);
		}

		/*
		 * Encode an RGB pixel in one 32-bit integer value
		 * 
		 * In Java RGB pixels are encoded in 32-bit integers for efficiency. Let's look
		 * at an example below.
		 */
		{
			int r = 0xFF; // red
			int g = 0x64; // green
			int b = 0x0B; // blue
			int rgb = 0xFF640B; // 24-bit representation of the Color

			// print in a decimal, hexadecimal and binary number system
			System.out.println("red: \t" + r + " \t0x" + Integer.toHexString(r) + " \t" + Integer.toBinaryString(r));
			System.out.println("green: \t" + g + " \t0x" + Integer.toHexString(g) + " \t" + Integer.toBinaryString(g));
			System.out.println("blue: \t" + b + " \t0x" + Integer.toHexString(b) + " \t" + Integer.toBinaryString(b));
			System.out.println(
					"RGB: \t" + rgb + " \t0x" + Integer.toHexString(rgb) + " \t" + Integer.toBinaryString(rgb));
			System.out.println("-------------------");

		}
		/*
		 * Bitwise Operations
		 * 
		 * Java has built-in functions to perform bitwise operations. You'll see a few
		 * examples below.
		 */
		{
			int a = 0b10011100;
			int b = 0b00110100;
			System.out.println("a \t" + Integer.toBinaryString(a));
			System.out.println("b \t" + Integer.toBinaryString(b));
			int bit_or = a | b; // bitwise OR (|)
			int bit_and = a & b; // bitwise AND (&)
			System.out.println("OR \t" + Integer.toBinaryString(bit_or));
			System.out.println("AND \t" + Integer.toBinaryString(bit_and));
			System.out.println("-------------------");

			int bit = 0b100111;
			int bit_left = bit << 1;// bitwise shift left (<<)
			int bit_right = bit_left >> 1; // bitwise shift right (>>)
			System.out.println("bit \t" + Integer.toBinaryString(bit));
			System.out.println("<<1 \t" + Integer.toBinaryString(bit_left));
			System.out.println(">>1 \t" + Integer.toBinaryString(bit_right));
			System.out.println("-------------------");
		}

		// Todo: Task 1
		/*
		 * Task 01 - Java : Bitwise functions to set/get pixels and to retrieve colors
		 * from an integer pixel [3 points]
		 * 
		 * 
		 * * Implement a function `getPixel` that takes u,v coordinates and returns the
		 * corresponding pixel value.
		 * 
		 * * Implement a function `setPixel` that takes u,v coordinates and a pixel
		 * value and sets the corresponding pixel value in the image.
		 * 
		 * * Implement functions `getRed`, `getGreen`, `getBlue` that takes an integer
		 * (24-bit) and decomposes it into 8-bit color channels. Use bitwise operations.
		 * 
		 * 
		 * Make sure that you apply a boundary check to the u,v coordinates and handle
		 * invalid coordinates gracefully.
		 */
		{
			int r, g, b, rgb;
			System.out.println("--- Task 01 ---");
			int[][] img = makeImage(8, 6, 128, 0, 255); // blueish color
			r = getRed(getPixel(img, 0, 0));
			g = getGreen(getPixel(img, 0, 0));
			b = getBlue(getPixel(img, 0, 0));
			System.out.println(r + ", " + g + ", " + b); // should be [128,0,255]!
			System.out.println(getPixel(img, 4, 10)); // invalid index. Return value should be -1!

			setPixel(img, 5, 4, 0, 100, 0);
			rgb = getPixel(img, 5, 4); // should be [0,100,0]
			r = getRed(rgb);
			g = getGreen(rgb);
			b = getBlue(rgb);
			System.out.println(r + ", " + g + ", " + b); // should be [0,100,0], now!
			setPixel(img, 10, 2, 0, 0, 0); // invalid index. should do nothing!
			IPR.imageShow("setPixel test", img);
			System.out.println("-------------------");
		}

		// Todo: Task 2
		/*
		 * Task 02 - Java : Implement a function to create a horizontal gradient image
		 * [2 points]
		 * 
		 * The function `makeGradientImage` creates an image of a specific width and
		 * height. The image contains a horizontal gradient (from left to right).
		 * `color1` is on the left side of the image, `color2` is on the right edge of
		 * the image. In-between pixels are interpolated linearly between the two colors
		 * (e.g., 50% of color1 and 50% of color2 in the center).
		 * 
		 * **Bonus:** [1/2 Extra-Point] The function can create a vertical and
		 * horizontal gradient. Use a boolean parameter to decide which gradient to
		 * create. Is there a way to implement a default parameter in Java?
		 */
		{
			var gradientImage = makeGradientImage(16, 3, 0, 0, 255, 255, 255, 0); // gradient from yellow to blue
			IPR.imageShow("gradient image", gradientImage);
		}

		// Todo: Task 3
		/*
		 * Task 03 - Java : Implement a function to create a checkerboard [2 points]
		 * 
		 * Implement the function `makeCheckerboardImage` with the parameters as defined
		 * below. The resulting image shows a checkerboard (the board you know from the
		 * game chess) and squares using `color1` and `color2` alternatingly.
		 * Additionally the size of the squares can be controlled by the parameter
		 * `checkerSize`. A square size of N means that one square is NxN pixels large.
		 */
		{
			var checkerboard = makeCheckerboardImage(16, 8, 0, 0, 255, 255, 255, 0, 1); // checkerboard yellow and blue
			IPR.imageShow("checkerboard yellow/blue", checkerboard);
			checkerboard = makeCheckerboardImage(16, 8, 0, 0, 0, 255, 255, 255, 4); // checkerboard balck and white with
																					// larger checkerboard squares
			IPR.imageShow("checkerboard black/white with large squares", checkerboard);
		}

	}

	/**
	 * Create a new image of the given width and height. The image is filled with a
	 * uniform color.
	 * 
	 * @param w     width of the image
	 * @param h     height of the image
	 * @param r,g,b red, green, blue components of the uniform color
	 * @return a new image with the given width and height and filled color
	 */
	public static int[][] makeImage(int w, int h, int r, int g, int b) {
		int[][] pixels = new int[h][w];
		for (int x = 0; x < w; ++x) {
			for (int y = 0; y < h; ++y) {
				setPixel(pixels, x, y, r, g, b);
			}
		}
		return pixels;
	}

	/**
	 * Sets the color of a pixel at coordinate u,v in an image
	 * 
	 * @param pixels the image
	 * @param u      pixel u/x coordinate
	 * @param v      pixel v/y coordinate
	 * @param r      red color component
	 * @param g      green color component
	 * @param b      blue color component
	 */
	public static void setPixel(int[][] image, int u, int v, int r, int g, int b) {
		// Todo: Task 1 - Implement this function
	}

	/**
	 * Get the color of a pixel at coordinate u,v in an image
	 * 
	 * @param pixels the image
	 * @param u      pixel u/x coordinate
	 * @param v      pixel v/y coordinate
	 * @return the color of the pixel at coordinate u,v
	 */
	public static int getPixel(int[][] image, int u, int v) {
		// Todo: Task 1 - Implement this function
		return -1;
	}

	/**
	 * Returns the red component in the range 0-255
	 * 
	 * @param rgb the packed rgb (32 bits)
	 * @return the red component.
	 */
	public static int getRed(int rgb) {
		// Todo: Task 1 - Implement this function
		return -1;
	}

	/**
	 * Returns the green component in the range 0-255
	 * 
	 * @param rgb the rgb value
	 * @return the green component.
	 */
	public static int getGreen(int rgb) {
		// Todo: Task 1 - Implement this function
		return -1;
	}

	/**
	 * Returns the blue component in the range 0-255
	 * 
	 * @param rgb the rgb value
	 * @return the blue component.
	 */
	public static int getBlue(int rgb) {
		// Todo: Task 1 - Implement this function
		return -1;
	}

	/**
	 * Create a new image of the given width and height. The image is filled with a
	 * gradient between color1 and color2.
	 * 
	 * @param w  width of the image
	 * @param h  height of the image
	 * @param r1 red component of color1
	 * @param g1 green component of color1
	 * @param b1 blue component of color1
	 * @param r2 red component of color2
	 * @param g2 green component of color2
	 * @param b2 blue component of color2
	 * @return a new image with the given width and height and filled with a
	 *         gradient between color1 and color2
	 */
	public static int[][] makeGradientImage(int w, int h, int r1, int g1, int b1, int r2, int g2, int b2) {
		int[][] pixels = makeImage(w, h, r1, g1, b1);
		// Todo: Task 2 - Implement this function
		return pixels;
	}

	/**
	 * Create a new checkerboard image of the given width and height. The squares of
	 * the checkerboard are pixels of colors color1 and color2 and the checkerSize
	 * determines the size (in pixels) of the squares.
	 * 
	 * @param w           width of the image
	 * @param h           height of the image
	 * @param r1          red component of color1
	 * @param g1          green component of color1
	 * @param b1          blue component of color1
	 * @param r2          red component of color2
	 * @param g2          green component of color2
	 * @param b2          blue component of color2
	 * @param checkerSize the size of the squares
	 * @return a new image with the given width and height and filled with a
	 *         gradient between color1 and color2
	 */
	public static int[][] makeCheckerboardImage(int w, int h, int r1, int g1, int b1, int r2, int g2, int b2,
			int checkerSize) {
		int[][] pixels = makeImage(w, h, r1, g1, b1);
		// Todo: Task 3 - Implement this function
		return pixels;
	}

}
