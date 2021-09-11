package cinema;

public class Stats {
    public int currentIncome;
    public int numberOfAvailableSeats;
    public int numberOfPurchasedTickets;

    public Stats() {
    }

    public Stats(Cinema cinema) {
        this.numberOfAvailableSeats = cinema.availableSeats.size();

        for (Seat aSeat : cinema.availableSeats) {
            if (aSeat.isPurchased()) {
                this.currentIncome += aSeat.getPrice();
                this.numberOfAvailableSeats--;
                this.numberOfPurchasedTickets++;
            }
        }
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }
}
