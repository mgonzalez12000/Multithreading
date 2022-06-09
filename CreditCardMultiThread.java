// Luhns algorithm using a multithreading implementation with the Thread API
import java.util.Scanner;

public class CreditCardMultiThread extends Thread {
	static int sum = 0;

	public static void main (String[] args) {

		System.out.print("Enter a credit card number: ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String creditCardNumber = scanner.nextLine();


		// Thread runs job to validate inputted string by user.
		Thread thread1 = new Thread(new Runnable() {  
			@Override
			public void run() {
				if (creditCardNumber.length() >= 13 && creditCardNumber.length() <= 19) {
					System.out.print("Card number length is correct...");
				}
				else {
					System.out.print("ERROR: card number is not between 13 and 19, ");
				}
			}
		});
		thread1.start();


		// Thread runs job to iterate through the odd indexes of the inputted string, and convert such string to int types
		// If the int is greater than 9, it will subtract 9 to get the sum of each digit in the number
		// These int types are then multiplied by 2 and adds to the sum.
		Thread thread2 = new Thread(new Runnable() {  
			@Override
			public void run() {
				for (int i = creditCardNumber.length() - 2; i >= 0; i = i - 2) {
					int digit = Integer.parseInt(creditCardNumber.substring(i, i + 1));
					digit = digit * 2;
					if (digit > 9) {
						digit = digit - 9;
					}
					sum += digit;
				}
			}
		});
		thread2.start();


		// Thread runs job to iterate through the even index of the inputted string, convert the string types to ints, and adds the ints to the sum.
		Thread thread3 = new Thread(new Runnable() {  
			@Override
			public void run() {
				for (int i = creditCardNumber.length() - 1; i >= 0; i = i - 2) {
					int digit = Integer.parseInt(creditCardNumber.substring(i, i + 1));
					sum += digit;
				}
			}
		});
		thread3.start();


		// Thread runs rob to validate if the sum has 0 remainder. If so, it identifies the card distributor by checking the first index of the inputted string.
		// Once the the character at the first index is verified, it prints the card distributor onto the console.
		Thread thread4 = new Thread(new Runnable() {  
			@Override
			public void run() {
				if (sum % 10 == 0) {
					System.out.print("credit card is VALID");
					if (creditCardNumber.charAt(0) == '3') {
						System.out.println(" and belongs to AMERICAN EXPRESS cards");
					}
					else if (creditCardNumber.charAt(0) == '6') {
						System.out.println(" and belongs to DISCOVER cards");
					}
					else if (creditCardNumber.charAt(0) == '5') {
						System.out.println(" and belongs to MASTER cards");
					}
					else if (creditCardNumber.charAt(0) == '4') {
						System.out.println(" and belongs to VISA cards");
					}
				}
				else {
					System.out.println("credit card is NOT VALID");
				}
			}
		});
		thread4.start();


		//Synchornous threading
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}