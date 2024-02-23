/*
 * Francine Vo
 * UIN 01253035
 */
import java.util.*;

public class Car {

	private String vin;
	private String location;
	private String make;
	private String model;
	private int year;
	private String color;
	private String type;
	
	// Use encapsulation to access the nested classes
	private Engine e = new Engine();
	private Wheel w = new Wheel();
	private Wheel.Tire t = w.new Tire();
	private Wheel.Brakes b = w.new Brakes();
	private Transmission tm = new Transmission();

	public Car(String vin, String location, String make, String model, int year,
			String color, String type, Car.Engine e, Car.Wheel.Tire t,
			Car.Wheel.Brakes b, Car.Transmission tm) {

		this.vin = vin;
		this.location = location;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.type = type;
		this.e = e;
		this.t = t;
		this.b = b;
		this.tm = tm;
	}

	public Car() {
		vin = "";
		location = "";
		make = "";
		model = "";
		year = 0;
		color = "";
		type = "";
	}

	public String toString() {

		String result = "VIN: " + vin + "\nBuild Location: " + location
				+ "\nYear: " + year + "\nType: " + type + "\nMake and Model: "
				+ make + " " + model + "\nColor: " + color + "\nEngine: "
				+ e.getLayout() + "\nCylinder(s): " + e.getCylinders()
				+ "\nTransmission: " + tm.getType() + "\nTires: " + t.getBrand()
				+ "@" + t.getRecommendedPSI() + ".psi" + "\nBrakes: "
				+ b.getBrand() + "Last Replaced at: " + b.getLastReplaced()
				+ "\n";

		return result;
	}

