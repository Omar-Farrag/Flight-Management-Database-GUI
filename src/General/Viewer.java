/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package General;

import DatabaseManagement.AttributeCollection;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public interface Viewer {

    public AttributeCollection getRow(int rowNum);

    public int[] getSelectedRows();

    public void applyBrowsingFilters() throws SQLException;

    public void applyModification() throws SQLException;

    public void applyInsertion() throws SQLException;

    public void applyDeletion() throws SQLException;
}
