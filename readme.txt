How long does your program take to produce the answer when using the bst, rbt, and hash
flags? Copy the results into this readme file.
    Hash
        * java MostAnagramsFinder words.txt hash  0.46s user 0.03s system 176% cpu 0.282 total
        * Average = 0.298s
    BST
        * java MostAnagramsFinder words.txt bst  1.72s user 0.05s system 112% cpu 1.577 total
        * Average = 1.686s
    RBT
        * java MostAnagramsFinder words.txt rbt  0.76s user 0.04s system 150% cpu 0.536 total
        * Average = 0.676s

What data structure do you expect to have the best (fastest) performance? Which one do
you expect to be the slowest? Do the results of timing your program’s execution match your
expectations? If so, briefly explain the correlation. If not, what run times deviated and
briefly explain why you think this is the case.
    * I expected MyHashMap to have the best/fastest performance since its insertion, along
      with hashing,operation has constant(Theta(1)) time complexity. Its get operation does
      need to traverse the linked lists of the map so the time complexity is technically
      Theta(lambda).However, given a good function that distributes the values fairly,time to
      traverse the linked list (Theta(lambda)) would be negligible in comparison to the
      entire dataset.BST has O(n) insertion and lookup in the worst case scenario
      , so I expected it to be the slowest.

    * The results of all 5 of my timings matched my expectations. As anticipated, the efficient
      searching and insertion processes in hashmaps resulted in the fastest performance. RBT was
      the second best and outperformed BST due to its self-balancing nature. It has a worst case
      time complexity of Theta(lgn) for both look up and insertion. It is true that the both hashmaps
      and BST could degenerate to a linked list containing all values. However,in practice the chances
      of that happening is higher in BST and very low in hashmaps(especially those with a good hash function).
      MyHashMap uses large prime numbers when rehashing so it would be fair to expect the data to be
      well distributed across the indices and the collisions to be minimal. Given the fact that all
      tests were run under the same conditions with the only changing variable being the data structure used,
      the difference in overall performance time can be attributed to the difference in performance
      between the data structures time performance.

