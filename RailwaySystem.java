import java.util.*;

public class RailwaySystem {

    static Scanner sc = new Scanner(System.in);

    static LinkedHashMap<String, Train> trains = 
        new LinkedHashMap<>();

    static HashMap<Integer, Platform> platforms =
            new HashMap<>();

    static HashMap<Integer, Reservation> reservations =
            new HashMap<>();

    static int nextPassengerId = 1;
    static int nextReservationId = 1;


    public static void main(String[] args) {

        initializeData();

        int choice;

        do {

            System.out.println("\n==========================");
            System.out.println("CENTRAL RAILWAY STATION");
            System.out.println("==========================");

            System.out.println("1. View Trains");
            System.out.println("2. View Platforms");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View Train Details");
            System.out.println("6. View Waiting List");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewTrains();
                    break;

                case 2:
                    viewPlatforms();
                    break;

                case 3:
                    bookTicket();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    viewTrainDetails();
                    break;

                case 6:
                    viewWaitingList();
                    break;

                case 7:
                    System.out.println("Program Closed.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);
    }


    // ==============================
    // INITIAL DATA
    // ==============================

    static void initializeData() {


    // =========================
    // ADD 6 TRAINS
    // =========================

    trains.put("T101", new Train("T101", "Kongu Express", 10));
    trains.put("T102", new Train("T102", "Salem Express", 10));
    trains.put("T103", new Train("T103", "Erode Passenger", 10));
    trains.put("T104", new Train("T104", "Chennai Express", 10));
    trains.put("T105", new Train("T105", "Coimbatore Special", 10));
    trains.put("T106", new Train("T106", "Night Express", 10));


    // =========================
    // ADD 5 DEFAULT BOOKINGS
    // FOR EACH TRAIN
    // =========================

    for (Train train : trains.values()) {

        for (int i = 0; i < 5; i++) {

            Passenger passenger = new Passenger(
                    "P" + nextPassengerId,
                    "Passenger " + nextPassengerId);

            nextPassengerId++;

            boolean booked = train.bookTicket(passenger);

            if (booked) {

                Reservation reservation = new Reservation(
                        nextReservationId,
                        passenger,
                        train.getTrainId());

                reservations.put(
                        nextReservationId,
                        reservation);

                nextReservationId++;
            }
        }
    }


    // =========================
    // ADD 3 PLATFORMS
    // =========================

    platforms.put(1, new Platform(1, 200));
    platforms.put(2, new Platform(2, 280));
    platforms.put(3, new Platform(3, 350));


    System.out.println("Railway System Initialized Successfully.");

    System.out.println(
            "6 Trains, 3 Platforms and 30 Default Bookings Added.");
}




    // ==============================
    // 1. VIEW TRAINS
    // ==============================

    static void viewTrains() {

        System.out.println("\n--- TRAIN LIST ---");

        for (Train train : trains.values()) {

            System.out.println(
                    train.getTrainId()
                            + " - "
                            + train.getTrainName());
        }
    }


    // ==============================
    // 2. VIEW PLATFORMS
    // ==============================

    static void viewPlatforms() {

        System.out.println("\n--- PLATFORM LIST ---");

        for (Platform platform : platforms.values()) {

            System.out.println(
                    "Platform "
                            + platform.getPlatformId()
                            + " | Length: "
                            + platform.getPlatformLength()
                            + " | Occupied: "
                            + platform.isOccupied());
        }
    }


    // ==============================
    // 3. BOOK TICKET
    // ==============================

    static void bookTicket() {

        sc.nextLine();

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Train ID: ");
        String trainId = sc.nextLine();

        Train train = trains.get(trainId);

        if (train == null) {

            System.out.println("Train not found.");

            return;
        }


        Passenger passenger =
                new Passenger(
                        "P" + nextPassengerId,
                        name);

        nextPassengerId++;


        boolean booked =
                train.bookTicket(passenger);


        if (booked) {

            Reservation reservation =
                    new Reservation(
                            nextReservationId,
                            passenger,
                            trainId);

            reservations.put(
                    nextReservationId,
                    reservation);

            System.out.println(
                    "Booking Confirmed!");

            System.out.println(
                    "Reservation ID: "
                            + nextReservationId);

            nextReservationId++;

        } else {

            System.out.println(
                    "Train is full.");

            System.out.println(
                    "Passenger added to Waiting List.");
        }
    }


    // ==============================
    // 4. CANCEL BOOKING
    // ==============================

    static void cancelBooking() {

        System.out.print(
                "Enter Reservation ID: ");

        int reservationId = sc.nextInt();

        Reservation reservation =
                reservations.get(reservationId);


        if (reservation == null
                || !reservation.isActive()) {

            System.out.println(
                    "Reservation not found.");

            return;
        }


        Train train =
                trains.get(
                        reservation.getTrainId());


        reservation.cancel();

        Passenger promoted =
                train.cancelTicket();


        System.out.println(
                "Booking Cancelled Successfully.");


        if (promoted != null) {

            System.out.println(
                    "Waiting Passenger Promoted: "
                            + promoted.getName());
        }
    }


    // ==============================
    // 5. VIEW TRAIN DETAILS
    // ==============================

    static void viewTrainDetails() {

        System.out.print("Enter Train ID: ");

        String trainId = sc.next();

        Train train = trains.get(trainId);


        if (train == null) {

            System.out.println("Train not found.");

            return;
        }


        System.out.println(
                "\n--- TRAIN DETAILS ---");

        System.out.println(
                "Train ID: "
                        + train.getTrainId());

        System.out.println(
                "Train Name: "
                        + train.getTrainName());

        System.out.println(
                "Total Seats: "
                        + train.getTotalSeats());

        System.out.println(
                "Available Seats: "
                        + train.getAvailableSeats());

        System.out.println(
                "Waiting Passengers: "
                        + train.getWaitingList().size());
    }


    // ==============================
    // 6. VIEW WAITING LIST
    // ==============================

    static void viewWaitingList() {

        System.out.print("Enter Train ID: ");

        String trainId = sc.next();

        Train train = trains.get(trainId);


        if (train == null) {

            System.out.println("Train not found.");

            return;
        }


        System.out.println(
                "\n--- WAITING LIST ---");


        if (train.getWaitingList().isEmpty()) {

            System.out.println(
                    "Waiting List is Empty.");

            return;
        }


        for (Passenger passenger :
                train.getWaitingList()) {

            System.out.println(
                    passenger.getPassengerId()
                            + " - "
                            + passenger.getName());
        }
    }
}