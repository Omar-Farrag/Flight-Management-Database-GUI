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

/**
 *
 * @author Dell
 */
public class Ticket extends TableForm {

    public Ticket() {
        initComponents();
        initBaseComponents(Table.TICKET, TopLabel, ActionBtn);

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
        numberField = new javax.swing.JTextField();
        ActionBtn = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        passengerSSNCMB = new javax.swing.JComboBox<>();
        flightNumberCMB = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        priceField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        TopLabel.setBackground(new java.awt.Color(0, 0, 0));
        TopLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        TopLabel.setForeground(new java.awt.Color(255, 255, 255));
        TopLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TopLabel.setText("Ticket");
        TopLabel.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setForeground(new java.awt.Color(0, 102, 0));
        jLabel25.setText("Please fill in this form then submit");

        jLabel26.setBackground(new java.awt.Color(204, 204, 204));
        jLabel26.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Number:");
        jLabel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel26.setOpaque(true);

        numberField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        numberField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        ActionBtn.setBackground(new java.awt.Color(0, 204, 0));
        ActionBtn.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        ActionBtn.setForeground(new java.awt.Color(255, 255, 255));
        ActionBtn.setText("Submit");

        jLabel27.setBackground(new java.awt.Color(204, 204, 204));
        jLabel27.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Flight Number:");
        jLabel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel27.setOpaque(true);

        jLabel28.setBackground(new java.awt.Color(204, 204, 204));
        jLabel28.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Passenger:");
        jLabel28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel28.setOpaque(true);

        passengerSSNCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        passengerSSNCMB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        flightNumberCMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        flightNumberCMB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel29.setBackground(new java.awt.Color(204, 204, 204));
        jLabel29.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Price(AED):");
        jLabel29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel29.setOpaque(true);

        priceField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        priceField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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
                                .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(flightNumberCMB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(passengerSSNCMB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(flightNumberCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(passengerSSNCMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(ActionBtn)
                .addContainerGap(166, Short.MAX_VALUE))
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
    private javax.swing.JComboBox<String> flightNumberCMB;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numberField;
    private javax.swing.JComboBox<String> passengerSSNCMB;
    private javax.swing.JTextField priceField;
    // End of variables declaration//GEN-END:variables
    @Override
    public AttributeCollection getAllAttributes() {
        AttributeCollection collection = new AttributeCollection();

        String number = numberField.getText().trim();
        String price = priceField.getText().trim();
        String flightNumber = flightNumberCMB.getSelectedItem().toString().trim();
        String passengerSSN = passengerSSNCMB.getSelectedItem().toString().trim();

        collection.add(new Attribute(Name.NUM, number, table));
        collection.add(new Attribute(Name.PRICE, price, table));
        collection.add(new Attribute(Name.FLIGHT_FNUMBER, flightNumber, table));
        collection.add(new Attribute(Name.PASSENGER_SSN, passengerSSN, table));

        return collection;
    }

    @Override
    public Filters getBrowsingFilters() {
        Filters filters = new Filters();

        String number = numberField.getText().trim();
        String price = priceField.getText().trim();
        String flightNumber = flightNumberCMB.getSelectedItem().toString().trim();
        String passengerSSN = passengerSSNCMB.getSelectedItem().toString().trim();

        if (!number.isBlank()) {
            filters.addLike(new Attribute(Name.NUM, "%" + number + "%", table));
        }
        if (!price.isBlank()) {
            filters.addEqual(new Attribute(Name.PRICE, price, table));
        }
        if (!flightNumber.isBlank()) {
            filters.addLike(new Attribute(Name.FLIGHT_FNUMBER, "%" + flightNumber + "%", table));
        }
        if (!passengerSSN.isBlank()) {
            filters.addEqual(new Attribute(Name.PASSENGER_SSN, passengerSSN, table));
        }

        return filters;
    }

    @Override
    public Filters getPKFilter() {
        Filters filters = new Filters();

        String number = numberField.getText().trim();
        filters.addEqual(new Attribute(Name.NUM, number, table));

        return filters;

    }

    @Override
    public void enableFields() {
        numberField.setEnabled(true);
        priceField.setEnabled(true);
        flightNumberCMB.setEnabled(true);
        passengerSSNCMB.setEnabled(true);
    }

    @Override
    public void disableUnmodifiableFields() {
        numberField.setEnabled(false);
    }

    @Override
    public void populateFields(AttributeCollection toPopulateWith) {

        ComboBoxFactory.populateFlightNumberCMB(flightNumberCMB);
        ComboBoxFactory.populatePassengerCMB(passengerSSNCMB);

        String number = toPopulateWith.getValue(new Attribute(Name.NUM, table));
        String seat = toPopulateWith.getValue(new Attribute(Name.SEAT, table));
        String price = toPopulateWith.getValue(new Attribute(Name.PRICE, table));
        String flightNumber = toPopulateWith.getValue(new Attribute(Name.FLIGHT_FNUMBER, table));
        String passengerSSN = toPopulateWith.getValue(new Attribute(Name.PASSENGER_SSN, table));

        numberField.setText(number);
        priceField.setText(price);
        flightNumberCMB.setSelectedItem(flightNumber);
        passengerSSNCMB.setSelectedItem(passengerSSN);
    }

    @Override
    public void clearFields() {
        ComboBoxFactory.populateFlightNumberCMB(flightNumberCMB);
        ComboBoxFactory.populatePassengerCMB(passengerSSNCMB);

        numberField.setText("");
        priceField.setText("");
        flightNumberCMB.setSelectedItem("");
        passengerSSNCMB.setSelectedItem("");

    }
}
