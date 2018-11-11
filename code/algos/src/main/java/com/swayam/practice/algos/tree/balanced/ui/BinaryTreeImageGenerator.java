package com.swayam.practice.algos.tree.balanced.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swayam.practice.algos.tree.balanced.Tree;
import com.swayam.practice.algos.tree.balanced.Tree.Node;

public class BinaryTreeImageGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryTreeImageGenerator.class);

    private static final int TREE_GAP = 100;
    private static final int NODE_DIA = 100;
    private static final int NODE_GAP = 70;

    public BufferedImage getImage(Tree binaryTree) {
        int treeHeight = binaryTree.getHeight();
        int maxTreeBreadth = 2 * TREE_GAP + getMaxNodes(treeHeight) * NODE_DIA + (getMaxNodes(treeHeight) - 1) * NODE_GAP;
        int imageHeight = treeHeight * NODE_DIA + (treeHeight + 1) * NODE_GAP + 2 * TREE_GAP;
        BufferedImage image = new BufferedImage(maxTreeBreadth, imageHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics g = image.getGraphics();
        g.fillRect(0, 0, maxTreeBreadth, imageHeight);

        // start from root
        g.setColor(Color.RED);

        paintNode(g, binaryTree, binaryTree.getRoot(), treeHeight, maxTreeBreadth, maxTreeBreadth / 2 - NODE_DIA / 2);

        return image;
    }

    private int getMaxNodes(int nodeHeight) {
        return (int) Math.ceil(Math.pow(2, nodeHeight - 1));
    }

    private int getNodeGap(int nodeHeight, int maxTreeBreadth) {
        LOGGER.debug("nodeHeight: {}", nodeHeight);
        return (maxTreeBreadth - (2 * TREE_GAP + getMaxNodes(nodeHeight) * NODE_DIA)) / (getMaxNodes(nodeHeight) - 1);
    }

    private void paintNode(Graphics g, Tree binaryTree, Node node, int treeHeight, int maxTreeBreadth, int nodeStartX) {
        if (node == null) {
            return;
        }

        int heightOfNode = binaryTree.getHeight(node);
        int nodeStartY = TREE_GAP + NODE_DIA + (treeHeight - heightOfNode) * (NODE_DIA + NODE_GAP);

        paintNode(g, new Point(nodeStartX, nodeStartY), node.getValue());

        int childNodeGap = 0;
        if (heightOfNode < treeHeight - 1) {
            childNodeGap = getNodeGap(treeHeight - heightOfNode, maxTreeBreadth);
        }

        Point lowerMidPointOfNode = new Point(nodeStartX + NODE_DIA / 2, nodeStartY + NODE_DIA);

        if (node.getLeft() != null) {
            g.setColor(Color.BLACK);
            int endX = lowerMidPointOfNode.x - (NODE_GAP + NODE_DIA / 2 + childNodeGap);
            g.drawLine(lowerMidPointOfNode.x, lowerMidPointOfNode.y, endX, lowerMidPointOfNode.y + NODE_GAP);
            g.setColor(Color.BLUE);
            paintNode(g, binaryTree, node.getLeft(), treeHeight, maxTreeBreadth, endX - NODE_DIA);
        }

        if (node.getRight() != null) {
            g.setColor(Color.BLACK);
            int endX = lowerMidPointOfNode.x + (NODE_GAP + NODE_DIA / 2 + childNodeGap);
            g.drawLine(lowerMidPointOfNode.x, lowerMidPointOfNode.y, endX, lowerMidPointOfNode.y + NODE_GAP);
            g.setColor(Color.GREEN);
            paintNode(g, binaryTree, node.getRight(), treeHeight, maxTreeBreadth, endX);
        }

    }

    private void paintNode(Graphics g, Point start, int value) {

        g.fillOval(start.x, start.y, NODE_DIA, NODE_DIA);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(value), start.x + NODE_DIA / 2, start.y + NODE_DIA / 2);

        LOGGER.info("Printed node {} at ({}, {})", value, start.x, start.y);

    }

}
