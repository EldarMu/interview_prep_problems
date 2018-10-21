package com.eldar;

import com.eldar.commonDataStructs.Codec;
import com.eldar.commonDataStructs.TreeNode;
import org.junit.Assert;
import org.junit.Test;

//test class for the Codec class
public class CodecTest {

  @Test
  public void serialize() throws Exception{
    Codec test = new Codec();
    String expectedResult;

    TreeNode head = new TreeNode(1);
    head.left = null;
    head.right = new TreeNode(2);
    head.right.right = null;
    head.right.left = new TreeNode(3);
    expectedResult = "1.n,2.3,n.n";
    Assert.assertTrue(expectedResult.equals(test.serialize(head)));

    head = new TreeNode(5);
    head.left = new TreeNode(1);
    head.right = new TreeNode(4);
    head.right.left = new TreeNode(3);
    head.right.right = new TreeNode(6);
    expectedResult = "5.1,4.n,n,3,6.n,n,n,n";
    Assert.assertTrue(expectedResult.equals(test.serialize(head)));

    head = new TreeNode(4);
    head.left = new TreeNode(9);
    head.left.left = new TreeNode(5);
    head.left.right = new TreeNode(1);
    head.right = new TreeNode(0);
    expectedResult = "4.9,0.5,1,n,n.n,n,n,n";
    Assert.assertTrue(expectedResult.equals(test.serialize(head)));

    head = new TreeNode(3);
    head.left = new TreeNode(9);
    head.right = new TreeNode(20);
    head.right.left = new TreeNode(15);
    head.right.right = new TreeNode(7);
    expectedResult = "3.9,20.n,n,15,7.n,n,n,n";
    Assert.assertTrue(expectedResult.equals(test.serialize(head)));


  }

}
