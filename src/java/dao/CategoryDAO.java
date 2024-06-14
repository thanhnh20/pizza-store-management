/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CategoryDTO;
import dto.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import ultils.DBHepler;

/**
 *
 * @author ASUS
 */
public class CategoryDAO {
    
    private final String SQL_GET_ALL_CATEGORY = "SELECT categoryId, categoryName, description "
                        + "FROM categories";
    
    public List<CategoryDTO> getAllCategory() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<CategoryDTO> listCate = new ArrayList<>();
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_ALL_CATEGORY;
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int categoryID = rs.getInt("categoryId");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");;
                    
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName, description);
                    listCate.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listCate;
    }
}
