/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Queries;

import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public interface SearchQueryViewer {

    /**
     * Filters the records displayed in the viewer using the given filter clause
     *
     * @param filters Filter clause starting from "where ... "
     * @throws SQLException
     */
    public void applyBrowsingFilters(String filters) throws SQLException;
}
