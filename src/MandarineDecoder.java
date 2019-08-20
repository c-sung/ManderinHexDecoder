import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;
import java.io.*;

public class MandarineDecoder {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String PATTERN = "^(%[a-zA-Z0-9][a-zA-Z0-9])*$";
    private static final String FILE_NAME_PATTERN = "^[a-zA-Z0-9]*$";

    public static void main(String[] args) {
        String input_encoded_str = "";
        String decode_result = "";
        String file_name = "";

        System.out.println("Enter the encoded Mandarine >> ");
        input_encoded_str = SCANNER.nextLine();

        if(check_utf8(input_encoded_str)) {
            try {
                decode_result = URLDecoder.decode(input_encoded_str, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please check if input string is valid.");
            System.exit(0);
        }

        if(init()) {
            file_name = file_name_check();
            save_as_file(file_name, decode_result);
        } else {
            System.out.println(decode_result);
        }
    }

    private static boolean init() {
        String file_selection = "";
        boolean print_out_file = false;

        while(true) {
            System.out.println("Do you want to print the result out as a file? (y/n)");
            file_selection = SCANNER.next();

            if (file_selection.equals("y")) {
                print_out_file = true;
                break;
            } else if (!file_selection.equals("y") && !file_selection.equals("n")) {
                System.out.println("check again.");
            } else {
                break;
            }
        }
        return print_out_file;
    }

    private static boolean check_utf8(String input_encoded_str) {

        boolean result = false;

        if(input_encoded_str.matches(PATTERN)) {
            result = true;
        }

        return result;
    }

    private static void save_as_file(String file_name, String content) {
        try {
            File file = new File(System.getProperty("user.home") + "\\Desktop\\" + file_name + ".txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("complete. result: " + content);
    }

    private static String file_name_check() {

        String file_name = "";

        while(true) {
            System.out.println("Please Enter file name (file will save to desktop.) >> ");
            file_name = SCANNER.next();

            if(file_name.matches(FILE_NAME_PATTERN)) {
                break;
            } else {
                System.out.println("Invalid File name. Please try again. ");
            }
        }
        return file_name;
    }
}
