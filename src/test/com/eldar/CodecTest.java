package com.eldar;

import com.eldar.commonDataStructs.Codec;
import com.eldar.commonDataStructs.TreeNode;
import com.eldar.commonDataStructs.BSTComparator;
import org.junit.Assert;
import org.junit.Test;

//test class for the Codec class
public class CodecTest {

  @Test
  public void serialize() throws Exception{
    Codec test = new Codec();
    String expectedResult;
    TreeNode head;

    head = new TreeNode(1);
    expectedResult = "1.n,n";
    Assert.assertTrue(expectedResult.equals(test.serialize(head)));

    head = new TreeNode(1);
    head.left = null;
    head.right = new TreeNode(2);
    head.right.right = null;
    head.right.left = new TreeNode(3);
    expectedResult = "1.n,2.3,n.n,n";
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

  @Test
  public void dfsSerialize() throws Exception{
    Codec test = new Codec();
    String expectedResult;
    TreeNode head;

    head = new TreeNode(1);
    expectedResult = "1";
    Assert.assertTrue(expectedResult.equals(test.dfsSerialize(head)));

    head = new TreeNode(1);
    head.left = null;
    head.right = new TreeNode(3);
    head.right.right = null;
    head.right.left = new TreeNode(2);
    expectedResult = "1 3 2";
    Assert.assertTrue(expectedResult.equals(test.dfsSerialize(head)));

    head = new TreeNode(3);
    head.left = new TreeNode(1);
    head.right = new TreeNode(5);
    head.right.left = new TreeNode(4);
    head.right.right = new TreeNode(6);
    expectedResult = "3 1 5 4 6";
    Assert.assertTrue(expectedResult.equals(test.dfsSerialize(head)));

    head = new TreeNode(4);
    head.left = new TreeNode(1);
    head.left.left = new TreeNode(0);
    head.left.right = new TreeNode(3);
    head.right = new TreeNode(9);
    expectedResult = "4 1 0 3 9";
    Assert.assertTrue(expectedResult.equals(test.dfsSerialize(head)));

    head = new TreeNode(9);
    head.left = new TreeNode(3);
    head.right = new TreeNode(20);
    head.right.left = new TreeNode(15);
    head.right.right = new TreeNode(25);
    expectedResult = "9 3 20 15 25";
    Assert.assertTrue(expectedResult.equals(test.dfsSerialize(head)));
  }

  @Test
  public void deserialize() throws Exception{
    Codec test = new Codec();
    BSTComparator bstComparator = new BSTComparator();
    TreeNode head;
    TreeNode expectedHead;

    expectedHead = new TreeNode(1);
    head = test.deserialize("1.n,n");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));

    expectedHead = new TreeNode(1);
    expectedHead.left = null;
    expectedHead.right = new TreeNode(2);
    expectedHead.right.right = null;
    expectedHead.right.left = new TreeNode(3);
    head = test.deserialize("1.n,2.3,n.n,n");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));

    expectedHead = new TreeNode(5);
    expectedHead.left = new TreeNode(1);
    expectedHead.right = new TreeNode(4);
    expectedHead.right.left = new TreeNode(3);
    expectedHead.right.right = new TreeNode(6);
    head = test.deserialize("5.1,4.n,n,3,6.n,n,n,n");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));

    expectedHead = new TreeNode(4);
    expectedHead.left = new TreeNode(9);
    expectedHead.left.left = new TreeNode(5);
    expectedHead.left.right = new TreeNode(1);
    expectedHead.right = new TreeNode(0);
    head = test.deserialize("4.9,0.5,1,n,n.n,n,n,n");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));

    expectedHead = new TreeNode(3);
    expectedHead.left = new TreeNode(9);
    expectedHead.right = new TreeNode(20);
    expectedHead.right.left = new TreeNode(15);
    expectedHead.right.right = new TreeNode(7);
    head = test.deserialize("3.9,20.n,n,15,7.n,n,n,n");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));
  }

  @Test
  public void dfsDeserialize() throws Exception{
    Codec test = new Codec();
    BSTComparator bstComparator = new BSTComparator();
    TreeNode head;
    TreeNode expectedHead;

    expectedHead = new TreeNode(1);
    head = test.dfsDeserialize("1");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));

    expectedHead = new TreeNode(1);
    expectedHead.left = null;
    expectedHead.right = new TreeNode(3);
    expectedHead.right.right = null;
    expectedHead.right.left = new TreeNode(2);
    head = test.dfsDeserialize("1 3 2");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));

    expectedHead = new TreeNode(3);
    expectedHead.left = new TreeNode(1);
    expectedHead.right = new TreeNode(5);
    expectedHead.right.left = new TreeNode(4);
    expectedHead.right.right = new TreeNode(6);
    head = test.dfsDeserialize("3 1 5 4 6");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));

    expectedHead = new TreeNode(4);
    expectedHead.left = new TreeNode(1);
    expectedHead.left.left = new TreeNode(0);
    expectedHead.left.right = new TreeNode(3);
    expectedHead.right = new TreeNode(9);
    head = test.dfsDeserialize("4 1 0 3 9");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));

    expectedHead = new TreeNode(9);
    expectedHead.left = new TreeNode(3);
    expectedHead.right = new TreeNode(20);
    expectedHead.right.left = new TreeNode(15);
    expectedHead.right.right = new TreeNode(25);
    head = test.dfsDeserialize("9 3 20 15 25");
    Assert.assertTrue(bstComparator.isSameTree(expectedHead, head));
  }

}
