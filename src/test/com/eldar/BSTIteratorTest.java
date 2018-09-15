package com.eldar;

import com.eldar.ProblemSolutions.TreeNode;
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

    root = tester.new TreeNode(8);
    root.left = tester.new TreeNode(3);
    root.left.left = tester.new TreeNode(1);
    root.left.right = tester.new TreeNode(6);
    TreeNode tmp = root.left.right;
    tmp.left = tester.new TreeNode(4);
    tmp.right = tester.new TreeNode(7);
    root.right = tester.new TreeNode(10);
    root.right.right = tester.new TreeNode(14);
    root.right.right.left = tester.new TreeNode(13);

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
