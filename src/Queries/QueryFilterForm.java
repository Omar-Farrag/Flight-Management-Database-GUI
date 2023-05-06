/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Queries;

import DatabaseManagement.AttributeCollection;
import DatabaseManagement.Filters;
import DatabaseManagement.Table;
import FormManipulationStrategies.FormInitializationStrategy;
import General.Viewer;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Dell
 */
public interface QueryFilterForm {

    public void display(boolean visibility);

    public void setViewer(SearchQueryViewer viewer);

    public JFrame getFrame();

    public void initForm();
}
