/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SuperAccess;

import DatabaseManagement.Attribute;
import DatabaseManagement.Attribute.Name;
import DatabaseManagement.AttributeCollection;
import DatabaseManagement.Filters;
import DatabaseManagement.Table;
import General.Controller;
import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class Flight extends TableForm {

    public Flight() {
        initComponents();
        initBaseComponents(Table.FLIGHT, TopLabel, ActionBtn);

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
        fNumberField = new javax.swing.JTextField();
        ActionBtn = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        gateNumberCMB = new javax.swing.JComboBox<>();
        statusCMB = new javax.swing.JComboBox<>();
        departureTimeField = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        durationField = new javax.swing.JTextField();
        firstSeatsField = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        businessSeatsField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        economySeatsField = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        distanceField = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        airplaneNumberCMB = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        airlineCodeCMB = new javax.swing.JComboBox<>();
        outcomingAirportCMB = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        arrivalTimeField = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        incomingAirportCMB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        TopLabel.setBackground(new java.awt.Color(0, 0, 0));
        TopLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        TopLabel.setForeground(new java.awt.Color(255, 255, 255));
        TopLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TopLabel.setText("Gates");
        TopLabel.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setForeground(new java.awt.Color(0, 102, 0));
        jLabel25.setText("Please fill in this form then submit");

        jLabel26.setBackground(new java.awt.Color(204, 204, 204));
        jLabel26.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Fnumber:");
        jLabel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel26.setOpaque(true);

        fNumberField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        fNumberField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        ActionBtn.setBackground(new java.awt.Color(0, 204, 0));
        ActionBtn.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        ActionBtn.setForeground(new java.awt.Color(255, 255, 255));
        ActionBtn.setText("Submit");

        jLabel27.setBackground(new java.awt.Color(204, 204, 204));
        jLabel27.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Status:");
        jLabel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel27.setOpaque(true);

        jLabel28.setBackground(new java.awt.Color(204, 204, 204));
        jLabel28.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("DepartureTime:");
        jLabel28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel28.setOpaque(true);

        jLabel29.setBackground(new java.awt.Color(204, 204, 204));
        jLabel29.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Gate Number:");
        jLabel29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel29.setOpaque(true);

        gateNumberCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gateNumberCMB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        statusCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        statusCMB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        departureTimeField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        departureTimeField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel30.setBackground(new java.awt.Color(204, 204, 204));
        jLabel30.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Duration:");
        jLabel30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel30.setOpaque(true);

        durationField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        durationField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        firstSeatsField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        firstSeatsField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel31.setBackground(new java.awt.Color(204, 204, 204));
        jLabel31.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("FirstSeats:");
        jLabel31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel31.setOpaque(true);

        jLabel32.setBackground(new java.awt.Color(204, 204, 204));
        jLabel32.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("BusinessSeats:");
        jLabel32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel32.setOpaque(true);

        businessSeatsField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        businessSeatsField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel33.setBackground(new java.awt.Color(204, 204, 204));
        jLabel33.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("EconomySeats:");
        jLabel33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel33.setOpaque(true);

        economySeatsField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        economySeatsField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel34.setBackground(new java.awt.Color(204, 204, 204));
        jLabel34.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Distance:");
        jLabel34.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel34.setOpaque(true);

        distanceField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        distanceField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel35.setBackground(new java.awt.Color(204, 204, 204));
        jLabel35.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("ArrivalTime:");
        jLabel35.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel35.setOpaque(true);

        jLabel36.setBackground(new java.awt.Color(204, 204, 204));
        jLabel36.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("AirplaneNumber:");
        jLabel36.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel36.setOpaque(true);

        airplaneNumberCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        airplaneNumberCMB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel37.setBackground(new java.awt.Color(204, 204, 204));
        jLabel37.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("AirlineCode:");
        jLabel37.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel37.setOpaque(true);

        airlineCodeCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        airlineCodeCMB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        outcomingAirportCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        outcomingAirportCMB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel38.setBackground(new java.awt.Color(204, 204, 204));
        jLabel38.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("From:");
        jLabel38.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel38.setOpaque(true);

        arrivalTimeField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        arrivalTimeField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel39.setBackground(new java.awt.Color(204, 204, 204));
        jLabel39.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("To:");
        jLabel39.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel39.setOpaque(true);

        incomingAirportCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        incomingAirportCMB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(statusCMB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(departureTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(gateNumberCMB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(firstSeatsField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(businessSeatsField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(economySeatsField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(distanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(arrivalTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(airplaneNumberCMB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(airlineCodeCMB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(outcomingAirportCMB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(incomingAirportCMB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addComponent(fNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(firstSeatsField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(businessSeatsField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(economySeatsField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(distanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(statusCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(departureTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(arrivalTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(gateNumberCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(airplaneNumberCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(airlineCodeCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(outcomingAirportCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(incomingAirportCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(ActionBtn)
                .addContainerGap(36, Short.MAX_VALUE))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActionBtn;
    private javax.swing.JLabel TopLabel;
    private javax.swing.JComboBox<String> airlineCodeCMB;
    private javax.swing.JComboBox<String> airplaneNumberCMB;
    private javax.swing.JTextField arrivalTimeField;
    private javax.swing.JTextField businessSeatsField;
    private javax.swing.JTextField departureTimeField;
    private javax.swing.JTextField distanceField;
    private javax.swing.JTextField durationField;
    private javax.swing.JTextField economySeatsField;
    private javax.swing.JTextField fNumberField;
    private javax.swing.JTextField firstSeatsField;
    private javax.swing.JComboBox<String> gateNumberCMB;
    private javax.swing.JComboBox<String> incomingAirportCMB;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> outcomingAirportCMB;
    private javax.swing.JComboBox<String> statusCMB;
    // End of variables declaration//GEN-END:variables
    @Override
    public AttributeCollection getAllAttributes() {
        AttributeCollection collection = new AttributeCollection();

        String flightNum = fNumberField.getText().trim();
        String duration = durationField.getText().trim();
        String firstSeats = firstSeatsField.getText().trim();
        String businessSeats = businessSeatsField.getText().trim();
        String economySeats = economySeatsField.getText().trim();
        String distance = distanceField.getText().trim();
        String status = statusCMB.getSelectedItem().toString().trim();
        String departureTime = departureTimeField.getText().trim();
        String arrivalTime = arrivalTimeField.getText().trim();
        String gateNumber = gateNumberCMB.getSelectedItem().toString().trim();
        String airplaneNumber = airplaneNumberCMB.getSelectedItem().toString().trim();
        String airlineCode = airlineCodeCMB.getSelectedItem().toString().trim();
        String incomingAirport = incomingAirportCMB.getSelectedItem().toString().trim();
        String outgoingAirport = outcomingAirportCMB.getSelectedItem().toString().trim();

        collection.add(new Attribute(Name.FNUMBER, flightNum, table));
        collection.add(new Attribute(Name.DURATION, duration, table));
        collection.add(new Attribute(Name.FIRST_SEATS, firstSeats, table));
        collection.add(new Attribute(Name.BUSINESS_SEATS, businessSeats, table));
        collection.add(new Attribute(Name.ECONOMY_SEATS, economySeats, table));
        collection.add(new Attribute(Name.DISTANCE, distance, table));
        collection.add(new Attribute(Name.STATUS, status, table));
        collection.add(new Attribute(Name.DEPARTURE, departureTime, table));
        collection.add(new Attribute(Name.ARRIVAL, arrivalTime, table));
        collection.add(new Attribute(Name.GATES_GNUMBER, gateNumber, table));
        collection.add(new Attribute(Name.AIRPLANE_NUMBER, airplaneNumber, table));
        collection.add(new Attribute(Name.AIRLINE_CODE, airlineCode, table));
        collection.add(new Attribute(Name.AIRPORT_INCOMING_CODE, incomingAirport, table));
        collection.add(new Attribute(Name.AIRPORT_OUTCOMING_CODE2, outgoingAirport, table));

        return collection;
    }

    @Override
    public Filters getBrowsingFilters() {
        Filters filters = new Filters();

        String flightNum = fNumberField.getText().trim();
        String duration = durationField.getText().trim();
        String firstSeats = firstSeatsField.getText().trim();
        String businessSeats = businessSeatsField.getText().trim();
        String economySeats = economySeatsField.getText().trim();
        String distance = distanceField.getText().trim();
        String status = statusCMB.getSelectedItem().toString().trim();
        String departureTime = departureTimeField.getText().trim();
        String arrivalTime = arrivalTimeField.getText().trim();
        String gateNumber = gateNumberCMB.getSelectedItem().toString().trim();
        String airplaneNumber = airplaneNumberCMB.getSelectedItem().toString().trim();
        String airlineCode = airlineCodeCMB.getSelectedItem().toString().trim();
        String incomingAirport = incomingAirportCMB.getSelectedItem().toString().trim();
        String outgoingAirport = outcomingAirportCMB.getSelectedItem().toString().trim();

        if (!flightNum.isBlank()) {
            filters.addLike(new Attribute(Name.FNUMBER, "%" + flightNum + "%", table));
        }
        if (!duration.isBlank()) {
            filters.addEqual(new Attribute(Name.DURATION, duration, table));
        }
        if (!firstSeats.isBlank()) {
            filters.addEqual(new Attribute(Name.FIRST_SEATS, firstSeats, table));
        }
        if (!businessSeats.isBlank()) {
            filters.addEqual(new Attribute(Name.BUSINESS_SEATS, businessSeats, table));
        }
        if (!economySeats.isBlank()) {
            filters.addEqual(new Attribute(Name.ECONOMY_SEATS, economySeats, table));
        }
        if (!distance.isBlank()) {
            filters.addEqual(new Attribute(Name.DISTANCE, distance, table));
        }
        if (!status.isBlank()) {
            filters.addLike(new Attribute(Name.STATUS, "%" + status + "%", table));
        }
        if (!departureTime.isBlank()) {
            filters.addEqual(new Attribute(Name.DEPARTURE, departureTime, table));
        }
        if (!arrivalTime.isBlank()) {
            filters.addEqual(new Attribute(Name.ARRIVAL, arrivalTime, table));
        }
        if (!gateNumber.isBlank()) {
            filters.addEqual(new Attribute(Name.GATES_GNUMBER, gateNumber, table));
        }
        if (!airplaneNumber.isBlank()) {
            filters.addLike(new Attribute(Name.AIRPLANE_NUMBER, "%" + airplaneNumber + "%", table));
        }
        if (!airlineCode.isBlank()) {
            filters.addLike(new Attribute(Name.AIRLINE_CODE, "%" + airlineCode + "%", table));
        }
        if (!incomingAirport.isBlank()) {
            filters.addLike(new Attribute(Name.AIRPORT_INCOMING_CODE, "%" + incomingAirport + "%", table));
        }
        if (!outgoingAirport.isBlank()) {
            filters.addLike(new Attribute(Name.AIRPORT_OUTCOMING_CODE2, "%" + outgoingAirport + "%", table));
        }
        return filters;
    }

    @Override
    public Filters getPKFilter() {
        Filters filters = new Filters();

        String flightNumber = fNumberField.getText().trim();
        filters.addEqual(new Attribute(Name.FNUMBER, flightNumber, table));

        return filters;

    }

    @Override
    public void enableFields() {
        fNumberField.setEnabled(true);
        durationField.setEnabled(true);
        firstSeatsField.setEnabled(true);
        businessSeatsField.setEnabled(true);
        economySeatsField.setEnabled(true);
        distanceField.setEnabled(true);
        statusCMB.setEnabled(true);
        departureTimeField.setEnabled(true);
        arrivalTimeField.setEnabled(true);
        gateNumberCMB.setEnabled(true);
        airplaneNumberCMB.setEnabled(true);
        airlineCodeCMB.setEnabled(true);
        incomingAirportCMB.setEnabled(true);
        outcomingAirportCMB.setEnabled(true);
    }

    @Override
    public void disableUnmodifiableFields() {
        fNumberField.setEnabled(false);
        airlineCodeCMB.setEnabled(false);
        incomingAirportCMB.setEnabled(false);
        outcomingAirportCMB.setEnabled(false);
    }

    @Override
    public void populateFields(AttributeCollection toPopulateWith) {
        Controller controller = new Controller();
        ComboBoxFactory.populateStatusCMB(statusCMB);
        ComboBoxFactory.populateGateNumberCMB(gateNumberCMB);
        ComboBoxFactory.populateAirplaneNumberCMB(airplaneNumberCMB);
        ComboBoxFactory.populateAirlineCodeCMB(airlineCodeCMB);
        ComboBoxFactory.populateAirportCodeCMB(incomingAirportCMB);
        ComboBoxFactory.populateAirportCodeCMB(outcomingAirportCMB);

        String flightNum = toPopulateWith.getValue(new Attribute(Name.FNUMBER, table));
        String duration = toPopulateWith.getValue(new Attribute(Name.DURATION, table));
        String firstSeats = toPopulateWith.getValue(new Attribute(Name.FIRST_SEATS, table));
        String businessSeats = toPopulateWith.getValue(new Attribute(Name.BUSINESS_SEATS, table));
        String economySeats = toPopulateWith.getValue(new Attribute(Name.ECONOMY_SEATS, table));
        String distance = toPopulateWith.getValue(new Attribute(Name.DISTANCE, table));
        String status = toPopulateWith.getValue(new Attribute(Name.STATUS, table));
        String departureTime = toPopulateWith.getValue(new Attribute(Name.DEPARTURE, table));
        String arrivalTime = toPopulateWith.getValue(new Attribute(Name.ARRIVAL, table));
        String gateNumber = toPopulateWith.getValue(new Attribute(Name.GATES_GNUMBER, table));
        String airplaneNumber = toPopulateWith.getValue(new Attribute(Name.AIRPLANE_NUMBER, table));
        String airlineCode = toPopulateWith.getValue(new Attribute(Name.AIRLINE_CODE, table));
        String incomingAirport = toPopulateWith.getValue(new Attribute(Name.AIRPORT_INCOMING_CODE, table));
        String outgoingAirport = toPopulateWith.getValue(new Attribute(Name.AIRPORT_OUTCOMING_CODE2, table));

        fNumberField.setText(flightNum);
        durationField.setText(duration);
        firstSeatsField.setText(firstSeats);
        businessSeatsField.setText(businessSeats);
        economySeatsField.setText(economySeats);
        distanceField.setText(distance);
        statusCMB.setSelectedItem(status);
        departureTimeField.setText(departureTime);
        arrivalTimeField.setText(arrivalTime);
        gateNumberCMB.setSelectedItem(gateNumber);
        airplaneNumberCMB.setSelectedItem(airplaneNumber);
        airlineCodeCMB.setSelectedItem(airlineCode);
        incomingAirportCMB.setSelectedItem(incomingAirport);
        outcomingAirportCMB.setSelectedItem(outgoingAirport);
    }

    @Override
    public void clearFields() {
        ComboBoxFactory.populateStatusCMB(statusCMB);
        ComboBoxFactory.populateGateNumberCMB(gateNumberCMB);
        ComboBoxFactory.populateAirplaneNumberCMB(airplaneNumberCMB);
        ComboBoxFactory.populateAirlineCodeCMB(airlineCodeCMB);
        ComboBoxFactory.populateAirportCodeCMB(incomingAirportCMB);
        ComboBoxFactory.populateAirportCodeCMB(outcomingAirportCMB);

        fNumberField.setText("");
        durationField.setText("");
        firstSeatsField.setText("");
        businessSeatsField.setText("");
        economySeatsField.setText("");
        distanceField.setText("");
        statusCMB.setSelectedItem("");
        departureTimeField.setText("");
        arrivalTimeField.setText("");
        gateNumberCMB.setSelectedItem("");
        airplaneNumberCMB.setSelectedItem("");
        airlineCodeCMB.setSelectedItem("");
        incomingAirportCMB.setSelectedItem("");
        outcomingAirportCMB.setSelectedItem("");

    }
}