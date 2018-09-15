package com.eldar;

import com.eldar.commonDataStructs.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;


//test class for the BST Iterator
public class BSTIteratorTest {

  @Test
  public void testBSTIterator() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    BSTIterator test;
    ArrayList<Integer> results;
    ArrayList<Integer> expected;
    TreeNode root;

    test = new BSTIterator(null);
    Assert.assertFalse(test.hasNext());

    root = new TreeNode(8);
    root.left = new TreeNode(3);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(6);
    TreeNode tmp = root.left.right;
    tmp.left = new TreeNode(4);
    tmp.right = new TreeNode(7);
    root.right = new TreeNode(10);
    root.right.right = new TreeNode(14);
    root.right.right.left = new TreeNode(13);

    test = new BSTIterator(root);
    expected = new ArrayList<>(Arrays.asList(new Integer[] {1,3,4,6,7,8,10,13,14}));
    results = new ArrayList<>(expected.size());
    while(test.hasNext()){
      results.add(test.next());
    }

    Assert.assertEquals(expected.size(), results.size());
    for(int i = 0; i < expected.size(); i++){
      Assert.assertEquals(expected.get(i), results.get(i));
    }
  }

}
