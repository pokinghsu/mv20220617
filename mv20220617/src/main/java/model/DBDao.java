package model;

import java.sql.*;

public class DBDao {
	public static boolean InsertCoffee(String coffee, int sale, int total, int supplier, double price)
			throws SQLException {
		Connection con = null;
		PreparedStatement insert = null;

		String insertStatement = "insert into classicmodels.COFFEES( COF_NAME , SUP_ID , PRICE , SALES ,TOTAL)"
				+ " values ( ? , ? ,? ,? ,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Taipei",
					"root", "1234");

			con.setAutoCommit(false);
			insert = con.prepareStatement(insertStatement);

			// for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) { }
			insert.setString(1, coffee);
			insert.setInt(2, supplier);
			insert.setDouble(3, price);
			insert.setInt(4, sale);
			insert.setInt(5, total);
			int row = insert.executeUpdate();
			con.commit();
			if (row > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} finally {
			if (insert != null) {
				insert.close();
			}
			if (insert != null) {
				insert.close();
			}
			con.setAutoCommit(true);
		}
		return false;
	}

	public static boolean updateCoffeeSales(String coffee, int sale, int total) throws SQLException {
		Connection con = null;
		PreparedStatement updateSales = null;
		PreparedStatement updateTotal = null;

		String updateString = "update  classicmodels.COFFEES " + "set SALES = ? where COF_NAME = ?";

		String updateStatement = "update   classicmodels.COFFEES " + "set TOTAL = TOTAL + ? " + "where COF_NAME = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?serverTimezone=Asia/Taipei",
					"root", "1234");

			con.setAutoCommit(false);
			updateSales = con.prepareStatement(updateString);
			updateTotal = con.prepareStatement(updateStatement);

			// for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) { }
			updateSales.setInt(1, sale);
			updateSales.setString(2, coffee);
			int r1 = updateSales.executeUpdate();
			updateTotal.setInt(1, total);
			updateTotal.setString(2, coffee);
			int r2 = updateTotal.executeUpdate();
			if (r1 > 0 && r2 > 0) {
				con.commit();
				return true;
			} else
				con.rollback();

			return false;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					System.out.println(e.getMessage());
				}
			}
		} finally {
			if (updateSales != null) {
				updateSales.close();
			}
			if (updateTotal != null) {
				updateTotal.close();
			}
			con.setAutoCommit(true);
		}
		return false;
	}

	public static boolean  DeleteCoffee(String coffee) throws SQLException {
		Connection con = null;
		PreparedStatement delete = null;

		String insertStatement = "delete from  classicmodels.COFFEES " + "where COF_NAME=? ";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Taipei",
					"root", "1234");

			con.setAutoCommit(false);
			delete = con.prepareStatement(insertStatement);

			// for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) { }
			delete.setString(1, coffee);
			int r=delete.executeUpdate();
			if(r>0) {
			   con.commit();
			   return true;
			}
			else
				con.rollback();
			
			return false;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} finally {
			if (delete != null) {
				delete.close();
			}
			if (delete != null) {
				delete.close();
			}
			con.setAutoCommit(true);
		}
		return false;
	}

}
