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
 * @author neelkapadia
 */
public class ViewRecord extends javax.swing.JFrame {

    /**
     * Creates new form ViewRecord
     */
    public ViewRecord() {
        initComponents();
        generateRecords();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Logout1 = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        invoiceFrame = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Logout1.setText("Logout");
        Logout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout1ActionPerformed(evt);
            }
        });

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        invoiceFrame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ServiceID", "Date", "Time", "Type", "Cost", "UpdatedBy", "HotelID", "RoomID"
            }
        ));
        jScrollPane1.setViewportView(invoiceFrame);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 486, Short.MAX_VALUE)
                .addComponent(Home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Logout1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Logout1)
                    .addComponent(Home))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void generateRecords() {
        DefaultTableModel model = (DefaultTableModel) invoiceFrame.getModel();
        db_connection db = new db_connection();
        Connection conn = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        Statement stmt4 = null;
        ResultSet servicesResult; 
        ResultSet serviceCostResult; 
        ResultSet updatedByResult; 
        ResultSet linkServiceResult; 
        
        try {
            conn = db.connect_db();
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();
            stmt3 = conn.createStatement();
            stmt4 = conn.createStatement();
            
            servicesResult = stmt1.executeQuery("select * from servicerecord");
            int servcID;
            String serviceType;
            
            while (servicesResult.next()) {
                String serviceCost = "";
                int bookingID = -1;
                String hotelID = "";
                String roomNum = "";
                servcID = servicesResult.getInt("serviceid");
                serviceType = servicesResult.getString("serviceType");
                serviceCostResult = stmt2.executeQuery("select cost from servicecost where servicetype='"+serviceType+"'");
                if (serviceCostResult.next()) {
                    serviceCost = serviceCostResult.getInt("cost")+"";
                }
                updatedByResult = stmt3.executeQuery("select staffid from updates where serviceid="+servcID);
                String updatedByStaff = "";
                while(updatedByResult.next()) {
                    updatedByStaff += updatedByResult.getInt("staffid")+",";
                }
                int index = updatedByStaff.lastIndexOf(',');
                if(index != -1) {
                    updatedByStaff = updatedByStaff.substring(0,index);
                }
                linkServiceResult = stmt4.executeQuery("select bookingid from linkservice where serviceid="+servcID);
                if(linkServiceResult.first()) {
                    bookingID = linkServiceResult.getInt("bookingid");
                    linkServiceResult = stmt4.executeQuery("select hotelid, roomnum from isAssigned where bookingid="+bookingID);
                    if(linkServiceResult.first()) {
                        hotelID = linkServiceResult.getInt("hotelid")+"";
                        roomNum = linkServiceResult.getInt("roomnum")+"";
                    }
                }
                
                model.addRow(new Object[]{servcID,servicesResult.getString("date"),servicesResult.getString("time"),serviceType,serviceCost,updatedByStaff,hotelID,roomNum});
                
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
            if (stmt3 != null) {
                try {
                    stmt3.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt4 != null) {
                try {
                    stmt4.close();
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
    
    
    private void Logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout1ActionPerformed
        // TODO add your handling code here:
        Login l = new Login();
        sysExit();
        l.setVisible(true);

    }//GEN-LAST:event_Logout1ActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        
        if((boolean)Intermediate.getItem("isCateringStaff") || (boolean)Intermediate.getItem("isRoomServiceStaff")) {     
            ServiceRecords sr = new ServiceRecords();
            sysExit();
            sr.setVisible(true);
        } else {
            if((boolean)Intermediate.getItem("isFrontDeskStaff")) {     
                FrontDesk fd = new FrontDesk();
                sysExit();
                fd.setVisible(true);
            } else {
                Manager m = new Manager();
                sysExit();
                m.setVisible(true); 
            }
        }   

    }//GEN-LAST:event_HomeActionPerformed
    
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
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewRecord().setVisible(true);
            }
        });
    }
    
    
        
        public void sysExit(){
        WindowEvent winClosing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton Logout1;
    public javax.swing.JTable invoiceFrame;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
