import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    static String[][] userDatas = { { "kurniawan", "dito", "lili", "abi", "steven" },
            { "123", "123a", "123b", "123c", "123d" } }; // username, password
    static String[][] kontaks = { { "Steven", "082150962519" }, { "Kurniawan", "085333260558" },
            { "Dito", "081332046077" }, { "Abi", "081253021235" }, { "Liset", "081336217528" } }; // nama, no.hp
    static double[][] bank = { { 001, 123, 100000 }, { 002, 345, 100000 }, { 003, 567, 100000 }, { 004, 789, 100000 },
            { 005, 000, 100000 } }; // no. rek, pin, saldo

    // keperluan ATM
    static int l = 1, x = 0, n = 0, rek;
    static double pin, pinFix, login, uang;
    static boolean check1 = false, check2 = false;

    public static void main(String[] args) {
        Login();
    }

    // Method Void
    static void Login() {
        String username, password;
        boolean checker;
        username = InputString("Masukan Username : ");
        password = InputString("Masukan Password : ");
        checker = LoginChecker(username, password);
        if (checker) {
            System.out.println("\nBerhasil Login sebagai : " + username + "\n");
            Menu();
        } else {
            System.out.println("\ngagal Login");
            Login();
        }
    }

    static void Menu() {
        int pilih;
        System.out.println("\nList Menu :");
        System.out.println("1. Kontak");
        System.out.println("2. ATM");
        System.out.println("3. Kalkulator");
        System.out.println("0. Keluar");
        System.out.println("====");
        do {
            pilih = InputInt("\nSilahkan Pilih Menu : ");
            System.out.println("");
            switch (pilih) {
                case 1:
                    Kontak();
                    break;
                case 2:
                    ATM();
                    break;
                case 3:
                    Kalkulator();
                    break;
                case 0:
                    Login();
                    break;
                default:
                    System.out.println("\nMenu tersebut tidak ada\n");
                    break;
            }
        } while (true);
    }

    static void Kontak() {
        int pilihan, i;
        do {
            i = 0;
            System.out.println("Daftar Kontak");
            for (String[] kontak : kontaks) {
                System.out.println(++i + ". " + kontak[0]);
            }
            pilihan = InputInt("\n0. Kembali ke Menu\n====\nMasukan Pilihan : ");
            pilihan--;
            if (pilihan < 0) {
                Menu();
            } else {
                System.out.println("\nNomor kontak " + kontaks[pilihan][0] + " adalah " + kontaks[pilihan][1] + "\n");
            }
        } while (true);
    }

    static void ATM() {

        loginATM();

        do {
            // check pilihan + memilih menu
            do {
                menuATM();
                chooseMenuATM();
            } while (n < 0 || n > 5);
            System.out.println("\n");
        } while (true);
    }

    static void loginATM() {
        System.out.println("[ ATM Pendragon ]");
        do {
            login = InputDouble("No rek: ");
            pin = InputDouble("Pin: ");

            for (int i = 0; i < bank.length; i++) {
                if (login == bank[i][0]) {
                    x = i;
                    check1 = true;
                    if (pin == bank[i][1]) {
                        check2 = true;
                    }
                }
            }

            if (check1 == false && check2 == false) {
                System.out.println("No. Rek tidak ditemukan!\n");
            } else if (check1 == true && check2 == false) {
                System.out.println("Pin salah!\n");
            }
            l++;
        } while ((!check1 || !check2) && l < 4);

        if ((!check1 || !check2) && l == 4) {
            System.out.println("Anda telah terblokir!");
            Menu();
            return;
        }
        pinFix = pin;
    }

    static void menuATM() {
        System.out.println(
                "==================================================================================================");
        System.out.print("==");
        System.out.printf("%8s", "0");
        System.out.printf("%8s", "==");
        System.out.printf("%8s", "1");
        System.out.printf("%8s", "==");
        System.out.printf("%8s", "2");
        System.out.printf("%8s", "==");
        System.out.printf("%8s", "3");
        System.out.printf("%8s", "==");
        System.out.printf("%8s", "4");
        System.out.printf("%8s", "==");
        System.out.printf("%8s", "5");
        System.out.printf("%8s %n", "==");

        System.out.print("==");
        System.out.printf("%10s", "Keluar");
        System.out.printf("%6s", "==");
        System.out.printf("%12s", "Cek Saldo");
        System.out.printf("%4s", "==");
        System.out.printf("%12s", "Isi Saldo");
        System.out.printf("%4s", "==");
        System.out.printf("%13s", "Tarik Saldo");
        System.out.printf("%3s", "==");
        System.out.printf("%11s", "Transfer");
        System.out.printf("%5s", "==");
        System.out.printf("%11s", "Ubah PIN");
        System.out.printf("%5s %n", "==");
        System.out.println(
                "==================================================================================================");
        System.out.println("      Important notes: Menginput 3x kesalahan pin akan membuat anda terblock");
        System.out.println(
                "==================================================================================================\n");
    }

    static void chooseMenuATM() {
        n = InputInt("Pilih menu: ");
        System.out.println();

        switch (n) {
            case 0:
                System.out.println(
                        "==================================================================================================");
                System.out.println("                      Terima kasih telah menggunakan ATM! :D");
                System.out.println(
                        "==================================================================================================\n\n");
                Menu();
                break;
            case 1:

                ATMchecker();

                System.out.println("Saldo anda: Rp. " + bank[(int) login - 1][2]);
                break;

            case 2:

                ATMchecker();

                do {
                    uang = InputDouble("Masukkan jumlah uang anda: Rp. ");
                } while (uang < 1);
                bank[(int) login - 1][2] = bank[(int) login - 1][2] + uang;
                System.out.println("Saldo anda sudah bertambah!");
                break;

            case 3:

                ATMchecker();

                ATMMoneyCheck("tarik");

                if (uang < bank[(int) login - 1][2]) {
                    bank[(int) login - 1][2] = bank[(int) login - 1][2] - uang;
                    System.out.println("Penarikan berhasil!");
                }
                break;

            case 4:

                ATMchecker();

                System.out.println(
                        "List rekening: \n1. Dito (001) \n2. Steven (002) \n3. Niwa (003) \n4. Abi (004) \n5. Liset (005)");
                rek = InputInt("Masukkan No. Rekening: ");
                rek--;

                ATMMoneyCheck("transfer");

                if (uang < bank[(int) login - 1][2]) {
                    bank[(int) login - 1][2] = bank[(int) login - 1][2] - uang;
                    bank[rek][2] = bank[rek][2] + uang;
                    System.out.println("Transfer berhasil!");
                }
                break;
            case 5:

                ATMchecker();

                do {
                    int pinB = InputInt("Masukkan pin baru anda: ");
                    String jwb = InputString("Yakin ingin ingin mengubah pin lama anda menjadi " + pinB + " (Y/N) ?");

                    if (jwb.equalsIgnoreCase("y")) {
                        bank[x][1] = pinB;
                        System.out.println("Selamat pin anda sudah berubah!");
                        break;
                    }
                } while (true);
            default:
                System.out.println("Menu ATM tidak ditemukan!");
        }
    }

    static void ATMchecker() {
        l = 1;
        do {
            pin = InputDouble("Masukkan pin: ");
            l++;
        } while (pinFix != pin && l < 4);

        if (pinFix != pin && l == 4) {
            System.out.println("Anda telah terblokir!");
            return;
        }
    }

    static void ATMMoneyCheck(String verb) {
        do {
            uang = InputDouble("Jumlah " + verb + " uang: Rp. ");
            if (uang > bank[(int) login - 1][2]) {
                System.out.println("Saldo anda tidak cukup!");
                break;
            }
        } while (uang < 1);
    }

    static void Kalkulator() {
        double a, b;
        double hasil = 0;
        char operator;
        System.out.println("\n [ Kalkulator 2 Bilangan ]");
        a = InputDouble("\nMasukan Nilai Pertama : ");
        b = InputDouble("Masukan Nilai Kedua : ");
        do {
            operator = InputChar("Masukan Oprator : ");
            switch (operator) {
                case '*':
                case 'x':
                    hasil = a * b;
                    break;
                case '/':
                case ':':
                    hasil = a / b;
                    break;
                case '+':
                    hasil = a + b;
                    break;
                case '-':
                    hasil = a - b;
                    break;
                default:
                    System.out.println("Operator tidak ditemukan silahkan coba lagi!");
                    break;
            }
        } while (hasil == 0);
        System.out.print("Hasil Perhitungan; " + a + operator + b);
        System.out.print(" = " + hasil + "\n\n");
        do {
            System.out.println("1. Coba Lagi");
            System.out.println("0. Kembali");
            int select = InputInt("\nMasukan Pilihan : ");
            switch (select) {
                case 1:
                    Kalkulator();
                    break;
                case 0:
                    Menu();
                    break;
                default:
                    System.out.println("Perintah Tidak ditemukan!!");
                    break;
            }
        } while (true);
    }

    // Method with return value
    static boolean LoginChecker(String username, String password) {
        for (int i = 0; i < userDatas[0].length; i++) {
            if (username.equals(userDatas[0][i]) && password.equals(userDatas[1][i])) {
                return true;
            }
        }
        return false;
    }

    static String InputString(String msg) {
        System.out.print(msg);
        return sc.next();
    }

    static int InputInt(String msg) {
        System.out.print(msg);
        return sc.nextInt();
    }

    static double InputDouble(String msg) {
        System.out.print(msg);
        return sc.nextDouble();
    }

    static char InputChar(String msg) {
        System.out.print(msg);
        return sc.next().toLowerCase().charAt(0);
    }
}