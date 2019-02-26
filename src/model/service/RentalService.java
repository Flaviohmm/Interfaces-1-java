package model.service;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double pricePerHour;
	private Double pricePerDay;

	private BrazilTaxService taxService;

	public RentalService() {

	}

	public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public BrazilTaxService getTaxService() {
		return taxService;
	}

	public void setTaxService(BrazilTaxService taxService) {
		this.taxService = taxService;
	}

	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double time = (double) (t2 - t1) / 1000 / 60 / 60;

		double basicPayment;
		if (time <= 12) {
			basicPayment = pricePerHour * Math.ceil(time);
		} else {
			basicPayment = pricePerDay * Math.ceil(time / 24);
		}

		double tax = taxService.tax(basicPayment);
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
}
