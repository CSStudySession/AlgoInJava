package BinarySearch;

public class FindMinInRotatedArray {

    public int findMin(int[] nums) {
        int s = 0, e = nums.length-1;

        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] < nums[1] ? nums[0] : nums[1];

        if (nums[s] < nums[e]) return nums[s];

        while (s + 1 < e) {

            int m = (s + e) / 2;
            if (m-1 >= 0 && nums[m-1] > nums[m] && m+1 < nums.length && nums[m] <= nums[m+1]) {
                return nums[m];
            } else if (m-1 >= 0 && nums[m-1] <= nums[m] && m+1 < nums.length && nums[m] > nums[m+1]) {
                return nums[m+1];
            } else if (nums[m] > nums[s]) {
                s = m;
            } else {
                e = m;
            }
        }

        //System.out.println("s: " + s + ", e: " + e);

        return -1;
    }

    public int findWithDuplicates(int[] nums) {
        int s = 0, e = nums.length-1;

        if (nums.length == 1) return 0;
        if (nums.length == 2) return nums[0] < nums[1] ? 0 : 1;

        System.out.println("s: " + s + ", e: " + e);
        while (s + 1 < e) {

            while (s + 1 < e && nums[s] == nums[s+1]) {
                ++s;
            }

            while (e - 1 > s && nums[e] == nums[e-1]) {
                --e;
            }
            System.out.println("s: " + s + " num[s]: " + nums[s] + ", e: " + e + " num[e]: " + nums[e]);

            int m = (s + e) / 2;
            if (m-1 >= 0 && nums[m-1] > nums[m] && m+1 < nums.length && nums[m] <= nums[m+1]) {
                while (m - 1 >= 0 && nums[m] == nums[m-1]) {
                    --m;
                }
                return m;
            } else if (m-1 >= 0 && nums[m-1] <= nums[m] && m+1 < nums.length && nums[m] > nums[m+1]) {
                while (m + 1 < nums.length && nums[m] == nums[m+1]) {
                    ++m;
                }
                return m+1;
            } else if (nums[m] > nums[s]) {
                s = m;
            } else {
                e = m;
            }
        }

        System.out.println("s: " + s + ", e: " + e);

        return nums[s] < nums[e] ? s : e;
    }


    public static void main(String[] args) {
        FindMinInRotatedArray f = new FindMinInRotatedArray();
        int[] t0 = {4,5,6,7,7,7,7,7,7,7,7,7,7,1,1,1,1,2,3,3,3,3,3,3,3};
        int index = f.findWithDuplicates(t0);
        System.out.println(index+ ", " + t0[index]);

        int[] t1 = {1};
        index = f.findWithDuplicates(t1);
        System.out.println(index+ ", " + t1[index]);


        int[] t2 = {2,1};
        index = f.findWithDuplicates(t2);
        System.out.println(index+ ", " + t2[index]);

        int[] t3 = {5,1,2,3,4};
        index = f.findWithDuplicates(t3);
        System.out.println(index+ ", " + t3[index]);
    }
}
