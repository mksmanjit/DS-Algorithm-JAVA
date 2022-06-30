package search;

/**
 * Time Complexity - O(n) // Iterating over all the list for determining the searched value
 * Space Complexity - O(1) // We are not using constant space only for holding the index
 */
public class LinearSearch {

    public static void main(String args[]) {
        int[] items = {1,3,9,2,5,4,0,-1};
        int index = linearSearch(items, 2);
        System.out.println(index + " For Value 2");

        index = linearSearch(items, 4);
        System.out.println(index + " For Value 4");

        index = linearSearch(items, 20);
        System.out.println(index + " For Value 20");
    }

    public static int linearSearch(int[] items, int valueToSearch) {
        int index = -1; // O(1)
        for(int i = 0;i < items.length; i++) { // O(n)
            if(items[i] == valueToSearch) { // O(1)
                index = i;
            }
        }
        return index;
    }

}
