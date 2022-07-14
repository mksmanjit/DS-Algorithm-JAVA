package greedy;

/**
 *  If two elements are in the same tree, then they are in the same disjoint set. The root node
 *  (or the topmost node) of each tree is called the representative of the set. There is always
 *  a single unique representative of each set. A simple rule to identify a representative is
 *  if ‘i’ is the representative of a set, then parent[i] = i. If i is not the representative
 *  of his set, then it can be found by traveling up the tree until we find the representative.
 *
 *  Union Time Complexity - O(1)
 *  Find Time Complexity - O(1)
 *  Create Set - O(n)
 */
public class DisJointSet {


    private int[] disJointSet ;
    // This will hold the height of the tree.
    private int[] rank ;
    public DisJointSet(int size) {
        this.disJointSet = new int[size];
        this.rank = new int[size];
    }

    public static void main(String[] args) {
        DisJointSet disJointSetByRank = new DisJointSet(10);
        for(int i = 0 ;i < disJointSetByRank.disJointSet.length ; i++) {
            // Initially all elements are parent to itself.
            disJointSetByRank.disJointSet[i] = i;
            // In the starting height of the tree is 0.
            disJointSetByRank.rank[i] = 0;
        }
        for(int i = 1 ;i < disJointSetByRank.disJointSet.length ; i++) {
            if(i%2 == 0) {
                disJointSetByRank.union(i, i - 2);
                System.out.println("Find " + i + ">>" + disJointSetByRank.find(i));
                System.out.println("Find " + (i -2) + ">>" + disJointSetByRank.find(i));
            }
        }
    }


    /**
     * Without using Path compression.
     * Time Complexity - O(n)
     *
     * Using Path Compression and Union by Rank.
     * Amortized Time Complexity - O(1)
     *
     * @param x
     * @return
     */
    public int find(int x) {
        if(disJointSet[x] == x) return x;
        int parent = find(disJointSet[x]);
        disJointSet[x] = parent; // path compression.
        return parent;
    }

    /**
     * Time Complexity : O(1)
     *
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        int parent1 = find(x);
        int parent2 = find(y);
        if(parent1 == parent2) {
            // Both belong to same parent no need of union.
            return;
        }
        if(rank[parent1] > rank[parent2]) {
            disJointSet[parent2] = parent1;
            // No need to increase rank in this case as height will not change.
        } else if(rank[parent1] < rank[parent2]) {
            disJointSet[parent1] = parent2;
            // No need to increase rank in this case as height will not change.
        } else {
            disJointSet[parent2] = parent1;
            // If both having same height and when we will combine the both in that case height
            // of the one which we are appending to increased by one.
            rank[parent1]++;
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
