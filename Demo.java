import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Demo {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    ArrayList<Cipher> ciphers = new ArrayList<Cipher>();
    boolean run = true;
    System.out.println("Welcome to the Wii Fit Cipher Service! To begin, please add your first cipher.\n");
    ciphers.add(initCipher(in));
    while (run) {
      System.out.println("\nYour ciphers: ");
      for (int i = 0; i < ciphers.size(); i++) {
        System.out.println("\t"+(i+1)+") " + ciphers.get(i));
      }
      System.out.println("\nWhat would you like to do?\n");
      System.out.println("\t[1] Add a cipher");
      System.out.println("\t[2] Remove a cipher");
      System.out.println("\t[3] Run a cipher");

      switch (in.next()) {
        case "1":
          ciphers.add(initCipher(in));
          break;
        case "2":
          System.out.println("\nWhich cipher would you like to remove?\n");
          for (int i = 0; i < ciphers.size(); i++) {
            System.out.println("\t["+(i+1)+"] " + ciphers.get(i));
          }
          System.out.println("\nRemoving cipher "+ ciphers.remove(Integer.parseInt(in.next())) + "\n");
          break;
        case "3":
          System.out.println("\nWhich cipher would you like to run?\n");
          for (int i = 0; i < ciphers.size(); i++) {
            System.out.println("\t["+i+"] " + ciphers.get(i));
          }
          int toRun = in.nextInt();
          System.out.println("\nRunning " + ciphers.get(toRun) + "\n");
          runCipher(ciphers.get(toRun), in);
          break;
        default:
          quit("Invalid selection! QUITTING");
      }
    }
  }

  public static void runCipher(Cipher toRun, Scanner preset) {
    System.out.println("\nSelect a function: \n");
    System.out.println("\t[1] Encryption");
    System.out.println("\t[2] Decryption\n");

    switch(preset.next()) {
      case "1":
        preset.nextLine();
        System.out.println("Enter your plaintext: ");
        System.out.println("\nCiphertext: " + toRun.encrypt(preset.nextLine()));
        break;
      case "2":
        preset.nextLine();
        System.out.println("Enter your ciphertext: ");
        System.out.println("\nPlaintext: " + toRun.decrypt(preset.nextLine()));
        break;
      default:
        quit("Invalid selection! QUITTING");
    }
  }

  public static Cipher initCipher(Scanner preset) {
    Cipher util;
    System.out.println("\nPlease select a cipher to add: \n");
    System.out.println("\t[1] Substitution Cipher");
    System.out.println("\t[2] Caesar Cipher");
    System.out.println("\t[3] Vigenere Cipher");
    System.out.println("\t[4] Book Cipher");
    System.out.println("\t[5] Rail-Fence Cipher");
    System.out.println("\t[6] Autokey Cipher\n");

    switch (preset.next()) {
      case "1":
        try{
          System.out.println("\nSelect a randomization key (int) for this substitution cipher!\n");
          util = new SubCipher(Integer.parseInt(preset.next()));
          break;
        }catch(NumberFormatException e){
          quit("Key must be a number! QUITTING");
        }
      case "2":
        try{
          System.out.println("\nSelect a shift key (int) for this Caesar cipher!\n");
          util = new CaesarCipher(Integer.parseInt(preset.next()));
          break;
        }catch(NumberFormatException e){
          quit("Key must be a number! QUITTING");
        }
      case "3":
        System.out.println("\nSelect a keyword for this Vigenere cipher!\n");
        util = new VigenereCipher(preset.next());
        break;
      case "4":
        try{
          System.out.println("\nChoose a plain text book file for this book Cipher!\n");
          String filename = preset.next();
          System.out.println("\nNow choose an integer randomization key!");
          util = new BookCipher(Integer.parseInt(preset.next()), "BookCipherText.txt");
          break;
        }catch(FileNotFoundException e){
          quit("BookCipherText.txt not found! QUITTING");
        }catch(NumberFormatException e){
          quit("input a int please! QUITTING");
        }
      case "5":
        System.out.println("\nSelect the height of your fence  (int) and specify the text to encrypt for the RailFenceCipher!\n");
        String a = preset.next();
        preset.nextLine();
        String b = preset.nextLine();
        util = new RailFenceCipher(Integer.parseInt(a), b);
        break;
      case "6":
        System.out.println("Select a keyword for this Autokey cipher!\n");
        util = new AutokeyCipher(preset.next());
        break;
      default:
        util = new SubCipher(0);
        quit("Invalid selection! QUITTING");
    }

    return util;
  }

  public static void quit(String msg) {
    System.out.println(msg);
    System.exit(1);
  }
}