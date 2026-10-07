import java.util.*;

public class RailwaySystem {

    static Scanner sc = new Scanner(System.in);

    static LinkedHashMap<String, Train> trains = 
        new LinkedHashMap<>();

    static HashMap<Integer, Platform> platforms =
            new HashMap<>();

    static HashMap<Integer, Reservation> reservations =
            new HashMap<>();
            static ArrayList<PlatformRequest> platformRequests =
        new ArrayList<>();

static ArrayList<PlatformAllocation> allocations =
        new ArrayList<>();

static int nextRequestOrder = 1;

static final int CLEARANCE_BUFFER = 10;

    static int nextPassengerId = 1;
    static int nextReservationId = 1;


    public static void main(String[] args) {

        initializeData();

        int choice;

        do {

            System.out.println("\n==========================");
            System.out.println("PAYANAM RAILWAY STATION");
            System.out.println("==========================");

            System.out.println("1. View Trains");
            System.out.println("2. View Platforms");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View Train Details");
            System.out.println("6. View Waiting List");
System.out.println("7. Request Platform");
System.out.println("8. Allocate Platforms");
System.out.println("9. View Platform Allocations");
System.out.println("10. Exit");

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
        requestPlatform();
        break;

    case 8:
        allocatePlatforms();
        break;

    case 9:
        viewPlatformAllocations();
        break;

    case 10:
        System.out.println("Program Closed.");
        break;

    default:
        System.out.println("Invalid choice.");
}

        } while (choice != 10);
    }


    

    static void initializeData() {


trains.put("T101",
        new Train("T101", "Kongu Express",
                10, 100, 130, 180, 2));

trains.put("T102",
        new Train("T102", "Salem Express",
                10, 140, 170, 220, 3));

trains.put("T103",
        new Train("T103", "Erode Passenger",
                10, 180, 210, 160, 1));

trains.put("T104",
        new Train("T104", "Chennai Express",
                10, 220, 260, 300, 4));

trains.put("T105",
        new Train("T105", "Coimbatore Special",
                10, 280, 310, 200, 2));

trains.put("T106",
        new Train("T106", "Night Express",
                10, 330, 360, 250, 3));

    platforms.put(1, new Platform(1, 200));
    platforms.put(2, new Platform(2, 280));
    platforms.put(3, new Platform(3, 350));


    System.out.println("Railway System Initialized Successfully.");

    System.out.println(
            "6 Trains, 3 Platforms and 30 Default Bookings Added.");
}




    



