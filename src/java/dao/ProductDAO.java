/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CategoryDTO;
import dto.ProductDTO;
import java.io.Serializable;
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
public class ProductDAO implements Serializable {

//    private final String SEARCH_BOOK_BY_NAME = " Select bookID, bookName, imagePath, quantity, price, status From tblBook Where bookName like ?";
//    private final String UPDATE_STATUS_BOOK = " Update tblBook Set status = 0 Where bookID = ?";
//    private final String UPDATE_BOOK = " Update tblBook Set bookName = ?, imagePath = ?, quantity = ?, price = ?, status = ? Where bookID = ?";
//    private final String CHECK_DUPLICATE_BOOKID = " Select bookID From tblBook Where bookID = ?";
//    private final String INSERT_BOOK = " Insert Into tblBook(bookID, bookName, imagePath, quantity, price, status) Values (?,?,?,?,?,?)";
//    private final String SHOW_ALL_BOOK_BY_NAME = " Select bookID, bookName, imagePath, quantity, price, status From tblBook ";
    private final String SQL_GET_ALL_PRODUCT = "SELECT productId, productName, quantity, price, description, imageUrl, status, categoryId "
                        + "FROM products "
                        + "ORDER BY status DESC";
    private final String SQL_GET_PRODUCT_BY_ID = "SELECT productId, productName, quantity, price, description, imageUrl, status, categoryId "
                        + "FROM products "
                        + "WHERE productId = ? ";
    
    private final String SQL_GET_CATEGORY_BY_ID = "SELECT categoryId, categoryName, description "
                        + "FROM categories "
                        + "WHERE categoryId = ? ";
    
//    public boolean updateStatusBook(String bookID)
//            throws SQLException, NamingException {
//        boolean check = false;
//        Connection con = null;
//        PreparedStatement stm = null;
//        try {
//            con = DBHepler.makeConnection();
//            if (con != null) {
//                String sql = "Update tblBook "
//                        + " Set status = 0 "
//                        + " Where bookID = ?";
//                stm = con.prepareStatement(sql);
//                stm = con.prepareStatement(UPDATE_STATUS_BOOK);
//                stm.setString(1, bookID);
//                check = stm.executeUpdate() > 0;
//            }
//        } finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return check;
//    }
//    
//    public List<TblBookDTO> searchBookByName(String searchBookName)
//            throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        List<TblBookDTO> listBook = new ArrayList<>();
//        try {
//            con = DBHepler.makeConnection();
//            if (con != null) {
//                stm = con.prepareStatement(SEARCH_BOOK_BY_NAME);
//                stm.setString(1, "%" + searchBookName + "%");
//                rs = stm.executeQuery();
//                while (rs.next()) {
//                    String bookID = rs.getString("bookID");
//                    String bookName = rs.getString("bookName");
//                    String imagePath = rs.getString("imagePath");
//                    int quantity = rs.getInt("quantity");
//                    double price = rs.getDouble("price");
//                    boolean status = rs.getBoolean("status");
//                    listBook.add(new TblBookDTO(bookID, bookName, imagePath, quantity, price, status));
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//
//        }
//        return listBook;
//    }
//
//    public boolean updateBook(TblBookDTO dtoBook)
//            throws SQLException, NamingException {
//        boolean check = false;
//        Connection con = null;
//        PreparedStatement stm = null;
//        try {
//            con = DBHepler.makeConnection();
//            if (con != null) {
//                stm = con.prepareStatement(UPDATE_BOOK);
//                stm.setString(1, dtoBook.getBookName());
//                stm.setString(2, dtoBook.getImagePath());
//                stm.setInt(3, dtoBook.getQuantity());
//                stm.setDouble(4, dtoBook.getPrice());
//                stm.setBoolean(5, dtoBook.isStatus());
//                stm.setString(6, dtoBook.getBookID());
//                check = stm.executeUpdate() > 0;
//            }
//        } finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return check;
//    }
//
//    public boolean checkDuplicate(String bookID)
//            throws SQLException, NamingException {
//        boolean check = false;
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            con = DBHepler.makeConnection();
//            if (con != null) {
//                stm = con.prepareStatement(CHECK_DUPLICATE_BOOKID);
//                stm.setString(1, bookID);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    check = true;
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return check;
//    }
//
//    public boolean insertBook(TblBookDTO book)
//            throws SQLException, NamingException {
//        if (book == null) {
//            return false;
//        }
//        Connection con = null;
//        PreparedStatement stm = null;
//        try {
//            con = DBHepler.makeConnection();
//            if (con != null) {
//                stm = con.prepareStatement(INSERT_BOOK);
//                stm.setString(1, book.getBookID());
//                stm.setString(2, book.getBookName());
//                stm.setString(3, book.getImagePath());
//                stm.setInt(4, book.getQuantity());
//                stm.setDouble(5, book.getPrice());
//                stm.setBoolean(6, book.isStatus());
//                int rowEffect = stm.executeUpdate();
//                if (rowEffect > 0) {
//                    return true;
//                }
//            }
//        } finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                stm.close();
//            }
//        }
//        return false;
//    }

//    public List<ProductDTO> showAllBookByName()
//            throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        List<ProductDTO> listBook = new ArrayList<>();
//        try {
//            con = DBHepler.makeConnection();
//            if (con != null) {
//                stm = con.prepareStatement(SHOW_ALL_BOOK_BY_NAME);
//                rs = stm.executeQuery();
//                while (rs.next()) {
//                    String bookID = rs.getString("bookID");
//                    String bookName = rs.getString("bookName");
//                    String imagePath = rs.getString("imagePath");
//                    int quantity = rs.getInt("quantity");
//                    double price = rs.getDouble("price");
//                    boolean status = rs.getBoolean("status");
//                    listBook.add(new ProductDTO(bookID, bookName, imagePath, quantity, price, status));
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return listBook;
//    }
//
    public ProductDTO getProductById(int productId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_PRODUCT_BY_ID;
                stm = con.prepareStatement(sql);
                stm.setInt(1, productId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String productName = rs.getString("productName");;
                    int quantity = rs.getInt("quantity");;
                    int price = rs.getInt("price");
                    String description = rs.getString("description");
                    String imageUrl = rs.getString("imageUrl");
                    boolean status = rs.getBoolean("status");
                    int categoryID = rs.getInt("categoryId");
                    CategoryDTO category = getCategoryById(categoryID);
                    ProductDTO productDTO = new ProductDTO(productId, productName, quantity, price, description, imageUrl, status, category);
                    return productDTO;
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
        return null;
    }
    
    private CategoryDTO getCategoryById(int categoryId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = null;
        try {
            if (con == null) {
                con = DBHepler.makeConnection();
            }            
                String sql = SQL_GET_CATEGORY_BY_ID;
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");
                    CategoryDTO categoryDTO = new CategoryDTO(categoryId, categoryName, description);
                    return categoryDTO;
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
        return null;
    }

    public List<ProductDTO> getAllProduct() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> listProduct = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_ALL_PRODUCT;
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    String productName = rs.getString("productName");;
                    int quantity = rs.getInt("quantity");;
                    int price = rs.getInt("price");
                    String description = rs.getString("description");
                    String imageUrl = rs.getString("imageUrl");
                    boolean status = rs.getBoolean("status");
                    int categoryID = rs.getInt("categoryId");
                    CategoryDTO category = getCategoryById(categoryID);
                    if (listProduct == null) {
                        listProduct = new ArrayList<>();
                    }
                    ProductDTO dto = new ProductDTO(productId, productName, quantity, price, description, imageUrl, status, category);
                    listProduct.add(dto);
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
        return listProduct;
    }
}
