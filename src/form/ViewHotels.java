/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author saurabhshanbhag
 */
public class ViewHotels extends javax.swing.JFrame {

    /**
     * Creates new form ViewHotels
     */
    public ViewHotels() {
        initComponents();
        generateHotels();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        invoiceFrame = new javax.swing.JTable();
        Home6 = new javax.swing.JButton();
        Logout1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        invoiceFrame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hotel ID", "Name", "Address", "City", "Phone No", "Manager ID"
            }
        ));
        jScrollPane1.setViewportView(invoiceFrame);

        Home6.setText("Home");
        Home6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Home6ActionPerformed(evt);
            }
        });

        Logout1.setText("Logout");
        Logout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Home6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Logout1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Home6)
                    .addComponent(Logout1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Home6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home6ActionPerformed
        // TODO add your handling code here:
        Manager mng = new Manager();
        sysExit();
        mng.setVisible(true);
    }//GEN-LAST:event_Home6ActionPerformed

    private void Logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout1ActionPerformed
        // TODO add your handling code here:
        Login l = new Login();
        sysExit();
        l.setVisible(true);
    }//GEN-LAST:event_Logout1ActionPerformed
    
    public void sysExit(){
        WindowEvent winClosing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewHotels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewHotels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewHotels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewHotels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewHotels().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton Home1;
    private javax.swing.JButton Home2;
    private javax.swing.JButton Home3;
    private javax.swing.JButton Home4;
    private javax.swing.JButton Home5;
    private javax.swing.JButton Home6;
    private javax.swing.JButton Logout1;
    public javax.swing.JTable invoiceFrame;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void generateHotels() {
        DefaultTableModel model = (DefaultTableModel) invoiceFrame.getModel();
        db_connection db = new db_connection();
        Connection conn = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        ResultSet hotelsResult; 
        ResultSet hotelCityResult; 
        
        try {
            conn = db.connect_db();
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();
            
            hotelsResult = stmt1.executeQuery("select * from hotel");
            
            while (hotelsResult.next()) {
                int hotelID = hotelsResult.getInt("hotelid");
                String hotelName = hotelsResult.getString("name");
                String hotelAdr = hotelsResult.getString("address");
                String hotelPhn = hotelsResult.getString("phoneNum");
                int hotelMgrID = hotelsResult.getInt("managerid");
                String hotelCity = "";
                hotelCityResult = stmt2.executeQuery("select city from hotelcity where address='"+hotelAdr+"'");
                if (hotelCityResult.next()) {
                    hotelCity = hotelCityResult.getString("city");
                }
                
                model.addRow(new Object[]{hotelID, hotelName, hotelAdr, hotelCity, hotelPhn, hotelMgrID});
                
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            if (stmt1 != null) {
                try {
                    stmt1.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt2 != null) {
                try {
                    stmt2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
            db.close_db(conn);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
}