	public void getInfoFromVin() {
		System.out.println(getLocation() + " " + getMake() + " " + getType()
				+ " " + getYear());
	}
	// Create a method for option 2 in the menu, reading in the new inputs
	public void menu(Scanner in) {

		System.out.println("Enter the vin");
		setVin(in.next());
		getInfoFromVin();
		System.out.println(
				"Checking if any information was not found\nEnter the Model of the car");
		setModel(in.next());
		System.out.println("Enter the color of the car");
		setColor(in.next());

		System.out.println(
				"Enter the Brand and PSI Recommendation separated by a comma(,)");
		String[] brandPsi = in.next().split(",");

		t.setBrand(brandPsi[0]);
		t.setRecommendedPSI(brandPsi[1]);

		System.out.println(
				"Enter the Brand and last date(MM-DD-YYYY) of replacement separated by a comma(,)");
		String[] brandDate = in.next().split(",");
		b.setBrand(brandDate[0]);
		b.setLastReplaced(brandDate[1]);

	}

	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getLocation() {
		char[] vinArr = vin.toCharArray();
		switch (vinArr[0]) {
			case '1', '4', '5' :
				return "United States";
			case '2' :
				return "Canada";
			case '3' :
				// Inference 1: All Mexican vehicles are manual
				tm.setType("Manual");
				return "Mexico";
			case 'J' :
				return "Japan";
			case 'L' :
				return "China";
			case 'K' :
				return "Korea";
			default :
				return "Invalid VIN";
		}
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMake() {
		char[] vinArr = vin.toCharArray();
		switch (vinArr[1]) {
			case 'H' :
				return "Honda";
			case 'V' :
				return "Volkswagen";
			case 'G' :
				return "General Motors / Chevrolet";
			case 'T' :
				return "Toyota";
			case 'F' :
				return "Ford";
			case 'Y' :
				// Inference 2: All Teslas return an Electric layout
				e.setLayout("Electric");
				return "Tesla";
			default :
				return "Invalid VIN";
		}
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		// Inference 3: All Odysseys are manual
		if (model.equalsIgnoreCase("Odyssey"))
			tm.setType("Manual");
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		char[] vinArr = vin.toCharArray();
		int result = 0;
		String yearInput = "STVWXY123456789ABCDEFGHJKLMNPR";
		char[] yearChar = yearInput.toCharArray();
		for (int i = 0; i < yearChar.length; i++) {
			if (vinArr[9] == yearChar[i]) {
				result = 1995 + i;
			}
		}
		// Inference 4: Any sports car in 2005 returns 6 cylinders
		if (result == 2005 && type.equalsIgnoreCase("Sports"))
			e.setCylinders(6);
		// Inference 5: All Toyotas made after 2000 are hybrids
		if (result >= 2000 && make.equalsIgnoreCase("Toyota"))
			e.setLayout("Hybrid");
		return result;
	}

	public void setYear(int year) {
		this.year = year;
	}
	public String getColor() {
		// Inference 6: All pink cars have V12 engine
		if (color.equalsIgnoreCase("Pink")) {
			e.setLayout("V12 Engine");
			e.setCylinders(12);
		}
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		char[] vinArr = vin.toCharArray();
		if (vinArr[1] == 'H') {
			switch (vinArr[2]) {
				case 'L' :
					return "SUV";
				case 'M' :
					return "Sedan";
				case 'V' :
					return "Minivan";
				case '1' :
					return "Truck";
			}
		}

		if (vinArr[1] == 'V') {
			switch (vinArr[2]) {
				case 'G' :
					return "SUV";
				case 'W' :
					return "Sedan";
				case '2' :
					return "Minivan";
				case '3' :
					return "Truck";
			}
		}

		if (vinArr[1] == 'G') {
			switch (vinArr[2]) {
				case 'K' :
					return "SUV";
				case '0', '1' :
					return "Sedan";
				case 'S' :
					return "Sports";
				case 'V' :
					return "Minivan";
				case 'T' :
					return "Truck";
			}
		}
		if (vinArr[1] == 'T') {
			switch (vinArr[2]) {
				case 'E', 'L', '3' :
					return "SUV";
				case 'D', 'G', 'K', '1' :
					return "Sedan";
				case 'X' :
					return "Sports";
				case 'V' :
					return "Minivan";
				case 'T' :
					return "Truck";
			}
		}

		if (vinArr[1] == 'F') {
			switch (vinArr[2]) {
				case 'A' :
					// Inference 7: All Ford SUVs are Manual and have 6
					// Cylinders
					tm.setType("Manual");
					e.setCylinders(6);
					return "SUV";
				case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' :
					return "Sedan";
				case 'V' :
					return "Sports";
				case 'M' :
					return "Minivan";
				case 'T' :
					return "Truck";
			}
		}

		if (vinArr[1] == 'Y') {
			switch (vinArr[2]) {
				case 'A' :
					return "SUV";
				case 'J' :
					return "Sedan";
				case 'Z' :
					return "Sports";
				case '2' :
					return "Truck";
			}
		}
		return "Invalid";
	}
	public void setType(String type) {
		this.type = type;
	}
	public Engine getE() {
		return e;
	}

	public void setE(Engine e) {
		this.e = e;
	}

	public Wheel.Tire getT() {
		return t;
	}

	public void setT(Wheel.Tire t) {
		this.t = t;
	}

	public Wheel.Brakes getB() {
		return b;
	}

	public void setB(Wheel.Brakes b) {
		this.b = b;
	}

	public Transmission getTm() {
		return tm;
	}

	public void setTm(Transmission tm) {
		this.tm = tm;
	}

	public class Engine {

		private String layout;
		private int cylinders;

		public Engine() {
			layout = "V6 Engine";
			cylinders = 0;
		}

		public Engine(String layout, int cylinders) {
			this.layout = layout;
			this.cylinders = cylinders;
		}

		public String getLayout() {
			return layout;
		}
		public void setLayout(String layout) {
			this.layout = layout;
		}
		public int getCylinders() {
			return cylinders;
		}
		public void setCylinders(int cylinders) {
			this.cylinders = cylinders;
		}

	}

	public class Transmission {
		private String type;

		public Transmission() {
			type = "Automatic";
		}

		public Transmission(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	public class Wheel {
		// Create Tire and Brakes classes inside of the Wheel class
		public class Tire {
			private String brand;
			private String recommendedPSI;

			public Tire() {
				brand = "N/A";
				recommendedPSI = "0";
			}

			public Tire(String brand, String recommendedPSI) {
				this.brand = brand;
				this.recommendedPSI = recommendedPSI;
			}
			public String getBrand() {
				return brand;
			}
			public void setBrand(String brand) {
				this.brand = brand;
			}
			public String getRecommendedPSI() {
				return recommendedPSI;
			}
			public void setRecommendedPSI(String recommendedPSI) {
				this.recommendedPSI = recommendedPSI;
			}
		}

		public class Brakes {
			private String brand;
			private String lastReplaced;

			public Brakes() {
				brand = "No";
				lastReplaced = "N/A";
			}

			public Brakes(String brand, String lastReplaced) {
				this.brand = brand;
				this.lastReplaced = lastReplaced;
			}
			public String getBrand() {
				return brand;
			}
			public void setBrand(String brand) {
				this.brand = brand;
			}
			public String getLastReplaced() {
				return lastReplaced;
			}
			public void setLastReplaced(String lastReplaced) {
				this.lastReplaced = lastReplaced;
			}

		}
	}
}
