

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Coffee;
import model.DBDao;

/**
 * Servlet implementation class CoffeeJsonServlet
 */
@WebServlet("/CoffeeJsonServlet")
public class CoffeeJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoffeeJsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream in=request.getInputStream();
		InputStreamReader ir=new InputStreamReader(in,"utf-8");
		BufferedReader br=new BufferedReader(ir);
		String data="";
        String temp=null;
        while((temp=br.readLine())!=null)
        	data+=temp;
		
		Gson g=new Gson();
		Coffee   c=g.fromJson(data, Coffee.class);
		boolean b=false;
		try {
		     b=DBDao.InsertCoffee(c.getCofName(), c.getSales(), c.getTotal(), c.getSupId(), c.getPrice());		     
		}catch(SQLException ex) {
			System.out.println("Json Servlet SQL Error:"+ex.getMessage());
		}
		response.setContentType("text/html;charset=utf8");
		if(b)
			response.getWriter().append("<h2>新增成功</h2>");
		else
			response.getWriter().append("<h2>新增失敗</h2>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