static void viewTrains() {

    System.out.println("\n========== TRAIN DETAILS ==========");

    for (Train train : trains.values()) {

        System.out.println("\n----------------------------------");

        System.out.println("Train ID       : "
                + train.getTrainId());

        System.out.println("Train Name     : "
                + train.getTrainName());

        System.out.println("Arrival Time   : "
                + train.getArrivalTime());

        System.out.println("Departure Time : "
                + train.getDepartureTime());

        System.out.println("Train Length   : "
                + train.getTrainLength());

        System.out.println("Priority       : "
                + train.getTrainPriority());

        System.out.println("Available Seats: "
                + train.getAvailableSeats()
                + "/" + train.getTotalSeats());

        System.out.println("----------------------------------");
    }
}



   

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
        "Reservation ID: PNR"
                + String.format("%06d", nextReservationId));


            nextReservationId++;

        } else {

            System.out.println(
                    "Train is full.");

            System.out.println(
                    "Passenger added to Waiting List.");
        }
    }


  

  static void cancelBooking() {

    System.out.print("Enter Reservation ID: ");
    String pnr = sc.next();

    if (!pnr.startsWith("PNR")) {
        System.out.println(
                "Invalid Reservation ID. Use format like PNR000001.");
        return;
    }

    try {

        int reservationId =
                Integer.parseInt(pnr.substring(3));

        Reservation reservation =
                reservations.get(reservationId);

        if (reservation == null || !reservation.isActive()) {

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

    } catch (NumberFormatException e) {

        System.out.println(
                "Invalid Reservation ID. Use format like PNR000001.");
    }
}


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

    System.out.println(
            "Arrival Time: "
                    + train.getArrivalTime());

    System.out.println(
            "Departure Time: "
                    + train.getDepartureTime());

    System.out.println(
            "Train Length: "
                    + train.getTrainLength());

    System.out.println(
            "Train Priority: "
                    + train.getTrainPriority());

}


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
    }static void requestPlatform() {

    System.out.print("Enter Train ID: ");

    String trainId = sc.next();

    Train train = trains.get(trainId);

    if (train == null) {

        System.out.println("Train not found.");

        return;
    }

    System.out.println("\nRequest Type:");

    System.out.println("1. Emergency");
    System.out.println("2. Connection");
    System.out.println("3. Scheduled");

    System.out.print("Enter request type: ");

    int type = sc.nextInt();

    if (type < 1 || type > 3) {

        System.out.println(
                "Invalid request type.");

        return;
    }

    PlatformRequest request =
            new PlatformRequest(
                    train,
                    type,
                    nextRequestOrder);

    nextRequestOrder++;

    platformRequests.add(request);

    System.out.println(
            "Platform request added successfully.");

    System.out.println(
            "Train: "
                    + train.getTrainId());

    System.out.println(
            "Request Type: "
                    + getRequestTypeName(type));
}
static String getRequestTypeName(int type) {

    if (type == 1) {
        return "Emergency";
    }

    if (type == 2) {
        return "Connection";
    }

    return "Scheduled";
}
static void allocatePlatforms() {

    if (platformRequests.isEmpty()) {

        System.out.println(
                "No platform requests available.");

        return;
    }

    // Sort by priority first.
    // If priority is same, use request order.
    platformRequests.sort(
            Comparator
                    .comparingInt(
                            PlatformRequest::getPriority)
                    .thenComparingInt(
                            PlatformRequest::getArrivalOrder));

    for (PlatformRequest request :
            platformRequests) {

        Train train = request.getTrain();

        boolean allocated = false;

        for (Platform platform :
                platforms.values()) {

            // Check platform length
            if (platform.getPlatformLength()
                    < train.getTrainLength()) {

                continue;
            }

            int startTime =
                    train.getArrivalTime();

            int endTime =
                    train.getDepartureTime()
                            + CLEARANCE_BUFFER;

            // Check time conflict
            if (hasPlatformConflict(
                    platform.getPlatformId(),
                    startTime,
                    endTime)) {

                continue;
            }

            PlatformAllocation allocation =
                    new PlatformAllocation(
                            train.getTrainId(),
                            platform.getPlatformId(),
                            startTime,
                            endTime);

            allocations.add(allocation);

            System.out.println(
                    "\nPlatform Allocated Successfully!");

            System.out.println(
                    "Train: "
                            + train.getTrainId());

            System.out.println(
                    "Platform: "
                            + platform.getPlatformId());

            System.out.println(
                    "Occupied From: "
                            + startTime);

            System.out.println(
                    "Occupied Until: "
                            + endTime);

            allocated = true;

            break;
        }

        if (!allocated) {

            System.out.println(
                    "\nPlatform Allocation Failed.");

            System.out.println(
                    "Train: "
                            + train.getTrainId());

            System.out.println(
                    "No suitable platform available.");
        }
    }

    // Requests have now been processed
    platformRequests.clear();
}
static boolean hasPlatformConflict(
        int platformId,
        int newStart,
        int newEnd) {

    for (PlatformAllocation allocation :
            allocations) {

        if (allocation.getPlatformId()
                == platformId) {

            if (allocation.overlaps(
                    newStart,
                    newEnd)) {

                return true;
            }
        }
    }

    return false;
}
static void viewPlatformAllocations() {

    System.out.println(
            "\n--- PLATFORM ALLOCATIONS ---");

    if (allocations.isEmpty()) {

        System.out.println(
                "No platforms allocated.");

        return;
    }

    for (PlatformAllocation allocation :
            allocations) {

        System.out.println(
                "Train: "
                        + allocation.getTrainId()
                        + " | Platform: "
                        + allocation.getPlatformId()
                        + " | Start: "
                        + allocation.getStartTime()
                        + " | End: "
                        + allocation.getEndTime());
    }
}
}