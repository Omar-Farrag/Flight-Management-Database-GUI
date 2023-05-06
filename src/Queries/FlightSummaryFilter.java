/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Queries;

import SuperAccess.*;
import DatabaseManagement.Attribute;
import DatabaseManagement.Attribute.Name;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.Filters;
import DatabaseManagement.Table;
import FormManipulationStrategies.FormInitializationStrategy;
import General.Viewer;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Dell
 */
public class FlightSummaryFilter extends FilterFrame {

    public FlightSummaryFilter() {
        initComponents();
        initForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TopLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        ActionBtn = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        airportNameCMB = new javax.swing.JComboBox<>();
        minArrivalsField = new javax.swing.JTextField();
        maxArrivalsField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        minDeparturesField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        maxDeparturesField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        TopLabel.setBackground(new java.awt.Color(0, 0, 0));
        TopLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        TopLabel.setForeground(new java.awt.Color(255, 255, 255));
        TopLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TopLabel.setText("Number of Flights Summary");
        TopLabel.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setForeground(new java.awt.Color(0, 102, 0));
        jLabel25.setText("Please fill in this form then submit");

        jLabel26.setBackground(new java.awt.Color(204, 204, 204));
        jLabel26.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Airport:");
        jLabel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel26.setOpaque(true);

        ActionBtn.setBackground(new java.awt.Color(0, 204, 0));
        ActionBtn.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        ActionBtn.setForeground(new java.awt.Color(255, 255, 255));
        ActionBtn.setText("Submit");
        ActionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionBtnActionPerformed(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(204, 204, 204));
        jLabel27.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Min No. Arrivals:");
        jLabel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel27.setOpaque(true);

        jLabel28.setBackground(new java.awt.Color(204, 204, 204));
        jLabel28.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Max No. Arrivals:");
        jLabel28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel28.setOpaque(true);

        airportNameCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        airportNameCMB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        minArrivalsField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        minArrivalsField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        maxArrivalsField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        maxArrivalsField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel29.setBackground(new java.awt.Color(204, 204, 204));
        jLabel29.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Min No. Departures:");
        jLabel29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel29.setOpaque(true);

        minDeparturesField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        minDeparturesField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel30.setBackground(new java.awt.Color(204, 204, 204));
        jLabel30.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Max No. Departures:");
        jLabel30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel30.setOpaque(true);

        maxDeparturesField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        maxDeparturesField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maxDeparturesField, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(airportNameCMB, javax.swing.GroupLayout.Alignment.TRAILING, 0, 459, Short.MAX_VALUE)
                                .addComponent(minArrivalsField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(minDeparturesField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(maxArrivalsField, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(29, 29, 29))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ActionBtn)
                .addGap(302, 302, 302))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(airportNameCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(minArrivalsField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(maxArrivalsField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(minDeparturesField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(maxDeparturesField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(ActionBtn)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TopLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ActionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActionBtnActionPerformed
        handleFormSubmission();
    }//GEN-LAST:event_ActionBtnActionPerformed

    @Override
    protected HashSet<String> validateInput() {
        HashSet<String> errors = new HashSet<>();
        try {
            if (!minArrivalsField.getText().isBlank()) {

                BigDecimal number = new BigDecimal(minArrivalsField.getText().trim());
            }
        } catch (NumberFormatException ex) {
            errors.add("Entered min Arrivals is not a number");
        }
        try {
            if (!maxArrivalsField.getText().isBlank()) {
                BigDecimal number = new BigDecimal(maxArrivalsField.getText().trim());
            }
        } catch (NumberFormatException ex) {
            errors.add("Entered max Arrivals is not a number");
        }
        try {
            if (!minDeparturesField.getText().isBlank()) {
                BigDecimal number = new BigDecimal(minDeparturesField.getText().trim());
            }
        } catch (NumberFormatException ex) {
            errors.add("Entered min Departures is not a number");
        }
        try {
            if (!maxDeparturesField.getText().isBlank()) {
                BigDecimal number = new BigDecimal(maxDeparturesField.getText().trim());
            }
        } catch (NumberFormatException ex) {
            errors.add("Entered max Departures is not a number");
        }

        return errors;
    }

    @Override
    protected String prepareFilterClause() {
        String maxArrivals = maxArrivalsField.getText().trim();
        String minArrivals = minArrivalsField.getText().trim();
        String maxDepartures = maxDeparturesField.getText().trim();
        String minDepartures = minDeparturesField.getText().trim();

        ArrayList<String> conditions = new ArrayList<String>();

        if (!maxArrivals.isEmpty()) {
            conditions.add("A.NUM_ARRIVALS <= " + maxArrivals);
        }
        if (!minArrivals.isEmpty()) {
            conditions.add(" A.NUM_ARRIVALS >= " + minArrivals);
        }
        if (!maxDepartures.isEmpty()) {
            conditions.add("D.NUM_DEPARTURES <= " + maxDepartures);
        }
        if (!minDepartures.isEmpty()) {
            conditions.add("D.NUM_DEPARTRUES >= " + minDepartures);
        }
        if (!airportNameCMB.getSelectedItem().toString().trim().isEmpty()) {
            String airportName = airportNameCMB.getSelectedItem().toString().trim();
            airportName = airportName.replace("'", "''");
            conditions.add("P.NAME = '" + airportName + "'");
        }
        String filter = "";
        if (!conditions.isEmpty()) {
            filter = " WHERE " + String.join(" AND ", conditions);
        }
        return filter;
    }

    @Override
    protected void initForm() {
        ComboBoxFactory.populateAirportNameCMB(airportNameCMB);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActionBtn;
    private javax.swing.JLabel TopLabel;
    private javax.swing.JComboBox<String> airportNameCMB;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField maxArrivalsField;
    private javax.swing.JTextField maxDeparturesField;
    private javax.swing.JTextField minArrivalsField;
    private javax.swing.JTextField minDeparturesField;
    // End of variables declaration//GEN-END:variables

}
