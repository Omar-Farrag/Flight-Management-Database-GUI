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
public class Person extends TableForm {

    /**
     * Creates new form InsertForm
     */
    public Person() {
        initComponents();

        initBaseComponents(Table.PERSON, TopLabel, ActionBtn);
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
        ssnField = new javax.swing.JTextField();
        ActionBtn = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        fnameField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        lnameField = new javax.swing.JTextField();
        ageField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        TopLabel.setBackground(new java.awt.Color(0, 0, 0));
        TopLabel.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        TopLabel.setForeground(new java.awt.Color(255, 255, 255));
        TopLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TopLabel.setText("Person");
        TopLabel.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setForeground(new java.awt.Color(0, 102, 0));
        jLabel25.setText("Please fill in this form then submit");

        jLabel26.setBackground(new java.awt.Color(204, 204, 204));
        jLabel26.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("SSN:");
        jLabel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel26.setOpaque(true);

        ssnField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        ssnField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        ActionBtn.setBackground(new java.awt.Color(0, 204, 0));
        ActionBtn.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        ActionBtn.setForeground(new java.awt.Color(255, 255, 255));
        ActionBtn.setText("Submit");

        jLabel27.setBackground(new java.awt.Color(204, 204, 204));
        jLabel27.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("FName:");
        jLabel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel27.setOpaque(true);

        fnameField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        fnameField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel28.setBackground(new java.awt.Color(204, 204, 204));
        jLabel28.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("LName:");
        jLabel28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel28.setOpaque(true);

        lnameField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        lnameField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        ageField.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        ageField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel29.setBackground(new java.awt.Color(204, 204, 204));
        jLabel29.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Age:");
        jLabel29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel29.setOpaque(true);

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ssnField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ActionBtn)
                .addGap(305, 305, 305))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(ssnField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(fnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(ActionBtn)
                .addContainerGap(174, Short.MAX_VALUE))
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
    private javax.swing.JTextField ageField;
    private javax.swing.JTextField fnameField;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lnameField;
    private javax.swing.JTextField ssnField;
    // End of variables declaration//GEN-END:variables
    @Override
    public AttributeCollection getAllAttributes() {
        AttributeCollection collection = new AttributeCollection();

        String ssn = ssnField.getText().trim();
        String fname = fnameField.getText().trim();
        String lname = lnameField.getText().trim();
        String age = ageField.getText().trim();

        collection.add(new Attribute(Name.SSN, ssn, table));
        collection.add(new Attribute(Name.FNAME, fname, table));
        collection.add(new Attribute(Name.LNAME, lname, table));
        collection.add(new Attribute(Name.AGE, age, table));

        return collection;
    }

    @Override
    public Filters getBrowsingFilters() {
        Filters filters = new Filters();

        String ssn = ssnField.getText().trim();
        String fname = fnameField.getText().trim();
        String lname = lnameField.getText().trim();
        String age = ageField.getText().trim();

        if (!ssn.isBlank()) {
            filters.addEqual(new Attribute(Name.SSN, ssn, table));
        }
        if (!fname.isBlank()) {
            filters.addLike(new Attribute(Name.FNAME, "%" + fname + "%", table));
        }
        if (!lname.isBlank()) {
            filters.addLike(new Attribute(Name.LNAME, "%" + lname + "%", table));
        }
        if (!age.isBlank()) {
            filters.addEqual(new Attribute(Name.AGE, age, table));
        }
        return filters;
    }

    @Override
    public Filters getPKFilter() {
        Filters filters = new Filters();

        String ssn = ssnField.getText().trim();

        filters.addEqual(new Attribute(Name.SSN, ssn, table));

        return filters;

    }

    @Override
    public void enableFields() {
        ssnField.setEnabled(true);
        fnameField.setEnabled(true);
        lnameField.setEnabled(true);
        ageField.setEnabled(true);
    }

    @Override
    public void disableUnmodifiableFields() {
        ssnField.setEnabled(false);
    }

    @Override
    public void populateFields(AttributeCollection toPopulateWith) {

        String ssn = toPopulateWith.getValue(new Attribute(Name.SSN, table));
        String fname = toPopulateWith.getValue(new Attribute(Name.FNAME, table));
        String lname = toPopulateWith.getValue(new Attribute(Name.LNAME, table));
        String age = toPopulateWith.getValue(new Attribute(Name.AGE, table));

        ssnField.setText(ssn);
        fnameField.setText(fname);
        lnameField.setText(lname);
        ageField.setText(age);

    }

    @Override
    public void clearFields() {

        ssnField.setText("");
        fnameField.setText("");
        lnameField.setText("");
        ageField.setText("");

    }

}
