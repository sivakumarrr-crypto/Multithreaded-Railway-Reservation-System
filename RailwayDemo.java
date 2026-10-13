java
class RailwayReservation {

    private boolean seatAvailable = true;

    // Synchronized booking method
    public synchronized void bookSeat(String passenger) {

        System.out.println(passenger
                           + " is trying to book the seat.");

        if (seatAvailable) {
            System.out.println("Seat is available for "
                               + passenger);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Booking interrupted.");
                return;
            }

            seatAvailable = false;

            System.out.println("Seat booked successfully by "
                               + passenger);
        } else {
            System.out.println("Sorry, " + passenger
                               + ", seat is already booked.");
        }
    }
}

class Passenger extends Thread {

    private RailwayReservation reservation;

    Passenger(RailwayReservation reservation, String name) {
        super(name);
        this.reservation = reservation;
    }

    public void run() {
        reservation.bookSeat(getName());
    }
}

public class RailwayDemo {

    public static void main(String[] args) {

        RailwayReservation reservation =
            new RailwayReservation();

        Passenger p1 =
            new Passenger(reservation, "Passenger 1");

        Passenger p2 =
            new Passenger(reservation, "Passenger 2");

        Passenger p3 =
            new Passenger(reservation, "Passenger 3");

        p1.start();
        p2.start();
        p3.start();
    }
}
