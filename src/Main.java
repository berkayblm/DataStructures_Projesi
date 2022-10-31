
import java.util.Random;

public class Main {

    static final double[][] DM_matrix = new double[40][40];
    static final String[] passengerNameMatrix = {"1.Person","2.Person","3.Person","4.Person","5.Person","6.Person","7.Person"
            ,"8.Person","9.Person","10.Person","11.Person","12.Person","13.Person","14.Person","15.Person","16.Person","17.Person"
            ,"18.Person","19.Person","20.Person","21.Person","22.Person","23.Person","24.Person","25.Person","26.Person","27.Person"
            ,"28.Person","29.Person","30.Person","31.Person","32.Person","33.Person","34.Person","35.Person","36.Person","37.Person"
            ,"38.Person","39.Person","40.Person"};
    static Random randomNm = new Random();
    static int[] placedPeopleIndexes = new int[40];
    static double[] distanceToOther = new double[40];

    public static void main(String[] args) {

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {

                double number;
                if ( i == j) {
                    number = 0;
                }
                else {
                    number = Math.round(randomNm.nextDouble(10) * 10.0) / 10.0;
                }

                DM_matrix[i][j] = number;
                DM_matrix[j][i] = number;

            }
        }

        // print out array
        printOutDMarray(DM_matrix);

