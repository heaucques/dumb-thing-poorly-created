public class Test {
    public static void main(String[] args) {
        String string = "rufbddbbdruuul";
        int times = 77;

        // create applescript
        System.out.println("activate application \"Google Chrome\"");
        System.out.printf("repeat %d times\n", times);
        System.out.println("\ttell application \"System Events\"");
        for (char c : string.toCharArray()) {
            System.out.println("\t\tkeystroke \"" + Character.toString(c) + "\"");
            System.out.println("\t\tdelay 0.001");
        }
        System.out.println("\tend tell");
        System.out.println("\tdelay 0.001");
        System.out.println("end repeat");

        System.out.println("===============================");

        // create ahk script

        System.out.println("^j::"); // control j to activate but it can be changed to other things; i just never use control j for anything else
        System.out.printf("Loop, %d\n", times);
        System.out.println("{"); // could probably combine braces with other lines but im too lazy to do that
        System.out.printf("\tSend, %s\n", string);
        System.out.println("\tSleep 10");
        System.out.println("}");
        System.out.println("return");
    }
}
