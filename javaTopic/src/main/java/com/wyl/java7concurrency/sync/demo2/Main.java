package com.wyl.java7concurrency.sync.demo2;

/**
 * Created by wangyulin on 22/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        Cinema cinema = new Cinema();

        TicketOffice1 ticketOfficel = new TicketOffice1(cinema);
        Thread thread1 = new Thread(ticketOfficel, "TicketOffice1");

        TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
        Thread thread2 = new Thread(ticketOffice2, "TicketOffice2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Room 1 Vacancies: %d \n", cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d \n", cinema.getVacanciesCinema2());
    }

}
