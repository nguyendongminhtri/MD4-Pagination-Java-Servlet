package rikkei.academy.service.category;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.Category;
import rikkei.academy.model.User;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceIMPL implements ICategoryService{
    Connection connection = ConnectMySQL.getConnection();
    private int totalElement;
//    private final String PAGE_CATEGORY = "SELECT SQL_CALC_FOUND_ROWS * FROM category LIMIT ?,?";
//    Statement stmt;
    IUserService userService = new UserServiceIMPL();
    private final String CREATE_CATEGORY = "INSERT INTO category(name, avatar, user_id) VALUES (?,?,?);";
    @Override
    public List<Category> findAll(int start, int elementOfPage) {
        List<Category> list=new ArrayList<>();
//        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM category limit "+start+","+noOfRecords;
        try{
            Statement statement = connection.createStatement();
//            statement = connection.createStatement();
            String PAGE_CATEGORY = "SELECT SQL_CALC_FOUND_ROWS * FROM category LIMIT "+start+","+elementOfPage;
            ResultSet resultSet = statement.executeQuery(PAGE_CATEGORY);
            while(resultSet.next()){
                Category category=new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setAvatar(resultSet.getString("avatar"));
                list.add(category);
            }
//            return .close();
//            resultSet.close();
            resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()){
                this.totalElement = resultSet.getInt(1);
            }

        }catch(Exception e){System.out.println(e);}
        return list;
    }



    @Override
    public void save(Category category, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CATEGORY);
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getAvatar());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Category findById(int id) {
        return null;
    }
    public int getNoOfRecords() {
        return totalElement;
    }
}
