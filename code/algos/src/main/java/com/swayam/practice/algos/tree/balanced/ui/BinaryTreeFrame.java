/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swayam.practice.algos.tree.balanced.ui;

import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.swayam.practice.algos.tree.balanced.BinaryTree;
import com.swayam.practice.algos.tree.balanced.Tree;

/**
 *
 * @author paawak
 */
public class BinaryTreeFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private final BinaryTree<Integer> binaryTree;

    /**
     * Creates new form BinaryTreeFrame
     */
    public BinaryTreeFrame(BinaryTree<Integer> binaryTree) {
        this.binaryTree = binaryTree;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        scrPnlTree = new javax.swing.JScrollPane();
        lblTree = new javax.swing.JLabel();
        pnlButtons = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Binary Tree");
        getContentPane().add(lblTitle, java.awt.BorderLayout.NORTH);

        lblTree.setMinimumSize(new java.awt.Dimension(500, 500));
        lblTree.setPreferredSize(new java.awt.Dimension(500, 500));
        scrPnlTree.setViewportView(lblTree);

        getContentPane().add(scrPnlTree, java.awt.BorderLayout.CENTER);

        btnAdd.setMnemonic('a');
        btnAdd.setText("Add...");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pnlButtons.add(btnAdd);

        btnRemove.setMnemonic('r');
        btnRemove.setText("Remove...");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        pnlButtons.add(btnRemove);

        getContentPane().add(pnlButtons, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddActionPerformed

        Optional<Integer> input = getUserInputForInteger("Enter the number to add", "Add");

        if (input.isPresent()) {
            Integer inputInt = input.get();

            binaryTree.add(inputInt);
            lblTree.setIcon(new ImageIcon(new BinaryTreeImageGenerator().getImage(500, 500, binaryTree.getSwingTree())));
        }

    }// GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRemoveActionPerformed

        throw new UnsupportedOperationException();

    }// GEN-LAST:event_btnRemoveActionPerformed

    private Optional<Integer> getUserInputForInteger(String message, String title) {
        String input = JOptionPane.showInputDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);

        try {
            Integer inputAsInt = Integer.valueOf(input);
            return Optional.of(inputAsInt);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid number", "Invalid input", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting
        // code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
         * html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BinaryTreeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BinaryTreeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BinaryTreeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BinaryTreeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BinaryTreeFrame(new Tree()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTree;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JScrollPane scrPnlTree;
    // End of variables declaration//GEN-END:variables
}
