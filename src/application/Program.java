package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat simDatFor = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String model = scanner.nextLine();
		System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
		Date start = simDatFor.parse(scanner.nextLine());
		System.out.print("Return (dd/MM/yyyy hh:mm): ");
		Date finish = simDatFor.parse(scanner.nextLine());
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(model));
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = scanner.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = scanner.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		System.out.println("INVOICE:");
		
		rentalService.processInvoice(carRental);
		
		System.out.println("Basic Payment: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f", carRental.getInvoice().totalPayment()));
		
		scanner.close();
	}

}
