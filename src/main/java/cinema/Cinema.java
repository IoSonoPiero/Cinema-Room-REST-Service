package cinema;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private int totalRows;
    private int totalColumns;
    List<Seat> availableSeats;

    public Cinema() {
        this(9, 9);
    }

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = initializeSeats(totalRows, totalColumns);
    }

    public List<Seat> getAvailableSeats() {
        // return all NOT purchased seats
        List<Seat> notPurchasedSeats = new ArrayList<>();
        for (Seat aSeat : availableSeats) {
            if (!aSeat.isPurchased()) {
                notPurchasedSeats.add(aSeat);
            }
        }
        return notPurchasedSeats;
    }

    public Seat getSeat(int row, int column) {
        int seat = ((row - 1) * totalColumns + column) - 1;
        return availableSeats.get(seat);
    }

    public Seat purchaseSeat(int row, int column) {
        int seat = ((row - 1) * totalColumns + column) - 1;
        availableSeats.get(seat).setPurchased(true);
        return availableSeats.get(seat);
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    private List<Seat> initializeSeats(int totalRows, int totalColumns) {
        availableSeats = new ArrayList<>(totalRows * totalColumns);
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new Seat(i, j, i <= 4 ? 10 : 8, false));
            }
        }
        return availableSeats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }
}