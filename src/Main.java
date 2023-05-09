import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Main {
    private static final BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(100);
    private static final BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(100);
    private static final BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<>(100);


    public static void main(String[] args) throws InterruptedException {
        int textsLength = 100;// todo 10_000;
        int stringLength = 100;// todo 100_000;

        new Thread(() -> {
            String[] texts = new String[textsLength];
            for (int i = 0; i < texts.length; i++) {
                texts[i] = generateText("abc", stringLength);
                try {
                    blockingQueue1.put(texts[i]);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();

        Thread.sleep(100);

        Thread letterA = new Thread(() -> {
            StringBuilder aString = new StringBuilder();
            int amountOfA = 0;
            int tempAmountOfA;
            int k = 1;// todo - delete!
            while (!blockingQueue1.isEmpty()) {
                try {
                    System.out.print(k + "; ");// todo - delete!
                    String tempString = blockingQueue1.take();
                    tempAmountOfA = symbolCounting('a', tempString);
                    if (tempAmountOfA > amountOfA) {
                        aString = new StringBuilder();
                        aString.append(tempString);
                        amountOfA = tempAmountOfA;
                    }
                } catch (InterruptedException e) {
                    return;
                }
                k++;// todo - delete!
            }// while
            System.out.println(aString);
            System.out.println("\nВ строке с наибольшим количеством букв 'a' содержится " + amountOfA + " букв.");

        });


        letterA.start();


        letterA.join();


    }// main

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static int symbolCounting(char chr, String text) {
        int quantity = 0;
        for (char letter : text.toCharArray()) {
            if (letter == chr) {
                quantity++;
            }
        }
        return quantity;
    }

}// class
