/*
 * Francine Vo
 * UIN 01253035
 */
import java.util.*;

public class CarMain {

	public static void main(String[] args) {
		// Create a Car ArrayList to add cars
		ArrayList<Car> cars = new ArrayList<>();

		try (Scanner in = new Scanner(System.in)) {
			Car car = new Car();
			int answer;

			// Use a loop to allow the user to continue adding or printing cars
			// until the user exits the program
			do {
				System.out.println("1. Print Cars\t2. Add Cars\t3. Exit");
				answer = in.nextInt();
				if (answer == 2) {
					car.menu(in);
					cars.add(new Car(car.getVin(), car.getLocation(),
							car.getMake(), car.getModel(), car.getYear(),
							car.getColor(), car.getType(), car.getE(),
							car.getT(), car.getB(), car.getTm()));
				}

				if (answer == 1) {
					for (Car c : cars)
						System.out.println(c);
				}
			} while (answer != 3);
		}
	}
}