        placePassengers(DM_matrix);

    }

    static void placePassengers(double[][] array) {
        int randomPassenger = randomNm.nextInt(40); // ilk oturacak kisi random belirlenir
        String firstSeat = passengerNameMatrix[randomPassenger];
        placedPeopleIndexes[0]= randomPassenger;
        distanceToOther[0] = 0.0;
        for (int i = 0; i < array.length; i++) {
            array[i][randomPassenger] = 0.0;
        }

        int closestPersonIndex = placedPeopleIndexes[0]; // 1. sıradaki kisinin indexi ile basla
        int currentSeatNumber = 2;   // 2. koltuktan itibaren check , bunu her yerlestirmede 1 artır

        for (; currentSeatNumber < array.length + 1; currentSeatNumber++) {
           closestPersonIndex =  searchForClosestPerson(array, closestPersonIndex , currentSeatNumber);


        }

        printOutPassengerTable();

    }

    static int searchForClosestPerson(double[][] array, int currentPersonIndex, int currentSeatNumber) {

        double closestPerson = 10.0;
        int closestPersonIndex = currentPersonIndex;
        double[] distanceTotalArr = new double[40];  // sol, sol çapraz,ve üst e olan uzaklık toplamları tutar


        if (currentSeatNumber % 4 == 1) {  // for 1,5,9....  üst ve sağ çapraz kontrolü 2 tane
            // seat number - 4 and seatnumber -3 check

            for (int i = 0; i < 40; i++) {
                double totalForOnePerson = 0;

                if (array[placedPeopleIndexes[currentSeatNumber - 5]][i] != 0.0
                        || array[placedPeopleIndexes[currentSeatNumber - 4]][i] != 0.0)
                {

                    totalForOnePerson = array[placedPeopleIndexes[currentSeatNumber - 5]][i]
                            + array[placedPeopleIndexes[currentSeatNumber - 4]][i];

                }
                distanceTotalArr[i] = totalForOnePerson;
            }

        }

        else if (currentSeatNumber % 4 == 2) { // for 2,6,10...

            if (currentSeatNumber == 2) {
                int indexOfFirstSeat = placedPeopleIndexes[0];
                for (int i = 0; i < 40; i++) {

                    if (array[indexOfFirstSeat][i] != 0) {
                        if (array[indexOfFirstSeat][i] < closestPerson) {
                            closestPerson = array[indexOfFirstSeat][i];
                            closestPersonIndex = i;
                        }
                    }
                }

            } else {  // other numbers but 2
                // sol, sol çapraz,ve üst kontrol
                //TODO
                for (int i = 0; i < 40; i++) {
                    double totalForOnePerson = 0;

                    if (array[placedPeopleIndexes[currentSeatNumber - 2]][i] != 0.0
                            || array[placedPeopleIndexes[currentSeatNumber - 4]][i] != 0.0
                            || array[placedPeopleIndexes[currentSeatNumber - 5]][i] != 0.0) {

                            totalForOnePerson = array[placedPeopleIndexes[currentSeatNumber - 1]][i]
                                    + array[placedPeopleIndexes[currentSeatNumber - 4]][i]
                                    + array[placedPeopleIndexes[currentSeatNumber - 5]][i];

                            distanceTotalArr[i] = totalForOnePerson;
                    }
                }

            }
        }

        else if (currentSeatNumber % 4 == 3) {
            int indexOfFirstSeat = placedPeopleIndexes[1];

            if (currentSeatNumber == 3) {

                for (int i = 0; i < 40; i++) {

                    if (array[indexOfFirstSeat][i] != 0) {
                        if (array[indexOfFirstSeat][i] < closestPerson) {
                            closestPerson = array[indexOfFirstSeat][i];
                            closestPersonIndex = i;
                        }
                    }

                }
            }
            else {  // other numbers but 2
                // sol, sol çapraz,ve üst kontrol
                //TODO
                for (int i = 0; i < 40; i++) {
                    double totalForOnePerson = 0;

                    if (array[placedPeopleIndexes[currentSeatNumber - 2]][i] != 0.0
                            || array[placedPeopleIndexes[currentSeatNumber - 4]][i] != 0.0
                            || array[placedPeopleIndexes[currentSeatNumber - 5]][i] != 0.0
                            ||array[placedPeopleIndexes[currentSeatNumber -6]][i] != 0.0) {

                        totalForOnePerson = array[placedPeopleIndexes[currentSeatNumber - 2]][i]
                                + array[placedPeopleIndexes[currentSeatNumber - 4]][i]
                                + array[placedPeopleIndexes[currentSeatNumber - 5]][i]
                                + array[placedPeopleIndexes[currentSeatNumber -6]][i];

                        distanceTotalArr[i] = totalForOnePerson;
                    }
                }

            }

        }

        else if (currentSeatNumber % 4 == 0) {
            int indexOfFirstSeat = placedPeopleIndexes[2];

            if (currentSeatNumber == 4) {

                for (int i = 0; i < 40; i++) {

                    if (array[indexOfFirstSeat][i] != 0) {
                        if (array[indexOfFirstSeat][i] < closestPerson) {
                            closestPerson = array[indexOfFirstSeat][i];
                            closestPersonIndex = i;
                        }
                    }

                }
            }
            else {  // other numbers but 2
                // sol, sol çapraz,ve üst kontrol
                //TODO
                for (int i = 0; i < 40; i++) {
                    double totalForOnePerson = 0;

                    if (array[placedPeopleIndexes[currentSeatNumber - 2]][i] != 0.0
                            || array[placedPeopleIndexes[currentSeatNumber - 5]][i] != 0.0
                            || array[placedPeopleIndexes[currentSeatNumber - 6]][i] != 0.0) {

                        totalForOnePerson = array[placedPeopleIndexes[currentSeatNumber - 2]][i]
                                + array[placedPeopleIndexes[currentSeatNumber - 5]][i]
                                + array[placedPeopleIndexes[currentSeatNumber -6]][i];

                        distanceTotalArr[i] = totalForOnePerson;
                    }
                }

            }

        }


        if (currentSeatNumber != 2 && currentSeatNumber != 3 && currentSeatNumber != 4) {
            int theClosestIndex = currentPersonIndex;
            double minNm = 1000; // set max as default from the array's first index's element
            for (int i = 0; i < distanceTotalArr.length; i++) {
                if (distanceTotalArr[i] < minNm && distanceTotalArr[i] != 0.0) {
                    minNm = distanceTotalArr[i];
                    theClosestIndex = i;
                }

            }

            //currentPersonIndex = theClosestIndex;
            closestPersonIndex = theClosestIndex;
            closestPerson = minNm;

        }

        for (int i = 0; i < array.length; i++) {
            array[i][closestPersonIndex] = 0.0;
        }
        distanceToOther[currentSeatNumber-1] = closestPerson;
        placedPeopleIndexes[currentSeatNumber - 1] = closestPersonIndex;
        return closestPersonIndex;
    }


    static void printOutDMarray(double[][] array) {
        System.out.print("    ");
        for (int i = 0; i < 40; i++) {
            if (i < 10)
                System.out.print(i + "    ");
            else {
                System.out.print(i + "   ");
            }
        }
        System.out.println();

        for (int i = 0; i < 40; i++) {
            if  (i < 10)
                System.out.print(i + "   ");
            else {
                System.out.print(i + "  ");
            }
            for (int j = 0; j < 40; j++) {

                System.out.print(array[i][j] + "  ");

            }
            System.out.println();
        }
    }

    static void printOutPassengerTable() {

        int count = 0;
        int seatNo = 1;
        while (count <=36) {
            System.out.println("     " + seatNo  + "       " + Integer.valueOf(seatNo+1) + "             "+
                    Integer.valueOf(seatNo+2) + "     "  + Integer.valueOf(seatNo+3));
            System.out.println(passengerNameMatrix[placedPeopleIndexes[count]] +"   " +
                    passengerNameMatrix[placedPeopleIndexes[count+1]]
                            + "     " + passengerNameMatrix[placedPeopleIndexes[count+2]] +
                    "  "+passengerNameMatrix[placedPeopleIndexes[count+3]]);

            System.out.println();
            seatNo += 4;
            count +=4;
        }

        count = 0;
        seatNo =1;

        System.out.println();
        System.out.println();
        System.out.println("***********************************************");

        while (count <=36) {
            System.out.println("     " + seatNo  + "       " + Integer.valueOf(seatNo+1) + "               "+
                    Integer.valueOf(seatNo+2) + "       "  + Integer.valueOf(seatNo+3));
            System.out.println(passengerNameMatrix[placedPeopleIndexes[count]] +"   " +
                    passengerNameMatrix[placedPeopleIndexes[count+1]]
                    + "     " + passengerNameMatrix[placedPeopleIndexes[count+2]] + "  "+
                    passengerNameMatrix[placedPeopleIndexes[count+3]]);

            System.out.println("   " + String.format("%.2f",distanceToOther[seatNo-1]) + "   " + "   " +
                    String.format("%.2f",distanceToOther[seatNo]) + "   " + "        "
                    + String.format("%.2f",distanceToOther[seatNo+1])+ "   " +"   " +
                    String.format("%.2f",distanceToOther[seatNo+2])+ "   ");

            System.out.println();
            seatNo += 4;
            count +=4;
        }

        System.out.println("**********************************************");
        double total = 0;
        for (Double d : distanceToOther) {
            total  += d;
        }
        System.out.println("Total Distance of All Passengers : " + String.format("%.2f",total));
    }


}
