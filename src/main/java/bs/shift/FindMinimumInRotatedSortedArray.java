package bs.shift;

/**
 * 153
 */
public class FindMinimumInRotatedSortedArray {

  /**
   * Like 33 - check in which part we are at and move from there. Keep in mind
   * that we know that min is always in the right part.
   */
  public static class Solution {

    public int findMin(int[] ar) {

      int n = ar.length;
      if (n == 0) return -1;

      int lo = 0;
      int hi = n - 1;

      if (ar[lo] < ar[hi]) return ar[lo];

      while (hi - lo >= 0) {
        int mid = lo + (hi - lo) / 2;

        // if mid in the left part - check for ans or go right
        if (ar[mid] > ar[hi]) {
          // check for ans
          if (mid + 1 < n && ar[mid + 1] < ar[mid]) {
            return ar[mid + 1];
          }
          // go right
          else {
            lo = mid + 1;
          }
        }

        // if mid is in the right part - check for ans or go left
        else if (ar[mid] < ar[lo]) {
          // check for ans
          if (mid - 1 >= 0 && ar[mid - 1] > ar[mid]) {
            return ar[mid];
          }
          // go left
          else {
            hi = mid - 1;
          }
        }

        // if ar[lo] < ar[hi] meaning subarray [lo..hi] is not shifted
        // we just go left because in the regular array smaller value is alw on the left
        else {
          hi = mid - 1;
        }
      }

      // the ans is always there
      return ar[lo];
    }
  }
}