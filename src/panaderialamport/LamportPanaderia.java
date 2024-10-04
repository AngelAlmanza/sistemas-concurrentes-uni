package panaderialamport;

import java.util.Arrays;

class LamportPanaderia {
    private final int numTuristas = 5;
    private final int[] tickets = { 0, 0, 0, 0, 0 };
    private final boolean[] entrando = { false, false, false, false, false };

    public void tomarTicket(int turista) {
        entrando[turista] = true;
        tickets[turista] = Arrays.stream(tickets).max().getAsInt() + 1;
        entrando[turista] = false;

        for (int j = 0; j < numTuristas; j++) {
            if (j != turista) {
                while (entrando[j]) {
                    Thread.yield();
                }
                while(tickets[j] != 0 && (tickets[j] < tickets[turista] || (tickets[j] == tickets[turista] && j < turista))) {
                    Thread.yield();
                }
            }
        }
    }

    public void dejarTicket(int turista) {
        tickets[turista] = 0;
    }
}